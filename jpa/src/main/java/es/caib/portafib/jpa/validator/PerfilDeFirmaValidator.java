package es.caib.portafib.jpa.validator;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import es.caib.portafib.model.fields.PerfilDeFirmaFields;
import es.caib.portafib.model.fields.UsuariAplicacioConfiguracioFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class PerfilDeFirmaValidator<T> implements PerfilDeFirmaFields {

  protected final Logger log = Logger.getLogger(getClass());


  public PerfilDeFirmaValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<T> __vr, T __target__, boolean __isNou__
    ,es.caib.portafib.model.dao.IPerfilDeFirmaManager __perfilDeFirmaManager
    ,es.caib.portafib.model.dao.IUsuariAplicacioConfiguracioManager __usuariAplicacioConfiguracioManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,NOM, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOM)));

    __vr.rejectIfEmptyOrWhitespace(__target__,CODI, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CODI)));

    __vr.rejectIfEmptyOrWhitespace(__target__,CONFIGURACIODEFIRMA1ID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CONFIGURACIODEFIRMA1ID)));

    // Check size
    if (__vr.getFieldErrorCount(NOM) == 0) {
      java.lang.String __nom = (java.lang.String)__vr.getFieldValue(__target__,NOM);
      if (__nom!= null && __nom.length() > 255) {
        __vr.rejectValue(NOM, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOM)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }
    
    if (__vr.getFieldErrorCount(CODI) == 0) {
      java.lang.String __codi = (java.lang.String)__vr.getFieldValue(__target__,CODI);
      if (__codi!= null && __codi.length() > 100) {
        __vr.rejectValue(CODI, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CODI)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(100)));
      }
    }
    
    //class java.lang.Class
    if (__vr.getFieldErrorCount(CODI) == 0) {
      String val = String.valueOf(__vr.getFieldValue(__target__,CODI));
      if (val != null && val.trim().length() != 0) {
        java.util.regex.Pattern p = java.util.regex.Pattern.compile("([A-Z]|[a-z]|[0-9]|_|\\-)*");
        if (!p.matcher(val).matches()) {
          __vr.rejectValue(CODI, "genapp.validation.malformed",
             new org.fundaciobit.genapp.common.i18n.I18NArgumentString(val), new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CODI)));
        }
      }
    }

    if (__vr.getFieldErrorCount(DESCRIPCIO) == 0) {
      java.lang.String __descripcio = (java.lang.String)__vr.getFieldValue(__target__,DESCRIPCIO);
      if (__descripcio!= null && __descripcio.length() > 500) {
        __vr.rejectValue(DESCRIPCIO, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DESCRIPCIO)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(500)));
      }
    }
    
    if (__vr.getFieldErrorCount(CONDICIO) == 0) {
      java.lang.String __condicio = (java.lang.String)__vr.getFieldValue(__target__,CONDICIO);
      if (__condicio!= null && __condicio.length() > 4000) {
        __vr.rejectValue(CONDICIO, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CONDICIO)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(4000)));
      }
    }
    
    if (__vr.getFieldErrorCount(URLBASE) == 0) {
      java.lang.String __urlbase = (java.lang.String)__vr.getFieldValue(__target__,URLBASE);
      if (__urlbase!= null && __urlbase.length() > 255) {
        __vr.rejectValue(URLBASE, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(URLBASE)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }
    
    if (__isNou__) { // Creació
      // ================ CREATION
      // Fitxers 
      // ====== Check Unique MULTIPLES - NOU =======

      // Check Unique - no PK
      if (__vr.getFieldErrorCount(CODI) == 0) {
        java.lang.String __codi = (java.lang.String)__vr.getFieldValue(__target__,CODI);
        Long __count_ = null;
        try { __count_ = __perfilDeFirmaManager.count(org.fundaciobit.genapp.common.query.Where.AND(CODI.equal(__codi))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(CODI, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__codi)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CODI)));
        }
      }

      // Check Unique - PK no AutoIncrement amb UNA SOLA PK 
    } else {
      // ================ UPDATE

      // ====== Check Unique MULTIPLES - EDIT  =======

      // Check Unique - no PK
      if (__vr.getFieldErrorCount(CODI) == 0 && __vr.getFieldErrorCount(USUARIAPLICACIOPERFILID) == 0) {
        java.lang.String __codi = (java.lang.String)__vr.getFieldValue(__target__,CODI);
        java.lang.Long __usuariaplicacioperfilid = (java.lang.Long)__vr.getFieldValue(__target__,USUARIAPLICACIOPERFILID);
        Long __count_ = null;
        try { __count_ = __perfilDeFirmaManager.count(org.fundaciobit.genapp.common.query.Where.AND(CODI.equal(__codi), USUARIAPLICACIOPERFILID.notEqual(__usuariaplicacioperfilid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(CODI, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__codi)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CODI)));
        }
      }

    }

    // Fields with References to Other tables 
    if (__vr.getFieldErrorCount(CONFIGURACIODEFIRMA1ID) == 0) {
      java.lang.Long __configuraciodefirma1id = (java.lang.Long)__vr.getFieldValue(__target__,CONFIGURACIODEFIRMA1ID);
      Long __count_ = null;
      try { __count_ = __usuariAplicacioConfiguracioManager.count(UsuariAplicacioConfiguracioFields.USUARIAPLICACIOCONFIGID.equal(__configuraciodefirma1id)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(CONFIGURACIODEFIRMA1ID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuariAplicacioConfiguracio.usuariAplicacioConfiguracio"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuariAplicacioConfiguracio.usuariAplicacioConfigID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__configuraciodefirma1id)));
      }
    }

    if (__vr.getFieldErrorCount(CONFIGURACIODEFIRMA2ID) == 0) {
      java.lang.Long __configuraciodefirma2id = (java.lang.Long)__vr.getFieldValue(__target__,CONFIGURACIODEFIRMA2ID);
      if (__configuraciodefirma2id != null ) {
        Long __count_ = null;
        try { __count_ = __usuariAplicacioConfiguracioManager.count(UsuariAplicacioConfiguracioFields.USUARIAPLICACIOCONFIGID.equal(__configuraciodefirma2id)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ == 0) {        
          __vr.rejectValue(CONFIGURACIODEFIRMA2ID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuariAplicacioConfiguracio.usuariAplicacioConfiguracio"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuariAplicacioConfiguracio.usuariAplicacioConfigID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__configuraciodefirma2id)));
        }
      }
    }

    if (__vr.getFieldErrorCount(CONFIGURACIODEFIRMA3ID) == 0) {
      java.lang.Long __configuraciodefirma3id = (java.lang.Long)__vr.getFieldValue(__target__,CONFIGURACIODEFIRMA3ID);
      if (__configuraciodefirma3id != null ) {
        Long __count_ = null;
        try { __count_ = __usuariAplicacioConfiguracioManager.count(UsuariAplicacioConfiguracioFields.USUARIAPLICACIOCONFIGID.equal(__configuraciodefirma3id)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ == 0) {        
          __vr.rejectValue(CONFIGURACIODEFIRMA3ID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuariAplicacioConfiguracio.usuariAplicacioConfiguracio"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuariAplicacioConfiguracio.usuariAplicacioConfigID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__configuraciodefirma3id)));
        }
      }
    }

    if (__vr.getFieldErrorCount(CONFIGURACIODEFIRMA4ID) == 0) {
      java.lang.Long __configuraciodefirma4id = (java.lang.Long)__vr.getFieldValue(__target__,CONFIGURACIODEFIRMA4ID);
      if (__configuraciodefirma4id != null ) {
        Long __count_ = null;
        try { __count_ = __usuariAplicacioConfiguracioManager.count(UsuariAplicacioConfiguracioFields.USUARIAPLICACIOCONFIGID.equal(__configuraciodefirma4id)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ == 0) {        
          __vr.rejectValue(CONFIGURACIODEFIRMA4ID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuariAplicacioConfiguracio.usuariAplicacioConfiguracio"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuariAplicacioConfiguracio.usuariAplicacioConfigID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__configuraciodefirma4id)));
        }
      }
    }

    if (__vr.getFieldErrorCount(CONFIGURACIODEFIRMA5ID) == 0) {
      java.lang.Long __configuraciodefirma5id = (java.lang.Long)__vr.getFieldValue(__target__,CONFIGURACIODEFIRMA5ID);
      if (__configuraciodefirma5id != null ) {
        Long __count_ = null;
        try { __count_ = __usuariAplicacioConfiguracioManager.count(UsuariAplicacioConfiguracioFields.USUARIAPLICACIOCONFIGID.equal(__configuraciodefirma5id)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ == 0) {        
          __vr.rejectValue(CONFIGURACIODEFIRMA5ID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuariAplicacioConfiguracio.usuariAplicacioConfiguracio"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuariAplicacioConfiguracio.usuariAplicacioConfigID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__configuraciodefirma5id)));
        }
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}