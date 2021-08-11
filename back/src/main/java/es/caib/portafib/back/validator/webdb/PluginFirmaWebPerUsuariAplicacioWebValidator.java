package es.caib.portafib.back.validator.webdb;

import org.apache.log4j.Logger;

import javax.ejb.EJB;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import es.caib.portafib.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.caib.portafib.jpa.validator.PluginFirmaWebPerUsuariAplicacioValidator;

import es.caib.portafib.back.form.webdb.PluginFirmaWebPerUsuariAplicacioForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class PluginFirmaWebPerUsuariAplicacioWebValidator  implements Validator, PluginFirmaWebPerUsuariAplicacioFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected PluginFirmaWebPerUsuariAplicacioValidator<Object> validator = new PluginFirmaWebPerUsuariAplicacioValidator<Object>();

  // EJB's
  @EJB(mappedName = es.caib.portafib.ejb.PluginLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.PluginLocal pluginEjb;

  @EJB(mappedName = es.caib.portafib.ejb.PluginFirmaWebPerUsuariAplicacioLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.PluginFirmaWebPerUsuariAplicacioLocal pluginFirmaWebPerUsuariAplicacioEjb;

  @EJB(mappedName = es.caib.portafib.ejb.UsuariAplicacioLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.UsuariAplicacioLocal usuariAplicacioEjb;



  public PluginFirmaWebPerUsuariAplicacioWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return PluginFirmaWebPerUsuariAplicacioForm.class.isAssignableFrom(clazz);
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
      isNou, pluginEjb, pluginFirmaWebPerUsuariAplicacioEjb, usuariAplicacioEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public PluginFirmaWebPerUsuariAplicacioValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(PluginFirmaWebPerUsuariAplicacioValidator<Object> validator) {
    this.validator = validator;
  }

}