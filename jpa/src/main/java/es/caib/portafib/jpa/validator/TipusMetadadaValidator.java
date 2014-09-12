package es.caib.portafib.jpa.validator;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import es.caib.portafib.model.fields.TipusMetadadaFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class TipusMetadadaValidator<T> implements TipusMetadadaFields {

  protected final Logger log = Logger.getLogger(getClass());


  public TipusMetadadaValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<T> __vr, T __target__, boolean __isNou__
    ,es.caib.portafib.model.dao.ITipusMetadadaManager __tipusMetadadaManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,TIPUSMETADADAID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TIPUSMETADADAID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,NOM, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOM)));

    // Check size
    if (__vr.getFieldErrorCount(NOM) == 0) {
      java.lang.String __nom = (java.lang.String)__vr.getFieldValue(__target__,NOM);
      if (__nom!= null && __nom.length() > 100) {
        __vr.rejectValue(NOM, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOM)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(100)));
      }
    }
    
    if (__vr.getFieldErrorCount(DESCRIPCIO) == 0) {
      java.lang.String __descripcio = (java.lang.String)__vr.getFieldValue(__target__,DESCRIPCIO);
      if (__descripcio!= null && __descripcio.length() > 255) {
        __vr.rejectValue(DESCRIPCIO, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DESCRIPCIO)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }
    
    if (__isNou__) { // Creació
      // ================ CREATION
      // Fitxers 
      // ====== Check Unique MULTIPLES - NOU =======

      // Check Unique - no PK
      if (__vr.getFieldErrorCount(NOM) == 0) {
        java.lang.String __nom = (java.lang.String)__vr.getFieldValue(__target__,NOM);
        Long __count_ = null;
        try { __count_ = __tipusMetadadaManager.count(org.fundaciobit.genapp.common.query.Where.AND(NOM.equal(__nom))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(NOM, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__nom)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOM)));
        }
      }

      // Check Unique - PK no AutoIncrement amb UNA SOLA PK 
      if (__vr.getFieldErrorCount(TIPUSMETADADAID) == 0) {
        java.lang.Integer __tipusmetadadaid = (java.lang.Integer)__vr.getFieldValue(__target__,TIPUSMETADADAID);
        Long __count_ = null;
        try { __count_ = __tipusMetadadaManager.count(org.fundaciobit.genapp.common.query.Where.AND(TIPUSMETADADAID.equal(__tipusmetadadaid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(TIPUSMETADADAID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__tipusmetadadaid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TIPUSMETADADAID)));
        }
      }

    } else {
      // ================ UPDATE

      // ====== Check Unique MULTIPLES - EDIT  =======

      // Check Unique - no PK
      if (__vr.getFieldErrorCount(NOM) == 0 && __vr.getFieldErrorCount(TIPUSMETADADAID) == 0) {
        java.lang.String __nom = (java.lang.String)__vr.getFieldValue(__target__,NOM);
        java.lang.Integer __tipusmetadadaid = (java.lang.Integer)__vr.getFieldValue(__target__,TIPUSMETADADAID);
        Long __count_ = null;
        try { __count_ = __tipusMetadadaManager.count(org.fundaciobit.genapp.common.query.Where.AND(NOM.equal(__nom), TIPUSMETADADAID.notEqual(__tipusmetadadaid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(NOM, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__nom)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOM)));
        }
      }

    }

    // Fields with References to Other tables 
  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}