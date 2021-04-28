package es.caib.portafib.logic.utils;

import es.caib.portafib.logic.passarela.api.PassarelaCommonInfoSignature;
import es.caib.portafib.logic.passarela.api.PassarelaFileInfoSignature;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author gdeignacio
 */
public class ConfiguracioUsuariAplicacioPassarelaUtils extends ConfiguracioCommonUtils {

    private static final String CONFIGURACIO_PASSARELA_FILE_INFO_SIGNATURE = "passarelaFileInfoSignature";
    private static final String CONFIGURACIO_PASSARELA_COMMON_INFO_SIGNATURE = "passarelaCommonInfoSignature";

    private final PassarelaCommonInfoSignature passarelaCommonInfoSignature;
    private final PassarelaFileInfoSignature passarelaFileInfoSignature;

    public ConfiguracioUsuariAplicacioPassarelaUtils(int usFirma, PassarelaCommonInfoSignature passarelaCommonInfoSignature, PassarelaFileInfoSignature passarelaFileInfoSignature) {
        super(usFirma);
        this.passarelaCommonInfoSignature = passarelaCommonInfoSignature;
        this.passarelaFileInfoSignature = passarelaFileInfoSignature;
    }

    @Override
    protected String getLang() {
        return passarelaCommonInfoSignature.getLanguageUI();
    }

    @Override
    protected long getTamanyFitxer() {
        return passarelaFileInfoSignature.getFileToSign().getTamany();
    }

    @Override
    protected String getMimeFitxer() {
        return passarelaFileInfoSignature.getFileToSign().getMime();
    }

    @Override
    protected Map<String, Object> getConfigParameters() {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(CONFIGURACIO_PASSARELA_FILE_INFO_SIGNATURE, passarelaFileInfoSignature);
        parameters.put(CONFIGURACIO_PASSARELA_COMMON_INFO_SIGNATURE, passarelaCommonInfoSignature);
        return parameters;
    }
    
}
