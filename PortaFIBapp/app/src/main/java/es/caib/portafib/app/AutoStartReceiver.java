package es.caib.portafib.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AutoStartReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (!Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            Log.e("AutoStartService", "Cridat amb un intent incorrecte: " + intent.getAction());
            return;
        }

        Log.i("AutoStartReceiver", "onReceive");
        context.sendBroadcast(new Intent(NotificacionsReceiver.ACTIVAR_NOTIFICACIONS));
    }

}