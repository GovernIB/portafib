package es.caib.portafib.jpa.validator;

import es.caib.portafib.jpa.RoleJPA;
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
public class RoleBeanValidator 
      extends AbstractBeanValidator<RoleJPA> {


  // EJB's
  protected final es.caib.portafib.model.dao.IRoleManager __roleManager;


  public final RoleValidator<RoleJPA> _validator;


  public RoleBeanValidator(es.caib.portafib.model.dao.IRoleManager __roleManager) { 
    this.__roleManager = __roleManager;
    _validator = new RoleValidator<RoleJPA>();
  }

  public RoleBeanValidator(RoleValidator<RoleJPA> _validator,
     es.caib.portafib.model.dao.IRoleManager __roleManager) {
    this.__roleManager = __roleManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(RoleJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<RoleJPA> _bvr_ = new BeanValidatorResult<RoleJPA>();
    _validator.validate(_bvr_, target, isNou, __roleManager);
    return _bvr_.getErrors();
  }
}
