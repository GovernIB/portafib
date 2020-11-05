package es.caib.portafib.app.client;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import es.caib.portafib.app.PreferenceHelper;
import es.caib.portafib.app.R;
import es.caib.portafib.app.Rol;

public class NotificacioUtil {

    public static List<NotificacioRest> fromJson(String json) throws Exception {

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
            Log.e("NotificacioUtil", "Error parsejant JSON", e);
            throw new Exception("Error parsejant JSON", e);
        }
    }

    public static String getLabel(Context context, NotificacioRest notificacioRest) {
        return context.getString(
                R.string.notificacio_pendent_message,
                context.getString(notificacioRest.getRol().resource()),
                notificacioRest.getPeticions().size());
    }

    public static String getUrl(Context context, NotificacioRest notificacioRest) {
        return PreferenceHelper.getServerBaseUrl(context)
                + notificacioRest.getRol().url()
                + "/list";
    }
}
