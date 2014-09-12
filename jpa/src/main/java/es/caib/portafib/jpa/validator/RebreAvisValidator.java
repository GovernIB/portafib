package es.caib.portafib.jpa.validator;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import es.caib.portafib.model.fields.RebreAvisFields;
import es.caib.portafib.model.fields.TipusNotificacioFields;
import es.caib.portafib.model.fields.UsuariEntitatFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class RebreAvisValidator<T> implements RebreAvisFields {

  protected final Logger log = Logger.getLogger(getClass());


  public RebreAvisValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<T> __vr, T __target__, boolean __isNou__
    ,es.caib.portafib.model.dao.IRebreAvisManager __rebreAvisManager
    ,es.caib.portafib.model.dao.ITipusNotificacioManager __tipusNotificacioManager
    ,es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,USUARIENTITATID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIENTITATID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,TIPUSNOTIFICACIOID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TIPUSNOTIFICACIOID)));

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

      // Check Unique MULTIPLE for (tipusnotificacioid, usuarientitatid)
      if (__vr.getFieldErrorCount(TIPUSNOTIFICACIOID) == 0 && __vr.getFieldErrorCount(USUARIENTITATID) == 0) {
        java.lang.Long __tipusnotificacioid = (java.lang.Long)__vr.getFieldValue(__target__,TIPUSNOTIFICACIOID);
        java.lang.String __usuarientitatid = (java.lang.String)__vr.getFieldValue(__target__,USUARIENTITATID);
        Long __count_ = null;
        try { __count_ = __rebreAvisManager.count(org.fundaciobit.genapp.common.query.Where.AND(TIPUSNOTIFICACIOID.equal(__tipusnotificacioid), USUARIENTITATID.equal(__usuarientitatid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(TIPUSNOTIFICACIOID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__tipusnotificacioid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TIPUSNOTIFICACIOID)));
            __vr.rejectValue(USUARIENTITATID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__usuarientitatid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIENTITATID)));
        }
      }

      // Check Unique - no PK
      // Check Unique - PK no AutoIncrement amb UNA SOLA PK 
    } else {
      // ================ UPDATE

      // ====== Check Unique MULTIPLES - EDIT  =======

      // Check Unique MULTIPLE for (tipusnotificacioid, usuarientitatid)
      if (__vr.getFieldErrorCount(TIPUSNOTIFICACIOID) == 0 && __vr.getFieldErrorCount(USUARIENTITATID) == 0 && __vr.getFieldErrorCount(ID) == 0) {
        java.lang.Long __tipusnotificacioid = (java.lang.Long)__vr.getFieldValue(__target__,TIPUSNOTIFICACIOID);
        java.lang.String __usuarientitatid = (java.lang.String)__vr.getFieldValue(__target__,USUARIENTITATID);
        java.lang.Long __id = (java.lang.Long)__vr.getFieldValue(__target__,ID);
        Long __count_ = null;
        try { __count_ = __rebreAvisManager.count(org.fundaciobit.genapp.common.query.Where.AND(TIPUSNOTIFICACIOID.equal(__tipusnotificacioid), USUARIENTITATID.equal(__usuarientitatid), ID.notEqual(__id))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(TIPUSNOTIFICACIOID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__tipusnotificacioid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TIPUSNOTIFICACIOID)));
            __vr.rejectValue(USUARIENTITATID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__usuarientitatid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIENTITATID)));
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

    if (__vr.getFieldErrorCount(TIPUSNOTIFICACIOID) == 0) {
      java.lang.Long __tipusnotificacioid = (java.lang.Long)__vr.getFieldValue(__target__,TIPUSNOTIFICACIOID);
      Long __count_ = null;
      try { __count_ = __tipusNotificacioManager.count(TipusNotificacioFields.TIPUSNOTIFICACIOID.equal(__tipusnotificacioid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(TIPUSNOTIFICACIOID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("tipusNotificacio.tipusNotificacio"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("tipusNotificacio.tipusNotificacioID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__tipusnotificacioid)));
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}