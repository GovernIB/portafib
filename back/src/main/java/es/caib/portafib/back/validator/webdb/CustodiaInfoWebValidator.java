package es.caib.portafib.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import es.caib.portafib.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.caib.portafib.jpa.validator.CustodiaInfoValidator;

import es.caib.portafib.back.form.webdb.CustodiaInfoForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class CustodiaInfoWebValidator  implements Validator, CustodiaInfoFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected CustodiaInfoValidator<Object> validator = new CustodiaInfoValidator<Object>();

  // EJB's
  @javax.ejb.EJB(mappedName = "portafib/CodiBarresEJB/local")
  protected es.caib.portafib.ejb.CodiBarresLocal codiBarresEjb;

  @javax.ejb.EJB(mappedName = "portafib/CustodiaInfoEJB/local")
  protected es.caib.portafib.ejb.CustodiaInfoLocal custodiaInfoEjb;

  @javax.ejb.EJB(mappedName = "portafib/EntitatEJB/local")
  protected es.caib.portafib.ejb.EntitatLocal entitatEjb;

  @javax.ejb.EJB(mappedName = "portafib/PluginEJB/local")
  protected es.caib.portafib.ejb.PluginLocal pluginEjb;

  @javax.ejb.EJB(mappedName = "portafib/PosicioPaginaEJB/local")
  protected es.caib.portafib.ejb.PosicioPaginaLocal posicioPaginaEjb;

  @javax.ejb.EJB(mappedName = "portafib/UsuariAplicacioEJB/local")
  protected es.caib.portafib.ejb.UsuariAplicacioLocal usuariAplicacioEjb;

  @javax.ejb.EJB(mappedName = "portafib/UsuariEntitatEJB/local")
  protected es.caib.portafib.ejb.UsuariEntitatLocal usuariEntitatEjb;



  public CustodiaInfoWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return CustodiaInfoForm.class.isAssignableFrom(clazz);
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
      isNou, codiBarresEjb, custodiaInfoEjb, entitatEjb, pluginEjb, posicioPaginaEjb, usuariAplicacioEjb, usuariEntitatEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public CustodiaInfoValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(CustodiaInfoValidator<Object> validator) {
    this.validator = validator;
  }

}