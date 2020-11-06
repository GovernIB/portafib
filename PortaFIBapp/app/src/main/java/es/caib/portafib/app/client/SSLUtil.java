package es.caib.portafib.app.client;

import android.content.Context;
import android.util.Log;

import java.security.GeneralSecurityException;
import java.security.SecureRandom;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

public class SSLUtil {

    public static void prepareSsl(Context context, String alias) {
        try {
            if (alias != null) {
                KeyManager km = KeyChainKeyManager.fromAlias(context, alias);
                KeyManager[] kms = new KeyManager[]{km};

                SSLContext sslContext = SSLContext.getInstance("TLS");
                sslContext.init(kms, null, new SecureRandom());

                HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
            } else {
                HttpsURLConnection.setDefaultSSLSocketFactory((SSLSocketFactory) SSLSocketFactory.getDefault());
            }
        } catch (GeneralSecurityException exception) {
            Log.e("SSLUtil", "Error preparant sslContext", exception);
            throw new RuntimeException("Error preparant sslContext", exception);
        }
    }
}
