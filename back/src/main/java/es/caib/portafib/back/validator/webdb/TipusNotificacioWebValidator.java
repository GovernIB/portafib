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
import es.caib.portafib.persistence.validator.TipusNotificacioValidator;

import es.caib.portafib.back.form.webdb.TipusNotificacioForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.portafib.model.entity.TipusNotificacio;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class TipusNotificacioWebValidator extends AbstractWebValidator<TipusNotificacioForm, TipusNotificacio>
     implements Validator, TipusNotificacioFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected TipusNotificacioValidator<TipusNotificacio> validator = new TipusNotificacioValidator<TipusNotificacio>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.TipusNotificacioService.JNDI_NAME)
  protected es.caib.portafib.ejb.TipusNotificacioService tipusNotificacioEjb;



  public TipusNotificacioWebValidator() {
    super();    
  }
  
  @Override
  public TipusNotificacio getBeanOfForm(TipusNotificacioForm form) {
    return  form.getTipusNotificacio();
  }

  @Override
  public Class<TipusNotificacioForm> getClassOfForm() {
    return TipusNotificacioForm.class;
  }

  @Override
  public void validate(TipusNotificacioForm __form, TipusNotificacio __bean, Errors errors) {

    WebValidationResult<TipusNotificacioForm> wvr;
    wvr = new WebValidationResult<TipusNotificacioForm>(errors);

    boolean isNou;
    {
        Object objNou = errors.getFieldValue("nou");
        if (objNou == null) {
            isNou = false;
        } else { 
         Boolean nou = Boolean.parseBoolean((String)objNou);
         isNou =  nou != null && nou.booleanValue();
        }
    }

    validate(__form, __bean , errors, wvr, isNou);
  }


  public void validate(TipusNotificacioForm __form, TipusNotificacio __bean, Errors errors,
    WebValidationResult<TipusNotificacioForm> wvr, boolean isNou) {

    BeanValidatorResult<TipusNotificacio> __vr = new BeanValidatorResult<TipusNotificacio>();
    validator.validate(__vr, __bean,
      isNou, tipusNotificacioEjb);

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

  public TipusNotificacioValidator<TipusNotificacio> getValidator() {
    return validator;
  }

  public void setValidator(TipusNotificacioValidator<TipusNotificacio> validator) {
    this.validator = validator;
  }

}