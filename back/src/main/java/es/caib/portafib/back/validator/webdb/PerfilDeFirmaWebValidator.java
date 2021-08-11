package es.caib.portafib.back.validator.webdb;

import org.apache.log4j.Logger;

import javax.ejb.EJB;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import es.caib.portafib.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.caib.portafib.jpa.validator.PerfilDeFirmaValidator;

import es.caib.portafib.back.form.webdb.PerfilDeFirmaForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class PerfilDeFirmaWebValidator  implements Validator, PerfilDeFirmaFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected PerfilDeFirmaValidator<Object> validator = new PerfilDeFirmaValidator<Object>();

  // EJB's
  @EJB(mappedName = es.caib.portafib.ejb.PerfilDeFirmaLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.PerfilDeFirmaLocal perfilDeFirmaEjb;

  @EJB(mappedName = es.caib.portafib.ejb.UsuariAplicacioConfiguracioLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.UsuariAplicacioConfiguracioLocal usuariAplicacioConfiguracioEjb;



  public PerfilDeFirmaWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return PerfilDeFirmaForm.class.isAssignableFrom(clazz);
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
      isNou, perfilDeFirmaEjb, usuariAplicacioConfiguracioEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public PerfilDeFirmaValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(PerfilDeFirmaValidator<Object> validator) {
    this.validator = validator;
  }

}