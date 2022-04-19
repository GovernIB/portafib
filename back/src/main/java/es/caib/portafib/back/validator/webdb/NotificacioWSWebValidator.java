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
import es.caib.portafib.persistence.validator.NotificacioWSValidator;

import es.caib.portafib.back.form.webdb.NotificacioWSForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.portafib.model.entity.NotificacioWS;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class NotificacioWSWebValidator extends AbstractWebValidator<NotificacioWSForm, NotificacioWS>
     implements Validator, NotificacioWSFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected NotificacioWSValidator<NotificacioWS> validator = new NotificacioWSValidator<NotificacioWS>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.NotificacioWSService.JNDI_NAME)
  protected es.caib.portafib.ejb.NotificacioWSService notificacioWSEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.TipusNotificacioService.JNDI_NAME)
  protected es.caib.portafib.ejb.TipusNotificacioService tipusNotificacioEjb;



  public NotificacioWSWebValidator() {
    super();    
  }
  
  @Override
  public NotificacioWS getBeanOfForm(NotificacioWSForm form) {
    return  form.getNotificacioWS();
  }

  @Override
  public Class<NotificacioWSForm> getClassOfForm() {
    return NotificacioWSForm.class;
  }

  @Override
  public void validate(NotificacioWSForm __form, NotificacioWS __bean, Errors errors) {

    WebValidationResult<NotificacioWSForm> wvr;
    wvr = new WebValidationResult<NotificacioWSForm>(errors);

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


  public void validate(NotificacioWSForm __form, NotificacioWS __bean, Errors errors,
    WebValidationResult<NotificacioWSForm> wvr, boolean isNou) {

    BeanValidatorResult<NotificacioWS> __vr = new BeanValidatorResult<NotificacioWS>();
    validator.validate(__vr, __bean,
      isNou, notificacioWSEjb, tipusNotificacioEjb);

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

  public NotificacioWSValidator<NotificacioWS> getValidator() {
    return validator;
  }

  public void setValidator(NotificacioWSValidator<NotificacioWS> validator) {
    this.validator = validator;
  }

}