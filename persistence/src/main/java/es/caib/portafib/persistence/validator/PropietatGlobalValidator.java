package es.caib.portafib.persistence.validator;

import org.apache.log4j.Logger;

import es.caib.portafib.model.entity.PropietatGlobal;
import org.fundaciobit.genapp.common.query.Field;
import es.caib.portafib.model.fields.PropietatGlobalFields;
import es.caib.portafib.model.fields.EntitatFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class PropietatGlobalValidator<I extends PropietatGlobal>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements PropietatGlobalFields {

    protected final Logger log = Logger.getLogger(getClass());


  public PropietatGlobalValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,es.caib.portafib.model.dao.IEntitatManager __entitatManager
    ,es.caib.portafib.model.dao.IPropietatGlobalManager __propietatGlobalManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,CLAU, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CLAU)));

    // Check size
    if (__vr.getFieldErrorCount(CLAU) == 0) {
      java.lang.String __clau = __target__.getClau();
      if (__clau!= null && __clau.length() > 255) {
        __vr.rejectValue(CLAU, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CLAU)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(VALOR) == 0) {
      java.lang.String __valor = __target__.getValor();
      if (__valor!= null && __valor.length() > 255) {
        __vr.rejectValue(VALOR, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(VALOR)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(ENTITATID) == 0) {
      java.lang.String __entitatid = __target__.getEntitatID();
      if (__entitatid!= null && __entitatid.length() > 50) {
        __vr.rejectValue(ENTITATID, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ENTITATID)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(50)));
      }
    }

    if (__vr.getFieldErrorCount(DESCRIPCIO) == 0) {
      java.lang.String __descripcio = __target__.getDescripcio();
      if (__descripcio!= null && __descripcio.length() > 1000) {
        __vr.rejectValue(DESCRIPCIO, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DESCRIPCIO)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(1000)));
      }
    }

    if (__isNou__) { // Creació
      // ================ CREATION
      // Fitxers 
      // ====== Check Unique MULTIPLES - NOU =======

      // Check Unique MULTIPLE for (clau, entitatid)
      if (__vr.getFieldErrorCount(CLAU) == 0 && __vr.getFieldErrorCount(ENTITATID) == 0) {
        java.lang.String __clau = __target__.getClau();
        java.lang.String __entitatid = __target__.getEntitatID();
        Long __count_ = null;
        try { __count_ = __propietatGlobalManager.count(org.fundaciobit.genapp.common.query.Where.AND(CLAU.equal(__clau), ENTITATID.equal(__entitatid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(CLAU, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__clau)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CLAU)));
            __vr.rejectValue(ENTITATID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__entitatid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ENTITATID)));
        }
      }

      // Check Unique - no PK
      // Check Unique - PK no AutoIncrement amb UNA SOLA PK 
    } else {
      // ================ UPDATE

      // ====== Check Unique MULTIPLES - EDIT  =======

      // Check Unique MULTIPLE for (clau, entitatid)
      if (__vr.getFieldErrorCount(CLAU) == 0 && __vr.getFieldErrorCount(ENTITATID) == 0 && __vr.getFieldErrorCount(PROPIETATGLOBALID) == 0) {
        java.lang.String __clau = __target__.getClau();
        java.lang.String __entitatid = __target__.getEntitatID();
        java.lang.Long __propietatglobalid = __target__.getPropietatGlobalID();
        Long __count_ = null;
        try { __count_ = __propietatGlobalManager.count(org.fundaciobit.genapp.common.query.Where.AND(CLAU.equal(__clau), ENTITATID.equal(__entitatid), PROPIETATGLOBALID.notEqual(__propietatglobalid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(CLAU, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__clau)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CLAU)));
            __vr.rejectValue(ENTITATID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__entitatid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ENTITATID)));
        }
      }

      // Check Unique - no PK
    }

    // Fields with References to Other tables 
    if (__vr.getFieldErrorCount(ENTITATID) == 0) {
      java.lang.String __entitatid = __target__.getEntitatID();
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

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}