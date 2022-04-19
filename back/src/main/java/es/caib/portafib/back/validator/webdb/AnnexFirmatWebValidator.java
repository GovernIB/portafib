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
import es.caib.portafib.persistence.validator.AnnexFirmatValidator;

import es.caib.portafib.back.form.webdb.AnnexFirmatForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.portafib.model.entity.AnnexFirmat;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class AnnexFirmatWebValidator extends AbstractWebValidator<AnnexFirmatForm, AnnexFirmat>
     implements Validator, AnnexFirmatFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected AnnexFirmatValidator<AnnexFirmat> validator = new AnnexFirmatValidator<AnnexFirmat>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.AnnexService.JNDI_NAME)
  protected es.caib.portafib.ejb.AnnexService annexEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.AnnexFirmatService.JNDI_NAME)
  protected es.caib.portafib.ejb.AnnexFirmatService annexFirmatEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.FirmaService.JNDI_NAME)
  protected es.caib.portafib.ejb.FirmaService firmaEjb;



  public AnnexFirmatWebValidator() {
    super();    
  }
  
  @Override
  public AnnexFirmat getBeanOfForm(AnnexFirmatForm form) {
    return  form.getAnnexFirmat();
  }

  @Override
  public Class<AnnexFirmatForm> getClassOfForm() {
    return AnnexFirmatForm.class;
  }

  @Override
  public void validate(AnnexFirmatForm __form, AnnexFirmat __bean, Errors errors) {

    WebValidationResult<AnnexFirmatForm> wvr;
    wvr = new WebValidationResult<AnnexFirmatForm>(errors);

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


  public void validate(AnnexFirmatForm __form, AnnexFirmat __bean, Errors errors,
    WebValidationResult<AnnexFirmatForm> wvr, boolean isNou) {

    if (isNou) { // Creacio
      // ================ CREATION
      // Fitxers 
      CommonsMultipartFile fitxerID = ((AnnexFirmatForm)__form).getFitxerID();
      if (fitxerID == null || fitxerID.isEmpty()) {
        errors.rejectValue(get(FITXERID), "genapp.validation.required",
          new String[]{ org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(get(FITXERID)) },
          null);
      }

    }
    BeanValidatorResult<AnnexFirmat> __vr = new BeanValidatorResult<AnnexFirmat>();
    validator.validate(__vr, __bean,
      isNou, annexEjb, annexFirmatEjb, firmaEjb);

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

  public AnnexFirmatValidator<AnnexFirmat> getValidator() {
    return validator;
  }

  public void setValidator(AnnexFirmatValidator<AnnexFirmat> validator) {
    this.validator = validator;
  }

}