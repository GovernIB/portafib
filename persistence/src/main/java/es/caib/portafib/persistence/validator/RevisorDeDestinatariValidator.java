package es.caib.portafib.persistence.validator;

import org.apache.log4j.Logger;

import es.caib.portafib.model.entity.RevisorDeDestinatari;
import org.fundaciobit.genapp.common.query.Field;
import es.caib.portafib.model.fields.RevisorDeDestinatariFields;
import es.caib.portafib.model.fields.UsuariEntitatFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class RevisorDeDestinatariValidator<I extends RevisorDeDestinatari>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements RevisorDeDestinatariFields {

    protected final Logger log = Logger.getLogger(getClass());


  public RevisorDeDestinatariValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,es.caib.portafib.model.dao.IRevisorDeDestinatariManager __revisorDeDestinatariManager
    ,es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,DESTINATARIID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DESTINATARIID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,REVISORID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(REVISORID)));

    // Check size
    if (__vr.getFieldErrorCount(DESTINATARIID) == 0) {
      java.lang.String __destinatariid = __target__.getDestinatariID();
      if (__destinatariid!= null && __destinatariid.length() > 101) {
        __vr.rejectValue(DESTINATARIID, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DESTINATARIID)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(101)));
      }
    }

    if (__vr.getFieldErrorCount(REVISORID) == 0) {
      java.lang.String __revisorid = __target__.getRevisorID();
      if (__revisorid!= null && __revisorid.length() > 101) {
        __vr.rejectValue(REVISORID, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(REVISORID)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(101)));
      }
    }

    if (__isNou__) { // Creació
      // ================ CREATION
      // Fitxers 
      // ====== Check Unique MULTIPLES - NOU =======

      // Check Unique MULTIPLE for (destinatariid, revisorid)
      if (__vr.getFieldErrorCount(DESTINATARIID) == 0 && __vr.getFieldErrorCount(REVISORID) == 0) {
        java.lang.String __destinatariid = __target__.getDestinatariID();
        java.lang.String __revisorid = __target__.getRevisorID();
        Long __count_ = null;
        try { __count_ = __revisorDeDestinatariManager.count(org.fundaciobit.genapp.common.query.Where.AND(DESTINATARIID.equal(__destinatariid), REVISORID.equal(__revisorid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(DESTINATARIID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__destinatariid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DESTINATARIID)));
            __vr.rejectValue(REVISORID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__revisorid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(REVISORID)));
        }
      }

      // Check Unique - no PK
      // Check Unique - PK no AutoIncrement amb UNA SOLA PK 
    } else {
      // ================ UPDATE

      // ====== Check Unique MULTIPLES - EDIT  =======

      // Check Unique MULTIPLE for (destinatariid, revisorid)
      if (__vr.getFieldErrorCount(DESTINATARIID) == 0 && __vr.getFieldErrorCount(REVISORID) == 0 && __vr.getFieldErrorCount(REVISORDEDESTINATARIID) == 0) {
        java.lang.String __destinatariid = __target__.getDestinatariID();
        java.lang.String __revisorid = __target__.getRevisorID();
        java.lang.Long __revisordedestinatariid = __target__.getRevisorDeDestinatariID();
        Long __count_ = null;
        try { __count_ = __revisorDeDestinatariManager.count(org.fundaciobit.genapp.common.query.Where.AND(DESTINATARIID.equal(__destinatariid), REVISORID.equal(__revisorid), REVISORDEDESTINATARIID.notEqual(__revisordedestinatariid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(DESTINATARIID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__destinatariid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DESTINATARIID)));
            __vr.rejectValue(REVISORID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__revisorid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(REVISORID)));
        }
      }

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

    if (__vr.getFieldErrorCount(REVISORID) == 0) {
      java.lang.String __revisorid = __target__.getRevisorID();
      Long __count_ = null;
      try { __count_ = __usuariEntitatManager.count(UsuariEntitatFields.USUARIENTITATID.equal(__revisorid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(REVISORID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuariEntitat.usuariEntitat"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuariEntitat.usuariEntitatID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__revisorid)));
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}