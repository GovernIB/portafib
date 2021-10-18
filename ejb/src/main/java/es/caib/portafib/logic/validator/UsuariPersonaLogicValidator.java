package es.caib.portafib.logic.validator;

import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.validation.IValidatorResult;

import es.caib.portafib.persistence.validator.UsuariPersonaValidator;
import es.caib.portafib.model.entity.UsuariPersona;
import es.caib.portafib.model.fields.IdiomaFields;

/**
 * 
 * @author anadal
 *
 */
public class UsuariPersonaLogicValidator<T extends UsuariPersona> extends UsuariPersonaValidator<T> {

  @Override
  public void validate(IValidatorResult<T> __vr, T __target__, boolean __isNou__
      ,es.caib.portafib.model.dao.IIdiomaManager __idiomaManager
      ,es.caib.portafib.model.dao.IUsuariPersonaManager __usuariPersonaManager) {
    
    super.validate(__vr, __target__, __isNou__, __idiomaManager, __usuariPersonaManager);
    
    // Validar si idioma esta suportat
    if (__vr.getFieldErrorCount(IDIOMAID) == 0) {
      String idiomaID = (String)__vr.getFieldValue(__target__, IDIOMAID);
      Long count = null;
      try { count = __idiomaManager.count(Where.AND(
          IdiomaFields.IDIOMAID.equal(idiomaID),
          IdiomaFields.SUPORTAT.equal(true)));
      } catch(I18NException e) {
        e.printStackTrace();
      }
      if (count == null || count == 0 ) {
        __vr.rejectValue(IDIOMAID, "idioma.nosuportat", new I18NArgumentString(idiomaID));
      }
    }
  }
  
}
