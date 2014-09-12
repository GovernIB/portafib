package es.caib.portafib.jpa.validator;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import es.caib.portafib.model.fields.PermisUsuariPlantillaFields;
import es.caib.portafib.model.fields.PlantillaFluxDeFirmesFields;
import es.caib.portafib.model.fields.UsuariEntitatFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class PermisUsuariPlantillaValidator<T> implements PermisUsuariPlantillaFields {

  protected final Logger log = Logger.getLogger(getClass());


  public PermisUsuariPlantillaValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<T> __vr, T __target__, boolean __isNou__
    ,es.caib.portafib.model.dao.IPermisUsuariPlantillaManager __permisUsuariPlantillaManager
    ,es.caib.portafib.model.dao.IPlantillaFluxDeFirmesManager __plantillaFluxDeFirmesManager
    ,es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,USUARIENTITATID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIENTITATID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,PLANTILLAFLUXDEFIRMESID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PLANTILLAFLUXDEFIRMESID)));

    // Check size
    if (__vr.getFieldErrorCount(USUARIENTITATID) == 0) {
      java.lang.String __usuarientitatid = (java.lang.String)__vr.getFieldValue(__target__,USUARIENTITATID);
      if (__usuarientitatid!= null && __usuarientitatid.length() > 101) {
        __vr.rejectValue(USUARIENTITATID, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIENTITATID)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(101)));
      }
    }
    
    if (__isNou__) { // Creació
      // ================ CREATION
      // Fitxers 
      // ====== Check Unique MULTIPLES - NOU =======

      // Check Unique MULTIPLE for (usuarientitatid, fluxdefirmesid)
      if (__vr.getFieldErrorCount(USUARIENTITATID) == 0 && __vr.getFieldErrorCount(PLANTILLAFLUXDEFIRMESID) == 0) {
        java.lang.String __usuarientitatid = (java.lang.String)__vr.getFieldValue(__target__,USUARIENTITATID);
        java.lang.Long __plantillafluxdefirmesid = (java.lang.Long)__vr.getFieldValue(__target__,PLANTILLAFLUXDEFIRMESID);
        Long __count_ = null;
        try { __count_ = __permisUsuariPlantillaManager.count(org.fundaciobit.genapp.common.query.Where.AND(USUARIENTITATID.equal(__usuarientitatid), PLANTILLAFLUXDEFIRMESID.equal(__plantillafluxdefirmesid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(USUARIENTITATID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__usuarientitatid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIENTITATID)));
            __vr.rejectValue(PLANTILLAFLUXDEFIRMESID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__plantillafluxdefirmesid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PLANTILLAFLUXDEFIRMESID)));
        }
      }

      // Check Unique - no PK
      // Check Unique - PK no AutoIncrement amb UNA SOLA PK 
    } else {
      // ================ UPDATE

      // ====== Check Unique MULTIPLES - EDIT  =======

      // Check Unique MULTIPLE for (usuarientitatid, fluxdefirmesid)
      if (__vr.getFieldErrorCount(USUARIENTITATID) == 0 && __vr.getFieldErrorCount(PLANTILLAFLUXDEFIRMESID) == 0 && __vr.getFieldErrorCount(PERMISUSUARIPLANTILLAID) == 0) {
        java.lang.String __usuarientitatid = (java.lang.String)__vr.getFieldValue(__target__,USUARIENTITATID);
        java.lang.Long __plantillafluxdefirmesid = (java.lang.Long)__vr.getFieldValue(__target__,PLANTILLAFLUXDEFIRMESID);
        java.lang.Long __permisusuariplantillaid = (java.lang.Long)__vr.getFieldValue(__target__,PERMISUSUARIPLANTILLAID);
        Long __count_ = null;
        try { __count_ = __permisUsuariPlantillaManager.count(org.fundaciobit.genapp.common.query.Where.AND(USUARIENTITATID.equal(__usuarientitatid), PLANTILLAFLUXDEFIRMESID.equal(__plantillafluxdefirmesid), PERMISUSUARIPLANTILLAID.notEqual(__permisusuariplantillaid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(USUARIENTITATID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__usuarientitatid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIENTITATID)));
            __vr.rejectValue(PLANTILLAFLUXDEFIRMESID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__plantillafluxdefirmesid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PLANTILLAFLUXDEFIRMESID)));
        }
      }

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

    if (__vr.getFieldErrorCount(PLANTILLAFLUXDEFIRMESID) == 0) {
      java.lang.Long __plantillafluxdefirmesid = (java.lang.Long)__vr.getFieldValue(__target__,PLANTILLAFLUXDEFIRMESID);
      Long __count_ = null;
      try { __count_ = __plantillaFluxDeFirmesManager.count(PlantillaFluxDeFirmesFields.FLUXDEFIRMESID.equal(__plantillafluxdefirmesid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(PLANTILLAFLUXDEFIRMESID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("plantillaFluxDeFirmes.plantillaFluxDeFirmes"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("plantillaFluxDeFirmes.fluxDeFirmesID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__plantillafluxdefirmesid)));
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}