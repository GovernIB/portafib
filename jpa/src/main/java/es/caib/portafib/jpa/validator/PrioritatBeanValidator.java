package es.caib.portafib.jpa.validator;

import es.caib.portafib.jpa.PrioritatJPA;
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
public class PrioritatBeanValidator 
      extends AbstractBeanValidator<PrioritatJPA> {


  // EJB's
  protected final es.caib.portafib.model.dao.IPrioritatManager __prioritatManager;


  public final PrioritatValidator<PrioritatJPA> _validator;


  public PrioritatBeanValidator(es.caib.portafib.model.dao.IPrioritatManager __prioritatManager) { 
    this.__prioritatManager = __prioritatManager;
    _validator = new PrioritatValidator<PrioritatJPA>();
  }

  public PrioritatBeanValidator(PrioritatValidator<PrioritatJPA> _validator,
     es.caib.portafib.model.dao.IPrioritatManager __prioritatManager) {
    this.__prioritatManager = __prioritatManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(PrioritatJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<PrioritatJPA> _bvr_ = new BeanValidatorResult<PrioritatJPA>();
    _validator.validate(_bvr_, target, isNou, __prioritatManager);
    return _bvr_.getErrors();
  }
}
