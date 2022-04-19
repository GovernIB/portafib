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
import es.caib.portafib.persistence.validator.PluginFirmaWebPerUsuariAplicacioValidator;

import es.caib.portafib.back.form.webdb.PluginFirmaWebPerUsuariAplicacioForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.portafib.model.entity.PluginFirmaWebPerUsuariAplicacio;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class PluginFirmaWebPerUsuariAplicacioWebValidator extends AbstractWebValidator<PluginFirmaWebPerUsuariAplicacioForm, PluginFirmaWebPerUsuariAplicacio>
     implements Validator, PluginFirmaWebPerUsuariAplicacioFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected PluginFirmaWebPerUsuariAplicacioValidator<PluginFirmaWebPerUsuariAplicacio> validator = new PluginFirmaWebPerUsuariAplicacioValidator<PluginFirmaWebPerUsuariAplicacio>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.PluginService.JNDI_NAME)
  protected es.caib.portafib.ejb.PluginService pluginEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.PluginFirmaWebPerUsuariAplicacioService.JNDI_NAME)
  protected es.caib.portafib.ejb.PluginFirmaWebPerUsuariAplicacioService pluginFirmaWebPerUsuariAplicacioEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.UsuariAplicacioService.JNDI_NAME)
  protected es.caib.portafib.ejb.UsuariAplicacioService usuariAplicacioEjb;



  public PluginFirmaWebPerUsuariAplicacioWebValidator() {
    super();    
  }
  
  @Override
  public PluginFirmaWebPerUsuariAplicacio getBeanOfForm(PluginFirmaWebPerUsuariAplicacioForm form) {
    return  form.getPluginFirmaWebPerUsuariAplicacio();
  }

  @Override
  public Class<PluginFirmaWebPerUsuariAplicacioForm> getClassOfForm() {
    return PluginFirmaWebPerUsuariAplicacioForm.class;
  }

  @Override
  public void validate(PluginFirmaWebPerUsuariAplicacioForm __form, PluginFirmaWebPerUsuariAplicacio __bean, Errors errors) {

    WebValidationResult<PluginFirmaWebPerUsuariAplicacioForm> wvr;
    wvr = new WebValidationResult<PluginFirmaWebPerUsuariAplicacioForm>(errors);

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


  public void validate(PluginFirmaWebPerUsuariAplicacioForm __form, PluginFirmaWebPerUsuariAplicacio __bean, Errors errors,
    WebValidationResult<PluginFirmaWebPerUsuariAplicacioForm> wvr, boolean isNou) {

    BeanValidatorResult<PluginFirmaWebPerUsuariAplicacio> __vr = new BeanValidatorResult<PluginFirmaWebPerUsuariAplicacio>();
    validator.validate(__vr, __bean,
      isNou, pluginEjb, pluginFirmaWebPerUsuariAplicacioEjb, usuariAplicacioEjb);

    if (__vr.hasErrors()) {
        List<I18NFieldError> vrErrors = __vr.getErrors();
    	   for (I18NFieldError i18nFieldError : vrErrors) {
    	       wvr.rejectValue(i18nFieldError.getField(), i18nFieldError.getTranslation().getCode(), i18nFieldError.getTranslation().getArgs());
        }
    }


  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public PluginFirmaWebPerUsuariAplicacioValidator<PluginFirmaWebPerUsuariAplicacio> getValidator() {
    return validator;
  }

  public void setValidator(PluginFirmaWebPerUsuariAplicacioValidator<PluginFirmaWebPerUsuariAplicacio> validator) {
    this.validator = validator;
  }

}