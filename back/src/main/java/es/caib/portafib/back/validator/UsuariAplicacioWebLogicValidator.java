package es.caib.portafib.back.validator;

import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import es.caib.portafib.back.form.webdb.UsuariAplicacioForm;
import es.caib.portafib.back.validator.webdb.UsuariAplicacioWebValidator;
import es.caib.portafib.logic.validator.UsuariAplicacioLogicValidator;
import es.caib.portafib.model.entity.UsuariAplicacio;


/**
 * 
 * @author anadal
 *
 */
@Component
public class UsuariAplicacioWebLogicValidator extends UsuariAplicacioWebValidator {

  protected UsuariAplicacioLogicValidator validatorLogic = new UsuariAplicacioLogicValidator();

  
  @Override
  public void validate(UsuariAplicacioForm __form, UsuariAplicacio __bean, Errors errors,
      WebValidationResult<UsuariAplicacioForm> wvr, boolean isNou) {
    
    validatorLogic.validate(wvr, __bean,
        isNou, custodiaInfoEjb, entitatEjb, idiomaEjb, usuariAplicacioEjb);

  } // Final de mètode
  
  

}
