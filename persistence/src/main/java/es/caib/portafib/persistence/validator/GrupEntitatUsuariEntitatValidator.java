package es.caib.portafib.persistence.validator;

import org.apache.log4j.Logger;

import es.caib.portafib.model.entity.GrupEntitatUsuariEntitat;
import org.fundaciobit.genapp.common.query.Field;
import es.caib.portafib.model.fields.GrupEntitatUsuariEntitatFields;
import es.caib.portafib.model.fields.GrupEntitatFields;
import es.caib.portafib.model.fields.UsuariEntitatFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class GrupEntitatUsuariEntitatValidator<I extends GrupEntitatUsuariEntitat>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements GrupEntitatUsuariEntitatFields {

    protected final Logger log = Logger.getLogger(getClass());


  public GrupEntitatUsuariEntitatValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,es.caib.portafib.model.dao.IGrupEntitatManager __grupEntitatManager
    ,es.caib.portafib.model.dao.IGrupEntitatUsuariEntitatManager __grupEntitatUsuariEntitatManager
    ,es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,USUARIENTITATID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIENTITATID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,GRUPENTITATID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(GRUPENTITATID)));

    // Check size
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

      // Check Unique MULTIPLE for (usuarientitatid, grupentitatid)
      if (__vr.getFieldErrorCount(USUARIENTITATID) == 0 && __vr.getFieldErrorCount(GRUPENTITATID) == 0) {
        java.lang.String __usuarientitatid = __target__.getUsuariEntitatID();
        java.lang.Long __grupentitatid = __target__.getGrupEntitatID();
        Long __count_ = null;
        try { __count_ = __grupEntitatUsuariEntitatManager.count(org.fundaciobit.genapp.common.query.Where.AND(USUARIENTITATID.equal(__usuarientitatid), GRUPENTITATID.equal(__grupentitatid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(USUARIENTITATID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__usuarientitatid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIENTITATID)));
            __vr.rejectValue(GRUPENTITATID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__grupentitatid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(GRUPENTITATID)));
        }
      }

      // Check Unique - no PK
      // Check Unique - PK no AutoIncrement amb UNA SOLA PK 
    } else {
      // ================ UPDATE

      // ====== Check Unique MULTIPLES - EDIT  =======

      // Check Unique MULTIPLE for (usuarientitatid, grupentitatid)
      if (__vr.getFieldErrorCount(USUARIENTITATID) == 0 && __vr.getFieldErrorCount(GRUPENTITATID) == 0 && __vr.getFieldErrorCount(GRUPENTITATUSUARIENTITATID) == 0) {
        java.lang.String __usuarientitatid = __target__.getUsuariEntitatID();
        java.lang.Long __grupentitatid = __target__.getGrupEntitatID();
        java.lang.Long __grupentitatusuarientitatid = __target__.getGrupEntitatUsuariEntitatID();
        Long __count_ = null;
        try { __count_ = __grupEntitatUsuariEntitatManager.count(org.fundaciobit.genapp.common.query.Where.AND(USUARIENTITATID.equal(__usuarientitatid), GRUPENTITATID.equal(__grupentitatid), GRUPENTITATUSUARIENTITATID.notEqual(__grupentitatusuarientitatid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(USUARIENTITATID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__usuarientitatid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIENTITATID)));
            __vr.rejectValue(GRUPENTITATID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__grupentitatid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(GRUPENTITATID)));
        }
      }

      // Check Unique - no PK
    }

    // Fields with References to Other tables 
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

    if (__vr.getFieldErrorCount(GRUPENTITATID) == 0) {
      java.lang.Long __grupentitatid = __target__.getGrupEntitatID();
      Long __count_ = null;
      try { __count_ = __grupEntitatManager.count(GrupEntitatFields.GRUPENTITATID.equal(__grupentitatid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(GRUPENTITATID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("grupEntitat.grupEntitat"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("grupEntitat.grupEntitatID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__grupentitatid)));
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}