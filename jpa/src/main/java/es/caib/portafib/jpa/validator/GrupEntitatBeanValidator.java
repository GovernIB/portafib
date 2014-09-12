package es.caib.portafib.jpa.validator;

import es.caib.portafib.jpa.GrupEntitatJPA;
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
public class GrupEntitatBeanValidator 
      extends AbstractBeanValidator<GrupEntitatJPA> {


  // EJB's
  protected final es.caib.portafib.model.dao.IEntitatManager __entitatManager;

  protected final es.caib.portafib.model.dao.IGrupEntitatManager __grupEntitatManager;


  public final GrupEntitatValidator<GrupEntitatJPA> _validator;


  public GrupEntitatBeanValidator(es.caib.portafib.model.dao.IEntitatManager __entitatManager,
     es.caib.portafib.model.dao.IGrupEntitatManager __grupEntitatManager) { 
    this.__entitatManager = __entitatManager;
    this.__grupEntitatManager = __grupEntitatManager;
    _validator = new GrupEntitatValidator<GrupEntitatJPA>();
  }

  public GrupEntitatBeanValidator(GrupEntitatValidator<GrupEntitatJPA> _validator,
     es.caib.portafib.model.dao.IEntitatManager __entitatManager,
     es.caib.portafib.model.dao.IGrupEntitatManager __grupEntitatManager) {
    this.__entitatManager = __entitatManager;
    this.__grupEntitatManager = __grupEntitatManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(GrupEntitatJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<GrupEntitatJPA> _bvr_ = new BeanValidatorResult<GrupEntitatJPA>();
    _validator.validate(_bvr_, target, isNou, __entitatManager, __grupEntitatManager);
    return _bvr_.getErrors();
  }
}
