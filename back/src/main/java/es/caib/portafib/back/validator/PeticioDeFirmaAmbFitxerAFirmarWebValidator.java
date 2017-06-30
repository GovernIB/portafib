package es.caib.portafib.back.validator;

import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import es.caib.portafib.back.form.webdb.PeticioDeFirmaForm;
import es.caib.portafib.back.validator.webdb.PeticioDeFirmaWebValidator;
/**
 * 
 * @author anadal
 *
 */
@Component
public class PeticioDeFirmaAmbFitxerAFirmarWebValidator extends PeticioDeFirmaWebValidator {

  @Override
  public void validate(Object target, Errors errors,
      WebValidationResult<Object> wvr, boolean isNou) {

      if (isNou) { // Creacio
        // ================ CREATION
        // Fitxers 
        CommonsMultipartFile fitxerAFirmarID = ((PeticioDeFirmaForm)target).getFitxerAFirmarID();
        if (fitxerAFirmarID == null || fitxerAFirmarID.isEmpty()) {
          errors.rejectValue(get(FITXERAFIRMARID), "genapp.validation.required",
            new String[]{ org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(get(FITXERAFIRMARID)) },
            null);
        }

      }
      
      super.validate(target, errors, wvr, isNou);
    } // Final de metode
  
}
