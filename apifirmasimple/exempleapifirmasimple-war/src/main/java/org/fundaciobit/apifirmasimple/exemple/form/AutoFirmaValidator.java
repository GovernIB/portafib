package org.fundaciobit.apifirmasimple.exemple.form;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * 
 * @author anadal
 *
 */
@Component
public class AutoFirmaValidator implements Validator {

  protected final Logger log = Logger.getLogger(getClass());

  public static final String MOTIU = "motiu"; // "motiu");

  public static final String FITXERAFIRMARID = "fitxerAFirmarID"; // "fitxerafirmarid");

  public AutoFirmaValidator() {
    super();
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return AutoFirmaForm.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {

    CommonsMultipartFile fitxerAFirmarID = ((AutoFirmaForm) target).getFitxerAFirmarID();

    if (fitxerAFirmarID == null || fitxerAFirmarID.isEmpty()) {
      errors.rejectValue(FITXERAFIRMARID, "genapp.validation.required",
          new String[] { FITXERAFIRMARID }, null);
    }

  }

}
