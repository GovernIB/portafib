package es.caib.portafib.jpa.validator;

import es.caib.portafib.jpa.UsuariEntitatFavoritJPA;
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
public class UsuariEntitatFavoritBeanValidator 
      extends AbstractBeanValidator<UsuariEntitatFavoritJPA> {


  // EJB's
  protected final es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager;

  protected final es.caib.portafib.model.dao.IUsuariEntitatFavoritManager __usuariEntitatFavoritManager;


  public final UsuariEntitatFavoritValidator<UsuariEntitatFavoritJPA> _validator;


  public UsuariEntitatFavoritBeanValidator(es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager,
     es.caib.portafib.model.dao.IUsuariEntitatFavoritManager __usuariEntitatFavoritManager) { 
    this.__usuariEntitatManager = __usuariEntitatManager;
    this.__usuariEntitatFavoritManager = __usuariEntitatFavoritManager;
    _validator = new UsuariEntitatFavoritValidator<UsuariEntitatFavoritJPA>();
  }

  public UsuariEntitatFavoritBeanValidator(UsuariEntitatFavoritValidator<UsuariEntitatFavoritJPA> _validator,
     es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager,
     es.caib.portafib.model.dao.IUsuariEntitatFavoritManager __usuariEntitatFavoritManager) {
    this.__usuariEntitatManager = __usuariEntitatManager;
    this.__usuariEntitatFavoritManager = __usuariEntitatFavoritManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(UsuariEntitatFavoritJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<UsuariEntitatFavoritJPA> _bvr_ = new BeanValidatorResult<UsuariEntitatFavoritJPA>();
    _validator.validate(_bvr_, target, isNou, __usuariEntitatManager, __usuariEntitatFavoritManager);
    return _bvr_.getErrors();
  }
}
