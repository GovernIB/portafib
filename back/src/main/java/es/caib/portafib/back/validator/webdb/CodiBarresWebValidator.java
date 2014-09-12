package es.caib.portafib.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import es.caib.portafib.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.caib.portafib.jpa.validator.CodiBarresValidator;

import es.caib.portafib.back.form.webdb.CodiBarresForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class CodiBarresWebValidator  implements Validator, CodiBarresFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected CodiBarresValidator<Object> validator = new CodiBarresValidator<Object>();

  // EJB's
  @javax.ejb.EJB(mappedName = "portafib/CodiBarresEJB/local")
  protected es.caib.portafib.ejb.CodiBarresLocal codiBarresEjb;



  public CodiBarresWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return CodiBarresForm.class.isAssignableFrom(clazz);
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
      isNou, codiBarresEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public CodiBarresValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(CodiBarresValidator<Object> validator) {
    this.validator = validator;
  }

}