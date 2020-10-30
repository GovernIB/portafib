package es.caib.portafib.app.worker;

import android.content.Context;

import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import java.util.concurrent.TimeUnit;

public class WorkerHelper {

    public static final String UNIQUE_TASK_NAME = "CONSULTA_PENDENTS_WORKER";

    public static void startWorker(Context context) {

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

}
