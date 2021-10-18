package es.caib.portafib.persistence.validator;

import org.apache.log4j.Logger;

import es.caib.portafib.model.entity.PerfilsPerUsuariAplicacio;
import org.fundaciobit.genapp.common.query.Field;
import es.caib.portafib.model.fields.PerfilsPerUsuariAplicacioFields;
import es.caib.portafib.model.fields.PerfilDeFirmaFields;
import es.caib.portafib.model.fields.UsuariAplicacioFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class PerfilsPerUsuariAplicacioValidator<I extends PerfilsPerUsuariAplicacio>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements PerfilsPerUsuariAplicacioFields {

    protected final Logger log = Logger.getLogger(getClass());


  public PerfilsPerUsuariAplicacioValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,es.caib.portafib.model.dao.IPerfilDeFirmaManager __perfilDeFirmaManager
    ,es.caib.portafib.model.dao.IPerfilsPerUsuariAplicacioManager __perfilsPerUsuariAplicacioManager
    ,es.caib.portafib.model.dao.IUsuariAplicacioManager __usuariAplicacioManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,PERFILDEFIRMAID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PERFILDEFIRMAID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,USUARIAPLICACIOID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIAPLICACIOID)));

    // Check size
    if (__vr.getFieldErrorCount(USUARIAPLICACIOID) == 0) {
      java.lang.String __usuariaplicacioid = __target__.getUsuariAplicacioID();
      if (__usuariaplicacioid!= null && __usuariaplicacioid.length() > 50) {
        __vr.rejectValue(USUARIAPLICACIOID, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIAPLICACIOID)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(50)));
      }
    }

    if (__isNou__) { // Creació
      // ================ CREATION
      // Fitxers 
      // ====== Check Unique MULTIPLES - NOU =======

      // Check Unique MULTIPLE for (usuariaplicacioperfilid, usuariaplicacioid)
      if (__vr.getFieldErrorCount(PERFILDEFIRMAID) == 0 && __vr.getFieldErrorCount(USUARIAPLICACIOID) == 0) {
        java.lang.Long __perfildefirmaid = __target__.getPerfilDeFirmaID();
        java.lang.String __usuariaplicacioid = __target__.getUsuariAplicacioID();
        Long __count_ = null;
        try { __count_ = __perfilsPerUsuariAplicacioManager.count(org.fundaciobit.genapp.common.query.Where.AND(PERFILDEFIRMAID.equal(__perfildefirmaid), USUARIAPLICACIOID.equal(__usuariaplicacioid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(PERFILDEFIRMAID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__perfildefirmaid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PERFILDEFIRMAID)));
            __vr.rejectValue(USUARIAPLICACIOID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__usuariaplicacioid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIAPLICACIOID)));
        }
      }

      // Check Unique - no PK
      // Check Unique - PK no AutoIncrement amb UNA SOLA PK 
    } else {
      // ================ UPDATE

      // ====== Check Unique MULTIPLES - EDIT  =======

      // Check Unique MULTIPLE for (usuariaplicacioperfilid, usuariaplicacioid)
      if (__vr.getFieldErrorCount(PERFILDEFIRMAID) == 0 && __vr.getFieldErrorCount(USUARIAPLICACIOID) == 0 && __vr.getFieldErrorCount(PERFILSPERUSRAPPID) == 0) {
        java.lang.Long __perfildefirmaid = __target__.getPerfilDeFirmaID();
        java.lang.String __usuariaplicacioid = __target__.getUsuariAplicacioID();
        java.lang.Long __perfilsperusrappid = __target__.getPerfilsPerUsrAppID();
        Long __count_ = null;
        try { __count_ = __perfilsPerUsuariAplicacioManager.count(org.fundaciobit.genapp.common.query.Where.AND(PERFILDEFIRMAID.equal(__perfildefirmaid), USUARIAPLICACIOID.equal(__usuariaplicacioid), PERFILSPERUSRAPPID.notEqual(__perfilsperusrappid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(PERFILDEFIRMAID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__perfildefirmaid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PERFILDEFIRMAID)));
            __vr.rejectValue(USUARIAPLICACIOID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__usuariaplicacioid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIAPLICACIOID)));
        }
      }

      // Check Unique - no PK
    }

    // Fields with References to Other tables 
    if (__vr.getFieldErrorCount(PERFILDEFIRMAID) == 0) {
      java.lang.Long __perfildefirmaid = __target__.getPerfilDeFirmaID();
      Long __count_ = null;
      try { __count_ = __perfilDeFirmaManager.count(PerfilDeFirmaFields.USUARIAPLICACIOPERFILID.equal(__perfildefirmaid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(PERFILDEFIRMAID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("perfilDeFirma.perfilDeFirma"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("perfilDeFirma.usuariAplicacioPerfilID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__perfildefirmaid)));
      }
    }

    if (__vr.getFieldErrorCount(USUARIAPLICACIOID) == 0) {
      java.lang.String __usuariaplicacioid = __target__.getUsuariAplicacioID();
      Long __count_ = null;
      try { __count_ = __usuariAplicacioManager.count(UsuariAplicacioFields.USUARIAPLICACIOID.equal(__usuariaplicacioid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(USUARIAPLICACIOID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuariAplicacio.usuariAplicacio"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuariAplicacio.usuariAplicacioID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__usuariaplicacioid)));
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}