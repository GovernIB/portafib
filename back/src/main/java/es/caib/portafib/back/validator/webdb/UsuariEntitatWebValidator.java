package es.caib.portafib.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import es.caib.portafib.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.caib.portafib.jpa.validator.UsuariEntitatValidator;

import es.caib.portafib.back.form.webdb.UsuariEntitatForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class UsuariEntitatWebValidator  implements Validator, UsuariEntitatFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected UsuariEntitatValidator<Object> validator = new UsuariEntitatValidator<Object>();

  // EJB's
  @javax.ejb.EJB(mappedName = "portafib/CustodiaInfoEJB/local")
  protected es.caib.portafib.ejb.CustodiaInfoLocal custodiaInfoEjb;

  @javax.ejb.EJB(mappedName = "portafib/EntitatEJB/local")
  protected es.caib.portafib.ejb.EntitatLocal entitatEjb;

  @javax.ejb.EJB(mappedName = "portafib/UsuariEntitatEJB/local")
  protected es.caib.portafib.ejb.UsuariEntitatLocal usuariEntitatEjb;

  @javax.ejb.EJB(mappedName = "portafib/UsuariPersonaEJB/local")
  protected es.caib.portafib.ejb.UsuariPersonaLocal usuariPersonaEjb;



  public UsuariEntitatWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return UsuariEntitatForm.class.isAssignableFrom(clazz);
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
      isNou, custodiaInfoEjb, entitatEjb, usuariEntitatEjb, usuariPersonaEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public UsuariEntitatValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(UsuariEntitatValidator<Object> validator) {
    this.validator = validator;
  }

}