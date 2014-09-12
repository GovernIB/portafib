package es.caib.portafib.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import es.caib.portafib.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.caib.portafib.jpa.validator.BlocDeFirmesValidator;

import es.caib.portafib.back.form.webdb.BlocDeFirmesForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class BlocDeFirmesWebValidator  implements Validator, BlocDeFirmesFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected BlocDeFirmesValidator<Object> validator = new BlocDeFirmesValidator<Object>();

  // EJB's
  @javax.ejb.EJB(mappedName = "portafib/BlocDeFirmesEJB/local")
  protected es.caib.portafib.ejb.BlocDeFirmesLocal blocDeFirmesEjb;

  @javax.ejb.EJB(mappedName = "portafib/FluxDeFirmesEJB/local")
  protected es.caib.portafib.ejb.FluxDeFirmesLocal fluxDeFirmesEjb;



  public BlocDeFirmesWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return BlocDeFirmesForm.class.isAssignableFrom(clazz);
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
      isNou, blocDeFirmesEjb, fluxDeFirmesEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public BlocDeFirmesValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(BlocDeFirmesValidator<Object> validator) {
    this.validator = validator;
  }

}