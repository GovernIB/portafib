package es.caib.portafib.back.validator.webdb;

import org.apache.log4j.Logger;

import javax.ejb.EJB;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import es.caib.portafib.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.caib.portafib.jpa.validator.EstatDeFirmaValidator;

import es.caib.portafib.back.form.webdb.EstatDeFirmaForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class EstatDeFirmaWebValidator  implements Validator, EstatDeFirmaFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected EstatDeFirmaValidator<Object> validator = new EstatDeFirmaValidator<Object>();

  // EJB's
  @EJB(mappedName = es.caib.portafib.ejb.ColaboracioDelegacioLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.ColaboracioDelegacioLocal colaboracioDelegacioEjb;

  @EJB(mappedName = es.caib.portafib.ejb.EstatDeFirmaLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.EstatDeFirmaLocal estatDeFirmaEjb;

  @EJB(mappedName = es.caib.portafib.ejb.FirmaLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.FirmaLocal firmaEjb;

  @EJB(mappedName = es.caib.portafib.ejb.UsuariEntitatLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.UsuariEntitatLocal usuariEntitatEjb;



  public EstatDeFirmaWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return EstatDeFirmaForm.class.isAssignableFrom(clazz);
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
      isNou, colaboracioDelegacioEjb, estatDeFirmaEjb, firmaEjb, usuariEntitatEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public EstatDeFirmaValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(EstatDeFirmaValidator<Object> validator) {
    this.validator = validator;
  }

}