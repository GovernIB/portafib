package es.caib.portafib.app.client;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.preference.PreferenceManager;
import android.util.Log;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;


public class RestClient {

    public static List<NotificacioRest> getNotificacions(Context context) throws Exception {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        String baseUrl = preferences.getString("server_baseurl", null);
        String alias = preferences.getString("client_alias_cert", null);
        Objects.requireNonNull(baseUrl, "No s'ha especificat url de servidor");
        Objects.requireNonNull(alias, "No s'ha especificat certificat");

        if (!baseUrl.endsWith("/")) {
            baseUrl = baseUrl + "/";
        }

        String url = baseUrl + "common/rest/usuarientitat/avisos/v1/list";

        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ORIGINAL_SERVER));
        SSLContext sslContext = prepareSsl(context, alias);
        HttpsURLConnection connection = (HttpsURLConnection) new URL(url).openConnection();
        connection.setSSLSocketFactory(sslContext.getSocketFactory());

        connection.setRequestMethod("GET");
        connection.setConnectTimeout(15000);

        try {
            connection.connect();
        } catch (Exception e) {
            System.out.println("XYZ ZZZ ERROR COMUNICACIO ");
            throw new Exception("Error en la comunicació amb el servidor: " +
                    "No es troba cap servidor " + url + ")");
        }

        Log.i("restclient", "Principal: " + connection.getLocalPrincipal().getName());

        int code = connection.getResponseCode();

        if (code != 200) {

            if (connection.getResponseCode() == 404) {

                throw new Exception("Per funcionar, aquesta aplicació requereix que el" +
                        " servidor de PortaFIB sigui una versió 1.1.3 o superior." +
                        " També revisi la configuració de l'adreça de PortaFIB així com l'usuari i contrasenya.");

            } else {
                throw new Exception("Error en la comunicació amb el servidor:  "
                        + connection.getResponseMessage() + " (Error: " + connection.getResponseCode() + ")");
            }
        }

        String json;
        try (InputStream in = connection.getInputStream()) {
            json = IOUtils.toString(in, StandardCharsets.UTF_8);
        }

        JSONArray jArray = new JSONArray(json);

        List<NotificacioRest> notificacions = new ArrayList<>(jArray.length());

        for (int i = 0; i < jArray.length(); i++) {

            JSONObject jObject = jArray.getJSONObject(i);
            String rol = jObject.getString("rol");
            JSONArray peticions = jObject.getJSONArray("peticions");
            List<Long> peticionsID = new ArrayList<>(peticions.length());

            for (int p = 0; p < peticions.length(); p++) {
                peticionsID.add(Long.parseLong(peticions.getString(p)));
            }

            notificacions.add(new NotificacioRest(rol, peticionsID));

        }
        return notificacions;
    }

    private static SSLContext prepareSsl(Context context, String alias) {

        Log.i("prepareSSl", "start");

        try {

            KeyManager km = KeyChainKeyManager.fromAlias(context, alias);
            KeyManager[] kms = new KeyManager[]{km};

            SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
            sslContext.init(kms, null, new SecureRandom());

            return sslContext;

        } catch (GeneralSecurityException exception) {
            Log.e("prepareSSl", "Error preparant sslContext", exception);
            throw new RuntimeException("Error preparant sslContext", exception);
        }
    }
}
