package es.caib.portafib.logic.utils;

import java.util.HashMap;
import java.util.Map;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignDocumentRequest;

/**
 *
 * @author gdeignacio
 */
public class ConfiguracioApiFirmaSimpleUtils extends ConfiguracioCommonUtils {

    private static final String CONFIGURACIO_FIRMA_SIMPLE_SIGN_DOCUMENT_REQUEST = "firmaSimpleSignDocumentRequest";

    private final FirmaSimpleSignDocumentRequest firmaSimpleSigndocumentRequest;

    public ConfiguracioApiFirmaSimpleUtils(int usFirma, FirmaSimpleSignDocumentRequest firmaSimpleSigndocumentRequest) {
        super(usFirma);
        this.firmaSimpleSigndocumentRequest = firmaSimpleSigndocumentRequest;
    }

    @Override
    protected String getLang() {
        return firmaSimpleSigndocumentRequest.getCommonInfo().getLanguageUI();
    }

    @Override
    protected long getTamanyFitxer() {
        return firmaSimpleSigndocumentRequest.getFileInfoSignature().getFileToSign().getData().length;
    }

    @Override
    protected String getMimeFitxer() {
        return firmaSimpleSigndocumentRequest.getFileInfoSignature().getFileToSign().getMime();
    }

    @Override
    protected Map<String, Object> getConfigParameters() {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(CONFIGURACIO_FIRMA_SIMPLE_SIGN_DOCUMENT_REQUEST, firmaSimpleSigndocumentRequest);
        return parameters;
    }
}
