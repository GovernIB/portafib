package es.caib.portafib.persistence.validator;

import es.caib.portafib.persistence.EntitatJPA;
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
public class EntitatBeanValidator 
      extends AbstractBeanValidator<EntitatJPA> {


  // EJB's
  protected final es.caib.portafib.model.dao.ICustodiaInfoManager __custodiaInfoManager;

  protected final es.caib.portafib.model.dao.IEntitatManager __entitatManager;

  protected final es.caib.portafib.model.dao.IPluginManager __pluginManager;

  protected final es.caib.portafib.model.dao.ITraduccioManager __traduccioManager;

  protected final es.caib.portafib.model.dao.IUsuariAplicacioManager __usuariAplicacioManager;


  public final EntitatValidator<EntitatJPA> _validator;


  public EntitatBeanValidator(es.caib.portafib.model.dao.ICustodiaInfoManager __custodiaInfoManager,
     es.caib.portafib.model.dao.IEntitatManager __entitatManager,
     es.caib.portafib.model.dao.IPluginManager __pluginManager,
     es.caib.portafib.model.dao.ITraduccioManager __traduccioManager,
     es.caib.portafib.model.dao.IUsuariAplicacioManager __usuariAplicacioManager) { 
    this.__custodiaInfoManager = __custodiaInfoManager;
    this.__entitatManager = __entitatManager;
    this.__pluginManager = __pluginManager;
    this.__traduccioManager = __traduccioManager;
    this.__usuariAplicacioManager = __usuariAplicacioManager;
    _validator = new EntitatValidator<EntitatJPA>();
  }

  public EntitatBeanValidator(EntitatValidator<EntitatJPA> _validator,
     es.caib.portafib.model.dao.ICustodiaInfoManager __custodiaInfoManager,
     es.caib.portafib.model.dao.IEntitatManager __entitatManager,
     es.caib.portafib.model.dao.IPluginManager __pluginManager,
     es.caib.portafib.model.dao.ITraduccioManager __traduccioManager,
     es.caib.portafib.model.dao.IUsuariAplicacioManager __usuariAplicacioManager) {
    this.__custodiaInfoManager = __custodiaInfoManager;
    this.__entitatManager = __entitatManager;
    this.__pluginManager = __pluginManager;
    this.__traduccioManager = __traduccioManager;
    this.__usuariAplicacioManager = __usuariAplicacioManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(EntitatJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<EntitatJPA> _bvr_ = new BeanValidatorResult<EntitatJPA>();
    _validator.validate(_bvr_, target, isNou, __custodiaInfoManager, __entitatManager, __pluginManager, __traduccioManager, __usuariAplicacioManager);
    return _bvr_.getErrors();
  }
}
