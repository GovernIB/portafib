package es.caib.portafib.logic.validator;

import java.util.List;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NFieldError;
import org.fundaciobit.genapp.common.validation.AbstractBeanValidator;
import org.fundaciobit.genapp.common.validation.BeanValidatorResult;

import es.caib.portafib.logic.PassarelaDeFirmaLocal;
import es.caib.portafib.logic.passarela.PassarelaSignaturesSet;

/**
 * 
 * @author anadal
 *
 */
public class SignaturesSetBeanValidator extends AbstractBeanValidator<PassarelaSignaturesSet> {
  
  
  protected final SignaturesSetValidator<PassarelaSignaturesSet> _validator;
  
  protected PassarelaDeFirmaLocal passarelaDeFirmaEjb;
  
  protected String entitatID;


  

  public SignaturesSetBeanValidator(SignaturesSetValidator<PassarelaSignaturesSet> _validator,
      PassarelaDeFirmaLocal passarelaDeFirmaEjb, String entitatID) {
    this._validator = _validator;
    this.passarelaDeFirmaEjb = passarelaDeFirmaEjb;
    this.entitatID = entitatID;
  }

  @Override
  public List<I18NFieldError> validate(PassarelaSignaturesSet target, boolean isNou) throws I18NException {
    BeanValidatorResult<PassarelaSignaturesSet> _bvr_ = new BeanValidatorResult<PassarelaSignaturesSet>();
    _validator.validate(_bvr_, target, isNou, this.passarelaDeFirmaEjb, this.entitatID);
    return _bvr_.getErrors();
  }
  
  

}
