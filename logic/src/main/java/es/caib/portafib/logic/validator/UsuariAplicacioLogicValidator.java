package es.caib.portafib.logic.validator;

import org.fundaciobit.plugins.userinformation.IUserInformationPlugin;
import org.fundaciobit.plugins.userinformation.UserInfo;
import org.fundaciobit.genapp.common.i18n.I18NArgument;
import org.fundaciobit.genapp.common.i18n.I18NArgumentCode;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NTranslation;
import org.fundaciobit.genapp.common.validation.IValidatorResult;

import es.caib.portafib.jpa.validator.UsuariAplicacioValidator;
import es.caib.portafib.logic.utils.PortaFIBPluginsManager;
import es.caib.portafib.utils.Configuracio;

/**
 * 
 * @author anadal
 *
 */
public class UsuariAplicacioLogicValidator<T> extends UsuariAplicacioValidator<T> {
 

  public void validate(IValidatorResult<T> __vr, T __target__, boolean __isNou__
      ,es.caib.portafib.model.dao.IEntitatManager __entitatManager
      ,es.caib.portafib.model.dao.IIdiomaManager __idiomaManager
      ,es.caib.portafib.model.dao.IUsuariAplicacioManager __usuariAplicacioManager
      , String entitatID) {

    log.debug(" BBBBBBBBBBBBB   Passa per UsuariAplicacioWebLogicValidator");

    super.validate(__vr, __target__, __isNou__, __entitatManager, __idiomaManager, __usuariAplicacioManager);

    if (__isNou__ && __vr.getFieldErrorCount(USUARIAPLICACIOID) == 0) {
      String userApp = (String)__vr.getFieldValue(__target__, USUARIAPLICACIOID);
      if (Configuracio.isCAIB()) {
        // Comprobar que aquest usuari està a SEYCON

        UserInfo userInfo = null;
        try {
          IUserInformationPlugin userInfoPlugin = PortaFIBPluginsManager.getUserInformationPluginInstance();
          userInfo = userInfoPlugin.getUserInfoByUserName(userApp);
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
      } else {
        // Aquest usuari ha de tenir el prefix de l'entitat
        String prefixExpected = entitatID + "_";
        if (userApp.startsWith(prefixExpected)) {
          if ((userApp.trim().length() - prefixExpected.length()) < 3) {
            __vr.rejectValue(USUARIAPLICACIOID, "genapp.validation.minsizerequired",
                new I18NArgument[] { 
                     new I18NArgumentCode(get(USUARIAPLICACIOID)),
                     new I18NArgumentString(String.valueOf(prefixExpected.length() + 3)) 
                   }); 
          }
        } else {
          __vr.rejectValue(USUARIAPLICACIOID, "usuariaplicacio.errorprefix",
              new I18NArgumentString(prefixExpected));
        }
      }      
    }

    if (!Configuracio.isCAIB()) {
      // Contrasenya no pot ser null
      __vr.rejectIfEmptyOrWhitespace(__target__,CONTRASENYA, 
          "genapp.validation.required",
          new I18NArgumentCode(get(CONTRASENYA)));
    }

  }
  
}
