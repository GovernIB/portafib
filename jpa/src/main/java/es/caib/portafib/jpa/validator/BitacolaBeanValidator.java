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


  public final BitacolaValidator<BitacolaJPA> _validator;


  public BitacolaBeanValidator(es.caib.portafib.model.dao.IBitacolaManager __bitacolaManager) { 
    this.__bitacolaManager = __bitacolaManager;
    _validator = new BitacolaValidator<BitacolaJPA>();
  }

  public BitacolaBeanValidator(BitacolaValidator<BitacolaJPA> _validator,
     es.caib.portafib.model.dao.IBitacolaManager __bitacolaManager) {
    this.__bitacolaManager = __bitacolaManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(BitacolaJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<BitacolaJPA> _bvr_ = new BeanValidatorResult<BitacolaJPA>();
    _validator.validate(_bvr_, target, isNou, __bitacolaManager);
    return _bvr_.getErrors();
  }
}
