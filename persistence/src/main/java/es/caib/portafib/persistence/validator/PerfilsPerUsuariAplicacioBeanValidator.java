package es.caib.portafib.persistence.validator;

import es.caib.portafib.persistence.PerfilsPerUsuariAplicacioJPA;
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
public class PerfilsPerUsuariAplicacioBeanValidator 
      extends AbstractBeanValidator<PerfilsPerUsuariAplicacioJPA> {


  // EJB's
  protected final es.caib.portafib.model.dao.IPerfilDeFirmaManager __perfilDeFirmaManager;

  protected final es.caib.portafib.model.dao.IPerfilsPerUsuariAplicacioManager __perfilsPerUsuariAplicacioManager;

  protected final es.caib.portafib.model.dao.IUsuariAplicacioManager __usuariAplicacioManager;


  public final PerfilsPerUsuariAplicacioValidator<PerfilsPerUsuariAplicacioJPA> _validator;


  public PerfilsPerUsuariAplicacioBeanValidator(es.caib.portafib.model.dao.IPerfilDeFirmaManager __perfilDeFirmaManager,
     es.caib.portafib.model.dao.IPerfilsPerUsuariAplicacioManager __perfilsPerUsuariAplicacioManager,
     es.caib.portafib.model.dao.IUsuariAplicacioManager __usuariAplicacioManager) { 
    this.__perfilDeFirmaManager = __perfilDeFirmaManager;
    this.__perfilsPerUsuariAplicacioManager = __perfilsPerUsuariAplicacioManager;
    this.__usuariAplicacioManager = __usuariAplicacioManager;
    _validator = new PerfilsPerUsuariAplicacioValidator<PerfilsPerUsuariAplicacioJPA>();
  }

  public PerfilsPerUsuariAplicacioBeanValidator(PerfilsPerUsuariAplicacioValidator<PerfilsPerUsuariAplicacioJPA> _validator,
     es.caib.portafib.model.dao.IPerfilDeFirmaManager __perfilDeFirmaManager,
     es.caib.portafib.model.dao.IPerfilsPerUsuariAplicacioManager __perfilsPerUsuariAplicacioManager,
     es.caib.portafib.model.dao.IUsuariAplicacioManager __usuariAplicacioManager) {
    this.__perfilDeFirmaManager = __perfilDeFirmaManager;
    this.__perfilsPerUsuariAplicacioManager = __perfilsPerUsuariAplicacioManager;
    this.__usuariAplicacioManager = __usuariAplicacioManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(PerfilsPerUsuariAplicacioJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<PerfilsPerUsuariAplicacioJPA> _bvr_ = new BeanValidatorResult<PerfilsPerUsuariAplicacioJPA>();
    _validator.validate(_bvr_, target, isNou, __perfilDeFirmaManager, __perfilsPerUsuariAplicacioManager, __usuariAplicacioManager);
    return _bvr_.getErrors();
  }
}
