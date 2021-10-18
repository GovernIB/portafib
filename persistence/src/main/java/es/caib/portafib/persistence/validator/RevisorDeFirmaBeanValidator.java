package es.caib.portafib.persistence.validator;

import es.caib.portafib.persistence.RevisorDeFirmaJPA;
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
public class RevisorDeFirmaBeanValidator 
      extends AbstractBeanValidator<RevisorDeFirmaJPA> {


  // EJB's
  protected final es.caib.portafib.model.dao.IFirmaManager __firmaManager;

  protected final es.caib.portafib.model.dao.IRevisorDeFirmaManager __revisorDeFirmaManager;

  protected final es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager;


  public final RevisorDeFirmaValidator<RevisorDeFirmaJPA> _validator;


  public RevisorDeFirmaBeanValidator(es.caib.portafib.model.dao.IFirmaManager __firmaManager,
     es.caib.portafib.model.dao.IRevisorDeFirmaManager __revisorDeFirmaManager,
     es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager) { 
    this.__firmaManager = __firmaManager;
    this.__revisorDeFirmaManager = __revisorDeFirmaManager;
    this.__usuariEntitatManager = __usuariEntitatManager;
    _validator = new RevisorDeFirmaValidator<RevisorDeFirmaJPA>();
  }

  public RevisorDeFirmaBeanValidator(RevisorDeFirmaValidator<RevisorDeFirmaJPA> _validator,
     es.caib.portafib.model.dao.IFirmaManager __firmaManager,
     es.caib.portafib.model.dao.IRevisorDeFirmaManager __revisorDeFirmaManager,
     es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager) {
    this.__firmaManager = __firmaManager;
    this.__revisorDeFirmaManager = __revisorDeFirmaManager;
    this.__usuariEntitatManager = __usuariEntitatManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(RevisorDeFirmaJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<RevisorDeFirmaJPA> _bvr_ = new BeanValidatorResult<RevisorDeFirmaJPA>();
    _validator.validate(_bvr_, target, isNou, __firmaManager, __revisorDeFirmaManager, __usuariEntitatManager);
    return _bvr_.getErrors();
  }
}
