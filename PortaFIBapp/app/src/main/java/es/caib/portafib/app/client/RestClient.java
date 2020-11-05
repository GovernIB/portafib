package es.caib.portafib.app.client;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Objects;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;

import es.caib.portafib.app.PreferenceHelper;

/**
 * TODO: es podria fer prepareSsl només una vegada?
 * TODO: millors traces en cas d'error
 */
public class RestClient {

    public static String getNotificacions(Context context) throws Exception {
        Log.i("RestClient", "getNotificacions");

        String baseUrl = PreferenceHelper.getServerBaseUrl(context);
        String alias = PreferenceHelper.getClientAliasCert(context);
        Objects.requireNonNull(baseUrl, "No s'ha especificat url de servidor");
        Objects.requireNonNull(alias, "No s'ha especificat certificat");

        String url = baseUrl + "/common/rest/usuarientitat/avisos/v1/list";

        prepareSsl(context, alias);
        HttpsURLConnection connection = (HttpsURLConnection) new URL(url).openConnection();

        connection.setRequestMethod("GET");
        connection.setConnectTimeout(20_000);
        connection.setReadTimeout(20_000);

        try {
            connection.connect();
        } catch (Exception e) {
            System.out.println("XYZ ZZZ ERROR COMUNICACIO ");
            throw new Exception("Error en la comunicació amb el servidor: " +
                    "No es troba cap servidor " + url + ")");
        }

        int code = connection.getResponseCode();
        if (code != 200) {
            if (connection.getResponseCode() == 404) {
                throw new Exception("Per funcionar, aquesta aplicació requereix que el" +
                        " servidor de PortaFIB sigui una versió 1.1.3 o superior." +
                        " També revisi la configuració de l'adreça de PortaFIB així com el certificat.");

            } else {
                throw new Exception("Error en la comunicació amb el servidor:  "
                        + connection.getResponseMessage() + " (Error: " + connection.getResponseCode() + ")");
            }
        }

        StringBuilder jsonBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                jsonBuilder.append(inputLine);
            }
        }

        return jsonBuilder.toString();
    }

    private static void prepareSsl(Context context, String alias) {
        try {
            KeyManager km = KeyChainKeyManager.fromAlias(context, alias);
            KeyManager[] kms = new KeyManager[]{km};

            SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
            sslContext.init(kms, null, new SecureRandom());

            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());

        } catch (GeneralSecurityException exception) {
            Log.e("prepareSSl", "Error preparant sslContext", exception);
            throw new RuntimeException("Error preparant sslContext", exception);
        }
    }
}
