package es.caib.portafib.jpa.validator;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import es.caib.portafib.model.fields.UsuariAplicacioConfiguracioFields;
import es.caib.portafib.model.fields.CustodiaInfoFields;
import es.caib.portafib.model.fields.EntitatFields;
import es.caib.portafib.model.fields.PluginFields;
import es.caib.portafib.model.fields.TraduccioFields;

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
    ,es.caib.portafib.model.dao.ICustodiaInfoManager __custodiaInfoManager
    ,es.caib.portafib.model.dao.IEntitatManager __entitatManager
    ,es.caib.portafib.model.dao.IPluginManager __pluginManager
    ,es.caib.portafib.model.dao.ITraduccioManager __traduccioManager
    ,es.caib.portafib.model.dao.IUsuariAplicacioConfiguracioManager __usuariAplicacioConfiguracioManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,NOM, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOM)));

    __vr.rejectIfEmptyOrWhitespace(__target__,ENTITATID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ENTITATID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,USENFIRMAAPISIMPLESERVIDOR, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USENFIRMAAPISIMPLESERVIDOR)));

    __vr.rejectIfEmptyOrWhitespace(__target__,USENFIRMAAPISIMPLEWEB, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USENFIRMAAPISIMPLEWEB)));

    __vr.rejectIfEmptyOrWhitespace(__target__,USENFIRMAWEB, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USENFIRMAWEB)));

    __vr.rejectIfEmptyOrWhitespace(__target__,USENFIRMAWS2, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USENFIRMAWS2)));

    __vr.rejectIfEmptyOrWhitespace(__target__,USENFIRMAPASSARELASERVIDOR, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USENFIRMAPASSARELASERVIDOR)));

    __vr.rejectIfEmptyOrWhitespace(__target__,USENFIRMAPASSARELAWEB, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USENFIRMAPASSARELAWEB)));

    __vr.rejectIfEmptyOrWhitespace(__target__,USPOLITICADEFIRMA, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USPOLITICADEFIRMA)));

    __vr.rejectIfEmptyOrWhitespace(__target__,TIPUSOPERACIOFIRMA, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TIPUSOPERACIOFIRMA)));

    __vr.rejectIfEmptyOrWhitespace(__target__,TIPUSFIRMAID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TIPUSFIRMAID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,MODEDEFIRMA, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(MODEDEFIRMA)));

    __vr.rejectIfEmptyOrWhitespace(__target__,POLITICACUSTODIA, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(POLITICACUSTODIA)));

    __vr.rejectIfEmptyOrWhitespace(__target__,POLITICATAULAFIRMES, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(POLITICATAULAFIRMES)));

    __vr.rejectIfEmptyOrWhitespace(__target__,POSICIOTAULAFIRMESID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(POSICIOTAULAFIRMESID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,POLITICASEGELLATDETEMPS, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(POLITICASEGELLATDETEMPS)));

    // Check size
    if (__vr.getFieldErrorCount(NOM) == 0) {
      java.lang.String __nom = (java.lang.String)__vr.getFieldValue(__target__,NOM);
      if (__nom!= null && __nom.length() > 255) {
        __vr.rejectValue(NOM, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOM)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }
    
    if (__vr.getFieldErrorCount(ENTITATID) == 0) {
      java.lang.String __entitatid = (java.lang.String)__vr.getFieldValue(__target__,ENTITATID);
      if (__entitatid!= null && __entitatid.length() > 50) {
        __vr.rejectValue(ENTITATID, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ENTITATID)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(50)));
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
    
    if (__vr.getFieldErrorCount(PROPIETATSTAULAFIRMES) == 0) {
      java.lang.String __propietatstaulafirmes = (java.lang.String)__vr.getFieldValue(__target__,PROPIETATSTAULAFIRMES);
      if (__propietatstaulafirmes!= null && __propietatstaulafirmes.length() > 2147483647) {
        __vr.rejectValue(PROPIETATSTAULAFIRMES, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PROPIETATSTAULAFIRMES)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(2147483647)));
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
      // Check Unique - PK no AutoIncrement amb UNA SOLA PK 
    } else {
      // ================ UPDATE

      // ====== Check Unique MULTIPLES - EDIT  =======

      // Check Unique - no PK
    }

    // Fields with References to Other tables 
    if (__vr.getFieldErrorCount(ENTITATID) == 0) {
      java.lang.String __entitatid = (java.lang.String)__vr.getFieldValue(__target__,ENTITATID);
      Long __count_ = null;
      try { __count_ = __entitatManager.count(EntitatFields.ENTITATID.equal(__entitatid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(ENTITATID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("entitat.entitat"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("entitat.entitatID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__entitatid)));
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