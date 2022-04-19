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
import es.caib.portafib.persistence.validator.BitacolaValidator;

import es.caib.portafib.back.form.webdb.BitacolaForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.portafib.model.entity.Bitacola;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class BitacolaWebValidator extends AbstractWebValidator<BitacolaForm, Bitacola>
     implements Validator, BitacolaFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected BitacolaValidator<Bitacola> validator = new BitacolaValidator<Bitacola>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.BitacolaService.JNDI_NAME)
  protected es.caib.portafib.ejb.BitacolaService bitacolaEjb;



  public BitacolaWebValidator() {
    super();    
  }
  
  @Override
  public Bitacola getBeanOfForm(BitacolaForm form) {
    return  form.getBitacola();
  }

  @Override
  public Class<BitacolaForm> getClassOfForm() {
    return BitacolaForm.class;
  }

  @Override
  public void validate(BitacolaForm __form, Bitacola __bean, Errors errors) {

    WebValidationResult<BitacolaForm> wvr;
    wvr = new WebValidationResult<BitacolaForm>(errors);

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


  public void validate(BitacolaForm __form, Bitacola __bean, Errors errors,
    WebValidationResult<BitacolaForm> wvr, boolean isNou) {

    BeanValidatorResult<Bitacola> __vr = new BeanValidatorResult<Bitacola>();
    validator.validate(__vr, __bean,
      isNou, bitacolaEjb);

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

  public BitacolaValidator<Bitacola> getValidator() {
    return validator;
  }

  public void setValidator(BitacolaValidator<Bitacola> validator) {
    this.validator = validator;
  }

}