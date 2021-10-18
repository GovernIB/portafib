package es.caib.portafib.persistence.validator;

import org.apache.log4j.Logger;

import es.caib.portafib.model.entity.RoleUsuariEntitat;
import org.fundaciobit.genapp.common.query.Field;
import es.caib.portafib.model.fields.RoleUsuariEntitatFields;
import es.caib.portafib.model.fields.RoleFields;
import es.caib.portafib.model.fields.UsuariEntitatFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class RoleUsuariEntitatValidator<I extends RoleUsuariEntitat>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements RoleUsuariEntitatFields {

    protected final Logger log = Logger.getLogger(getClass());


  public RoleUsuariEntitatValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,es.caib.portafib.model.dao.IRoleManager __roleManager
    ,es.caib.portafib.model.dao.IRoleUsuariEntitatManager __roleUsuariEntitatManager
    ,es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,ROLEID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ROLEID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,USUARIENTITATID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIENTITATID)));

    // Check size
    if (__vr.getFieldErrorCount(ROLEID) == 0) {
      java.lang.String __roleid = __target__.getRoleID();
      if (__roleid!= null && __roleid.length() > 50) {
        __vr.rejectValue(ROLEID, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ROLEID)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(50)));
      }
    }

    if (__vr.getFieldErrorCount(USUARIENTITATID) == 0) {
      java.lang.String __usuarientitatid = __target__.getUsuariEntitatID();
      if (__usuarientitatid!= null && __usuarientitatid.length() > 101) {
        __vr.rejectValue(USUARIENTITATID, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIENTITATID)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(101)));
      }
    }

    if (__isNou__) { // Creació
      // ================ CREATION
      // Fitxers 
      // ====== Check Unique MULTIPLES - NOU =======

      // Check Unique MULTIPLE for (roleid, usuarientitatid)
      if (__vr.getFieldErrorCount(ROLEID) == 0 && __vr.getFieldErrorCount(USUARIENTITATID) == 0) {
        java.lang.String __roleid = __target__.getRoleID();
        java.lang.String __usuarientitatid = __target__.getUsuariEntitatID();
        Long __count_ = null;
        try { __count_ = __roleUsuariEntitatManager.count(org.fundaciobit.genapp.common.query.Where.AND(ROLEID.equal(__roleid), USUARIENTITATID.equal(__usuarientitatid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(ROLEID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__roleid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ROLEID)));
            __vr.rejectValue(USUARIENTITATID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__usuarientitatid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIENTITATID)));
        }
      }

      // Check Unique - no PK
      // Check Unique - PK no AutoIncrement amb UNA SOLA PK 
    } else {
      // ================ UPDATE

      // ====== Check Unique MULTIPLES - EDIT  =======

      // Check Unique MULTIPLE for (roleid, usuarientitatid)
      if (__vr.getFieldErrorCount(ROLEID) == 0 && __vr.getFieldErrorCount(USUARIENTITATID) == 0 && __vr.getFieldErrorCount(ID) == 0) {
        java.lang.String __roleid = __target__.getRoleID();
        java.lang.String __usuarientitatid = __target__.getUsuariEntitatID();
        java.lang.Long __id = __target__.getId();
        Long __count_ = null;
        try { __count_ = __roleUsuariEntitatManager.count(org.fundaciobit.genapp.common.query.Where.AND(ROLEID.equal(__roleid), USUARIENTITATID.equal(__usuarientitatid), ID.notEqual(__id))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(ROLEID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__roleid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ROLEID)));
            __vr.rejectValue(USUARIENTITATID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__usuarientitatid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIENTITATID)));
        }
      }

      // Check Unique - no PK
    }

    // Fields with References to Other tables 
    if (__vr.getFieldErrorCount(ROLEID) == 0) {
      java.lang.String __roleid = __target__.getRoleID();
      Long __count_ = null;
      try { __count_ = __roleManager.count(RoleFields.ROLEID.equal(__roleid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(ROLEID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("role.role"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("role.roleID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__roleid)));
      }
    }

    if (__vr.getFieldErrorCount(USUARIENTITATID) == 0) {
      java.lang.String __usuarientitatid = __target__.getUsuariEntitatID();
      Long __count_ = null;
      try { __count_ = __usuariEntitatManager.count(UsuariEntitatFields.USUARIENTITATID.equal(__usuarientitatid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(USUARIENTITATID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuariEntitat.usuariEntitat"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuariEntitat.usuariEntitatID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__usuarientitatid)));
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}