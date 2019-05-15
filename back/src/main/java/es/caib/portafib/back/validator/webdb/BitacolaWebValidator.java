package es.caib.portafib.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import es.caib.portafib.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.caib.portafib.jpa.validator.BitacolaValidator;

import es.caib.portafib.back.form.webdb.BitacolaForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class BitacolaWebValidator  implements Validator, BitacolaFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected BitacolaValidator<Object> validator = new BitacolaValidator<Object>();

  // EJB's
  @javax.ejb.EJB(mappedName = "portafib/BitacolaEJB/local")
  protected es.caib.portafib.ejb.BitacolaLocal bitacolaEjb;



  public BitacolaWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return BitacolaForm.class.isAssignableFrom(clazz);
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
      isNou, bitacolaEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public BitacolaValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(BitacolaValidator<Object> validator) {
    this.validator = validator;
  }

}