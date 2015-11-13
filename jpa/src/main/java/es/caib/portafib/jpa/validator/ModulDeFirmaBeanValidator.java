package es.caib.portafib.jpa.validator;

import es.caib.portafib.jpa.ModulDeFirmaJPA;
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
public class ModulDeFirmaBeanValidator 
      extends AbstractBeanValidator<ModulDeFirmaJPA> {


  // EJB's
  protected final es.caib.portafib.model.dao.IEntitatManager __entitatManager;

  protected final es.caib.portafib.model.dao.IModulDeFirmaManager __modulDeFirmaManager;

  protected final es.caib.portafib.model.dao.ITraduccioManager __traduccioManager;


  public final ModulDeFirmaValidator<ModulDeFirmaJPA> _validator;


  public ModulDeFirmaBeanValidator(es.caib.portafib.model.dao.IEntitatManager __entitatManager,
     es.caib.portafib.model.dao.IModulDeFirmaManager __modulDeFirmaManager,
     es.caib.portafib.model.dao.ITraduccioManager __traduccioManager) { 
    this.__entitatManager = __entitatManager;
    this.__modulDeFirmaManager = __modulDeFirmaManager;
    this.__traduccioManager = __traduccioManager;
    _validator = new ModulDeFirmaValidator<ModulDeFirmaJPA>();
  }

  public ModulDeFirmaBeanValidator(ModulDeFirmaValidator<ModulDeFirmaJPA> _validator,
     es.caib.portafib.model.dao.IEntitatManager __entitatManager,
     es.caib.portafib.model.dao.IModulDeFirmaManager __modulDeFirmaManager,
     es.caib.portafib.model.dao.ITraduccioManager __traduccioManager) {
    this.__entitatManager = __entitatManager;
    this.__modulDeFirmaManager = __modulDeFirmaManager;
    this.__traduccioManager = __traduccioManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(ModulDeFirmaJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<ModulDeFirmaJPA> _bvr_ = new BeanValidatorResult<ModulDeFirmaJPA>();
    _validator.validate(_bvr_, target, isNou, __entitatManager, __modulDeFirmaManager, __traduccioManager);
    return _bvr_.getErrors();
  }
}
