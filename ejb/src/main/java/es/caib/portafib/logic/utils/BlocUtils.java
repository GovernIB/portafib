package es.caib.portafib.logic.utils;

import es.caib.portafib.model.entity.Firma;

import java.util.Set;

public class BlocUtils {

    public static int minimFirmes(Set<? extends Firma> firmes) {
        int countObligatories = 0;
        boolean noObligatories = false;
        for (Firma firma: firmes) {
            if (firma.isObligatori()) {
                countObligatories++;
            } else {
                noObligatories = true;
            }
        }
        return (countObligatories + (noObligatories ? 1 : 0));
    }
}
