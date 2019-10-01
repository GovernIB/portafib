package es.caib.portafib.back.validator;

import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.back.validator.webdb.UsuariAplicacioWebValidator;
import es.caib.portafib.logic.validator.UsuariAplicacioLogicValidator;


/**
 * 
 * @author anadal
 *
 */
@Component
public class UsuariAplicacioWebLogicValidator extends UsuariAplicacioWebValidator {

  protected UsuariAplicacioLogicValidator<Object> validatorLogic = new UsuariAplicacioLogicValidator<Object>();

  
  @Override
  public void validate(Object target, Errors errors,
      WebValidationResult<Object> wvr, boolean isNou) {
    
    validatorLogic.validate(wvr, target,
        isNou, custodiaInfoEjb, entitatEjb, idiomaEjb, usuariAplicacioEjb);

  } // Final de mètode
  
  

}
