package es.caib.portafib.jpa.validator;

import es.caib.portafib.jpa.PlantillaFluxDeFirmesJPA;
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
public class PlantillaFluxDeFirmesBeanValidator 
      extends AbstractBeanValidator<PlantillaFluxDeFirmesJPA> {


  // EJB's
  protected final es.caib.portafib.model.dao.IFluxDeFirmesManager __fluxDeFirmesManager;

  protected final es.caib.portafib.model.dao.IPlantillaFluxDeFirmesManager __plantillaFluxDeFirmesManager;

  protected final es.caib.portafib.model.dao.IUsuariAplicacioManager __usuariAplicacioManager;

  protected final es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager;


  public final PlantillaFluxDeFirmesValidator<PlantillaFluxDeFirmesJPA> _validator;


  public PlantillaFluxDeFirmesBeanValidator(es.caib.portafib.model.dao.IFluxDeFirmesManager __fluxDeFirmesManager,
     es.caib.portafib.model.dao.IPlantillaFluxDeFirmesManager __plantillaFluxDeFirmesManager,
     es.caib.portafib.model.dao.IUsuariAplicacioManager __usuariAplicacioManager,
     es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager) { 
    this.__fluxDeFirmesManager = __fluxDeFirmesManager;
    this.__plantillaFluxDeFirmesManager = __plantillaFluxDeFirmesManager;
    this.__usuariAplicacioManager = __usuariAplicacioManager;
    this.__usuariEntitatManager = __usuariEntitatManager;
    _validator = new PlantillaFluxDeFirmesValidator<PlantillaFluxDeFirmesJPA>();
  }

  public PlantillaFluxDeFirmesBeanValidator(PlantillaFluxDeFirmesValidator<PlantillaFluxDeFirmesJPA> _validator,
     es.caib.portafib.model.dao.IFluxDeFirmesManager __fluxDeFirmesManager,
     es.caib.portafib.model.dao.IPlantillaFluxDeFirmesManager __plantillaFluxDeFirmesManager,
     es.caib.portafib.model.dao.IUsuariAplicacioManager __usuariAplicacioManager,
     es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager) {
    this.__fluxDeFirmesManager = __fluxDeFirmesManager;
    this.__plantillaFluxDeFirmesManager = __plantillaFluxDeFirmesManager;
    this.__usuariAplicacioManager = __usuariAplicacioManager;
    this.__usuariEntitatManager = __usuariEntitatManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(PlantillaFluxDeFirmesJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<PlantillaFluxDeFirmesJPA> _bvr_ = new BeanValidatorResult<PlantillaFluxDeFirmesJPA>();
    _validator.validate(_bvr_, target, isNou, __fluxDeFirmesManager, __plantillaFluxDeFirmesManager, __usuariAplicacioManager, __usuariEntitatManager);
    return _bvr_.getErrors();
  }
}
