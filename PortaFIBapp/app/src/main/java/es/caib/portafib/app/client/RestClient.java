package es.caib.portafib.app.client;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.net.ssl.HttpsURLConnection;

import es.caib.portafib.app.Rol;

/**
 *
 */
public class RestClient {

    private volatile String baseUrl;
    private CookieManager cookieManager;

    public RestClient(String baseUrl) {
        this.baseUrl = baseUrl;
        prepareCookies();
    }

    private void prepareCookies() {
        this.cookieManager = new CookieManager(null, CookiePolicy.ACCEPT_ORIGINAL_SERVER);
        CookieHandler.setDefault(cookieManager);
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public List<NotificacioRest> getNotificacions() throws Exception {
        Log.i("RestClient", "getNotificacions");
        Objects.requireNonNull(baseUrl, "No s'ha especificat url de servidor");
        final String url = baseUrl + "/common/rest/usuarientitat/avisos/v1/list";

        // Netejam cookies ja que per defecte no s'esborren les cookies de sessió i ens caduquen
        cookieManager.getCookieStore().removeAll();
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

    private List<NotificacioRest> fromJson(String json) throws Exception {
        try {
            JSONArray jArray = new JSONArray(json);

            List<NotificacioRest> notificacions = new ArrayList<>(jArray.length());

            for (int i = 0; i < jArray.length(); i++) {
                JSONObject jObject = jArray.getJSONObject(i);
                String rolName = jObject.getString("rol");
                Rol rol = Rol.fromString(rolName);
                if (rol == null) {
                    Log.w("RestClient", "Rol desconegut: " + rolName);
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

}
