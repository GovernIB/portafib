/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.caib.portafib.logic.utils;

import java.util.HashMap;
import java.util.Map;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignDocumentRequest;

/**
 *
 * @author gdeignacio
 */
public class ConfiguracioApiFirmaSimpleUtils extends ConfiguracioCommonUtils {

    public static String CONFIGURACIO_FIRMA_SIMPLE_SIGN_DOCUMENT_REQUEST = "firmaSimpleSignDocumentRequest";
    public static String CONFIGURACIO_FIRMA_SIMPLE_ISUPGRADE = "isUpgrade";
    
    public ConfiguracioApiFirmaSimpleUtils(boolean isUpgrade, FirmaSimpleSignDocumentRequest firmaSimpleSigndocumentRequest, int usFirma) {
        super(usFirma, ((firmaSimpleSigndocumentRequest!=null)
                &&(firmaSimpleSigndocumentRequest.getCommonInfo()!=null))?firmaSimpleSigndocumentRequest.getCommonInfo().getLanguageUI():null);
        this.isUpgrade = isUpgrade;
        this.firmaSimpleSigndocumentRequest = firmaSimpleSigndocumentRequest;
    }

    private boolean isUpgrade;
    private FirmaSimpleSignDocumentRequest firmaSimpleSigndocumentRequest;

    public boolean isIsUpgrade() {
        return isUpgrade;
    }

    public void setIsUpgrade(boolean isUpgrade) {
        this.isUpgrade = isUpgrade;
    }

    public FirmaSimpleSignDocumentRequest getFirmaSimpleSigndocumentRequest() {
        return firmaSimpleSigndocumentRequest;
    }

    public void setFirmaSimpleSigndocumentRequest(FirmaSimpleSignDocumentRequest firmaSimpleSigndocumentRequest) {
        this.firmaSimpleSigndocumentRequest = firmaSimpleSigndocumentRequest;
    }

    @Override
    protected Map<String, Object> getConfigParameters() {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(CONFIGURACIO_FIRMA_SIMPLE_SIGN_DOCUMENT_REQUEST, getFirmaSimpleSigndocumentRequest());
        parameters.put(CONFIGURACIO_FIRMA_SIMPLE_ISUPGRADE, isIsUpgrade());
        return parameters;
    }

    @Override
    protected Long getConfigID() {
        return null;
    }
    
}
