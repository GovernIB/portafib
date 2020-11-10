package es.caib.portafib.app;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import es.caib.portafib.app.client.RestClient;
import es.caib.portafib.app.client.SSLUtil;
import es.caib.portafib.app.worker.WorkerHelper;

public class PortaFIBApplication extends Application {

    private final ExecutorService executorService = Executors.newFixedThreadPool(1);

    private RestClient restClient;

    private static PortaFIBApplication sInstance;

    public static PortaFIBApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        Log.i("PortaFIBApplication", "onCreate");
        super.onCreate();
        sInstance = this;
        // Per versions superiors a 26 cal crear el canal de notificacions
        createNotificationChannel();

        String alias = PreferenceHelper.getClientAliasCert(this);
        // Preparam l'SSL. S'ha de fer en background
        executorService.submit(() -> SSLUtil.prepareSsl(this, alias));

        // Instanciam el client rest
        restClient = new RestClient(ServerUrlUtil.getRestEndpoint(this));

        // Si les notificacions estan activades posam en marxa el worker!
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

    public RestClient getRestClient() {
        return restClient;
    }
}
