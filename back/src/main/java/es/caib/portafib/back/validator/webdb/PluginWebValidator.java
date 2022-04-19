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
import es.caib.portafib.persistence.validator.PluginValidator;

import es.caib.portafib.back.form.webdb.PluginForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.portafib.model.entity.Plugin;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class PluginWebValidator extends AbstractWebValidator<PluginForm, Plugin>
     implements Validator, PluginFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected PluginValidator<Plugin> validator = new PluginValidator<Plugin>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.EntitatService.JNDI_NAME)
  protected es.caib.portafib.ejb.EntitatService entitatEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.PluginService.JNDI_NAME)
  protected es.caib.portafib.ejb.PluginService pluginEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.TraduccioService.JNDI_NAME)
  protected es.caib.portafib.ejb.TraduccioService traduccioEjb;



  public PluginWebValidator() {
    super();    
  }
  
  @Override
  public Plugin getBeanOfForm(PluginForm form) {
    return  form.getPlugin();
  }

  @Override
  public Class<PluginForm> getClassOfForm() {
    return PluginForm.class;
  }

  @Override
  public void validate(PluginForm __form, Plugin __bean, Errors errors) {

java.util.List<Field<?>> _ignoreFields = new java.util.ArrayList<Field<?>>();
_ignoreFields.add(NOMID);
_ignoreFields.add(DESCRIPCIOCURTAID);
    WebValidationResult<PluginForm> wvr;
    wvr = new WebValidationResult<PluginForm>(errors, _ignoreFields);

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


  public void validate(PluginForm __form, Plugin __bean, Errors errors,
    WebValidationResult<PluginForm> wvr, boolean isNou) {

  {
      es.caib.portafib.persistence.PluginJPA __jpa;
      __jpa = (es.caib.portafib.persistence.PluginJPA)__bean;
    {
      // IF CAMP ES NOT NULL verificar que totes les traduccions son not null
      es.caib.portafib.persistence.TraduccioJPA tradJPA = __jpa.getNom();
      if (tradJPA != null) {
        // TODO ERROR
        java.util.Map<String,es.caib.portafib.persistence.TraduccioMapJPA> _trad = tradJPA.getTraduccions();
        int countNotNull = 0;
        for (String _idioma : _trad.keySet()) {
          es.caib.portafib.persistence.TraduccioMapJPA _map = _trad.get(_idioma);
          if (_map == null || (_map.getValor() == null || _map.getValor().length() == 0 )) {
          } else {
            countNotNull++;
          }
        }

          if (countNotNull  == _trad.size()) {
            // OK Tot esta ple
          } else {
            for (String _idioma : _trad.keySet()) {
              es.caib.portafib.persistence.TraduccioMapJPA _map = _trad.get(_idioma);
              if (_map == null || (_map.getValor() == null || _map.getValor().length() == 0 )) {
                errors.rejectValue("plugin.nom", "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(NOMID.fullName)}, null);
                errors.rejectValue("plugin.nom.traduccions["+ _idioma +"].valor",
                  "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(NOMID.fullName)}, null);
              }
            }
          }
      } else {
        errors.rejectValue("plugin.nom", "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(NOMID.fullName)}, null);
      }
    }
    {
      // IF CAMP ES NOT NULL verificar que totes les traduccions son not null
      es.caib.portafib.persistence.TraduccioJPA tradJPA = __jpa.getDescripcioCurta();
      if (tradJPA != null) {
        // TODO ERROR
        java.util.Map<String,es.caib.portafib.persistence.TraduccioMapJPA> _trad = tradJPA.getTraduccions();
        int countNotNull = 0;
        for (String _idioma : _trad.keySet()) {
          es.caib.portafib.persistence.TraduccioMapJPA _map = _trad.get(_idioma);
          if (_map == null || (_map.getValor() == null || _map.getValor().length() == 0 )) {
          } else {
            countNotNull++;
          }
        }

          if (countNotNull  == _trad.size()) {
            // OK Tot esta ple
          } else {
            for (String _idioma : _trad.keySet()) {
              es.caib.portafib.persistence.TraduccioMapJPA _map = _trad.get(_idioma);
              if (_map == null || (_map.getValor() == null || _map.getValor().length() == 0 )) {
                errors.rejectValue("plugin.descripcioCurta", "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(DESCRIPCIOCURTAID.fullName)}, null);
                errors.rejectValue("plugin.descripcioCurta.traduccions["+ _idioma +"].valor",
                  "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(DESCRIPCIOCURTAID.fullName)}, null);
              }
            }
          }
      } else {
        errors.rejectValue("plugin.descripcioCurta", "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(DESCRIPCIOCURTAID.fullName)}, null);
      }
    }

  }
    BeanValidatorResult<Plugin> __vr = new BeanValidatorResult<Plugin>();
    validator.validate(__vr, __bean,
      isNou, entitatEjb, pluginEjb, traduccioEjb);

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

  public PluginValidator<Plugin> getValidator() {
    return validator;
  }

  public void setValidator(PluginValidator<Plugin> validator) {
    this.validator = validator;
  }

}