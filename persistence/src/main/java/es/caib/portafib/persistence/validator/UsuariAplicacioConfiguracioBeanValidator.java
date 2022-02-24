package es.caib.portafib.persistence.validator;

import es.caib.portafib.persistence.UsuariAplicacioConfiguracioJPA;
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
public class UsuariAplicacioConfiguracioBeanValidator 
      extends AbstractBeanValidator<UsuariAplicacioConfiguracioJPA> {


  // EJB's
  protected final es.caib.portafib.model.dao.IEntitatManager __entitatManager;

  protected final es.caib.portafib.model.dao.IPluginManager __pluginManager;

  protected final es.caib.portafib.model.dao.ITraduccioManager __traduccioManager;

  protected final es.caib.portafib.model.dao.IUsuariAplicacioConfiguracioManager __usuariAplicacioConfiguracioManager;


  public final UsuariAplicacioConfiguracioValidator<UsuariAplicacioConfiguracioJPA> _validator;


  public UsuariAplicacioConfiguracioBeanValidator(es.caib.portafib.model.dao.IEntitatManager __entitatManager,
     es.caib.portafib.model.dao.IPluginManager __pluginManager,
     es.caib.portafib.model.dao.ITraduccioManager __traduccioManager,
     es.caib.portafib.model.dao.IUsuariAplicacioConfiguracioManager __usuariAplicacioConfiguracioManager) { 
    this.__entitatManager = __entitatManager;
    this.__pluginManager = __pluginManager;
    this.__traduccioManager = __traduccioManager;
    this.__usuariAplicacioConfiguracioManager = __usuariAplicacioConfiguracioManager;
    _validator = new UsuariAplicacioConfiguracioValidator<UsuariAplicacioConfiguracioJPA>();
  }

  public UsuariAplicacioConfiguracioBeanValidator(UsuariAplicacioConfiguracioValidator<UsuariAplicacioConfiguracioJPA> _validator,
     es.caib.portafib.model.dao.IEntitatManager __entitatManager,
     es.caib.portafib.model.dao.IPluginManager __pluginManager,
     es.caib.portafib.model.dao.ITraduccioManager __traduccioManager,
     es.caib.portafib.model.dao.IUsuariAplicacioConfiguracioManager __usuariAplicacioConfiguracioManager) {
    this.__entitatManager = __entitatManager;
    this.__pluginManager = __pluginManager;
    this.__traduccioManager = __traduccioManager;
    this.__usuariAplicacioConfiguracioManager = __usuariAplicacioConfiguracioManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(UsuariAplicacioConfiguracioJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<UsuariAplicacioConfiguracioJPA> _bvr_ = new BeanValidatorResult<UsuariAplicacioConfiguracioJPA>();
    _validator.validate(_bvr_, target, isNou, __entitatManager, __pluginManager, __traduccioManager, __usuariAplicacioConfiguracioManager);
    return _bvr_.getErrors();
  }
}