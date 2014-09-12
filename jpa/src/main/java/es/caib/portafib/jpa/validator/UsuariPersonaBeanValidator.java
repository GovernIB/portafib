package es.caib.portafib.jpa.validator;

import es.caib.portafib.jpa.UsuariPersonaJPA;
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
public class UsuariPersonaBeanValidator 
      extends AbstractBeanValidator<UsuariPersonaJPA> {


  // EJB's
  protected final es.caib.portafib.model.dao.IIdiomaManager __idiomaManager;

  protected final es.caib.portafib.model.dao.IUsuariPersonaManager __usuariPersonaManager;


  public final UsuariPersonaValidator<UsuariPersonaJPA> _validator;


  public UsuariPersonaBeanValidator(es.caib.portafib.model.dao.IIdiomaManager __idiomaManager,
     es.caib.portafib.model.dao.IUsuariPersonaManager __usuariPersonaManager) { 
    this.__idiomaManager = __idiomaManager;
    this.__usuariPersonaManager = __usuariPersonaManager;
    _validator = new UsuariPersonaValidator<UsuariPersonaJPA>();
  }

  public UsuariPersonaBeanValidator(UsuariPersonaValidator<UsuariPersonaJPA> _validator,
     es.caib.portafib.model.dao.IIdiomaManager __idiomaManager,
     es.caib.portafib.model.dao.IUsuariPersonaManager __usuariPersonaManager) {
    this.__idiomaManager = __idiomaManager;
    this.__usuariPersonaManager = __usuariPersonaManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(UsuariPersonaJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<UsuariPersonaJPA> _bvr_ = new BeanValidatorResult<UsuariPersonaJPA>();
    _validator.validate(_bvr_, target, isNou, __idiomaManager, __usuariPersonaManager);
    return _bvr_.getErrors();
  }
}
