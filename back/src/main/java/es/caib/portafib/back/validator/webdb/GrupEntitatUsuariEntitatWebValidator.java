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
import es.caib.portafib.persistence.validator.GrupEntitatUsuariEntitatValidator;

import es.caib.portafib.back.form.webdb.GrupEntitatUsuariEntitatForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.portafib.model.entity.GrupEntitatUsuariEntitat;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class GrupEntitatUsuariEntitatWebValidator extends AbstractWebValidator<GrupEntitatUsuariEntitatForm, GrupEntitatUsuariEntitat>
     implements Validator, GrupEntitatUsuariEntitatFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected GrupEntitatUsuariEntitatValidator<GrupEntitatUsuariEntitat> validator = new GrupEntitatUsuariEntitatValidator<GrupEntitatUsuariEntitat>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.GrupEntitatService.JNDI_NAME)
  protected es.caib.portafib.ejb.GrupEntitatService grupEntitatEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.GrupEntitatUsuariEntitatService.JNDI_NAME)
  protected es.caib.portafib.ejb.GrupEntitatUsuariEntitatService grupEntitatUsuariEntitatEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.UsuariEntitatService.JNDI_NAME)
  protected es.caib.portafib.ejb.UsuariEntitatService usuariEntitatEjb;



  public GrupEntitatUsuariEntitatWebValidator() {
    super();    
  }
  
  @Override
  public GrupEntitatUsuariEntitat getBeanOfForm(GrupEntitatUsuariEntitatForm form) {
    return  form.getGrupEntitatUsuariEntitat();
  }

  @Override
  public Class<GrupEntitatUsuariEntitatForm> getClassOfForm() {
    return GrupEntitatUsuariEntitatForm.class;
  }

  @Override
  public void validate(GrupEntitatUsuariEntitatForm __form, GrupEntitatUsuariEntitat __bean, Errors errors) {

    WebValidationResult<GrupEntitatUsuariEntitatForm> wvr;
    wvr = new WebValidationResult<GrupEntitatUsuariEntitatForm>(errors);

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


  public void validate(GrupEntitatUsuariEntitatForm __form, GrupEntitatUsuariEntitat __bean, Errors errors,
    WebValidationResult<GrupEntitatUsuariEntitatForm> wvr, boolean isNou) {

    BeanValidatorResult<GrupEntitatUsuariEntitat> __vr = new BeanValidatorResult<GrupEntitatUsuariEntitat>();
    validator.validate(__vr, __bean,
      isNou, grupEntitatEjb, grupEntitatUsuariEntitatEjb, usuariEntitatEjb);

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

  public GrupEntitatUsuariEntitatValidator<GrupEntitatUsuariEntitat> getValidator() {
    return validator;
  }

  public void setValidator(GrupEntitatUsuariEntitatValidator<GrupEntitatUsuariEntitat> validator) {
    this.validator = validator;
  }

}