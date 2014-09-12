package es.caib.portafib.jpa.validator;

import es.caib.portafib.jpa.TipusFirmaJPA;
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
public class TipusFirmaBeanValidator 
      extends AbstractBeanValidator<TipusFirmaJPA> {


  // EJB's
  protected final es.caib.portafib.model.dao.ITipusFirmaManager __tipusFirmaManager;


  public final TipusFirmaValidator<TipusFirmaJPA> _validator;


  public TipusFirmaBeanValidator(es.caib.portafib.model.dao.ITipusFirmaManager __tipusFirmaManager) { 
    this.__tipusFirmaManager = __tipusFirmaManager;
    _validator = new TipusFirmaValidator<TipusFirmaJPA>();
  }

  public TipusFirmaBeanValidator(TipusFirmaValidator<TipusFirmaJPA> _validator,
     es.caib.portafib.model.dao.ITipusFirmaManager __tipusFirmaManager) {
    this.__tipusFirmaManager = __tipusFirmaManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(TipusFirmaJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<TipusFirmaJPA> _bvr_ = new BeanValidatorResult<TipusFirmaJPA>();
    _validator.validate(_bvr_, target, isNou, __tipusFirmaManager);
    return _bvr_.getErrors();
  }
}
