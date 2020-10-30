package es.caib.portafib.app;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

public class PreferenceHelper {

    public static boolean isNotificacioSw(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean("notificacions_sw", false);
    }

    public static String getServerBaseUrl(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("server_baseurl", null);
    }

    public static String getClientAliasCert(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("client_alias_cert", null);
    }

    public static String getLastJsonResponse(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("last_json_response", null);
    }

    public static void setLastJsonResponse(Context context, String json) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putString("last_json_response", json).apply();
    }
}
