package es.caib.portafib.back.validator.webdb;

import org.apache.log4j.Logger;

import javax.ejb.EJB;
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
  @EJB(mappedName = es.caib.portafib.ejb.TipusDocumentLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.TipusDocumentLocal tipusDocumentEjb;

  @EJB(mappedName = es.caib.portafib.ejb.TraduccioLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.TraduccioLocal traduccioEjb;

  @EJB(mappedName = es.caib.portafib.ejb.UsuariAplicacioLocal.JNDI_NAME)
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
      if (tradJPA != null) {
        // TODO ERROR
        java.util.Map<String,es.caib.portafib.jpa.TraduccioMapJPA> _trad = tradJPA.getTraduccions();
        int countNotNull = 0;
        for (String _idioma : _trad.keySet()) {
          es.caib.portafib.jpa.TraduccioMapJPA _map = _trad.get(_idioma);
          if (_map == null || (_map.getValor() == null || _map.getValor().length() == 0 )) {
          } else {
            countNotNull++;
          }
        }

          if (countNotNull  == _trad.size()) {
            // OK Tot esta ple
          } else {
            for (String _idioma : _trad.keySet()) {
              es.caib.portafib.jpa.TraduccioMapJPA _map = _trad.get(_idioma);
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