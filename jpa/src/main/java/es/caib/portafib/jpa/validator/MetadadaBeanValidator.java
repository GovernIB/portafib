package es.caib.portafib.jpa.validator;

import es.caib.portafib.jpa.MetadadaJPA;
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

  protected final es.caib.portafib.model.dao.ITipusMetadadaManager __tipusMetadadaManager;


  public final MetadadaValidator<MetadadaJPA> _validator;


  public MetadadaBeanValidator(es.caib.portafib.model.dao.IMetadadaManager __metadadaManager,
     es.caib.portafib.model.dao.IPeticioDeFirmaManager __peticioDeFirmaManager,
     es.caib.portafib.model.dao.ITipusMetadadaManager __tipusMetadadaManager) { 
    this.__metadadaManager = __metadadaManager;
    this.__peticioDeFirmaManager = __peticioDeFirmaManager;
    this.__tipusMetadadaManager = __tipusMetadadaManager;
    _validator = new MetadadaValidator<MetadadaJPA>();
  }

  public MetadadaBeanValidator(MetadadaValidator<MetadadaJPA> _validator,
     es.caib.portafib.model.dao.IMetadadaManager __metadadaManager,
     es.caib.portafib.model.dao.IPeticioDeFirmaManager __peticioDeFirmaManager,
     es.caib.portafib.model.dao.ITipusMetadadaManager __tipusMetadadaManager) {
    this.__metadadaManager = __metadadaManager;
    this.__peticioDeFirmaManager = __peticioDeFirmaManager;
    this.__tipusMetadadaManager = __tipusMetadadaManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(MetadadaJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<MetadadaJPA> _bvr_ = new BeanValidatorResult<MetadadaJPA>();
    _validator.validate(_bvr_, target, isNou, __metadadaManager, __peticioDeFirmaManager, __tipusMetadadaManager);
    return _bvr_.getErrors();
  }
}
