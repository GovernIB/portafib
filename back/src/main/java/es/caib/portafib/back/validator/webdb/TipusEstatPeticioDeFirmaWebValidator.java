package es.caib.portafib.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import es.caib.portafib.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.caib.portafib.jpa.validator.TipusEstatPeticioDeFirmaValidator;

import es.caib.portafib.back.form.webdb.TipusEstatPeticioDeFirmaForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class TipusEstatPeticioDeFirmaWebValidator  implements Validator, TipusEstatPeticioDeFirmaFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected TipusEstatPeticioDeFirmaValidator<Object> validator = new TipusEstatPeticioDeFirmaValidator<Object>();

  // EJB's
  @javax.ejb.EJB(mappedName = "portafib/TipusEstatPeticioDeFirmaEJB/local")
  protected es.caib.portafib.ejb.TipusEstatPeticioDeFirmaLocal tipusEstatPeticioDeFirmaEjb;



  public TipusEstatPeticioDeFirmaWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return TipusEstatPeticioDeFirmaForm.class.isAssignableFrom(clazz);
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
      isNou, tipusEstatPeticioDeFirmaEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public TipusEstatPeticioDeFirmaValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(TipusEstatPeticioDeFirmaValidator<Object> validator) {
    this.validator = validator;
  }

}