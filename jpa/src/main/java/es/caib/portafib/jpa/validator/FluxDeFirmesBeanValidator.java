package es.caib.portafib.jpa.validator;

import es.caib.portafib.jpa.FluxDeFirmesJPA;
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
public class FluxDeFirmesBeanValidator 
      extends AbstractBeanValidator<FluxDeFirmesJPA> {


  // EJB's
  protected final es.caib.portafib.model.dao.IFluxDeFirmesManager __fluxDeFirmesManager;


  public final FluxDeFirmesValidator<FluxDeFirmesJPA> _validator;


  public FluxDeFirmesBeanValidator(es.caib.portafib.model.dao.IFluxDeFirmesManager __fluxDeFirmesManager) { 
    this.__fluxDeFirmesManager = __fluxDeFirmesManager;
    _validator = new FluxDeFirmesValidator<FluxDeFirmesJPA>();
  }

  public FluxDeFirmesBeanValidator(FluxDeFirmesValidator<FluxDeFirmesJPA> _validator,
     es.caib.portafib.model.dao.IFluxDeFirmesManager __fluxDeFirmesManager) {
    this.__fluxDeFirmesManager = __fluxDeFirmesManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(FluxDeFirmesJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<FluxDeFirmesJPA> _bvr_ = new BeanValidatorResult<FluxDeFirmesJPA>();
    _validator.validate(_bvr_, target, isNou, __fluxDeFirmesManager);
    return _bvr_.getErrors();
  }
}
