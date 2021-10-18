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
import es.caib.portafib.persistence.validator.FirmaValidator;

import es.caib.portafib.back.form.webdb.FirmaForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.portafib.model.entity.Firma;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class FirmaWebValidator extends AbstractWebValidator<FirmaForm, Firma>
     implements Validator, FirmaFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected FirmaValidator<Firma> validator = new FirmaValidator<Firma>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.BlocDeFirmesService.JNDI_NAME)
  protected es.caib.portafib.ejb.BlocDeFirmesService blocDeFirmesEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.FirmaService.JNDI_NAME)
  protected es.caib.portafib.ejb.FirmaService firmaEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.UsuariEntitatService.JNDI_NAME)
  protected es.caib.portafib.ejb.UsuariEntitatService usuariEntitatEjb;



  public FirmaWebValidator() {
    super();    
  }
  
  @Override
  public Firma getBeanOfForm(FirmaForm form) {
    return  form.getFirma();
  }

  @Override
  public Class<FirmaForm> getClassOfForm() {
    return FirmaForm.class;
  }

  @Override
  public void validate(FirmaForm __form, Firma __bean, Errors errors) {

    WebValidationResult<FirmaForm> wvr;
    wvr = new WebValidationResult<FirmaForm>(errors);

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


  public void validate(FirmaForm __form, Firma __bean, Errors errors,
    WebValidationResult<FirmaForm> wvr, boolean isNou) {

    if (isNou) { // Creacio
      // ================ CREATION
      // Fitxers 
    }
    BeanValidatorResult<Firma> __vr = new BeanValidatorResult<Firma>();
    validator.validate(__vr, __bean,
      isNou, blocDeFirmesEjb, firmaEjb, usuariEntitatEjb);

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

  public FirmaValidator<Firma> getValidator() {
    return validator;
  }

  public void setValidator(FirmaValidator<Firma> validator) {
    this.validator = validator;
  }

}