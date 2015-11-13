package es.caib.portafib.jpa.validator;

import es.caib.portafib.jpa.ModulDeFirmaPerTipusDeDocumentJPA;
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
public class ModulDeFirmaPerTipusDeDocumentBeanValidator 
      extends AbstractBeanValidator<ModulDeFirmaPerTipusDeDocumentJPA> {


  // EJB's
  protected final es.caib.portafib.model.dao.IModulDeFirmaManager __modulDeFirmaManager;

  protected final es.caib.portafib.model.dao.IModulDeFirmaPerTipusDeDocumentManager __modulDeFirmaPerTipusDeDocumentManager;

  protected final es.caib.portafib.model.dao.ITipusDocumentManager __tipusDocumentManager;


  public final ModulDeFirmaPerTipusDeDocumentValidator<ModulDeFirmaPerTipusDeDocumentJPA> _validator;


  public ModulDeFirmaPerTipusDeDocumentBeanValidator(es.caib.portafib.model.dao.IModulDeFirmaManager __modulDeFirmaManager,
     es.caib.portafib.model.dao.IModulDeFirmaPerTipusDeDocumentManager __modulDeFirmaPerTipusDeDocumentManager,
     es.caib.portafib.model.dao.ITipusDocumentManager __tipusDocumentManager) { 
    this.__modulDeFirmaManager = __modulDeFirmaManager;
    this.__modulDeFirmaPerTipusDeDocumentManager = __modulDeFirmaPerTipusDeDocumentManager;
    this.__tipusDocumentManager = __tipusDocumentManager;
    _validator = new ModulDeFirmaPerTipusDeDocumentValidator<ModulDeFirmaPerTipusDeDocumentJPA>();
  }

  public ModulDeFirmaPerTipusDeDocumentBeanValidator(ModulDeFirmaPerTipusDeDocumentValidator<ModulDeFirmaPerTipusDeDocumentJPA> _validator,
     es.caib.portafib.model.dao.IModulDeFirmaManager __modulDeFirmaManager,
     es.caib.portafib.model.dao.IModulDeFirmaPerTipusDeDocumentManager __modulDeFirmaPerTipusDeDocumentManager,
     es.caib.portafib.model.dao.ITipusDocumentManager __tipusDocumentManager) {
    this.__modulDeFirmaManager = __modulDeFirmaManager;
    this.__modulDeFirmaPerTipusDeDocumentManager = __modulDeFirmaPerTipusDeDocumentManager;
    this.__tipusDocumentManager = __tipusDocumentManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(ModulDeFirmaPerTipusDeDocumentJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<ModulDeFirmaPerTipusDeDocumentJPA> _bvr_ = new BeanValidatorResult<ModulDeFirmaPerTipusDeDocumentJPA>();
    _validator.validate(_bvr_, target, isNou, __modulDeFirmaManager, __modulDeFirmaPerTipusDeDocumentManager, __tipusDocumentManager);
    return _bvr_.getErrors();
  }
}
