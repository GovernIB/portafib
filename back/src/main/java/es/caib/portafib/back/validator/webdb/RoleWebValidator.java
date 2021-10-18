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
import es.caib.portafib.persistence.validator.RoleValidator;

import es.caib.portafib.back.form.webdb.RoleForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.portafib.model.entity.Role;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class RoleWebValidator extends AbstractWebValidator<RoleForm, Role>
     implements Validator, RoleFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected RoleValidator<Role> validator = new RoleValidator<Role>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.RoleService.JNDI_NAME)
  protected es.caib.portafib.ejb.RoleService roleEjb;



  public RoleWebValidator() {
    super();    
  }
  
  @Override
  public Role getBeanOfForm(RoleForm form) {
    return  form.getRole();
  }

  @Override
  public Class<RoleForm> getClassOfForm() {
    return RoleForm.class;
  }

  @Override
  public void validate(RoleForm __form, Role __bean, Errors errors) {

    WebValidationResult<RoleForm> wvr;
    wvr = new WebValidationResult<RoleForm>(errors);

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


  public void validate(RoleForm __form, Role __bean, Errors errors,
    WebValidationResult<RoleForm> wvr, boolean isNou) {

    BeanValidatorResult<Role> __vr = new BeanValidatorResult<Role>();
    validator.validate(__vr, __bean,
      isNou, roleEjb);

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

  public RoleValidator<Role> getValidator() {
    return validator;
  }

  public void setValidator(RoleValidator<Role> validator) {
    this.validator = validator;
  }

}