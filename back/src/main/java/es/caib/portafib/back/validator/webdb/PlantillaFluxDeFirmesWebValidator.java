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
import es.caib.portafib.persistence.validator.PlantillaFluxDeFirmesValidator;

import es.caib.portafib.back.form.webdb.PlantillaFluxDeFirmesForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.portafib.model.entity.PlantillaFluxDeFirmes;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class PlantillaFluxDeFirmesWebValidator extends AbstractWebValidator<PlantillaFluxDeFirmesForm, PlantillaFluxDeFirmes>
     implements Validator, PlantillaFluxDeFirmesFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected PlantillaFluxDeFirmesValidator<PlantillaFluxDeFirmes> validator = new PlantillaFluxDeFirmesValidator<PlantillaFluxDeFirmes>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.FluxDeFirmesService.JNDI_NAME)
  protected es.caib.portafib.ejb.FluxDeFirmesService fluxDeFirmesEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.PlantillaFluxDeFirmesService.JNDI_NAME)
  protected es.caib.portafib.ejb.PlantillaFluxDeFirmesService plantillaFluxDeFirmesEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.UsuariAplicacioService.JNDI_NAME)
  protected es.caib.portafib.ejb.UsuariAplicacioService usuariAplicacioEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.UsuariEntitatService.JNDI_NAME)
  protected es.caib.portafib.ejb.UsuariEntitatService usuariEntitatEjb;



  public PlantillaFluxDeFirmesWebValidator() {
    super();    
  }
  
  @Override
  public PlantillaFluxDeFirmes getBeanOfForm(PlantillaFluxDeFirmesForm form) {
    return  form.getPlantillaFluxDeFirmes();
  }

  @Override
  public Class<PlantillaFluxDeFirmesForm> getClassOfForm() {
    return PlantillaFluxDeFirmesForm.class;
  }

  @Override
  public void validate(PlantillaFluxDeFirmesForm __form, PlantillaFluxDeFirmes __bean, Errors errors) {

    WebValidationResult<PlantillaFluxDeFirmesForm> wvr;
    wvr = new WebValidationResult<PlantillaFluxDeFirmesForm>(errors);

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


  public void validate(PlantillaFluxDeFirmesForm __form, PlantillaFluxDeFirmes __bean, Errors errors,
    WebValidationResult<PlantillaFluxDeFirmesForm> wvr, boolean isNou) {

    BeanValidatorResult<PlantillaFluxDeFirmes> __vr = new BeanValidatorResult<PlantillaFluxDeFirmes>();
    validator.validate(__vr, __bean,
      isNou, fluxDeFirmesEjb, plantillaFluxDeFirmesEjb, usuariAplicacioEjb, usuariEntitatEjb);

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

  public PlantillaFluxDeFirmesValidator<PlantillaFluxDeFirmes> getValidator() {
    return validator;
  }

  public void setValidator(PlantillaFluxDeFirmesValidator<PlantillaFluxDeFirmes> validator) {
    this.validator = validator;
  }

}