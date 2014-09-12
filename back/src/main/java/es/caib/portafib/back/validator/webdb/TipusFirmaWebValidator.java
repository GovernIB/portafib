package es.caib.portafib.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import es.caib.portafib.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.caib.portafib.jpa.validator.TipusFirmaValidator;

import es.caib.portafib.back.form.webdb.TipusFirmaForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class TipusFirmaWebValidator  implements Validator, TipusFirmaFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected TipusFirmaValidator<Object> validator = new TipusFirmaValidator<Object>();

  // EJB's
  @javax.ejb.EJB(mappedName = "portafib/TipusFirmaEJB/local")
  protected es.caib.portafib.ejb.TipusFirmaLocal tipusFirmaEjb;



  public TipusFirmaWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return TipusFirmaForm.class.isAssignableFrom(clazz);
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
      isNou, tipusFirmaEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public TipusFirmaValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(TipusFirmaValidator<Object> validator) {
    this.validator = validator;
  }

}