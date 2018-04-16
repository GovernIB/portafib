package es.caib.portafib.jpa.validator;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import es.caib.portafib.model.fields.RevisorDeFirmaFields;
import es.caib.portafib.model.fields.FirmaFields;
import es.caib.portafib.model.fields.UsuariEntitatRevisorFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class RevisorDeFirmaValidator<T> implements RevisorDeFirmaFields {

  protected final Logger log = Logger.getLogger(getClass());


  public RevisorDeFirmaValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<T> __vr, T __target__, boolean __isNou__
    ,es.caib.portafib.model.dao.IFirmaManager __firmaManager
    ,es.caib.portafib.model.dao.IRevisorDeFirmaManager __revisorDeFirmaManager
    ,es.caib.portafib.model.dao.IUsuariEntitatRevisorManager __usuariEntitatRevisorManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,USUARIENTITATREVISORID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIENTITATREVISORID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,FIRMAID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(FIRMAID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,OBLIGATORI, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(OBLIGATORI)));

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
    if (__vr.getFieldErrorCount(USUARIENTITATREVISORID) == 0) {
      java.lang.Long __usuarientitatrevisorid = (java.lang.Long)__vr.getFieldValue(__target__,USUARIENTITATREVISORID);
      Long __count_ = null;
      try { __count_ = __usuariEntitatRevisorManager.count(UsuariEntitatRevisorFields.USUARIENTITATREVISORID.equal(__usuarientitatrevisorid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(USUARIENTITATREVISORID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuariEntitatRevisor.usuariEntitatRevisor"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuariEntitatRevisor.usuariEntitatRevisorId"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__usuarientitatrevisorid)));
      }
    }

    if (__vr.getFieldErrorCount(FIRMAID) == 0) {
      java.lang.Long __firmaid = (java.lang.Long)__vr.getFieldValue(__target__,FIRMAID);
      Long __count_ = null;
      try { __count_ = __firmaManager.count(FirmaFields.FIRMAID.equal(__firmaid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(FIRMAID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("firma.firma"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("firma.firmaID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__firmaid)));
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}