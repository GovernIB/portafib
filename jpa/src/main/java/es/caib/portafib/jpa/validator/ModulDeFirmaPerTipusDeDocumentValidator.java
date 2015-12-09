package es.caib.portafib.jpa.validator;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import es.caib.portafib.model.fields.ModulDeFirmaPerTipusDeDocumentFields;
import es.caib.portafib.model.fields.PluginFields;
import es.caib.portafib.model.fields.TipusDocumentFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class ModulDeFirmaPerTipusDeDocumentValidator<T> implements ModulDeFirmaPerTipusDeDocumentFields {

  protected final Logger log = Logger.getLogger(getClass());


  public ModulDeFirmaPerTipusDeDocumentValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<T> __vr, T __target__, boolean __isNou__
    ,es.caib.portafib.model.dao.IModulDeFirmaPerTipusDeDocumentManager __modulDeFirmaPerTipusDeDocumentManager
    ,es.caib.portafib.model.dao.IPluginManager __pluginManager
    ,es.caib.portafib.model.dao.ITipusDocumentManager __tipusDocumentManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,NOM, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOM)));

    __vr.rejectIfEmptyOrWhitespace(__target__,TIPUSDOCUMENTID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TIPUSDOCUMENTID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,PLUGINID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PLUGINID)));

    // Check size
    if (__vr.getFieldErrorCount(NOM) == 0) {
      java.lang.String __nom = (java.lang.String)__vr.getFieldValue(__target__,NOM);
      if (__nom!= null && __nom.length() > 100) {
        __vr.rejectValue(NOM, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOM)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(100)));
      }
    }
    
    if (__isNou__) { // Creació
      // ================ CREATION
      // Fitxers 
      // ====== Check Unique MULTIPLES - NOU =======

      // Check Unique MULTIPLE for (tipusdocumentid, pluginid)
      if (__vr.getFieldErrorCount(TIPUSDOCUMENTID) == 0 && __vr.getFieldErrorCount(PLUGINID) == 0) {
        java.lang.Long __tipusdocumentid = (java.lang.Long)__vr.getFieldValue(__target__,TIPUSDOCUMENTID);
        java.lang.Long __pluginid = (java.lang.Long)__vr.getFieldValue(__target__,PLUGINID);
        Long __count_ = null;
        try { __count_ = __modulDeFirmaPerTipusDeDocumentManager.count(org.fundaciobit.genapp.common.query.Where.AND(TIPUSDOCUMENTID.equal(__tipusdocumentid), PLUGINID.equal(__pluginid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(TIPUSDOCUMENTID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__tipusdocumentid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TIPUSDOCUMENTID)));
            __vr.rejectValue(PLUGINID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__pluginid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PLUGINID)));
        }
      }

      // Check Unique - no PK
      // Check Unique - PK no AutoIncrement amb UNA SOLA PK 
    } else {
      // ================ UPDATE

      // ====== Check Unique MULTIPLES - EDIT  =======

      // Check Unique MULTIPLE for (tipusdocumentid, pluginid)
      if (__vr.getFieldErrorCount(TIPUSDOCUMENTID) == 0 && __vr.getFieldErrorCount(PLUGINID) == 0 && __vr.getFieldErrorCount(ID) == 0) {
        java.lang.Long __tipusdocumentid = (java.lang.Long)__vr.getFieldValue(__target__,TIPUSDOCUMENTID);
        java.lang.Long __pluginid = (java.lang.Long)__vr.getFieldValue(__target__,PLUGINID);
        java.lang.Long __id = (java.lang.Long)__vr.getFieldValue(__target__,ID);
        Long __count_ = null;
        try { __count_ = __modulDeFirmaPerTipusDeDocumentManager.count(org.fundaciobit.genapp.common.query.Where.AND(TIPUSDOCUMENTID.equal(__tipusdocumentid), PLUGINID.equal(__pluginid), ID.notEqual(__id))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(TIPUSDOCUMENTID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__tipusdocumentid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TIPUSDOCUMENTID)));
            __vr.rejectValue(PLUGINID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__pluginid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PLUGINID)));
        }
      }

      // Check Unique - no PK
    }

    // Fields with References to Other tables 
    if (__vr.getFieldErrorCount(TIPUSDOCUMENTID) == 0) {
      java.lang.Long __tipusdocumentid = (java.lang.Long)__vr.getFieldValue(__target__,TIPUSDOCUMENTID);
      Long __count_ = null;
      try { __count_ = __tipusDocumentManager.count(TipusDocumentFields.TIPUSDOCUMENTID.equal(__tipusdocumentid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(TIPUSDOCUMENTID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("tipusDocument.tipusDocument"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("tipusDocument.tipusDocumentID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__tipusdocumentid)));
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