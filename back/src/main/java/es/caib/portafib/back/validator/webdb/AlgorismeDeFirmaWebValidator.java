package es.caib.portafib.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import es.caib.portafib.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.caib.portafib.jpa.validator.AlgorismeDeFirmaValidator;

import es.caib.portafib.back.form.webdb.AlgorismeDeFirmaForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class AlgorismeDeFirmaWebValidator  implements Validator, AlgorismeDeFirmaFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected AlgorismeDeFirmaValidator<Object> validator = new AlgorismeDeFirmaValidator<Object>();

  // EJB's
  @javax.ejb.EJB(mappedName = "portafib/AlgorismeDeFirmaEJB/local")
  protected es.caib.portafib.ejb.AlgorismeDeFirmaLocal algorismeDeFirmaEjb;



  public AlgorismeDeFirmaWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return AlgorismeDeFirmaForm.class.isAssignableFrom(clazz);
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
      isNou, algorismeDeFirmaEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public AlgorismeDeFirmaValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(AlgorismeDeFirmaValidator<Object> validator) {
    this.validator = validator;
  }

}