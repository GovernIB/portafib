package es.caib.portafib.persistence.validator;

import es.caib.portafib.persistence.RoleUsuariEntitatJPA;
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
public class RoleUsuariEntitatBeanValidator 
      extends AbstractBeanValidator<RoleUsuariEntitatJPA> {


  // EJB's
  protected final es.caib.portafib.model.dao.IRoleManager __roleManager;

  protected final es.caib.portafib.model.dao.IRoleUsuariEntitatManager __roleUsuariEntitatManager;

  protected final es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager;


  public final RoleUsuariEntitatValidator<RoleUsuariEntitatJPA> _validator;


  public RoleUsuariEntitatBeanValidator(es.caib.portafib.model.dao.IRoleManager __roleManager,
     es.caib.portafib.model.dao.IRoleUsuariEntitatManager __roleUsuariEntitatManager,
     es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager) { 
    this.__roleManager = __roleManager;
    this.__roleUsuariEntitatManager = __roleUsuariEntitatManager;
    this.__usuariEntitatManager = __usuariEntitatManager;
    _validator = new RoleUsuariEntitatValidator<RoleUsuariEntitatJPA>();
  }

  public RoleUsuariEntitatBeanValidator(RoleUsuariEntitatValidator<RoleUsuariEntitatJPA> _validator,
     es.caib.portafib.model.dao.IRoleManager __roleManager,
     es.caib.portafib.model.dao.IRoleUsuariEntitatManager __roleUsuariEntitatManager,
     es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager) {
    this.__roleManager = __roleManager;
    this.__roleUsuariEntitatManager = __roleUsuariEntitatManager;
    this.__usuariEntitatManager = __usuariEntitatManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(RoleUsuariEntitatJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<RoleUsuariEntitatJPA> _bvr_ = new BeanValidatorResult<RoleUsuariEntitatJPA>();
    _validator.validate(_bvr_, target, isNou, __roleManager, __roleUsuariEntitatManager, __usuariEntitatManager);
    return _bvr_.getErrors();
  }
}
