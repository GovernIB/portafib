package es.caib.portafib.app;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import androidx.preference.PreferenceManager;

import es.caib.portafib.app.worker.WorkerHelper;

public class NotificacionsReceiver extends BroadcastReceiver {

    public static final String ACTIVAR_NOTIFICACIONS = "es.caib.portafib.app.action.ACTIVAR_NOTIFICACIONS";
    public static final String DESACTIVAR_NOTIFICACIONS = "es.caib.portafib.app.action.DESACTIVAR_NOTIFICACIONS";
    public static final String CHANNEL_ID = "portafibId";

    private boolean notificacionsActivades = false;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("NotificacionsReceiver", "onReceive");

        switch (intent.getAction()) {
            case ACTIVAR_NOTIFICACIONS:
                if (isNotificacioSw(context) && !notificacionsActivades) {
                    activarNotificacions(context);
                }
                break;
            case DESACTIVAR_NOTIFICACIONS:
                if (!isNotificacioSw(context) && notificacionsActivades) {
                    desactivarNotificacions(context);
                }
                break;
            default:
                Log.e("NotificacionsReceiver", "Cridat amb un intent incorrecte: " + intent.getAction());
        }
    }

    private void activarNotificacions(Context context) {
        createNotificationChannel(context);
        WorkerHelper.startWorker(context);
        notificacionsActivades = true;
    }

    private void desactivarNotificacions(Context context) {
        destroyNotificacionChannel(context);
        WorkerHelper.cancelWorker(context);
        notificacionsActivades = false;
    }

    private boolean isNotificacioSw(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean("notificacions_sw", false);
    }

    private void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //CharSequence name = context.getString(R.string.channel_name);
            //String description = context.getString(R.string.channel_description);
            String name = "portafib";
            String description = "notificacions portafib";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            context.getSystemService(NotificationManager.class).createNotificationChannel(channel);
        }
    }

    private void destroyNotificacionChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.getSystemService(NotificationManager.class).deleteNotificationChannel(CHANNEL_ID);
        }
    }
}
