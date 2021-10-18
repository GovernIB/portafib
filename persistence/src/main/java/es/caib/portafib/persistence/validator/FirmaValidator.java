package es.caib.portafib.persistence.validator;

import org.apache.log4j.Logger;

import es.caib.portafib.model.entity.Firma;
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
public class FirmaValidator<I extends Firma>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements FirmaFields {

    protected final Logger log = Logger.getLogger(getClass());


  public FirmaValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
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
      java.lang.String __destinatariid = __target__.getDestinatariID();
      if (__destinatariid!= null && __destinatariid.length() > 101) {
        __vr.rejectValue(DESTINATARIID, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DESTINATARIID)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(101)));
      }
    }

    if (__vr.getFieldErrorCount(EMISSORCERTIFICAT) == 0) {
      java.lang.String __emissorcertificat = __target__.getEmissorCertificat();
      if (__emissorcertificat!= null && __emissorcertificat.length() > 1000) {
        __vr.rejectValue(EMISSORCERTIFICAT, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(EMISSORCERTIFICAT)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(1000)));
      }
    }

    if (__vr.getFieldErrorCount(NOMCERTIFICAT) == 0) {
      java.lang.String __nomcertificat = __target__.getNomCertificat();
      if (__nomcertificat!= null && __nomcertificat.length() > 1000) {
        __vr.rejectValue(NOMCERTIFICAT, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOMCERTIFICAT)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(1000)));
      }
    }

    if (__vr.getFieldErrorCount(MOTIU) == 0) {
      java.lang.String __motiu = __target__.getMotiu();
      if (__motiu!= null && __motiu.length() > 255) {
        __vr.rejectValue(MOTIU, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(MOTIU)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(PERFILDEFIRMA) == 0) {
      java.lang.String __perfildefirma = __target__.getPerfilDeFirma();
      if (__perfildefirma!= null && __perfildefirma.length() > 50) {
        __vr.rejectValue(PERFILDEFIRMA, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PERFILDEFIRMA)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(50)));
      }
    }

    if (__vr.getFieldErrorCount(USUARIEXTERNNOM) == 0) {
      java.lang.String __usuariexternnom = __target__.getUsuariExternNom();
      if (__usuariexternnom!= null && __usuariexternnom.length() > 100) {
        __vr.rejectValue(USUARIEXTERNNOM, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIEXTERNNOM)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(100)));
      }
    }

    if (__vr.getFieldErrorCount(USUARIEXTERNLLINATGES) == 0) {
      java.lang.String __usuariexternllinatges = __target__.getUsuariExternLlinatges();
      if (__usuariexternllinatges!= null && __usuariexternllinatges.length() > 255) {
        __vr.rejectValue(USUARIEXTERNLLINATGES, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIEXTERNLLINATGES)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(USUARIEXTERNEMAIL) == 0) {
      java.lang.String __usuariexternemail = __target__.getUsuariExternEmail();
      if (__usuariexternemail!= null && __usuariexternemail.length() > 255) {
        __vr.rejectValue(USUARIEXTERNEMAIL, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIEXTERNEMAIL)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(USUARIEXTERNIDIOMA) == 0) {
      java.lang.String __usuariexternidioma = __target__.getUsuariExternIdioma();
      if (__usuariexternidioma!= null && __usuariexternidioma.length() > 2) {
        __vr.rejectValue(USUARIEXTERNIDIOMA, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIEXTERNIDIOMA)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(2)));
      }
    }

    if (__vr.getFieldErrorCount(USUARIEXTERNTOKEN) == 0) {
      java.lang.String __usuariexterntoken = __target__.getUsuariExternToken();
      if (__usuariexterntoken!= null && __usuariexterntoken.length() > 255) {
        __vr.rejectValue(USUARIEXTERNTOKEN, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIEXTERNTOKEN)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
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
      java.lang.String __destinatariid = __target__.getDestinatariID();
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
      java.lang.Long __blocdefirmaid = __target__.getBlocDeFirmaID();
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