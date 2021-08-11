package es.caib.portafib.back.validator.webdb;

import org.apache.log4j.Logger;

import javax.ejb.EJB;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import es.caib.portafib.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.caib.portafib.jpa.validator.TipusDocumentColaboracioDelegacioValidator;

import es.caib.portafib.back.form.webdb.TipusDocumentColaboracioDelegacioForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class TipusDocumentColaboracioDelegacioWebValidator  implements Validator, TipusDocumentColaboracioDelegacioFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected TipusDocumentColaboracioDelegacioValidator<Object> validator = new TipusDocumentColaboracioDelegacioValidator<Object>();

  // EJB's
  @EJB(mappedName = es.caib.portafib.ejb.ColaboracioDelegacioLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.ColaboracioDelegacioLocal colaboracioDelegacioEjb;

  @EJB(mappedName = es.caib.portafib.ejb.TipusDocumentLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.TipusDocumentLocal tipusDocumentEjb;

  @EJB(mappedName = es.caib.portafib.ejb.TipusDocumentColaboracioDelegacioLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.TipusDocumentColaboracioDelegacioLocal tipusDocumentColaboracioDelegacioEjb;



  public TipusDocumentColaboracioDelegacioWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return TipusDocumentColaboracioDelegacioForm.class.isAssignableFrom(clazz);
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
      isNou, colaboracioDelegacioEjb, tipusDocumentEjb, tipusDocumentColaboracioDelegacioEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public TipusDocumentColaboracioDelegacioValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(TipusDocumentColaboracioDelegacioValidator<Object> validator) {
    this.validator = validator;
  }

}