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
import es.caib.portafib.persistence.validator.FluxDeFirmesValidator;

import es.caib.portafib.back.form.webdb.FluxDeFirmesForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.portafib.model.entity.FluxDeFirmes;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class FluxDeFirmesWebValidator extends AbstractWebValidator<FluxDeFirmesForm, FluxDeFirmes>
     implements Validator, FluxDeFirmesFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected FluxDeFirmesValidator<FluxDeFirmes> validator = new FluxDeFirmesValidator<FluxDeFirmes>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.FluxDeFirmesService.JNDI_NAME)
  protected es.caib.portafib.ejb.FluxDeFirmesService fluxDeFirmesEjb;



  public FluxDeFirmesWebValidator() {
    super();    
  }
  
  @Override
  public FluxDeFirmes getBeanOfForm(FluxDeFirmesForm form) {
    return  form.getFluxDeFirmes();
  }

  @Override
  public Class<FluxDeFirmesForm> getClassOfForm() {
    return FluxDeFirmesForm.class;
  }

  @Override
  public void validate(FluxDeFirmesForm __form, FluxDeFirmes __bean, Errors errors) {

    WebValidationResult<FluxDeFirmesForm> wvr;
    wvr = new WebValidationResult<FluxDeFirmesForm>(errors);

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


  public void validate(FluxDeFirmesForm __form, FluxDeFirmes __bean, Errors errors,
    WebValidationResult<FluxDeFirmesForm> wvr, boolean isNou) {

    BeanValidatorResult<FluxDeFirmes> __vr = new BeanValidatorResult<FluxDeFirmes>();
    validator.validate(__vr, __bean,
      isNou, fluxDeFirmesEjb);

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

  public FluxDeFirmesValidator<FluxDeFirmes> getValidator() {
    return validator;
  }

  public void setValidator(FluxDeFirmesValidator<FluxDeFirmes> validator) {
    this.validator = validator;
  }

}