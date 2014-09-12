package es.caib.portafib.back.validator;

import es.caib.portafib.back.form.SeleccioUsuariEntitatForm;
import es.caib.portafib.model.fields.UsuariPersonaFields;
import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


/**
 * Created 4/06/13 9:15
 *
 * @author mgonzalez
 */
@Component
public class SelectUsuariEntitatValidator implements Validator {

  protected final Logger log = Logger.getLogger(getClass());


  public SelectUsuariEntitatValidator() {
    super();
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return SeleccioUsuariEntitatForm.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {

    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nif",
    "genapp.validation.required", new Object[] { I18NUtils.tradueix(get(UsuariPersonaFields.NIF)) });

  }
  
  public String get(Field<?> field) {
    return field.fullName;
  }


}
