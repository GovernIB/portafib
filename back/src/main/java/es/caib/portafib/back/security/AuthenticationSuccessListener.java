package es.caib.portafib.back.security;

import es.caib.portafib.back.preparer.BasePreparer;
import es.caib.portafib.persistence.EntitatJPA;
import es.caib.portafib.persistence.RoleUsuariEntitatJPA;
import es.caib.portafib.persistence.UsuariEntitatJPA;
import es.caib.portafib.persistence.UsuariPersonaJPA;
import es.caib.portafib.logic.RevisorDeDestinatariLogicaService;
import es.caib.portafib.logic.UsuariAplicacioLogicaLocal;
import es.caib.portafib.logic.UsuariEntitatLogicaLocal;
import es.caib.portafib.logic.UsuariPersonaLogicaLocal;
import es.caib.portafib.logic.utils.EjbManager;
import es.caib.portafib.logic.utils.PortaFIBPluginsManager;
import es.caib.portafib.logic.utils.PropietatGlobalUtil;
import es.caib.portafib.model.entity.Entitat;
import es.caib.portafib.model.entity.RoleUsuariEntitat;
import es.caib.portafib.utils.Configuracio;
import es.caib.portafib.utils.ConstantsV2;
import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NTranslation;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pluginsib.userinformation.IUserInformationPlugin;
import org.fundaciobit.pluginsib.userinformation.UserInfo;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.ejb.EJB;

/**
 *
 * @author anadal(u80067)
 *
 */
@Component
public class AuthenticationSuccessListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {

    private final Logger log = Logger.getLogger(getClass());

