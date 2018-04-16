package es.caib.portafib.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import es.caib.portafib.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.caib.portafib.jpa.validator.PluginFirmaWebPerUsuariEntitatValidator;

import es.caib.portafib.back.form.webdb.PluginFirmaWebPerUsuariEntitatForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class PluginFirmaWebPerUsuariEntitatWebValidator  implements Validator, PluginFirmaWebPerUsuariEntitatFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected PluginFirmaWebPerUsuariEntitatValidator<Object> validator = new PluginFirmaWebPerUsuariEntitatValidator<Object>();

  // EJB's
  @javax.ejb.EJB(mappedName = "portafib/PluginEJB/local")
  protected es.caib.portafib.ejb.PluginLocal pluginEjb;

  @javax.ejb.EJB(mappedName = "portafib/PluginFirmaWebPerUsuariEntitatEJB/local")
  protected es.caib.portafib.ejb.PluginFirmaWebPerUsuariEntitatLocal pluginFirmaWebPerUsuariEntitatEjb;

  @javax.ejb.EJB(mappedName = "portafib/UsuariEntitatEJB/local")
  protected es.caib.portafib.ejb.UsuariEntitatLocal usuariEntitatEjb;



  public PluginFirmaWebPerUsuariEntitatWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return PluginFirmaWebPerUsuariEntitatForm.class.isAssignableFrom(clazz);
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
      isNou, pluginEjb, pluginFirmaWebPerUsuariEntitatEjb, usuariEntitatEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public PluginFirmaWebPerUsuariEntitatValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(PluginFirmaWebPerUsuariEntitatValidator<Object> validator) {
    this.validator = validator;
  }

}