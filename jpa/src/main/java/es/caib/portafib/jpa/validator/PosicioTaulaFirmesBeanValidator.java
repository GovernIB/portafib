package es.caib.portafib.jpa.validator;

import es.caib.portafib.jpa.PosicioTaulaFirmesJPA;
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
public class PosicioTaulaFirmesBeanValidator 
      extends AbstractBeanValidator<PosicioTaulaFirmesJPA> {


  // EJB's
  protected final es.caib.portafib.model.dao.IPosicioTaulaFirmesManager __posicioTaulaFirmesManager;


  public final PosicioTaulaFirmesValidator<PosicioTaulaFirmesJPA> _validator;


  public PosicioTaulaFirmesBeanValidator(es.caib.portafib.model.dao.IPosicioTaulaFirmesManager __posicioTaulaFirmesManager) { 
    this.__posicioTaulaFirmesManager = __posicioTaulaFirmesManager;
    _validator = new PosicioTaulaFirmesValidator<PosicioTaulaFirmesJPA>();
  }

  public PosicioTaulaFirmesBeanValidator(PosicioTaulaFirmesValidator<PosicioTaulaFirmesJPA> _validator,
     es.caib.portafib.model.dao.IPosicioTaulaFirmesManager __posicioTaulaFirmesManager) {
    this.__posicioTaulaFirmesManager = __posicioTaulaFirmesManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(PosicioTaulaFirmesJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<PosicioTaulaFirmesJPA> _bvr_ = new BeanValidatorResult<PosicioTaulaFirmesJPA>();
    _validator.validate(_bvr_, target, isNou, __posicioTaulaFirmesManager);
    return _bvr_.getErrors();
  }
}
