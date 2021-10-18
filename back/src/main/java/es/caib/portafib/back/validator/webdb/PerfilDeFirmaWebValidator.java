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
import es.caib.portafib.persistence.validator.PerfilDeFirmaValidator;

import es.caib.portafib.back.form.webdb.PerfilDeFirmaForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.portafib.model.entity.PerfilDeFirma;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class PerfilDeFirmaWebValidator extends AbstractWebValidator<PerfilDeFirmaForm, PerfilDeFirma>
     implements Validator, PerfilDeFirmaFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected PerfilDeFirmaValidator<PerfilDeFirma> validator = new PerfilDeFirmaValidator<PerfilDeFirma>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.PerfilDeFirmaService.JNDI_NAME)
  protected es.caib.portafib.ejb.PerfilDeFirmaService perfilDeFirmaEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.UsuariAplicacioConfiguracioService.JNDI_NAME)
  protected es.caib.portafib.ejb.UsuariAplicacioConfiguracioService usuariAplicacioConfiguracioEjb;



  public PerfilDeFirmaWebValidator() {
    super();    
  }
  
  @Override
  public PerfilDeFirma getBeanOfForm(PerfilDeFirmaForm form) {
    return  form.getPerfilDeFirma();
  }

  @Override
  public Class<PerfilDeFirmaForm> getClassOfForm() {
    return PerfilDeFirmaForm.class;
  }

  @Override
  public void validate(PerfilDeFirmaForm __form, PerfilDeFirma __bean, Errors errors) {

    WebValidationResult<PerfilDeFirmaForm> wvr;
    wvr = new WebValidationResult<PerfilDeFirmaForm>(errors);

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


  public void validate(PerfilDeFirmaForm __form, PerfilDeFirma __bean, Errors errors,
    WebValidationResult<PerfilDeFirmaForm> wvr, boolean isNou) {

    BeanValidatorResult<PerfilDeFirma> __vr = new BeanValidatorResult<PerfilDeFirma>();
    validator.validate(__vr, __bean,
      isNou, perfilDeFirmaEjb, usuariAplicacioConfiguracioEjb);

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

  public PerfilDeFirmaValidator<PerfilDeFirma> getValidator() {
    return validator;
  }

  public void setValidator(PerfilDeFirmaValidator<PerfilDeFirma> validator) {
    this.validator = validator;
  }

}