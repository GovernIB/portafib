package es.caib.portafib.logic.utils;

import java.util.Map;


/**
 * 
 * @author anadal
 * 16 may 2025 10:36:04
 */
public class ConfiguracioApiFirmaGenericUtils extends ConfiguracioCommonUtils {

    final String lang;
    final boolean isUpgrade;
    final long tamanyFitxer;
    final String mimeFitxer;
    final Map<String, Object> configParameters;

    public ConfiguracioApiFirmaGenericUtils(int usFirma, String lang, boolean isUpgrade, long tamanyFitxer,
            String mimeFitxer, Map<String, Object> configParameters) {
        super(usFirma);
        this.lang = lang;
        this.isUpgrade = isUpgrade;
        this.tamanyFitxer = tamanyFitxer;
        this.mimeFitxer = mimeFitxer;
        this.configParameters = configParameters;
    }

    @Override
    protected String getLang() {
        return lang;
    }

    @Override
    protected long getTamanyFitxer() {
        return tamanyFitxer;
    }

    @Override
    protected String getMimeFitxer() {
        return mimeFitxer;
    }

    @Override
    protected boolean isUpgrade() {
        return isUpgrade;
    }

    @Override
    protected Map<String, Object> getConfigParameters() {
        return configParameters;
    }

}
