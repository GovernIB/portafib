package es.caib.portafib.back.validator.webdb;

import org.apache.log4j.Logger;

import javax.ejb.EJB;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import es.caib.portafib.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.caib.portafib.jpa.validator.PermisGrupPlantillaValidator;

import es.caib.portafib.back.form.webdb.PermisGrupPlantillaForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class PermisGrupPlantillaWebValidator  implements Validator, PermisGrupPlantillaFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected PermisGrupPlantillaValidator<Object> validator = new PermisGrupPlantillaValidator<Object>();

  // EJB's
  @EJB(mappedName = es.caib.portafib.ejb.GrupEntitatLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.GrupEntitatLocal grupEntitatEjb;

  @EJB(mappedName = es.caib.portafib.ejb.PermisGrupPlantillaLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.PermisGrupPlantillaLocal permisGrupPlantillaEjb;

  @EJB(mappedName = es.caib.portafib.ejb.PlantillaFluxDeFirmesLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.PlantillaFluxDeFirmesLocal plantillaFluxDeFirmesEjb;



  public PermisGrupPlantillaWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return PermisGrupPlantillaForm.class.isAssignableFrom(clazz);
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
      isNou, grupEntitatEjb, permisGrupPlantillaEjb, plantillaFluxDeFirmesEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public PermisGrupPlantillaValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(PermisGrupPlantillaValidator<Object> validator) {
    this.validator = validator;
  }

}