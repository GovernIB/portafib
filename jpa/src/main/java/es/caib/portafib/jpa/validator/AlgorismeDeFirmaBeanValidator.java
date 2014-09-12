package es.caib.portafib.jpa.validator;

import es.caib.portafib.jpa.AlgorismeDeFirmaJPA;
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
public class AlgorismeDeFirmaBeanValidator 
      extends AbstractBeanValidator<AlgorismeDeFirmaJPA> {


  // EJB's
  protected final es.caib.portafib.model.dao.IAlgorismeDeFirmaManager __algorismeDeFirmaManager;


  public final AlgorismeDeFirmaValidator<AlgorismeDeFirmaJPA> _validator;


  public AlgorismeDeFirmaBeanValidator(es.caib.portafib.model.dao.IAlgorismeDeFirmaManager __algorismeDeFirmaManager) { 
    this.__algorismeDeFirmaManager = __algorismeDeFirmaManager;
    _validator = new AlgorismeDeFirmaValidator<AlgorismeDeFirmaJPA>();
  }

  public AlgorismeDeFirmaBeanValidator(AlgorismeDeFirmaValidator<AlgorismeDeFirmaJPA> _validator,
     es.caib.portafib.model.dao.IAlgorismeDeFirmaManager __algorismeDeFirmaManager) {
    this.__algorismeDeFirmaManager = __algorismeDeFirmaManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(AlgorismeDeFirmaJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<AlgorismeDeFirmaJPA> _bvr_ = new BeanValidatorResult<AlgorismeDeFirmaJPA>();
    _validator.validate(_bvr_, target, isNou, __algorismeDeFirmaManager);
    return _bvr_.getErrors();
  }
}
