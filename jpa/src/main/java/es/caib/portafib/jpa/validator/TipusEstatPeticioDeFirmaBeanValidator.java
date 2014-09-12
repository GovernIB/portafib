package es.caib.portafib.jpa.validator;

import es.caib.portafib.jpa.TipusEstatPeticioDeFirmaJPA;
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
public class TipusEstatPeticioDeFirmaBeanValidator 
      extends AbstractBeanValidator<TipusEstatPeticioDeFirmaJPA> {


  // EJB's
  protected final es.caib.portafib.model.dao.ITipusEstatPeticioDeFirmaManager __tipusEstatPeticioDeFirmaManager;


  public final TipusEstatPeticioDeFirmaValidator<TipusEstatPeticioDeFirmaJPA> _validator;


  public TipusEstatPeticioDeFirmaBeanValidator(es.caib.portafib.model.dao.ITipusEstatPeticioDeFirmaManager __tipusEstatPeticioDeFirmaManager) { 
    this.__tipusEstatPeticioDeFirmaManager = __tipusEstatPeticioDeFirmaManager;
    _validator = new TipusEstatPeticioDeFirmaValidator<TipusEstatPeticioDeFirmaJPA>();
  }

  public TipusEstatPeticioDeFirmaBeanValidator(TipusEstatPeticioDeFirmaValidator<TipusEstatPeticioDeFirmaJPA> _validator,
     es.caib.portafib.model.dao.ITipusEstatPeticioDeFirmaManager __tipusEstatPeticioDeFirmaManager) {
    this.__tipusEstatPeticioDeFirmaManager = __tipusEstatPeticioDeFirmaManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(TipusEstatPeticioDeFirmaJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<TipusEstatPeticioDeFirmaJPA> _bvr_ = new BeanValidatorResult<TipusEstatPeticioDeFirmaJPA>();
    _validator.validate(_bvr_, target, isNou, __tipusEstatPeticioDeFirmaManager);
    return _bvr_.getErrors();
  }
}
