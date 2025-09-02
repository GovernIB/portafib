package es.caib.portafib.persistence.validator;

import es.caib.portafib.persistence.CorreuAgrupatJPA;
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
public class CorreuAgrupatBeanValidator 
      extends AbstractBeanValidator<CorreuAgrupatJPA> {


  // EJB's
  protected final es.caib.portafib.model.dao.ICorreuAgrupatManager __correuAgrupatManager;

  protected final es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager;


  public final CorreuAgrupatValidator<CorreuAgrupatJPA> _validator;


  public CorreuAgrupatBeanValidator(es.caib.portafib.model.dao.ICorreuAgrupatManager __correuAgrupatManager,
     es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager) { 
    this.__correuAgrupatManager = __correuAgrupatManager;
    this.__usuariEntitatManager = __usuariEntitatManager;
    _validator = new CorreuAgrupatValidator<CorreuAgrupatJPA>();
  }

  public CorreuAgrupatBeanValidator(CorreuAgrupatValidator<CorreuAgrupatJPA> _validator,
     es.caib.portafib.model.dao.ICorreuAgrupatManager __correuAgrupatManager,
     es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager) {
    this.__correuAgrupatManager = __correuAgrupatManager;
    this.__usuariEntitatManager = __usuariEntitatManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(CorreuAgrupatJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<CorreuAgrupatJPA> _bvr_ = new BeanValidatorResult<CorreuAgrupatJPA>();
    _validator.validate(_bvr_, target, isNou, __correuAgrupatManager, __usuariEntitatManager);
    return _bvr_.getErrors();
  }
}
