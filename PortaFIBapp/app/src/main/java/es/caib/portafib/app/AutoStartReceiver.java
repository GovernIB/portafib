package es.caib.portafib.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import es.caib.portafib.app.worker.WorkerHelper;

/**
 * Permet rebre la notificació de que s'ha iniciat el mòbil per inciar el procés que
 * consulta les notificacions si està activat a les preferències.
 */
public class AutoStartReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (!Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            Log.e("AutoStartService", "Cridat amb un intent incorrecte: " + intent.getAction());
            return;
        }

        Log.i("AutoStartReceiver", "onReceive");
        if (PreferenceHelper.isNotificacioSw(context)) {
            WorkerHelper.startWorker(context);
        }
    }

}