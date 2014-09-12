package es.caib.portafib.back.validator;

import es.caib.portafib.back.form.SeleccioCarrecForm;
import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created 2/07/13 13:03
 *
 * @author mgonzalez
 */
@Component
public class SelectCarrecValidator implements Validator {

protected final Logger log = Logger.getLogger(getClass());


  public SelectCarrecValidator() {
    super();
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return SeleccioCarrecForm.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {

    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "carrec",
              "genapp.validation.required", new Object[] { I18NUtils.tradueix("carrec") });

    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idCarrec",
              "genapp.validation.required", new Object[] { I18NUtils.tradueix("carrec.id") });

    // Verificam que id Carrec compleixi l'expresió del matcher.
    {
      String val = String.valueOf(errors.getFieldValue("idCarrec"));
      if (val != null && val.trim().length() != 0) {
        java.util.regex.Pattern p = java.util.regex.Pattern.compile("[a-zA-Z0-9._-]{3,}");
        if (!p.matcher(val).matches()) {
              errors.rejectValue("idCarrec", "genapp.validation.malformed",
                 new String[]{val, I18NUtils.tradueix("carrec.id")},
                 null);
        }
      }
    }

  }

}
