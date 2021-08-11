package es.caib.portafib.back.validator.webdb;

import org.apache.log4j.Logger;

import javax.ejb.EJB;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import es.caib.portafib.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.caib.portafib.jpa.validator.CustodiaInfoValidator;

import es.caib.portafib.back.form.webdb.CustodiaInfoForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class CustodiaInfoWebValidator  implements Validator, CustodiaInfoFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected CustodiaInfoValidator<Object> validator = new CustodiaInfoValidator<Object>();

  // EJB's
  @EJB(mappedName = es.caib.portafib.ejb.CodiBarresLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.CodiBarresLocal codiBarresEjb;

  @EJB(mappedName = es.caib.portafib.ejb.CustodiaInfoLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.CustodiaInfoLocal custodiaInfoEjb;

  @EJB(mappedName = es.caib.portafib.ejb.EntitatLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.EntitatLocal entitatEjb;

  @EJB(mappedName = es.caib.portafib.ejb.PluginLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.PluginLocal pluginEjb;

  @EJB(mappedName = es.caib.portafib.ejb.UsuariAplicacioLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.UsuariAplicacioLocal usuariAplicacioEjb;

  @EJB(mappedName = es.caib.portafib.ejb.UsuariEntitatLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.UsuariEntitatLocal usuariEntitatEjb;



  public CustodiaInfoWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return CustodiaInfoForm.class.isAssignableFrom(clazz);
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
      isNou, codiBarresEjb, custodiaInfoEjb, entitatEjb, pluginEjb, usuariAplicacioEjb, usuariEntitatEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public CustodiaInfoValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(CustodiaInfoValidator<Object> validator) {
    this.validator = validator;
  }

}