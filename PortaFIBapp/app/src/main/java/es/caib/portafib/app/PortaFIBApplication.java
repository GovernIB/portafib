package es.caib.portafib.app;

import android.app.Application;
import android.util.Log;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;

import es.caib.portafib.app.worker.WorkerHelper;

public class PortaFIBApplication extends Application {

    @Override
    public void onCreate() {
        Log.i("PortaFIBApplication", "onCreate");
        super.onCreate();

        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ORIGINAL_SERVER));

        if (PreferenceHelper.isNotificacioSw(getApplicationContext())) {
            WorkerHelper.startWorker(getApplicationContext());
        }
    }
}
