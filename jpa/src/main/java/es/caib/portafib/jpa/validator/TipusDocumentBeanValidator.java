package es.caib.portafib.jpa.validator;

import es.caib.portafib.jpa.TipusDocumentJPA;
import org.fundaciobit.genapp.common.validation.BeanValidatorResult;
import java.util.List;
import org.fundaciobit.genapp.common.i18n.I18NFieldError;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.validation.AbstractBeanValidator;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class TipusDocumentBeanValidator 
      extends AbstractBeanValidator<TipusDocumentJPA> {


  // EJB's
  protected final es.caib.portafib.model.dao.ITipusDocumentManager __tipusDocumentManager;

  protected final es.caib.portafib.model.dao.ITraduccioManager __traduccioManager;

  protected final es.caib.portafib.model.dao.IUsuariAplicacioManager __usuariAplicacioManager;


  public final TipusDocumentValidator<TipusDocumentJPA> _validator;


  public TipusDocumentBeanValidator(es.caib.portafib.model.dao.ITipusDocumentManager __tipusDocumentManager,
     es.caib.portafib.model.dao.ITraduccioManager __traduccioManager,
     es.caib.portafib.model.dao.IUsuariAplicacioManager __usuariAplicacioManager) { 
    this.__tipusDocumentManager = __tipusDocumentManager;
    this.__traduccioManager = __traduccioManager;
    this.__usuariAplicacioManager = __usuariAplicacioManager;
    _validator = new TipusDocumentValidator<TipusDocumentJPA>();
  }

  public TipusDocumentBeanValidator(TipusDocumentValidator<TipusDocumentJPA> _validator,
     es.caib.portafib.model.dao.ITipusDocumentManager __tipusDocumentManager,
     es.caib.portafib.model.dao.ITraduccioManager __traduccioManager,
     es.caib.portafib.model.dao.IUsuariAplicacioManager __usuariAplicacioManager) {
    this.__tipusDocumentManager = __tipusDocumentManager;
    this.__traduccioManager = __traduccioManager;
    this.__usuariAplicacioManager = __usuariAplicacioManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(TipusDocumentJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<TipusDocumentJPA> _bvr_ = new BeanValidatorResult<TipusDocumentJPA>();
    _validator.validate(_bvr_, target, isNou, __tipusDocumentManager, __traduccioManager, __usuariAplicacioManager);
    return _bvr_.getErrors();
  }
}
