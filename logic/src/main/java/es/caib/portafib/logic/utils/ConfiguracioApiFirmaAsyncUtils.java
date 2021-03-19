/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.caib.portafib.logic.utils;

import java.util.HashMap;
import java.util.Map;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestWithSignBlockList;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignDocumentRequest;

/**
 *
 * @author gdeignacio
 */
public class ConfiguracioApiFirmaAsyncUtils extends ConfiguracioCommonUtils {

    public static String CONFIGURACIO_FIRMA_SIGNATURE_REQUEST = "signatureRequest";

    public ConfiguracioApiFirmaAsyncUtils(FirmaAsyncSimpleSignatureRequestWithSignBlockList signatureRequest, int usFirma) {
        super(usFirma, (signatureRequest!=null)?signatureRequest.getLanguageUI():null);
        this.signatureRequest = signatureRequest;
    }
    
    private FirmaAsyncSimpleSignatureRequestWithSignBlockList signatureRequest;

    public FirmaAsyncSimpleSignatureRequestWithSignBlockList getSignatureRequest() {
        return signatureRequest;
    }

    public void setSignatureRequest(FirmaAsyncSimpleSignatureRequestWithSignBlockList signatureRequest) {
        this.signatureRequest = signatureRequest;
    }


    @Override
    protected Map<String, Object> getConfigParameters() {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(CONFIGURACIO_FIRMA_SIGNATURE_REQUEST, getSignatureRequest());
       
        return parameters;
    }

    @Override
    protected Long getConfigID() {
        return null;
    }
    
}
