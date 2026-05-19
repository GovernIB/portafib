package es.caib.portafib.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import es.caib.portafib.ejb.UsuariAplicacioService;
import es.caib.portafib.persistence.UsuariEntitatJPA;
import es.caib.portafib.persistence.UsuariPersonaJPA;
import es.caib.portafib.logic.utils.EmailUtil;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.logic.utils.PropietatGlobalUtil;
import es.caib.portafib.model.entity.Firma;
import es.caib.portafib.model.entity.UsuariEntitat;
import es.caib.portafib.model.fields.BlocDeFirmesQueryPath;
import es.caib.portafib.model.fields.EstatDeFirmaFields;
import es.caib.portafib.model.fields.FirmaFields;
import es.caib.portafib.model.fields.FirmaQueryPath;
import es.caib.portafib.model.fields.RebreAvisFields;
import es.caib.portafib.commons.utils.Configuracio;
import es.caib.portafib.commons.utils.Constants;
import es.caib.portafib.utils.ConstantsV2;

import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.IntegerField;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pluginsib.userinformation.UserInfo;

/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "AgentsCAIBEJB")
@RunAs(Constants.PFI_USER)
public class AgentsCAIBEJB implements AgentsCAIBLocal {

    protected final Logger log = Logger.getLogger(getClass());

    public static final Locale locale = new Locale("ca");

    @EJB(mappedName = UsuariEntitatNonSecureLogicaLocal.JNDI_NAME)
    private UsuariEntitatNonSecureLogicaLocal usuariEntitatNonSecureLogicaEjb;

    @EJB(mappedName = UsuariPersonaNonSecureLogicaLocal.JNDI_NAME)
    protected UsuariPersonaNonSecureLogicaLocal usuariPersonaLogicaNonSecuredEjb;

    @EJB(mappedName = UsuariAplicacioService.JNDI_NAME)
    protected UsuariAplicacioService usuariAplicacioEjb;

    @EJB(mappedName = FirmaLogicaLocal.JNDI_NAME)
    protected FirmaLogicaLocal firmaLogicaEjb;

    @EJB(mappedName = EstatDeFirmaLogicaLocal.JNDI_NAME)
    private EstatDeFirmaLogicaLocal estatDeFirmaLogicaEjb;
    
    @EJB(mappedName = RebreAvisLogicaLocal.JNDI_NAME)
    protected RebreAvisLogicaLocal rebreAvisLogicaEjb;

