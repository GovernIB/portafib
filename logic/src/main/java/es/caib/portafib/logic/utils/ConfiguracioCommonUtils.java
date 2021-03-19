/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.caib.portafib.logic.utils;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author gdeignacio
 */
public abstract class ConfiguracioCommonUtils {

    public ConfiguracioCommonUtils(int usFirma, String lang) {
        this.usFirma = usFirma;
        this.lang = lang;
    }
    
    public static String CONFIGURACIO_FIRMA_USFIRMA = "usFirma";
    public static String CONFIGURACIO_FIRMA_LANG = "lang";
    
    private int usFirma;
    private String lang;

    public int getUsFirma() {
        return usFirma;
    }

    public void setUsFirma(int usFirma) {
        this.usFirma = usFirma;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
    
    public Map<String, Object> getParameters(){
        Map<String, Object> parameters = new HashMap<String, Object>();
        
        parameters.put(CONFIGURACIO_FIRMA_USFIRMA, getUsFirma());
        parameters.put(CONFIGURACIO_FIRMA_LANG, getLang());
        parameters.putAll(getConfigParameters());
        
        return parameters;
    }
    
    protected abstract Map<String, Object> getConfigParameters();
    
    protected abstract Long getConfigID();
}
