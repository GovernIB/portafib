/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    public static String CONFIGURACIO_PASSARELA_FILE_INFO_SIGNATURE = "passarelaFileInfoSignature";
    public static String CONFIGURACIO_PASSARELA_COMMON_INFO_SIGNATURE = "passarelaCommonInfoSignature";

    public ConfiguracioUsuariAplicacioPassarelaUtils(PassarelaCommonInfoSignature passarelaCommonInfoSignature, PassarelaFileInfoSignature passarelaFileInfoSignature, int usFirma) {
        super(usFirma, (passarelaCommonInfoSignature!=null)?passarelaCommonInfoSignature.getLanguageUI():null);
        this.passarelaCommonInfoSignature = passarelaCommonInfoSignature;
        this.passarelaFileInfoSignature = passarelaFileInfoSignature;
    }
    
    private PassarelaCommonInfoSignature passarelaCommonInfoSignature;
    private PassarelaFileInfoSignature passarelaFileInfoSignature;

    
    public PassarelaCommonInfoSignature getPassarelaCommonInfoSignature() {
        return passarelaCommonInfoSignature;
    }

    public void setPassarelaCommonInfoSignature(PassarelaCommonInfoSignature passarelaCommonInfoSignature) {
        this.passarelaCommonInfoSignature = passarelaCommonInfoSignature;
    }

    public PassarelaFileInfoSignature getPassarelaFileInfoSignature() {
        return passarelaFileInfoSignature;
    }

    public void setPassarelaFileInfoSignature(PassarelaFileInfoSignature passarelaFileInfoSignature) {
        this.passarelaFileInfoSignature = passarelaFileInfoSignature;
    }
    

    @Override
    protected Map<String, Object> getConfigParameters() {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(CONFIGURACIO_PASSARELA_FILE_INFO_SIGNATURE, getPassarelaFileInfoSignature());
        parameters.put(CONFIGURACIO_PASSARELA_COMMON_INFO_SIGNATURE, getPassarelaCommonInfoSignature());
        return parameters;
    }

    @Override
    protected Long getConfigID() {
        return null;
    }
    
}