    @Override
    public UsuariEntitat processarCarrecCAIB(String tipus, String codusu, String nomrol, String valordomini,
            String agentsql, String nom) {

        final String entitatID = PropietatGlobalUtil.getEntitatIDForAgentsSQL();

        try {
            log.info(" + tipus =" + tipus);
            log.info(" + codusu = " + codusu);
            log.info(" + nomrol = " + nomrol);
            log.info(" + valordomini = " + valordomini);
            log.info(" + agentsql = " + agentsql);
            log.info(" + nom = " + nom);

            if (tipus == null) {
                log.error("Tipus és NULL !!!!");
                return null;
            }

            final String usuariEntitatID = entitatID + "_carrec_" + nomrol + "-" + valordomini + "-" + agentsql;

            // Cercar usuari-entitat
            UsuariEntitat ue = usuariEntitatNonSecureLogicaEjb.findByPrimaryKey(usuariEntitatID);

            // ------------------------------
            // I N S E R T
            // ------------------------------
            if ("insert".equals(tipus.trim())) {

                if (ue != null) {
                    // llavors ho substituim per un update
                    tipus = "update";
                } else {
                    // No existeix i ho cream

                    ue = new UsuariEntitatJPA();

                    ue.setActiu(false);
                    ue.setCarrec(((nom == null) || (nom.trim().length() == 0)) ? "PENDENT posar nom" : nom);
                    ue.setEntitatID(entitatID);
                    ue.setPoliticaCustodia(ConstantsV2.POLITICA_CUSTODIA_NO_PERMETRE);

                    ue.setUsuariEntitatID(usuariEntitatID);

                    ue.setUsuariPersonaID(codusu);

                    usuariEntitatNonSecureLogicaEjb.create(ue);

                    enviarCorreuAdmistradorsOpcional(
                            getSubjectUpdadeUsers(), "S'ha creat el càrrec " + usuariEntitatID + " dins l'entitat "
                                    + entitatID + ". Accedeixi a la Gestió de càrrecs per revisar el nom i activar-ho",
                            entitatID);

                    return ue;
                }

            }

            // ------------------------------------------------------
            // U P D A T E & D I S A B L E & D E L E T E
            // ------------------------------------------------------
            // UPDATE: canvi de la persona que representa aquest càrrec
            // DISABLE: desactivar el càrrec
            final boolean isUpdate = "update".equals(tipus.trim());
            final boolean isDisable = "disable".equals(tipus.trim());
            final boolean isDelete = "delete".equals(tipus.trim());
            if (isUpdate || isDisable || isDelete) {

                if (ue == null) {

                    // Enviar a correu a ADEN
                    String msg = "S'esta intentant executar una operació '" + tipus + "' sobre carrec amb ID="
                            + usuariEntitatID + "(Entitat " + entitatID + ") però aquest no existeix.";
                    enviarCorreuAdmistradorsOpcional(getSubjectUpdadeUsers(), msg, entitatID);
                    log.error(msg, new Exception(msg));
                    return null;
                }

                // (1) No ha d'apareixer en firmes de peticions de firmes actives o
                // pausades on la firma està en un bloc que s'esta processant
                String firmesEnBlocsActius = getFirmesEnBlocsActius(usuariEntitatID);

                if (firmesEnBlocsActius.isEmpty()) {
                    if (isUpdate) {
                        // ACTUALITZAR USERNAME
                        ue.setUsuariPersonaID(codusu);
                    } else if (isDisable) {
                        // DESACTIVAR
                        // NOTA: Es possible desactivar un carrec d'un bloc verge ja que
                        // l'execució de la darrera firma del bloc anterior al bloc verge
                        // fallarà
                        // indicant que en el següent bloc hi ha un càrrec desactivat.
                        ue.setActiu(false);
                    } else {
                        ue.setActiu(false);
                        ue.setCarrec("PENDENT D'ESBORRAR - " + ue.getCarrec());
                    }
                    usuariEntitatNonSecureLogicaEjb.update(ue);
                    if (isUpdate) {
                        if (!ue.isActiu()) {
                            String msg = "S'ha rebut una petició per actualitzar el càrrec amb ID " + usuariEntitatID
                                    + " per assignar-li l'usuari " + ue.getUsuariPersonaID()
                                    + ". S'ha actualitzat l'usuari però voliem informar que aquest càrrec està desactivat.";
                            enviarCorreuAdmistradorsOpcional(getSubjectUpdadeUsers(), msg, entitatID);
                        }
                    } else {
                        if (isDelete) {
                            String msg = "S'ha rebut una petició per esborrar el càrrec amb ID " + usuariEntitatID
                                    + ". S'ha pogut desactivar però queda pendent esborrar-ho de PortaFIB. ";
                            enviarCorreuAdmistradorsOpcional(getSubjectUpdadeUsers(), msg, entitatID);
                        }
                    }
                    return ue;
                } else {
                    // Existeix una firma en marxa dins un bloc actiu que correspon a un
                    // càrrec que fa que no poguem canviar l'usuari o desactivar o esborrar

                    String msg;
                    if (isUpdate) {
                        msg = "No es pot actualitzar el carrec " + usuariEntitatID + " del usuari "
                                + ue.getUsuariPersonaID() + " pel nou usuari " + codusu
                                + " ja que existeix una o varies firmes actives del càrrec anterior " + "(Firma/es "
                                + firmesEnBlocsActius + ").\n" + "Manualment ha d'assignar l'usuari " + codusu
                                + " al càrrec" + usuariEntitatID + ".";
                    } else {
                        String accio = (isDisable ? "desactivar" : "eliminar");
                        msg = "\nNo es pot " + accio + " el carrec " + usuariEntitatID
                                + " ja que existeix una o varies firmes actives pendents de signar " + "(Firma/es  "
                                + firmesEnBlocsActius + ")\n" + "Manualment ha d'aplicar " + accio + " al càrrec"
                                + usuariEntitatID + ".";
                    }

                    enviarCorreuAdmistradorsOpcional(getSubjectUpdadeUsers(), msg, entitatID);
                    log.error(msg, new Exception(msg));

                    return null;
                }

            }

            // Enviar correu a admin
            String msg = "No existeix el tipus ]" + tipus + "[ pel seu processament dins processarCarrecCAIB(" + codusu
                    + ", " + nomrol + ", " + valordomini + ", " + agentsql + ", " + nom + ")";
            enviarCorreuAdmistradorsRequerit(getSubjectUpdadeUsers(), msg, entitatID);
            log.error(msg, new Exception(msg));

        } catch (Throwable th) {
            processException(th, "CarrecCAIB", entitatID);
        }

        return null;
    }

