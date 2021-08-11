package es.caib.portafib.back.validator.webdb;

import org.apache.log4j.Logger;

import javax.ejb.EJB;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import es.caib.portafib.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.caib.portafib.jpa.validator.RoleUsuariEntitatValidator;

import es.caib.portafib.back.form.webdb.RoleUsuariEntitatForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class RoleUsuariEntitatWebValidator  implements Validator, RoleUsuariEntitatFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected RoleUsuariEntitatValidator<Object> validator = new RoleUsuariEntitatValidator<Object>();

  // EJB's
  @EJB(mappedName = es.caib.portafib.ejb.RoleLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.RoleLocal roleEjb;

  @EJB(mappedName = es.caib.portafib.ejb.RoleUsuariEntitatLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.RoleUsuariEntitatLocal roleUsuariEntitatEjb;

  @EJB(mappedName = es.caib.portafib.ejb.UsuariEntitatLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.UsuariEntitatLocal usuariEntitatEjb;



  public RoleUsuariEntitatWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return RoleUsuariEntitatForm.class.isAssignableFrom(clazz);
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
      isNou, roleEjb, roleUsuariEntitatEjb, usuariEntitatEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public RoleUsuariEntitatValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(RoleUsuariEntitatValidator<Object> validator) {
    this.validator = validator;
  }

}