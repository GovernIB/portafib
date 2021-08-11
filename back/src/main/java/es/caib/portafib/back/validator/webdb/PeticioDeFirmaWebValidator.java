package es.caib.portafib.back.validator.webdb;

import org.apache.log4j.Logger;

import javax.ejb.EJB;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import es.caib.portafib.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.caib.portafib.jpa.validator.PeticioDeFirmaValidator;

import es.caib.portafib.back.form.webdb.PeticioDeFirmaForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class PeticioDeFirmaWebValidator  implements Validator, PeticioDeFirmaFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected PeticioDeFirmaValidator<Object> validator = new PeticioDeFirmaValidator<Object>();

  // EJB's
  @EJB(mappedName = es.caib.portafib.ejb.CustodiaInfoLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.CustodiaInfoLocal custodiaInfoEjb;

  @EJB(mappedName = es.caib.portafib.ejb.FluxDeFirmesLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.FluxDeFirmesLocal fluxDeFirmesEjb;

  @EJB(mappedName = es.caib.portafib.ejb.IdiomaLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.IdiomaLocal idiomaEjb;

  @EJB(mappedName = es.caib.portafib.ejb.PeticioDeFirmaLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.PeticioDeFirmaLocal peticioDeFirmaEjb;

  @EJB(mappedName = es.caib.portafib.ejb.TipusDocumentLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.TipusDocumentLocal tipusDocumentEjb;

  @EJB(mappedName = es.caib.portafib.ejb.UsuariAplicacioLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.UsuariAplicacioLocal usuariAplicacioEjb;

  @EJB(mappedName = es.caib.portafib.ejb.UsuariAplicacioConfiguracioLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.UsuariAplicacioConfiguracioLocal usuariAplicacioConfiguracioEjb;

  @EJB(mappedName = es.caib.portafib.ejb.UsuariEntitatLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.UsuariEntitatLocal usuariEntitatEjb;



  public PeticioDeFirmaWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return PeticioDeFirmaForm.class.isAssignableFrom(clazz);
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

    if (isNou) { // Creacio
      // ================ CREATION
      // Fitxers 
    }
    validator.validate(wvr, target,
      isNou, custodiaInfoEjb, fluxDeFirmesEjb, idiomaEjb, peticioDeFirmaEjb, tipusDocumentEjb, usuariAplicacioEjb, usuariAplicacioConfiguracioEjb, usuariEntitatEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public PeticioDeFirmaValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(PeticioDeFirmaValidator<Object> validator) {
    this.validator = validator;
  }

}