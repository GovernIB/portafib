/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.caib.portafib.logic.utils;

import java.util.HashMap;
import java.util.Map;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleUpgradeRequest;

/**
 *
 * @author gdeignacio
 */
public class ConfiguracioUsuariAplicacioUpgradeUtils extends ConfiguracioCommonUtils {

    public static String CONFIGURACIO_FIRMA_SIMPLE_UPGRADE_REQUEST = "firmaSimpleUpgradeRequest";
    public static String CONFIGURACIO_FIRMA_SIMPLE_ISUPGRADE = "isUpgrade";

    public ConfiguracioUsuariAplicacioUpgradeUtils(boolean isUpgrade, FirmaSimpleUpgradeRequest firmaSimpleUpgradeRequest, int usFirma) {
        super(usFirma, (firmaSimpleUpgradeRequest!=null)?firmaSimpleUpgradeRequest.getLanguageUI():null);
        this.isUpgrade = isUpgrade;
        this.firmaSimpleUpgradeRequest = firmaSimpleUpgradeRequest;
    }
    
    
    
    private boolean isUpgrade;
    private FirmaSimpleUpgradeRequest firmaSimpleUpgradeRequest;

    public boolean isIsUpgrade() {
        return isUpgrade;
    }

    public void setIsUpgrade(boolean isUpgrade) {
        this.isUpgrade = isUpgrade;
    }

    public FirmaSimpleUpgradeRequest getFirmaSimpleUpgradeRequest() {
        return firmaSimpleUpgradeRequest;
    }

    public void setFirmaSimpleUpgradeRequest(FirmaSimpleUpgradeRequest firmaSimpleUpgradeRequest) {
        this.firmaSimpleUpgradeRequest = firmaSimpleUpgradeRequest;
    }

 

    @Override
    protected Map<String, Object> getConfigParameters() {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(CONFIGURACIO_FIRMA_SIMPLE_UPGRADE_REQUEST, getFirmaSimpleUpgradeRequest());
        parameters.put(CONFIGURACIO_FIRMA_SIMPLE_ISUPGRADE, isIsUpgrade());
        return parameters;
    }

    @Override
    protected Long getConfigID() {
        return null;
    }
    
}
