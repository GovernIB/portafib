package es.caib.portafib.jpa.validator;

import es.caib.portafib.jpa.PluginFirmaWebPerUsuariEntitatJPA;
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
public class PluginFirmaWebPerUsuariEntitatBeanValidator 
      extends AbstractBeanValidator<PluginFirmaWebPerUsuariEntitatJPA> {


  // EJB's
  protected final es.caib.portafib.model.dao.IPluginManager __pluginManager;

  protected final es.caib.portafib.model.dao.IPluginFirmaWebPerUsuariEntitatManager __pluginFirmaWebPerUsuariEntitatManager;

  protected final es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager;


  public final PluginFirmaWebPerUsuariEntitatValidator<PluginFirmaWebPerUsuariEntitatJPA> _validator;


  public PluginFirmaWebPerUsuariEntitatBeanValidator(es.caib.portafib.model.dao.IPluginManager __pluginManager,
     es.caib.portafib.model.dao.IPluginFirmaWebPerUsuariEntitatManager __pluginFirmaWebPerUsuariEntitatManager,
     es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager) { 
    this.__pluginManager = __pluginManager;
    this.__pluginFirmaWebPerUsuariEntitatManager = __pluginFirmaWebPerUsuariEntitatManager;
    this.__usuariEntitatManager = __usuariEntitatManager;
    _validator = new PluginFirmaWebPerUsuariEntitatValidator<PluginFirmaWebPerUsuariEntitatJPA>();
  }

  public PluginFirmaWebPerUsuariEntitatBeanValidator(PluginFirmaWebPerUsuariEntitatValidator<PluginFirmaWebPerUsuariEntitatJPA> _validator,
     es.caib.portafib.model.dao.IPluginManager __pluginManager,
     es.caib.portafib.model.dao.IPluginFirmaWebPerUsuariEntitatManager __pluginFirmaWebPerUsuariEntitatManager,
     es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager) {
    this.__pluginManager = __pluginManager;
    this.__pluginFirmaWebPerUsuariEntitatManager = __pluginFirmaWebPerUsuariEntitatManager;
    this.__usuariEntitatManager = __usuariEntitatManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(PluginFirmaWebPerUsuariEntitatJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<PluginFirmaWebPerUsuariEntitatJPA> _bvr_ = new BeanValidatorResult<PluginFirmaWebPerUsuariEntitatJPA>();
    _validator.validate(_bvr_, target, isNou, __pluginManager, __pluginFirmaWebPerUsuariEntitatManager, __usuariEntitatManager);
    return _bvr_.getErrors();
  }
}
