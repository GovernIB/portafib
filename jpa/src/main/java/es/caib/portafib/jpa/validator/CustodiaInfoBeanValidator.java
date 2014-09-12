package es.caib.portafib.jpa.validator;

import es.caib.portafib.jpa.CustodiaInfoJPA;
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
public class CustodiaInfoBeanValidator 
      extends AbstractBeanValidator<CustodiaInfoJPA> {


  // EJB's
  protected final es.caib.portafib.model.dao.ICodiBarresManager __codiBarresManager;

  protected final es.caib.portafib.model.dao.ICustodiaInfoManager __custodiaInfoManager;

  protected final es.caib.portafib.model.dao.IEntitatManager __entitatManager;

  protected final es.caib.portafib.model.dao.IPosicioPaginaManager __posicioPaginaManager;

  protected final es.caib.portafib.model.dao.IUsuariAplicacioManager __usuariAplicacioManager;

  protected final es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager;


  public final CustodiaInfoValidator<CustodiaInfoJPA> _validator;


  public CustodiaInfoBeanValidator(es.caib.portafib.model.dao.ICodiBarresManager __codiBarresManager,
     es.caib.portafib.model.dao.ICustodiaInfoManager __custodiaInfoManager,
     es.caib.portafib.model.dao.IEntitatManager __entitatManager,
     es.caib.portafib.model.dao.IPosicioPaginaManager __posicioPaginaManager,
     es.caib.portafib.model.dao.IUsuariAplicacioManager __usuariAplicacioManager,
     es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager) { 
    this.__codiBarresManager = __codiBarresManager;
    this.__custodiaInfoManager = __custodiaInfoManager;
    this.__entitatManager = __entitatManager;
    this.__posicioPaginaManager = __posicioPaginaManager;
    this.__usuariAplicacioManager = __usuariAplicacioManager;
    this.__usuariEntitatManager = __usuariEntitatManager;
    _validator = new CustodiaInfoValidator<CustodiaInfoJPA>();
  }

  public CustodiaInfoBeanValidator(CustodiaInfoValidator<CustodiaInfoJPA> _validator,
     es.caib.portafib.model.dao.ICodiBarresManager __codiBarresManager,
     es.caib.portafib.model.dao.ICustodiaInfoManager __custodiaInfoManager,
     es.caib.portafib.model.dao.IEntitatManager __entitatManager,
     es.caib.portafib.model.dao.IPosicioPaginaManager __posicioPaginaManager,
     es.caib.portafib.model.dao.IUsuariAplicacioManager __usuariAplicacioManager,
     es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager) {
    this.__codiBarresManager = __codiBarresManager;
    this.__custodiaInfoManager = __custodiaInfoManager;
    this.__entitatManager = __entitatManager;
    this.__posicioPaginaManager = __posicioPaginaManager;
    this.__usuariAplicacioManager = __usuariAplicacioManager;
    this.__usuariEntitatManager = __usuariEntitatManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(CustodiaInfoJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<CustodiaInfoJPA> _bvr_ = new BeanValidatorResult<CustodiaInfoJPA>();
    _validator.validate(_bvr_, target, isNou, __codiBarresManager, __custodiaInfoManager, __entitatManager, __posicioPaginaManager, __usuariAplicacioManager, __usuariEntitatManager);
    return _bvr_.getErrors();
  }
}
