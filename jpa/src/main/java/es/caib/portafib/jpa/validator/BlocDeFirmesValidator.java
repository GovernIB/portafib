package es.caib.portafib.jpa.validator;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import es.caib.portafib.model.fields.BlocDeFirmesFields;
import es.caib.portafib.model.fields.FluxDeFirmesFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class BlocDeFirmesValidator<T> implements BlocDeFirmesFields {

  protected final Logger log = Logger.getLogger(getClass());


  public BlocDeFirmesValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<T> __vr, T __target__, boolean __isNou__
    ,es.caib.portafib.model.dao.IBlocDeFirmesManager __blocDeFirmesManager
    ,es.caib.portafib.model.dao.IFluxDeFirmesManager __fluxDeFirmesManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,ORDRE, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ORDRE)));

    __vr.rejectIfEmptyOrWhitespace(__target__,FLUXDEFIRMESID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(FLUXDEFIRMESID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,MINIMDEFIRMES, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(MINIMDEFIRMES)));

    // Check size
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
    if (__vr.getFieldErrorCount(FLUXDEFIRMESID) == 0) {
      java.lang.Long __fluxdefirmesid = (java.lang.Long)__vr.getFieldValue(__target__,FLUXDEFIRMESID);
      Long __count_ = null;
      try { __count_ = __fluxDeFirmesManager.count(FluxDeFirmesFields.FLUXDEFIRMESID.equal(__fluxdefirmesid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(FLUXDEFIRMESID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("fluxDeFirmes.fluxDeFirmes"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("fluxDeFirmes.fluxDeFirmesID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__fluxdefirmesid)));
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}