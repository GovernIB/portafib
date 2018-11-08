package es.caib.portafib.jpa.validator;

import es.caib.portafib.jpa.FirmaJPA;
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
public class FirmaBeanValidator 
      extends AbstractBeanValidator<FirmaJPA> {


  // EJB's
  protected final es.caib.portafib.model.dao.IBlocDeFirmesManager __blocDeFirmesManager;

  protected final es.caib.portafib.model.dao.IFirmaManager __firmaManager;

  protected final es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager;


  public final FirmaValidator<FirmaJPA> _validator;


  public FirmaBeanValidator(es.caib.portafib.model.dao.IBlocDeFirmesManager __blocDeFirmesManager,
     es.caib.portafib.model.dao.IFirmaManager __firmaManager,
     es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager) { 
    this.__blocDeFirmesManager = __blocDeFirmesManager;
    this.__firmaManager = __firmaManager;
    this.__usuariEntitatManager = __usuariEntitatManager;
    _validator = new FirmaValidator<FirmaJPA>();
  }

  public FirmaBeanValidator(FirmaValidator<FirmaJPA> _validator,
     es.caib.portafib.model.dao.IBlocDeFirmesManager __blocDeFirmesManager,
     es.caib.portafib.model.dao.IFirmaManager __firmaManager,
     es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager) {
    this.__blocDeFirmesManager = __blocDeFirmesManager;
    this.__firmaManager = __firmaManager;
    this.__usuariEntitatManager = __usuariEntitatManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(FirmaJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<FirmaJPA> _bvr_ = new BeanValidatorResult<FirmaJPA>();
    _validator.validate(_bvr_, target, isNou, __blocDeFirmesManager, __firmaManager, __usuariEntitatManager);
    return _bvr_.getErrors();
  }
}
