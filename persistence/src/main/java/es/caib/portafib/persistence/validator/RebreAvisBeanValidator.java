package es.caib.portafib.persistence.validator;

import es.caib.portafib.persistence.RebreAvisJPA;
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
public class RebreAvisBeanValidator 
      extends AbstractBeanValidator<RebreAvisJPA> {


  // EJB's
  protected final es.caib.portafib.model.dao.IRebreAvisManager __rebreAvisManager;

  protected final es.caib.portafib.model.dao.ITipusNotificacioManager __tipusNotificacioManager;

  protected final es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager;


  public final RebreAvisValidator<RebreAvisJPA> _validator;


  public RebreAvisBeanValidator(es.caib.portafib.model.dao.IRebreAvisManager __rebreAvisManager,
     es.caib.portafib.model.dao.ITipusNotificacioManager __tipusNotificacioManager,
     es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager) { 
    this.__rebreAvisManager = __rebreAvisManager;
    this.__tipusNotificacioManager = __tipusNotificacioManager;
    this.__usuariEntitatManager = __usuariEntitatManager;
    _validator = new RebreAvisValidator<RebreAvisJPA>();
  }

  public RebreAvisBeanValidator(RebreAvisValidator<RebreAvisJPA> _validator,
     es.caib.portafib.model.dao.IRebreAvisManager __rebreAvisManager,
     es.caib.portafib.model.dao.ITipusNotificacioManager __tipusNotificacioManager,
     es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager) {
    this.__rebreAvisManager = __rebreAvisManager;
    this.__tipusNotificacioManager = __tipusNotificacioManager;
    this.__usuariEntitatManager = __usuariEntitatManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(RebreAvisJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<RebreAvisJPA> _bvr_ = new BeanValidatorResult<RebreAvisJPA>();
    _validator.validate(_bvr_, target, isNou, __rebreAvisManager, __tipusNotificacioManager, __usuariEntitatManager);
    return _bvr_.getErrors();
  }
}
