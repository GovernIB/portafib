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
import es.caib.portafib.persistence.validator.PseudonimValidator;

import es.caib.portafib.back.form.webdb.PseudonimForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.portafib.model.entity.Pseudonim;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class PseudonimWebValidator extends AbstractWebValidator<PseudonimForm, Pseudonim>
     implements Validator, PseudonimFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected PseudonimValidator<Pseudonim> validator = new PseudonimValidator<Pseudonim>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.PseudonimService.JNDI_NAME)
  protected es.caib.portafib.ejb.PseudonimService pseudonimEjb;



  public PseudonimWebValidator() {
    super();    
  }
  
  @Override
  public Pseudonim getBeanOfForm(PseudonimForm form) {
    return  form.getPseudonim();
  }

  @Override
  public Class<PseudonimForm> getClassOfForm() {
    return PseudonimForm.class;
  }

  @Override
  public void validate(PseudonimForm __form, Pseudonim __bean, Errors errors) {

    WebValidationResult<PseudonimForm> wvr;
    wvr = new WebValidationResult<PseudonimForm>(errors);

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


  public void validate(PseudonimForm __form, Pseudonim __bean, Errors errors,
    WebValidationResult<PseudonimForm> wvr, boolean isNou) {

    BeanValidatorResult<Pseudonim> __vr = new BeanValidatorResult<Pseudonim>();
    validator.validate(__vr, __bean,
      isNou, pseudonimEjb);

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

  public PseudonimValidator<Pseudonim> getValidator() {
    return validator;
  }

  public void setValidator(PseudonimValidator<Pseudonim> validator) {
    this.validator = validator;
  }

}