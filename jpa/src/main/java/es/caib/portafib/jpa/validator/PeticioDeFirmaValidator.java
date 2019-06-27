package es.caib.portafib.jpa.validator;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;
import es.caib.portafib.model.fields.CustodiaInfoFields;
import es.caib.portafib.model.fields.FluxDeFirmesFields;
import es.caib.portafib.model.fields.IdiomaFields;
import es.caib.portafib.model.fields.TipusDocumentFields;
import es.caib.portafib.model.fields.UsuariAplicacioFields;
import es.caib.portafib.model.fields.UsuariAplicacioConfiguracioFields;
import es.caib.portafib.model.fields.UsuariEntitatFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class PeticioDeFirmaValidator<T> implements PeticioDeFirmaFields {

  protected final Logger log = Logger.getLogger(getClass());


  public PeticioDeFirmaValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<T> __vr, T __target__, boolean __isNou__
    ,es.caib.portafib.model.dao.ICustodiaInfoManager __custodiaInfoManager
    ,es.caib.portafib.model.dao.IFluxDeFirmesManager __fluxDeFirmesManager
    ,es.caib.portafib.model.dao.IIdiomaManager __idiomaManager
    ,es.caib.portafib.model.dao.IPeticioDeFirmaManager __peticioDeFirmaManager
    ,es.caib.portafib.model.dao.ITipusDocumentManager __tipusDocumentManager
    ,es.caib.portafib.model.dao.IUsuariAplicacioManager __usuariAplicacioManager
    ,es.caib.portafib.model.dao.IUsuariAplicacioConfiguracioManager __usuariAplicacioConfiguracioManager
    ,es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,TITOL, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TITOL)));

    __vr.rejectIfEmptyOrWhitespace(__target__,MOTIU, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(MOTIU)));

    __vr.rejectIfEmptyOrWhitespace(__target__,TIPUSDOCUMENTID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TIPUSDOCUMENTID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,POSICIOTAULAFIRMESID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(POSICIOTAULAFIRMESID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,DATACADUCITAT, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DATACADUCITAT)));

    __vr.rejectIfEmptyOrWhitespace(__target__,TIPUSOPERACIOFIRMA, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TIPUSOPERACIOFIRMA)));

    __vr.rejectIfEmptyOrWhitespace(__target__,TIPUSFIRMAID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TIPUSFIRMAID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,ALGORISMEDEFIRMAID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ALGORISMEDEFIRMAID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,MODEDEFIRMA, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(MODEDEFIRMA)));

    __vr.rejectIfEmptyOrWhitespace(__target__,TIPUSESTATPETICIODEFIRMAID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TIPUSESTATPETICIODEFIRMAID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,IDIOMAID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(IDIOMAID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,PRIORITATID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PRIORITATID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,FLUXDEFIRMESID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(FLUXDEFIRMESID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,SOLICITANTUSUARIAPLICACIOID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(SOLICITANTUSUARIAPLICACIOID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,REMITENTNOM, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(REMITENTNOM)));

    __vr.rejectIfEmptyOrWhitespace(__target__,AVISWEB, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(AVISWEB)));

    __vr.rejectIfEmptyOrWhitespace(__target__,SEGELLATDETEMPS, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(SEGELLATDETEMPS)));

    __vr.rejectIfEmptyOrWhitespace(__target__,ORIGENPETICIODEFIRMA, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ORIGENPETICIODEFIRMA)));

    // Check size
    if (__vr.getFieldErrorCount(TITOL) == 0) {
      java.lang.String __titol = (java.lang.String)__vr.getFieldValue(__target__,TITOL);
      if (__titol!= null && __titol.length() > 255) {
        __vr.rejectValue(TITOL, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TITOL)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }
    
    if (__vr.getFieldErrorCount(DESCRIPCIO) == 0) {
      java.lang.String __descripcio = (java.lang.String)__vr.getFieldValue(__target__,DESCRIPCIO);
      if (__descripcio!= null && __descripcio.length() > 255) {
        __vr.rejectValue(DESCRIPCIO, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DESCRIPCIO)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }
    
    if (__vr.getFieldErrorCount(MOTIU) == 0) {
      java.lang.String __motiu = (java.lang.String)__vr.getFieldValue(__target__,MOTIU);
      if (__motiu!= null && __motiu.length() > 255) {
        __vr.rejectValue(MOTIU, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(MOTIU)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }
    
    if (__vr.getFieldErrorCount(DESCRIPCIOTIPUSDOCUMENT) == 0) {
      java.lang.String __descripciotipusdocument = (java.lang.String)__vr.getFieldValue(__target__,DESCRIPCIOTIPUSDOCUMENT);
      if (__descripciotipusdocument!= null && __descripciotipusdocument.length() > 255) {
        __vr.rejectValue(DESCRIPCIOTIPUSDOCUMENT, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DESCRIPCIOTIPUSDOCUMENT)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }
    
    if (__vr.getFieldErrorCount(MOTIUDEREBUIG) == 0) {
      java.lang.String __motiuderebuig = (java.lang.String)__vr.getFieldValue(__target__,MOTIUDEREBUIG);
      if (__motiuderebuig!= null && __motiuderebuig.length() > 255) {
        __vr.rejectValue(MOTIUDEREBUIG, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(MOTIUDEREBUIG)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }
    
    if (__vr.getFieldErrorCount(IDIOMAID) == 0) {
      java.lang.String __idiomaid = (java.lang.String)__vr.getFieldValue(__target__,IDIOMAID);
      if (__idiomaid!= null && __idiomaid.length() > 5) {
        __vr.rejectValue(IDIOMAID, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(IDIOMAID)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(5)));
      }
    }
    
    if (__vr.getFieldErrorCount(SOLICITANTUSUARIAPLICACIOID) == 0) {
      java.lang.String __solicitantusuariaplicacioid = (java.lang.String)__vr.getFieldValue(__target__,SOLICITANTUSUARIAPLICACIOID);
      if (__solicitantusuariaplicacioid!= null && __solicitantusuariaplicacioid.length() > 101) {
        __vr.rejectValue(SOLICITANTUSUARIAPLICACIOID, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(SOLICITANTUSUARIAPLICACIOID)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(101)));
      }
    }
    
    if (__vr.getFieldErrorCount(REMITENTNOM) == 0) {
      java.lang.String __remitentnom = (java.lang.String)__vr.getFieldValue(__target__,REMITENTNOM);
      if (__remitentnom!= null && __remitentnom.length() > 100) {
        __vr.rejectValue(REMITENTNOM, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(REMITENTNOM)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(100)));
      }
    }
    
    if (__vr.getFieldErrorCount(REMITENTDESCRIPCIO) == 0) {
      java.lang.String __remitentdescripcio = (java.lang.String)__vr.getFieldValue(__target__,REMITENTDESCRIPCIO);
      if (__remitentdescripcio!= null && __remitentdescripcio.length() > 500) {
        __vr.rejectValue(REMITENTDESCRIPCIO, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(REMITENTDESCRIPCIO)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(500)));
      }
    }
    
    if (__vr.getFieldErrorCount(EXPEDIENTCODI) == 0) {
      java.lang.String __expedientcodi = (java.lang.String)__vr.getFieldValue(__target__,EXPEDIENTCODI);
      if (__expedientcodi!= null && __expedientcodi.length() > 255) {
        __vr.rejectValue(EXPEDIENTCODI, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(EXPEDIENTCODI)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }
    
    if (__vr.getFieldErrorCount(EXPEDIENTNOM) == 0) {
      java.lang.String __expedientnom = (java.lang.String)__vr.getFieldValue(__target__,EXPEDIENTNOM);
      if (__expedientnom!= null && __expedientnom.length() > 255) {
        __vr.rejectValue(EXPEDIENTNOM, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(EXPEDIENTNOM)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }
    
    if (__vr.getFieldErrorCount(EXPEDIENTURL) == 0) {
      java.lang.String __expedienturl = (java.lang.String)__vr.getFieldValue(__target__,EXPEDIENTURL);
      if (__expedienturl!= null && __expedienturl.length() > 255) {
        __vr.rejectValue(EXPEDIENTURL, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(EXPEDIENTURL)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }
    
    if (__vr.getFieldErrorCount(PROCEDIMENTCODI) == 0) {
      java.lang.String __procedimentcodi = (java.lang.String)__vr.getFieldValue(__target__,PROCEDIMENTCODI);
      if (__procedimentcodi!= null && __procedimentcodi.length() > 255) {
        __vr.rejectValue(PROCEDIMENTCODI, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PROCEDIMENTCODI)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }
    
    if (__vr.getFieldErrorCount(PROCEDIMENTNOM) == 0) {
      java.lang.String __procedimentnom = (java.lang.String)__vr.getFieldValue(__target__,PROCEDIMENTNOM);
      if (__procedimentnom!= null && __procedimentnom.length() > 255) {
        __vr.rejectValue(PROCEDIMENTNOM, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PROCEDIMENTNOM)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }
    
    if (__vr.getFieldErrorCount(INFORMACIOADDICIONAL) == 0) {
      java.lang.String __informacioaddicional = (java.lang.String)__vr.getFieldValue(__target__,INFORMACIOADDICIONAL);
      if (__informacioaddicional!= null && __informacioaddicional.length() > 500) {
        __vr.rejectValue(INFORMACIOADDICIONAL, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(INFORMACIOADDICIONAL)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(500)));
      }
    }
    
    if (__vr.getFieldErrorCount(SOLICITANTUSUARIENTITAT1ID) == 0) {
      java.lang.String __solicitantusuarientitat1id = (java.lang.String)__vr.getFieldValue(__target__,SOLICITANTUSUARIENTITAT1ID);
      if (__solicitantusuarientitat1id!= null && __solicitantusuarientitat1id.length() > 101) {
        __vr.rejectValue(SOLICITANTUSUARIENTITAT1ID, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(SOLICITANTUSUARIENTITAT1ID)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(101)));
      }
    }
    
    if (__vr.getFieldErrorCount(SOLICITANTUSUARIENTITAT2ID) == 0) {
      java.lang.String __solicitantusuarientitat2id = (java.lang.String)__vr.getFieldValue(__target__,SOLICITANTUSUARIENTITAT2ID);
      if (__solicitantusuarientitat2id!= null && __solicitantusuarientitat2id.length() > 101) {
        __vr.rejectValue(SOLICITANTUSUARIENTITAT2ID, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(SOLICITANTUSUARIENTITAT2ID)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(101)));
      }
    }
    
    if (__vr.getFieldErrorCount(SOLICITANTUSUARIENTITAT3ID) == 0) {
      java.lang.String __solicitantusuarientitat3id = (java.lang.String)__vr.getFieldValue(__target__,SOLICITANTUSUARIENTITAT3ID);
      if (__solicitantusuarientitat3id!= null && __solicitantusuarientitat3id.length() > 101) {
        __vr.rejectValue(SOLICITANTUSUARIENTITAT3ID, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(SOLICITANTUSUARIENTITAT3ID)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(101)));
      }
    }
    
    if (__isNou__) { // Creació
      // ================ CREATION
      // Fitxers 
      // ====== Check Unique MULTIPLES - NOU =======

      // Check Unique - no PK
      if (__vr.getFieldErrorCount(FLUXDEFIRMESID) == 0) {
        java.lang.Long __fluxdefirmesid = (java.lang.Long)__vr.getFieldValue(__target__,FLUXDEFIRMESID);
        Long __count_ = null;
        try { __count_ = __peticioDeFirmaManager.count(org.fundaciobit.genapp.common.query.Where.AND(FLUXDEFIRMESID.equal(__fluxdefirmesid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(FLUXDEFIRMESID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__fluxdefirmesid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(FLUXDEFIRMESID)));
        }
      }

      // Check Unique - PK no AutoIncrement amb UNA SOLA PK 
    } else {
      // ================ UPDATE

      // ====== Check Unique MULTIPLES - EDIT  =======

      // Check Unique - no PK
      if (__vr.getFieldErrorCount(FLUXDEFIRMESID) == 0 && __vr.getFieldErrorCount(PETICIODEFIRMAID) == 0) {
        java.lang.Long __fluxdefirmesid = (java.lang.Long)__vr.getFieldValue(__target__,FLUXDEFIRMESID);
        java.lang.Long __peticiodefirmaid = (java.lang.Long)__vr.getFieldValue(__target__,PETICIODEFIRMAID);
        Long __count_ = null;
        try { __count_ = __peticioDeFirmaManager.count(org.fundaciobit.genapp.common.query.Where.AND(FLUXDEFIRMESID.equal(__fluxdefirmesid), PETICIODEFIRMAID.notEqual(__peticiodefirmaid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(FLUXDEFIRMESID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__fluxdefirmesid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(FLUXDEFIRMESID)));
        }
      }

    }

    // Fields with References to Other tables 
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

    if (__vr.getFieldErrorCount(IDIOMAID) == 0) {
      java.lang.String __idiomaid = (java.lang.String)__vr.getFieldValue(__target__,IDIOMAID);
      Long __count_ = null;
      try { __count_ = __idiomaManager.count(IdiomaFields.IDIOMAID.equal(__idiomaid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(IDIOMAID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("idioma.idioma"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("idioma.idiomaID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__idiomaid)));
      }
    }

    if (__vr.getFieldErrorCount(FLUXDEFIRMESID) == 0) {
      java.lang.Long __fluxdefirmesid = (java.lang.Long)__vr.getFieldValue(__target__,FLUXDEFIRMESID);
      Long __count_ = null;
      try { __count_ = __fluxDeFirmesManager.count(FluxDeFirmesFields.FLUXDEFIRMESID.equal(__fluxdefirmesid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(FLUXDEFIRMESID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("fluxDeFirmes.fluxDeFirmes"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("fluxDeFirmes.fluxDeFirmesID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__fluxdefirmesid)));
      }
    }

    if (__vr.getFieldErrorCount(SOLICITANTUSUARIAPLICACIOID) == 0) {
      java.lang.String __solicitantusuariaplicacioid = (java.lang.String)__vr.getFieldValue(__target__,SOLICITANTUSUARIAPLICACIOID);
      Long __count_ = null;
      try { __count_ = __usuariAplicacioManager.count(UsuariAplicacioFields.USUARIAPLICACIOID.equal(__solicitantusuariaplicacioid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(SOLICITANTUSUARIAPLICACIOID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuariAplicacio.usuariAplicacio"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuariAplicacio.usuariAplicacioID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__solicitantusuariaplicacioid)));
      }
    }

    if (__vr.getFieldErrorCount(CUSTODIAINFOID) == 0) {
      java.lang.Long __custodiainfoid = (java.lang.Long)__vr.getFieldValue(__target__,CUSTODIAINFOID);
      if (__custodiainfoid != null ) {
        Long __count_ = null;
        try { __count_ = __custodiaInfoManager.count(CustodiaInfoFields.CUSTODIAINFOID.equal(__custodiainfoid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ == 0) {        
          __vr.rejectValue(CUSTODIAINFOID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("custodiaInfo.custodiaInfo"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("custodiaInfo.custodiaInfoID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__custodiainfoid)));
        }
      }
    }

    if (__vr.getFieldErrorCount(SOLICITANTUSUARIENTITAT1ID) == 0) {
      java.lang.String __solicitantusuarientitat1id = (java.lang.String)__vr.getFieldValue(__target__,SOLICITANTUSUARIENTITAT1ID);
      if (__solicitantusuarientitat1id != null  && __solicitantusuarientitat1id.length() != 0) {
        Long __count_ = null;
        try { __count_ = __usuariEntitatManager.count(UsuariEntitatFields.USUARIENTITATID.equal(__solicitantusuarientitat1id)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ == 0) {        
          __vr.rejectValue(SOLICITANTUSUARIENTITAT1ID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuariEntitat.usuariEntitat"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuariEntitat.usuariEntitatID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__solicitantusuarientitat1id)));
        }
      }
    }

    if (__vr.getFieldErrorCount(SOLICITANTUSUARIENTITAT2ID) == 0) {
      java.lang.String __solicitantusuarientitat2id = (java.lang.String)__vr.getFieldValue(__target__,SOLICITANTUSUARIENTITAT2ID);
      if (__solicitantusuarientitat2id != null  && __solicitantusuarientitat2id.length() != 0) {
        Long __count_ = null;
        try { __count_ = __usuariEntitatManager.count(UsuariEntitatFields.USUARIENTITATID.equal(__solicitantusuarientitat2id)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ == 0) {        
          __vr.rejectValue(SOLICITANTUSUARIENTITAT2ID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuariEntitat.usuariEntitat"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuariEntitat.usuariEntitatID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__solicitantusuarientitat2id)));
        }
      }
    }

    if (__vr.getFieldErrorCount(SOLICITANTUSUARIENTITAT3ID) == 0) {
      java.lang.String __solicitantusuarientitat3id = (java.lang.String)__vr.getFieldValue(__target__,SOLICITANTUSUARIENTITAT3ID);
      if (__solicitantusuarientitat3id != null  && __solicitantusuarientitat3id.length() != 0) {
        Long __count_ = null;
        try { __count_ = __usuariEntitatManager.count(UsuariEntitatFields.USUARIENTITATID.equal(__solicitantusuarientitat3id)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ == 0) {        
          __vr.rejectValue(SOLICITANTUSUARIENTITAT3ID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuariEntitat.usuariEntitat"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuariEntitat.usuariEntitatID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__solicitantusuarientitat3id)));
        }
      }
    }

    if (__vr.getFieldErrorCount(CONFIGURACIODEFIRMAID) == 0) {
      java.lang.Long __configuraciodefirmaid = (java.lang.Long)__vr.getFieldValue(__target__,CONFIGURACIODEFIRMAID);
      if (__configuraciodefirmaid != null ) {
        Long __count_ = null;
        try { __count_ = __usuariAplicacioConfiguracioManager.count(UsuariAplicacioConfiguracioFields.USUARIAPLICACIOCONFIGID.equal(__configuraciodefirmaid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ == 0) {        
          __vr.rejectValue(CONFIGURACIODEFIRMAID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuariAplicacioConfiguracio.usuariAplicacioConfiguracio"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuariAplicacioConfiguracio.usuariAplicacioConfigID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__configuraciodefirmaid)));
        }
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}