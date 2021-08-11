package es.caib.portafib.back.validator.webdb;

import org.apache.log4j.Logger;

import javax.ejb.EJB;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import es.caib.portafib.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.caib.portafib.jpa.validator.ModulDeFirmaPerTipusDeDocumentValidator;

import es.caib.portafib.back.form.webdb.ModulDeFirmaPerTipusDeDocumentForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class ModulDeFirmaPerTipusDeDocumentWebValidator  implements Validator, ModulDeFirmaPerTipusDeDocumentFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected ModulDeFirmaPerTipusDeDocumentValidator<Object> validator = new ModulDeFirmaPerTipusDeDocumentValidator<Object>();

  // EJB's
  @EJB(mappedName = es.caib.portafib.ejb.ModulDeFirmaPerTipusDeDocumentLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.ModulDeFirmaPerTipusDeDocumentLocal modulDeFirmaPerTipusDeDocumentEjb;

  @EJB(mappedName = es.caib.portafib.ejb.PluginLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.PluginLocal pluginEjb;

  @EJB(mappedName = es.caib.portafib.ejb.TipusDocumentLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.TipusDocumentLocal tipusDocumentEjb;



  public ModulDeFirmaPerTipusDeDocumentWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return ModulDeFirmaPerTipusDeDocumentForm.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {

    WebValidationResult<Object> wvr;
    wvr = new WebValidationResult<Object>(errors);

    Boolean nou = (Boolean)errors.getFieldValue("nou");
    boolean isNou =  nou != null && nou.booleanValue();

    validate(target, errors, wvr, isNou);
  }


  public void validate(Object target, Errors errors,
    WebValidationResult<Object> wvr, boolean isNou) {

    validator.validate(wvr, target,
      isNou, modulDeFirmaPerTipusDeDocumentEjb, pluginEjb, tipusDocumentEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public ModulDeFirmaPerTipusDeDocumentValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(ModulDeFirmaPerTipusDeDocumentValidator<Object> validator) {
    this.validator = validator;
  }

}