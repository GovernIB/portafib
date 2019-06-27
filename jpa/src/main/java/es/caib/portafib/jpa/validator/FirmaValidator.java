package es.caib.portafib.jpa.validator;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import es.caib.portafib.model.fields.FirmaFields;
import es.caib.portafib.model.fields.BlocDeFirmesFields;
import es.caib.portafib.model.fields.UsuariEntitatFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class FirmaValidator<T> implements FirmaFields {

  protected final Logger log = Logger.getLogger(getClass());


  public FirmaValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<T> __vr, T __target__, boolean __isNou__
    ,es.caib.portafib.model.dao.IBlocDeFirmesManager __blocDeFirmesManager
    ,es.caib.portafib.model.dao.IFirmaManager __firmaManager
    ,es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,DESTINATARIID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DESTINATARIID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,BLOCDEFIRMAID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(BLOCDEFIRMAID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,OBLIGATORI, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(OBLIGATORI)));

    __vr.rejectIfEmptyOrWhitespace(__target__,CAIXAPAGINA, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CAIXAPAGINA)));

    __vr.rejectIfEmptyOrWhitespace(__target__,MOSTRARRUBRICA, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(MOSTRARRUBRICA)));

    __vr.rejectIfEmptyOrWhitespace(__target__,MINIMDEREVISORS, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(MINIMDEREVISORS)));

    // Check size
    if (__vr.getFieldErrorCount(DESTINATARIID) == 0) {
      java.lang.String __destinatariid = (java.lang.String)__vr.getFieldValue(__target__,DESTINATARIID);
      if (__destinatariid!= null && __destinatariid.length() > 101) {
        __vr.rejectValue(DESTINATARIID, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DESTINATARIID)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(101)));
      }
    }
    
    if (__vr.getFieldErrorCount(EMISSORCERTIFICAT) == 0) {
      java.lang.String __emissorcertificat = (java.lang.String)__vr.getFieldValue(__target__,EMISSORCERTIFICAT);
      if (__emissorcertificat!= null && __emissorcertificat.length() > 1000) {
        __vr.rejectValue(EMISSORCERTIFICAT, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(EMISSORCERTIFICAT)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(1000)));
      }
    }
    
    if (__vr.getFieldErrorCount(NOMCERTIFICAT) == 0) {
      java.lang.String __nomcertificat = (java.lang.String)__vr.getFieldValue(__target__,NOMCERTIFICAT);
      if (__nomcertificat!= null && __nomcertificat.length() > 1000) {
        __vr.rejectValue(NOMCERTIFICAT, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOMCERTIFICAT)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(1000)));
      }
    }
    
    if (__vr.getFieldErrorCount(MOTIU) == 0) {
      java.lang.String __motiu = (java.lang.String)__vr.getFieldValue(__target__,MOTIU);
      if (__motiu!= null && __motiu.length() > 255) {
        __vr.rejectValue(MOTIU, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(MOTIU)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }
    
    if (__vr.getFieldErrorCount(PERFILDEFIRMA) == 0) {
      java.lang.String __perfildefirma = (java.lang.String)__vr.getFieldValue(__target__,PERFILDEFIRMA);
      if (__perfildefirma!= null && __perfildefirma.length() > 50) {
        __vr.rejectValue(PERFILDEFIRMA, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PERFILDEFIRMA)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(50)));
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
    if (__vr.getFieldErrorCount(DESTINATARIID) == 0) {
      java.lang.String __destinatariid = (java.lang.String)__vr.getFieldValue(__target__,DESTINATARIID);
      Long __count_ = null;
      try { __count_ = __usuariEntitatManager.count(UsuariEntitatFields.USUARIENTITATID.equal(__destinatariid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(DESTINATARIID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuariEntitat.usuariEntitat"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuariEntitat.usuariEntitatID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__destinatariid)));
      }
    }

    if (__vr.getFieldErrorCount(BLOCDEFIRMAID) == 0) {
      java.lang.Long __blocdefirmaid = (java.lang.Long)__vr.getFieldValue(__target__,BLOCDEFIRMAID);
      Long __count_ = null;
      try { __count_ = __blocDeFirmesManager.count(BlocDeFirmesFields.BLOCDEFIRMESID.equal(__blocdefirmaid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(BLOCDEFIRMAID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("blocDeFirmes.blocDeFirmes"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("blocDeFirmes.blocDeFirmesID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__blocdefirmaid)));
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}