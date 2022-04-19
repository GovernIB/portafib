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
import es.caib.portafib.persistence.validator.MetadadaValidator;

import es.caib.portafib.back.form.webdb.MetadadaForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.portafib.model.entity.Metadada;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class MetadadaWebValidator extends AbstractWebValidator<MetadadaForm, Metadada>
     implements Validator, MetadadaFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected MetadadaValidator<Metadada> validator = new MetadadaValidator<Metadada>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.MetadadaService.JNDI_NAME)
  protected es.caib.portafib.ejb.MetadadaService metadadaEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.PeticioDeFirmaService.JNDI_NAME)
  protected es.caib.portafib.ejb.PeticioDeFirmaService peticioDeFirmaEjb;



  public MetadadaWebValidator() {
    super();    
  }
  
  @Override
  public Metadada getBeanOfForm(MetadadaForm form) {
    return  form.getMetadada();
  }

  @Override
  public Class<MetadadaForm> getClassOfForm() {
    return MetadadaForm.class;
  }

  @Override
  public void validate(MetadadaForm __form, Metadada __bean, Errors errors) {

    WebValidationResult<MetadadaForm> wvr;
    wvr = new WebValidationResult<MetadadaForm>(errors);

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


  public void validate(MetadadaForm __form, Metadada __bean, Errors errors,
    WebValidationResult<MetadadaForm> wvr, boolean isNou) {

    BeanValidatorResult<Metadada> __vr = new BeanValidatorResult<Metadada>();
    validator.validate(__vr, __bean,
      isNou, metadadaEjb, peticioDeFirmaEjb);

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

  public MetadadaValidator<Metadada> getValidator() {
    return validator;
  }

  public void setValidator(MetadadaValidator<Metadada> validator) {
    this.validator = validator;
  }

}