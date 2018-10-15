package es.caib.portafib.jpa.validator;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import es.caib.portafib.model.fields.PluginCridadaFields;
import es.caib.portafib.model.fields.EntitatFields;
import es.caib.portafib.model.fields.PluginFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class PluginCridadaValidator<T> implements PluginCridadaFields {

  protected final Logger log = Logger.getLogger(getClass());


  public PluginCridadaValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<T> __vr, T __target__, boolean __isNou__
    ,es.caib.portafib.model.dao.IEntitatManager __entitatManager
    ,es.caib.portafib.model.dao.IPluginManager __pluginManager
    ,es.caib.portafib.model.dao.IPluginCridadaManager __pluginCridadaManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,DATA, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DATA)));

    __vr.rejectIfEmptyOrWhitespace(__target__,PLUGINID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PLUGINID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,METODEPLUGIN, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(METODEPLUGIN)));

    __vr.rejectIfEmptyOrWhitespace(__target__,TIPUSTESULTAT, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TIPUSTESULTAT)));

    __vr.rejectIfEmptyOrWhitespace(__target__,TEMPSEXECUCIO, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TEMPSEXECUCIO)));

    // Check size
    if (__vr.getFieldErrorCount(ENTITATID) == 0) {
      java.lang.String __entitatid = (java.lang.String)__vr.getFieldValue(__target__,ENTITATID);
      if (__entitatid!= null && __entitatid.length() > 50) {
        __vr.rejectValue(ENTITATID, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ENTITATID)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(50)));
      }
    }
    
    if (__vr.getFieldErrorCount(METODEPLUGIN) == 0) {
      java.lang.String __metodeplugin = (java.lang.String)__vr.getFieldValue(__target__,METODEPLUGIN);
      if (__metodeplugin!= null && __metodeplugin.length() > 100) {
        __vr.rejectValue(METODEPLUGIN, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(METODEPLUGIN)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(100)));
      }
    }
    
    if (__vr.getFieldErrorCount(PARAMETRESTEXT) == 0) {
      java.lang.String __parametrestext = (java.lang.String)__vr.getFieldValue(__target__,PARAMETRESTEXT);
      if (__parametrestext!= null && __parametrestext.length() > 2147483647) {
        __vr.rejectValue(PARAMETRESTEXT, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PARAMETRESTEXT)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(2147483647)));
      }
    }
    
    if (__vr.getFieldErrorCount(RETORNTEXT) == 0) {
      java.lang.String __retorntext = (java.lang.String)__vr.getFieldValue(__target__,RETORNTEXT);
      if (__retorntext!= null && __retorntext.length() > 2147483647) {
        __vr.rejectValue(RETORNTEXT, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(RETORNTEXT)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(2147483647)));
      }
    }
    
    if (__isNou__) { // Creació
      // ================ CREATION
      // Fitxers 
      // ====== Check Unique MULTIPLES - NOU =======

      // Check Unique - no PK
      // Check Unique - PK no AutoIncrement amb UNA SOLA PK 
    } else {
      // ================ UPDATE

      // ====== Check Unique MULTIPLES - EDIT  =======

      // Check Unique - no PK
    }

    // Fields with References to Other tables 
    if (__vr.getFieldErrorCount(ENTITATID) == 0) {
      java.lang.String __entitatid = (java.lang.String)__vr.getFieldValue(__target__,ENTITATID);
      if (__entitatid != null  && __entitatid.length() != 0) {
        Long __count_ = null;
        try { __count_ = __entitatManager.count(EntitatFields.ENTITATID.equal(__entitatid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ == 0) {        
          __vr.rejectValue(ENTITATID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("entitat.entitat"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("entitat.entitatID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__entitatid)));
        }
      }
    }

    if (__vr.getFieldErrorCount(PLUGINID) == 0) {
      java.lang.Long __pluginid = (java.lang.Long)__vr.getFieldValue(__target__,PLUGINID);
      Long __count_ = null;
      try { __count_ = __pluginManager.count(PluginFields.PLUGINID.equal(__pluginid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(PLUGINID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("plugin.plugin"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("plugin.pluginID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__pluginid)));
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}