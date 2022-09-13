package es.caib.portafib.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.validation.BeanValidatorResult;
import org.fundaciobit.genapp.common.i18n.I18NFieldError;
import java.util.List;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import es.caib.portafib.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.caib.portafib.persistence.validator.PluginCridadaValidator;

import es.caib.portafib.back.form.webdb.PluginCridadaForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.portafib.model.entity.PluginCridada;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class PluginCridadaWebValidator extends AbstractWebValidator<PluginCridadaForm, PluginCridada>
     implements Validator, PluginCridadaFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected PluginCridadaValidator<PluginCridada> validator = new PluginCridadaValidator<PluginCridada>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.EntitatService.JNDI_NAME)
  protected es.caib.portafib.ejb.EntitatService entitatEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.PluginService.JNDI_NAME)
  protected es.caib.portafib.ejb.PluginService pluginEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.PluginCridadaService.JNDI_NAME)
  protected es.caib.portafib.ejb.PluginCridadaService pluginCridadaEjb;



  public PluginCridadaWebValidator() {
    super();    
  }
  
  @Override
  public PluginCridada getBeanOfForm(PluginCridadaForm form) {
    return  form.getPluginCridada();
  }

  @Override
  public Class<PluginCridadaForm> getClassOfForm() {
    return PluginCridadaForm.class;
  }

  @Override
  public void validate(PluginCridadaForm __form, PluginCridada __bean, Errors errors) {

    WebValidationResult<PluginCridadaForm> wvr;
    wvr = new WebValidationResult<PluginCridadaForm>(errors);

    boolean isNou;
    {
        Object objNou = errors.getFieldValue("nou");
        if (objNou == null) {
            isNou = false;
        } else { 
         Boolean nou = Boolean.parseBoolean(String.valueOf(objNou));
         isNou =  nou != null && nou.booleanValue();
        }
    }

    validate(__form, __bean , errors, wvr, isNou);
  }


  public void validate(PluginCridadaForm __form, PluginCridada __bean, Errors errors,
    WebValidationResult<PluginCridadaForm> wvr, boolean isNou) {

    BeanValidatorResult<PluginCridada> __vr = new BeanValidatorResult<PluginCridada>();
    validator.validate(__vr, __bean,
      isNou, entitatEjb, pluginEjb, pluginCridadaEjb);

    if (__vr.hasErrors()) {
        List<I18NFieldError> vrErrors = __vr.getErrors();
    	   for (I18NFieldError i18nFieldError : vrErrors) {
    	       wvr.rejectValue(i18nFieldError.getField(), i18nFieldError.getTranslation().getCode(), i18nFieldError.getTranslation().getArgs());
        }
    }

    if (isNou) { // Creacio
      // ================ CREATION
      // Fitxers 
    }

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public PluginCridadaValidator<PluginCridada> getValidator() {
    return validator;
  }

  public void setValidator(PluginCridadaValidator<PluginCridada> validator) {
    this.validator = validator;
  }

}