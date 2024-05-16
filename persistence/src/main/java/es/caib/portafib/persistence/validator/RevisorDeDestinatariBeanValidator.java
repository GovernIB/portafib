package es.caib.portafib.persistence.validator;

import es.caib.portafib.persistence.RevisorDeDestinatariJPA;
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
public class RevisorDeDestinatariBeanValidator 
      extends AbstractBeanValidator<RevisorDeDestinatariJPA> {


  // EJB's
  protected final es.caib.portafib.model.dao.IRevisorDeDestinatariManager __revisorDeDestinatariManager;

  protected final es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager;


  public final RevisorDeDestinatariValidator<RevisorDeDestinatariJPA> _validator;


  public RevisorDeDestinatariBeanValidator(es.caib.portafib.model.dao.IRevisorDeDestinatariManager __revisorDeDestinatariManager,
     es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager) { 
    this.__revisorDeDestinatariManager = __revisorDeDestinatariManager;
    this.__usuariEntitatManager = __usuariEntitatManager;
    _validator = new RevisorDeDestinatariValidator<RevisorDeDestinatariJPA>();
  }

  public RevisorDeDestinatariBeanValidator(RevisorDeDestinatariValidator<RevisorDeDestinatariJPA> _validator,
     es.caib.portafib.model.dao.IRevisorDeDestinatariManager __revisorDeDestinatariManager,
     es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager) {
    this.__revisorDeDestinatariManager = __revisorDeDestinatariManager;
    this.__usuariEntitatManager = __usuariEntitatManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(RevisorDeDestinatariJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<RevisorDeDestinatariJPA> _bvr_ = new BeanValidatorResult<RevisorDeDestinatariJPA>();
    _validator.validate(_bvr_, target, isNou, __revisorDeDestinatariManager, __usuariEntitatManager);
    return _bvr_.getErrors();
  }
}
