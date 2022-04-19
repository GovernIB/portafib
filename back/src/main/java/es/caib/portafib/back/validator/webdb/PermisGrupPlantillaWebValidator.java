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
import es.caib.portafib.persistence.validator.PermisGrupPlantillaValidator;

import es.caib.portafib.back.form.webdb.PermisGrupPlantillaForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.portafib.model.entity.PermisGrupPlantilla;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class PermisGrupPlantillaWebValidator extends AbstractWebValidator<PermisGrupPlantillaForm, PermisGrupPlantilla>
     implements Validator, PermisGrupPlantillaFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected PermisGrupPlantillaValidator<PermisGrupPlantilla> validator = new PermisGrupPlantillaValidator<PermisGrupPlantilla>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.GrupEntitatService.JNDI_NAME)
  protected es.caib.portafib.ejb.GrupEntitatService grupEntitatEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.PermisGrupPlantillaService.JNDI_NAME)
  protected es.caib.portafib.ejb.PermisGrupPlantillaService permisGrupPlantillaEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.PlantillaFluxDeFirmesService.JNDI_NAME)
  protected es.caib.portafib.ejb.PlantillaFluxDeFirmesService plantillaFluxDeFirmesEjb;



  public PermisGrupPlantillaWebValidator() {
    super();    
  }
  
  @Override
  public PermisGrupPlantilla getBeanOfForm(PermisGrupPlantillaForm form) {
    return  form.getPermisGrupPlantilla();
  }

  @Override
  public Class<PermisGrupPlantillaForm> getClassOfForm() {
    return PermisGrupPlantillaForm.class;
  }

  @Override
  public void validate(PermisGrupPlantillaForm __form, PermisGrupPlantilla __bean, Errors errors) {

    WebValidationResult<PermisGrupPlantillaForm> wvr;
    wvr = new WebValidationResult<PermisGrupPlantillaForm>(errors);

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


  public void validate(PermisGrupPlantillaForm __form, PermisGrupPlantilla __bean, Errors errors,
    WebValidationResult<PermisGrupPlantillaForm> wvr, boolean isNou) {

    BeanValidatorResult<PermisGrupPlantilla> __vr = new BeanValidatorResult<PermisGrupPlantilla>();
    validator.validate(__vr, __bean,
      isNou, grupEntitatEjb, permisGrupPlantillaEjb, plantillaFluxDeFirmesEjb);

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

  public PermisGrupPlantillaValidator<PermisGrupPlantilla> getValidator() {
    return validator;
  }

  public void setValidator(PermisGrupPlantillaValidator<PermisGrupPlantilla> validator) {
    this.validator = validator;
  }

}