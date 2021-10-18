package es.caib.portafib.persistence.validator;

import es.caib.portafib.persistence.MetadadaJPA;
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
public class MetadadaBeanValidator 
      extends AbstractBeanValidator<MetadadaJPA> {


  // EJB's
  protected final es.caib.portafib.model.dao.IMetadadaManager __metadadaManager;

  protected final es.caib.portafib.model.dao.IPeticioDeFirmaManager __peticioDeFirmaManager;


  public final MetadadaValidator<MetadadaJPA> _validator;


  public MetadadaBeanValidator(es.caib.portafib.model.dao.IMetadadaManager __metadadaManager,
     es.caib.portafib.model.dao.IPeticioDeFirmaManager __peticioDeFirmaManager) { 
    this.__metadadaManager = __metadadaManager;
    this.__peticioDeFirmaManager = __peticioDeFirmaManager;
    _validator = new MetadadaValidator<MetadadaJPA>();
  }

  public MetadadaBeanValidator(MetadadaValidator<MetadadaJPA> _validator,
     es.caib.portafib.model.dao.IMetadadaManager __metadadaManager,
     es.caib.portafib.model.dao.IPeticioDeFirmaManager __peticioDeFirmaManager) {
    this.__metadadaManager = __metadadaManager;
    this.__peticioDeFirmaManager = __peticioDeFirmaManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(MetadadaJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<MetadadaJPA> _bvr_ = new BeanValidatorResult<MetadadaJPA>();
    _validator.validate(_bvr_, target, isNou, __metadadaManager, __peticioDeFirmaManager);
    return _bvr_.getErrors();
  }
}
