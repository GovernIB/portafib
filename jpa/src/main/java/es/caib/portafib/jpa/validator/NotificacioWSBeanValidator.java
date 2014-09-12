package es.caib.portafib.jpa.validator;

import es.caib.portafib.jpa.NotificacioWSJPA;
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
public class NotificacioWSBeanValidator 
      extends AbstractBeanValidator<NotificacioWSJPA> {


  // EJB's
  protected final es.caib.portafib.model.dao.INotificacioWSManager __notificacioWSManager;

  protected final es.caib.portafib.model.dao.IPeticioDeFirmaManager __peticioDeFirmaManager;

  protected final es.caib.portafib.model.dao.ITipusNotificacioManager __tipusNotificacioManager;


  public final NotificacioWSValidator<NotificacioWSJPA> _validator;


  public NotificacioWSBeanValidator(es.caib.portafib.model.dao.INotificacioWSManager __notificacioWSManager,
     es.caib.portafib.model.dao.IPeticioDeFirmaManager __peticioDeFirmaManager,
     es.caib.portafib.model.dao.ITipusNotificacioManager __tipusNotificacioManager) { 
    this.__notificacioWSManager = __notificacioWSManager;
    this.__peticioDeFirmaManager = __peticioDeFirmaManager;
    this.__tipusNotificacioManager = __tipusNotificacioManager;
    _validator = new NotificacioWSValidator<NotificacioWSJPA>();
  }

  public NotificacioWSBeanValidator(NotificacioWSValidator<NotificacioWSJPA> _validator,
     es.caib.portafib.model.dao.INotificacioWSManager __notificacioWSManager,
     es.caib.portafib.model.dao.IPeticioDeFirmaManager __peticioDeFirmaManager,
     es.caib.portafib.model.dao.ITipusNotificacioManager __tipusNotificacioManager) {
    this.__notificacioWSManager = __notificacioWSManager;
    this.__peticioDeFirmaManager = __peticioDeFirmaManager;
    this.__tipusNotificacioManager = __tipusNotificacioManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(NotificacioWSJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<NotificacioWSJPA> _bvr_ = new BeanValidatorResult<NotificacioWSJPA>();
    _validator.validate(_bvr_, target, isNou, __notificacioWSManager, __peticioDeFirmaManager, __tipusNotificacioManager);
    return _bvr_.getErrors();
  }
}
