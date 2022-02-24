package es.caib.portafib.logic.validator;

import java.util.List;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NFieldError;
import org.fundaciobit.genapp.common.validation.BeanValidatorResult;


import es.caib.portafib.persistence.UsuariAplicacioJPA;
import es.caib.portafib.persistence.validator.UsuariAplicacioBeanValidator;

/**
 * 
 * @author anadal
 *
 */
public class UsuariAplicacioBeanLogicValidator extends UsuariAplicacioBeanValidator {

  protected final Logger log = Logger.getLogger(getClass());
  
  public UsuariAplicacioBeanLogicValidator(es.caib.portafib.model.dao.ICustodiaInfoManager __custodiaInfoManager,
      es.caib.portafib.model.dao.IEntitatManager __entitatManager,
      es.caib.portafib.model.dao.IIdiomaManager __idiomaManager,
      es.caib.portafib.model.dao.IUsuariAplicacioManager __usuariAplicacioManager) { 
     super(new UsuariAplicacioLogicValidator<UsuariAplicacioJPA>(),
         __custodiaInfoManager,__entitatManager, __idiomaManager, __usuariAplicacioManager);

   }

   @Override
  public List<I18NFieldError> validate(UsuariAplicacioJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<UsuariAplicacioJPA> _bvr_ = new BeanValidatorResult<UsuariAplicacioJPA>();
    
    _validator.validate(_bvr_, target, isNou, __custodiaInfoManager, __entitatManager, __idiomaManager, __usuariAplicacioManager);
    
    return _bvr_.getErrors();
  }
  
}