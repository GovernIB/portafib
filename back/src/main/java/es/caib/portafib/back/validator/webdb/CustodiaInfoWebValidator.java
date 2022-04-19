package es.caib.portafib.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.validation.BeanValidatorResult;
import org.fundaciobit.genapp.common.i18n.I18NFieldError;
import java.util.List;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import es.caib.portafib.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.caib.portafib.persistence.validator.CustodiaInfoValidator;

import es.caib.portafib.back.form.webdb.CustodiaInfoForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.portafib.model.entity.CustodiaInfo;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class CustodiaInfoWebValidator extends AbstractWebValidator<CustodiaInfoForm, CustodiaInfo>
     implements Validator, CustodiaInfoFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected CustodiaInfoValidator<CustodiaInfo> validator = new CustodiaInfoValidator<CustodiaInfo>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.CodiBarresService.JNDI_NAME)
  protected es.caib.portafib.ejb.CodiBarresService codiBarresEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.CustodiaInfoService.JNDI_NAME)
  protected es.caib.portafib.ejb.CustodiaInfoService custodiaInfoEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.EntitatService.JNDI_NAME)
  protected es.caib.portafib.ejb.EntitatService entitatEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.PluginService.JNDI_NAME)
  protected es.caib.portafib.ejb.PluginService pluginEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.UsuariAplicacioService.JNDI_NAME)
  protected es.caib.portafib.ejb.UsuariAplicacioService usuariAplicacioEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.UsuariEntitatService.JNDI_NAME)
  protected es.caib.portafib.ejb.UsuariEntitatService usuariEntitatEjb;



  public CustodiaInfoWebValidator() {
    super();    
  }
  
  @Override
  public CustodiaInfo getBeanOfForm(CustodiaInfoForm form) {
    return  form.getCustodiaInfo();
  }

  @Override
  public Class<CustodiaInfoForm> getClassOfForm() {
    return CustodiaInfoForm.class;
  }

  @Override
  public void validate(CustodiaInfoForm __form, CustodiaInfo __bean, Errors errors) {

    WebValidationResult<CustodiaInfoForm> wvr;
    wvr = new WebValidationResult<CustodiaInfoForm>(errors);

    boolean isNou;
    {
        Object objNou = errors.getFieldValue("nou");
        if (objNou == null) {
            isNou = false;
        } else { 
         Boolean nou = Boolean.parseBoolean(String.valueOf(objNou));
         isNou =  nou != null && nou.booleanValue();
        }
    }

    validate(__form, __bean , errors, wvr, isNou);
  }


  public void validate(CustodiaInfoForm __form, CustodiaInfo __bean, Errors errors,
    WebValidationResult<CustodiaInfoForm> wvr, boolean isNou) {

    BeanValidatorResult<CustodiaInfo> __vr = new BeanValidatorResult<CustodiaInfo>();
    validator.validate(__vr, __bean,
      isNou, codiBarresEjb, custodiaInfoEjb, entitatEjb, pluginEjb, usuariAplicacioEjb, usuariEntitatEjb);

    if (__vr.hasErrors()) {
        List<I18NFieldError> vrErrors = __vr.getErrors();
    	   for (I18NFieldError i18nFieldError : vrErrors) {
    	       wvr.rejectValue(i18nFieldError.getField(), i18nFieldError.getTranslation().getCode(), i18nFieldError.getTranslation().getArgs());
        }
    }


  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public CustodiaInfoValidator<CustodiaInfo> getValidator() {
    return validator;
  }

  public void setValidator(CustodiaInfoValidator<CustodiaInfo> validator) {
    this.validator = validator;
  }

}