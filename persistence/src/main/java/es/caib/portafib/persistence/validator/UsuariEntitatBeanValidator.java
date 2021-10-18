package es.caib.portafib.persistence.validator;

import es.caib.portafib.persistence.UsuariEntitatJPA;
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
public class UsuariEntitatBeanValidator 
      extends AbstractBeanValidator<UsuariEntitatJPA> {


  // EJB's
  protected final es.caib.portafib.model.dao.ICustodiaInfoManager __custodiaInfoManager;

  protected final es.caib.portafib.model.dao.IEntitatManager __entitatManager;

  protected final es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager;

  protected final es.caib.portafib.model.dao.IUsuariPersonaManager __usuariPersonaManager;


  public final UsuariEntitatValidator<UsuariEntitatJPA> _validator;


  public UsuariEntitatBeanValidator(es.caib.portafib.model.dao.ICustodiaInfoManager __custodiaInfoManager,
     es.caib.portafib.model.dao.IEntitatManager __entitatManager,
     es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager,
     es.caib.portafib.model.dao.IUsuariPersonaManager __usuariPersonaManager) { 
    this.__custodiaInfoManager = __custodiaInfoManager;
    this.__entitatManager = __entitatManager;
    this.__usuariEntitatManager = __usuariEntitatManager;
    this.__usuariPersonaManager = __usuariPersonaManager;
    _validator = new UsuariEntitatValidator<UsuariEntitatJPA>();
  }

  public UsuariEntitatBeanValidator(UsuariEntitatValidator<UsuariEntitatJPA> _validator,
     es.caib.portafib.model.dao.ICustodiaInfoManager __custodiaInfoManager,
     es.caib.portafib.model.dao.IEntitatManager __entitatManager,
     es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager,
     es.caib.portafib.model.dao.IUsuariPersonaManager __usuariPersonaManager) {
    this.__custodiaInfoManager = __custodiaInfoManager;
    this.__entitatManager = __entitatManager;
    this.__usuariEntitatManager = __usuariEntitatManager;
    this.__usuariPersonaManager = __usuariPersonaManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(UsuariEntitatJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<UsuariEntitatJPA> _bvr_ = new BeanValidatorResult<UsuariEntitatJPA>();
    _validator.validate(_bvr_, target, isNou, __custodiaInfoManager, __entitatManager, __usuariEntitatManager, __usuariPersonaManager);
    return _bvr_.getErrors();
  }
}
