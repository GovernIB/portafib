package es.caib.portafib.back.validator;

import es.caib.portafib.back.form.SeleccioUsuariForm;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


/**
 *
 * @author anadal
 */
@Component
public class SeleccioUsuariValidator implements Validator {

  protected final Logger log = Logger.getLogger(getClass());


  public SeleccioUsuariValidator() {
    super();
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return SeleccioUsuariForm.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {

    // TODO En altres idiomes (diferents de es i ca) potser el misstage no estigui be
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id",        
    "genapp.validation.required", new Object[] { "" });

  }


}
