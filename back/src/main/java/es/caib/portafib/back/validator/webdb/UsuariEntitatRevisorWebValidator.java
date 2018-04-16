package es.caib.portafib.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import es.caib.portafib.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.caib.portafib.jpa.validator.UsuariEntitatRevisorValidator;

import es.caib.portafib.back.form.webdb.UsuariEntitatRevisorForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class UsuariEntitatRevisorWebValidator  implements Validator, UsuariEntitatRevisorFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected UsuariEntitatRevisorValidator<Object> validator = new UsuariEntitatRevisorValidator<Object>();

  // EJB's
  @javax.ejb.EJB(mappedName = "portafib/UsuariEntitatEJB/local")
  protected es.caib.portafib.ejb.UsuariEntitatLocal usuariEntitatEjb;

  @javax.ejb.EJB(mappedName = "portafib/UsuariEntitatRevisorEJB/local")
  protected es.caib.portafib.ejb.UsuariEntitatRevisorLocal usuariEntitatRevisorEjb;



  public UsuariEntitatRevisorWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return UsuariEntitatRevisorForm.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {

    WebValidationResult<Object> wvr;
    wvr = new WebValidationResult<Object>(errors);

    Boolean nou = (Boolean)errors.getFieldValue("nou");
    boolean isNou =  nou != null && nou.booleanValue();

    validate(target, errors, wvr, isNou);
  }


  public void validate(Object target, Errors errors,
    WebValidationResult<Object> wvr, boolean isNou) {

    validator.validate(wvr, target,
      isNou, usuariEntitatEjb, usuariEntitatRevisorEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public UsuariEntitatRevisorValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(UsuariEntitatRevisorValidator<Object> validator) {
    this.validator = validator;
  }

}