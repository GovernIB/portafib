package es.caib.portafib.back.validator.webdb;

import org.apache.log4j.Logger;

import javax.ejb.EJB;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import es.caib.portafib.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.caib.portafib.jpa.validator.UsuariEntitatFavoritValidator;

import es.caib.portafib.back.form.webdb.UsuariEntitatFavoritForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class UsuariEntitatFavoritWebValidator  implements Validator, UsuariEntitatFavoritFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected UsuariEntitatFavoritValidator<Object> validator = new UsuariEntitatFavoritValidator<Object>();

  // EJB's
  @EJB(mappedName = es.caib.portafib.ejb.UsuariEntitatLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.UsuariEntitatLocal usuariEntitatEjb;

  @EJB(mappedName = es.caib.portafib.ejb.UsuariEntitatFavoritLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.UsuariEntitatFavoritLocal usuariEntitatFavoritEjb;



  public UsuariEntitatFavoritWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return UsuariEntitatFavoritForm.class.isAssignableFrom(clazz);
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
      isNou, usuariEntitatEjb, usuariEntitatFavoritEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public UsuariEntitatFavoritValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(UsuariEntitatFavoritValidator<Object> validator) {
    this.validator = validator;
  }

}