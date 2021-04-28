package es.caib.portafib.logic.utils;

import java.util.HashMap;
import java.util.Map;

import es.caib.portafib.utils.ConstantsV2;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestWithSignBlockList;

/**
 *
 * @author gdeignacio
 */
public class ConfiguracioApiFirmaAsyncUtils extends ConfiguracioCommonUtils {

    private static final String CONFIGURACIO_FIRMA_SIGNATURE_REQUEST = "signatureRequest";
    private static final int US_FIRMA = ConstantsV2.US_FIRMA_CONF_APP_FIRMAASYNCSIMPLEREST2;

    private final FirmaAsyncSimpleSignatureRequestWithSignBlockList signatureRequest;

    public ConfiguracioApiFirmaAsyncUtils(FirmaAsyncSimpleSignatureRequestWithSignBlockList signatureRequest) {
        super(US_FIRMA);
        this.signatureRequest = signatureRequest;
    }

    @Override
    protected String getLang() {
        return signatureRequest.getLanguageUI();
    }

    @Override
    protected long getTamanyFitxer() {
        return signatureRequest.getFileToSign().getData().length;
    }

    @Override
    protected String getMimeFitxer() {
        return signatureRequest.getFileToSign().getMime();
    }

    @Override
    protected Map<String, Object> getConfigParameters() {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(CONFIGURACIO_FIRMA_SIGNATURE_REQUEST, signatureRequest);
        return parameters;
    }
    
}
