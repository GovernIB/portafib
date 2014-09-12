package es.caib.portafib.jpa.validator;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import es.caib.portafib.model.fields.UsuariEntitatFields;
import es.caib.portafib.model.fields.EntitatFields;
import es.caib.portafib.model.fields.UsuariPersonaFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class UsuariEntitatValidator<T> implements UsuariEntitatFields {

  protected final Logger log = Logger.getLogger(getClass());


  public UsuariEntitatValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<T> __vr, T __target__, boolean __isNou__
    ,es.caib.portafib.model.dao.IEntitatManager __entitatManager
    ,es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager
    ,es.caib.portafib.model.dao.IUsuariPersonaManager __usuariPersonaManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,USUARIENTITATID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIENTITATID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,USUARIPERSONAID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIPERSONAID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,ENTITATID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ENTITATID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,ACTIU, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ACTIU)));

    __vr.rejectIfEmptyOrWhitespace(__target__,PREDETERMINAT, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PREDETERMINAT)));

    __vr.rejectIfEmptyOrWhitespace(__target__,REBRETOTSELSAVISOS, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(REBRETOTSELSAVISOS)));

    // Check size
    if (__vr.getFieldErrorCount(USUARIENTITATID) == 0) {
      java.lang.String __usuarientitatid = (java.lang.String)__vr.getFieldValue(__target__,USUARIENTITATID);
      if (__usuarientitatid!= null && __usuarientitatid.length() > 101) {
        __vr.rejectValue(USUARIENTITATID, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIENTITATID)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(101)));
      }
    }
    
    if (__vr.getFieldErrorCount(CARREC) == 0) {
      java.lang.String __carrec = (java.lang.String)__vr.getFieldValue(__target__,CARREC);
      if (__carrec!= null && __carrec.length() > 150) {
        __vr.rejectValue(CARREC, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CARREC)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(150)));
      }
    }
    
    if (__vr.getFieldErrorCount(USUARIPERSONAID) == 0) {
      java.lang.String __usuaripersonaid = (java.lang.String)__vr.getFieldValue(__target__,USUARIPERSONAID);
      if (__usuaripersonaid!= null && __usuaripersonaid.length() > 50) {
        __vr.rejectValue(USUARIPERSONAID, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIPERSONAID)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(50)));
      }
    }
    
    if (__vr.getFieldErrorCount(ENTITATID) == 0) {
      java.lang.String __entitatid = (java.lang.String)__vr.getFieldValue(__target__,ENTITATID);
      if (__entitatid!= null && __entitatid.length() > 50) {
        __vr.rejectValue(ENTITATID, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ENTITATID)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(50)));
      }
    }
    
    if (__vr.getFieldErrorCount(EMAIL) == 0) {
      java.lang.String __email = (java.lang.String)__vr.getFieldValue(__target__,EMAIL);
      if (__email!= null && __email.length() > 100) {
        __vr.rejectValue(EMAIL, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(EMAIL)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(100)));
      }
    }
    
    if (__isNou__) { // Creació
      // ================ CREATION
      // Fitxers 
      // ====== Check Unique MULTIPLES - NOU =======

      // Check Unique MULTIPLE for (usuaripersonaid, entitatid, carrec)
      if (__vr.getFieldErrorCount(USUARIPERSONAID) == 0 && __vr.getFieldErrorCount(ENTITATID) == 0 && __vr.getFieldErrorCount(CARREC) == 0) {
        java.lang.String __usuaripersonaid = (java.lang.String)__vr.getFieldValue(__target__,USUARIPERSONAID);
        java.lang.String __entitatid = (java.lang.String)__vr.getFieldValue(__target__,ENTITATID);
        java.lang.String __carrec = (java.lang.String)__vr.getFieldValue(__target__,CARREC);
        Long __count_ = null;
        try { __count_ = __usuariEntitatManager.count(org.fundaciobit.genapp.common.query.Where.AND(USUARIPERSONAID.equal(__usuaripersonaid), ENTITATID.equal(__entitatid), CARREC.equal(__carrec))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(USUARIPERSONAID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__usuaripersonaid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIPERSONAID)));
            __vr.rejectValue(ENTITATID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__entitatid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ENTITATID)));
            __vr.rejectValue(CARREC, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__carrec)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CARREC)));
        }
      }

      // Check Unique - no PK
      // Check Unique - PK no AutoIncrement amb UNA SOLA PK 
      if (__vr.getFieldErrorCount(USUARIENTITATID) == 0) {
        java.lang.String __usuarientitatid = (java.lang.String)__vr.getFieldValue(__target__,USUARIENTITATID);
        Long __count_ = null;
        try { __count_ = __usuariEntitatManager.count(org.fundaciobit.genapp.common.query.Where.AND(USUARIENTITATID.equal(__usuarientitatid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(USUARIENTITATID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__usuarientitatid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIENTITATID)));
        }
      }

    } else {
      // ================ UPDATE

      // ====== Check Unique MULTIPLES - EDIT  =======

      // Check Unique MULTIPLE for (usuaripersonaid, entitatid, carrec)
      if (__vr.getFieldErrorCount(USUARIPERSONAID) == 0 && __vr.getFieldErrorCount(ENTITATID) == 0 && __vr.getFieldErrorCount(CARREC) == 0 && __vr.getFieldErrorCount(USUARIENTITATID) == 0) {
        java.lang.String __usuaripersonaid = (java.lang.String)__vr.getFieldValue(__target__,USUARIPERSONAID);
        java.lang.String __entitatid = (java.lang.String)__vr.getFieldValue(__target__,ENTITATID);
        java.lang.String __carrec = (java.lang.String)__vr.getFieldValue(__target__,CARREC);
        java.lang.String __usuarientitatid = (java.lang.String)__vr.getFieldValue(__target__,USUARIENTITATID);
        Long __count_ = null;
        try { __count_ = __usuariEntitatManager.count(org.fundaciobit.genapp.common.query.Where.AND(USUARIPERSONAID.equal(__usuaripersonaid), ENTITATID.equal(__entitatid), CARREC.equal(__carrec), USUARIENTITATID.notEqual(__usuarientitatid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(USUARIPERSONAID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__usuaripersonaid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIPERSONAID)));
            __vr.rejectValue(ENTITATID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__entitatid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ENTITATID)));
            __vr.rejectValue(CARREC, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__carrec)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CARREC)));
        }
      }

      // Check Unique - no PK
    }

    // Fields with References to Other tables 
    if (__vr.getFieldErrorCount(USUARIPERSONAID) == 0) {
      java.lang.String __usuaripersonaid = (java.lang.String)__vr.getFieldValue(__target__,USUARIPERSONAID);
      Long __count_ = null;
      try { __count_ = __usuariPersonaManager.count(UsuariPersonaFields.USUARIPERSONAID.equal(__usuaripersonaid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(USUARIPERSONAID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuariPersona.usuariPersona"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuariPersona.usuariPersonaID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__usuaripersonaid)));
      }
    }

    if (__vr.getFieldErrorCount(ENTITATID) == 0) {
      java.lang.String __entitatid = (java.lang.String)__vr.getFieldValue(__target__,ENTITATID);
      Long __count_ = null;
      try { __count_ = __entitatManager.count(EntitatFields.ENTITATID.equal(__entitatid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(ENTITATID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("entitat.entitat"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("entitat.entitatID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__entitatid)));
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}