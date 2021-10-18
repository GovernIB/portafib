package es.caib.portafib.persistence.validator;

import org.apache.log4j.Logger;

import es.caib.portafib.model.entity.Metadada;
import org.fundaciobit.genapp.common.query.Field;
import es.caib.portafib.model.fields.MetadadaFields;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class MetadadaValidator<I extends Metadada>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements MetadadaFields {

    protected final Logger log = Logger.getLogger(getClass());


  public MetadadaValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,es.caib.portafib.model.dao.IMetadadaManager __metadadaManager
    ,es.caib.portafib.model.dao.IPeticioDeFirmaManager __peticioDeFirmaManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,NOM, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOM)));

    __vr.rejectIfEmptyOrWhitespace(__target__,VALOR, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(VALOR)));

    __vr.rejectIfEmptyOrWhitespace(__target__,PETICIODEFIRMAID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PETICIODEFIRMAID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,TIPUSMETADADAID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TIPUSMETADADAID)));

    // Check size
    if (__vr.getFieldErrorCount(NOM) == 0) {
      java.lang.String __nom = __target__.getNom();
      if (__nom!= null && __nom.length() > 50) {
        __vr.rejectValue(NOM, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOM)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(50)));
      }
    }

    if (__vr.getFieldErrorCount(VALOR) == 0) {
      java.lang.String __valor = __target__.getValor();
      if (__valor!= null && __valor.length() > 2147483647) {
        __vr.rejectValue(VALOR, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(VALOR)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(2147483647)));
      }
    }

    if (__vr.getFieldErrorCount(DESCRIPCIO) == 0) {
      java.lang.String __descripcio = __target__.getDescripcio();
      if (__descripcio!= null && __descripcio.length() > 1000) {
        __vr.rejectValue(DESCRIPCIO, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DESCRIPCIO)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(1000)));
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
    if (__vr.getFieldErrorCount(PETICIODEFIRMAID) == 0) {
      java.lang.Long __peticiodefirmaid = __target__.getPeticioDeFirmaID();
      Long __count_ = null;
      try { __count_ = __peticioDeFirmaManager.count(PeticioDeFirmaFields.PETICIODEFIRMAID.equal(__peticiodefirmaid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(PETICIODEFIRMAID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("peticioDeFirma.peticioDeFirma"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("peticioDeFirma.peticioDeFirmaID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__peticiodefirmaid)));
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}