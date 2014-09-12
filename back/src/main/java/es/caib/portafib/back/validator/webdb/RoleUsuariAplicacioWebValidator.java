package es.caib.portafib.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import es.caib.portafib.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.caib.portafib.jpa.validator.RoleUsuariAplicacioValidator;

import es.caib.portafib.back.form.webdb.RoleUsuariAplicacioForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class RoleUsuariAplicacioWebValidator  implements Validator, RoleUsuariAplicacioFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected RoleUsuariAplicacioValidator<Object> validator = new RoleUsuariAplicacioValidator<Object>();

  // EJB's
  @javax.ejb.EJB(mappedName = "portafib/RoleEJB/local")
  protected es.caib.portafib.ejb.RoleLocal roleEjb;

  @javax.ejb.EJB(mappedName = "portafib/RoleUsuariAplicacioEJB/local")
  protected es.caib.portafib.ejb.RoleUsuariAplicacioLocal roleUsuariAplicacioEjb;

  @javax.ejb.EJB(mappedName = "portafib/UsuariAplicacioEJB/local")
  protected es.caib.portafib.ejb.UsuariAplicacioLocal usuariAplicacioEjb;



  public RoleUsuariAplicacioWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return RoleUsuariAplicacioForm.class.isAssignableFrom(clazz);
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
      isNou, roleEjb, roleUsuariAplicacioEjb, usuariAplicacioEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public RoleUsuariAplicacioValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(RoleUsuariAplicacioValidator<Object> validator) {
    this.validator = validator;
  }

}