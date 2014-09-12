package es.caib.portafib.jpa.validator;

import es.caib.portafib.jpa.PermisUsuariPlantillaJPA;
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
public class PermisUsuariPlantillaBeanValidator 
      extends AbstractBeanValidator<PermisUsuariPlantillaJPA> {


  // EJB's
  protected final es.caib.portafib.model.dao.IPermisUsuariPlantillaManager __permisUsuariPlantillaManager;

  protected final es.caib.portafib.model.dao.IPlantillaFluxDeFirmesManager __plantillaFluxDeFirmesManager;

  protected final es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager;


  public final PermisUsuariPlantillaValidator<PermisUsuariPlantillaJPA> _validator;


  public PermisUsuariPlantillaBeanValidator(es.caib.portafib.model.dao.IPermisUsuariPlantillaManager __permisUsuariPlantillaManager,
     es.caib.portafib.model.dao.IPlantillaFluxDeFirmesManager __plantillaFluxDeFirmesManager,
     es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager) { 
    this.__permisUsuariPlantillaManager = __permisUsuariPlantillaManager;
    this.__plantillaFluxDeFirmesManager = __plantillaFluxDeFirmesManager;
    this.__usuariEntitatManager = __usuariEntitatManager;
    _validator = new PermisUsuariPlantillaValidator<PermisUsuariPlantillaJPA>();
  }

  public PermisUsuariPlantillaBeanValidator(PermisUsuariPlantillaValidator<PermisUsuariPlantillaJPA> _validator,
     es.caib.portafib.model.dao.IPermisUsuariPlantillaManager __permisUsuariPlantillaManager,
     es.caib.portafib.model.dao.IPlantillaFluxDeFirmesManager __plantillaFluxDeFirmesManager,
     es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager) {
    this.__permisUsuariPlantillaManager = __permisUsuariPlantillaManager;
    this.__plantillaFluxDeFirmesManager = __plantillaFluxDeFirmesManager;
    this.__usuariEntitatManager = __usuariEntitatManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(PermisUsuariPlantillaJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<PermisUsuariPlantillaJPA> _bvr_ = new BeanValidatorResult<PermisUsuariPlantillaJPA>();
    _validator.validate(_bvr_, target, isNou, __permisUsuariPlantillaManager, __plantillaFluxDeFirmesManager, __usuariEntitatManager);
    return _bvr_.getErrors();
  }
}
