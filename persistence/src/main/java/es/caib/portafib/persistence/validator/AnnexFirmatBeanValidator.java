package es.caib.portafib.persistence.validator;

import es.caib.portafib.persistence.AnnexFirmatJPA;
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
public class AnnexFirmatBeanValidator 
      extends AbstractBeanValidator<AnnexFirmatJPA> {


  // EJB's
  protected final es.caib.portafib.model.dao.IAnnexManager __annexManager;

  protected final es.caib.portafib.model.dao.IAnnexFirmatManager __annexFirmatManager;

  protected final es.caib.portafib.model.dao.IFirmaManager __firmaManager;


  public final AnnexFirmatValidator<AnnexFirmatJPA> _validator;


  public AnnexFirmatBeanValidator(es.caib.portafib.model.dao.IAnnexManager __annexManager,
     es.caib.portafib.model.dao.IAnnexFirmatManager __annexFirmatManager,
     es.caib.portafib.model.dao.IFirmaManager __firmaManager) { 
    this.__annexManager = __annexManager;
    this.__annexFirmatManager = __annexFirmatManager;
    this.__firmaManager = __firmaManager;
    _validator = new AnnexFirmatValidator<AnnexFirmatJPA>();
  }

  public AnnexFirmatBeanValidator(AnnexFirmatValidator<AnnexFirmatJPA> _validator,
     es.caib.portafib.model.dao.IAnnexManager __annexManager,
     es.caib.portafib.model.dao.IAnnexFirmatManager __annexFirmatManager,
     es.caib.portafib.model.dao.IFirmaManager __firmaManager) {
    this.__annexManager = __annexManager;
    this.__annexFirmatManager = __annexFirmatManager;
    this.__firmaManager = __firmaManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(AnnexFirmatJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<AnnexFirmatJPA> _bvr_ = new BeanValidatorResult<AnnexFirmatJPA>();
    _validator.validate(_bvr_, target, isNou, __annexManager, __annexFirmatManager, __firmaManager);
    return _bvr_.getErrors();
  }
}
