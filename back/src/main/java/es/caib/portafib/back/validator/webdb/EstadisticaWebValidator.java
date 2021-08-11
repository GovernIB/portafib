package es.caib.portafib.back.validator.webdb;

import org.apache.log4j.Logger;

import javax.ejb.EJB;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import es.caib.portafib.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.caib.portafib.jpa.validator.EstadisticaValidator;

import es.caib.portafib.back.form.webdb.EstadisticaForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class EstadisticaWebValidator  implements Validator, EstadisticaFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected EstadisticaValidator<Object> validator = new EstadisticaValidator<Object>();

  // EJB's
  @EJB(mappedName = es.caib.portafib.ejb.EntitatLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.EntitatLocal entitatEjb;

  @EJB(mappedName = es.caib.portafib.ejb.EstadisticaLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.EstadisticaLocal estadisticaEjb;



  public EstadisticaWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return EstadisticaForm.class.isAssignableFrom(clazz);
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
      isNou, entitatEjb, estadisticaEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public EstadisticaValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(EstadisticaValidator<Object> validator) {
    this.validator = validator;
  }

}