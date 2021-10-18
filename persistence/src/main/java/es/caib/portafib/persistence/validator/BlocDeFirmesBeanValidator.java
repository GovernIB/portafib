package es.caib.portafib.persistence.validator;

import es.caib.portafib.persistence.BlocDeFirmesJPA;
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
public class BlocDeFirmesBeanValidator 
      extends AbstractBeanValidator<BlocDeFirmesJPA> {


  // EJB's
  protected final es.caib.portafib.model.dao.IBlocDeFirmesManager __blocDeFirmesManager;

  protected final es.caib.portafib.model.dao.IFluxDeFirmesManager __fluxDeFirmesManager;


  public final BlocDeFirmesValidator<BlocDeFirmesJPA> _validator;


  public BlocDeFirmesBeanValidator(es.caib.portafib.model.dao.IBlocDeFirmesManager __blocDeFirmesManager,
     es.caib.portafib.model.dao.IFluxDeFirmesManager __fluxDeFirmesManager) { 
    this.__blocDeFirmesManager = __blocDeFirmesManager;
    this.__fluxDeFirmesManager = __fluxDeFirmesManager;
    _validator = new BlocDeFirmesValidator<BlocDeFirmesJPA>();
  }

  public BlocDeFirmesBeanValidator(BlocDeFirmesValidator<BlocDeFirmesJPA> _validator,
     es.caib.portafib.model.dao.IBlocDeFirmesManager __blocDeFirmesManager,
     es.caib.portafib.model.dao.IFluxDeFirmesManager __fluxDeFirmesManager) {
    this.__blocDeFirmesManager = __blocDeFirmesManager;
    this.__fluxDeFirmesManager = __fluxDeFirmesManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(BlocDeFirmesJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<BlocDeFirmesJPA> _bvr_ = new BeanValidatorResult<BlocDeFirmesJPA>();
    _validator.validate(_bvr_, target, isNou, __blocDeFirmesManager, __fluxDeFirmesManager);
    return _bvr_.getErrors();
  }
}
