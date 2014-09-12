package es.caib.portafib.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import es.caib.portafib.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.caib.portafib.jpa.validator.GrupEntitatUsuariEntitatValidator;

import es.caib.portafib.back.form.webdb.GrupEntitatUsuariEntitatForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class GrupEntitatUsuariEntitatWebValidator  implements Validator, GrupEntitatUsuariEntitatFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected GrupEntitatUsuariEntitatValidator<Object> validator = new GrupEntitatUsuariEntitatValidator<Object>();

  // EJB's
  @javax.ejb.EJB(mappedName = "portafib/GrupEntitatEJB/local")
  protected es.caib.portafib.ejb.GrupEntitatLocal grupEntitatEjb;

  @javax.ejb.EJB(mappedName = "portafib/GrupEntitatUsuariEntitatEJB/local")
  protected es.caib.portafib.ejb.GrupEntitatUsuariEntitatLocal grupEntitatUsuariEntitatEjb;

  @javax.ejb.EJB(mappedName = "portafib/UsuariEntitatEJB/local")
  protected es.caib.portafib.ejb.UsuariEntitatLocal usuariEntitatEjb;



  public GrupEntitatUsuariEntitatWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return GrupEntitatUsuariEntitatForm.class.isAssignableFrom(clazz);
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
      isNou, grupEntitatEjb, grupEntitatUsuariEntitatEjb, usuariEntitatEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public GrupEntitatUsuariEntitatValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(GrupEntitatUsuariEntitatValidator<Object> validator) {
    this.validator = validator;
  }

}