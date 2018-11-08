package es.caib.portafib.jpa.validator;

import es.caib.portafib.jpa.PeticioDeFirmaJPA;
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
public class PeticioDeFirmaBeanValidator 
      extends AbstractBeanValidator<PeticioDeFirmaJPA> {


  // EJB's
  protected final es.caib.portafib.model.dao.ICustodiaInfoManager __custodiaInfoManager;

  protected final es.caib.portafib.model.dao.IFluxDeFirmesManager __fluxDeFirmesManager;

  protected final es.caib.portafib.model.dao.IIdiomaManager __idiomaManager;

  protected final es.caib.portafib.model.dao.IPeticioDeFirmaManager __peticioDeFirmaManager;

  protected final es.caib.portafib.model.dao.ITipusDocumentManager __tipusDocumentManager;

  protected final es.caib.portafib.model.dao.IUsuariAplicacioManager __usuariAplicacioManager;

  protected final es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager;


  public final PeticioDeFirmaValidator<PeticioDeFirmaJPA> _validator;


  public PeticioDeFirmaBeanValidator(es.caib.portafib.model.dao.ICustodiaInfoManager __custodiaInfoManager,
     es.caib.portafib.model.dao.IFluxDeFirmesManager __fluxDeFirmesManager,
     es.caib.portafib.model.dao.IIdiomaManager __idiomaManager,
     es.caib.portafib.model.dao.IPeticioDeFirmaManager __peticioDeFirmaManager,
     es.caib.portafib.model.dao.ITipusDocumentManager __tipusDocumentManager,
     es.caib.portafib.model.dao.IUsuariAplicacioManager __usuariAplicacioManager,
     es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager) { 
    this.__custodiaInfoManager = __custodiaInfoManager;
    this.__fluxDeFirmesManager = __fluxDeFirmesManager;
    this.__idiomaManager = __idiomaManager;
    this.__peticioDeFirmaManager = __peticioDeFirmaManager;
    this.__tipusDocumentManager = __tipusDocumentManager;
    this.__usuariAplicacioManager = __usuariAplicacioManager;
    this.__usuariEntitatManager = __usuariEntitatManager;
    _validator = new PeticioDeFirmaValidator<PeticioDeFirmaJPA>();
  }

  public PeticioDeFirmaBeanValidator(PeticioDeFirmaValidator<PeticioDeFirmaJPA> _validator,
     es.caib.portafib.model.dao.ICustodiaInfoManager __custodiaInfoManager,
     es.caib.portafib.model.dao.IFluxDeFirmesManager __fluxDeFirmesManager,
     es.caib.portafib.model.dao.IIdiomaManager __idiomaManager,
     es.caib.portafib.model.dao.IPeticioDeFirmaManager __peticioDeFirmaManager,
     es.caib.portafib.model.dao.ITipusDocumentManager __tipusDocumentManager,
     es.caib.portafib.model.dao.IUsuariAplicacioManager __usuariAplicacioManager,
     es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager) {
    this.__custodiaInfoManager = __custodiaInfoManager;
    this.__fluxDeFirmesManager = __fluxDeFirmesManager;
    this.__idiomaManager = __idiomaManager;
    this.__peticioDeFirmaManager = __peticioDeFirmaManager;
    this.__tipusDocumentManager = __tipusDocumentManager;
    this.__usuariAplicacioManager = __usuariAplicacioManager;
    this.__usuariEntitatManager = __usuariEntitatManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(PeticioDeFirmaJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<PeticioDeFirmaJPA> _bvr_ = new BeanValidatorResult<PeticioDeFirmaJPA>();
    _validator.validate(_bvr_, target, isNou, __custodiaInfoManager, __fluxDeFirmesManager, __idiomaManager, __peticioDeFirmaManager, __tipusDocumentManager, __usuariAplicacioManager, __usuariEntitatManager);
    return _bvr_.getErrors();
  }
}
