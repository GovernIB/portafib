package es.caib.portafib.back.validator.webdb;

import org.apache.log4j.Logger;

import javax.ejb.EJB;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import es.caib.portafib.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.caib.portafib.jpa.validator.PlantillaFluxDeFirmesValidator;

import es.caib.portafib.back.form.webdb.PlantillaFluxDeFirmesForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class PlantillaFluxDeFirmesWebValidator  implements Validator, PlantillaFluxDeFirmesFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected PlantillaFluxDeFirmesValidator<Object> validator = new PlantillaFluxDeFirmesValidator<Object>();

  // EJB's
  @EJB(mappedName = es.caib.portafib.ejb.FluxDeFirmesLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.FluxDeFirmesLocal fluxDeFirmesEjb;

  @EJB(mappedName = es.caib.portafib.ejb.PlantillaFluxDeFirmesLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.PlantillaFluxDeFirmesLocal plantillaFluxDeFirmesEjb;

  @EJB(mappedName = es.caib.portafib.ejb.UsuariAplicacioLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.UsuariAplicacioLocal usuariAplicacioEjb;

  @EJB(mappedName = es.caib.portafib.ejb.UsuariEntitatLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.UsuariEntitatLocal usuariEntitatEjb;



  public PlantillaFluxDeFirmesWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return PlantillaFluxDeFirmesForm.class.isAssignableFrom(clazz);
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
      isNou, fluxDeFirmesEjb, plantillaFluxDeFirmesEjb, usuariAplicacioEjb, usuariEntitatEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public PlantillaFluxDeFirmesValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(PlantillaFluxDeFirmesValidator<Object> validator) {
    this.validator = validator;
  }

}