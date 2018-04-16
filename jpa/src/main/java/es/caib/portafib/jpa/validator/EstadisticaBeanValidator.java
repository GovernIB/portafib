package es.caib.portafib.jpa.validator;

import es.caib.portafib.jpa.EstadisticaJPA;
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
public class EstadisticaBeanValidator 
      extends AbstractBeanValidator<EstadisticaJPA> {


  // EJB's
  protected final es.caib.portafib.model.dao.IEntitatManager __entitatManager;

  protected final es.caib.portafib.model.dao.IEstadisticaManager __estadisticaManager;


  public final EstadisticaValidator<EstadisticaJPA> _validator;


  public EstadisticaBeanValidator(es.caib.portafib.model.dao.IEntitatManager __entitatManager,
     es.caib.portafib.model.dao.IEstadisticaManager __estadisticaManager) { 
    this.__entitatManager = __entitatManager;
    this.__estadisticaManager = __estadisticaManager;
    _validator = new EstadisticaValidator<EstadisticaJPA>();
  }

  public EstadisticaBeanValidator(EstadisticaValidator<EstadisticaJPA> _validator,
     es.caib.portafib.model.dao.IEntitatManager __entitatManager,
     es.caib.portafib.model.dao.IEstadisticaManager __estadisticaManager) {
    this.__entitatManager = __entitatManager;
    this.__estadisticaManager = __estadisticaManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(EstadisticaJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<EstadisticaJPA> _bvr_ = new BeanValidatorResult<EstadisticaJPA>();
    _validator.validate(_bvr_, target, isNou, __entitatManager, __estadisticaManager);
    return _bvr_.getErrors();
  }
}
