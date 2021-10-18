package es.caib.portafib.logic.utils;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author gdeignacio
 */
public abstract class ConfiguracioCommonUtils {

    private static final String CONFIGURACIO_FIRMA_USFIRMA = "usFirma";
    private static final String CONFIGURACIO_FIRMA_LANG = "lang";
    private final static String CONFIGURACIO_FIRMA_ISUPGRADE = "isUpgrade";
    private final static String CONFIGURACIO_FIRMA_TAMANYFITXER = "tamanyFitxer";
    private final static String CONFIGURACIO_FIRMA_MIMEFITXER = "mimeFitxer";

    private final int usFirma;

    public ConfiguracioCommonUtils(int usFirma) {
        this.usFirma = usFirma;
    }

    public final Map<String, Object> getParameters(){
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(CONFIGURACIO_FIRMA_USFIRMA, getUsFirma());
        parameters.put(CONFIGURACIO_FIRMA_LANG, getLang());
        parameters.put(CONFIGURACIO_FIRMA_ISUPGRADE, isUpgrade());
        parameters.put(CONFIGURACIO_FIRMA_TAMANYFITXER, getTamanyFitxer());
        parameters.put(CONFIGURACIO_FIRMA_MIMEFITXER, getMimeFitxer());
        parameters.putAll(getConfigParameters());
        return parameters;
    }

    public final int getUsFirma() {
        return usFirma;
    }

    protected abstract String getLang();

    protected abstract long getTamanyFitxer();

    protected abstract String getMimeFitxer();

    protected boolean isUpgrade() {
        return false;
    }

    protected abstract Map<String, Object> getConfigParameters();
}
