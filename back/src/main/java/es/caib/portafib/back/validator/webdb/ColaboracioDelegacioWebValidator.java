package es.caib.portafib.back.validator.webdb;

import org.apache.log4j.Logger;

import javax.ejb.EJB;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import es.caib.portafib.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.caib.portafib.jpa.validator.ColaboracioDelegacioValidator;

import es.caib.portafib.back.form.webdb.ColaboracioDelegacioForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class ColaboracioDelegacioWebValidator  implements Validator, ColaboracioDelegacioFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected ColaboracioDelegacioValidator<Object> validator = new ColaboracioDelegacioValidator<Object>();

  // EJB's
  @EJB(mappedName = es.caib.portafib.ejb.ColaboracioDelegacioLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.ColaboracioDelegacioLocal colaboracioDelegacioEjb;

  @EJB(mappedName = es.caib.portafib.ejb.UsuariEntitatLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.UsuariEntitatLocal usuariEntitatEjb;



  public ColaboracioDelegacioWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return ColaboracioDelegacioForm.class.isAssignableFrom(clazz);
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

    if (isNou) { // Creacio
      // ================ CREATION
      // Fitxers 
    }
    validator.validate(wvr, target,
      isNou, colaboracioDelegacioEjb, usuariEntitatEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public ColaboracioDelegacioValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(ColaboracioDelegacioValidator<Object> validator) {
    this.validator = validator;
  }

}