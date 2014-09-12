package es.caib.portafib.logic.validator;

import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.validation.IValidatorResult;

import es.caib.portafib.jpa.validator.RoleUsuariEntitatValidator;
import es.caib.portafib.model.entity.UsuariEntitat;

/**
 * 
 * @author anadal
 *
 * @param <T>
 */
public class RoleUsuariEntitatLogicValidator<T> extends RoleUsuariEntitatValidator<T> {

  
  /** Constructor */
  public void validate(IValidatorResult<T> __vr, T __target__, boolean __isNou__
    ,es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager
    ,es.caib.portafib.model.dao.IRoleUsuariEntitatManager __roleUsuariEntitatManager
    ,es.caib.portafib.model.dao.IRoleManager __roleManager) {
    
    super.validate(__vr, __target__, __isNou__, 
        __roleManager, __roleUsuariEntitatManager, __usuariEntitatManager);
    
    if (!__vr.hasErrors()) {
      // Verificar que usuari entitat no és un carrec
      String usuariEntitatID = (String)__vr.getFieldValue(__target__, USUARIENTITATID);
      UsuariEntitat ue = __usuariEntitatManager.findByPrimaryKey(usuariEntitatID);
      
      if (ue.getCarrec() != null) {
        __vr.rejectValue(USUARIENTITATID, "roleusuarientitat.error.escarrec",
            new I18NArgumentString(usuariEntitatID));
      }
      
    }
    
  }
  
  
}
