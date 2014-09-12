package es.caib.portafib.jpa.validator;

import es.caib.portafib.jpa.PermisGrupPlantillaJPA;
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
public class PermisGrupPlantillaBeanValidator 
      extends AbstractBeanValidator<PermisGrupPlantillaJPA> {


  // EJB's
  protected final es.caib.portafib.model.dao.IGrupEntitatManager __grupEntitatManager;

  protected final es.caib.portafib.model.dao.IPermisGrupPlantillaManager __permisGrupPlantillaManager;

  protected final es.caib.portafib.model.dao.IPlantillaFluxDeFirmesManager __plantillaFluxDeFirmesManager;


  public final PermisGrupPlantillaValidator<PermisGrupPlantillaJPA> _validator;


  public PermisGrupPlantillaBeanValidator(es.caib.portafib.model.dao.IGrupEntitatManager __grupEntitatManager,
     es.caib.portafib.model.dao.IPermisGrupPlantillaManager __permisGrupPlantillaManager,
     es.caib.portafib.model.dao.IPlantillaFluxDeFirmesManager __plantillaFluxDeFirmesManager) { 
    this.__grupEntitatManager = __grupEntitatManager;
    this.__permisGrupPlantillaManager = __permisGrupPlantillaManager;
    this.__plantillaFluxDeFirmesManager = __plantillaFluxDeFirmesManager;
    _validator = new PermisGrupPlantillaValidator<PermisGrupPlantillaJPA>();
  }

  public PermisGrupPlantillaBeanValidator(PermisGrupPlantillaValidator<PermisGrupPlantillaJPA> _validator,
     es.caib.portafib.model.dao.IGrupEntitatManager __grupEntitatManager,
     es.caib.portafib.model.dao.IPermisGrupPlantillaManager __permisGrupPlantillaManager,
     es.caib.portafib.model.dao.IPlantillaFluxDeFirmesManager __plantillaFluxDeFirmesManager) {
    this.__grupEntitatManager = __grupEntitatManager;
    this.__permisGrupPlantillaManager = __permisGrupPlantillaManager;
    this.__plantillaFluxDeFirmesManager = __plantillaFluxDeFirmesManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(PermisGrupPlantillaJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<PermisGrupPlantillaJPA> _bvr_ = new BeanValidatorResult<PermisGrupPlantillaJPA>();
    _validator.validate(_bvr_, target, isNou, __grupEntitatManager, __permisGrupPlantillaManager, __plantillaFluxDeFirmesManager);
    return _bvr_.getErrors();
  }
}
