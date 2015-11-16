package es.caib.portafib.jpa.validator;

import es.caib.portafib.jpa.PluginJPA;
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
public class PluginBeanValidator 
      extends AbstractBeanValidator<PluginJPA> {


  // EJB's
  protected final es.caib.portafib.model.dao.IEntitatManager __entitatManager;

  protected final es.caib.portafib.model.dao.IPluginManager __pluginManager;

  protected final es.caib.portafib.model.dao.ITraduccioManager __traduccioManager;


  public final PluginValidator<PluginJPA> _validator;


  public PluginBeanValidator(es.caib.portafib.model.dao.IEntitatManager __entitatManager,
     es.caib.portafib.model.dao.IPluginManager __pluginManager,
     es.caib.portafib.model.dao.ITraduccioManager __traduccioManager) { 
    this.__entitatManager = __entitatManager;
    this.__pluginManager = __pluginManager;
    this.__traduccioManager = __traduccioManager;
    _validator = new PluginValidator<PluginJPA>();
  }

  public PluginBeanValidator(PluginValidator<PluginJPA> _validator,
     es.caib.portafib.model.dao.IEntitatManager __entitatManager,
     es.caib.portafib.model.dao.IPluginManager __pluginManager,
     es.caib.portafib.model.dao.ITraduccioManager __traduccioManager) {
    this.__entitatManager = __entitatManager;
    this.__pluginManager = __pluginManager;
    this.__traduccioManager = __traduccioManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(PluginJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<PluginJPA> _bvr_ = new BeanValidatorResult<PluginJPA>();
    _validator.validate(_bvr_, target, isNou, __entitatManager, __pluginManager, __traduccioManager);
    return _bvr_.getErrors();
  }
}
