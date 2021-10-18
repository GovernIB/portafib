package es.caib.portafib.logic.utils;

import es.caib.portafib.model.entity.RevisorDeFirma;

import java.util.Set;

public class FirmaUtils {

    public static int minimRevisors(Set<? extends RevisorDeFirma> revisors) {
        int countObligatories = 0;
        boolean noObligatories = false;
        for (RevisorDeFirma firma: revisors) {
            if (firma.isObligatori()) {
                countObligatories++;
            } else {
                noObligatories = true;
            }
        }
        return (countObligatories + (noObligatories ? 1 : 0));
    }
}
