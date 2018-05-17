package es.caib.portafib.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import es.caib.portafib.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.caib.portafib.jpa.validator.UsuariAplicacioConfiguracioValidator;

import es.caib.portafib.back.form.webdb.UsuariAplicacioConfiguracioForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class UsuariAplicacioConfiguracioWebValidator  implements Validator, UsuariAplicacioConfiguracioFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected UsuariAplicacioConfiguracioValidator<Object> validator = new UsuariAplicacioConfiguracioValidator<Object>();

  // EJB's
  @javax.ejb.EJB(mappedName = "portafib/AlgorismeDeFirmaEJB/local")
  protected es.caib.portafib.ejb.AlgorismeDeFirmaLocal algorismeDeFirmaEjb;

  @javax.ejb.EJB(mappedName = "portafib/CustodiaInfoEJB/local")
  protected es.caib.portafib.ejb.CustodiaInfoLocal custodiaInfoEjb;

  @javax.ejb.EJB(mappedName = "portafib/PluginEJB/local")
  protected es.caib.portafib.ejb.PluginLocal pluginEjb;

  @javax.ejb.EJB(mappedName = "portafib/TipusFirmaEJB/local")
  protected es.caib.portafib.ejb.TipusFirmaLocal tipusFirmaEjb;

  @javax.ejb.EJB(mappedName = "portafib/TraduccioEJB/local")
  protected es.caib.portafib.ejb.TraduccioLocal traduccioEjb;

  @javax.ejb.EJB(mappedName = "portafib/UsuariAplicacioEJB/local")
  protected es.caib.portafib.ejb.UsuariAplicacioLocal usuariAplicacioEjb;

  @javax.ejb.EJB(mappedName = "portafib/UsuariAplicacioConfiguracioEJB/local")
  protected es.caib.portafib.ejb.UsuariAplicacioConfiguracioLocal usuariAplicacioConfiguracioEjb;



  public UsuariAplicacioConfiguracioWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return UsuariAplicacioConfiguracioForm.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {

java.util.List<Field<?>> _ignoreFields = new java.util.ArrayList<Field<?>>();
_ignoreFields.add(FIRMATPERFORMATID);
_ignoreFields.add(MOTIUDELEGACIOID);
    WebValidationResult<Object> wvr;
    wvr = new WebValidationResult<Object>(errors, _ignoreFields);

    Boolean nou = (Boolean)errors.getFieldValue("nou");
    boolean isNou =  nou != null && nou.booleanValue();

    validate(target, errors, wvr, isNou);
  }


  public void validate(Object target, Errors errors,
    WebValidationResult<Object> wvr, boolean isNou) {

  {
    es.caib.portafib.jpa.UsuariAplicacioConfiguracioJPA usuariAplicacioConfiguracio;
    if (target instanceof UsuariAplicacioConfiguracioForm) {
      usuariAplicacioConfiguracio = ((UsuariAplicacioConfiguracioForm)target).getUsuariAplicacioConfiguracio();
    } else {
      usuariAplicacioConfiguracio = (es.caib.portafib.jpa.UsuariAplicacioConfiguracioJPA)target;
    }
    {
      // IF CAMP ES NOT NULL verificar que totes les traduccions son not null
      es.caib.portafib.jpa.TraduccioJPA tradJPA = usuariAplicacioConfiguracio.getFirmatPerFormat();
      if (tradJPA != null) {
        // TODO ERROR
        java.util.Map<String,es.caib.portafib.jpa.TraduccioMapJPA> _trad = tradJPA.getTraduccions();
        int countNull= 0;
        int countNotNull = 0;
        for (String _idioma : _trad.keySet()) {
          es.caib.portafib.jpa.TraduccioMapJPA _map = _trad.get(_idioma);
          if (_map == null || (_map.getValor() == null || _map.getValor().length() == 0 )) {
            countNull++;
          } else {
            countNotNull++;
          }
        }

        if (countNull == _trad.size()) {
          // OK Tot esta buit ==> passam el camp a NULL
          usuariAplicacioConfiguracio.setFirmatPerFormatID(null);
          usuariAplicacioConfiguracio.setFirmatPerFormat(null);
        } else {
          if (countNotNull  == _trad.size()) {
            // OK Tot esta ple
          } else {
            for (String _idioma : _trad.keySet()) {
              es.caib.portafib.jpa.TraduccioMapJPA _map = _trad.get(_idioma);
              if (_map == null || (_map.getValor() == null || _map.getValor().length() == 0 )) {
                errors.rejectValue("usuariAplicacioConfiguracio.firmatPerFormat", "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(FIRMATPERFORMATID.fullName)}, null);
                errors.rejectValue("usuariAplicacioConfiguracio.firmatPerFormat.traduccions["+ _idioma +"].valor",
                  "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(FIRMATPERFORMATID.fullName)}, null);
              }
            }
          }
        }
      } else {
        errors.rejectValue("usuariAplicacioConfiguracio.firmatPerFormat", "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(FIRMATPERFORMATID.fullName)}, null);
      }
    }
    {
      // IF CAMP ES NOT NULL verificar que totes les traduccions son not null
      es.caib.portafib.jpa.TraduccioJPA tradJPA = usuariAplicacioConfiguracio.getMotiuDelegacio();
      if (tradJPA != null) {
        // TODO ERROR
        java.util.Map<String,es.caib.portafib.jpa.TraduccioMapJPA> _trad = tradJPA.getTraduccions();
        int countNull= 0;
        int countNotNull = 0;
        for (String _idioma : _trad.keySet()) {
          es.caib.portafib.jpa.TraduccioMapJPA _map = _trad.get(_idioma);
          if (_map == null || (_map.getValor() == null || _map.getValor().length() == 0 )) {
            countNull++;
          } else {
            countNotNull++;
          }
        }

        if (countNull == _trad.size()) {
          // OK Tot esta buit ==> passam el camp a NULL
          usuariAplicacioConfiguracio.setMotiuDelegacioID(null);
          usuariAplicacioConfiguracio.setMotiuDelegacio(null);
        } else {
          if (countNotNull  == _trad.size()) {
            // OK Tot esta ple
          } else {
            for (String _idioma : _trad.keySet()) {
              es.caib.portafib.jpa.TraduccioMapJPA _map = _trad.get(_idioma);
              if (_map == null || (_map.getValor() == null || _map.getValor().length() == 0 )) {
                errors.rejectValue("usuariAplicacioConfiguracio.motiuDelegacio", "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(MOTIUDELEGACIOID.fullName)}, null);
                errors.rejectValue("usuariAplicacioConfiguracio.motiuDelegacio.traduccions["+ _idioma +"].valor",
                  "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(MOTIUDELEGACIOID.fullName)}, null);
              }
            }
          }
        }
      } else {
        errors.rejectValue("usuariAplicacioConfiguracio.motiuDelegacio", "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(MOTIUDELEGACIOID.fullName)}, null);
      }
    }

  }
    if (isNou) { // Creacio
      // ================ CREATION
      // Fitxers 
    }
    validator.validate(wvr, target,
      isNou, algorismeDeFirmaEjb, custodiaInfoEjb, pluginEjb, tipusFirmaEjb, traduccioEjb, usuariAplicacioEjb, usuariAplicacioConfiguracioEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public UsuariAplicacioConfiguracioValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(UsuariAplicacioConfiguracioValidator<Object> validator) {
    this.validator = validator;
  }

}