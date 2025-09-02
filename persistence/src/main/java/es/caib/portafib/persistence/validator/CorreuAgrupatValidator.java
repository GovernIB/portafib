package es.caib.portafib.persistence.validator;

import org.apache.log4j.Logger;

import es.caib.portafib.model.entity.CorreuAgrupat;
import org.fundaciobit.genapp.common.query.Field;
import es.caib.portafib.model.fields.CorreuAgrupatFields;
import es.caib.portafib.model.fields.UsuariEntitatFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class CorreuAgrupatValidator<I extends CorreuAgrupat>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements CorreuAgrupatFields {

    protected final Logger log = Logger.getLogger(getClass());


  public CorreuAgrupatValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,es.caib.portafib.model.dao.ICorreuAgrupatManager __correuAgrupatManager
    ,es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,EMAIL, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(EMAIL)));

    __vr.rejectIfEmptyOrWhitespace(__target__,SUBJECT, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(SUBJECT)));

    __vr.rejectIfEmptyOrWhitespace(__target__,MESSAGE, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(MESSAGE)));

    __vr.rejectIfEmptyOrWhitespace(__target__,HTML, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(HTML)));

    __vr.rejectIfEmptyOrWhitespace(__target__,USUARIENTITATID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIENTITATID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,DATACREACIO, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DATACREACIO)));

    // Check size
    if (__vr.getFieldErrorCount(EMAIL) == 0) {
      java.lang.String __email = __target__.getEmail();
      if (__email!= null && __email.length() > 255) {
        __vr.rejectValue(EMAIL, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(EMAIL)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(SUBJECT) == 0) {
      java.lang.String __subject = __target__.getSubject();
      if (__subject!= null && __subject.length() > 255) {
        __vr.rejectValue(SUBJECT, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(SUBJECT)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(MESSAGE) == 0) {
      java.lang.String __message = __target__.getMessage();
      if (__message!= null && __message.length() > 2147483647) {
        __vr.rejectValue(MESSAGE, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(MESSAGE)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(2147483647)));
      }
    }

    if (__vr.getFieldErrorCount(USUARIENTITATID) == 0) {
      java.lang.String __usuarientitatid = __target__.getUsuariEntitatID();
      if (__usuarientitatid!= null && __usuarientitatid.length() > 101) {
        __vr.rejectValue(USUARIENTITATID, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIENTITATID)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(101)));
      }
    }

    if (__vr.getFieldErrorCount(ERROR) == 0) {
      java.lang.String __error = __target__.getError();
      if (__error!= null && __error.length() > 2147483647) {
        __vr.rejectValue(ERROR, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ERROR)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(2147483647)));
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
    if (__vr.getFieldErrorCount(USUARIENTITATID) == 0) {
      java.lang.String __usuarientitatid = __target__.getUsuariEntitatID();
      Long __count_ = null;
      try { __count_ = __usuariEntitatManager.count(UsuariEntitatFields.USUARIENTITATID.equal(__usuarientitatid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(USUARIENTITATID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuariEntitat.usuariEntitat"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuariEntitat.usuariEntitatID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__usuarientitatid)));
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}