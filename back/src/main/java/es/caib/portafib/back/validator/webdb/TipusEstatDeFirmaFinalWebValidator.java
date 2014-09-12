package es.caib.portafib.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import es.caib.portafib.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.caib.portafib.jpa.validator.TipusEstatDeFirmaFinalValidator;

import es.caib.portafib.back.form.webdb.TipusEstatDeFirmaFinalForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class TipusEstatDeFirmaFinalWebValidator  implements Validator, TipusEstatDeFirmaFinalFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected TipusEstatDeFirmaFinalValidator<Object> validator = new TipusEstatDeFirmaFinalValidator<Object>();

  // EJB's
  @javax.ejb.EJB(mappedName = "portafib/TipusEstatDeFirmaFinalEJB/local")
  protected es.caib.portafib.ejb.TipusEstatDeFirmaFinalLocal tipusEstatDeFirmaFinalEjb;



  public TipusEstatDeFirmaFinalWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return TipusEstatDeFirmaFinalForm.class.isAssignableFrom(clazz);
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
      isNou, tipusEstatDeFirmaFinalEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public TipusEstatDeFirmaFinalValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(TipusEstatDeFirmaFinalValidator<Object> validator) {
    this.validator = validator;
  }

}