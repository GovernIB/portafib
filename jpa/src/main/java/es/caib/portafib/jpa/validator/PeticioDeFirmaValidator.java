package es.caib.portafib.jpa.validator;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;
import es.caib.portafib.model.fields.AlgorismeDeFirmaFields;
import es.caib.portafib.model.fields.CustodiaInfoFields;
import es.caib.portafib.model.fields.FluxDeFirmesFields;
import es.caib.portafib.model.fields.IdiomaFields;
import es.caib.portafib.model.fields.PosicioTaulaFirmesFields;
import es.caib.portafib.model.fields.PrioritatFields;
import es.caib.portafib.model.fields.TipusDocumentFields;
import es.caib.portafib.model.fields.TipusEstatPeticioDeFirmaFields;
import es.caib.portafib.model.fields.TipusFirmaFields;
import es.caib.portafib.model.fields.UsuariAplicacioFields;
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
    ,es.caib.portafib.model.dao.IAlgorismeDeFirmaManager __algorismeDeFirmaManager
    ,es.caib.portafib.model.dao.ICustodiaInfoManager __custodiaInfoManager
    ,es.caib.portafib.model.dao.IFluxDeFirmesManager __fluxDeFirmesManager
    ,es.caib.portafib.model.dao.IIdiomaManager __idiomaManager
    ,es.caib.portafib.model.dao.IPeticioDeFirmaManager __peticioDeFirmaManager
    ,es.caib.portafib.model.dao.IPosicioTaulaFirmesManager __posicioTaulaFirmesManager
    ,es.caib.portafib.model.dao.IPrioritatManager __prioritatManager
    ,es.caib.portafib.model.dao.ITipusDocumentManager __tipusDocumentManager
    ,es.caib.portafib.model.dao.ITipusEstatPeticioDeFirmaManager __tipusEstatPeticioDeFirmaManager
    ,es.caib.portafib.model.dao.ITipusFirmaManager __tipusFirmaManager
    ,es.caib.portafib.model.dao.IUsuariAplicacioManager __usuariAplicacioManager
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

    __vr.rejectIfEmptyOrWhitespace(__target__,USUARIAPLICACIOID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIAPLICACIOID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,REMITENTNOM, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(REMITENTNOM)));

    __vr.rejectIfEmptyOrWhitespace(__target__,AVISWEB, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(AVISWEB)));

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
    
    if (__vr.getFieldErrorCount(USUARIAPLICACIOID) == 0) {
      java.lang.String __usuariaplicacioid = (java.lang.String)__vr.getFieldValue(__target__,USUARIAPLICACIOID);
      if (__usuariaplicacioid!= null && __usuariaplicacioid.length() > 101) {
        __vr.rejectValue(USUARIAPLICACIOID, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIAPLICACIOID)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(101)));
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
    
    if (__vr.getFieldErrorCount(INFORMACIOADICIONAL) == 0) {
      java.lang.String __informacioadicional = (java.lang.String)__vr.getFieldValue(__target__,INFORMACIOADICIONAL);
      if (__informacioadicional!= null && __informacioadicional.length() > 500) {
        __vr.rejectValue(INFORMACIOADICIONAL, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(INFORMACIOADICIONAL)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(500)));
      }
    }
    
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
    if (__vr.getFieldErrorCount(FITXERAFIRMARID) == 0) { // FITXER
      Object __value = __vr.getFieldValue(__target__,FITXERAFIRMARID);
      if (__value == null || String.valueOf(__value).trim().length() == 0 || String.valueOf(__value).trim().equals("0") ) {
          __vr.rejectValue(FITXERAFIRMARID, "genapp.validation.required",
             new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(FITXERAFIRMARID)));
      }
    }

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

    if (__vr.getFieldErrorCount(POSICIOTAULAFIRMESID) == 0) {
      java.lang.Integer __posiciotaulafirmesid = (java.lang.Integer)__vr.getFieldValue(__target__,POSICIOTAULAFIRMESID);
      Long __count_ = null;
      try { __count_ = __posicioTaulaFirmesManager.count(PosicioTaulaFirmesFields.POSICIOTAULAFIRMESID.equal(__posiciotaulafirmesid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(POSICIOTAULAFIRMESID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("posicioTaulaFirmes.posicioTaulaFirmes"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("posicioTaulaFirmes.posicioTaulaFirmesID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__posiciotaulafirmesid)));
      }
    }

    if (__vr.getFieldErrorCount(TIPUSFIRMAID) == 0) {
      java.lang.Integer __tipusfirmaid = (java.lang.Integer)__vr.getFieldValue(__target__,TIPUSFIRMAID);
      Long __count_ = null;
      try { __count_ = __tipusFirmaManager.count(TipusFirmaFields.TIPUSFIRMAID.equal(__tipusfirmaid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(TIPUSFIRMAID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("tipusFirma.tipusFirma"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("tipusFirma.tipusFirmaID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__tipusfirmaid)));
      }
    }

    if (__vr.getFieldErrorCount(ALGORISMEDEFIRMAID) == 0) {
      java.lang.Integer __algorismedefirmaid = (java.lang.Integer)__vr.getFieldValue(__target__,ALGORISMEDEFIRMAID);
      Long __count_ = null;
      try { __count_ = __algorismeDeFirmaManager.count(AlgorismeDeFirmaFields.ALGORISMEDEFIRMAID.equal(__algorismedefirmaid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(ALGORISMEDEFIRMAID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("algorismeDeFirma.algorismeDeFirma"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("algorismeDeFirma.algorismeDeFirmaID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__algorismedefirmaid)));
      }
    }

    if (__vr.getFieldErrorCount(TIPUSESTATPETICIODEFIRMAID) == 0) {
      java.lang.Integer __tipusestatpeticiodefirmaid = (java.lang.Integer)__vr.getFieldValue(__target__,TIPUSESTATPETICIODEFIRMAID);
      Long __count_ = null;
      try { __count_ = __tipusEstatPeticioDeFirmaManager.count(TipusEstatPeticioDeFirmaFields.TIPUSESTATPETICIODEFIRMAID.equal(__tipusestatpeticiodefirmaid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(TIPUSESTATPETICIODEFIRMAID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("tipusEstatPeticioDeFirma.tipusEstatPeticioDeFirma"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("tipusEstatPeticioDeFirma.tipusEstatPeticioDeFirmaID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__tipusestatpeticiodefirmaid)));
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

    if (__vr.getFieldErrorCount(PRIORITATID) == 0) {
      java.lang.Integer __prioritatid = (java.lang.Integer)__vr.getFieldValue(__target__,PRIORITATID);
      Long __count_ = null;
      try { __count_ = __prioritatManager.count(PrioritatFields.PRIORITATID.equal(__prioritatid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(PRIORITATID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("prioritat.prioritat"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("prioritat.prioritatID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__prioritatid)));
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

    if (__vr.getFieldErrorCount(USUARIAPLICACIOID) == 0) {
      java.lang.String __usuariaplicacioid = (java.lang.String)__vr.getFieldValue(__target__,USUARIAPLICACIOID);
      Long __count_ = null;
      try { __count_ = __usuariAplicacioManager.count(UsuariAplicacioFields.USUARIAPLICACIOID.equal(__usuariaplicacioid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(USUARIAPLICACIOID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuariAplicacio.usuariAplicacio"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuariAplicacio.usuariAplicacioID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__usuariaplicacioid)));
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

    if (__vr.getFieldErrorCount(USUARIENTITATID) == 0) {
      java.lang.String __usuarientitatid = (java.lang.String)__vr.getFieldValue(__target__,USUARIENTITATID);
      if (__usuarientitatid != null  && __usuarientitatid.length() != 0) {
        Long __count_ = null;
        try { __count_ = __usuariEntitatManager.count(UsuariEntitatFields.USUARIENTITATID.equal(__usuarientitatid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ == 0) {        
          __vr.rejectValue(USUARIENTITATID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuariEntitat.usuariEntitat"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuariEntitat.usuariEntitatID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__usuarientitatid)));
        }
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}