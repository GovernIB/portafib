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
import es.caib.portafib.persistence.validator.UsuariEntitatFavoritValidator;

import es.caib.portafib.back.form.webdb.UsuariEntitatFavoritForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.portafib.model.entity.UsuariEntitatFavorit;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class UsuariEntitatFavoritWebValidator extends AbstractWebValidator<UsuariEntitatFavoritForm, UsuariEntitatFavorit>
     implements Validator, UsuariEntitatFavoritFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected UsuariEntitatFavoritValidator<UsuariEntitatFavorit> validator = new UsuariEntitatFavoritValidator<UsuariEntitatFavorit>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.UsuariEntitatService.JNDI_NAME)
  protected es.caib.portafib.ejb.UsuariEntitatService usuariEntitatEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.UsuariEntitatFavoritService.JNDI_NAME)
  protected es.caib.portafib.ejb.UsuariEntitatFavoritService usuariEntitatFavoritEjb;



  public UsuariEntitatFavoritWebValidator() {
    super();    
  }
  
  @Override
  public UsuariEntitatFavorit getBeanOfForm(UsuariEntitatFavoritForm form) {
    return  form.getUsuariEntitatFavorit();
  }

  @Override
  public Class<UsuariEntitatFavoritForm> getClassOfForm() {
    return UsuariEntitatFavoritForm.class;
  }

  @Override
  public void validate(UsuariEntitatFavoritForm __form, UsuariEntitatFavorit __bean, Errors errors) {

    WebValidationResult<UsuariEntitatFavoritForm> wvr;
    wvr = new WebValidationResult<UsuariEntitatFavoritForm>(errors);

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


  public void validate(UsuariEntitatFavoritForm __form, UsuariEntitatFavorit __bean, Errors errors,
    WebValidationResult<UsuariEntitatFavoritForm> wvr, boolean isNou) {

    BeanValidatorResult<UsuariEntitatFavorit> __vr = new BeanValidatorResult<UsuariEntitatFavorit>();
    validator.validate(__vr, __bean,
      isNou, usuariEntitatEjb, usuariEntitatFavoritEjb);

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

  public UsuariEntitatFavoritValidator<UsuariEntitatFavorit> getValidator() {
    return validator;
  }

  public void setValidator(UsuariEntitatFavoritValidator<UsuariEntitatFavorit> validator) {
    this.validator = validator;
  }

}