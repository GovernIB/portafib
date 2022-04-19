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
import es.caib.portafib.persistence.validator.AnnexValidator;

import es.caib.portafib.back.form.webdb.AnnexForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.portafib.model.entity.Annex;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class AnnexWebValidator extends AbstractWebValidator<AnnexForm, Annex>
     implements Validator, AnnexFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected AnnexValidator<Annex> validator = new AnnexValidator<Annex>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.AnnexService.JNDI_NAME)
  protected es.caib.portafib.ejb.AnnexService annexEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.PeticioDeFirmaService.JNDI_NAME)
  protected es.caib.portafib.ejb.PeticioDeFirmaService peticioDeFirmaEjb;



  public AnnexWebValidator() {
    super();    
  }
  
  @Override
  public Annex getBeanOfForm(AnnexForm form) {
    return  form.getAnnex();
  }

  @Override
  public Class<AnnexForm> getClassOfForm() {
    return AnnexForm.class;
  }

  @Override
  public void validate(AnnexForm __form, Annex __bean, Errors errors) {

    WebValidationResult<AnnexForm> wvr;
    wvr = new WebValidationResult<AnnexForm>(errors);

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


  public void validate(AnnexForm __form, Annex __bean, Errors errors,
    WebValidationResult<AnnexForm> wvr, boolean isNou) {

    if (isNou) { // Creacio
      // ================ CREATION
      // Fitxers 
      CommonsMultipartFile fitxerID = ((AnnexForm)__form).getFitxerID();
      if (fitxerID == null || fitxerID.isEmpty()) {
        errors.rejectValue(get(FITXERID), "genapp.validation.required",
          new String[]{ org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(get(FITXERID)) },
          null);
      }

    }
    BeanValidatorResult<Annex> __vr = new BeanValidatorResult<Annex>();
    validator.validate(__vr, __bean,
      isNou, annexEjb, peticioDeFirmaEjb);

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

  public AnnexValidator<Annex> getValidator() {
    return validator;
  }

  public void setValidator(AnnexValidator<Annex> validator) {
    this.validator = validator;
  }

}