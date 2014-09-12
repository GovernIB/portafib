package es.caib.portafib.jpa.validator;

import es.caib.portafib.jpa.TipusMetadadaJPA;
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
public class TipusMetadadaBeanValidator 
      extends AbstractBeanValidator<TipusMetadadaJPA> {


  // EJB's
  protected final es.caib.portafib.model.dao.ITipusMetadadaManager __tipusMetadadaManager;


  public final TipusMetadadaValidator<TipusMetadadaJPA> _validator;


  public TipusMetadadaBeanValidator(es.caib.portafib.model.dao.ITipusMetadadaManager __tipusMetadadaManager) { 
    this.__tipusMetadadaManager = __tipusMetadadaManager;
    _validator = new TipusMetadadaValidator<TipusMetadadaJPA>();
  }

  public TipusMetadadaBeanValidator(TipusMetadadaValidator<TipusMetadadaJPA> _validator,
     es.caib.portafib.model.dao.ITipusMetadadaManager __tipusMetadadaManager) {
    this.__tipusMetadadaManager = __tipusMetadadaManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(TipusMetadadaJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<TipusMetadadaJPA> _bvr_ = new BeanValidatorResult<TipusMetadadaJPA>();
    _validator.validate(_bvr_, target, isNou, __tipusMetadadaManager);
    return _bvr_.getErrors();
  }
}