    protected String getFirmesEnBlocsActius(final String usuariEntitatID) throws I18NException {
        String firmesEnBlocsActius;
        {
            final BlocDeFirmesQueryPath BLOC = new FirmaQueryPath().BLOCDEFIRMES();
            final IntegerField TIPUSESTATPETICIODEFIRMAID = BLOC.FLUXDEFIRMES().PETICIODEFIRMA()
                    .TIPUSESTATPETICIODEFIRMAID();

            List<Firma> firmes = firmaLogicaEjb.select(Where.AND(FirmaFields.DESTINATARIID.equal(usuariEntitatID),
                    FirmaFields.TIPUSESTATDEFIRMAFINALID.isNull(), BLOC.DATAFINALITZACIO().isNull(),
                    TIPUSESTATPETICIODEFIRMAID.in(new Integer[] { ConstantsV2.TIPUSESTATPETICIODEFIRMA_ENPROCES,
                            ConstantsV2.TIPUSESTATPETICIODEFIRMA_PAUSAT })));

            List<Firma> firmesEnBlocActiu = new ArrayList<Firma>();
            // final FirmaQueryPath firmaQueryPath = new
            // EstatDeFirmaQueryPath().FIRMA();
            for (Firma firma : firmes) {

                Long count = estatDeFirmaLogicaEjb.count(EstatDeFirmaFields.FIRMAID.equal(firma.getFirmaID()));
                if (count != 0) {
                    firmesEnBlocActiu.add(firma);
                }
            }

            StringBuffer ids = new StringBuffer();
            for (Firma fir : firmesEnBlocActiu) {
                if (ids.length() != 0) {
                    ids.append(", ");
                }
                ids.append(fir.getFirmaID() + "(Bloc: " + fir.getBlocDeFirmaID() + ")");
            }

            firmesEnBlocsActius = ids.toString();

        }
        return firmesEnBlocsActius;
    }

    protected String getSubjectUpdadeUsers() {
        return "PORTAFIB: Actualització de Càrrecs/Usuaris requereix la seva actuació";
    }
    
    

