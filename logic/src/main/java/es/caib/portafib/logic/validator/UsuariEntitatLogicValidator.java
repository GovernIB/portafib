package es.caib.portafib.logic.validator;

import org.fundaciobit.genapp.common.i18n.I18NArgumentCode;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.validation.IValidatorResult;

import es.caib.portafib.jpa.validator.UsuariEntitatValidator;

/**
 * 
 * @author anadal
 * 
 */
public class UsuariEntitatLogicValidator<T> extends UsuariEntitatValidator<T> {

  @Override
  public void validate(IValidatorResult<T> __vr, T __target__, boolean __isNou__,
      es.caib.portafib.model.dao.IEntitatManager __entitatManager,
      es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager,
      es.caib.portafib.model.dao.IUsuariPersonaManager __usuariPersonaManager)  {
    
    String __usuarientitatid = (java.lang.String) __vr.getFieldValue(__target__, USUARIENTITATID);
    String __entitatid = (java.lang.String) __vr.getFieldValue(__target__, ENTITATID);
    String __carrec = (java.lang.String) __vr.getFieldValue(__target__, CARREC);
    Long __count_ = null;
    Where w;
    if (__carrec != null) {
      if (__isNou__) {
        // Check Unique MULTIPLE for (entitatid, carrec)
        w = org.fundaciobit.genapp.common.query.Where.AND(ENTITATID.equal(__entitatid),
            CARREC.equal(__carrec));
      } else {
        
        w = org.fundaciobit.genapp.common.query.Where.AND(ENTITATID.equal(__entitatid),
            CARREC.equal(__carrec), USUARIENTITATID.notEqual(__usuarientitatid));
      }
      try {
        __count_ = __usuariEntitatManager.count(w);
      } catch (I18NException e) {
        e.printStackTrace();
      };
      if (__count_ == null || __count_ != 0) {
        __vr.rejectValue(CARREC, "genapp.validation.unique",
            new I18NArgumentString(String.valueOf(__carrec)), new I18NArgumentCode(
                CARREC.fullName));
      }
    }
    
    super.validate(__vr, __target__, __isNou__,  __entitatManager, __usuariEntitatManager,
        __usuariPersonaManager);
    
    // Si carrec == null llavors usuariEntitatID és la concatenació
    // de entitatid + "_" + usuaripersona 
    
    if (!__vr.hasErrors() && __carrec == null) {
      String __usuaripersonaid = (java.lang.String) __vr.getFieldValue(__target__, USUARIPERSONAID);
      String expected = __entitatid + "_" + __usuaripersonaid;
      if (!expected.equals(__usuarientitatid)) {
        log.error("EXPECTED = " + __carrec + " | usuarientitatid = " + __usuarientitatid);
        __vr.rejectValue(USUARIENTITATID, "usuariEntitat.error.formatusuarientitatid",
            new I18NArgumentString(expected), new I18NArgumentString(
                __usuarientitatid));
      }
      
    }

  }

}
