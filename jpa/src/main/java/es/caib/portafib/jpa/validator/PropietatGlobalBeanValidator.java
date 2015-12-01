package es.caib.portafib.jpa.validator;

import es.caib.portafib.jpa.PropietatGlobalJPA;
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
public class PropietatGlobalBeanValidator 
      extends AbstractBeanValidator<PropietatGlobalJPA> {


  // EJB's
  protected final es.caib.portafib.model.dao.IEntitatManager __entitatManager;

  protected final es.caib.portafib.model.dao.IPropietatGlobalManager __propietatGlobalManager;


  public final PropietatGlobalValidator<PropietatGlobalJPA> _validator;


  public PropietatGlobalBeanValidator(es.caib.portafib.model.dao.IEntitatManager __entitatManager,
     es.caib.portafib.model.dao.IPropietatGlobalManager __propietatGlobalManager) { 
    this.__entitatManager = __entitatManager;
    this.__propietatGlobalManager = __propietatGlobalManager;
    _validator = new PropietatGlobalValidator<PropietatGlobalJPA>();
  }

  public PropietatGlobalBeanValidator(PropietatGlobalValidator<PropietatGlobalJPA> _validator,
     es.caib.portafib.model.dao.IEntitatManager __entitatManager,
     es.caib.portafib.model.dao.IPropietatGlobalManager __propietatGlobalManager) {
    this.__entitatManager = __entitatManager;
    this.__propietatGlobalManager = __propietatGlobalManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(PropietatGlobalJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<PropietatGlobalJPA> _bvr_ = new BeanValidatorResult<PropietatGlobalJPA>();
    _validator.validate(_bvr_, target, isNou, __entitatManager, __propietatGlobalManager);
    return _bvr_.getErrors();
  }
}
