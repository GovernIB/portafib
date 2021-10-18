package es.caib.portafib.persistence.validator;

import org.apache.log4j.Logger;

import es.caib.portafib.model.entity.PermisGrupPlantilla;
import org.fundaciobit.genapp.common.query.Field;
import es.caib.portafib.model.fields.PermisGrupPlantillaFields;
import es.caib.portafib.model.fields.GrupEntitatFields;
import es.caib.portafib.model.fields.PlantillaFluxDeFirmesFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class PermisGrupPlantillaValidator<I extends PermisGrupPlantilla>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements PermisGrupPlantillaFields {

    protected final Logger log = Logger.getLogger(getClass());


  public PermisGrupPlantillaValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,es.caib.portafib.model.dao.IGrupEntitatManager __grupEntitatManager
    ,es.caib.portafib.model.dao.IPermisGrupPlantillaManager __permisGrupPlantillaManager
    ,es.caib.portafib.model.dao.IPlantillaFluxDeFirmesManager __plantillaFluxDeFirmesManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,GRUPENTITATID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(GRUPENTITATID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,PLANTILLAFLUXDEFIRMESID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PLANTILLAFLUXDEFIRMESID)));

    // Check size
    if (__isNou__) { // Creació
      // ================ CREATION
      // Fitxers 
      // ====== Check Unique MULTIPLES - NOU =======

      // Check Unique MULTIPLE for (grupentitatid, fluxdefirmesid)
      if (__vr.getFieldErrorCount(GRUPENTITATID) == 0 && __vr.getFieldErrorCount(PLANTILLAFLUXDEFIRMESID) == 0) {
        java.lang.Long __grupentitatid = __target__.getGrupEntitatID();
        java.lang.Long __plantillafluxdefirmesid = __target__.getPlantillaFluxDeFirmesID();
        Long __count_ = null;
        try { __count_ = __permisGrupPlantillaManager.count(org.fundaciobit.genapp.common.query.Where.AND(GRUPENTITATID.equal(__grupentitatid), PLANTILLAFLUXDEFIRMESID.equal(__plantillafluxdefirmesid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(GRUPENTITATID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__grupentitatid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(GRUPENTITATID)));
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

      // Check Unique MULTIPLE for (grupentitatid, fluxdefirmesid)
      if (__vr.getFieldErrorCount(GRUPENTITATID) == 0 && __vr.getFieldErrorCount(PLANTILLAFLUXDEFIRMESID) == 0 && __vr.getFieldErrorCount(PERMISGRUPPLANTILLAID) == 0) {
        java.lang.Long __grupentitatid = __target__.getGrupEntitatID();
        java.lang.Long __plantillafluxdefirmesid = __target__.getPlantillaFluxDeFirmesID();
        java.lang.Long __permisgrupplantillaid = __target__.getPermisGrupPlantillaID();
        Long __count_ = null;
        try { __count_ = __permisGrupPlantillaManager.count(org.fundaciobit.genapp.common.query.Where.AND(GRUPENTITATID.equal(__grupentitatid), PLANTILLAFLUXDEFIRMESID.equal(__plantillafluxdefirmesid), PERMISGRUPPLANTILLAID.notEqual(__permisgrupplantillaid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(GRUPENTITATID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__grupentitatid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(GRUPENTITATID)));
            __vr.rejectValue(PLANTILLAFLUXDEFIRMESID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__plantillafluxdefirmesid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PLANTILLAFLUXDEFIRMESID)));
        }
      }

      // Check Unique - no PK
    }

    // Fields with References to Other tables 
    if (__vr.getFieldErrorCount(GRUPENTITATID) == 0) {
      java.lang.Long __grupentitatid = __target__.getGrupEntitatID();
      Long __count_ = null;
      try { __count_ = __grupEntitatManager.count(GrupEntitatFields.GRUPENTITATID.equal(__grupentitatid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(GRUPENTITATID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("grupEntitat.grupEntitat"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("grupEntitat.grupEntitatID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__grupentitatid)));
      }
    }

    if (__vr.getFieldErrorCount(PLANTILLAFLUXDEFIRMESID) == 0) {
      java.lang.Long __plantillafluxdefirmesid = __target__.getPlantillaFluxDeFirmesID();
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