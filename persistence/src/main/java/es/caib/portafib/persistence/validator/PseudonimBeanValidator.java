package es.caib.portafib.persistence.validator;

import es.caib.portafib.persistence.PseudonimJPA;
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
public class PseudonimBeanValidator 
      extends AbstractBeanValidator<PseudonimJPA> {


  // EJB's
  protected final es.caib.portafib.model.dao.IPseudonimManager __pseudonimManager;


  public final PseudonimValidator<PseudonimJPA> _validator;


  public PseudonimBeanValidator(es.caib.portafib.model.dao.IPseudonimManager __pseudonimManager) { 
    this.__pseudonimManager = __pseudonimManager;
    _validator = new PseudonimValidator<PseudonimJPA>();
  }

  public PseudonimBeanValidator(PseudonimValidator<PseudonimJPA> _validator,
     es.caib.portafib.model.dao.IPseudonimManager __pseudonimManager) {
    this.__pseudonimManager = __pseudonimManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(PseudonimJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<PseudonimJPA> _bvr_ = new BeanValidatorResult<PseudonimJPA>();
    _validator.validate(_bvr_, target, isNou, __pseudonimManager);
    return _bvr_.getErrors();
  }
}
