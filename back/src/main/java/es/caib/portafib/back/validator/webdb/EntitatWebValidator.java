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
import es.caib.portafib.persistence.validator.EntitatValidator;

import es.caib.portafib.back.form.webdb.EntitatForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.portafib.model.entity.Entitat;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class EntitatWebValidator extends AbstractWebValidator<EntitatForm, Entitat>
     implements Validator, EntitatFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected EntitatValidator<Entitat> validator = new EntitatValidator<Entitat>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.CustodiaInfoService.JNDI_NAME)
  protected es.caib.portafib.ejb.CustodiaInfoService custodiaInfoEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.EntitatService.JNDI_NAME)
  protected es.caib.portafib.ejb.EntitatService entitatEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.PluginService.JNDI_NAME)
  protected es.caib.portafib.ejb.PluginService pluginEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.TraduccioService.JNDI_NAME)
  protected es.caib.portafib.ejb.TraduccioService traduccioEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.UsuariAplicacioService.JNDI_NAME)
  protected es.caib.portafib.ejb.UsuariAplicacioService usuariAplicacioEjb;



  public EntitatWebValidator() {
    super();    
  }
  
  @Override
  public Entitat getBeanOfForm(EntitatForm form) {
    return  form.getEntitat();
  }

  @Override
  public Class<EntitatForm> getClassOfForm() {
    return EntitatForm.class;
  }

  @Override
  public void validate(EntitatForm __form, Entitat __bean, Errors errors) {

java.util.List<Field<?>> _ignoreFields = new java.util.ArrayList<Field<?>>();
_ignoreFields.add(MOTIUDELEGACIOID);
_ignoreFields.add(FIRMATPERFORMATID);
    WebValidationResult<EntitatForm> wvr;
    wvr = new WebValidationResult<EntitatForm>(errors, _ignoreFields);

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


  public void validate(EntitatForm __form, Entitat __bean, Errors errors,
    WebValidationResult<EntitatForm> wvr, boolean isNou) {

  {
      es.caib.portafib.persistence.EntitatJPA __jpa;
      __jpa = (es.caib.portafib.persistence.EntitatJPA)__bean;
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
                errors.rejectValue("entitat.motiuDelegacio", "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(MOTIUDELEGACIOID.fullName)}, null);
                errors.rejectValue("entitat.motiuDelegacio.traduccions["+ _idioma +"].valor",
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
                errors.rejectValue("entitat.firmatPerFormat", "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(FIRMATPERFORMATID.fullName)}, null);
                errors.rejectValue("entitat.firmatPerFormat.traduccions["+ _idioma +"].valor",
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
    BeanValidatorResult<Entitat> __vr = new BeanValidatorResult<Entitat>();
    validator.validate(__vr, __bean,
      isNou, custodiaInfoEjb, entitatEjb, pluginEjb, traduccioEjb, usuariAplicacioEjb);

    if (__vr.hasErrors()) {
        List<I18NFieldError> vrErrors = __vr.getErrors();
    	   for (I18NFieldError i18nFieldError : vrErrors) {
    	       wvr.rejectValue(i18nFieldError.getField(), i18nFieldError.getTranslation().getCode(), i18nFieldError.getTranslation().getArgs());
        }
    }

    if (isNou) { // Creacio
      // ================ CREATION
      // Fitxers 
        if (!errors.hasFieldErrors(get(FAVICONID))){
            CommonsMultipartFile faviconID = ((EntitatForm)__form).getFaviconID();
            if (faviconID == null || faviconID.isEmpty()) {
                errors.rejectValue(get(FAVICONID), "genapp.validation.required",
                new String[]{ org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(get(FAVICONID)) },
                null);
            }
        }

        if (!errors.hasFieldErrors(get(LOGOWEBID))){
            CommonsMultipartFile logoWebID = ((EntitatForm)__form).getLogoWebID();
            if (logoWebID == null || logoWebID.isEmpty()) {
                errors.rejectValue(get(LOGOWEBID), "genapp.validation.required",
                new String[]{ org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(get(LOGOWEBID)) },
                null);
            }
        }

        if (!errors.hasFieldErrors(get(LOGOWEBPEUID))){
            CommonsMultipartFile logoWebPeuID = ((EntitatForm)__form).getLogoWebPeuID();
            if (logoWebPeuID == null || logoWebPeuID.isEmpty()) {
                errors.rejectValue(get(LOGOWEBPEUID), "genapp.validation.required",
                new String[]{ org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(get(LOGOWEBPEUID)) },
                null);
            }
        }

        if (!errors.hasFieldErrors(get(LOGOSEGELLID))){
            CommonsMultipartFile logoSegellID = ((EntitatForm)__form).getLogoSegellID();
            if (logoSegellID == null || logoSegellID.isEmpty()) {
                errors.rejectValue(get(LOGOSEGELLID), "genapp.validation.required",
                new String[]{ org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(get(LOGOSEGELLID)) },
                null);
            }
        }

        if (!errors.hasFieldErrors(get(PDFAUTORITZACIODELEGACIOID))){
            CommonsMultipartFile pdfAutoritzacioDelegacioID = ((EntitatForm)__form).getPdfAutoritzacioDelegacioID();
            if (pdfAutoritzacioDelegacioID == null || pdfAutoritzacioDelegacioID.isEmpty()) {
                errors.rejectValue(get(PDFAUTORITZACIODELEGACIOID), "genapp.validation.required",
                new String[]{ org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(get(PDFAUTORITZACIODELEGACIOID)) },
                null);
            }
        }

    }

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public EntitatValidator<Entitat> getValidator() {
    return validator;
  }

  public void setValidator(EntitatValidator<Entitat> validator) {
    this.validator = validator;
  }

}