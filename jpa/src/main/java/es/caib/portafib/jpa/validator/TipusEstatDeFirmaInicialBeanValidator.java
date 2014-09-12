package es.caib.portafib.jpa.validator;

import es.caib.portafib.jpa.TipusEstatDeFirmaInicialJPA;
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
public class TipusEstatDeFirmaInicialBeanValidator 
      extends AbstractBeanValidator<TipusEstatDeFirmaInicialJPA> {


  // EJB's
  protected final es.caib.portafib.model.dao.ITipusEstatDeFirmaInicialManager __tipusEstatDeFirmaInicialManager;


  public final TipusEstatDeFirmaInicialValidator<TipusEstatDeFirmaInicialJPA> _validator;


  public TipusEstatDeFirmaInicialBeanValidator(es.caib.portafib.model.dao.ITipusEstatDeFirmaInicialManager __tipusEstatDeFirmaInicialManager) { 
    this.__tipusEstatDeFirmaInicialManager = __tipusEstatDeFirmaInicialManager;
    _validator = new TipusEstatDeFirmaInicialValidator<TipusEstatDeFirmaInicialJPA>();
  }

  public TipusEstatDeFirmaInicialBeanValidator(TipusEstatDeFirmaInicialValidator<TipusEstatDeFirmaInicialJPA> _validator,
     es.caib.portafib.model.dao.ITipusEstatDeFirmaInicialManager __tipusEstatDeFirmaInicialManager) {
    this.__tipusEstatDeFirmaInicialManager = __tipusEstatDeFirmaInicialManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(TipusEstatDeFirmaInicialJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<TipusEstatDeFirmaInicialJPA> _bvr_ = new BeanValidatorResult<TipusEstatDeFirmaInicialJPA>();
    _validator.validate(_bvr_, target, isNou, __tipusEstatDeFirmaInicialManager);
    return _bvr_.getErrors();
  }
}
