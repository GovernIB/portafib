package es.caib.portafib.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import es.caib.portafib.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.caib.portafib.jpa.validator.TipusEstatDeFirmaInicialValidator;

import es.caib.portafib.back.form.webdb.TipusEstatDeFirmaInicialForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class TipusEstatDeFirmaInicialWebValidator  implements Validator, TipusEstatDeFirmaInicialFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected TipusEstatDeFirmaInicialValidator<Object> validator = new TipusEstatDeFirmaInicialValidator<Object>();

  // EJB's
  @javax.ejb.EJB(mappedName = "portafib/TipusEstatDeFirmaInicialEJB/local")
  protected es.caib.portafib.ejb.TipusEstatDeFirmaInicialLocal tipusEstatDeFirmaInicialEjb;



  public TipusEstatDeFirmaInicialWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return TipusEstatDeFirmaInicialForm.class.isAssignableFrom(clazz);
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
      isNou, tipusEstatDeFirmaInicialEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public TipusEstatDeFirmaInicialValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(TipusEstatDeFirmaInicialValidator<Object> validator) {
    this.validator = validator;
  }

}