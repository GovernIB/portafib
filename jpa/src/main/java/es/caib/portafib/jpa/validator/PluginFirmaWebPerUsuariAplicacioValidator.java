package es.caib.portafib.jpa.validator;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import es.caib.portafib.model.fields.PluginFirmaWebPerUsuariAplicacioFields;
import es.caib.portafib.model.fields.PluginFields;
import es.caib.portafib.model.fields.UsuariAplicacioFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class PluginFirmaWebPerUsuariAplicacioValidator<T> implements PluginFirmaWebPerUsuariAplicacioFields {

  protected final Logger log = Logger.getLogger(getClass());


  public PluginFirmaWebPerUsuariAplicacioValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<T> __vr, T __target__, boolean __isNou__
    ,es.caib.portafib.model.dao.IPluginManager __pluginManager
    ,es.caib.portafib.model.dao.IPluginFirmaWebPerUsuariAplicacioManager __pluginFirmaWebPerUsuariAplicacioManager
    ,es.caib.portafib.model.dao.IUsuariAplicacioManager __usuariAplicacioManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,USUARIAPLICACIOID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIAPLICACIOID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,PLUGINFIRMAWEBID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PLUGINFIRMAWEBID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,ACCIO, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ACCIO)));

    // Check size
    if (__vr.getFieldErrorCount(USUARIAPLICACIOID) == 0) {
      java.lang.String __usuariaplicacioid = (java.lang.String)__vr.getFieldValue(__target__,USUARIAPLICACIOID);
      if (__usuariaplicacioid!= null && __usuariaplicacioid.length() > 101) {
        __vr.rejectValue(USUARIAPLICACIOID, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIAPLICACIOID)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(101)));
      }
    }
    
    if (__isNou__) { // Creació
      // ================ CREATION
      // Fitxers 
      // ====== Check Unique MULTIPLES - NOU =======

      // Check Unique MULTIPLE for (usuariaplicacioid, pluginfirmawebid)
      if (__vr.getFieldErrorCount(USUARIAPLICACIOID) == 0 && __vr.getFieldErrorCount(PLUGINFIRMAWEBID) == 0) {
        java.lang.String __usuariaplicacioid = (java.lang.String)__vr.getFieldValue(__target__,USUARIAPLICACIOID);
        java.lang.Long __pluginfirmawebid = (java.lang.Long)__vr.getFieldValue(__target__,PLUGINFIRMAWEBID);
        Long __count_ = null;
        try { __count_ = __pluginFirmaWebPerUsuariAplicacioManager.count(org.fundaciobit.genapp.common.query.Where.AND(USUARIAPLICACIOID.equal(__usuariaplicacioid), PLUGINFIRMAWEBID.equal(__pluginfirmawebid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(USUARIAPLICACIOID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__usuariaplicacioid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIAPLICACIOID)));
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

      // Check Unique MULTIPLE for (usuariaplicacioid, pluginfirmawebid)
      if (__vr.getFieldErrorCount(USUARIAPLICACIOID) == 0 && __vr.getFieldErrorCount(PLUGINFIRMAWEBID) == 0 && __vr.getFieldErrorCount(PLUGINFIRMAWEBPERUSRAPPID) == 0) {
        java.lang.String __usuariaplicacioid = (java.lang.String)__vr.getFieldValue(__target__,USUARIAPLICACIOID);
        java.lang.Long __pluginfirmawebid = (java.lang.Long)__vr.getFieldValue(__target__,PLUGINFIRMAWEBID);
        java.lang.Long __pluginfirmawebperusrappid = (java.lang.Long)__vr.getFieldValue(__target__,PLUGINFIRMAWEBPERUSRAPPID);
        Long __count_ = null;
        try { __count_ = __pluginFirmaWebPerUsuariAplicacioManager.count(org.fundaciobit.genapp.common.query.Where.AND(USUARIAPLICACIOID.equal(__usuariaplicacioid), PLUGINFIRMAWEBID.equal(__pluginfirmawebid), PLUGINFIRMAWEBPERUSRAPPID.notEqual(__pluginfirmawebperusrappid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(USUARIAPLICACIOID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__usuariaplicacioid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIAPLICACIOID)));
            __vr.rejectValue(PLUGINFIRMAWEBID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__pluginfirmawebid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PLUGINFIRMAWEBID)));
        }
      }

      // Check Unique - no PK
    }

    // Fields with References to Other tables 
    if (__vr.getFieldErrorCount(USUARIAPLICACIOID) == 0) {
      java.lang.String __usuariaplicacioid = (java.lang.String)__vr.getFieldValue(__target__,USUARIAPLICACIOID);
      Long __count_ = null;
      try { __count_ = __usuariAplicacioManager.count(UsuariAplicacioFields.USUARIAPLICACIOID.equal(__usuariaplicacioid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(USUARIAPLICACIOID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuariAplicacio.usuariAplicacio"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuariAplicacio.usuariAplicacioID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__usuariaplicacioid)));
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