package es.caib.portafib.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import es.caib.portafib.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.caib.portafib.jpa.validator.PropietatGlobalValidator;

import es.caib.portafib.back.form.webdb.PropietatGlobalForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class PropietatGlobalWebValidator  implements Validator, PropietatGlobalFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected PropietatGlobalValidator<Object> validator = new PropietatGlobalValidator<Object>();

  // EJB's
  @javax.ejb.EJB(mappedName = "portafib/EntitatEJB/local")
  protected es.caib.portafib.ejb.EntitatLocal entitatEjb;

  @javax.ejb.EJB(mappedName = "portafib/PropietatGlobalEJB/local")
  protected es.caib.portafib.ejb.PropietatGlobalLocal propietatGlobalEjb;



  public PropietatGlobalWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return PropietatGlobalForm.class.isAssignableFrom(clazz);
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
      isNou, entitatEjb, propietatGlobalEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public PropietatGlobalValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(PropietatGlobalValidator<Object> validator) {
    this.validator = validator;
  }

}