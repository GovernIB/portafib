package es.caib.portafib.back.validator;

import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import es.caib.portafib.back.form.webdb.PeticioDeFirmaForm;
import es.caib.portafib.back.validator.webdb.PeticioDeFirmaWebValidator;
import es.caib.portafib.model.entity.PeticioDeFirma;
/**
 * 
 * @author anadal
 *
 */
@Component
public class PeticioDeFirmaAmbFitxerAFirmarWebValidator extends PeticioDeFirmaWebValidator {

  @Override
  public void validate(PeticioDeFirmaForm __form, PeticioDeFirma __bean, Errors errors,
      WebValidationResult<PeticioDeFirmaForm> wvr, boolean isNou) {

      if (isNou) { // Creacio
        // ================ CREATION
        // Fitxers 
        CommonsMultipartFile fitxerAFirmarID = __form.getFitxerAFirmarID();
        if (fitxerAFirmarID == null || fitxerAFirmarID.isEmpty()) {
          errors.rejectValue(get(FITXERAFIRMARID), "genapp.validation.required",
            new String[]{ org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(get(FITXERAFIRMARID)) },
            null);
        }

      }
      
      super.validate(__form,__bean, errors, wvr, isNou);
    } // Final de metode
  
}
