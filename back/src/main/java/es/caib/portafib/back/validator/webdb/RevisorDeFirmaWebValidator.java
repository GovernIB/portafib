package es.caib.portafib.back.validator.webdb;

import org.apache.log4j.Logger;

import javax.ejb.EJB;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import es.caib.portafib.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.caib.portafib.jpa.validator.RevisorDeFirmaValidator;

import es.caib.portafib.back.form.webdb.RevisorDeFirmaForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class RevisorDeFirmaWebValidator  implements Validator, RevisorDeFirmaFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected RevisorDeFirmaValidator<Object> validator = new RevisorDeFirmaValidator<Object>();

  // EJB's
  @EJB(mappedName = es.caib.portafib.ejb.FirmaLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.FirmaLocal firmaEjb;

  @EJB(mappedName = es.caib.portafib.ejb.RevisorDeFirmaLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.RevisorDeFirmaLocal revisorDeFirmaEjb;

  @EJB(mappedName = es.caib.portafib.ejb.UsuariEntitatLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.UsuariEntitatLocal usuariEntitatEjb;



  public RevisorDeFirmaWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return RevisorDeFirmaForm.class.isAssignableFrom(clazz);
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
      isNou, firmaEjb, revisorDeFirmaEjb, usuariEntitatEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public RevisorDeFirmaValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(RevisorDeFirmaValidator<Object> validator) {
    this.validator = validator;
  }

}