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
import es.caib.portafib.persistence.validator.RebreAvisValidator;

import es.caib.portafib.back.form.webdb.RebreAvisForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.portafib.model.entity.RebreAvis;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class RebreAvisWebValidator extends AbstractWebValidator<RebreAvisForm, RebreAvis>
     implements Validator, RebreAvisFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected RebreAvisValidator<RebreAvis> validator = new RebreAvisValidator<RebreAvis>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.RebreAvisService.JNDI_NAME)
  protected es.caib.portafib.ejb.RebreAvisService rebreAvisEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.TipusNotificacioService.JNDI_NAME)
  protected es.caib.portafib.ejb.TipusNotificacioService tipusNotificacioEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.UsuariEntitatService.JNDI_NAME)
  protected es.caib.portafib.ejb.UsuariEntitatService usuariEntitatEjb;



  public RebreAvisWebValidator() {
    super();    
  }
  
  @Override
  public RebreAvis getBeanOfForm(RebreAvisForm form) {
    return  form.getRebreAvis();
  }

  @Override
  public Class<RebreAvisForm> getClassOfForm() {
    return RebreAvisForm.class;
  }

  @Override
  public void validate(RebreAvisForm __form, RebreAvis __bean, Errors errors) {

    WebValidationResult<RebreAvisForm> wvr;
    wvr = new WebValidationResult<RebreAvisForm>(errors);

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


  public void validate(RebreAvisForm __form, RebreAvis __bean, Errors errors,
    WebValidationResult<RebreAvisForm> wvr, boolean isNou) {

    BeanValidatorResult<RebreAvis> __vr = new BeanValidatorResult<RebreAvis>();
    validator.validate(__vr, __bean,
      isNou, rebreAvisEjb, tipusNotificacioEjb, usuariEntitatEjb);

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

  public RebreAvisValidator<RebreAvis> getValidator() {
    return validator;
  }

  public void setValidator(RebreAvisValidator<RebreAvis> validator) {
    this.validator = validator;
  }

}