package es.caib.portafib.app.worker;

import android.content.Context;

import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import java.util.concurrent.TimeUnit;

public class WorkerHelper {

    public static void startWorker(Context context) {

        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.NOT_ROAMING)
                .build();

        PeriodicWorkRequest workRequest = new PeriodicWorkRequest.Builder(
                ConsultaPendentsWorker.class, 15, TimeUnit.MINUTES)
                .setInitialDelay(30, TimeUnit.SECONDS)
                .setConstraints(constraints)
                .build();

        WorkManager workManager = WorkManager.getInstance(context);
        workManager.enqueue(workRequest);
    }

    public static void cancelWorker(Context context) {
        WorkManager workManager = WorkManager.getInstance(context);
        workManager.cancelAllWork();
    }
}
