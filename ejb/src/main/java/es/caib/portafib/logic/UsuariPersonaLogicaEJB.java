package es.caib.portafib.logic;

import es.caib.portafib.ejb.UsuariEntitatService;
import es.caib.portafib.ejb.UsuariPersonaEJB;
import es.caib.portafib.persistence.UsuariEntitatJPA;
import es.caib.portafib.persistence.UsuariPersonaJPA;
import es.caib.portafib.persistence.validator.UsuariPersonaBeanValidator;
import es.caib.portafib.logic.utils.PortaFIBPluginsManager;
import es.caib.portafib.logic.validator.UsuariPersonaLogicValidator;
import es.caib.portafib.model.entity.UsuariPersona;
import es.caib.portafib.model.fields.UsuariEntitatFields;
import es.caib.portafib.model.fields.UsuariPersonaFields;
import es.caib.portafib.commons.utils.Configuracio;
import es.caib.portafib.utils.ConstantsV2;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import org.fundaciobit.pluginsib.userinformation.IUserInformationPlugin;
import org.fundaciobit.pluginsib.userinformation.UserInfo;
import org.hibernate.Hibernate;
import org.fundaciobit.genapp.common.i18n.I18NArgumentCode;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NFieldError;
import org.fundaciobit.genapp.common.i18n.I18NTranslation;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Where;

/**
 *
 * @author anadal
 *
 */
@Stateless(name = "UsuariPersonaLogicaEJB")
public class UsuariPersonaLogicaEJB extends UsuariPersonaEJB implements UsuariPersonaLogicaLocal {

    @Resource
    private SessionContext context;

    @EJB(mappedName = es.caib.portafib.ejb.IdiomaService.JNDI_NAME)
    protected es.caib.portafib.ejb.IdiomaService idiomaEjb;

    @EJB(mappedName = UsuariEntitatService.JNDI_NAME)
    protected es.caib.portafib.ejb.UsuariEntitatService usuariEntitatEjb;

    @Override
    @RolesAllowed({ "PFI_ADMIN", "PFI_USER", "tothom" })
    public UsuariPersona update(UsuariPersona instance) throws I18NException {
        return super.update(instance);
    }

    @Override
    @RolesAllowed({ "PFI_ADMIN", "PFI_USER", "tothom" })
    public UsuariPersonaJPA findByPrimaryKey(String _ID_) {
        return super.findByPrimaryKey(_ID_);
    }

    @Override
    @PermitAll
    public UsuariPersonaJPA findByPrimaryKeyFull(String _usuariPersonaID_) {
        UsuariPersonaJPA upJPA = findByPrimaryKey(_usuariPersonaID_);
        if (upJPA != null) {
            Hibernate.initialize(upJPA.getUsuariEntitats());
            Set<UsuariEntitatJPA> all = upJPA.getUsuariEntitats();
            if (all != null) {
                for (UsuariEntitatJPA usuariEntitatJPA : all) {
                    Hibernate.initialize(usuariEntitatJPA.getEntitat());
                    Hibernate.initialize(usuariEntitatJPA.getRoleUsuariEntitats());
                    if (usuariEntitatJPA.getEntitat().getUsuariAplicacioID() != null) {
                        Hibernate.initialize(usuariEntitatJPA.getEntitat().getUsuariAplicacio());
                    }

                }
            }
        }
        return upJPA;
    }

    @Override
    public Set<Long> deleteFull(String usuariPersonaID) throws I18NException {

        Set<Long> fitxers = new HashSet<Long>();

        // Check 1
        if (usuariPersonaID == null) {
            return fitxers;
        }

        // Check 2
        UsuariPersonaJPA up = findByPrimaryKey(usuariPersonaID);
        if (up == null) {
            return fitxers;
        }

        // Check 3 Si no te usuarisEntitat associats, es pot esborrar.    
        Where w = UsuariEntitatFields.USUARIPERSONAID.equal(usuariPersonaID);
        long count = usuariEntitatEjb.count(w);
        if (count != 0) {
            throw new I18NException("usuaripersona.teUsuariEntitat", usuariPersonaID);
        }

        // Esborrar
        if (up.getRubricaID() != null) {
            fitxers.add(up.getRubricaID());
        }
        super.delete(up);

        return fitxers;
    }

    @Override
    public Set<String> getRolesOfLoggedUser() throws I18NException {

        String[] allRoles = { ConstantsV2.PFI_USER, ConstantsV2.PFI_ADMIN };
        Set<String> roles = new HashSet<String>();

        for (String rol : allRoles) {
            if (context.isCallerInRole(rol)) {
                roles.add(rol);
            }
        }
        return roles;
    }

