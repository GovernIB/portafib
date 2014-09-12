package es.caib.portafib.jpa.validator;

import es.caib.portafib.jpa.ColaboracioDelegacioJPA;
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
public class ColaboracioDelegacioBeanValidator 
      extends AbstractBeanValidator<ColaboracioDelegacioJPA> {


  // EJB's
  protected final es.caib.portafib.model.dao.IColaboracioDelegacioManager __colaboracioDelegacioManager;

  protected final es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager;


  public final ColaboracioDelegacioValidator<ColaboracioDelegacioJPA> _validator;


  public ColaboracioDelegacioBeanValidator(es.caib.portafib.model.dao.IColaboracioDelegacioManager __colaboracioDelegacioManager,
     es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager) { 
    this.__colaboracioDelegacioManager = __colaboracioDelegacioManager;
    this.__usuariEntitatManager = __usuariEntitatManager;
    _validator = new ColaboracioDelegacioValidator<ColaboracioDelegacioJPA>();
  }

  public ColaboracioDelegacioBeanValidator(ColaboracioDelegacioValidator<ColaboracioDelegacioJPA> _validator,
     es.caib.portafib.model.dao.IColaboracioDelegacioManager __colaboracioDelegacioManager,
     es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager) {
    this.__colaboracioDelegacioManager = __colaboracioDelegacioManager;
    this.__usuariEntitatManager = __usuariEntitatManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(ColaboracioDelegacioJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<ColaboracioDelegacioJPA> _bvr_ = new BeanValidatorResult<ColaboracioDelegacioJPA>();
    _validator.validate(_bvr_, target, isNou, __colaboracioDelegacioManager, __usuariEntitatManager);
    return _bvr_.getErrors();
  }
}
