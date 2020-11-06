package es.caib.portafib.app.worker;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.List;

import es.caib.portafib.app.PortaFIBApplication;
import es.caib.portafib.app.R;
import es.caib.portafib.app.client.NotificacioRest;
import es.caib.portafib.app.NotificacioUtil;
import es.caib.portafib.app.client.RestClient;

public class ConsultaPendentsWorker extends Worker {

    private final RestClient restClient = PortaFIBApplication.getInstance().getRestClient();

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
            Context context = getApplicationContext();

            List<NotificacioRest> notificacions = restClient.getNotificacions();
            for (NotificacioRest notificacioRest : notificacions) {

                String label = NotificacioUtil.getLabel(context, notificacioRest);
                String url = NotificacioUtil.getUrl(context, notificacioRest);

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                PendingIntent pendingIntent = PendingIntent.getActivity(
                        context,
                        0,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

                String channelId = context.getString(R.string.channel_id);

                Notification notification = new NotificationCompat.Builder(context, channelId)
                        .setContentTitle(label)
                        .setSmallIcon(R.drawable.baseline_assignment_black_24)
                        .setContentIntent(pendingIntent)
                        .setOnlyAlertOnce(true)
                        .setAutoCancel(true)
                        .build();

                notificationManager.notify(notificacioRest.getRol().ordinal(), notification);
            }

            return Result.success();

        } catch (Exception e) {
            Log.e("consultaPedentWorker", "Error a doWork", e);
            return Result.failure();
        }
    }

}
