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
import es.caib.portafib.persistence.validator.TipusDocumentColaboracioDelegacioValidator;

import es.caib.portafib.back.form.webdb.TipusDocumentColaboracioDelegacioForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.portafib.model.entity.TipusDocumentColaboracioDelegacio;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class TipusDocumentColaboracioDelegacioWebValidator extends AbstractWebValidator<TipusDocumentColaboracioDelegacioForm, TipusDocumentColaboracioDelegacio>
     implements Validator, TipusDocumentColaboracioDelegacioFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected TipusDocumentColaboracioDelegacioValidator<TipusDocumentColaboracioDelegacio> validator = new TipusDocumentColaboracioDelegacioValidator<TipusDocumentColaboracioDelegacio>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.ColaboracioDelegacioService.JNDI_NAME)
  protected es.caib.portafib.ejb.ColaboracioDelegacioService colaboracioDelegacioEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.TipusDocumentService.JNDI_NAME)
  protected es.caib.portafib.ejb.TipusDocumentService tipusDocumentEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.TipusDocumentColaboracioDelegacioService.JNDI_NAME)
  protected es.caib.portafib.ejb.TipusDocumentColaboracioDelegacioService tipusDocumentColaboracioDelegacioEjb;



  public TipusDocumentColaboracioDelegacioWebValidator() {
    super();    
  }
  
  @Override
  public TipusDocumentColaboracioDelegacio getBeanOfForm(TipusDocumentColaboracioDelegacioForm form) {
    return  form.getTipusDocumentColaboracioDelegacio();
  }

  @Override
  public Class<TipusDocumentColaboracioDelegacioForm> getClassOfForm() {
    return TipusDocumentColaboracioDelegacioForm.class;
  }

  @Override
  public void validate(TipusDocumentColaboracioDelegacioForm __form, TipusDocumentColaboracioDelegacio __bean, Errors errors) {

    WebValidationResult<TipusDocumentColaboracioDelegacioForm> wvr;
    wvr = new WebValidationResult<TipusDocumentColaboracioDelegacioForm>(errors);

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


  public void validate(TipusDocumentColaboracioDelegacioForm __form, TipusDocumentColaboracioDelegacio __bean, Errors errors,
    WebValidationResult<TipusDocumentColaboracioDelegacioForm> wvr, boolean isNou) {

    BeanValidatorResult<TipusDocumentColaboracioDelegacio> __vr = new BeanValidatorResult<TipusDocumentColaboracioDelegacio>();
    validator.validate(__vr, __bean,
      isNou, colaboracioDelegacioEjb, tipusDocumentEjb, tipusDocumentColaboracioDelegacioEjb);

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

  public TipusDocumentColaboracioDelegacioValidator<TipusDocumentColaboracioDelegacio> getValidator() {
    return validator;
  }

  public void setValidator(TipusDocumentColaboracioDelegacioValidator<TipusDocumentColaboracioDelegacio> validator) {
    this.validator = validator;
  }

}