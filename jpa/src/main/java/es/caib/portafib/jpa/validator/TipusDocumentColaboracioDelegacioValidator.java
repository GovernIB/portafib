package es.caib.portafib.jpa.validator;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import es.caib.portafib.model.fields.TipusDocumentColaboracioDelegacioFields;
import es.caib.portafib.model.fields.ColaboracioDelegacioFields;
import es.caib.portafib.model.fields.TipusDocumentFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class TipusDocumentColaboracioDelegacioValidator<T> implements TipusDocumentColaboracioDelegacioFields {

  protected final Logger log = Logger.getLogger(getClass());


  public TipusDocumentColaboracioDelegacioValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<T> __vr, T __target__, boolean __isNou__
    ,es.caib.portafib.model.dao.IColaboracioDelegacioManager __colaboracioDelegacioManager
    ,es.caib.portafib.model.dao.ITipusDocumentManager __tipusDocumentManager
    ,es.caib.portafib.model.dao.ITipusDocumentColaboracioDelegacioManager __tipusDocumentColaboracioDelegacioManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,COLABORACIODELEGACIOID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(COLABORACIODELEGACIOID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,TIPUSDOCUMENTID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TIPUSDOCUMENTID)));

    // Check size
    if (__isNou__) { // Creació
      // ================ CREATION
      // Fitxers 
      // ====== Check Unique MULTIPLES - NOU =======

      // Check Unique MULTIPLE for (colaboraciodelegacioid, tipusdocumentid)
      if (__vr.getFieldErrorCount(COLABORACIODELEGACIOID) == 0 && __vr.getFieldErrorCount(TIPUSDOCUMENTID) == 0) {
        java.lang.Long __colaboraciodelegacioid = (java.lang.Long)__vr.getFieldValue(__target__,COLABORACIODELEGACIOID);
        java.lang.Long __tipusdocumentid = (java.lang.Long)__vr.getFieldValue(__target__,TIPUSDOCUMENTID);
        Long __count_ = null;
        try { __count_ = __tipusDocumentColaboracioDelegacioManager.count(org.fundaciobit.genapp.common.query.Where.AND(COLABORACIODELEGACIOID.equal(__colaboraciodelegacioid), TIPUSDOCUMENTID.equal(__tipusdocumentid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(COLABORACIODELEGACIOID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__colaboraciodelegacioid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(COLABORACIODELEGACIOID)));
            __vr.rejectValue(TIPUSDOCUMENTID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__tipusdocumentid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TIPUSDOCUMENTID)));
        }
      }

      // Check Unique - no PK
      // Check Unique - PK no AutoIncrement amb UNA SOLA PK 
    } else {
      // ================ UPDATE

      // ====== Check Unique MULTIPLES - EDIT  =======

      // Check Unique MULTIPLE for (colaboraciodelegacioid, tipusdocumentid)
      if (__vr.getFieldErrorCount(COLABORACIODELEGACIOID) == 0 && __vr.getFieldErrorCount(TIPUSDOCUMENTID) == 0 && __vr.getFieldErrorCount(ID) == 0) {
        java.lang.Long __colaboraciodelegacioid = (java.lang.Long)__vr.getFieldValue(__target__,COLABORACIODELEGACIOID);
        java.lang.Long __tipusdocumentid = (java.lang.Long)__vr.getFieldValue(__target__,TIPUSDOCUMENTID);
        java.lang.Long __id = (java.lang.Long)__vr.getFieldValue(__target__,ID);
        Long __count_ = null;
        try { __count_ = __tipusDocumentColaboracioDelegacioManager.count(org.fundaciobit.genapp.common.query.Where.AND(COLABORACIODELEGACIOID.equal(__colaboraciodelegacioid), TIPUSDOCUMENTID.equal(__tipusdocumentid), ID.notEqual(__id))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(COLABORACIODELEGACIOID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__colaboraciodelegacioid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(COLABORACIODELEGACIOID)));
            __vr.rejectValue(TIPUSDOCUMENTID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__tipusdocumentid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TIPUSDOCUMENTID)));
        }
      }

      // Check Unique - no PK
    }

    // Fields with References to Other tables 
    if (__vr.getFieldErrorCount(COLABORACIODELEGACIOID) == 0) {
      java.lang.Long __colaboraciodelegacioid = (java.lang.Long)__vr.getFieldValue(__target__,COLABORACIODELEGACIOID);
      Long __count_ = null;
      try { __count_ = __colaboracioDelegacioManager.count(ColaboracioDelegacioFields.COLABORACIODELEGACIOID.equal(__colaboraciodelegacioid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(COLABORACIODELEGACIOID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("colaboracioDelegacio.colaboracioDelegacio"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("colaboracioDelegacio.colaboracioDelegacioID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__colaboraciodelegacioid)));
      }
    }

    if (__vr.getFieldErrorCount(TIPUSDOCUMENTID) == 0) {
      java.lang.Long __tipusdocumentid = (java.lang.Long)__vr.getFieldValue(__target__,TIPUSDOCUMENTID);
      Long __count_ = null;
      try { __count_ = __tipusDocumentManager.count(TipusDocumentFields.TIPUSDOCUMENTID.equal(__tipusdocumentid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(TIPUSDOCUMENTID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("tipusDocument.tipusDocument"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("tipusDocument.tipusDocumentID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__tipusdocumentid)));
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}