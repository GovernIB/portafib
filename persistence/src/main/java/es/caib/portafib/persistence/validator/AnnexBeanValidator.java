package es.caib.portafib.persistence.validator;

import es.caib.portafib.persistence.AnnexJPA;
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
public class AnnexBeanValidator 
      extends AbstractBeanValidator<AnnexJPA> {


  // EJB's
  protected final es.caib.portafib.model.dao.IAnnexManager __annexManager;

  protected final es.caib.portafib.model.dao.IPeticioDeFirmaManager __peticioDeFirmaManager;


  public final AnnexValidator<AnnexJPA> _validator;


  public AnnexBeanValidator(es.caib.portafib.model.dao.IAnnexManager __annexManager,
     es.caib.portafib.model.dao.IPeticioDeFirmaManager __peticioDeFirmaManager) { 
    this.__annexManager = __annexManager;
    this.__peticioDeFirmaManager = __peticioDeFirmaManager;
    _validator = new AnnexValidator<AnnexJPA>();
  }

  public AnnexBeanValidator(AnnexValidator<AnnexJPA> _validator,
     es.caib.portafib.model.dao.IAnnexManager __annexManager,
     es.caib.portafib.model.dao.IPeticioDeFirmaManager __peticioDeFirmaManager) {
    this.__annexManager = __annexManager;
    this.__peticioDeFirmaManager = __peticioDeFirmaManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(AnnexJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<AnnexJPA> _bvr_ = new BeanValidatorResult<AnnexJPA>();
    _validator.validate(_bvr_, target, isNou, __annexManager, __peticioDeFirmaManager);
    return _bvr_.getErrors();
  }
}
