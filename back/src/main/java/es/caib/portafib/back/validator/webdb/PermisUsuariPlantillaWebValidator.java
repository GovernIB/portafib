package es.caib.portafib.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import es.caib.portafib.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.caib.portafib.jpa.validator.PermisUsuariPlantillaValidator;

import es.caib.portafib.back.form.webdb.PermisUsuariPlantillaForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class PermisUsuariPlantillaWebValidator  implements Validator, PermisUsuariPlantillaFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected PermisUsuariPlantillaValidator<Object> validator = new PermisUsuariPlantillaValidator<Object>();

  // EJB's
  @javax.ejb.EJB(mappedName = "portafib/PermisUsuariPlantillaEJB/local")
  protected es.caib.portafib.ejb.PermisUsuariPlantillaLocal permisUsuariPlantillaEjb;

  @javax.ejb.EJB(mappedName = "portafib/PlantillaFluxDeFirmesEJB/local")
  protected es.caib.portafib.ejb.PlantillaFluxDeFirmesLocal plantillaFluxDeFirmesEjb;

  @javax.ejb.EJB(mappedName = "portafib/UsuariEntitatEJB/local")
  protected es.caib.portafib.ejb.UsuariEntitatLocal usuariEntitatEjb;



  public PermisUsuariPlantillaWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return PermisUsuariPlantillaForm.class.isAssignableFrom(clazz);
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
      isNou, permisUsuariPlantillaEjb, plantillaFluxDeFirmesEjb, usuariEntitatEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public PermisUsuariPlantillaValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(PermisUsuariPlantillaValidator<Object> validator) {
    this.validator = validator;
  }

}