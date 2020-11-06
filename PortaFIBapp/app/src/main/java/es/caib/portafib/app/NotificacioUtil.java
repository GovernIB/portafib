package es.caib.portafib.app;

import android.content.Context;

import es.caib.portafib.app.client.NotificacioRest;

public class NotificacioUtil {

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
