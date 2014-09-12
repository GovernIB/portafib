package es.caib.portafib.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import es.caib.portafib.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.caib.portafib.jpa.validator.EstatDeFirmaValidator;

import es.caib.portafib.back.form.webdb.EstatDeFirmaForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class EstatDeFirmaWebValidator  implements Validator, EstatDeFirmaFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected EstatDeFirmaValidator<Object> validator = new EstatDeFirmaValidator<Object>();

  // EJB's
  @javax.ejb.EJB(mappedName = "portafib/ColaboracioDelegacioEJB/local")
  protected es.caib.portafib.ejb.ColaboracioDelegacioLocal colaboracioDelegacioEjb;

  @javax.ejb.EJB(mappedName = "portafib/EstatDeFirmaEJB/local")
  protected es.caib.portafib.ejb.EstatDeFirmaLocal estatDeFirmaEjb;

  @javax.ejb.EJB(mappedName = "portafib/FirmaEJB/local")
  protected es.caib.portafib.ejb.FirmaLocal firmaEjb;

  @javax.ejb.EJB(mappedName = "portafib/TipusEstatDeFirmaFinalEJB/local")
  protected es.caib.portafib.ejb.TipusEstatDeFirmaFinalLocal tipusEstatDeFirmaFinalEjb;

  @javax.ejb.EJB(mappedName = "portafib/TipusEstatDeFirmaInicialEJB/local")
  protected es.caib.portafib.ejb.TipusEstatDeFirmaInicialLocal tipusEstatDeFirmaInicialEjb;

  @javax.ejb.EJB(mappedName = "portafib/UsuariEntitatEJB/local")
  protected es.caib.portafib.ejb.UsuariEntitatLocal usuariEntitatEjb;



  public EstatDeFirmaWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return EstatDeFirmaForm.class.isAssignableFrom(clazz);
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
      isNou, colaboracioDelegacioEjb, estatDeFirmaEjb, firmaEjb, tipusEstatDeFirmaFinalEjb, tipusEstatDeFirmaInicialEjb, usuariEntitatEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public EstatDeFirmaValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(EstatDeFirmaValidator<Object> validator) {
    this.validator = validator;
  }

}