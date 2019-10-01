package es.caib.portafib.logic.validator;

import org.fundaciobit.pluginsib.userinformation.IUserInformationPlugin;
import org.fundaciobit.pluginsib.userinformation.UserInfo;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NTranslation;
import org.fundaciobit.genapp.common.validation.IValidatorResult;

import es.caib.portafib.jpa.validator.UsuariAplicacioValidator;
import es.caib.portafib.logic.utils.PortaFIBPluginsManager;

/**
 * Validació d'usuari aplicació. Afegeix a les validacions generades, la comprovació que l'usuari existeix al sistema
 * dinformació d'usuaris i que no és una persona (no té NIF)
 * @author anadal
 * @author areus
 * @see IUserInformationPlugin
 */
public class UsuariAplicacioLogicValidator<T> extends UsuariAplicacioValidator<T> {

  @Override
  public void validate(IValidatorResult<T> __vr, T __target__, boolean __isNou__
      ,es.caib.portafib.model.dao.ICustodiaInfoManager __custodiaInfoManager
      ,es.caib.portafib.model.dao.IEntitatManager __entitatManager
      ,es.caib.portafib.model.dao.IIdiomaManager __idiomaManager
      ,es.caib.portafib.model.dao.IUsuariAplicacioManager __usuariAplicacioManager) {

    log.debug(" BBBBBBBBBBBBB   Passa per UsuariAplicacioWebLogicValidator");

    super.validate(__vr, __target__, __isNou__, __custodiaInfoManager ,__entitatManager, __idiomaManager, __usuariAplicacioManager);

    if (__isNou__ && __vr.getFieldErrorCount(USUARIAPLICACIOID) == 0) {
      String userApp = (String)__vr.getFieldValue(__target__, USUARIAPLICACIOID);

      // Comprobar que aquest usuari està al sistema d'informació d'usuaris i que no és una persona.
      try {
        IUserInformationPlugin userInfoPlugin = PortaFIBPluginsManager.getUserInformationPluginInstance();
        UserInfo userInfo = userInfoPlugin.getUserInfoByUserName(userApp);
        String nif = userInfo.getAdministrationID();
        if (nif != null && nif.trim().length() != 0) {
          //usuariaplicacio.error.esusuaripersona=L´usuari aplicació {0} és un usuari persona amb NIF {1}
          __vr.rejectValue(USUARIAPLICACIOID, "usuariaplicacio.error.esusuaripersona",
              new I18NArgumentString(userApp), new I18NArgumentString(nif));
        }
      } catch(I18NException e) {
        log.error(e.getMessage(), e);
        I18NTranslation i18n = e.getTraduccio();
        __vr.rejectValue(USUARIAPLICACIOID, i18n.getCode(), i18n.getArgs());
      } catch(Exception e) {
        log.error(e.getMessage(), e);
        __vr.rejectValue(USUARIAPLICACIOID, "usuariaplicacio.usuarinoseycon",
            new I18NArgumentString(userApp));
      }
    }
  }
  
}
