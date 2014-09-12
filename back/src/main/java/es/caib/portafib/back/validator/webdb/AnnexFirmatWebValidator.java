package es.caib.portafib.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import es.caib.portafib.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.caib.portafib.jpa.validator.AnnexFirmatValidator;

import es.caib.portafib.back.form.webdb.AnnexFirmatForm;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class AnnexFirmatWebValidator  implements Validator, AnnexFirmatFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected AnnexFirmatValidator<Object> validator = new AnnexFirmatValidator<Object>();

  // EJB's
  @javax.ejb.EJB(mappedName = "portafib/AnnexEJB/local")
  protected es.caib.portafib.ejb.AnnexLocal annexEjb;

  @javax.ejb.EJB(mappedName = "portafib/AnnexFirmatEJB/local")
  protected es.caib.portafib.ejb.AnnexFirmatLocal annexFirmatEjb;

  @javax.ejb.EJB(mappedName = "portafib/FirmaEJB/local")
  protected es.caib.portafib.ejb.FirmaLocal firmaEjb;



  public AnnexFirmatWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return AnnexFirmatForm.class.isAssignableFrom(clazz);
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
      CommonsMultipartFile fitxerID = ((AnnexFirmatForm)target).getFitxerID();
      if (fitxerID == null || fitxerID.isEmpty()) {
        errors.rejectValue(get(FITXERID), "genapp.validation.required",
          new String[]{ org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(get(FITXERID)) },
          null);
      }

    }
    validator.validate(wvr, target,
      isNou, annexEjb, annexFirmatEjb, firmaEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public AnnexFirmatValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(AnnexFirmatValidator<Object> validator) {
    this.validator = validator;
  }

}