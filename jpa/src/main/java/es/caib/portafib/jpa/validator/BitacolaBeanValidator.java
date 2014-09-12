package es.caib.portafib.jpa.validator;

import es.caib.portafib.jpa.BitacolaJPA;
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
public class BitacolaBeanValidator 
      extends AbstractBeanValidator<BitacolaJPA> {


  // EJB's
  protected final es.caib.portafib.model.dao.IBitacolaManager __bitacolaManager;

  protected final es.caib.portafib.model.dao.IPeticioDeFirmaManager __peticioDeFirmaManager;

  protected final es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager;


  public final BitacolaValidator<BitacolaJPA> _validator;


  public BitacolaBeanValidator(es.caib.portafib.model.dao.IBitacolaManager __bitacolaManager,
     es.caib.portafib.model.dao.IPeticioDeFirmaManager __peticioDeFirmaManager,
     es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager) { 
    this.__bitacolaManager = __bitacolaManager;
    this.__peticioDeFirmaManager = __peticioDeFirmaManager;
    this.__usuariEntitatManager = __usuariEntitatManager;
    _validator = new BitacolaValidator<BitacolaJPA>();
  }

  public BitacolaBeanValidator(BitacolaValidator<BitacolaJPA> _validator,
     es.caib.portafib.model.dao.IBitacolaManager __bitacolaManager,
     es.caib.portafib.model.dao.IPeticioDeFirmaManager __peticioDeFirmaManager,
     es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager) {
    this.__bitacolaManager = __bitacolaManager;
    this.__peticioDeFirmaManager = __peticioDeFirmaManager;
    this.__usuariEntitatManager = __usuariEntitatManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(BitacolaJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<BitacolaJPA> _bvr_ = new BeanValidatorResult<BitacolaJPA>();
    _validator.validate(_bvr_, target, isNou, __bitacolaManager, __peticioDeFirmaManager, __usuariEntitatManager);
    return _bvr_.getErrors();
  }
}
