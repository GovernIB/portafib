package es.caib.portafib.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import es.caib.portafib.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.caib.portafib.jpa.validator.TipusDocumentValidator;

import es.caib.portafib.back.form.webdb.TipusDocumentForm;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class TipusDocumentWebValidator  implements Validator, TipusDocumentFields {

  protected final Logger log = Logger.getLogger(getClass());

  protected TipusDocumentValidator<Object> validator = new TipusDocumentValidator<Object>();

  // EJB's
  @javax.ejb.EJB(mappedName = "portafib/TipusDocumentEJB/local")
  protected es.caib.portafib.ejb.TipusDocumentLocal tipusDocumentEjb;

  @javax.ejb.EJB(mappedName = "portafib/TraduccioEJB/local")
  protected es.caib.portafib.ejb.TraduccioLocal traduccioEjb;

  @javax.ejb.EJB(mappedName = "portafib/UsuariAplicacioEJB/local")
  protected es.caib.portafib.ejb.UsuariAplicacioLocal usuariAplicacioEjb;



  public TipusDocumentWebValidator() {
    super();    
  }
  
  @Override
  public boolean supports(Class<?> clazz) {
    return TipusDocumentForm.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {

java.util.List<Field<?>> _ignoreFields = new java.util.ArrayList<Field<?>>();
_ignoreFields.add(NOMID);
    WebValidationResult<Object> wvr;
    wvr = new WebValidationResult<Object>(errors, _ignoreFields);

    Boolean nou = (Boolean)errors.getFieldValue("nou");
    boolean isNou =  nou != null && nou.booleanValue();

    validate(target, errors, wvr, isNou);
  }


  public void validate(Object target, Errors errors,
    WebValidationResult<Object> wvr, boolean isNou) {

  {
    es.caib.portafib.jpa.TipusDocumentJPA tipusDocument;
    if (target instanceof TipusDocumentForm) {
      tipusDocument = ((TipusDocumentForm)target).getTipusDocument();
    } else {
      tipusDocument = (es.caib.portafib.jpa.TipusDocumentJPA)target;
    }
    {
    // IF CAMP ES NOT NULL verificar que totes les traduccions son not null
    es.caib.portafib.jpa.TraduccioJPA tradJPA = tipusDocument.getNom();
    if (tradJPA == null) {
      // TODO ERROR
      errors.rejectValue("tipusDocument.nom", "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(NOMID.fullName)}, null);
    } else {
      java.util.Map<String,es.caib.portafib.jpa.TraduccioMapJPA> _trad = tradJPA.getTraduccions();
      for (String _idioma : _trad.keySet()) {
        es.caib.portafib.jpa.TraduccioMapJPA _map = _trad.get(_idioma);
        if (_map == null || _map.getValor() == null) {
          errors.rejectValue("tipusDocument.nom", "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(NOMID.fullName)}, null);
          errors.rejectValue("tipusDocument.nom.traduccions["+ _idioma +"].valor",
              "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(NOMID.fullName)}, null);
        }
      }
    }
    }

  }
    validator.validate(wvr, target,
      isNou, tipusDocumentEjb, traduccioEjb, usuariAplicacioEjb);

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public TipusDocumentValidator<Object> getValidator() {
    return validator;
  }

  public void setValidator(TipusDocumentValidator<Object> validator) {
    this.validator = validator;
  }

}