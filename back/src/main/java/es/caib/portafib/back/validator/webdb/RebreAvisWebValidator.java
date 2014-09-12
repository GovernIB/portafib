package es.caib.portafib.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import es.caib.portafib.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.caib.portafib.jpa.validator.RebreAvisValidator;

import es.caib.portafib.back.form.webdb.RebreAvisForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class RebreAvisWebValidator  implements Validator, RebreAvisFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected RebreAvisValidator<Object> validator = new RebreAvisValidator<Object>();

  // EJB's
  @javax.ejb.EJB(mappedName = "portafib/RebreAvisEJB/local")
  protected es.caib.portafib.ejb.RebreAvisLocal rebreAvisEjb;

  @javax.ejb.EJB(mappedName = "portafib/TipusNotificacioEJB/local")
  protected es.caib.portafib.ejb.TipusNotificacioLocal tipusNotificacioEjb;

  @javax.ejb.EJB(mappedName = "portafib/UsuariEntitatEJB/local")
  protected es.caib.portafib.ejb.UsuariEntitatLocal usuariEntitatEjb;



  public RebreAvisWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return RebreAvisForm.class.isAssignableFrom(clazz);
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
      isNou, rebreAvisEjb, tipusNotificacioEjb, usuariEntitatEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public RebreAvisValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(RebreAvisValidator<Object> validator) {
    this.validator = validator;
  }

}