package es.caib.portafib.logic.validator;

import java.util.List;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NFieldError;
import org.fundaciobit.genapp.common.validation.BeanValidatorResult;


import es.caib.portafib.jpa.UsuariAplicacioJPA;
import es.caib.portafib.jpa.validator.UsuariAplicacioBeanValidator;

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
  
  public List<I18NFieldError> validate(UsuariAplicacioJPA target, boolean isNou, String entitatID) throws I18NException {
    BeanValidatorResult<UsuariAplicacioJPA> _bvr_ = new BeanValidatorResult<UsuariAplicacioJPA>();
    
    ((UsuariAplicacioLogicValidator<UsuariAplicacioJPA>)_validator).validate(_bvr_, target, isNou, __custodiaInfoManager, __entitatManager, __idiomaManager, __usuariAplicacioManager, entitatID);
    
    return _bvr_.getErrors();
  }
  
}
