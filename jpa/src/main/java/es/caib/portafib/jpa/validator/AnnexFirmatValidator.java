package es.caib.portafib.jpa.validator;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import es.caib.portafib.model.fields.AnnexFirmatFields;
import es.caib.portafib.model.fields.AnnexFields;
import es.caib.portafib.model.fields.FirmaFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class AnnexFirmatValidator<T> implements AnnexFirmatFields {

  protected final Logger log = Logger.getLogger(getClass());


  public AnnexFirmatValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<T> __vr, T __target__, boolean __isNou__
    ,es.caib.portafib.model.dao.IAnnexManager __annexManager
    ,es.caib.portafib.model.dao.IAnnexFirmatManager __annexFirmatManager
    ,es.caib.portafib.model.dao.IFirmaManager __firmaManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,ANNEXID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ANNEXID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,FIRMAID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(FIRMAID)));

    // Check size
    if (__isNou__) { // Creació
      // ================ CREATION
      // Fitxers 
    if (__vr.getFieldErrorCount(FITXERID) == 0) { // FITXER
      Object __value = __vr.getFieldValue(__target__,FITXERID);
      if (__value == null || String.valueOf(__value).trim().length() == 0 || String.valueOf(__value).trim().equals("0") ) {
          __vr.rejectValue(FITXERID, "genapp.validation.required",
             new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(FITXERID)));
      }
    }

      // ====== Check Unique MULTIPLES - NOU =======

      // Check Unique - no PK
      // Check Unique - PK no AutoIncrement amb UNA SOLA PK 
    } else {
      // ================ UPDATE

      // ====== Check Unique MULTIPLES - EDIT  =======

      // Check Unique - no PK
    }

    // Fields with References to Other tables 
    if (__vr.getFieldErrorCount(ANNEXID) == 0) {
      java.lang.Long __annexid = (java.lang.Long)__vr.getFieldValue(__target__,ANNEXID);
      Long __count_ = null;
      try { __count_ = __annexManager.count(AnnexFields.ANNEXID.equal(__annexid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(ANNEXID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("annex.annex"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("annex.annexID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__annexid)));
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