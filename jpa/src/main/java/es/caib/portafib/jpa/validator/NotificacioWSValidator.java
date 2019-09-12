package es.caib.portafib.jpa.validator;

import es.caib.portafib.model.fields.NotificacioWSFields;
import es.caib.portafib.model.fields.TipusNotificacioFields;
import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class NotificacioWSValidator<T> implements NotificacioWSFields {

  protected final Logger log = Logger.getLogger(getClass());


  public NotificacioWSValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<T> __vr, T __target__, boolean __isNou__
    ,es.caib.portafib.model.dao.INotificacioWSManager __notificacioWSManager
    ,es.caib.portafib.model.dao.ITipusNotificacioManager __tipusNotificacioManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,PETICIODEFIRMAID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PETICIODEFIRMAID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,TIPUSNOTIFICACIOID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TIPUSNOTIFICACIOID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,DATACREACIO, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DATACREACIO)));

    __vr.rejectIfEmptyOrWhitespace(__target__,REINTENTS, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(REINTENTS)));

    // Check size
    if (__vr.getFieldErrorCount(DESCRIPCIO) == 0) {
      java.lang.String __descripcio = (java.lang.String)__vr.getFieldValue(__target__,DESCRIPCIO);
      if (__descripcio!= null && __descripcio.length() > 2147483647) {
        __vr.rejectValue(DESCRIPCIO, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DESCRIPCIO)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(2147483647)));
      }
    }
    
    if (__vr.getFieldErrorCount(ERROR) == 0) {
      java.lang.String __error = (java.lang.String)__vr.getFieldValue(__target__,ERROR);
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