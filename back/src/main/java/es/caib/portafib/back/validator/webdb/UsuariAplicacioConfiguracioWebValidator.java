package es.caib.portafib.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.validation.BeanValidatorResult;
import org.fundaciobit.genapp.common.i18n.I18NFieldError;
import java.util.List;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import es.caib.portafib.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.caib.portafib.persistence.validator.UsuariAplicacioConfiguracioValidator;

import es.caib.portafib.back.form.webdb.UsuariAplicacioConfiguracioForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.portafib.model.entity.UsuariAplicacioConfiguracio;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class UsuariAplicacioConfiguracioWebValidator extends AbstractWebValidator<UsuariAplicacioConfiguracioForm, UsuariAplicacioConfiguracio>
     implements Validator, UsuariAplicacioConfiguracioFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected UsuariAplicacioConfiguracioValidator<UsuariAplicacioConfiguracio> validator = new UsuariAplicacioConfiguracioValidator<UsuariAplicacioConfiguracio>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.EntitatService.JNDI_NAME)
  protected es.caib.portafib.ejb.EntitatService entitatEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.PluginService.JNDI_NAME)
  protected es.caib.portafib.ejb.PluginService pluginEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.TraduccioService.JNDI_NAME)
  protected es.caib.portafib.ejb.TraduccioService traduccioEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.UsuariAplicacioConfiguracioService.JNDI_NAME)
  protected es.caib.portafib.ejb.UsuariAplicacioConfiguracioService usuariAplicacioConfiguracioEjb;



  public UsuariAplicacioConfiguracioWebValidator() {
    super();    
  }
  
  @Override
  public UsuariAplicacioConfiguracio getBeanOfForm(UsuariAplicacioConfiguracioForm form) {
    return  form.getUsuariAplicacioConfiguracio();
  }

  @Override
  public Class<UsuariAplicacioConfiguracioForm> getClassOfForm() {
    return UsuariAplicacioConfiguracioForm.class;
  }

  @Override
  public void validate(UsuariAplicacioConfiguracioForm __form, UsuariAplicacioConfiguracio __bean, Errors errors) {

java.util.List<Field<?>> _ignoreFields = new java.util.ArrayList<Field<?>>();
_ignoreFields.add(MOTIUDELEGACIOID);
_ignoreFields.add(FIRMATPERFORMATID);
    WebValidationResult<UsuariAplicacioConfiguracioForm> wvr;
    wvr = new WebValidationResult<UsuariAplicacioConfiguracioForm>(errors, _ignoreFields);

    boolean isNou;
    {
        Object objNou = errors.getFieldValue("nou");
        if (objNou == null) {
            isNou = false;
        } else { 
         Boolean nou = Boolean.parseBoolean(String.valueOf(objNou));
         isNou =  nou != null && nou.booleanValue();
        }
    }

    validate(__form, __bean , errors, wvr, isNou);
  }


  public void validate(UsuariAplicacioConfiguracioForm __form, UsuariAplicacioConfiguracio __bean, Errors errors,
    WebValidationResult<UsuariAplicacioConfiguracioForm> wvr, boolean isNou) {

  {
      es.caib.portafib.persistence.UsuariAplicacioConfiguracioJPA __jpa;
      __jpa = (es.caib.portafib.persistence.UsuariAplicacioConfiguracioJPA)__bean;
    {
      // IF CAMP ES NOT NULL verificar que totes les traduccions son not null
      es.caib.portafib.persistence.TraduccioJPA tradJPA = __jpa.getMotiuDelegacio();
      if (tradJPA != null) {
        // TODO ERROR
        java.util.Map<String,es.caib.portafib.persistence.TraduccioMapJPA> _trad = tradJPA.getTraduccions();
        int countNull= 0;
        int countNotNull = 0;
        for (String _idioma : _trad.keySet()) {
          es.caib.portafib.persistence.TraduccioMapJPA _map = _trad.get(_idioma);
          if (_map == null || (_map.getValor() == null || _map.getValor().length() == 0 )) {
            countNull++;
          } else {
            countNotNull++;
          }
        }

        if (countNull == _trad.size()) {
          // OK Tot esta buit ==> passam el camp a NULL
          __jpa.setMotiuDelegacioID(null);
          __jpa.setMotiuDelegacio(null);
        } else {
          if (countNotNull  == _trad.size()) {
            // OK Tot esta ple
          } else {
            for (String _idioma : _trad.keySet()) {
              es.caib.portafib.persistence.TraduccioMapJPA _map = _trad.get(_idioma);
              if (_map == null || (_map.getValor() == null || _map.getValor().length() == 0 )) {
                errors.rejectValue("usuariAplicacioConfiguracio.motiuDelegacio", "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(MOTIUDELEGACIOID.fullName)}, null);
                errors.rejectValue("usuariAplicacioConfiguracio.motiuDelegacio.traduccions["+ _idioma +"].valor",
                  "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(MOTIUDELEGACIOID.fullName)}, null);
              }
            }
          }
        }
      } else {
          __jpa.setMotiuDelegacioID(null);
          __jpa.setMotiuDelegacio(null);
      }
    }
    {
      // IF CAMP ES NOT NULL verificar que totes les traduccions son not null
      es.caib.portafib.persistence.TraduccioJPA tradJPA = __jpa.getFirmatPerFormat();
      if (tradJPA != null) {
        // TODO ERROR
        java.util.Map<String,es.caib.portafib.persistence.TraduccioMapJPA> _trad = tradJPA.getTraduccions();
        int countNull= 0;
        int countNotNull = 0;
        for (String _idioma : _trad.keySet()) {
          es.caib.portafib.persistence.TraduccioMapJPA _map = _trad.get(_idioma);
          if (_map == null || (_map.getValor() == null || _map.getValor().length() == 0 )) {
            countNull++;
          } else {
            countNotNull++;
          }
        }

        if (countNull == _trad.size()) {
          // OK Tot esta buit ==> passam el camp a NULL
          __jpa.setFirmatPerFormatID(null);
          __jpa.setFirmatPerFormat(null);
        } else {
          if (countNotNull  == _trad.size()) {
            // OK Tot esta ple
          } else {
            for (String _idioma : _trad.keySet()) {
              es.caib.portafib.persistence.TraduccioMapJPA _map = _trad.get(_idioma);
              if (_map == null || (_map.getValor() == null || _map.getValor().length() == 0 )) {
                errors.rejectValue("usuariAplicacioConfiguracio.firmatPerFormat", "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(FIRMATPERFORMATID.fullName)}, null);
                errors.rejectValue("usuariAplicacioConfiguracio.firmatPerFormat.traduccions["+ _idioma +"].valor",
                  "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(FIRMATPERFORMATID.fullName)}, null);
              }
            }
          }
        }
      } else {
          __jpa.setFirmatPerFormatID(null);
          __jpa.setFirmatPerFormat(null);
      }
    }

  }
    BeanValidatorResult<UsuariAplicacioConfiguracio> __vr = new BeanValidatorResult<UsuariAplicacioConfiguracio>();
    validator.validate(__vr, __bean,
      isNou, entitatEjb, pluginEjb, traduccioEjb, usuariAplicacioConfiguracioEjb);

    if (__vr.hasErrors()) {
        List<I18NFieldError> vrErrors = __vr.getErrors();
    	   for (I18NFieldError i18nFieldError : vrErrors) {
    	       wvr.rejectValue(i18nFieldError.getField(), i18nFieldError.getTranslation().getCode(), i18nFieldError.getTranslation().getArgs());
        }
    }


  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public UsuariAplicacioConfiguracioValidator<UsuariAplicacioConfiguracio> getValidator() {
    return validator;
  }

  public void setValidator(UsuariAplicacioConfiguracioValidator<UsuariAplicacioConfiguracio> validator) {
    this.validator = validator;
  }

}