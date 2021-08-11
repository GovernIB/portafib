package es.caib.portafib.back.validator.webdb;

import org.apache.log4j.Logger;

import javax.ejb.EJB;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import es.caib.portafib.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.caib.portafib.jpa.validator.PerfilsPerUsuariAplicacioValidator;

import es.caib.portafib.back.form.webdb.PerfilsPerUsuariAplicacioForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class PerfilsPerUsuariAplicacioWebValidator  implements Validator, PerfilsPerUsuariAplicacioFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected PerfilsPerUsuariAplicacioValidator<Object> validator = new PerfilsPerUsuariAplicacioValidator<Object>();

  // EJB's
  @EJB(mappedName = es.caib.portafib.ejb.PerfilDeFirmaLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.PerfilDeFirmaLocal perfilDeFirmaEjb;

  @EJB(mappedName = es.caib.portafib.ejb.PerfilsPerUsuariAplicacioLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.PerfilsPerUsuariAplicacioLocal perfilsPerUsuariAplicacioEjb;

  @EJB(mappedName = es.caib.portafib.ejb.UsuariAplicacioLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.UsuariAplicacioLocal usuariAplicacioEjb;



  public PerfilsPerUsuariAplicacioWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return PerfilsPerUsuariAplicacioForm.class.isAssignableFrom(clazz);
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
      isNou, perfilDeFirmaEjb, perfilsPerUsuariAplicacioEjb, usuariAplicacioEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public PerfilsPerUsuariAplicacioValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(PerfilsPerUsuariAplicacioValidator<Object> validator) {
    this.validator = validator;
  }

}