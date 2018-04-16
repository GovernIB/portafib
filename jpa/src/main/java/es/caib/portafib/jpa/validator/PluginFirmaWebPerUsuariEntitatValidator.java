package es.caib.portafib.jpa.validator;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import es.caib.portafib.model.fields.PluginFirmaWebPerUsuariEntitatFields;
import es.caib.portafib.model.fields.PluginFields;
import es.caib.portafib.model.fields.UsuariEntitatFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class PluginFirmaWebPerUsuariEntitatValidator<T> implements PluginFirmaWebPerUsuariEntitatFields {

  protected final Logger log = Logger.getLogger(getClass());


  public PluginFirmaWebPerUsuariEntitatValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<T> __vr, T __target__, boolean __isNou__
    ,es.caib.portafib.model.dao.IPluginManager __pluginManager
    ,es.caib.portafib.model.dao.IPluginFirmaWebPerUsuariEntitatManager __pluginFirmaWebPerUsuariEntitatManager
    ,es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,USUARIENTITATID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIENTITATID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,PLUGINFIRMAWEBID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PLUGINFIRMAWEBID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,ACCIO, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ACCIO)));

    // Check size
    if (__vr.getFieldErrorCount(USUARIENTITATID) == 0) {
      java.lang.String __usuarientitatid = (java.lang.String)__vr.getFieldValue(__target__,USUARIENTITATID);
      if (__usuarientitatid!= null && __usuarientitatid.length() > 101) {
        __vr.rejectValue(USUARIENTITATID, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIENTITATID)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(101)));
      }
    }
    
    if (__isNou__) { // Creació
      // ================ CREATION
      // Fitxers 
      // ====== Check Unique MULTIPLES - NOU =======

      // Check Unique MULTIPLE for (usuarientitatid, pluginfirmawebid)
      if (__vr.getFieldErrorCount(USUARIENTITATID) == 0 && __vr.getFieldErrorCount(PLUGINFIRMAWEBID) == 0) {
        java.lang.String __usuarientitatid = (java.lang.String)__vr.getFieldValue(__target__,USUARIENTITATID);
        java.lang.Long __pluginfirmawebid = (java.lang.Long)__vr.getFieldValue(__target__,PLUGINFIRMAWEBID);
        Long __count_ = null;
        try { __count_ = __pluginFirmaWebPerUsuariEntitatManager.count(org.fundaciobit.genapp.common.query.Where.AND(USUARIENTITATID.equal(__usuarientitatid), PLUGINFIRMAWEBID.equal(__pluginfirmawebid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(USUARIENTITATID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__usuarientitatid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIENTITATID)));
            __vr.rejectValue(PLUGINFIRMAWEBID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__pluginfirmawebid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PLUGINFIRMAWEBID)));
        }
      }

      // Check Unique - no PK
      // Check Unique - PK no AutoIncrement amb UNA SOLA PK 
    } else {
      // ================ UPDATE

      // ====== Check Unique MULTIPLES - EDIT  =======

      // Check Unique MULTIPLE for (usuarientitatid, pluginfirmawebid)
      if (__vr.getFieldErrorCount(USUARIENTITATID) == 0 && __vr.getFieldErrorCount(PLUGINFIRMAWEBID) == 0 && __vr.getFieldErrorCount(PLUGINFIRMAWEBPERUSRENTID) == 0) {
        java.lang.String __usuarientitatid = (java.lang.String)__vr.getFieldValue(__target__,USUARIENTITATID);
        java.lang.Long __pluginfirmawebid = (java.lang.Long)__vr.getFieldValue(__target__,PLUGINFIRMAWEBID);
        java.lang.Long __pluginfirmawebperusrentid = (java.lang.Long)__vr.getFieldValue(__target__,PLUGINFIRMAWEBPERUSRENTID);
        Long __count_ = null;
        try { __count_ = __pluginFirmaWebPerUsuariEntitatManager.count(org.fundaciobit.genapp.common.query.Where.AND(USUARIENTITATID.equal(__usuarientitatid), PLUGINFIRMAWEBID.equal(__pluginfirmawebid), PLUGINFIRMAWEBPERUSRENTID.notEqual(__pluginfirmawebperusrentid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(USUARIENTITATID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__usuarientitatid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIENTITATID)));
            __vr.rejectValue(PLUGINFIRMAWEBID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__pluginfirmawebid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PLUGINFIRMAWEBID)));
        }
      }

      // Check Unique - no PK
    }

    // Fields with References to Other tables 
    if (__vr.getFieldErrorCount(USUARIENTITATID) == 0) {
      java.lang.String __usuarientitatid = (java.lang.String)__vr.getFieldValue(__target__,USUARIENTITATID);
      Long __count_ = null;
      try { __count_ = __usuariEntitatManager.count(UsuariEntitatFields.USUARIENTITATID.equal(__usuarientitatid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(USUARIENTITATID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuariEntitat.usuariEntitat"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuariEntitat.usuariEntitatID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__usuarientitatid)));
      }
    }

    if (__vr.getFieldErrorCount(PLUGINFIRMAWEBID) == 0) {
      java.lang.Long __pluginfirmawebid = (java.lang.Long)__vr.getFieldValue(__target__,PLUGINFIRMAWEBID);
      Long __count_ = null;
      try { __count_ = __pluginManager.count(PluginFields.PLUGINID.equal(__pluginfirmawebid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(PLUGINFIRMAWEBID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("plugin.plugin"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("plugin.pluginID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__pluginfirmawebid)));
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}