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
import es.caib.portafib.persistence.validator.PeticioDeFirmaValidator;

import es.caib.portafib.back.form.webdb.PeticioDeFirmaForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.portafib.model.entity.PeticioDeFirma;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class PeticioDeFirmaWebValidator extends AbstractWebValidator<PeticioDeFirmaForm, PeticioDeFirma>
     implements Validator, PeticioDeFirmaFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected PeticioDeFirmaValidator<PeticioDeFirma> validator = new PeticioDeFirmaValidator<PeticioDeFirma>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.CustodiaInfoService.JNDI_NAME)
  protected es.caib.portafib.ejb.CustodiaInfoService custodiaInfoEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.FluxDeFirmesService.JNDI_NAME)
  protected es.caib.portafib.ejb.FluxDeFirmesService fluxDeFirmesEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.IdiomaService.JNDI_NAME)
  protected es.caib.portafib.ejb.IdiomaService idiomaEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.PeticioDeFirmaService.JNDI_NAME)
  protected es.caib.portafib.ejb.PeticioDeFirmaService peticioDeFirmaEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.TipusDocumentService.JNDI_NAME)
  protected es.caib.portafib.ejb.TipusDocumentService tipusDocumentEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.UsuariAplicacioService.JNDI_NAME)
  protected es.caib.portafib.ejb.UsuariAplicacioService usuariAplicacioEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.UsuariAplicacioConfiguracioService.JNDI_NAME)
  protected es.caib.portafib.ejb.UsuariAplicacioConfiguracioService usuariAplicacioConfiguracioEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.UsuariEntitatService.JNDI_NAME)
  protected es.caib.portafib.ejb.UsuariEntitatService usuariEntitatEjb;



  public PeticioDeFirmaWebValidator() {
    super();    
  }
  
  @Override
  public PeticioDeFirma getBeanOfForm(PeticioDeFirmaForm form) {
    return  form.getPeticioDeFirma();
  }

  @Override
  public Class<PeticioDeFirmaForm> getClassOfForm() {
    return PeticioDeFirmaForm.class;
  }

  @Override
  public void validate(PeticioDeFirmaForm __form, PeticioDeFirma __bean, Errors errors) {

    WebValidationResult<PeticioDeFirmaForm> wvr;
    wvr = new WebValidationResult<PeticioDeFirmaForm>(errors);

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


  public void validate(PeticioDeFirmaForm __form, PeticioDeFirma __bean, Errors errors,
    WebValidationResult<PeticioDeFirmaForm> wvr, boolean isNou) {

    if (isNou) { // Creacio
      // ================ CREATION
      // Fitxers 
    }
    BeanValidatorResult<PeticioDeFirma> __vr = new BeanValidatorResult<PeticioDeFirma>();
    validator.validate(__vr, __bean,
      isNou, custodiaInfoEjb, fluxDeFirmesEjb, idiomaEjb, peticioDeFirmaEjb, tipusDocumentEjb, usuariAplicacioEjb, usuariAplicacioConfiguracioEjb, usuariEntitatEjb);

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

  public PeticioDeFirmaValidator<PeticioDeFirma> getValidator() {
    return validator;
  }

  public void setValidator(PeticioDeFirmaValidator<PeticioDeFirma> validator) {
    this.validator = validator;
  }

}