package es.caib.portafib.persistence.validator;

import es.caib.portafib.persistence.PluginFirmaWebPerUsuariAplicacioJPA;
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
public class PluginFirmaWebPerUsuariAplicacioBeanValidator 
      extends AbstractBeanValidator<PluginFirmaWebPerUsuariAplicacioJPA> {


  // EJB's
  protected final es.caib.portafib.model.dao.IPluginManager __pluginManager;

  protected final es.caib.portafib.model.dao.IPluginFirmaWebPerUsuariAplicacioManager __pluginFirmaWebPerUsuariAplicacioManager;

  protected final es.caib.portafib.model.dao.IUsuariAplicacioManager __usuariAplicacioManager;


  public final PluginFirmaWebPerUsuariAplicacioValidator<PluginFirmaWebPerUsuariAplicacioJPA> _validator;


  public PluginFirmaWebPerUsuariAplicacioBeanValidator(es.caib.portafib.model.dao.IPluginManager __pluginManager,
     es.caib.portafib.model.dao.IPluginFirmaWebPerUsuariAplicacioManager __pluginFirmaWebPerUsuariAplicacioManager,
     es.caib.portafib.model.dao.IUsuariAplicacioManager __usuariAplicacioManager) { 
    this.__pluginManager = __pluginManager;
    this.__pluginFirmaWebPerUsuariAplicacioManager = __pluginFirmaWebPerUsuariAplicacioManager;
    this.__usuariAplicacioManager = __usuariAplicacioManager;
    _validator = new PluginFirmaWebPerUsuariAplicacioValidator<PluginFirmaWebPerUsuariAplicacioJPA>();
  }

  public PluginFirmaWebPerUsuariAplicacioBeanValidator(PluginFirmaWebPerUsuariAplicacioValidator<PluginFirmaWebPerUsuariAplicacioJPA> _validator,
     es.caib.portafib.model.dao.IPluginManager __pluginManager,
     es.caib.portafib.model.dao.IPluginFirmaWebPerUsuariAplicacioManager __pluginFirmaWebPerUsuariAplicacioManager,
     es.caib.portafib.model.dao.IUsuariAplicacioManager __usuariAplicacioManager) {
    this.__pluginManager = __pluginManager;
    this.__pluginFirmaWebPerUsuariAplicacioManager = __pluginFirmaWebPerUsuariAplicacioManager;
    this.__usuariAplicacioManager = __usuariAplicacioManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(PluginFirmaWebPerUsuariAplicacioJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<PluginFirmaWebPerUsuariAplicacioJPA> _bvr_ = new BeanValidatorResult<PluginFirmaWebPerUsuariAplicacioJPA>();
    _validator.validate(_bvr_, target, isNou, __pluginManager, __pluginFirmaWebPerUsuariAplicacioManager, __usuariAplicacioManager);
    return _bvr_.getErrors();
  }
}
