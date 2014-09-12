package es.caib.portafib.jpa.validator;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import es.caib.portafib.model.fields.CustodiaInfoFields;
import es.caib.portafib.model.fields.CodiBarresFields;
import es.caib.portafib.model.fields.EntitatFields;
import es.caib.portafib.model.fields.PosicioPaginaFields;
import es.caib.portafib.model.fields.UsuariAplicacioFields;
import es.caib.portafib.model.fields.UsuariEntitatFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class CustodiaInfoValidator<T> implements CustodiaInfoFields {

  protected final Logger log = Logger.getLogger(getClass());


  public CustodiaInfoValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<T> __vr, T __target__, boolean __isNou__
    ,es.caib.portafib.model.dao.ICodiBarresManager __codiBarresManager
    ,es.caib.portafib.model.dao.ICustodiaInfoManager __custodiaInfoManager
    ,es.caib.portafib.model.dao.IEntitatManager __entitatManager
    ,es.caib.portafib.model.dao.IPosicioPaginaManager __posicioPaginaManager
    ,es.caib.portafib.model.dao.IUsuariAplicacioManager __usuariAplicacioManager
    ,es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,CUSTODIAPLUGINCLASSID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CUSTODIAPLUGINCLASSID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,CUSTODIAR, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CUSTODIAR)));

    __vr.rejectIfEmptyOrWhitespace(__target__,PAGINES, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PAGINES)));

    __vr.rejectIfEmptyOrWhitespace(__target__,MISSATGE, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(MISSATGE)));

    __vr.rejectIfEmptyOrWhitespace(__target__,MISSATGEPOSICIOPAGINAID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(MISSATGEPOSICIOPAGINAID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,CODIBARRESID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CODIBARRESID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,CODIBARRESPOSICIOPAGINAID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CODIBARRESPOSICIOPAGINAID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,CODIBARRESTEXT, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CODIBARRESTEXT)));

    __vr.rejectIfEmptyOrWhitespace(__target__,EDITABLE, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(EDITABLE)));

    // Check size
    if (__vr.getFieldErrorCount(CUSTODIAPLUGINID) == 0) {
      java.lang.String __custodiapluginid = (java.lang.String)__vr.getFieldValue(__target__,CUSTODIAPLUGINID);
      if (__custodiapluginid!= null && __custodiapluginid.length() > 255) {
        __vr.rejectValue(CUSTODIAPLUGINID, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CUSTODIAPLUGINID)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }
    
    if (__vr.getFieldErrorCount(CUSTODIAPLUGINCLASSID) == 0) {
      java.lang.String __custodiapluginclassid = (java.lang.String)__vr.getFieldValue(__target__,CUSTODIAPLUGINCLASSID);
      if (__custodiapluginclassid!= null && __custodiapluginclassid.length() > 255) {
        __vr.rejectValue(CUSTODIAPLUGINCLASSID, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CUSTODIAPLUGINCLASSID)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }
    
    if (__vr.getFieldErrorCount(CUSTODIAPLUGINPARAMETERS) == 0) {
      java.lang.String __custodiapluginparameters = (java.lang.String)__vr.getFieldValue(__target__,CUSTODIAPLUGINPARAMETERS);
      if (__custodiapluginparameters!= null && __custodiapluginparameters.length() > 3000) {
        __vr.rejectValue(CUSTODIAPLUGINPARAMETERS, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CUSTODIAPLUGINPARAMETERS)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(3000)));
      }
    }
    
    if (__vr.getFieldErrorCount(NOMPLANTILLA) == 0) {
      java.lang.String __nomplantilla = (java.lang.String)__vr.getFieldValue(__target__,NOMPLANTILLA);
      if (__nomplantilla!= null && __nomplantilla.length() > 255) {
        __vr.rejectValue(NOMPLANTILLA, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOMPLANTILLA)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }
    
    if (__vr.getFieldErrorCount(URLFITXERCUSTODIAT) == 0) {
      java.lang.String __urlfitxercustodiat = (java.lang.String)__vr.getFieldValue(__target__,URLFITXERCUSTODIAT);
      if (__urlfitxercustodiat!= null && __urlfitxercustodiat.length() > 500) {
        __vr.rejectValue(URLFITXERCUSTODIAT, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(URLFITXERCUSTODIAT)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(500)));
      }
    }
    
    if (__vr.getFieldErrorCount(PAGINES) == 0) {
      java.lang.String __pagines = (java.lang.String)__vr.getFieldValue(__target__,PAGINES);
      if (__pagines!= null && __pagines.length() > 255) {
        __vr.rejectValue(PAGINES, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PAGINES)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }
    
    if (__vr.getFieldErrorCount(MISSATGE) == 0) {
      java.lang.String __missatge = (java.lang.String)__vr.getFieldValue(__target__,MISSATGE);
      if (__missatge!= null && __missatge.length() > 3000) {
        __vr.rejectValue(MISSATGE, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(MISSATGE)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(3000)));
      }
    }
    
    if (__vr.getFieldErrorCount(CODIBARRESID) == 0) {
      java.lang.String __codibarresid = (java.lang.String)__vr.getFieldValue(__target__,CODIBARRESID);
      if (__codibarresid!= null && __codibarresid.length() > 255) {
        __vr.rejectValue(CODIBARRESID, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CODIBARRESID)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }
    
    if (__vr.getFieldErrorCount(CODIBARRESTEXT) == 0) {
      java.lang.String __codibarrestext = (java.lang.String)__vr.getFieldValue(__target__,CODIBARRESTEXT);
      if (__codibarrestext!= null && __codibarrestext.length() > 255) {
        __vr.rejectValue(CODIBARRESTEXT, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CODIBARRESTEXT)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }
    
    if (__vr.getFieldErrorCount(USUARIENTITATID) == 0) {
      java.lang.String __usuarientitatid = (java.lang.String)__vr.getFieldValue(__target__,USUARIENTITATID);
      if (__usuarientitatid!= null && __usuarientitatid.length() > 101) {
        __vr.rejectValue(USUARIENTITATID, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIENTITATID)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(101)));
      }
    }
    
    if (__vr.getFieldErrorCount(USUARIAPLICACIOID) == 0) {
      java.lang.String __usuariaplicacioid = (java.lang.String)__vr.getFieldValue(__target__,USUARIAPLICACIOID);
      if (__usuariaplicacioid!= null && __usuariaplicacioid.length() > 101) {
        __vr.rejectValue(USUARIAPLICACIOID, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIAPLICACIOID)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(101)));
      }
    }
    
    if (__vr.getFieldErrorCount(ENTITATID) == 0) {
      java.lang.String __entitatid = (java.lang.String)__vr.getFieldValue(__target__,ENTITATID);
      if (__entitatid!= null && __entitatid.length() > 50) {
        __vr.rejectValue(ENTITATID, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ENTITATID)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(50)));
      }
    }
    
    if (__vr.getFieldErrorCount(TITOLPETICIO) == 0) {
      java.lang.String __titolpeticio = (java.lang.String)__vr.getFieldValue(__target__,TITOLPETICIO);
      if (__titolpeticio!= null && __titolpeticio.length() > 255) {
        __vr.rejectValue(TITOLPETICIO, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TITOLPETICIO)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
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
    if (__vr.getFieldErrorCount(MISSATGEPOSICIOPAGINAID) == 0) {
      java.lang.Long __missatgeposiciopaginaid = (java.lang.Long)__vr.getFieldValue(__target__,MISSATGEPOSICIOPAGINAID);
      Long __count_ = null;
      try { __count_ = __posicioPaginaManager.count(PosicioPaginaFields.POSICIOPAGINAID.equal(__missatgeposiciopaginaid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(MISSATGEPOSICIOPAGINAID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("posicioPagina.posicioPagina"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("posicioPagina.posicioPaginaID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__missatgeposiciopaginaid)));
      }
    }

    if (__vr.getFieldErrorCount(CODIBARRESID) == 0) {
      java.lang.String __codibarresid = (java.lang.String)__vr.getFieldValue(__target__,CODIBARRESID);
      Long __count_ = null;
      try { __count_ = __codiBarresManager.count(CodiBarresFields.CODIBARRESID.equal(__codibarresid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(CODIBARRESID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("codiBarres.codiBarres"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("codiBarres.codiBarresID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__codibarresid)));
      }
    }

    if (__vr.getFieldErrorCount(CODIBARRESPOSICIOPAGINAID) == 0) {
      java.lang.Long __codibarresposiciopaginaid = (java.lang.Long)__vr.getFieldValue(__target__,CODIBARRESPOSICIOPAGINAID);
      Long __count_ = null;
      try { __count_ = __posicioPaginaManager.count(PosicioPaginaFields.POSICIOPAGINAID.equal(__codibarresposiciopaginaid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(CODIBARRESPOSICIOPAGINAID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("posicioPagina.posicioPagina"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("posicioPagina.posicioPaginaID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__codibarresposiciopaginaid)));
      }
    }

    if (__vr.getFieldErrorCount(USUARIENTITATID) == 0) {
      java.lang.String __usuarientitatid = (java.lang.String)__vr.getFieldValue(__target__,USUARIENTITATID);
      if (__usuarientitatid != null  && __usuarientitatid.length() != 0) {
        Long __count_ = null;
        try { __count_ = __usuariEntitatManager.count(UsuariEntitatFields.USUARIENTITATID.equal(__usuarientitatid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ == 0) {        
          __vr.rejectValue(USUARIENTITATID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuariEntitat.usuariEntitat"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuariEntitat.usuariEntitatID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__usuarientitatid)));
        }
      }
    }

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

    if (__vr.getFieldErrorCount(ENTITATID) == 0) {
      java.lang.String __entitatid = (java.lang.String)__vr.getFieldValue(__target__,ENTITATID);
      if (__entitatid != null  && __entitatid.length() != 0) {
        Long __count_ = null;
        try { __count_ = __entitatManager.count(EntitatFields.ENTITATID.equal(__entitatid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ == 0) {        
          __vr.rejectValue(ENTITATID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("entitat.entitat"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("entitat.entitatID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__entitatid)));
        }
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}