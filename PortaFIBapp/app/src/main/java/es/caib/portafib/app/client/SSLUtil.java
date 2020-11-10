package es.caib.portafib.app.client;

import android.content.Context;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

public class SSLUtil {

    public static void prepareSsl(Context context, String alias) {
        try {
            if (alias != null) {
                KeyManager km = KeyChainKeyManager.fromAlias(context, alias);
                KeyManager[] kms = new KeyManager[]{km};

                SSLContext sslContext = SSLContext.getInstance("TLS");
                sslContext.init(kms, new TrustManager[] {prepareTrustManager(context)}, new SecureRandom());

                HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
            } else {
                HttpsURLConnection.setDefaultSSLSocketFactory((SSLSocketFactory) SSLSocketFactory.getDefault());
            }
        } catch (GeneralSecurityException| IOException exception) {
            Log.e("SSLUtil", "Error preparant sslContext", exception);
            throw new RuntimeException("Error preparant sslContext", exception);
        }
    }

    public static TrustManager prepareTrustManager(Context context) throws GeneralSecurityException, IOException {

        // Cream un keystore buid
        String keyStoreType = KeyStore.getDefaultType();
        KeyStore keyStore = KeyStore.getInstance(keyStoreType);
        keyStore.load(null, null);

        CertificateFactory cf = CertificateFactory.getInstance("X.509");

        // Hi afegim tots els certificats de la carpeta certs
        String[] certFiles = context.getAssets().list("certs");
        for (int i = 0; i < certFiles.length; i++) {
            Log.i("SSLUtil", "Instal·lant " + certFiles[i]);
            try (InputStream is = context.getAssets().open("certs/" + certFiles[i]);
                 InputStream caInput = new BufferedInputStream(is)) {
                Certificate ca = cf.generateCertificate(caInput);
                String alias = "cert" + i;
                keyStore.setCertificateEntry(alias, ca);
                Log.i("SSLUtil", alias + "=" + ((X509Certificate) ca).getSubjectDN());
            }
        }

        // Retornam un trustmanager compost que intentarà validar els certificats amb els certificats per defecte
        // del sistema, i sinó amb el keystore que li passam.
        return new CompositeTrustManager(keyStore);
    }
}
