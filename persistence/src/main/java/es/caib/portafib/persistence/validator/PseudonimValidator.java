package es.caib.portafib.persistence.validator;

import org.apache.log4j.Logger;

import es.caib.portafib.model.entity.Pseudonim;
import org.fundaciobit.genapp.common.query.Field;
import es.caib.portafib.model.fields.PseudonimFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class PseudonimValidator<I extends Pseudonim>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements PseudonimFields {

    protected final Logger log = Logger.getLogger(getClass());


  public PseudonimValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,es.caib.portafib.model.dao.IPseudonimManager __pseudonimManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,PSEUDONIM, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PSEUDONIM)));

    __vr.rejectIfEmptyOrWhitespace(__target__,NIF, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NIF)));

    // Check size
    if (__vr.getFieldErrorCount(PSEUDONIM) == 0) {
      java.lang.String __pseudonim = __target__.getPseudonim();
      if (__pseudonim!= null && __pseudonim.length() > 255) {
        __vr.rejectValue(PSEUDONIM, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PSEUDONIM)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(NIF) == 0) {
      java.lang.String __nif = __target__.getNif();
      if (__nif!= null && __nif.length() > 255) {
        __vr.rejectValue(NIF, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NIF)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
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
  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}