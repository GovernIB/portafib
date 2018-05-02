package es.caib.portafib.jpa.validator;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import es.caib.portafib.model.fields.UsuariAplicacioFields;
import es.caib.portafib.model.fields.EntitatFields;
import es.caib.portafib.model.fields.IdiomaFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class UsuariAplicacioValidator<T> implements UsuariAplicacioFields {

  protected final Logger log = Logger.getLogger(getClass());


  public UsuariAplicacioValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<T> __vr, T __target__, boolean __isNou__
    ,es.caib.portafib.model.dao.IEntitatManager __entitatManager
    ,es.caib.portafib.model.dao.IIdiomaManager __idiomaManager
    ,es.caib.portafib.model.dao.IUsuariAplicacioManager __usuariAplicacioManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,USUARIAPLICACIOID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIAPLICACIOID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,ENTITATID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ENTITATID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,EMAILADMIN, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(EMAILADMIN)));

    __vr.rejectIfEmptyOrWhitespace(__target__,CALLBACKVERSIO, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CALLBACKVERSIO)));

    __vr.rejectIfEmptyOrWhitespace(__target__,CALLBACKURL, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CALLBACKURL)));

    __vr.rejectIfEmptyOrWhitespace(__target__,ACTIU, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ACTIU)));

    __vr.rejectIfEmptyOrWhitespace(__target__,IDIOMAID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(IDIOMAID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,POLITICADEPLUGINFIRMAWEB, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(POLITICADEPLUGINFIRMAWEB)));

    // Check size
    if (__vr.getFieldErrorCount(USUARIAPLICACIOID) == 0) {
      java.lang.String __usuariaplicacioid = (java.lang.String)__vr.getFieldValue(__target__,USUARIAPLICACIOID);
      if (__usuariaplicacioid!= null && __usuariaplicacioid.length() > 101) {
        __vr.rejectValue(USUARIAPLICACIOID, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIAPLICACIOID)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(101)));
      }
    }
    
    if (__vr.getFieldErrorCount(USUARIAPLICACIOID) == 0) {
      String val = String.valueOf(__vr.getFieldValue(__target__,USUARIAPLICACIOID));
      if (val != null && val.trim().length() != 0) {
        java.util.regex.Pattern p = java.util.regex.Pattern.compile("^[\\u0021-\\u0024\\u0027-\\u002E\\u0030-\\u003B\\u0040-\\u005B\\u005D-\\u007E\\u00C0-\\u00D6\\u00D8-\\u00F6\\u00F8-\\u00FF]+$");
        if (!p.matcher(val).matches()) {
          __vr.rejectValue(USUARIAPLICACIOID, "genapp.validation.malformed",
             new org.fundaciobit.genapp.common.i18n.I18NArgumentString(val), new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIAPLICACIOID)));
        }
      }
    }

    if (__vr.getFieldErrorCount(CONTRASENYA) == 0) {
      java.lang.String __contrasenya = (java.lang.String)__vr.getFieldValue(__target__,CONTRASENYA);
      if (__contrasenya!= null && __contrasenya.length() > 50) {
        __vr.rejectValue(CONTRASENYA, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CONTRASENYA)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(50)));
      }
    }
    
    if (__vr.getFieldErrorCount(ENTITATID) == 0) {
      java.lang.String __entitatid = (java.lang.String)__vr.getFieldValue(__target__,ENTITATID);
      if (__entitatid!= null && __entitatid.length() > 50) {
        __vr.rejectValue(ENTITATID, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ENTITATID)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(50)));
      }
    }
    
    if (__vr.getFieldErrorCount(EMAILADMIN) == 0) {
      java.lang.String __emailadmin = (java.lang.String)__vr.getFieldValue(__target__,EMAILADMIN);
      if (__emailadmin!= null && __emailadmin.length() > 100) {
        __vr.rejectValue(EMAILADMIN, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(EMAILADMIN)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(100)));
      }
    }
    
    if (__vr.getFieldErrorCount(EMAILADMIN) == 0) {
      String val = String.valueOf(__vr.getFieldValue(__target__,EMAILADMIN));
      if (val != null && val.trim().length() != 0) {
        java.util.regex.Pattern p = java.util.regex.Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?");
        if (!p.matcher(val).matches()) {
          __vr.rejectValue(EMAILADMIN, "genapp.validation.malformed",
             new org.fundaciobit.genapp.common.i18n.I18NArgumentString(val), new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(EMAILADMIN)));
        }
      }
    }

    if (__vr.getFieldErrorCount(CALLBACKURL) == 0) {
      java.lang.String __callbackurl = (java.lang.String)__vr.getFieldValue(__target__,CALLBACKURL);
      if (__callbackurl!= null && __callbackurl.length() > 400) {
        __vr.rejectValue(CALLBACKURL, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CALLBACKURL)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(400)));
      }
    }
    
    if (__vr.getFieldErrorCount(IDIOMAID) == 0) {
      java.lang.String __idiomaid = (java.lang.String)__vr.getFieldValue(__target__,IDIOMAID);
      if (__idiomaid!= null && __idiomaid.length() > 5) {
        __vr.rejectValue(IDIOMAID, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(IDIOMAID)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(5)));
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
      if (__vr.getFieldErrorCount(USUARIAPLICACIOID) == 0) {
        java.lang.String __usuariaplicacioid = (java.lang.String)__vr.getFieldValue(__target__,USUARIAPLICACIOID);
        Long __count_ = null;
        try { __count_ = __usuariAplicacioManager.count(org.fundaciobit.genapp.common.query.Where.AND(USUARIAPLICACIOID.equal(__usuariaplicacioid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(USUARIAPLICACIOID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__usuariaplicacioid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIAPLICACIOID)));
        }
      }

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

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}