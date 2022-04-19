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
import es.caib.portafib.persistence.validator.BlocDeFirmesValidator;

import es.caib.portafib.back.form.webdb.BlocDeFirmesForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.portafib.model.entity.BlocDeFirmes;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class BlocDeFirmesWebValidator extends AbstractWebValidator<BlocDeFirmesForm, BlocDeFirmes>
     implements Validator, BlocDeFirmesFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected BlocDeFirmesValidator<BlocDeFirmes> validator = new BlocDeFirmesValidator<BlocDeFirmes>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.BlocDeFirmesService.JNDI_NAME)
  protected es.caib.portafib.ejb.BlocDeFirmesService blocDeFirmesEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.FluxDeFirmesService.JNDI_NAME)
  protected es.caib.portafib.ejb.FluxDeFirmesService fluxDeFirmesEjb;



  public BlocDeFirmesWebValidator() {
    super();    
  }
  
  @Override
  public BlocDeFirmes getBeanOfForm(BlocDeFirmesForm form) {
    return  form.getBlocDeFirmes();
  }

  @Override
  public Class<BlocDeFirmesForm> getClassOfForm() {
    return BlocDeFirmesForm.class;
  }

  @Override
  public void validate(BlocDeFirmesForm __form, BlocDeFirmes __bean, Errors errors) {

    WebValidationResult<BlocDeFirmesForm> wvr;
    wvr = new WebValidationResult<BlocDeFirmesForm>(errors);

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


  public void validate(BlocDeFirmesForm __form, BlocDeFirmes __bean, Errors errors,
    WebValidationResult<BlocDeFirmesForm> wvr, boolean isNou) {

    BeanValidatorResult<BlocDeFirmes> __vr = new BeanValidatorResult<BlocDeFirmes>();
    validator.validate(__vr, __bean,
      isNou, blocDeFirmesEjb, fluxDeFirmesEjb);

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

  public BlocDeFirmesValidator<BlocDeFirmes> getValidator() {
    return validator;
  }

  public void setValidator(BlocDeFirmesValidator<BlocDeFirmes> validator) {
    this.validator = validator;
  }

}