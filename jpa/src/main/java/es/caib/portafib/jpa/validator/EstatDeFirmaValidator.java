package es.caib.portafib.jpa.validator;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import es.caib.portafib.model.fields.EstatDeFirmaFields;
import es.caib.portafib.model.fields.ColaboracioDelegacioFields;
import es.caib.portafib.model.fields.FirmaFields;
import es.caib.portafib.model.fields.TipusEstatDeFirmaFinalFields;
import es.caib.portafib.model.fields.TipusEstatDeFirmaInicialFields;
import es.caib.portafib.model.fields.UsuariEntitatFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class EstatDeFirmaValidator<T> implements EstatDeFirmaFields {

  protected final Logger log = Logger.getLogger(getClass());


  public EstatDeFirmaValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<T> __vr, T __target__, boolean __isNou__
    ,es.caib.portafib.model.dao.IColaboracioDelegacioManager __colaboracioDelegacioManager
    ,es.caib.portafib.model.dao.IEstatDeFirmaManager __estatDeFirmaManager
    ,es.caib.portafib.model.dao.IFirmaManager __firmaManager
    ,es.caib.portafib.model.dao.ITipusEstatDeFirmaFinalManager __tipusEstatDeFirmaFinalManager
    ,es.caib.portafib.model.dao.ITipusEstatDeFirmaInicialManager __tipusEstatDeFirmaInicialManager
    ,es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,FIRMAID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(FIRMAID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,USUARIENTITATID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIENTITATID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,DATAINICI, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DATAINICI)));

    __vr.rejectIfEmptyOrWhitespace(__target__,TIPUSESTATDEFIRMAINICIALID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TIPUSESTATDEFIRMAINICIALID)));

    // Check size
    if (__vr.getFieldErrorCount(USUARIENTITATID) == 0) {
      java.lang.String __usuarientitatid = (java.lang.String)__vr.getFieldValue(__target__,USUARIENTITATID);
      if (__usuarientitatid!= null && __usuarientitatid.length() > 101) {
        __vr.rejectValue(USUARIENTITATID, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIENTITATID)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(101)));
      }
    }
    
    if (__vr.getFieldErrorCount(DESCRIPCIO) == 0) {
      java.lang.String __descripcio = (java.lang.String)__vr.getFieldValue(__target__,DESCRIPCIO);
      if (__descripcio!= null && __descripcio.length() > 255) {
        __vr.rejectValue(DESCRIPCIO, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DESCRIPCIO)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
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

    if (__vr.getFieldErrorCount(TIPUSESTATDEFIRMAINICIALID) == 0) {
      java.lang.Long __tipusestatdefirmainicialid = (java.lang.Long)__vr.getFieldValue(__target__,TIPUSESTATDEFIRMAINICIALID);
      Long __count_ = null;
      try { __count_ = __tipusEstatDeFirmaInicialManager.count(TipusEstatDeFirmaInicialFields.TIPUSESTATDEFIRMAINICIALID.equal(__tipusestatdefirmainicialid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(TIPUSESTATDEFIRMAINICIALID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("tipusEstatDeFirmaInicial.tipusEstatDeFirmaInicial"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("tipusEstatDeFirmaInicial.tipusEstatDeFirmaInicialID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__tipusestatdefirmainicialid)));
      }
    }

    if (__vr.getFieldErrorCount(TIPUSESTATDEFIRMAFINALID) == 0) {
      java.lang.Long __tipusestatdefirmafinalid = (java.lang.Long)__vr.getFieldValue(__target__,TIPUSESTATDEFIRMAFINALID);
      if (__tipusestatdefirmafinalid != null ) {
        Long __count_ = null;
        try { __count_ = __tipusEstatDeFirmaFinalManager.count(TipusEstatDeFirmaFinalFields.TIPUSESTATDEFIRMAFINALID.equal(__tipusestatdefirmafinalid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ == 0) {        
          __vr.rejectValue(TIPUSESTATDEFIRMAFINALID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("tipusEstatDeFirmaFinal.tipusEstatDeFirmaFinal"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("tipusEstatDeFirmaFinal.tipusEstatDeFirmaFinalID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__tipusestatdefirmafinalid)));
        }
      }
    }

    if (__vr.getFieldErrorCount(COLABORACIODELEGACIOID) == 0) {
      java.lang.Long __colaboraciodelegacioid = (java.lang.Long)__vr.getFieldValue(__target__,COLABORACIODELEGACIOID);
      if (__colaboraciodelegacioid != null ) {
        Long __count_ = null;
        try { __count_ = __colaboracioDelegacioManager.count(ColaboracioDelegacioFields.COLABORACIODELEGACIOID.equal(__colaboraciodelegacioid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ == 0) {        
          __vr.rejectValue(COLABORACIODELEGACIOID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("colaboracioDelegacio.colaboracioDelegacio"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("colaboracioDelegacio.colaboracioDelegacioID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__colaboraciodelegacioid)));
        }
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}