    @Override
    public List<String> enviarCorreuAdmistradorsOpcional(String subject, String message, String entitatID)
            throws I18NException {
        final boolean requerit = false;
        return enviarCorreuAdmistradors(subject, message, entitatID, requerit);
    }
    @Override
    public List<String> enviarCorreuAdmistradorsRequerit(String subject, String message, String entitatID)
            throws I18NException {
        final boolean requerit = true;
        return enviarCorreuAdmistradors(subject, message, entitatID, requerit);
    }
        
        
    protected List<String> enviarCorreuAdmistradors(String subject, String message, String entitatID, boolean requerit)
                throws I18NException {

        Map<String, String> emailsByUsuariEntitatID = usuariEntitatNonSecureLogicaEjb
                .getEmailsOfAdministradorsEntitatByEntitat(entitatID);
        
        Collection<String> recipientsList;
        if (requerit) {
            recipientsList = emailsByUsuariEntitatID.values();
        } else {
            // Enviar correu a administradors de l'entitat només si tenen el RebreAvis de INCIDENCIES_ADMINISTRADOR activa
            Set<String> usuarisEntitatIDs = emailsByUsuariEntitatID.keySet();
            List<String> usuarisAmbNotificacio = rebreAvisLogicaEjb.executeQuery(RebreAvisFields.USUARIENTITATID, 
                    Where.AND(RebreAvisFields.USUARIENTITATID.in(usuarisEntitatIDs),
                    RebreAvisFields.TIPUSNOTIFICACIOID.equal(ConstantsV2.NOTIFICACIOAVIS_INCIDENCIES_ADMINISTRADOR)));
            List<String> emailsList = new ArrayList<String>();
            for (String ueID : usuarisEntitatIDs) {
                if (usuarisAmbNotificacio.contains(ueID)) {
                    // Si l'usuari té el tipus de notificació activat, l'afegim a la llista
                    emailsList.add(emailsByUsuariEntitatID.get(ueID));
                }
            }
            recipientsList = emailsList;
        }

        // Netejam duplicats
        recipientsList = new HashSet<String>(recipientsList);

        if (log.isDebugEnabled()) {
            log.info("Enviarà correu a " + recipientsList + " amb el missatge: " + message);
        }

        final boolean isHtml = false;
        // Com que si alguna de les adreces falla, llavors tot el bloc falla, 
        // s'enviaram els correus un a un
        for (String recipient : recipientsList) {
            try {
                EmailUtil.postMail(subject, message, isHtml, PropietatGlobalUtil.getAppEmail(), recipient);
            } catch (Exception e) {
                log.error("Error enviant missatge a administrador d'entitat " + recipient + ":" + e.getMessage(), e);
            }
        }

        return new ArrayList<String>(recipientsList);
    }

