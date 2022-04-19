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
import es.caib.portafib.persistence.validator.RoleUsuariEntitatValidator;

import es.caib.portafib.back.form.webdb.RoleUsuariEntitatForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.portafib.model.entity.RoleUsuariEntitat;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class RoleUsuariEntitatWebValidator extends AbstractWebValidator<RoleUsuariEntitatForm, RoleUsuariEntitat>
     implements Validator, RoleUsuariEntitatFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected RoleUsuariEntitatValidator<RoleUsuariEntitat> validator = new RoleUsuariEntitatValidator<RoleUsuariEntitat>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.RoleService.JNDI_NAME)
  protected es.caib.portafib.ejb.RoleService roleEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.RoleUsuariEntitatService.JNDI_NAME)
  protected es.caib.portafib.ejb.RoleUsuariEntitatService roleUsuariEntitatEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.UsuariEntitatService.JNDI_NAME)
  protected es.caib.portafib.ejb.UsuariEntitatService usuariEntitatEjb;



  public RoleUsuariEntitatWebValidator() {
    super();    
  }
  
  @Override
  public RoleUsuariEntitat getBeanOfForm(RoleUsuariEntitatForm form) {
    return  form.getRoleUsuariEntitat();
  }

  @Override
  public Class<RoleUsuariEntitatForm> getClassOfForm() {
    return RoleUsuariEntitatForm.class;
  }

  @Override
  public void validate(RoleUsuariEntitatForm __form, RoleUsuariEntitat __bean, Errors errors) {

    WebValidationResult<RoleUsuariEntitatForm> wvr;
    wvr = new WebValidationResult<RoleUsuariEntitatForm>(errors);

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


  public void validate(RoleUsuariEntitatForm __form, RoleUsuariEntitat __bean, Errors errors,
    WebValidationResult<RoleUsuariEntitatForm> wvr, boolean isNou) {

    BeanValidatorResult<RoleUsuariEntitat> __vr = new BeanValidatorResult<RoleUsuariEntitat>();
    validator.validate(__vr, __bean,
      isNou, roleEjb, roleUsuariEntitatEjb, usuariEntitatEjb);

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

  public RoleUsuariEntitatValidator<RoleUsuariEntitat> getValidator() {
    return validator;
  }

  public void setValidator(RoleUsuariEntitatValidator<RoleUsuariEntitat> validator) {
    this.validator = validator;
  }

}