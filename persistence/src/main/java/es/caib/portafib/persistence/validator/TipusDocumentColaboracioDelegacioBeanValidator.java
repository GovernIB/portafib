package es.caib.portafib.persistence.validator;

import es.caib.portafib.persistence.TipusDocumentColaboracioDelegacioJPA;
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
public class TipusDocumentColaboracioDelegacioBeanValidator 
      extends AbstractBeanValidator<TipusDocumentColaboracioDelegacioJPA> {


  // EJB's
  protected final es.caib.portafib.model.dao.IColaboracioDelegacioManager __colaboracioDelegacioManager;

  protected final es.caib.portafib.model.dao.ITipusDocumentManager __tipusDocumentManager;

  protected final es.caib.portafib.model.dao.ITipusDocumentColaboracioDelegacioManager __tipusDocumentColaboracioDelegacioManager;


  public final TipusDocumentColaboracioDelegacioValidator<TipusDocumentColaboracioDelegacioJPA> _validator;


  public TipusDocumentColaboracioDelegacioBeanValidator(es.caib.portafib.model.dao.IColaboracioDelegacioManager __colaboracioDelegacioManager,
     es.caib.portafib.model.dao.ITipusDocumentManager __tipusDocumentManager,
     es.caib.portafib.model.dao.ITipusDocumentColaboracioDelegacioManager __tipusDocumentColaboracioDelegacioManager) { 
    this.__colaboracioDelegacioManager = __colaboracioDelegacioManager;
    this.__tipusDocumentManager = __tipusDocumentManager;
    this.__tipusDocumentColaboracioDelegacioManager = __tipusDocumentColaboracioDelegacioManager;
    _validator = new TipusDocumentColaboracioDelegacioValidator<TipusDocumentColaboracioDelegacioJPA>();
  }

  public TipusDocumentColaboracioDelegacioBeanValidator(TipusDocumentColaboracioDelegacioValidator<TipusDocumentColaboracioDelegacioJPA> _validator,
     es.caib.portafib.model.dao.IColaboracioDelegacioManager __colaboracioDelegacioManager,
     es.caib.portafib.model.dao.ITipusDocumentManager __tipusDocumentManager,
     es.caib.portafib.model.dao.ITipusDocumentColaboracioDelegacioManager __tipusDocumentColaboracioDelegacioManager) {
    this.__colaboracioDelegacioManager = __colaboracioDelegacioManager;
    this.__tipusDocumentManager = __tipusDocumentManager;
    this.__tipusDocumentColaboracioDelegacioManager = __tipusDocumentColaboracioDelegacioManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(TipusDocumentColaboracioDelegacioJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<TipusDocumentColaboracioDelegacioJPA> _bvr_ = new BeanValidatorResult<TipusDocumentColaboracioDelegacioJPA>();
    _validator.validate(_bvr_, target, isNou, __colaboracioDelegacioManager, __tipusDocumentManager, __tipusDocumentColaboracioDelegacioManager);
    return _bvr_.getErrors();
  }
}
