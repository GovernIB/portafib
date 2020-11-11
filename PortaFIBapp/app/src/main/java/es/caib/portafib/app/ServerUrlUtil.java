package es.caib.portafib.app;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ServerUrlUtil {

    public static String getUrl(Context context, String path) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String serverBaseUrl = preferences.getString("server_baseurl", "");
        String serverUrl = serverBaseUrl + path;

        boolean authServerSw = preferences.getBoolean("auth_server_sw", false);
        if (!authServerSw) {
            return serverUrl;
        }

        String authServerUrl = preferences.getString("auth_server_url", "");
        try {
            return authServerUrl + URLEncoder.encode(serverUrl, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("No pot passar mai");
        }
    }

    public static String getRestEndpoint(Context context) {
        return getUrl(context, "/common/rest/usuarientitat/avisos/v1/list");
    }
}
