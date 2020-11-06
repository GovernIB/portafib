package es.caib.portafib.app.client;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.net.CookieStore;
import java.net.HttpCookie;
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

import es.caib.portafib.app.PortaFIBApplication;
import es.caib.portafib.app.PreferenceHelper;
import es.caib.portafib.app.Rol;

/**
 * TODO: es podria fer prepareSsl només una vegada?
 * TODO: millors traces en cas d'error
 */
public class RestClient {

    public static List<NotificacioRest> getNotificacions(Context context) throws Exception {

        Log.i("RestClient", "getNotificacions");

        String baseUrl = PreferenceHelper.getServerBaseUrl(context);
        String alias = PreferenceHelper.getClientAliasCert(context);
        Objects.requireNonNull(baseUrl, "No s'ha especificat url de servidor");
        Objects.requireNonNull(alias, "No s'ha especificat certificat");

        String url = baseUrl + "/common/rest/usuarientitat/avisos/v1/list";

        CookieStore cookieStore = PortaFIBApplication.getInstance().getCookieManager().getCookieStore();
        cookieStore.removeAll();

        prepareSsl(context, alias);
        HttpsURLConnection connection = (HttpsURLConnection) new URL(url).openConnection();

        connection.setRequestMethod("GET");
        connection.setConnectTimeout(20_000);
        connection.setReadTimeout(20_000);

        try {
            connection.connect();
        } catch (Exception e) {
            Log.e("RestClient", "Error en la comunicació", e);
            throw new Exception("Error en la comunicació amb el servidor: " +
                    "No es troba cap servidor " + url + ")");
        }

        int code = connection.getResponseCode();
        if (code != 200) {
            if (connection.getResponseCode() == 404) {
                Log.e("RestClient", "Error en la comunicació: (Codi: 404)");
                throw new Exception("Per funcionar, aquesta aplicació requereix que el" +
                        " servidor de PortaFIB sigui una versió 1.1.3 o superior." +
                        " També revisi la configuració de l'adreça de PortaFIB així com el certificat.");

            } else {
                Log.e("RestClient", "Error en la comunicació: (Codi: " + connection.getResponseCode() + ")");
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

        return fromJson(jsonBuilder.toString());
    }

    private static List<NotificacioRest> fromJson(String json) throws Exception {

        try {
            JSONArray jArray = new JSONArray(json);

            List<NotificacioRest> notificacions = new ArrayList<>(jArray.length());

            for (int i = 0; i < jArray.length(); i++) {

                JSONObject jObject = jArray.getJSONObject(i);
                String rolName = jObject.getString("rol");
                Rol rol = Rol.fromString(rolName);
                if (rol == null) {
                    Log.w("RestClient", "Role desconegut: " + rolName);
                    continue;
                }

                JSONArray peticions = jObject.getJSONArray("peticions");
                List<Long> peticionsID = new ArrayList<>(peticions.length());

                for (int p = 0; p < peticions.length(); p++) {
                    peticionsID.add(peticions.getLong(p));
                }

                notificacions.add(new NotificacioRest(rol, peticionsID));

            }
            return notificacions;
        } catch (JSONException e) {
            Log.e("RestClient", "Error parsejant JSON", e);
            throw new Exception("Error parsejant JSON", e);
        }
    }

    private static void prepareSsl(Context context, String alias) {
        try {
            KeyManager km = KeyChainKeyManager.fromAlias(context, alias);
            KeyManager[] kms = new KeyManager[]{km};

            SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
            sslContext.init(kms, null, new SecureRandom());

            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());

        } catch (GeneralSecurityException exception) {
            Log.e("RestClient", "Error preparant sslContext", exception);
            throw new RuntimeException("Error preparant sslContext", exception);
        }
    }
}
