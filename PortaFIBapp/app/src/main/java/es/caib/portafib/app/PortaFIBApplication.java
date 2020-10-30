package es.caib.portafib.app;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.util.Log;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import es.caib.portafib.app.worker.WorkerHelper;

public class PortaFIBApplication extends Application {

    private final ExecutorService executorService = Executors.newFixedThreadPool(1);

    private static PortaFIBApplication sInstance;

    public static PortaFIBApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        Log.i("PortaFIBApplication", "onCreate");
        super.onCreate();
        sInstance = this;
        createNotificationChannel();
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ORIGINAL_SERVER));
        if (PreferenceHelper.isNotificacioSw(this)) {
            WorkerHelper.startWorker(this);
        }
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = getString(R.string.channel_id);
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    name,
                    importance);
            channel.setDescription(description);
            getSystemService(NotificationManager.class).createNotificationChannel(channel);
        }
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }
}
