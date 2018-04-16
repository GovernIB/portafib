package es.caib.portafib.jpa.validator;

import es.caib.portafib.jpa.UsuariEntitatRevisorJPA;
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
public class UsuariEntitatRevisorBeanValidator 
      extends AbstractBeanValidator<UsuariEntitatRevisorJPA> {


  // EJB's
  protected final es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager;

  protected final es.caib.portafib.model.dao.IUsuariEntitatRevisorManager __usuariEntitatRevisorManager;


  public final UsuariEntitatRevisorValidator<UsuariEntitatRevisorJPA> _validator;


  public UsuariEntitatRevisorBeanValidator(es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager,
     es.caib.portafib.model.dao.IUsuariEntitatRevisorManager __usuariEntitatRevisorManager) { 
    this.__usuariEntitatManager = __usuariEntitatManager;
    this.__usuariEntitatRevisorManager = __usuariEntitatRevisorManager;
    _validator = new UsuariEntitatRevisorValidator<UsuariEntitatRevisorJPA>();
  }

  public UsuariEntitatRevisorBeanValidator(UsuariEntitatRevisorValidator<UsuariEntitatRevisorJPA> _validator,
     es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager,
     es.caib.portafib.model.dao.IUsuariEntitatRevisorManager __usuariEntitatRevisorManager) {
    this.__usuariEntitatManager = __usuariEntitatManager;
    this.__usuariEntitatRevisorManager = __usuariEntitatRevisorManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(UsuariEntitatRevisorJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<UsuariEntitatRevisorJPA> _bvr_ = new BeanValidatorResult<UsuariEntitatRevisorJPA>();
    _validator.validate(_bvr_, target, isNou, __usuariEntitatManager, __usuariEntitatRevisorManager);
    return _bvr_.getErrors();
  }
}
