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
import es.caib.portafib.persistence.validator.TipusDocumentValidator;

import es.caib.portafib.back.form.webdb.TipusDocumentForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.portafib.model.entity.TipusDocument;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class TipusDocumentWebValidator extends AbstractWebValidator<TipusDocumentForm, TipusDocument>
     implements Validator, TipusDocumentFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected TipusDocumentValidator<TipusDocument> validator = new TipusDocumentValidator<TipusDocument>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.TipusDocumentService.JNDI_NAME)
  protected es.caib.portafib.ejb.TipusDocumentService tipusDocumentEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.TraduccioService.JNDI_NAME)
  protected es.caib.portafib.ejb.TraduccioService traduccioEjb;

  @javax.ejb.EJB(mappedName = es.caib.portafib.ejb.UsuariAplicacioService.JNDI_NAME)
  protected es.caib.portafib.ejb.UsuariAplicacioService usuariAplicacioEjb;



  public TipusDocumentWebValidator() {
    super();    
  }
  
  @Override
  public TipusDocument getBeanOfForm(TipusDocumentForm form) {
    return  form.getTipusDocument();
  }

  @Override
  public Class<TipusDocumentForm> getClassOfForm() {
    return TipusDocumentForm.class;
  }

  @Override
  public void validate(TipusDocumentForm __form, TipusDocument __bean, Errors errors) {

java.util.List<Field<?>> _ignoreFields = new java.util.ArrayList<Field<?>>();
_ignoreFields.add(NOMID);
    WebValidationResult<TipusDocumentForm> wvr;
    wvr = new WebValidationResult<TipusDocumentForm>(errors, _ignoreFields);

    boolean isNou;
    {
        Object objNou = errors.getFieldValue("nou");
        if (objNou == null) {
            isNou = false;
        } else { 
         Boolean nou = Boolean.parseBoolean((String)objNou);
         isNou =  nou != null && nou.booleanValue();
        }
    }

    validate(__form, __bean , errors, wvr, isNou);
  }


  public void validate(TipusDocumentForm __form, TipusDocument __bean, Errors errors,
    WebValidationResult<TipusDocumentForm> wvr, boolean isNou) {

  {
      es.caib.portafib.persistence.TipusDocumentJPA __jpa;
      __jpa = (es.caib.portafib.persistence.TipusDocumentJPA)__bean;
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
                errors.rejectValue("tipusDocument.nom", "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(NOMID.fullName)}, null);
                errors.rejectValue("tipusDocument.nom.traduccions["+ _idioma +"].valor",
                  "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(NOMID.fullName)}, null);
              }
            }
          }
      } else {
        errors.rejectValue("tipusDocument.nom", "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(NOMID.fullName)}, null);
      }
    }

  }
    BeanValidatorResult<TipusDocument> __vr = new BeanValidatorResult<TipusDocument>();
    validator.validate(__vr, __bean,
      isNou, tipusDocumentEjb, traduccioEjb, usuariAplicacioEjb);

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

  public TipusDocumentValidator<TipusDocument> getValidator() {
    return validator;
  }

  public void setValidator(TipusDocumentValidator<TipusDocument> validator) {
    this.validator = validator;
  }

}