    @Override
    public UsuariPersonaJPA createFull(UsuariPersonaJPA usuariPersonaJPA)
            throws I18NException, I18NValidationException {

        if (usuariPersonaJPA == null) {
            return null;
        }

        // 1.- Validation Basica
        UsuariPersonaLogicValidator<UsuariPersonaJPA> validador = new UsuariPersonaLogicValidator<UsuariPersonaJPA>();

        UsuariPersonaBeanValidator upbv = new UsuariPersonaBeanValidator(validador, idiomaEjb, this);
        final boolean isNou = true;
        List<I18NFieldError> list = upbv.validate(usuariPersonaJPA, isNou);
        if (!list.isEmpty()) {
            throw new I18NValidationException(list);
        }

        // 2.- Comparar usuaripersona amb usuari del plugin de info (login)  
        // Llançarà errors si el nif no és correcte
        UserInfo userInfo = checkUsernameInUserInformationPlugin(usuariPersonaJPA.getUsuariPersonaID());

        // Comparar username
        if (!usuariPersonaJPA.getNif().equals(userInfo.getAdministrationID())) {
            list.add(new I18NFieldError(NIF, new I18NTranslation("usuaripersona.iddiferentdeplugin",
                    usuariPersonaJPA.getUsuariPersonaID(), userInfo.getUsername())));
            throw new I18NValidationException(list);
        }

        usuariPersonaJPA.setUsuariIntern(true);
        usuariPersonaJPA.setUsuariEntitats(null);
        usuariPersonaJPA.setIdioma(null);

        return (UsuariPersonaJPA) create(usuariPersonaJPA);
    }

    /* Necessari pq des del component de fluxes es usuaris puguin donar d'alta usuaris externs. */
    @Override
    @PermitAll
    public UsuariPersona create(UsuariPersona instance) throws I18NException {
        return super.create(instance);
    }

    /**
     * Obtenim la informació de l'usuari del sistema d'autenticació.
     */
    @Override
    public UserInfo checkAdministrationIDInUserInformationPlugin(String nif) throws I18NException {
        // Obtenim la informació de l'usuari del sistema d'autenticació.
        IUserInformationPlugin userInfoPlugin = PortaFIBPluginsManager.getUserInformationPluginInstance();

        UserInfo pfui;
        try {
            pfui = userInfoPlugin.getUserInfoByAdministrationID(nif);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            pfui = null;
        }

        final String codeParam = "nif";

        checkUserInformationPlugin(nif, pfui, codeParam);

        return pfui;
    }

    protected void checkUserInformationPlugin(String nifOrUsername, UserInfo pfui, final String codeParam)
            throws I18NException {
        // No hi ha informació de l'usuari al sistema d'autenticació.
        if (pfui == null) {
            throw new I18NException("usuaripersona.senseinformacio", new I18NArgumentCode(codeParam),
                    new I18NArgumentString(nifOrUsername));
        }
        // El plugin de login no ha tornat la informació correcta.
        if (pfui.getAdministrationID() == null || pfui.getUsername() == null) {
            throw new I18NException("usuaripersona.infoincorrecta", new I18NArgumentCode(codeParam),
                    new I18NArgumentString(nifOrUsername));
        }

        if (pfui.getLanguage() == null) {
            pfui.setLanguage(Configuracio.getDefaultLanguage());
        }

        if (pfui.getSurname1() == null) {
            String nom = pfui.getName();
            if (nom != null) {
                int index = nom.indexOf(' ');
                if (index != -1) {
                    pfui.setName(nom.substring(0, index));
                    pfui.setSurname1(nom.substring(index + 1));
                }
            }
        }
    }

    /**
     * Obtenim la informació de l'usuari del sistema d'autenticació.
     */
    @Override
    public UserInfo checkUsernameInUserInformationPlugin(String username) throws I18NException {
        // Obtenim la informació de l'usuari del sistema d'autenticació.
        IUserInformationPlugin userInfoPlugin = PortaFIBPluginsManager.getUserInformationPluginInstance();
        UserInfo pfui;
        try {
            pfui = userInfoPlugin.getUserInfoByUserName(username);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            pfui = null;
        }

        final String codeParam = "username";

        checkUserInformationPlugin(username, pfui, codeParam);

        return pfui;
    }

    @Override
    public UsuariPersonaJPA getUsuariPersonaIDByUsernameOrAdministrationID(String usernameOrAdministrationID)
            throws I18NException {
        if (usernameOrAdministrationID == null || usernameOrAdministrationID.trim().length() == 0) {
            return null;
        }
        // TODO SELECTONE
        Where w = Where.OR(
                Where.AND(UsuariPersonaFields.NIF.equal(usernameOrAdministrationID.toUpperCase()),
                        UsuariPersonaFields.USUARIINTERN.equal(true)),
                UsuariPersonaFields.USUARIPERSONAID.equal(usernameOrAdministrationID.trim()));
        List<UsuariPersona> usuariPersonaList = this.select(w);
        if (usuariPersonaList.isEmpty()) {
            return null;
        } else {
            return (UsuariPersonaJPA) usuariPersonaList.get(0);
        }
    }

    @Override
    public UsuariPersonaJPA getUsuariPersonaIDByAdministrationID(String administrationID) throws I18NException {
        if (administrationID == null) {
            return null;
        }
        // TODO SELECTONE
        Where w = Where.AND(UsuariPersonaFields.NIF.equal(administrationID.toUpperCase()),
                UsuariPersonaFields.USUARIINTERN.equal(true));
        List<UsuariPersona> usuariPersonaList = this.select(w);
        if (usuariPersonaList.isEmpty()) {
            return null;
        } else {
            return (UsuariPersonaJPA) usuariPersonaList.get(0);
        }
    }
}
