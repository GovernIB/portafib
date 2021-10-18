package es.caib.portafib.persistence.validator;

import es.caib.portafib.persistence.TipusNotificacioJPA;
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
public class TipusNotificacioBeanValidator 
      extends AbstractBeanValidator<TipusNotificacioJPA> {


  // EJB's
  protected final es.caib.portafib.model.dao.ITipusNotificacioManager __tipusNotificacioManager;


  public final TipusNotificacioValidator<TipusNotificacioJPA> _validator;


  public TipusNotificacioBeanValidator(es.caib.portafib.model.dao.ITipusNotificacioManager __tipusNotificacioManager) { 
    this.__tipusNotificacioManager = __tipusNotificacioManager;
    _validator = new TipusNotificacioValidator<TipusNotificacioJPA>();
  }

  public TipusNotificacioBeanValidator(TipusNotificacioValidator<TipusNotificacioJPA> _validator,
     es.caib.portafib.model.dao.ITipusNotificacioManager __tipusNotificacioManager) {
    this.__tipusNotificacioManager = __tipusNotificacioManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(TipusNotificacioJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<TipusNotificacioJPA> _bvr_ = new BeanValidatorResult<TipusNotificacioJPA>();
    _validator.validate(_bvr_, target, isNou, __tipusNotificacioManager);
    return _bvr_.getErrors();
  }
}
