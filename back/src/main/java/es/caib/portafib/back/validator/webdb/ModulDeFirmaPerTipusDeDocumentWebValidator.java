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
import es.caib.portafib.persistence.validator.ModulDeFirmaPerTipusDeDocumentValidator;

import es.caib.portafib.back.form.webdb.ModulDeFirmaPerTipusDeDocumentForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.portafib.model.entity.ModulDeFirmaPerTipusDeDocument;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class ModulDeFirmaPerTipusDeDocumentWebValidator extends AbstractWebValidator<ModulDeFirmaPerTipusDeDocumentForm, ModulDeFirmaPerTipusDeDocument>
     implements Validator, ModulDeFirmaPerTipusDeDocumentFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected ModulDeFirmaPerTipusDeDocumentValidator<ModulDeFirmaPerTipusDeDocument> validator = new ModulDeFirmaPerTipusDeDocumentValidator<ModulDeFirmaPerTipusDeDocument>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.ModulDeFirmaPerTipusDeDocumentService.JNDI_NAME)
  protected es.caib.portafib.ejb.ModulDeFirmaPerTipusDeDocumentService modulDeFirmaPerTipusDeDocumentEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.PluginService.JNDI_NAME)
  protected es.caib.portafib.ejb.PluginService pluginEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.TipusDocumentService.JNDI_NAME)
  protected es.caib.portafib.ejb.TipusDocumentService tipusDocumentEjb;



  public ModulDeFirmaPerTipusDeDocumentWebValidator() {
    super();    
  }
  
  @Override
  public ModulDeFirmaPerTipusDeDocument getBeanOfForm(ModulDeFirmaPerTipusDeDocumentForm form) {
    return  form.getModulDeFirmaPerTipusDeDocument();
  }

  @Override
  public Class<ModulDeFirmaPerTipusDeDocumentForm> getClassOfForm() {
    return ModulDeFirmaPerTipusDeDocumentForm.class;
  }

  @Override
  public void validate(ModulDeFirmaPerTipusDeDocumentForm __form, ModulDeFirmaPerTipusDeDocument __bean, Errors errors) {

    WebValidationResult<ModulDeFirmaPerTipusDeDocumentForm> wvr;
    wvr = new WebValidationResult<ModulDeFirmaPerTipusDeDocumentForm>(errors);

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


  public void validate(ModulDeFirmaPerTipusDeDocumentForm __form, ModulDeFirmaPerTipusDeDocument __bean, Errors errors,
    WebValidationResult<ModulDeFirmaPerTipusDeDocumentForm> wvr, boolean isNou) {

    BeanValidatorResult<ModulDeFirmaPerTipusDeDocument> __vr = new BeanValidatorResult<ModulDeFirmaPerTipusDeDocument>();
    validator.validate(__vr, __bean,
      isNou, modulDeFirmaPerTipusDeDocumentEjb, pluginEjb, tipusDocumentEjb);

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

  public ModulDeFirmaPerTipusDeDocumentValidator<ModulDeFirmaPerTipusDeDocument> getValidator() {
    return validator;
  }

  public void setValidator(ModulDeFirmaPerTipusDeDocumentValidator<ModulDeFirmaPerTipusDeDocument> validator) {
    this.validator = validator;
  }

}