    @Override
    public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {

        SecurityContext sc = SecurityContextHolder.getContext();
        Authentication au = sc.getAuthentication();

        if (au == null) {
            // TODO traduccio
            throw new LoginException("NO PUC ACCEDIR A LA INFORMACIO de AUTENTICACIO");
        }

        User user = (User) au.getPrincipal();
        String name = user.getUsername();

        log.info(" ============ Login Usuari: " + name);

        try {
            UsuariAplicacioLogicaLocal usuariAplicacioEjb = EjbManager.getUsuariAplicacioLogicaEJB();
            if (usuariAplicacioEjb.findByPrimaryKey(name) != null) {
                log.info(" ============ Login Aplicacio: " + name);
                return;
            }
        } catch (I18NException e) {
            throw new LoginException(
                    "No puc accedir al gestor per obtenir informació de l'usuari per " + name + ": " + e.getMessage(),
                    e);
        }

        try {
            LoginInfo loginInfo = LoginInfo.getInstance();

            if (!name.equals(loginInfo.getUsuariPersona().getUsuariPersonaID())) {
                throw new LoginException("Amb aquest navegador ja s'ha autenticat amb un altre usuari."
                        + " Tanqui el navegador completament.");
            }
        } catch (Throwable e) {
            log.info(" XYZ ZZZ ZZZ S'ha produit un error consultant la informació de login actual: " + e.getMessage());
        }

        final boolean isDebug = log.isDebugEnabled();

        // Cercam si té el ROLE_USER o ROLE_ADMIN
        Collection<GrantedAuthority> seyconAuthorities = user.getAuthorities();
        boolean containsRoleUser = false;
        boolean containsRoleAdmin = false;
        boolean containsRoleAny = false;
        for (GrantedAuthority grantedAuthority : seyconAuthorities) {
            String rol = grantedAuthority.getAuthority();
            if (ConstantsV2.ROLE_ANY.equals(rol)) {
                containsRoleAny = true;
            }
            if (ConstantsV2.ROLE_USER.equals(rol)) {
                containsRoleUser = true;
            }
            if (ConstantsV2.ROLE_ADMIN.equals(rol)) {
                containsRoleAdmin = true;
            }
        }

        UsuariPersonaLogicaLocal usuariPersonaEjb;
        try {
            usuariPersonaEjb = EjbManager.getUsuariPersonaLogicaEJB();
        } catch (Throwable e) {
            // TODO traduccio
            throw new LoginException("No puc accedir al gestor d´obtenció de" + " informació de usuari per " + name
                    + ": " + e.getMessage(), e);
        }

        UsuariPersonaJPA usuariPersona = usuariPersonaEjb.findByPrimaryKeyFull(name);
        boolean necesitaConfigurar = false;

        if (usuariPersona == null && (containsRoleUser || containsRoleAdmin)) {
            // Revisar si és un Administrador que entra per primera vegada
            if (isDebug) {
                log.debug("Configuracio.getDefaultEntity() = ]" + PropietatGlobalUtil.getDefaultEntity() + "[");
            }
            {
                try {
                    IUserInformationPlugin plugin = PortaFIBPluginsManager.getUserInformationPluginInstance();
                    UserInfo info = plugin.getUserInfoByUserName(name);
                    if (info != null) {
                        UsuariPersonaJPA persona = new UsuariPersonaJPA();
                        persona.setEmail(info.getEmail() == null ? PropietatGlobalUtil.getAppEmail() : info.getEmail());
                        persona.setIdiomaID(Configuracio.getDefaultLanguage());
                        final String nom, llinatges;
                        {
                            String nomTmp = info.getName() == null ? name : info.getName();

                            String llinatgesTmp = (info.getSurname1() == null ? "" : info.getSurname1())
                                    + (info.getSurname2() == null ? "" : (" " + info.getSurname2()));
                            llinatgesTmp = llinatgesTmp.trim();

                            if (llinatgesTmp.length() == 0) {
                                // Miram si podem xapar el nom
                                int pos = nomTmp.indexOf(' ');
                                if (pos == -1) {
                                    nom = nomTmp;
                                    llinatges = name;
                                } else {
                                    nom = nomTmp.substring(0, pos);
                                    llinatges = nomTmp.substring(pos).trim();
                                }
                            } else {
                                nom = nomTmp;
                                llinatges = llinatgesTmp;
                            }
                        }
                        persona.setNom(nom);
                        persona.setLlinatges(llinatges);
                        persona.setUsuariPersonaID(name);
                        if (info.getAdministrationID() == null) {
                            throw new I18NException("genapp.comodi",
                                    "El sistema d´informació d'usuaris no està ben configurat."
                                            + " No s'ha pogut obtenir el AdministrationID(nif) de l´usuari " + name
                                            + "." + " Contacti amb l´administrador per informar-li d'aquest fet.");

                        }

                        persona.setNif(info.getAdministrationID().toUpperCase());
                        persona.setUsuariIntern(true);

                        UsuariEntitatJPA usuariEntitat = null;
                        Set<String> virtualRoles = null;
                        if (containsRoleUser) {
                            String defaultEntity;

                            if (Configuracio.isCAIB()) {
                                defaultEntity = PropietatGlobalUtil.getEntitatIDForAgentsSQL();
                                virtualRoles = new HashSet<String>();
                                virtualRoles.add(ConstantsV2.ROLE_DEST);
                            } else {
                                defaultEntity = PropietatGlobalUtil.getDefaultEntity();
                                String defRolesStr = PropietatGlobalUtil.getDefaultRolesInCreation();
                                // log.info("defRolesStr = " + defRolesStr);
                                if (defRolesStr != null && defRolesStr.trim().length() != 0) {
                                    virtualRoles = new HashSet<String>(Arrays.asList(defRolesStr.split(",")));
                                    // log.info(" virtualRoles = " + Arrays.toString(virtualRoles.toArray()));
                                }
                            }
                            if (defaultEntity != null && defaultEntity.trim().length() != 0) {
                                usuariEntitat = new UsuariEntitatJPA();
                                usuariEntitat.setActiu(true);
                                usuariEntitat.setCarrec(null);
                                usuariEntitat.setEntitatID(defaultEntity);
                                usuariEntitat.setPoliticaCustodia(ConstantsV2.POLITICA_CUSTODIA_NO_PERMETRE);

                                usuariEntitat.setPredeterminat(true);
                                usuariEntitat.setUsuariPersonaID(persona.getUsuariPersonaID());
                            }

                        }
                        necesitaConfigurar = true;

                        UsuariEntitatLogicaLocal usuariEntitatLogicaEjb;
                        try {
                            usuariEntitatLogicaEjb = EjbManager.getUsuariEntitatLogicaEJB();
                        } catch (Exception e) {
                            // TODO traduccio
                            throw new LoginException("No puc accedir al gestor d´obtenció de"
                                    + " informació d´usuari-entitat per " + name + ": " + e.getMessage(), e);
                        }

                        if (usuariEntitat == null) {
                            usuariPersona = usuariEntitatLogicaEjb.create(persona, virtualRoles);
                        } else {
                            usuariEntitat = usuariEntitatLogicaEjb.create(persona, usuariEntitat, virtualRoles);
                            usuariPersona = usuariEntitat.getUsuariPersona();
                        }

                        if (isDebug) {
                            log.debug("necesitaConfigurarUsuari = " + necesitaConfigurar);
                        }

                    }

                } catch (Throwable e) {
                    usuariPersona = null;
                    necesitaConfigurar = true;
                    String msg;
                    if (e instanceof I18NException) {
                        msg = I18NUtils.getMessage((I18NException) e);
                    } else if (e instanceof I18NValidationException) {
                        msg = I18NUtils.getMessage((I18NValidationException) e);
                    } else {
                        msg = e.getMessage();
                    }
                    msg = "Error llegint informació del plugin de UserInformation: " + msg;
                    log.error(msg, e);
                    throw new LoginException(msg, e);
                }
            }
        }

        Set<UsuariEntitatJPA> usuariEntitats = null;
        if (usuariPersona != null) {
            usuariEntitats = usuariPersona.getUsuariEntitats();
        }

        if (isDebug) {
            log.debug("POST getUsuariEntitats() = " + usuariEntitats);
            if (usuariEntitats != null && log.isDebugEnabled()) {
                log.debug("POST getUsuariEntitats()[SIZE] = " + usuariEntitats.size());
                for (UsuariEntitatJPA usuariEntitatJPA : usuariEntitats) {
                    log.debug("POST getUsuariEntitats()[ITEM] = " + usuariEntitatJPA.getEntitatID() + " - "
                            + usuariEntitatJPA.getUsuariEntitatID());
                }

            }
        }

        if (usuariEntitats == null) {
            usuariEntitats = new HashSet<UsuariEntitatJPA>();
        }

        if (!(containsRoleUser || containsRoleAny) && usuariEntitats.size() != 0) {
            // L'usuari " + name + " està assignat a una o varies
            // entitats però no té el rol PFI_USER ni tothom" ;
            I18NTranslation translation = new I18NTranslation("error.sensepfiuser", name);
            log.error("");
            log.error(I18NUtils.tradueix(translation));
            log.error("Authenntication Info:\n" + au);
            log.error("");

            // Com enviar-ho a la PAGINA WEB
            BasePreparer.loginErrorMessage.put(name, translation);

            usuariEntitats = new HashSet<UsuariEntitatJPA>();
        }

        // Seleccionam l'entitat per defecte i verificam que les entitats disponibles
        // siguin correctes
        Map<String, EntitatJPA> entitats = new HashMap<String, EntitatJPA>();
        Map<String, Set<GrantedAuthority>> rolesPerEntitat = new HashMap<String, Set<GrantedAuthority>>();
        rolesPerEntitat.put(null, new HashSet<GrantedAuthority>(seyconAuthorities));
        Map<String, UsuariEntitatJPA> usuariEntitatPerEntitatID = new HashMap<String, UsuariEntitatJPA>();
        Entitat entitatPredeterminada = null;
        for (UsuariEntitatJPA usuariEntitat : usuariEntitats) {

            EntitatJPA entitat = usuariEntitat.getEntitat();

            if (entitat == null) {
                log.info("Configuracio.getDefaultEntity() = ]" + PropietatGlobalUtil.getDefaultEntity() + "[");
                log.info("ROLES = ]" + Arrays.toString(seyconAuthorities.toArray()) + "[");
                log.warn("Entitat val null");
                continue;
            }

            String entitatID = entitat.getEntitatID();
            if (isDebug) {
                log.debug("--------------- Entitat " + entitatID);
            }
            // Check deshabilitada
            if (!entitat.isActiva()) {
                log.warn("L'entitat " + entitat.getNom() + " esta deshabilitada.");
                continue;
            }
            if (!usuariEntitat.isActiu()) {
                log.warn("L'entitat " + entitat.getNom() + " esta deshabilitatda per l'usuari "
                        + usuariPersona.getUsuariPersonaID());
                continue;
            }
            if (usuariEntitat.getCarrec() != null) {
                if (isDebug) {
                    log.debug("L'usuari-entitat " + usuariEntitat.getUsuariEntitatID() + " és un càrrec. ");
                }
                continue;
            }

            // Per si no n'hi ha cap per defecte
            if (entitatPredeterminada == null) {
                entitatPredeterminada = entitat;
            }
            // Cercam Rols Virtuals ROLE_USER / ROLE_ANY de PortaFIB
            if (containsRoleUser || containsRoleAny) {
                Set<RoleUsuariEntitatJPA> rolesEntitat = usuariEntitat.getRoleUsuariEntitats();
                Set<GrantedAuthority> rolesPortaFIB = new TreeSet<GrantedAuthority>(GRANTEDAUTHORITYCOMPARATOR);
                rolesPortaFIB.addAll(seyconAuthorities);
                boolean usuariAplicacioPerPerticionsIsNull = (entitat.getUsuariAplicacioID() == null);
                for (RoleUsuariEntitat roleUsuariEntitat : rolesEntitat) {
                    String roleName = roleUsuariEntitat.getRoleID();
                    if (usuariAplicacioPerPerticionsIsNull && ConstantsV2.ROLE_SOLI.equals(roleName)) {
                        log.warn("No afegim el role " + roleName + " ja que aquesta entitat no té definit "
                                + " usuariAplicacio per les peticions de firma dels usuaris.");
                    } else if (ConstantsV2.ROLE_ADMIN.equals(roleName)) {
                        // TODO enviar un correu a l'administrador del sistema
                        log.warn("Error de seguretat: L'usuari " + name + " té el role virtual "
                                + ConstantsV2.ROLE_ADMIN + " però aquest rol s'ha d'obtenir dels rols de JBOSS."
                                + " Eliminar aquest rol de la BBDD !!!!!", new Exception());
                    } else {
                        if (isDebug) {
                            log.debug("Afegint role portafib " + roleName);
                        }
                        rolesPortaFIB.add(new SimpleGrantedAuthority(roleName));
                    }
                }

                // El permis de REVISOR actualment esta repartir entre l ataula de ROLES per usuarientitatid
                // i la taula de Revisors de Destinatari.
                if (!rolesPortaFIB.contains(new SimpleGrantedAuthority(ConstantsV2.ROLE_REVI))) {

                    RevisorDeDestinatariLogicaService revisorDeDestinatariEjb;
                    try {
                        revisorDeDestinatariEjb = EjbManager.getRevisorDeDestinatariEJB();

                        if (revisorDeDestinatariEjb.usuariEntitatIdEsRevisor(usuariEntitat.getUsuariEntitatID())) {
                            rolesPortaFIB.add(new SimpleGrantedAuthority(ConstantsV2.ROLE_REVI));
                        }

                    } catch (Exception e) {
                        // TODO traduccio
                        throw new LoginException("No puc accedir al gestor d´obtenció de"
                                + " si un usuarientitat té el role REVISOR: " + e.getMessage(), e);
                    }

                }

                rolesPerEntitat.put(entitatID, rolesPortaFIB);

            } else {
                log.debug("No te el role seycon PFI_USER");
                rolesPerEntitat.put(entitatID, new HashSet<GrantedAuthority>());
            }
            // Entitat per defecte
            if (usuariEntitat.isPredeterminat()) {
                entitatPredeterminada = entitat;
            }

            // Entitats
            entitats.put(entitatID, usuariEntitat.getEntitat());
            // Usuari Entitat
            usuariEntitat.setUsuariPersona(usuariPersona);
            usuariEntitatPerEntitatID.put(entitatID, usuariEntitat);
        }

        if (entitats.size() == 0 && !containsRoleAdmin) {

            if (usuariEntitats.size() == 0) {
                // L'usuari " + name + " no té cap entitat associada. Consulti amb
                // l'Administrador
                I18NTranslation translation = new I18NTranslation("error.senseentitat", name);
                BasePreparer.loginErrorMessage.put(name, translation);
            } else {
                // Les entitats a les que pertany estan desactivades
                // "L'usuari " + name + " no té cap entitat vàlida associada");
                I18NTranslation translation = new I18NTranslation("error.senseentitatvalida", name);
                BasePreparer.loginErrorMessage.put(name, translation);
            }
        }

        String entitatIDActual = null;
        if (entitatPredeterminada != null) {
            entitatIDActual = entitatPredeterminada.getEntitatID();
            if (isDebug) {
                log.debug(">>>>>> Entitat predeterminada " + entitatIDActual);
            }
        }

        log.info("LoginInfo:\n" + "\tuser: " + user + "\n" + "\tusuariPersona: " + usuariPersona + "\n"
                + "\tentitatIDActual: " + entitatIDActual + "\n" + "\tentitats: " + entitats + "\n"
                + "\trolesPerEntitat: " + rolesPerEntitat + "\n" + "\tusuariEntitatPerEntitatID: "
                + usuariEntitatPerEntitatID + "\n" + "\tnecesitaConfigurar: " + necesitaConfigurar + "\n"

        );
        LoginInfo loginInfo = new LoginInfo(user, usuariPersona, entitatIDActual, entitats, rolesPerEntitat,
                usuariEntitatPerEntitatID, necesitaConfigurar);

        // and set the authentication of the current Session context
        SecurityContextHolder.getContext().setAuthentication(loginInfo.generateToken());

        if (isDebug) {
            log.debug(">>>>>> Final del Process d'autenticació.");
        }
        log.debug(" =================================================================");

    }

    public static final Comparator<GrantedAuthority> GRANTEDAUTHORITYCOMPARATOR = new Comparator<GrantedAuthority>() {
        @Override
        public int compare(GrantedAuthority o1, GrantedAuthority o2) {
            return -o1.getAuthority().compareTo(o2.getAuthority());
        }
    };

}
