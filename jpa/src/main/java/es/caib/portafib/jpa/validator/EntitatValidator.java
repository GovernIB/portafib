package es.caib.portafib.jpa.validator;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import es.caib.portafib.model.fields.EntitatFields;
import es.caib.portafib.model.fields.CustodiaInfoFields;
import es.caib.portafib.model.fields.PluginFields;
import es.caib.portafib.model.fields.TraduccioFields;
import es.caib.portafib.model.fields.UsuariAplicacioFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class EntitatValidator<T> implements EntitatFields {

  protected final Logger log = Logger.getLogger(getClass());


  public EntitatValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<T> __vr, T __target__, boolean __isNou__
    ,es.caib.portafib.model.dao.ICustodiaInfoManager __custodiaInfoManager
    ,es.caib.portafib.model.dao.IEntitatManager __entitatManager
    ,es.caib.portafib.model.dao.IPluginManager __pluginManager
    ,es.caib.portafib.model.dao.ITraduccioManager __traduccioManager
    ,es.caib.portafib.model.dao.IUsuariAplicacioManager __usuariAplicacioManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,ENTITATID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ENTITATID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,NOM, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOM)));

    __vr.rejectIfEmptyOrWhitespace(__target__,ACTIVA, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ACTIVA)));

    __vr.rejectIfEmptyOrWhitespace(__target__,WEB, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(WEB)));

    __vr.rejectIfEmptyOrWhitespace(__target__,ADREZAHTML, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ADREZAHTML)));

    __vr.rejectIfEmptyOrWhitespace(__target__,FILTRECERTIFICATS, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(FILTRECERTIFICATS)));

    __vr.rejectIfEmptyOrWhitespace(__target__,USPOLITICADEFIRMA, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USPOLITICADEFIRMA)));

    __vr.rejectIfEmptyOrWhitespace(__target__,ALGORISMEDEFIRMAID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ALGORISMEDEFIRMAID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,POLITICACUSTODIA, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(POLITICACUSTODIA)));

    __vr.rejectIfEmptyOrWhitespace(__target__,POLITICATAULAFIRMES, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(POLITICATAULAFIRMES)));

    __vr.rejectIfEmptyOrWhitespace(__target__,POSICIOTAULAFIRMES, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(POSICIOTAULAFIRMES)));

    __vr.rejectIfEmptyOrWhitespace(__target__,POLITICASEGELLATDETEMPS, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(POLITICASEGELLATDETEMPS)));

    __vr.rejectIfEmptyOrWhitespace(__target__,VALIDARFIRMA, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(VALIDARFIRMA)));

    __vr.rejectIfEmptyOrWhitespace(__target__,COMPROVARNIFFIRMA, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(COMPROVARNIFFIRMA)));

    __vr.rejectIfEmptyOrWhitespace(__target__,CHECKCANVIATDOCFIRMAT, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CHECKCANVIATDOCFIRMAT)));

    // Check size
    if (__vr.getFieldErrorCount(ENTITATID) == 0) {
      java.lang.String __entitatid = (java.lang.String)__vr.getFieldValue(__target__,ENTITATID);
      if (__entitatid!= null && __entitatid.length() > 50) {
        __vr.rejectValue(ENTITATID, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ENTITATID)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(50)));
      }
    }
    
    //class java.lang.Class
    if (__vr.getFieldErrorCount(ENTITATID) == 0) {
      String val = String.valueOf(__vr.getFieldValue(__target__,ENTITATID));
      if (val != null && val.trim().length() != 0) {
        java.util.regex.Pattern p = java.util.regex.Pattern.compile("^[a-zA-Z0-9._-]{3,}$");
        if (!p.matcher(val).matches()) {
          __vr.rejectValue(ENTITATID, "genapp.validation.malformed",
             new org.fundaciobit.genapp.common.i18n.I18NArgumentString(val), new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ENTITATID)));
        }
      }
    }

    if (__vr.getFieldErrorCount(NOM) == 0) {
      java.lang.String __nom = (java.lang.String)__vr.getFieldValue(__target__,NOM);
      if (__nom!= null && __nom.length() > 50) {
        __vr.rejectValue(NOM, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOM)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(50)));
      }
    }
    
    if (__vr.getFieldErrorCount(DESCRIPCIO) == 0) {
      java.lang.String __descripcio = (java.lang.String)__vr.getFieldValue(__target__,DESCRIPCIO);
      if (__descripcio!= null && __descripcio.length() > 255) {
        __vr.rejectValue(DESCRIPCIO, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DESCRIPCIO)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }
    
    if (__vr.getFieldErrorCount(WEB) == 0) {
      java.lang.String __web = (java.lang.String)__vr.getFieldValue(__target__,WEB);
      if (__web!= null && __web.length() > 250) {
        __vr.rejectValue(WEB, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(WEB)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(250)));
      }
    }
    
    if (__vr.getFieldErrorCount(ADREZAHTML) == 0) {
      java.lang.String __adrezahtml = (java.lang.String)__vr.getFieldValue(__target__,ADREZAHTML);
      if (__adrezahtml!= null && __adrezahtml.length() > 2000) {
        __vr.rejectValue(ADREZAHTML, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ADREZAHTML)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(2000)));
      }
    }
    
    if (__vr.getFieldErrorCount(FILTRECERTIFICATS) == 0) {
      java.lang.String __filtrecertificats = (java.lang.String)__vr.getFieldValue(__target__,FILTRECERTIFICATS);
      if (__filtrecertificats!= null && __filtrecertificats.length() > 2147483647) {
        __vr.rejectValue(FILTRECERTIFICATS, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(FILTRECERTIFICATS)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(2147483647)));
      }
    }
    
    if (__vr.getFieldErrorCount(SUPORTTELEFON) == 0) {
      java.lang.String __suporttelefon = (java.lang.String)__vr.getFieldValue(__target__,SUPORTTELEFON);
      if (__suporttelefon!= null && __suporttelefon.length() > 50) {
        __vr.rejectValue(SUPORTTELEFON, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(SUPORTTELEFON)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(50)));
      }
    }
    
    if (__vr.getFieldErrorCount(SUPORTWEB) == 0) {
      java.lang.String __suportweb = (java.lang.String)__vr.getFieldValue(__target__,SUPORTWEB);
      if (__suportweb!= null && __suportweb.length() > 250) {
        __vr.rejectValue(SUPORTWEB, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(SUPORTWEB)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(250)));
      }
    }
    
    if (__vr.getFieldErrorCount(SUPORTEMAIL) == 0) {
      java.lang.String __suportemail = (java.lang.String)__vr.getFieldValue(__target__,SUPORTEMAIL);
      if (__suportemail!= null && __suportemail.length() > 100) {
        __vr.rejectValue(SUPORTEMAIL, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(SUPORTEMAIL)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(100)));
      }
    }
    
    //class java.lang.Class
    if (__vr.getFieldErrorCount(SUPORTEMAIL) == 0) {
      String val = String.valueOf(__vr.getFieldValue(__target__,SUPORTEMAIL));
      if (val != null && val.trim().length() != 0) {
        java.util.regex.Pattern p = java.util.regex.Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?");
        if (!p.matcher(val).matches()) {
          __vr.rejectValue(SUPORTEMAIL, "genapp.validation.malformed",
             new org.fundaciobit.genapp.common.i18n.I18NArgumentString(val), new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(SUPORTEMAIL)));
        }
      }
    }

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
      if (__policyidentifierhash!= null && __policyidentifierhash.length() > 2147483647) {
        __vr.rejectValue(POLICYIDENTIFIERHASH, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(POLICYIDENTIFIERHASH)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(2147483647)));
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
    
    if (__vr.getFieldErrorCount(PROPIETATSTAULAFIRMES) == 0) {
      java.lang.String __propietatstaulafirmes = (java.lang.String)__vr.getFieldValue(__target__,PROPIETATSTAULAFIRMES);
      if (__propietatstaulafirmes!= null && __propietatstaulafirmes.length() > 2147483647) {
        __vr.rejectValue(PROPIETATSTAULAFIRMES, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PROPIETATSTAULAFIRMES)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(2147483647)));
      }
    }
    
    if (__isNou__) { // Creació
      // ================ CREATION
      // Fitxers 
    if (__vr.getFieldErrorCount(FAVICONID) == 0) { // FITXER
      Object __value = __vr.getFieldValue(__target__,FAVICONID);
      if (__value == null || String.valueOf(__value).trim().length() == 0 || String.valueOf(__value).trim().equals("0") ) {
          __vr.rejectValue(FAVICONID, "genapp.validation.required",
             new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(FAVICONID)));
      }
    }

    if (__vr.getFieldErrorCount(LOGOWEBID) == 0) { // FITXER
      Object __value = __vr.getFieldValue(__target__,LOGOWEBID);
      if (__value == null || String.valueOf(__value).trim().length() == 0 || String.valueOf(__value).trim().equals("0") ) {
          __vr.rejectValue(LOGOWEBID, "genapp.validation.required",
             new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(LOGOWEBID)));
      }
    }

    if (__vr.getFieldErrorCount(LOGOWEBPEUID) == 0) { // FITXER
      Object __value = __vr.getFieldValue(__target__,LOGOWEBPEUID);
      if (__value == null || String.valueOf(__value).trim().length() == 0 || String.valueOf(__value).trim().equals("0") ) {
          __vr.rejectValue(LOGOWEBPEUID, "genapp.validation.required",
             new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(LOGOWEBPEUID)));
      }
    }

    if (__vr.getFieldErrorCount(LOGOSEGELLID) == 0) { // FITXER
      Object __value = __vr.getFieldValue(__target__,LOGOSEGELLID);
      if (__value == null || String.valueOf(__value).trim().length() == 0 || String.valueOf(__value).trim().equals("0") ) {
          __vr.rejectValue(LOGOSEGELLID, "genapp.validation.required",
             new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(LOGOSEGELLID)));
      }
    }

    if (__vr.getFieldErrorCount(PDFAUTORITZACIODELEGACIOID) == 0) { // FITXER
      Object __value = __vr.getFieldValue(__target__,PDFAUTORITZACIODELEGACIOID);
      if (__value == null || String.valueOf(__value).trim().length() == 0 || String.valueOf(__value).trim().equals("0") ) {
          __vr.rejectValue(PDFAUTORITZACIODELEGACIOID, "genapp.validation.required",
             new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PDFAUTORITZACIODELEGACIOID)));
      }
    }

      // ====== Check Unique MULTIPLES - NOU =======

      // Check Unique - no PK
      // Check Unique - PK no AutoIncrement amb UNA SOLA PK 
      if (__vr.getFieldErrorCount(ENTITATID) == 0) {
        java.lang.String __entitatid = (java.lang.String)__vr.getFieldValue(__target__,ENTITATID);
        Long __count_ = null;
        try { __count_ = __entitatManager.count(org.fundaciobit.genapp.common.query.Where.AND(ENTITATID.equal(__entitatid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(ENTITATID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__entitatid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ENTITATID)));
        }
      }

    } else {
      // ================ UPDATE

      // ====== Check Unique MULTIPLES - EDIT  =======

      // Check Unique - no PK
    }

    // Fields with References to Other tables 
    if (__vr.getFieldErrorCount(USUARIAPLICACIOID) == 0) {
      java.lang.String __usuariaplicacioid = (java.lang.String)__vr.getFieldValue(__target__,USUARIAPLICACIOID);
      if (__usuariaplicacioid != null  && __usuariaplicacioid.length() != 0) {
        Long __count_ = null;
        try { __count_ = __usuariAplicacioManager.count(UsuariAplicacioFields.USUARIAPLICACIOID.equal(__usuariaplicacioid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ == 0) {        
          __vr.rejectValue(USUARIAPLICACIOID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuariAplicacio.usuariAplicacio"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuariAplicacio.usuariAplicacioID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__usuariaplicacioid)));
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

    if (__vr.getFieldErrorCount(PLUGINSEGELLTEMPSID) == 0) {
      java.lang.Long __pluginsegelltempsid = (java.lang.Long)__vr.getFieldValue(__target__,PLUGINSEGELLTEMPSID);
      if (__pluginsegelltempsid != null ) {
        Long __count_ = null;
        try { __count_ = __pluginManager.count(PluginFields.PLUGINID.equal(__pluginsegelltempsid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ == 0) {        
          __vr.rejectValue(PLUGINSEGELLTEMPSID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("plugin.plugin"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("plugin.pluginID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__pluginsegelltempsid)));
        }
      }
    }

    if (__vr.getFieldErrorCount(PLUGINRUBRICAID) == 0) {
      java.lang.Long __pluginrubricaid = (java.lang.Long)__vr.getFieldValue(__target__,PLUGINRUBRICAID);
      if (__pluginrubricaid != null ) {
        Long __count_ = null;
        try { __count_ = __pluginManager.count(PluginFields.PLUGINID.equal(__pluginrubricaid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ == 0) {        
          __vr.rejectValue(PLUGINRUBRICAID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("plugin.plugin"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("plugin.pluginID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__pluginrubricaid)));
        }
      }
    }

    if (__vr.getFieldErrorCount(PLUGINVALIDAFIRMESID) == 0) {
      java.lang.Long __pluginvalidafirmesid = (java.lang.Long)__vr.getFieldValue(__target__,PLUGINVALIDAFIRMESID);
      if (__pluginvalidafirmesid != null ) {
        Long __count_ = null;
        try { __count_ = __pluginManager.count(PluginFields.PLUGINID.equal(__pluginvalidafirmesid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ == 0) {        
          __vr.rejectValue(PLUGINVALIDAFIRMESID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("plugin.plugin"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("plugin.pluginID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__pluginvalidafirmesid)));
        }
      }
    }

    if (__vr.getFieldErrorCount(PLUGINVALIDACERTIFICATID) == 0) {
      java.lang.Long __pluginvalidacertificatid = (java.lang.Long)__vr.getFieldValue(__target__,PLUGINVALIDACERTIFICATID);
      if (__pluginvalidacertificatid != null ) {
        Long __count_ = null;
        try { __count_ = __pluginManager.count(PluginFields.PLUGINID.equal(__pluginvalidacertificatid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ == 0) {        
          __vr.rejectValue(PLUGINVALIDACERTIFICATID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("plugin.plugin"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("plugin.pluginID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__pluginvalidacertificatid)));
        }
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}