package es.caib.portafib.jpa.validator;

import es.caib.portafib.jpa.CodiBarresJPA;
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
public class CodiBarresBeanValidator 
      extends AbstractBeanValidator<CodiBarresJPA> {


  // EJB's
  protected final es.caib.portafib.model.dao.ICodiBarresManager __codiBarresManager;


  public final CodiBarresValidator<CodiBarresJPA> _validator;


  public CodiBarresBeanValidator(es.caib.portafib.model.dao.ICodiBarresManager __codiBarresManager) { 
    this.__codiBarresManager = __codiBarresManager;
    _validator = new CodiBarresValidator<CodiBarresJPA>();
  }

  public CodiBarresBeanValidator(CodiBarresValidator<CodiBarresJPA> _validator,
     es.caib.portafib.model.dao.ICodiBarresManager __codiBarresManager) {
    this.__codiBarresManager = __codiBarresManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(CodiBarresJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<CodiBarresJPA> _bvr_ = new BeanValidatorResult<CodiBarresJPA>();
    _validator.validate(_bvr_, target, isNou, __codiBarresManager);
    return _bvr_.getErrors();
  }
}
