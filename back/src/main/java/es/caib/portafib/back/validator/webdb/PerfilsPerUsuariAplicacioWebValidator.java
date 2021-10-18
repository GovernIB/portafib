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
import es.caib.portafib.persistence.validator.PerfilsPerUsuariAplicacioValidator;

import es.caib.portafib.back.form.webdb.PerfilsPerUsuariAplicacioForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.portafib.model.entity.PerfilsPerUsuariAplicacio;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class PerfilsPerUsuariAplicacioWebValidator extends AbstractWebValidator<PerfilsPerUsuariAplicacioForm, PerfilsPerUsuariAplicacio>
     implements Validator, PerfilsPerUsuariAplicacioFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected PerfilsPerUsuariAplicacioValidator<PerfilsPerUsuariAplicacio> validator = new PerfilsPerUsuariAplicacioValidator<PerfilsPerUsuariAplicacio>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.PerfilDeFirmaService.JNDI_NAME)
  protected es.caib.portafib.ejb.PerfilDeFirmaService perfilDeFirmaEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.PerfilsPerUsuariAplicacioService.JNDI_NAME)
  protected es.caib.portafib.ejb.PerfilsPerUsuariAplicacioService perfilsPerUsuariAplicacioEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.UsuariAplicacioService.JNDI_NAME)
  protected es.caib.portafib.ejb.UsuariAplicacioService usuariAplicacioEjb;



  public PerfilsPerUsuariAplicacioWebValidator() {
    super();    
  }
  
  @Override
  public PerfilsPerUsuariAplicacio getBeanOfForm(PerfilsPerUsuariAplicacioForm form) {
    return  form.getPerfilsPerUsuariAplicacio();
  }

  @Override
  public Class<PerfilsPerUsuariAplicacioForm> getClassOfForm() {
    return PerfilsPerUsuariAplicacioForm.class;
  }

  @Override
  public void validate(PerfilsPerUsuariAplicacioForm __form, PerfilsPerUsuariAplicacio __bean, Errors errors) {

    WebValidationResult<PerfilsPerUsuariAplicacioForm> wvr;
    wvr = new WebValidationResult<PerfilsPerUsuariAplicacioForm>(errors);

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


  public void validate(PerfilsPerUsuariAplicacioForm __form, PerfilsPerUsuariAplicacio __bean, Errors errors,
    WebValidationResult<PerfilsPerUsuariAplicacioForm> wvr, boolean isNou) {

    BeanValidatorResult<PerfilsPerUsuariAplicacio> __vr = new BeanValidatorResult<PerfilsPerUsuariAplicacio>();
    validator.validate(__vr, __bean,
      isNou, perfilDeFirmaEjb, perfilsPerUsuariAplicacioEjb, usuariAplicacioEjb);

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

  public PerfilsPerUsuariAplicacioValidator<PerfilsPerUsuariAplicacio> getValidator() {
    return validator;
  }

  public void setValidator(PerfilsPerUsuariAplicacioValidator<PerfilsPerUsuariAplicacio> validator) {
    this.validator = validator;
  }

}