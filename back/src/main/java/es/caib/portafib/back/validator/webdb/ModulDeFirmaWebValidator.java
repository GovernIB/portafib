package es.caib.portafib.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import es.caib.portafib.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.caib.portafib.jpa.validator.ModulDeFirmaValidator;

import es.caib.portafib.back.form.webdb.ModulDeFirmaForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class ModulDeFirmaWebValidator  implements Validator, ModulDeFirmaFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected ModulDeFirmaValidator<Object> validator = new ModulDeFirmaValidator<Object>();

  // EJB's
  @javax.ejb.EJB(mappedName = "portafib/EntitatEJB/local")
  protected es.caib.portafib.ejb.EntitatLocal entitatEjb;

  @javax.ejb.EJB(mappedName = "portafib/ModulDeFirmaEJB/local")
  protected es.caib.portafib.ejb.ModulDeFirmaLocal modulDeFirmaEjb;

  @javax.ejb.EJB(mappedName = "portafib/TraduccioEJB/local")
  protected es.caib.portafib.ejb.TraduccioLocal traduccioEjb;



  public ModulDeFirmaWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return ModulDeFirmaForm.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {

java.util.List<Field<?>> _ignoreFields = new java.util.ArrayList<Field<?>>();
_ignoreFields.add(NOMID);
_ignoreFields.add(DESCRIPCIOCURTAID);
    WebValidationResult<Object> wvr;
    wvr = new WebValidationResult<Object>(errors, _ignoreFields);

    Boolean nou = (Boolean)errors.getFieldValue("nou");
    boolean isNou =  nou != null && nou.booleanValue();

    validate(target, errors, wvr, isNou);
  }


  public void validate(Object target, Errors errors,
    WebValidationResult<Object> wvr, boolean isNou) {

  {
    es.caib.portafib.jpa.ModulDeFirmaJPA modulDeFirma;
    if (target instanceof ModulDeFirmaForm) {
      modulDeFirma = ((ModulDeFirmaForm)target).getModulDeFirma();
    } else {
      modulDeFirma = (es.caib.portafib.jpa.ModulDeFirmaJPA)target;
    }
    {
    // IF CAMP ES NOT NULL verificar que totes les traduccions son not null
    es.caib.portafib.jpa.TraduccioJPA tradJPA = modulDeFirma.getNom();
    if (tradJPA == null) {
      // TODO ERROR
      errors.rejectValue("modulDeFirma.nom", "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(NOMID.fullName)}, null);
    } else {
      java.util.Map<String,es.caib.portafib.jpa.TraduccioMapJPA> _trad = tradJPA.getTraduccions();
      for (String _idioma : _trad.keySet()) {
        es.caib.portafib.jpa.TraduccioMapJPA _map = _trad.get(_idioma);
        if (_map == null || _map.getValor() == null) {
          errors.rejectValue("modulDeFirma.nom", "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(NOMID.fullName)}, null);
          errors.rejectValue("modulDeFirma.nom.traduccions["+ _idioma +"].valor",
              "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(NOMID.fullName)}, null);
        }
      }
    }
    }
    {
    // IF CAMP ES NOT NULL verificar que totes les traduccions son not null
    es.caib.portafib.jpa.TraduccioJPA tradJPA = modulDeFirma.getDescripcioCurta();
    if (tradJPA == null) {
      // TODO ERROR
      errors.rejectValue("modulDeFirma.descripcioCurta", "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(DESCRIPCIOCURTAID.fullName)}, null);
    } else {
      java.util.Map<String,es.caib.portafib.jpa.TraduccioMapJPA> _trad = tradJPA.getTraduccions();
      for (String _idioma : _trad.keySet()) {
        es.caib.portafib.jpa.TraduccioMapJPA _map = _trad.get(_idioma);
        if (_map == null || _map.getValor() == null) {
          errors.rejectValue("modulDeFirma.descripcioCurta", "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(DESCRIPCIOCURTAID.fullName)}, null);
          errors.rejectValue("modulDeFirma.descripcioCurta.traduccions["+ _idioma +"].valor",
              "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(DESCRIPCIOCURTAID.fullName)}, null);
        }
      }
    }
    }

  }
    validator.validate(wvr, target,
      isNou, entitatEjb, modulDeFirmaEjb, traduccioEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public ModulDeFirmaValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(ModulDeFirmaValidator<Object> validator) {
    this.validator = validator;
  }

}