    @Override
    public UsuariEntitat processarUsuariCAIB(String tipus, String codusu, String agentsql, String password) {

        codusu = codusu.trim();

        final String entitatID = PropietatGlobalUtil.getEntitatIDForAgentsSQL();
        try {
            // Cercar usuari-entitat
            UsuariEntitatJPA ue = usuariEntitatNonSecureLogicaEjb.findUsuariEntitatByUsername(entitatID, codusu);

            // ------------------------------
            // I N S E R T
            // ------------------------------
            if ("insert".equals(tipus.trim())) {

                if (ue != null) {
                    // Usuari-Entitat ja existeix: Només l'he, d'activar si no està actiu
                    if (!ue.isActiu()) {
                        final boolean usuariEntitatActiu = PropietatGlobalUtil
                                .isActiveUsuariEntitatAfterAgentSeyconCreation();

                        if (usuariEntitatActiu) {
                            // Activar-ho automàticament
                            try {
                                usuariEntitatNonSecureLogicaEjb.activarUsuariEntitat(ue.getUsuariEntitatID());
                                ue.setActiu(true);
                                return ue;
                            } catch (I18NException i18ne) {
                                String msg = "Error desconegut activant usuari entitat " + ue.getUsuariEntitatID()
                                        + ": " + I18NLogicUtils.getMessage(i18ne, locale);
                                enviarCorreuAdmistradorsOpcional(getSubjectUpdadeUsers(), msg, entitatID);
                                log.error(msg, i18ne);
                            }
                        }

                        enviarCorreuAdmistradorsOpcional(getSubjectUpdadeUsers(),
                                "S'ha rebut una petició via AgentSQL(" + agentsql + ") per donar"
                                        + " d'alta (insert) l'usuari " + codusu + " dins l'entitat " + entitatID
                                        + ", però aquest usuari-entitat " + ue.getUsuariEntitatID()
                                        + " no està actiu. Accedeixi a la Gestió d'Usuaris-Entitat"
                                        + " per revisar les dades i activar-ho",
                                entitatID);
                    }
                    return ue;
                } else {

                    // Usuari-Entitat no existeix i l'hem de crear
                    Set<String> virtualRoles = null;
                    if (Configuracio.isCAIB()) {
                        virtualRoles = new HashSet<String>();
                        virtualRoles.add(Constants.ROLE_DEST);
                    } else {
                        String defRolesStr = PropietatGlobalUtil.getDefaultRolesInCreation();
                        //log.info("defRolesStr = " + defRolesStr);
                        if (defRolesStr != null && defRolesStr.trim().length() != 0) {
                            virtualRoles = new HashSet<String>(Arrays.asList(defRolesStr.split(",")));
                            //log.info(" virtualRoles = " + Arrays.toString(virtualRoles.toArray()));
                        }
                    }

                    // Mirar si existeix la Persona
                    UsuariPersonaJPA usuariPersona = usuariPersonaLogicaNonSecuredEjb.findByPrimaryKey(codusu);

                    final boolean usuariEntitatActiu = PropietatGlobalUtil
                            .isActiveUsuariEntitatAfterAgentSeyconCreation();

                    if (usuariPersona == null) {
                        // La persona no existeix i l'hen de crear

                        UserInfo pfui;
                        try {
                            pfui = usuariPersonaLogicaNonSecuredEjb.checkUsernameInUserInformationPlugin(codusu);
                        } catch (I18NException i18ne) {

                            String msg = "\nS'ha rebut una petició via AgentSQL(" + agentsql + ") per donar"
                                    + " d'alta (insert) l'usuari " + codusu + ", però hi ha hagut un error"
                                    + " consultant la informació al UserInformationPlugin: "
                                    + I18NLogicUtils.getMessage(i18ne, locale);
                            enviarCorreuAdmistradorsRequerit(getSubjectUpdadeUsers(), msg, entitatID);
                            log.error(msg, i18ne);
                            return null;
                        }

                        // Omplim l'usuariPersona amb les dades del PortaFIBUserInfo
                        usuariPersona = new UsuariPersonaJPA();
                        usuariPersona.setNif(pfui.getAdministrationID().toUpperCase());
                        usuariPersona.setUsuariPersonaID(pfui.getUsername());
                        usuariPersona.setEmail(pfui.getEmail());
                        usuariPersona.setIdiomaID(
                                pfui.getLanguage() == null ? Configuracio.getDefaultLanguage() : pfui.getLanguage());
                        usuariPersona.setLlinatges(
                                pfui.getSurname1() + ((pfui.getSurname2() == null) ? "" : (" " + pfui.getSurname2())));
                        usuariPersona.setNom(pfui.getName());
                        usuariPersona.setUsuariIntern(true);

                        ue = getUsuariEntitatJPAInstance(codusu, entitatID, usuariEntitatActiu);

                        ue = usuariEntitatNonSecureLogicaEjb.create(usuariPersona, ue, virtualRoles);
                    } else {
                        // La persona està creada, només hem de crear l'usuari entitat
                        ue = getUsuariEntitatJPAInstance(codusu, entitatID, usuariEntitatActiu);
                        ue = usuariEntitatNonSecureLogicaEjb.create(codusu, ue, virtualRoles);
                    }

                    String msgBase = "S'ha creat l'usuari-entitat " + ue.getUsuariEntitatID()
                            + " amb la següent informació:" + "\n    * NIF:" + usuariPersona.getNif()
                            + "\n    * Username:" + usuariPersona.getUsuariPersonaID() + "\n    * Email:"
                            + usuariPersona.getEmail() + "\n    * Idioma:" + usuariPersona.getIdiomaID()
                            + "\n    * Llinatges: " + usuariPersona.getLlinatges() + "\n    * Nom: "
                            + usuariPersona.getNom();

                    if (usuariEntitatActiu) {
                        // XYZ ZZZ Fer propietat per enviar sempre missatge.
                        enviarCorreuAdmistradorsOpcional(getSubjectUpdadeUsers(), msgBase
                                + "\n L´usuari-entitat JA està actiu, però si troba que alguna dada no està correcte,"
                                + " llavors accedeixi a la Gestió d'Usuaris-Entitat per modificar la informació errònia.",
                                entitatID);

                    } else {

                        enviarCorreuAdmistradorsOpcional(getSubjectUpdadeUsers(), msgBase
                                + "\n L´usuari-entitat NO està actiu, per això seria necessari accedir a la Gestió d'Usuaris-Entitat"
                                + " per revisar les dades i activar-ho.", entitatID);
                    }

                    return ue;
                }

            }

            if ("delete".equals(tipus.trim())) {
                // El que farem serà desactivar l'usuari a no ser que tengui peticions
                // de firma pendents de firmar en un bloc de firma que s'esta processant
                if (ue == null) {
                    String msg = "\nS'esta intentant desactivar una persona " + codusu + " a través d'un AgentSQL("
                            + agentsql + "), però aquesta"
                            + " persona no existeix com a usuari-entitat dins de l'entitat " + entitatID;
                    enviarCorreuAdmistradorsOpcional(getSubjectUpdadeUsers(), msg, entitatID);
                    log.error(msg, new Exception(msg));
                    return null;
                } else {
                    final String usuariEntitatID = ue.getUsuariEntitatID();
                    String firmesEnBlocsActius = getFirmesEnBlocsActius(usuariEntitatID);

                    if (firmesEnBlocsActius.isEmpty()) {
                        usuariEntitatNonSecureLogicaEjb.desactivarUsuariEntitat(usuariEntitatID);
                        return ue;
                    } else {
                        String msg = "\nNo es pot desactivar l'usuari-entitat " + usuariEntitatID
                                + " ja que existeix una o varies firmes actives pendents de signar" + " (Firma/es  "
                                + firmesEnBlocsActius + ")\n"
                                + "Manualment ha d'aplicar la desactivació a l'Usuari-Entitat " + usuariEntitatID + ".";
                        enviarCorreuAdmistradorsOpcional(getSubjectUpdadeUsers(), msg, entitatID);
                        log.error(msg, new Exception(msg));
                        return null;
                    }
                }
            }

            // Enviar correu a admin
            String msg = "No existeix el tipus ]" + tipus + "[ pel seu processament dins processarUsuariCAIB(" + codusu
                    + ", " + agentsql + ")";
            enviarCorreuAdmistradorsRequerit(getSubjectUpdadeUsers(), msg, entitatID);
            log.error(msg, new Exception(msg));

        } catch (Throwable th) {
            processException(th, "UsuariCAIB", entitatID);
        }

        return null;
    }

