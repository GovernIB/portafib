package es.caib.portafib.jpa.validator;

import es.caib.portafib.jpa.RoleUsuariAplicacioJPA;
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
public class RoleUsuariAplicacioBeanValidator 
      extends AbstractBeanValidator<RoleUsuariAplicacioJPA> {


  // EJB's
  protected final es.caib.portafib.model.dao.IRoleManager __roleManager;

  protected final es.caib.portafib.model.dao.IRoleUsuariAplicacioManager __roleUsuariAplicacioManager;

  protected final es.caib.portafib.model.dao.IUsuariAplicacioManager __usuariAplicacioManager;


  public final RoleUsuariAplicacioValidator<RoleUsuariAplicacioJPA> _validator;


  public RoleUsuariAplicacioBeanValidator(es.caib.portafib.model.dao.IRoleManager __roleManager,
     es.caib.portafib.model.dao.IRoleUsuariAplicacioManager __roleUsuariAplicacioManager,
     es.caib.portafib.model.dao.IUsuariAplicacioManager __usuariAplicacioManager) { 
    this.__roleManager = __roleManager;
    this.__roleUsuariAplicacioManager = __roleUsuariAplicacioManager;
    this.__usuariAplicacioManager = __usuariAplicacioManager;
    _validator = new RoleUsuariAplicacioValidator<RoleUsuariAplicacioJPA>();
  }

  public RoleUsuariAplicacioBeanValidator(RoleUsuariAplicacioValidator<RoleUsuariAplicacioJPA> _validator,
     es.caib.portafib.model.dao.IRoleManager __roleManager,
     es.caib.portafib.model.dao.IRoleUsuariAplicacioManager __roleUsuariAplicacioManager,
     es.caib.portafib.model.dao.IUsuariAplicacioManager __usuariAplicacioManager) {
    this.__roleManager = __roleManager;
    this.__roleUsuariAplicacioManager = __roleUsuariAplicacioManager;
    this.__usuariAplicacioManager = __usuariAplicacioManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(RoleUsuariAplicacioJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<RoleUsuariAplicacioJPA> _bvr_ = new BeanValidatorResult<RoleUsuariAplicacioJPA>();
    _validator.validate(_bvr_, target, isNou, __roleManager, __roleUsuariAplicacioManager, __usuariAplicacioManager);
    return _bvr_.getErrors();
  }
}
