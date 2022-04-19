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
import es.caib.portafib.persistence.validator.PluginFirmaWebPerUsuariEntitatValidator;

import es.caib.portafib.back.form.webdb.PluginFirmaWebPerUsuariEntitatForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.portafib.model.entity.PluginFirmaWebPerUsuariEntitat;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class PluginFirmaWebPerUsuariEntitatWebValidator extends AbstractWebValidator<PluginFirmaWebPerUsuariEntitatForm, PluginFirmaWebPerUsuariEntitat>
     implements Validator, PluginFirmaWebPerUsuariEntitatFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected PluginFirmaWebPerUsuariEntitatValidator<PluginFirmaWebPerUsuariEntitat> validator = new PluginFirmaWebPerUsuariEntitatValidator<PluginFirmaWebPerUsuariEntitat>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.PluginService.JNDI_NAME)
  protected es.caib.portafib.ejb.PluginService pluginEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.PluginFirmaWebPerUsuariEntitatService.JNDI_NAME)
  protected es.caib.portafib.ejb.PluginFirmaWebPerUsuariEntitatService pluginFirmaWebPerUsuariEntitatEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.UsuariEntitatService.JNDI_NAME)
  protected es.caib.portafib.ejb.UsuariEntitatService usuariEntitatEjb;



  public PluginFirmaWebPerUsuariEntitatWebValidator() {
    super();    
  }
  
  @Override
  public PluginFirmaWebPerUsuariEntitat getBeanOfForm(PluginFirmaWebPerUsuariEntitatForm form) {
    return  form.getPluginFirmaWebPerUsuariEntitat();
  }

  @Override
  public Class<PluginFirmaWebPerUsuariEntitatForm> getClassOfForm() {
    return PluginFirmaWebPerUsuariEntitatForm.class;
  }

  @Override
  public void validate(PluginFirmaWebPerUsuariEntitatForm __form, PluginFirmaWebPerUsuariEntitat __bean, Errors errors) {

    WebValidationResult<PluginFirmaWebPerUsuariEntitatForm> wvr;
    wvr = new WebValidationResult<PluginFirmaWebPerUsuariEntitatForm>(errors);

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


  public void validate(PluginFirmaWebPerUsuariEntitatForm __form, PluginFirmaWebPerUsuariEntitat __bean, Errors errors,
    WebValidationResult<PluginFirmaWebPerUsuariEntitatForm> wvr, boolean isNou) {

    BeanValidatorResult<PluginFirmaWebPerUsuariEntitat> __vr = new BeanValidatorResult<PluginFirmaWebPerUsuariEntitat>();
    validator.validate(__vr, __bean,
      isNou, pluginEjb, pluginFirmaWebPerUsuariEntitatEjb, usuariEntitatEjb);

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

  public PluginFirmaWebPerUsuariEntitatValidator<PluginFirmaWebPerUsuariEntitat> getValidator() {
    return validator;
  }

  public void setValidator(PluginFirmaWebPerUsuariEntitatValidator<PluginFirmaWebPerUsuariEntitat> validator) {
    this.validator = validator;
  }

}