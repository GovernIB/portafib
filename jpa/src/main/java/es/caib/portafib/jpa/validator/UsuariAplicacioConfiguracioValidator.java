package es.caib.portafib.jpa.validator;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import es.caib.portafib.model.fields.UsuariAplicacioConfiguracioFields;
import es.caib.portafib.model.fields.AlgorismeDeFirmaFields;
import es.caib.portafib.model.fields.CustodiaInfoFields;
import es.caib.portafib.model.fields.PluginFields;
import es.caib.portafib.model.fields.PosicioTaulaFirmesFields;
import es.caib.portafib.model.fields.TipusFirmaFields;
import es.caib.portafib.model.fields.TraduccioFields;
import es.caib.portafib.model.fields.UsuariAplicacioFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class UsuariAplicacioConfiguracioValidator<T> implements UsuariAplicacioConfiguracioFields {

  protected final Logger log = Logger.getLogger(getClass());


  public UsuariAplicacioConfiguracioValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<T> __vr, T __target__, boolean __isNou__
    ,es.caib.portafib.model.dao.IAlgorismeDeFirmaManager __algorismeDeFirmaManager
    ,es.caib.portafib.model.dao.ICustodiaInfoManager __custodiaInfoManager
    ,es.caib.portafib.model.dao.IPluginManager __pluginManager
    ,es.caib.portafib.model.dao.IPosicioTaulaFirmesManager __posicioTaulaFirmesManager
    ,es.caib.portafib.model.dao.ITipusFirmaManager __tipusFirmaManager
    ,es.caib.portafib.model.dao.ITraduccioManager __traduccioManager
    ,es.caib.portafib.model.dao.IUsuariAplicacioManager __usuariAplicacioManager
    ,es.caib.portafib.model.dao.IUsuariAplicacioConfiguracioManager __usuariAplicacioConfiguracioManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,USUARIAPLICACIOID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIAPLICACIOID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,USPOLITICADETIRMA, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USPOLITICADETIRMA)));

    __vr.rejectIfEmptyOrWhitespace(__target__,TIPUSOPERACIOFIRMA, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TIPUSOPERACIOFIRMA)));

    __vr.rejectIfEmptyOrWhitespace(__target__,TIPUSFIRMAID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TIPUSFIRMAID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,MODEDEFIRMA, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(MODEDEFIRMA)));

    __vr.rejectIfEmptyOrWhitespace(__target__,POSICIOTAULAFIRMESID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(POSICIOTAULAFIRMESID)));

    // Check size
    if (__vr.getFieldErrorCount(USUARIAPLICACIOID) == 0) {
      java.lang.String __usuariaplicacioid = (java.lang.String)__vr.getFieldValue(__target__,USUARIAPLICACIOID);
      if (__usuariaplicacioid!= null && __usuariaplicacioid.length() > 101) {
        __vr.rejectValue(USUARIAPLICACIOID, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIAPLICACIOID)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(101)));
      }
    }
    
    if (__vr.getFieldErrorCount(POLICYIDENTIFIER) == 0) {
      java.lang.String __policyidentifier = (java.lang.String)__vr.getFieldValue(__target__,POLICYIDENTIFIER);
      if (__policyidentifier!= null && __policyidentifier.length() > 100) {
        __vr.rejectValue(POLICYIDENTIFIER, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(POLICYIDENTIFIER)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(100)));
      }
    }
    
    if (__vr.getFieldErrorCount(POLICYIDENTIFIERHASH) == 0) {
      java.lang.String __policyidentifierhash = (java.lang.String)__vr.getFieldValue(__target__,POLICYIDENTIFIERHASH);
      if (__policyidentifierhash!= null && __policyidentifierhash.length() > 256) {
        __vr.rejectValue(POLICYIDENTIFIERHASH, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(POLICYIDENTIFIERHASH)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(256)));
      }
    }
    
    if (__vr.getFieldErrorCount(POLICYIDENTIFIERHASHALGORITHM) == 0) {
      java.lang.String __policyidentifierhashalgorithm = (java.lang.String)__vr.getFieldValue(__target__,POLICYIDENTIFIERHASHALGORITHM);
      if (__policyidentifierhashalgorithm!= null && __policyidentifierhashalgorithm.length() > 50) {
        __vr.rejectValue(POLICYIDENTIFIERHASHALGORITHM, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(POLICYIDENTIFIERHASHALGORITHM)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(50)));
      }
    }
    
    if (__vr.getFieldErrorCount(POLICYURLDOCUMENT) == 0) {
      java.lang.String __policyurldocument = (java.lang.String)__vr.getFieldValue(__target__,POLICYURLDOCUMENT);
      if (__policyurldocument!= null && __policyurldocument.length() > 255) {
        __vr.rejectValue(POLICYURLDOCUMENT, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(POLICYURLDOCUMENT)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }
    
    if (__vr.getFieldErrorCount(FILTRECERTIFICATS) == 0) {
      java.lang.String __filtrecertificats = (java.lang.String)__vr.getFieldValue(__target__,FILTRECERTIFICATS);
      if (__filtrecertificats!= null && __filtrecertificats.length() > 2147483647) {
        __vr.rejectValue(FILTRECERTIFICATS, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(FILTRECERTIFICATS)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(2147483647)));
      }
    }
    
    if (__vr.getFieldErrorCount(HTMLPERLLISTARPLUGINSFIRMAWEB) == 0) {
      java.lang.String __htmlperllistarpluginsfirmaweb = (java.lang.String)__vr.getFieldValue(__target__,HTMLPERLLISTARPLUGINSFIRMAWEB);
      if (__htmlperllistarpluginsfirmaweb!= null && __htmlperllistarpluginsfirmaweb.length() > 2147483647) {
        __vr.rejectValue(HTMLPERLLISTARPLUGINSFIRMAWEB, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(HTMLPERLLISTARPLUGINSFIRMAWEB)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(2147483647)));
      }
    }
    
    if (__isNou__) { // Creació
      // ================ CREATION
      // Fitxers 
      // ====== Check Unique MULTIPLES - NOU =======

      // Check Unique - no PK
      if (__vr.getFieldErrorCount(USUARIAPLICACIOID) == 0) {
        java.lang.String __usuariaplicacioid = (java.lang.String)__vr.getFieldValue(__target__,USUARIAPLICACIOID);
        Long __count_ = null;
        try { __count_ = __usuariAplicacioConfiguracioManager.count(org.fundaciobit.genapp.common.query.Where.AND(USUARIAPLICACIOID.equal(__usuariaplicacioid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(USUARIAPLICACIOID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__usuariaplicacioid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIAPLICACIOID)));
        }
      }

      // Check Unique - PK no AutoIncrement amb UNA SOLA PK 
    } else {
      // ================ UPDATE

      // ====== Check Unique MULTIPLES - EDIT  =======

      // Check Unique - no PK
      if (__vr.getFieldErrorCount(USUARIAPLICACIOID) == 0 && __vr.getFieldErrorCount(USUARIAPLICACIOCONFIGID) == 0) {
        java.lang.String __usuariaplicacioid = (java.lang.String)__vr.getFieldValue(__target__,USUARIAPLICACIOID);
        java.lang.Long __usuariaplicacioconfigid = (java.lang.Long)__vr.getFieldValue(__target__,USUARIAPLICACIOCONFIGID);
        Long __count_ = null;
        try { __count_ = __usuariAplicacioConfiguracioManager.count(org.fundaciobit.genapp.common.query.Where.AND(USUARIAPLICACIOID.equal(__usuariaplicacioid), USUARIAPLICACIOCONFIGID.notEqual(__usuariaplicacioconfigid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(USUARIAPLICACIOID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__usuariaplicacioid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIAPLICACIOID)));
        }
      }

    }

    // Fields with References to Other tables 
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
      if (__algorismedefirmaid != null ) {
        Long __count_ = null;
        try { __count_ = __algorismeDeFirmaManager.count(AlgorismeDeFirmaFields.ALGORISMEDEFIRMAID.equal(__algorismedefirmaid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ == 0) {        
          __vr.rejectValue(ALGORISMEDEFIRMAID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("algorismeDeFirma.algorismeDeFirma"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("algorismeDeFirma.algorismeDeFirmaID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__algorismedefirmaid)));
        }
      }
    }

    if (__vr.getFieldErrorCount(MOTIUDELEGACIOID) == 0) {
      java.lang.Long __motiudelegacioid = (java.lang.Long)__vr.getFieldValue(__target__,MOTIUDELEGACIOID);
      if (__motiudelegacioid != null ) {
        Long __count_ = null;
        try { __count_ = __traduccioManager.count(TraduccioFields.TRADUCCIOID.equal(__motiudelegacioid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ == 0) {        
          __vr.rejectValue(MOTIUDELEGACIOID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("traduccio.traduccio"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("traduccio.traduccioID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__motiudelegacioid)));
        }
      }
    }

    if (__vr.getFieldErrorCount(FIRMATPERFORMATID) == 0) {
      java.lang.Long __firmatperformatid = (java.lang.Long)__vr.getFieldValue(__target__,FIRMATPERFORMATID);
      if (__firmatperformatid != null ) {
        Long __count_ = null;
        try { __count_ = __traduccioManager.count(TraduccioFields.TRADUCCIOID.equal(__firmatperformatid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ == 0) {        
          __vr.rejectValue(FIRMATPERFORMATID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("traduccio.traduccio"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("traduccio.traduccioID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__firmatperformatid)));
        }
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

    if (__vr.getFieldErrorCount(PLUGINSEGELLATID) == 0) {
      java.lang.Long __pluginsegellatid = (java.lang.Long)__vr.getFieldValue(__target__,PLUGINSEGELLATID);
      if (__pluginsegellatid != null ) {
        Long __count_ = null;
        try { __count_ = __pluginManager.count(PluginFields.PLUGINID.equal(__pluginsegellatid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ == 0) {        
          __vr.rejectValue(PLUGINSEGELLATID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("plugin.plugin"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("plugin.pluginID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__pluginsegellatid)));
        }
      }
    }

    if (__vr.getFieldErrorCount(PLUGINFIRMASERVIDORID) == 0) {
      java.lang.Long __pluginfirmaservidorid = (java.lang.Long)__vr.getFieldValue(__target__,PLUGINFIRMASERVIDORID);
      if (__pluginfirmaservidorid != null ) {
        Long __count_ = null;
        try { __count_ = __pluginManager.count(PluginFields.PLUGINID.equal(__pluginfirmaservidorid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ == 0) {        
          __vr.rejectValue(PLUGINFIRMASERVIDORID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("plugin.plugin"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("plugin.pluginID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__pluginfirmaservidorid)));
        }
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}