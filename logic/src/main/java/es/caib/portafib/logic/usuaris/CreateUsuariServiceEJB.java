package es.caib.portafib.logic.usuaris;

import es.caib.portafib.jpa.UsuariEntitatJPA;
import es.caib.portafib.jpa.UsuariPersonaJPA;
import es.caib.portafib.logic.UsuariEntitatLogicaLocal;
import es.caib.portafib.logic.UsuariPersonaLogicaLocal;
import es.caib.portafib.utils.Configuracio;
import es.caib.portafib.utils.ConstantsV2;
import org.fundaciobit.genapp.common.i18n.I18NCommonUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.pluginsib.userinformation.UserInfo;


import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.Collections;

@Stateless
@Local(CreateUsuariServiceLocal.class)
public class CreateUsuariServiceEJB implements CreateUsuariServiceLocal {

    @EJB
    protected UsuariEntitatLogicaLocal usuariEntitatLogicaEjb;

    @EJB
    protected UsuariPersonaLogicaLocal usuariPersonaLogicaEjb;

    @Override
    public UsuariEntitatJPA getOrCreateByUsername(String username, String entitatId) {
        try {
            UsuariEntitatJPA usuariEntitat = usuariEntitatLogicaEjb.findUsuariEntitatByUsername(entitatId, username);
            if (usuariEntitat != null) {
                return usuariEntitat;
            }

            UsuariPersonaJPA usuariPersona = usuariPersonaLogicaEjb.findByPrimaryKey(username);
            if (usuariPersona == null) {
                UserInfo userInfo = usuariPersonaLogicaEjb.checkUsernameInUserInformationPlugin(username);
                usuariPersona = toPersona(userInfo);
                usuariPersonaLogicaEjb.createFull(usuariPersona);
            }

            usuariEntitat = usuariEntitatLogicaEjb.create(username,
                    toUsuariEntitat(username, entitatId),
                    Collections.<String>emptySet());

            return usuariEntitat;

        } catch (I18NValidationException e) {
            String message = "Error creant usuari [" + username + "]: " +
                    I18NCommonUtils.getMessage(e, Configuracio.getDefaultLocale());
            throw new RuntimeException(message, e);
        } catch (I18NException e) {
            String message = "Error creant usuari [" + username + "]: " +
                    I18NCommonUtils.getMessage(e, Configuracio.getDefaultLocale());
            throw new RuntimeException(message, e);
        }
    }

    @Override
    public UsuariEntitatJPA getOrCreateByAdministrationId(String administrationId, String entitatId) {
        try {
            UsuariEntitatJPA usuariEntitat = usuariEntitatLogicaEjb.findUsuariEntitatInternByNif(entitatId, administrationId);
            if (usuariEntitat != null) {
                return usuariEntitat;
            }

            UsuariPersonaJPA usuariPersona = usuariPersonaLogicaEjb.getUsuariPersonaIDByAdministrationID(administrationId);
            if (usuariPersona == null) {
                UserInfo userInfo = usuariPersonaLogicaEjb.checkAdministrationIDInUserInformationPlugin(administrationId);
                usuariPersona = toPersona(userInfo);
                usuariPersonaLogicaEjb.createFull(usuariPersona);
            }

            String username = usuariPersona.getUsuariPersonaID();
            usuariEntitat = usuariEntitatLogicaEjb.create(username,
                    toUsuariEntitat(username, entitatId),
                    Collections.<String>emptySet());

            return usuariEntitat;

        } catch (I18NValidationException e) {
            String message = "Error creant usuari amb nif [" + administrationId + "]: " +
                    I18NCommonUtils.getMessage(e, Configuracio.getDefaultLocale());
            throw new RuntimeException(message, e);
        } catch (I18NException e) {
            String message = "Error creant usuari amb nif [" + administrationId + "]: " +
                    I18NCommonUtils.getMessage(e, Configuracio.getDefaultLocale());
            throw new RuntimeException(message, e);
        }
    }

    private UsuariPersonaJPA toPersona(UserInfo userInfo) {
        UsuariPersonaJPA usuariPersona = new UsuariPersonaJPA();
        usuariPersona.setUsuariPersonaID(userInfo.getUsername());
        usuariPersona.setNif(userInfo.getAdministrationID().toUpperCase());
        usuariPersona.setEmail(userInfo.getEmail());
        usuariPersona.setIdiomaID(userInfo.getLanguage() == null
                ? Configuracio.getDefaultLanguage()
                : userInfo.getLanguage());

        usuariPersona.setLlinatges(userInfo.getSurname1()
                + (userInfo.getSurname2() == null ? "" : " " + userInfo.getSurname2()));
        usuariPersona.setNom(userInfo.getName());
        usuariPersona.setUsuariIntern(true);
        return usuariPersona;
    }

    private UsuariEntitatJPA toUsuariEntitat(String codusu, String entitatID) {
        UsuariEntitatJPA usuariEntitat = new UsuariEntitatJPA();
        usuariEntitat.setUsuariPersonaID(codusu);
        usuariEntitat.setEntitatID(entitatID);
        usuariEntitat.setActiu(true);
        usuariEntitat.setCarrec(null);
        usuariEntitat.setPoliticaCustodia(ConstantsV2.POLITICA_CUSTODIA_NO_PERMETRE);
        return usuariEntitat;
    }

}
