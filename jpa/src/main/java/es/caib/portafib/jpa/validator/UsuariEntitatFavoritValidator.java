package es.caib.portafib.jpa.validator;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import es.caib.portafib.model.fields.UsuariEntitatFavoritFields;
import es.caib.portafib.model.fields.UsuariEntitatFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class UsuariEntitatFavoritValidator<T> implements UsuariEntitatFavoritFields {

  protected final Logger log = Logger.getLogger(getClass());


  public UsuariEntitatFavoritValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<T> __vr, T __target__, boolean __isNou__
    ,es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager
    ,es.caib.portafib.model.dao.IUsuariEntitatFavoritManager __usuariEntitatFavoritManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,ORIGENID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ORIGENID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,FAVORITID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(FAVORITID)));

    // Check size
    if (__vr.getFieldErrorCount(ORIGENID) == 0) {
      java.lang.String __origenid = (java.lang.String)__vr.getFieldValue(__target__,ORIGENID);
      if (__origenid!= null && __origenid.length() > 101) {
        __vr.rejectValue(ORIGENID, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ORIGENID)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(101)));
      }
    }
    
    if (__vr.getFieldErrorCount(FAVORITID) == 0) {
      java.lang.String __favoritid = (java.lang.String)__vr.getFieldValue(__target__,FAVORITID);
      if (__favoritid!= null && __favoritid.length() > 101) {
        __vr.rejectValue(FAVORITID, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(FAVORITID)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(101)));
      }
    }
    
    if (__isNou__) { // Creació
      // ================ CREATION
      // Fitxers 
      // ====== Check Unique MULTIPLES - NOU =======

      // Check Unique MULTIPLE for (origenid, favoritid)
      if (__vr.getFieldErrorCount(ORIGENID) == 0 && __vr.getFieldErrorCount(FAVORITID) == 0) {
        java.lang.String __origenid = (java.lang.String)__vr.getFieldValue(__target__,ORIGENID);
        java.lang.String __favoritid = (java.lang.String)__vr.getFieldValue(__target__,FAVORITID);
        Long __count_ = null;
        try { __count_ = __usuariEntitatFavoritManager.count(org.fundaciobit.genapp.common.query.Where.AND(ORIGENID.equal(__origenid), FAVORITID.equal(__favoritid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(ORIGENID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__origenid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ORIGENID)));
            __vr.rejectValue(FAVORITID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__favoritid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(FAVORITID)));
        }
      }

      // Check Unique - no PK
      // Check Unique - PK no AutoIncrement amb UNA SOLA PK 
    } else {
      // ================ UPDATE

      // ====== Check Unique MULTIPLES - EDIT  =======

      // Check Unique MULTIPLE for (origenid, favoritid)
      if (__vr.getFieldErrorCount(ORIGENID) == 0 && __vr.getFieldErrorCount(FAVORITID) == 0 && __vr.getFieldErrorCount(ID) == 0) {
        java.lang.String __origenid = (java.lang.String)__vr.getFieldValue(__target__,ORIGENID);
        java.lang.String __favoritid = (java.lang.String)__vr.getFieldValue(__target__,FAVORITID);
        java.lang.Long __id = (java.lang.Long)__vr.getFieldValue(__target__,ID);
        Long __count_ = null;
        try { __count_ = __usuariEntitatFavoritManager.count(org.fundaciobit.genapp.common.query.Where.AND(ORIGENID.equal(__origenid), FAVORITID.equal(__favoritid), ID.notEqual(__id))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(ORIGENID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__origenid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ORIGENID)));
            __vr.rejectValue(FAVORITID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__favoritid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(FAVORITID)));
        }
      }

      // Check Unique - no PK
    }

    // Fields with References to Other tables 
    if (__vr.getFieldErrorCount(ORIGENID) == 0) {
      java.lang.String __origenid = (java.lang.String)__vr.getFieldValue(__target__,ORIGENID);
      Long __count_ = null;
      try { __count_ = __usuariEntitatManager.count(UsuariEntitatFields.USUARIENTITATID.equal(__origenid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(ORIGENID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuariEntitat.usuariEntitat"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuariEntitat.usuariEntitatID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__origenid)));
      }
    }

    if (__vr.getFieldErrorCount(FAVORITID) == 0) {
      java.lang.String __favoritid = (java.lang.String)__vr.getFieldValue(__target__,FAVORITID);
      Long __count_ = null;
      try { __count_ = __usuariEntitatManager.count(UsuariEntitatFields.USUARIENTITATID.equal(__favoritid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(FAVORITID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuariEntitat.usuariEntitat"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuariEntitat.usuariEntitatID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__favoritid)));
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}