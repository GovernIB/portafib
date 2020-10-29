package es.caib.portafib.app.worker;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import java.util.List;
import java.util.concurrent.TimeUnit;

import es.caib.portafib.app.R;

public class WorkerHelper {

    public static final String UNIQUE_TASK_NAME = "CONSULTA_PENDENTS_WORKER";

    public static void startWorker(Context context) {

        createNotificationChannel(context);

        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();

        PeriodicWorkRequest workRequest = new PeriodicWorkRequest.Builder(
                ConsultaPendentsWorker.class, 15, TimeUnit.MINUTES)
                .setInitialDelay(5, TimeUnit.SECONDS)
                .setConstraints(constraints)
                .keepResultsForAtLeast(60, TimeUnit.SECONDS)
                .build();

        WorkManager workManager = WorkManager.getInstance(context);
        workManager.enqueueUniquePeriodicWork(
                UNIQUE_TASK_NAME,
                ExistingPeriodicWorkPolicy.REPLACE,
                workRequest);
    }

    public static void cancelWorker(Context context) {
        WorkManager workManager = WorkManager.getInstance(context);
        workManager.cancelUniqueWork(UNIQUE_TASK_NAME);
    }

    public static Data getLastWorkerData(Context context) {
        WorkManager workManager = WorkManager.getInstance(context);
        List<WorkInfo> workInfoList = workManager
                .getWorkInfosForUniqueWorkLiveData(UNIQUE_TASK_NAME)
                .getValue();
        if (workInfoList == null || workInfoList.isEmpty()) {
            Log.w("getLastWorkerData", "workInfoList: " + workInfoList);
            return null;
        }
        return workInfoList.get(0).getOutputData();
    }

    private static void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = context.getString(R.string.channel_id);
            CharSequence name = context.getString(R.string.channel_name);
            String description = context.getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    name,
                    importance);
            channel.setDescription(description);
            context.getSystemService(NotificationManager.class).createNotificationChannel(channel);
        }
    }
}
