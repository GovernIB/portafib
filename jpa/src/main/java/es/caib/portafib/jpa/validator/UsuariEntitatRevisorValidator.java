package es.caib.portafib.jpa.validator;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import es.caib.portafib.model.fields.UsuariEntitatRevisorFields;
import es.caib.portafib.model.fields.UsuariEntitatFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class UsuariEntitatRevisorValidator<T> implements UsuariEntitatRevisorFields {

  protected final Logger log = Logger.getLogger(getClass());


  public UsuariEntitatRevisorValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<T> __vr, T __target__, boolean __isNou__
    ,es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager
    ,es.caib.portafib.model.dao.IUsuariEntitatRevisorManager __usuariEntitatRevisorManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,USUARIENTITATID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIENTITATID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,ACTIU, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ACTIU)));

    // Check size
    if (__vr.getFieldErrorCount(USUARIENTITATID) == 0) {
      java.lang.String __usuarientitatid = (java.lang.String)__vr.getFieldValue(__target__,USUARIENTITATID);
      if (__usuarientitatid!= null && __usuarientitatid.length() > 50) {
        __vr.rejectValue(USUARIENTITATID, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIENTITATID)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(50)));
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

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}