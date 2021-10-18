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
import es.caib.portafib.persistence.validator.GrupEntitatValidator;

import es.caib.portafib.back.form.webdb.GrupEntitatForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.portafib.model.entity.GrupEntitat;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class GrupEntitatWebValidator extends AbstractWebValidator<GrupEntitatForm, GrupEntitat>
     implements Validator, GrupEntitatFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected GrupEntitatValidator<GrupEntitat> validator = new GrupEntitatValidator<GrupEntitat>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.EntitatService.JNDI_NAME)
  protected es.caib.portafib.ejb.EntitatService entitatEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.GrupEntitatService.JNDI_NAME)
  protected es.caib.portafib.ejb.GrupEntitatService grupEntitatEjb;



  public GrupEntitatWebValidator() {
    super();    
  }
  
  @Override
  public GrupEntitat getBeanOfForm(GrupEntitatForm form) {
    return  form.getGrupEntitat();
  }

  @Override
  public Class<GrupEntitatForm> getClassOfForm() {
    return GrupEntitatForm.class;
  }

  @Override
  public void validate(GrupEntitatForm __form, GrupEntitat __bean, Errors errors) {

    WebValidationResult<GrupEntitatForm> wvr;
    wvr = new WebValidationResult<GrupEntitatForm>(errors);

    boolean isNou;
    {
        Object objNou = errors.getFieldValue("nou");
        if (objNou == null) {
            isNou = false;
        } else { 
         Boolean nou = Boolean.parseBoolean((String)objNou);
         isNou =  nou != null && nou.booleanValue();
        }
    }

    validate(__form, __bean , errors, wvr, isNou);
  }


  public void validate(GrupEntitatForm __form, GrupEntitat __bean, Errors errors,
    WebValidationResult<GrupEntitatForm> wvr, boolean isNou) {

    BeanValidatorResult<GrupEntitat> __vr = new BeanValidatorResult<GrupEntitat>();
    validator.validate(__vr, __bean,
      isNou, entitatEjb, grupEntitatEjb);

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

  public GrupEntitatValidator<GrupEntitat> getValidator() {
    return validator;
  }

  public void setValidator(GrupEntitatValidator<GrupEntitat> validator) {
    this.validator = validator;
  }

}