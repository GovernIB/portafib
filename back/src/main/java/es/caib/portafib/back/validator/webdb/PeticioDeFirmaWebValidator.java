package es.caib.portafib.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import es.caib.portafib.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.caib.portafib.jpa.validator.PeticioDeFirmaValidator;

import es.caib.portafib.back.form.webdb.PeticioDeFirmaForm;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class PeticioDeFirmaWebValidator  implements Validator, PeticioDeFirmaFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected PeticioDeFirmaValidator<Object> validator = new PeticioDeFirmaValidator<Object>();

  // EJB's
  @javax.ejb.EJB(mappedName = "portafib/AlgorismeDeFirmaEJB/local")
  protected es.caib.portafib.ejb.AlgorismeDeFirmaLocal algorismeDeFirmaEjb;

  @javax.ejb.EJB(mappedName = "portafib/CustodiaInfoEJB/local")
  protected es.caib.portafib.ejb.CustodiaInfoLocal custodiaInfoEjb;

  @javax.ejb.EJB(mappedName = "portafib/FluxDeFirmesEJB/local")
  protected es.caib.portafib.ejb.FluxDeFirmesLocal fluxDeFirmesEjb;

  @javax.ejb.EJB(mappedName = "portafib/IdiomaEJB/local")
  protected es.caib.portafib.ejb.IdiomaLocal idiomaEjb;

  @javax.ejb.EJB(mappedName = "portafib/PeticioDeFirmaEJB/local")
  protected es.caib.portafib.ejb.PeticioDeFirmaLocal peticioDeFirmaEjb;

  @javax.ejb.EJB(mappedName = "portafib/PosicioTaulaFirmesEJB/local")
  protected es.caib.portafib.ejb.PosicioTaulaFirmesLocal posicioTaulaFirmesEjb;

  @javax.ejb.EJB(mappedName = "portafib/PrioritatEJB/local")
  protected es.caib.portafib.ejb.PrioritatLocal prioritatEjb;

  @javax.ejb.EJB(mappedName = "portafib/TipusDocumentEJB/local")
  protected es.caib.portafib.ejb.TipusDocumentLocal tipusDocumentEjb;

  @javax.ejb.EJB(mappedName = "portafib/TipusEstatPeticioDeFirmaEJB/local")
  protected es.caib.portafib.ejb.TipusEstatPeticioDeFirmaLocal tipusEstatPeticioDeFirmaEjb;

  @javax.ejb.EJB(mappedName = "portafib/TipusFirmaEJB/local")
  protected es.caib.portafib.ejb.TipusFirmaLocal tipusFirmaEjb;

  @javax.ejb.EJB(mappedName = "portafib/UsuariAplicacioEJB/local")
  protected es.caib.portafib.ejb.UsuariAplicacioLocal usuariAplicacioEjb;

  @javax.ejb.EJB(mappedName = "portafib/UsuariEntitatEJB/local")
  protected es.caib.portafib.ejb.UsuariEntitatLocal usuariEntitatEjb;



  public PeticioDeFirmaWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return PeticioDeFirmaForm.class.isAssignableFrom(clazz);
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
      CommonsMultipartFile fitxerAFirmarID = ((PeticioDeFirmaForm)target).getFitxerAFirmarID();
      if (fitxerAFirmarID == null || fitxerAFirmarID.isEmpty()) {
        errors.rejectValue(get(FITXERAFIRMARID), "genapp.validation.required",
          new String[]{ org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(get(FITXERAFIRMARID)) },
          null);
      }

    }
    validator.validate(wvr, target,
      isNou, algorismeDeFirmaEjb, custodiaInfoEjb, fluxDeFirmesEjb, idiomaEjb, peticioDeFirmaEjb, posicioTaulaFirmesEjb, prioritatEjb, tipusDocumentEjb, tipusEstatPeticioDeFirmaEjb, tipusFirmaEjb, usuariAplicacioEjb, usuariEntitatEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public PeticioDeFirmaValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(PeticioDeFirmaValidator<Object> validator) {
    this.validator = validator;
  }

}