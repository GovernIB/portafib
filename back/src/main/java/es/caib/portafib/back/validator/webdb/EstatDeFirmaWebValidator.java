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
import es.caib.portafib.persistence.validator.EstatDeFirmaValidator;

import es.caib.portafib.back.form.webdb.EstatDeFirmaForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.portafib.model.entity.EstatDeFirma;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class EstatDeFirmaWebValidator extends AbstractWebValidator<EstatDeFirmaForm, EstatDeFirma>
     implements Validator, EstatDeFirmaFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected EstatDeFirmaValidator<EstatDeFirma> validator = new EstatDeFirmaValidator<EstatDeFirma>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.ColaboracioDelegacioService.JNDI_NAME)
  protected es.caib.portafib.ejb.ColaboracioDelegacioService colaboracioDelegacioEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.EstatDeFirmaService.JNDI_NAME)
  protected es.caib.portafib.ejb.EstatDeFirmaService estatDeFirmaEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.FirmaService.JNDI_NAME)
  protected es.caib.portafib.ejb.FirmaService firmaEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.UsuariEntitatService.JNDI_NAME)
  protected es.caib.portafib.ejb.UsuariEntitatService usuariEntitatEjb;



  public EstatDeFirmaWebValidator() {
    super();    
  }
  
  @Override
  public EstatDeFirma getBeanOfForm(EstatDeFirmaForm form) {
    return  form.getEstatDeFirma();
  }

  @Override
  public Class<EstatDeFirmaForm> getClassOfForm() {
    return EstatDeFirmaForm.class;
  }

  @Override
  public void validate(EstatDeFirmaForm __form, EstatDeFirma __bean, Errors errors) {

    WebValidationResult<EstatDeFirmaForm> wvr;
    wvr = new WebValidationResult<EstatDeFirmaForm>(errors);

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


  public void validate(EstatDeFirmaForm __form, EstatDeFirma __bean, Errors errors,
    WebValidationResult<EstatDeFirmaForm> wvr, boolean isNou) {

    BeanValidatorResult<EstatDeFirma> __vr = new BeanValidatorResult<EstatDeFirma>();
    validator.validate(__vr, __bean,
      isNou, colaboracioDelegacioEjb, estatDeFirmaEjb, firmaEjb, usuariEntitatEjb);

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

  public EstatDeFirmaValidator<EstatDeFirma> getValidator() {
    return validator;
  }

  public void setValidator(EstatDeFirmaValidator<EstatDeFirma> validator) {
    this.validator = validator;
  }

}