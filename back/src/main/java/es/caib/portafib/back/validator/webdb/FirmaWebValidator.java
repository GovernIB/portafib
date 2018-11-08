package es.caib.portafib.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import es.caib.portafib.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.caib.portafib.jpa.validator.FirmaValidator;

import es.caib.portafib.back.form.webdb.FirmaForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class FirmaWebValidator  implements Validator, FirmaFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected FirmaValidator<Object> validator = new FirmaValidator<Object>();

  // EJB's
  @javax.ejb.EJB(mappedName = "portafib/BlocDeFirmesEJB/local")
  protected es.caib.portafib.ejb.BlocDeFirmesLocal blocDeFirmesEjb;

  @javax.ejb.EJB(mappedName = "portafib/FirmaEJB/local")
  protected es.caib.portafib.ejb.FirmaLocal firmaEjb;

  @javax.ejb.EJB(mappedName = "portafib/UsuariEntitatEJB/local")
  protected es.caib.portafib.ejb.UsuariEntitatLocal usuariEntitatEjb;



  public FirmaWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return FirmaForm.class.isAssignableFrom(clazz);
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
      isNou, blocDeFirmesEjb, firmaEjb, usuariEntitatEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public FirmaValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(FirmaValidator<Object> validator) {
    this.validator = validator;
  }

}