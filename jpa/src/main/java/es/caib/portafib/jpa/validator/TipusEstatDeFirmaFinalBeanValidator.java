package es.caib.portafib.jpa.validator;

import es.caib.portafib.jpa.TipusEstatDeFirmaFinalJPA;
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
public class TipusEstatDeFirmaFinalBeanValidator 
      extends AbstractBeanValidator<TipusEstatDeFirmaFinalJPA> {


  // EJB's
  protected final es.caib.portafib.model.dao.ITipusEstatDeFirmaFinalManager __tipusEstatDeFirmaFinalManager;


  public final TipusEstatDeFirmaFinalValidator<TipusEstatDeFirmaFinalJPA> _validator;


  public TipusEstatDeFirmaFinalBeanValidator(es.caib.portafib.model.dao.ITipusEstatDeFirmaFinalManager __tipusEstatDeFirmaFinalManager) { 
    this.__tipusEstatDeFirmaFinalManager = __tipusEstatDeFirmaFinalManager;
    _validator = new TipusEstatDeFirmaFinalValidator<TipusEstatDeFirmaFinalJPA>();
  }

  public TipusEstatDeFirmaFinalBeanValidator(TipusEstatDeFirmaFinalValidator<TipusEstatDeFirmaFinalJPA> _validator,
     es.caib.portafib.model.dao.ITipusEstatDeFirmaFinalManager __tipusEstatDeFirmaFinalManager) {
    this.__tipusEstatDeFirmaFinalManager = __tipusEstatDeFirmaFinalManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(TipusEstatDeFirmaFinalJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<TipusEstatDeFirmaFinalJPA> _bvr_ = new BeanValidatorResult<TipusEstatDeFirmaFinalJPA>();
    _validator.validate(_bvr_, target, isNou, __tipusEstatDeFirmaFinalManager);
    return _bvr_.getErrors();
  }
}
