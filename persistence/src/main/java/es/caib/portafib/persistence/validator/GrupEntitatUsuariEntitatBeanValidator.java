package es.caib.portafib.persistence.validator;

import es.caib.portafib.persistence.GrupEntitatUsuariEntitatJPA;
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
public class GrupEntitatUsuariEntitatBeanValidator 
      extends AbstractBeanValidator<GrupEntitatUsuariEntitatJPA> {


  // EJB's
  protected final es.caib.portafib.model.dao.IGrupEntitatManager __grupEntitatManager;

  protected final es.caib.portafib.model.dao.IGrupEntitatUsuariEntitatManager __grupEntitatUsuariEntitatManager;

  protected final es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager;


  public final GrupEntitatUsuariEntitatValidator<GrupEntitatUsuariEntitatJPA> _validator;


  public GrupEntitatUsuariEntitatBeanValidator(es.caib.portafib.model.dao.IGrupEntitatManager __grupEntitatManager,
     es.caib.portafib.model.dao.IGrupEntitatUsuariEntitatManager __grupEntitatUsuariEntitatManager,
     es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager) { 
    this.__grupEntitatManager = __grupEntitatManager;
    this.__grupEntitatUsuariEntitatManager = __grupEntitatUsuariEntitatManager;
    this.__usuariEntitatManager = __usuariEntitatManager;
    _validator = new GrupEntitatUsuariEntitatValidator<GrupEntitatUsuariEntitatJPA>();
  }

  public GrupEntitatUsuariEntitatBeanValidator(GrupEntitatUsuariEntitatValidator<GrupEntitatUsuariEntitatJPA> _validator,
     es.caib.portafib.model.dao.IGrupEntitatManager __grupEntitatManager,
     es.caib.portafib.model.dao.IGrupEntitatUsuariEntitatManager __grupEntitatUsuariEntitatManager,
     es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager) {
    this.__grupEntitatManager = __grupEntitatManager;
    this.__grupEntitatUsuariEntitatManager = __grupEntitatUsuariEntitatManager;
    this.__usuariEntitatManager = __usuariEntitatManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(GrupEntitatUsuariEntitatJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<GrupEntitatUsuariEntitatJPA> _bvr_ = new BeanValidatorResult<GrupEntitatUsuariEntitatJPA>();
    _validator.validate(_bvr_, target, isNou, __grupEntitatManager, __grupEntitatUsuariEntitatManager, __usuariEntitatManager);
    return _bvr_.getErrors();
  }
}
