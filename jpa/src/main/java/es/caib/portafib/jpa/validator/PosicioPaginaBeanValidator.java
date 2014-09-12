package es.caib.portafib.jpa.validator;

import es.caib.portafib.jpa.PosicioPaginaJPA;
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
public class PosicioPaginaBeanValidator 
      extends AbstractBeanValidator<PosicioPaginaJPA> {


  // EJB's
  protected final es.caib.portafib.model.dao.IPosicioPaginaManager __posicioPaginaManager;


  public final PosicioPaginaValidator<PosicioPaginaJPA> _validator;


  public PosicioPaginaBeanValidator(es.caib.portafib.model.dao.IPosicioPaginaManager __posicioPaginaManager) { 
    this.__posicioPaginaManager = __posicioPaginaManager;
    _validator = new PosicioPaginaValidator<PosicioPaginaJPA>();
  }

  public PosicioPaginaBeanValidator(PosicioPaginaValidator<PosicioPaginaJPA> _validator,
     es.caib.portafib.model.dao.IPosicioPaginaManager __posicioPaginaManager) {
    this.__posicioPaginaManager = __posicioPaginaManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(PosicioPaginaJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<PosicioPaginaJPA> _bvr_ = new BeanValidatorResult<PosicioPaginaJPA>();
    _validator.validate(_bvr_, target, isNou, __posicioPaginaManager);
    return _bvr_.getErrors();
  }
}
