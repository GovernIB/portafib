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
import es.caib.portafib.persistence.validator.UsuariPersonaValidator;

import es.caib.portafib.back.form.webdb.UsuariPersonaForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.portafib.model.entity.UsuariPersona;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class UsuariPersonaWebValidator extends AbstractWebValidator<UsuariPersonaForm, UsuariPersona>
     implements Validator, UsuariPersonaFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected UsuariPersonaValidator<UsuariPersona> validator = new UsuariPersonaValidator<UsuariPersona>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.IdiomaService.JNDI_NAME)
  protected es.caib.portafib.ejb.IdiomaService idiomaEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.UsuariPersonaService.JNDI_NAME)
  protected es.caib.portafib.ejb.UsuariPersonaService usuariPersonaEjb;



  public UsuariPersonaWebValidator() {
    super();    
  }
  
  @Override
  public UsuariPersona getBeanOfForm(UsuariPersonaForm form) {
    return  form.getUsuariPersona();
  }

  @Override
  public Class<UsuariPersonaForm> getClassOfForm() {
    return UsuariPersonaForm.class;
  }

  @Override
  public void validate(UsuariPersonaForm __form, UsuariPersona __bean, Errors errors) {

    WebValidationResult<UsuariPersonaForm> wvr;
    wvr = new WebValidationResult<UsuariPersonaForm>(errors);

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


  public void validate(UsuariPersonaForm __form, UsuariPersona __bean, Errors errors,
    WebValidationResult<UsuariPersonaForm> wvr, boolean isNou) {

    BeanValidatorResult<UsuariPersona> __vr = new BeanValidatorResult<UsuariPersona>();
    validator.validate(__vr, __bean,
      isNou, idiomaEjb, usuariPersonaEjb);

    if (__vr.hasErrors()) {
        List<I18NFieldError> vrErrors = __vr.getErrors();
    	   for (I18NFieldError i18nFieldError : vrErrors) {
    	       wvr.rejectValue(i18nFieldError.getField(), i18nFieldError.getTranslation().getCode(), i18nFieldError.getTranslation().getArgs());
        }
    }

    if (isNou) { // Creacio
      // ================ CREATION
      // Fitxers 
    }

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public UsuariPersonaValidator<UsuariPersona> getValidator() {
    return validator;
  }

  public void setValidator(UsuariPersonaValidator<UsuariPersona> validator) {
    this.validator = validator;
  }

}