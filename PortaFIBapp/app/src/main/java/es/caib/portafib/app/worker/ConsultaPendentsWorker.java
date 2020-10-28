package es.caib.portafib.app.worker;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.List;

import es.caib.portafib.app.client.NotificacioRest;
import es.caib.portafib.app.client.RestClient;

public class ConsultaPendentsWorker extends Worker {

    private final NotificationManager notificationManager;

    public ConsultaPendentsWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        notificationManager = context.getSystemService(NotificationManager.class);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.i("consultaPedentWorker", "doWork");

        try {
            List<NotificacioRest> notificacions = RestClient.getNotificacions(getApplicationContext());
            for (int i = 0; i < notificacions.size(); i++) {
                NotificacioRest notificacioRest = notificacions.get(i);
                String title = notificacioRest.getPeticions().size() + " peticions pendents pel rol " + notificacioRest.getRol();
                Notification notification = new NotificationCompat.Builder(getApplicationContext(), "portafibId")
                        .setContentTitle(title)
                        .setSmallIcon(android.R.mipmap.sym_def_app_icon)
                        .build();
                notificationManager.notify(i, notification);
            }

            return Result.success();
        } catch (Exception e) {
            Log.e("consultaPedentWorker", "Error a doWork", e);
            return Result.failure();
        }
    }


}
