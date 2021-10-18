package es.caib.portafib.persistence.validator;

import org.apache.log4j.Logger;

import es.caib.portafib.model.entity.UsuariPersona;
import org.fundaciobit.genapp.common.query.Field;
import es.caib.portafib.model.fields.UsuariPersonaFields;
import es.caib.portafib.model.fields.IdiomaFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class UsuariPersonaValidator<I extends UsuariPersona>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements UsuariPersonaFields {

    protected final Logger log = Logger.getLogger(getClass());


  public UsuariPersonaValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,es.caib.portafib.model.dao.IIdiomaManager __idiomaManager
    ,es.caib.portafib.model.dao.IUsuariPersonaManager __usuariPersonaManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,USUARIPERSONAID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIPERSONAID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,NOM, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOM)));

    __vr.rejectIfEmptyOrWhitespace(__target__,LLINATGES, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(LLINATGES)));

    __vr.rejectIfEmptyOrWhitespace(__target__,EMAIL, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(EMAIL)));

    __vr.rejectIfEmptyOrWhitespace(__target__,NIF, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NIF)));

    __vr.rejectIfEmptyOrWhitespace(__target__,IDIOMAID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(IDIOMAID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,USUARIINTERN, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIINTERN)));

    // Check size
    if (__vr.getFieldErrorCount(USUARIPERSONAID) == 0) {
      java.lang.String __usuaripersonaid = __target__.getUsuariPersonaID();
      if (__usuaripersonaid!= null && __usuaripersonaid.length() > 50) {
        __vr.rejectValue(USUARIPERSONAID, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIPERSONAID)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(50)));
      }
    }

    if (__vr.getFieldErrorCount(USUARIPERSONAID) == 0) {
      String val = __target__.getUsuariPersonaID();
      if (val != null && val.trim().length() != 0) {
        java.util.regex.Pattern p = java.util.regex.Pattern.compile("^[\\u0021-\\u0024\\u0027-\\u002E\\u0030-\\u003B\\u0040-\\u005B\\u005D-\\u007E\\u00C0-\\u00D6\\u00D8-\\u00F6\\u00F8-\\u00FF]+$");
        if (!p.matcher(val).matches()) {
          __vr.rejectValue(USUARIPERSONAID, "genapp.validation.malformed",
             new org.fundaciobit.genapp.common.i18n.I18NArgumentString(val), new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIPERSONAID)));
        }
      }
    }

    if (__vr.getFieldErrorCount(NOM) == 0) {
      java.lang.String __nom = __target__.getNom();
      if (__nom!= null && __nom.length() > 50) {
        __vr.rejectValue(NOM, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOM)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(50)));
      }
    }

    if (__vr.getFieldErrorCount(LLINATGES) == 0) {
      java.lang.String __llinatges = __target__.getLlinatges();
      if (__llinatges!= null && __llinatges.length() > 100) {
        __vr.rejectValue(LLINATGES, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(LLINATGES)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(100)));
      }
    }

    if (__vr.getFieldErrorCount(EMAIL) == 0) {
      java.lang.String __email = __target__.getEmail();
      if (__email!= null && __email.length() > 100) {
        __vr.rejectValue(EMAIL, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(EMAIL)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(100)));
      }
    }

    if (__vr.getFieldErrorCount(EMAIL) == 0) {
      String val = __target__.getEmail();
      if (val != null && val.trim().length() != 0) {
        java.util.regex.Pattern p = java.util.regex.Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?");
        if (!p.matcher(val).matches()) {
          __vr.rejectValue(EMAIL, "genapp.validation.malformed",
             new org.fundaciobit.genapp.common.i18n.I18NArgumentString(val), new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(EMAIL)));
        }
      }
    }

    if (__vr.getFieldErrorCount(NIF) == 0) {
      java.lang.String __nif = __target__.getNif();
      if (__nif!= null && __nif.length() > 9) {
        __vr.rejectValue(NIF, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NIF)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(9)));
      }
    }

    if (__vr.getFieldErrorCount(NIF) == 0) {
      String val = __target__.getNif();
      if (val != null && val.trim().length() != 0) {
        java.util.regex.Pattern p = java.util.regex.Pattern.compile("([XYZ][0-9]{7}[A-Z])|([0-9]{8}[A-Z])");
        if (!p.matcher(val).matches()) {
          __vr.rejectValue(NIF, "genapp.validation.malformed",
             new org.fundaciobit.genapp.common.i18n.I18NArgumentString(val), new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NIF)));
        }
      }
    }

    if (__vr.getFieldErrorCount(IDIOMAID) == 0) {
      java.lang.String __idiomaid = __target__.getIdiomaID();
      if (__idiomaid!= null && __idiomaid.length() > 5) {
        __vr.rejectValue(IDIOMAID, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(IDIOMAID)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(5)));
      }
    }

    if (__vr.getFieldErrorCount(CONTRASENYA) == 0) {
      java.lang.String __contrasenya = __target__.getContrasenya();
      if (__contrasenya!= null && __contrasenya.length() > 255) {
        __vr.rejectValue(CONTRASENYA, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CONTRASENYA)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__isNou__) { // Creació
      // ================ CREATION
      // Fitxers 
      // ====== Check Unique MULTIPLES - NOU =======

      // Check Unique MULTIPLE for (nif, usuariintern)
      if (__vr.getFieldErrorCount(NIF) == 0 && __vr.getFieldErrorCount(USUARIINTERN) == 0) {
        java.lang.String __nif = __target__.getNif();
        java.lang.Boolean __usuariintern = __target__.isUsuariIntern();
        Long __count_ = null;
        try { __count_ = __usuariPersonaManager.count(org.fundaciobit.genapp.common.query.Where.AND(NIF.equal(__nif), USUARIINTERN.equal(__usuariintern))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(NIF, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__nif)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NIF)));
            __vr.rejectValue(USUARIINTERN, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__usuariintern)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIINTERN)));
        }
      }

      // Check Unique - no PK
      // Check Unique - PK no AutoIncrement amb UNA SOLA PK 
      if (__vr.getFieldErrorCount(USUARIPERSONAID) == 0) {
        java.lang.String __usuaripersonaid = __target__.getUsuariPersonaID();
        Long __count_ = null;
        try { __count_ = __usuariPersonaManager.count(org.fundaciobit.genapp.common.query.Where.AND(USUARIPERSONAID.equal(__usuaripersonaid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(USUARIPERSONAID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__usuaripersonaid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIPERSONAID)));
        }
      }

    } else {
      // ================ UPDATE

      // ====== Check Unique MULTIPLES - EDIT  =======

      // Check Unique MULTIPLE for (nif, usuariintern)
      if (__vr.getFieldErrorCount(NIF) == 0 && __vr.getFieldErrorCount(USUARIINTERN) == 0 && __vr.getFieldErrorCount(USUARIPERSONAID) == 0) {
        java.lang.String __nif = __target__.getNif();
        java.lang.Boolean __usuariintern = __target__.isUsuariIntern();
        java.lang.String __usuaripersonaid = __target__.getUsuariPersonaID();
        Long __count_ = null;
        try { __count_ = __usuariPersonaManager.count(org.fundaciobit.genapp.common.query.Where.AND(NIF.equal(__nif), USUARIINTERN.equal(__usuariintern), USUARIPERSONAID.notEqual(__usuaripersonaid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(NIF, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__nif)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NIF)));
            __vr.rejectValue(USUARIINTERN, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__usuariintern)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIINTERN)));
        }
      }

      // Check Unique - no PK
    }

    // Fields with References to Other tables 
    if (__vr.getFieldErrorCount(IDIOMAID) == 0) {
      java.lang.String __idiomaid = __target__.getIdiomaID();
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