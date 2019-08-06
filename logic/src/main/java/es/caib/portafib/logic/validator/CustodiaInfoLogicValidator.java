package es.caib.portafib.logic.validator;

import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NTranslation;
import org.fundaciobit.genapp.common.validation.IValidatorResult;

import es.caib.portafib.jpa.validator.CustodiaInfoValidator;
import es.caib.portafib.logic.utils.PdfUtils;
import es.caib.portafib.utils.ConstantsV2;

/**
 * 
 * @author anadal
 *
 */
public class CustodiaInfoLogicValidator<T> extends CustodiaInfoValidator<T> {
  
  /** Constructor */
  @Override
  public void validate(IValidatorResult<T> __vr, T __target__, boolean __isNou__
      ,es.caib.portafib.model.dao.ICodiBarresManager __codiBarresManager
      ,es.caib.portafib.model.dao.ICustodiaInfoManager __custodiaInfoManager
      ,es.caib.portafib.model.dao.IEntitatManager __entitatManager
      ,es.caib.portafib.model.dao.IPluginManager __pluginManager
      ,es.caib.portafib.model.dao.IUsuariAplicacioManager __usuariAplicacioManager
      ,es.caib.portafib.model.dao.IUsuariEntitatManager __usuariEntitatManager) {
    
    super.validate(__vr, __target__, __isNou__, __codiBarresManager
        , __custodiaInfoManager
        , __entitatManager
        ,__pluginManager
        , __usuariAplicacioManager
        , __usuariEntitatManager);
    
    if (__vr.getFieldErrorCount(PAGINES) == 0) {
      String pagines = (String) __vr.getFieldValue(__target__, PAGINES);
      if (pagines.trim().length() != 0) {
        final int lastPage = 100;
        final int posicioTaulaDeFirmes = ConstantsV2.TAULADEFIRMES_SENSETAULA;
        try {
          PdfUtils.processarPagines(pagines, posicioTaulaDeFirmes, lastPage);
        } catch(I18NException i18ne) {
          I18NTranslation traduccio = i18ne.getTraduccio(); 
          __vr.rejectValue(PAGINES, traduccio.getCode(), traduccio.getArgs());
        } catch(Exception e) {
          __vr.rejectValue(PAGINES, "custodiainfo.pagines.errorformat", new I18NArgumentString(pagines));        
        }
      }
    }
    
  }

}
