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
import es.caib.portafib.persistence.validator.PermisUsuariPlantillaValidator;

import es.caib.portafib.back.form.webdb.PermisUsuariPlantillaForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.portafib.model.entity.PermisUsuariPlantilla;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class PermisUsuariPlantillaWebValidator extends AbstractWebValidator<PermisUsuariPlantillaForm, PermisUsuariPlantilla>
     implements Validator, PermisUsuariPlantillaFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected PermisUsuariPlantillaValidator<PermisUsuariPlantilla> validator = new PermisUsuariPlantillaValidator<PermisUsuariPlantilla>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.PermisUsuariPlantillaService.JNDI_NAME)
  protected es.caib.portafib.ejb.PermisUsuariPlantillaService permisUsuariPlantillaEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.PlantillaFluxDeFirmesService.JNDI_NAME)
  protected es.caib.portafib.ejb.PlantillaFluxDeFirmesService plantillaFluxDeFirmesEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.UsuariEntitatService.JNDI_NAME)
  protected es.caib.portafib.ejb.UsuariEntitatService usuariEntitatEjb;



  public PermisUsuariPlantillaWebValidator() {
    super();    
  }
  
  @Override
  public PermisUsuariPlantilla getBeanOfForm(PermisUsuariPlantillaForm form) {
    return  form.getPermisUsuariPlantilla();
  }

  @Override
  public Class<PermisUsuariPlantillaForm> getClassOfForm() {
    return PermisUsuariPlantillaForm.class;
  }

  @Override
  public void validate(PermisUsuariPlantillaForm __form, PermisUsuariPlantilla __bean, Errors errors) {

    WebValidationResult<PermisUsuariPlantillaForm> wvr;
    wvr = new WebValidationResult<PermisUsuariPlantillaForm>(errors);

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


  public void validate(PermisUsuariPlantillaForm __form, PermisUsuariPlantilla __bean, Errors errors,
    WebValidationResult<PermisUsuariPlantillaForm> wvr, boolean isNou) {

    BeanValidatorResult<PermisUsuariPlantilla> __vr = new BeanValidatorResult<PermisUsuariPlantilla>();
    validator.validate(__vr, __bean,
      isNou, permisUsuariPlantillaEjb, plantillaFluxDeFirmesEjb, usuariEntitatEjb);

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

  public PermisUsuariPlantillaValidator<PermisUsuariPlantilla> getValidator() {
    return validator;
  }

  public void setValidator(PermisUsuariPlantillaValidator<PermisUsuariPlantilla> validator) {
    this.validator = validator;
  }

}