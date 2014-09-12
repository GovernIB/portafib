package es.caib.portafib.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import es.caib.portafib.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.caib.portafib.jpa.validator.EntitatValidator;

import es.caib.portafib.back.form.webdb.EntitatForm;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class EntitatWebValidator  implements Validator, EntitatFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected EntitatValidator<Object> validator = new EntitatValidator<Object>();

  // EJB's
  @javax.ejb.EJB(mappedName = "portafib/EntitatEJB/local")
  protected es.caib.portafib.ejb.EntitatLocal entitatEjb;

  @javax.ejb.EJB(mappedName = "portafib/UsuariAplicacioEJB/local")
  protected es.caib.portafib.ejb.UsuariAplicacioLocal usuariAplicacioEjb;



  public EntitatWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return EntitatForm.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {

    WebValidationResult<Object> wvr;
    wvr = new WebValidationResult<Object>(errors);

    Boolean nou = (Boolean)errors.getFieldValue("nou");
    boolean isNou =  nou != null && nou.booleanValue();

    validate(target, errors, wvr, isNou);
  }


  public void validate(Object target, Errors errors,
    WebValidationResult<Object> wvr, boolean isNou) {

    if (isNou) { // Creacio
      // ================ CREATION
      // Fitxers 
      CommonsMultipartFile faviconID = ((EntitatForm)target).getFaviconID();
      if (faviconID == null || faviconID.isEmpty()) {
        errors.rejectValue(get(FAVICONID), "genapp.validation.required",
          new String[]{ org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(get(FAVICONID)) },
          null);
      }

      CommonsMultipartFile logoWebID = ((EntitatForm)target).getLogoWebID();
      if (logoWebID == null || logoWebID.isEmpty()) {
        errors.rejectValue(get(LOGOWEBID), "genapp.validation.required",
          new String[]{ org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(get(LOGOWEBID)) },
          null);
      }

      CommonsMultipartFile logoWebPeuID = ((EntitatForm)target).getLogoWebPeuID();
      if (logoWebPeuID == null || logoWebPeuID.isEmpty()) {
        errors.rejectValue(get(LOGOWEBPEUID), "genapp.validation.required",
          new String[]{ org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(get(LOGOWEBPEUID)) },
          null);
      }

      CommonsMultipartFile logoSegellID = ((EntitatForm)target).getLogoSegellID();
      if (logoSegellID == null || logoSegellID.isEmpty()) {
        errors.rejectValue(get(LOGOSEGELLID), "genapp.validation.required",
          new String[]{ org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(get(LOGOSEGELLID)) },
          null);
      }

      CommonsMultipartFile pdfAutoritzacioDelegacioID = ((EntitatForm)target).getPdfAutoritzacioDelegacioID();
      if (pdfAutoritzacioDelegacioID == null || pdfAutoritzacioDelegacioID.isEmpty()) {
        errors.rejectValue(get(PDFAUTORITZACIODELEGACIOID), "genapp.validation.required",
          new String[]{ org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(get(PDFAUTORITZACIODELEGACIOID)) },
          null);
      }

    }
    validator.validate(wvr, target,
      isNou, entitatEjb, usuariAplicacioEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public EntitatValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(EntitatValidator<Object> validator) {
    this.validator = validator;
  }

}