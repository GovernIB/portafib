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
import es.caib.portafib.persistence.validator.RevisorDeFirmaValidator;

import es.caib.portafib.back.form.webdb.RevisorDeFirmaForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.portafib.model.entity.RevisorDeFirma;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class RevisorDeFirmaWebValidator extends AbstractWebValidator<RevisorDeFirmaForm, RevisorDeFirma>
     implements Validator, RevisorDeFirmaFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected RevisorDeFirmaValidator<RevisorDeFirma> validator = new RevisorDeFirmaValidator<RevisorDeFirma>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.FirmaService.JNDI_NAME)
  protected es.caib.portafib.ejb.FirmaService firmaEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.RevisorDeFirmaService.JNDI_NAME)
  protected es.caib.portafib.ejb.RevisorDeFirmaService revisorDeFirmaEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.UsuariEntitatService.JNDI_NAME)
  protected es.caib.portafib.ejb.UsuariEntitatService usuariEntitatEjb;



  public RevisorDeFirmaWebValidator() {
    super();    
  }
  
  @Override
  public RevisorDeFirma getBeanOfForm(RevisorDeFirmaForm form) {
    return  form.getRevisorDeFirma();
  }

  @Override
  public Class<RevisorDeFirmaForm> getClassOfForm() {
    return RevisorDeFirmaForm.class;
  }

  @Override
  public void validate(RevisorDeFirmaForm __form, RevisorDeFirma __bean, Errors errors) {

    WebValidationResult<RevisorDeFirmaForm> wvr;
    wvr = new WebValidationResult<RevisorDeFirmaForm>(errors);

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


  public void validate(RevisorDeFirmaForm __form, RevisorDeFirma __bean, Errors errors,
    WebValidationResult<RevisorDeFirmaForm> wvr, boolean isNou) {

    BeanValidatorResult<RevisorDeFirma> __vr = new BeanValidatorResult<RevisorDeFirma>();
    validator.validate(__vr, __bean,
      isNou, firmaEjb, revisorDeFirmaEjb, usuariEntitatEjb);

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

  public RevisorDeFirmaValidator<RevisorDeFirma> getValidator() {
    return validator;
  }

  public void setValidator(RevisorDeFirmaValidator<RevisorDeFirma> validator) {
    this.validator = validator;
  }

}