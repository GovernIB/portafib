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
import es.caib.portafib.persistence.validator.CodiBarresValidator;

import es.caib.portafib.back.form.webdb.CodiBarresForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.portafib.model.entity.CodiBarres;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class CodiBarresWebValidator extends AbstractWebValidator<CodiBarresForm, CodiBarres>
     implements Validator, CodiBarresFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected CodiBarresValidator<CodiBarres> validator = new CodiBarresValidator<CodiBarres>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.CodiBarresService.JNDI_NAME)
  protected es.caib.portafib.ejb.CodiBarresService codiBarresEjb;



  public CodiBarresWebValidator() {
    super();    
  }
  
  @Override
  public CodiBarres getBeanOfForm(CodiBarresForm form) {
    return  form.getCodiBarres();
  }

  @Override
  public Class<CodiBarresForm> getClassOfForm() {
    return CodiBarresForm.class;
  }

  @Override
  public void validate(CodiBarresForm __form, CodiBarres __bean, Errors errors) {

    WebValidationResult<CodiBarresForm> wvr;
    wvr = new WebValidationResult<CodiBarresForm>(errors);

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


  public void validate(CodiBarresForm __form, CodiBarres __bean, Errors errors,
    WebValidationResult<CodiBarresForm> wvr, boolean isNou) {

    BeanValidatorResult<CodiBarres> __vr = new BeanValidatorResult<CodiBarres>();
    validator.validate(__vr, __bean,
      isNou, codiBarresEjb);

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

  public CodiBarresValidator<CodiBarres> getValidator() {
    return validator;
  }

  public void setValidator(CodiBarresValidator<CodiBarres> validator) {
    this.validator = validator;
  }

}