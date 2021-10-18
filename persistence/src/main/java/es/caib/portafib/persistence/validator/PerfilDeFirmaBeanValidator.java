package es.caib.portafib.persistence.validator;

import es.caib.portafib.persistence.PerfilDeFirmaJPA;
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
public class PerfilDeFirmaBeanValidator 
      extends AbstractBeanValidator<PerfilDeFirmaJPA> {


  // EJB's
  protected final es.caib.portafib.model.dao.IPerfilDeFirmaManager __perfilDeFirmaManager;

  protected final es.caib.portafib.model.dao.IUsuariAplicacioConfiguracioManager __usuariAplicacioConfiguracioManager;


  public final PerfilDeFirmaValidator<PerfilDeFirmaJPA> _validator;


  public PerfilDeFirmaBeanValidator(es.caib.portafib.model.dao.IPerfilDeFirmaManager __perfilDeFirmaManager,
     es.caib.portafib.model.dao.IUsuariAplicacioConfiguracioManager __usuariAplicacioConfiguracioManager) { 
    this.__perfilDeFirmaManager = __perfilDeFirmaManager;
    this.__usuariAplicacioConfiguracioManager = __usuariAplicacioConfiguracioManager;
    _validator = new PerfilDeFirmaValidator<PerfilDeFirmaJPA>();
  }

  public PerfilDeFirmaBeanValidator(PerfilDeFirmaValidator<PerfilDeFirmaJPA> _validator,
     es.caib.portafib.model.dao.IPerfilDeFirmaManager __perfilDeFirmaManager,
     es.caib.portafib.model.dao.IUsuariAplicacioConfiguracioManager __usuariAplicacioConfiguracioManager) {
    this.__perfilDeFirmaManager = __perfilDeFirmaManager;
    this.__usuariAplicacioConfiguracioManager = __usuariAplicacioConfiguracioManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(PerfilDeFirmaJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<PerfilDeFirmaJPA> _bvr_ = new BeanValidatorResult<PerfilDeFirmaJPA>();
    _validator.validate(_bvr_, target, isNou, __perfilDeFirmaManager, __usuariAplicacioConfiguracioManager);
    return _bvr_.getErrors();
  }
}
