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
import es.caib.portafib.persistence.validator.ColaboracioDelegacioValidator;

import es.caib.portafib.back.form.webdb.ColaboracioDelegacioForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.portafib.model.entity.ColaboracioDelegacio;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class ColaboracioDelegacioWebValidator extends AbstractWebValidator<ColaboracioDelegacioForm, ColaboracioDelegacio>
     implements Validator, ColaboracioDelegacioFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected ColaboracioDelegacioValidator<ColaboracioDelegacio> validator = new ColaboracioDelegacioValidator<ColaboracioDelegacio>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.ColaboracioDelegacioService.JNDI_NAME)
  protected es.caib.portafib.ejb.ColaboracioDelegacioService colaboracioDelegacioEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.UsuariEntitatService.JNDI_NAME)
  protected es.caib.portafib.ejb.UsuariEntitatService usuariEntitatEjb;



  public ColaboracioDelegacioWebValidator() {
    super();    
  }
  
  @Override
  public ColaboracioDelegacio getBeanOfForm(ColaboracioDelegacioForm form) {
    return  form.getColaboracioDelegacio();
  }

  @Override
  public Class<ColaboracioDelegacioForm> getClassOfForm() {
    return ColaboracioDelegacioForm.class;
  }

  @Override
  public void validate(ColaboracioDelegacioForm __form, ColaboracioDelegacio __bean, Errors errors) {

    WebValidationResult<ColaboracioDelegacioForm> wvr;
    wvr = new WebValidationResult<ColaboracioDelegacioForm>(errors);

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


  public void validate(ColaboracioDelegacioForm __form, ColaboracioDelegacio __bean, Errors errors,
    WebValidationResult<ColaboracioDelegacioForm> wvr, boolean isNou) {

    if (isNou) { // Creacio
      // ================ CREATION
      // Fitxers 
    }
    BeanValidatorResult<ColaboracioDelegacio> __vr = new BeanValidatorResult<ColaboracioDelegacio>();
    validator.validate(__vr, __bean,
      isNou, colaboracioDelegacioEjb, usuariEntitatEjb);

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

  public ColaboracioDelegacioValidator<ColaboracioDelegacio> getValidator() {
    return validator;
  }

  public void setValidator(ColaboracioDelegacioValidator<ColaboracioDelegacio> validator) {
    this.validator = validator;
  }

}