    protected void processException(Throwable th, String type, String entitatID) {

        String msg;
        if (th instanceof I18NValidationException) {
            msg = I18NLogicUtils.getMessage((I18NValidationException) th, locale);
        } else if (th instanceof I18NException) {
            msg = I18NLogicUtils.getMessage((I18NException) th, locale);
        } else {
            msg = th.getMessage();
        }

        log.error("Error desconegut processant " + type + ": " + msg, th);

        // Enviar correu a admin
        try {
            enviarCorreuAdmistradorsRequerit("ERROR processant la modificació d'un usuari/carrec des de PortaFIB", msg,
                    entitatID);
        } catch (Throwable th2) {
            log.error(th.getMessage(), th2);
        }

    }

    protected UsuariEntitatJPA getUsuariEntitatJPAInstance(String codusu, final String entitatID, boolean actiu) {
        UsuariEntitatJPA ue = new UsuariEntitatJPA();
        ue.setActiu(actiu);
        ue.setCarrec(null);
        ue.setEntitatID(entitatID);

        //ue.setPotCustodiar(false);
        ue.setPoliticaCustodia(ConstantsV2.POLITICA_CUSTODIA_NO_PERMETRE);

        // ue.setUsuariEntitatID(usuariEntitatID); Es definexi dins el mètode create
        ue.setUsuariPersonaID(codusu);
        return ue;
    }

}
