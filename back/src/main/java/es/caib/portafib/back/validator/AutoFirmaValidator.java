package es.caib.portafib.back.validator;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import es.caib.portafib.back.form.AutoFirmaForm;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;

/**
 * 
 * @author anadal
 *
 */
@Component
public class AutoFirmaValidator implements Validator, PeticioDeFirmaFields {

  protected final Logger log = Logger.getLogger(getClass());
  

  public AutoFirmaValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return AutoFirmaForm.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {

    // Valors Not Null
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, TITOL.javaName,
        "genapp.validation.required", new Object[] { I18NUtils.tradueix(get(TITOL)) });
    
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, DESCRIPCIO.javaName,
        "genapp.validation.required", new Object[] { I18NUtils.tradueix(get(DESCRIPCIO)) });
  
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, POSICIOTAULAFIRMESID.javaName,
        "genapp.validation.required", new Object[] { I18NUtils.tradueix(get(POSICIOTAULAFIRMESID)) });
    
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, MOTIU.javaName,
        "genapp.validation.required", new Object[] { I18NUtils.tradueix(get(MOTIU)) });
  
    
    //ValidationUtils.rejectIfEmpty(errors, FITXERAFIRMARID.javaName,
    //    "genapp.validation.required", new Object[] { I18NUtils.tradueix(get(FITXERAFIRMARID)) });
    
    CommonsMultipartFile fitxerAFirmarID = ((AutoFirmaForm)target).getFitxerAFirmarID();
    
    if (fitxerAFirmarID == null || fitxerAFirmarID.isEmpty()) {
      errors.rejectValue(FITXERAFIRMARID.javaName, "genapp.validation.required",
          new String[]{ I18NUtils.tradueix(get(FITXERAFIRMARID)) },
          null);
    }

  }

  
  public String get(Field<?> field) {
    return field.fullName;
  }

}
