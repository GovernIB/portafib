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
import es.caib.portafib.persistence.validator.RevisorDeDestinatariValidator;

import es.caib.portafib.back.form.webdb.RevisorDeDestinatariForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.portafib.model.entity.RevisorDeDestinatari;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class RevisorDeDestinatariWebValidator extends AbstractWebValidator<RevisorDeDestinatariForm, RevisorDeDestinatari>
     implements Validator, RevisorDeDestinatariFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected RevisorDeDestinatariValidator<RevisorDeDestinatari> validator = new RevisorDeDestinatariValidator<RevisorDeDestinatari>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.RevisorDeDestinatariService.JNDI_NAME)
  protected es.caib.portafib.ejb.RevisorDeDestinatariService revisorDeDestinatariEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.UsuariEntitatService.JNDI_NAME)
  protected es.caib.portafib.ejb.UsuariEntitatService usuariEntitatEjb;



  public RevisorDeDestinatariWebValidator() {
    super();    
  }
  
  @Override
  public RevisorDeDestinatari getBeanOfForm(RevisorDeDestinatariForm form) {
    return  form.getRevisorDeDestinatari();
  }

  @Override
  public Class<RevisorDeDestinatariForm> getClassOfForm() {
    return RevisorDeDestinatariForm.class;
  }

  @Override
  public void validate(RevisorDeDestinatariForm __form, RevisorDeDestinatari __bean, Errors errors) {

    WebValidationResult<RevisorDeDestinatariForm> wvr;
    wvr = new WebValidationResult<RevisorDeDestinatariForm>(errors);

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


  public void validate(RevisorDeDestinatariForm __form, RevisorDeDestinatari __bean, Errors errors,
    WebValidationResult<RevisorDeDestinatariForm> wvr, boolean isNou) {

    BeanValidatorResult<RevisorDeDestinatari> __vr = new BeanValidatorResult<RevisorDeDestinatari>();
    validator.validate(__vr, __bean,
      isNou, revisorDeDestinatariEjb, usuariEntitatEjb);

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

  public RevisorDeDestinatariValidator<RevisorDeDestinatari> getValidator() {
    return validator;
  }

  public void setValidator(RevisorDeDestinatariValidator<RevisorDeDestinatari> validator) {
    this.validator = validator;
  }

}