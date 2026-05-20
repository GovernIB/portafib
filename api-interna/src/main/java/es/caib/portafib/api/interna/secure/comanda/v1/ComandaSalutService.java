package es.caib.portafib.api.interna.secure.comanda.v1;

import java.net.URL;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.fundaciobit.genapp.common.i18n.I18NCommonUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pluginsib.utils.rest.RestException;
import org.fundaciobit.pluginsib.utils.rest.RestUtils;
import org.jboss.logging.Logger;

import es.caib.comanda.model.server.monitoring.AppInfo;
import es.caib.comanda.model.server.monitoring.ContextInfo;
import es.caib.comanda.model.server.monitoring.EstatSalut;
import es.caib.comanda.model.server.monitoring.EstatSalutEnum;
import es.caib.comanda.model.server.monitoring.IntegracioInfo;
import es.caib.comanda.model.server.monitoring.IntegracioPeticions;
import es.caib.comanda.model.server.monitoring.IntegracioSalut;
import es.caib.comanda.model.server.monitoring.Manual;
import es.caib.comanda.model.server.monitoring.MissatgeSalut;
import es.caib.comanda.model.server.monitoring.SalutInfo;
import es.caib.comanda.model.server.monitoring.SalutNivell;
import es.caib.comanda.model.server.monitoring.SubsistemaInfo;
import es.caib.comanda.model.server.monitoring.SubsistemaSalut;
import es.caib.comanda.ms.salut.helper.IntegracioApp;
import es.caib.comanda.ms.salut.helper.MonitorHelper;
import es.caib.comanda.ms.salut.helper.SalutHelper;
import es.caib.comanda.ms.salut.helper.SalutHelper.BuildInfo;
import es.caib.portafib.commons.utils.Configuracio;
import es.caib.portafib.commons.utils.Constants;
import es.caib.portafib.commons.utils.Version;
import es.caib.portafib.logic.CorreuAgrupatLogicaLocal;
import es.caib.portafib.logic.EntitatLogicaLocal;
import es.caib.portafib.logic.NotificacioWSLogicaLocal;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.logic.utils.PropietatGlobalUtil;
import es.caib.portafib.model.fields.CorreuAgrupatFields;
import es.caib.portafib.model.fields.EstadisticaFields;
import es.caib.portafib.model.fields.NotificacioWSFields;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;
import es.caib.portafib.utils.ConstantsV2;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

/**
 * 
 * @author anadal
 * 27 ene 2026 14:13:10
 */
@Path("/secure/")
@SecurityScheme(type = SecuritySchemeType.HTTP, name = ComandaSalutService.SECURITY_NAME, scheme = "basic")
public class ComandaSalutService extends RestUtils implements es.caib.comanda.api.server.monitoring.ComandaAppSalutApi {

    protected Logger log = Logger.getLogger(ComandaSalutService.class);

    protected static final String SECURITY_NAME = "BasicAuth";

    @EJB(mappedName = es.caib.portafib.ejb.PeticioDeFirmaService.JNDI_NAME)
    protected es.caib.portafib.ejb.PeticioDeFirmaService peticioDeFirmaEjb;

    @EJB(mappedName = CorreuAgrupatLogicaLocal.JNDI_NAME)
    protected CorreuAgrupatLogicaLocal correuAgrupatLogicaEjb;

    @EJB(mappedName = NotificacioWSLogicaLocal.JNDI_NAME)
    protected NotificacioWSLogicaLocal notificacioLogicaEjb;

    @EJB(mappedName = EntitatLogicaLocal.JNDI_NAME)
    protected EntitatLogicaLocal entitatLogicaEjb;

    @EJB(mappedName = es.caib.portafib.ejb.EstadisticaService.JNDI_NAME)
    protected es.caib.portafib.ejb.EstadisticaService estadisticaEjb;

    /**
     * ACH ("Archium"),
    AFI ("Afirma"),
    ARX ("Arxiu"),
    CAR ("Carpeta"),
    CSV ("ConCSV"),
    CDO ("Conversió de documents"),
    CIE ("Cie"),
    CUS ("Custòdia"),
    DIB ("DigitalIB"),
    DIR ("Dir3Caib"),
    DIS ("Distribucio"),
    EML ("E-mail"),
    EMS ("Emiserv"),
    EVI ("EvidenciesIB"),
    GDC ("Gestor documental"),
    HEL ("Helium"),
    IPA ("Ripea"),
    ITD ("InterDoc"),
    LGI ("LoginIB"),
    NOT ("Notib"),
    NTF ("Notifica"),
    PAE ("PaymentIB"),
    PBL ("Pinbal"),
    PFI ("Portafirmes"),
    REG ("Registre"),
    RSC ("Rolsac"),
    RS2 ("Rolsac2"),
    SIG ("Signatura"),
    SIS ("Sistra"),
    SI2 ("Sistra2"),
    TIN ("TinyCaib"),
    TRA ("TranslatorIB"),
    USR ("Usuaris"),
    VIF ("ViaFirma"),
    VFI ("Validació firma");
     */

    // Upgrade
    public static final String IntegracioApp_UPG = "UPG";

    protected static Map<String, String> INTEGRACIONS_PORTAFIB = Map.of(
            // EVI ("EvidenciesIB")
            IntegracioApp.EVI.getCodi(), "EvidenciesIB",
            // CDO ("Conversió de documents"),
            IntegracioApp.CDO.getCodi(), "Conversió de documents",
            // EML ("E-mail"),
            IntegracioApp.EML.getCodi(), "E-mail",
            // SIG ("Signatura"),
            IntegracioApp.SIG.getCodi(), "Signatura",
            // VFI ("Validació firma");    
            IntegracioApp.VFI.getCodi(), "Validació firma",
            //VIF ("ViaFirma"),
            IntegracioApp.VIF.getCodi(), "ViaFirma",
            //  USR ("Usuaris"), "Usuaris"
            IntegracioApp.USR.getCodi(), "Usuaris",
            // UPG ("Upgrade firma"),
            IntegracioApp_UPG, "Upgrade firma");

    //public static final String SUBSISTEMA_API_FIRMA_ASYNC = "PFI_API_FIRMA_ASYNC";

    protected static final String[][] SUBSISTEMES_PORTAFIB = {

            //  { SUBSISTEMA_API_FIRMA_ASYNC, "API de Firma Asyncrona" } 

    };

    protected enum FinalProcess {
        OK, ERROR
    }

    // IntegracioApp.SIG, "Signatura",
    // IntegracioApp.VFI, "Validació firma",  
    // IntegracioApp_UPG, "Upgrade firma"
    protected static final Map<String, Map<FinalProcess, List<Integer>>> ESTADISTIQUES_BY_INTEGRACIOAPP = Map.of(

            IntegracioApp.SIG.getCodi(),
            Map.of(FinalProcess.OK,
                    List.of(ConstantsV2.ESTADISTICA_TIPUS_APIFIRMASIMPLE_SERVIDOR_OK,
                            ConstantsV2.ESTADISTICA_TIPUS_APISWAGGER_SIGNONSERVERV1_OK),
                    FinalProcess.ERROR,
                    List.of(ConstantsV2.ESTADISTICA_TIPUS_APISWAGGER_SIGNONSERVERV1_ERROR,
                            ConstantsV2.ESTADISTICA_TIPUS_APIFIRMASIMPLE_SERVIDOR_ERROR)),

            IntegracioApp_UPG,
            Map.of(FinalProcess.OK,
                    List.of(ConstantsV2.ESTADISTICA_TIPUS_APISWAGGER_UPGRADEV1_OK,
                            ConstantsV2.ESTADISTICA_TIPUS_APIFIRMASIMPLE_UPGRADE_OK),
                    FinalProcess.ERROR,
                    List.of(ConstantsV2.ESTADISTICA_TIPUS_APIFIRMASIMPLE_UPGRADE_ERROR,
                            ConstantsV2.ESTADISTICA_TIPUS_APISWAGGER_UPGRADEV1_ERROR)),

            IntegracioApp.VFI.getCodi(),
            Map.of(FinalProcess.OK,
                    List.of(ConstantsV2.ESTADISTICA_TIPUS_APISWAGGER_VALIDATE_VALID,
                            ConstantsV2.ESTADISTICA_TIPUS_PORTAFIB_VALIDATE_VALID,
                            ConstantsV2.ESTADISTICA_TIPUS_PORTAFIB_VALIDATE_INVALID,
                            ConstantsV2.ESTADISTICA_TIPUS_APISWAGGER_VALIDATE_INVALID),
                    FinalProcess.ERROR, List.of(ConstantsV2.ESTADISTICA_TIPUS_PORTAFIB_VALIDATE_ERROR,
                            ConstantsV2.ESTADISTICA_TIPUS_APISWAGGER_VALIDATE_ERROR))

    );

    /**
     * Obtenir informació de l&#39;aplicació
     *
     * Retorna dades bàsiques de l&#39;aplicació (codi, nom, versió, data de build, etc.) i contextos exposats.
     *
     */
    @GET
    @Path("/salut/v1/info")
    @Produces({ "application/json" })
    @ApiOperation(value = "Obtenir informació de l'aplicació", tags = { "COMANDA → APP / Salut" })
    @ApiResponses(value = { @ApiResponse(code = 200, message = "successful operation", response = AppInfo.class) })
    @SecurityRequirement(name = SECURITY_NAME)
    @RolesAllowed({ Constants.PFI_WS })
    @Override
    public @Valid
    AppInfo salutInfo() {

        AppInfo a = new AppInfo();

        Version version = new Version();
        {

            BuildInfo infoTmp = SalutHelper.getBuildInfo();

            a.setJdkVersion(version.getJdkVersion());
            a.setRevisio(version.getScmRevision());

            // NO funciona 
            //a.revisio(infoTmp.getCommitId());            
            //a.jdkVersion(infoTmp.getBuildJDK());

            a.setData(infoTmp.getBuildDate());

        }

        String urlBase = PropietatGlobalUtil.getAppUrl();

        a.codi("PFI");
        a.nom("PortaFIB");

        {

            List<ContextInfo> contexts = new ArrayList<>();
            {
                ContextInfo back = new ContextInfo();

                back.setApi(null);
                back.setCodi("PFI_BACK");

                Manual manual = new Manual();
                manual.setNom("Manual_de_Usuari_de_PortaFIB");
                manual.setPath(
                        "https://github.com/GovernIB/portafib/raw/refs/heads/portafib-3.0/doc/Manual_de_Usuari_de_PortaFIB.odt");
                back.setManuals(List.of(manual));

                back.setNom("PortaFIB Backoffice/Frontoffice");
                back.setPath(urlBase + "/portafiback");

                contexts.add(back);
            }

            {
                ContextInfo apiinterna = new ContextInfo();
                apiinterna.setApi(urlBase + "/portafibapi/interna");
                apiinterna.setCodi("PFI_API_INTERNA");
                apiinterna.setNom("PortaFIB API Interna");
                apiinterna.setPath(urlBase + "/portafibapi/interna");

                Manual manual = new Manual();
                manual.setNom("Manual_de_Migració_de_APIsIB_a_Api_Interna");
                manual.setPath(
                        "https://github.com/GovernIB/portafib/raw/refs/heads/portafib-3.0/doc/Manual_de_Migraci%C3%B3_de_APIsIB_a_Api_Interna.odt");
                apiinterna.setManuals(List.of(manual));

                contexts.add(apiinterna);
            }

            {
                ContextInfo restsimple = new ContextInfo();
                restsimple.setApi(null);
                restsimple.setCodi("PFI_API_SIMPLE_REST");

                final String[][] manualsPaths = { { "Manual_de_RESTServices_de_PortaFIB",
                        "https://github.com/GovernIB/portafib/raw/refs/heads/portafib-3.0/doc/Manual_de_RESTServices_de_PortaFIB.odt" },
                        { "Manual_Integracio_API_Firma_Async_Simple_v2_0",
                                "https://github.com/GovernIB/portafib/raw/refs/heads/portafib-3.0/doc/Manual_Integracio_API_Firma_Async_Simple_v2_0.odt" },
                        { "Manual_Integracio_API_Firma_Simple_v1_0",
                                "https://github.com/GovernIB/portafib/raw/refs/heads/portafib-3.0/doc/Manual_Integracio_API_Firma_Simple_v1_0.odt" },
                        { "Manual_Integracio_API_Plantilla_Flux_Simple_v1_0",
                                "https://github.com/GovernIB/portafib/raw/refs/heads/portafib-3.0/doc/Manual_Integracio_API_Plantilla_Flux_Simple_v1_0.odt" },
                        { "Model_de_classes_API_de_firma",
                                "https://github.com/GovernIB/portafib/raw/refs/heads/portafib-3.0/doc/Model_de_classes_API_de_firma.odt" }

                };

                for (String[] manualPath : manualsPaths) {
                    Manual manual = new Manual();
                    manual.setNom(manualPath[0]);
                    manual.setPath(manualPath[1]);
                    restsimple.addManualsItem(manual);
                }

                restsimple.setNom("PortaFIB API FIRMA SIMPLE REST");
                restsimple.setPath(urlBase + "/portafib/common/rest");

                contexts.add(restsimple);
            }

            // Afegir contexts
            a.setContexts(contexts);

        }

        // Integracions 
        {
            List<IntegracioInfo> list = new ArrayList<>();
            for (Map.Entry<String, String> entry : INTEGRACIONS_PORTAFIB.entrySet()) {
                String codi = entry.getKey();
                IntegracioInfo i1 = new IntegracioInfo();
                i1.setCodi(codi);
                i1.setNom(entry.getValue());
                list.add(i1);
            }
            a.setIntegracions(list);
        }

        {
            List<SubsistemaInfo> subsistemes = new java.util.ArrayList<>();
            for (String[] subsistema : SUBSISTEMES_PORTAFIB) {
                SubsistemaInfo ss = new SubsistemaInfo();
                ss.setCodi(subsistema[0]);
                ss.setNom(subsistema[1]);
                subsistemes.add(ss);
            }
            a.setSubsistemes(subsistemes);
        }

        a.versio(version.getVersion());

        a.setVersioJboss(getJBossVersion());

        return a;

    }

    /**
     * Obtenir informació de l&#39;estat de salut de l&#39;aplicació
     *
     * Retorna l&#39;estat de salut funcional i integracions, amb metadades de versió.
     *
     */
    @GET
    @Path("/salut/v1")
    @Produces({ "application/json" })
    @ApiOperation(value = "Obtenir informació de l'estat de salut de l'aplicació", tags = { "COMANDA → APP / Salut" })
    @ApiResponses(value = { @ApiResponse(code = 200, message = "successful operation", response = SalutInfo.class) })
    //    @RolesAllowed({ Constants.PFI_WS })
    //    @SecurityRequirement(name = SECURITY_NAME)
    @Override
    public @Valid
    SalutInfo salut(@QueryParam("dataPeriode") @ApiParam(
            defaultValue = "Data mínima de la que es demana informació per període",
            example = "2025-12-31T23:59:59Z")
    java.time.OffsetDateTime dataPeriode,
            @QueryParam("dataTotal") @ApiParam(
                    defaultValue = "Data mínima de la que demana informació per totals",
                    example = "2025-01-01T00:00:00Z")
            java.time.OffsetDateTime dataTotal) {
        SalutInfo sInfo = new SalutInfo();
        sInfo.setCodi("PFI");
        sInfo.setData(getDateTime());

        {
            EstatSalut estatBaseDeDades = new EstatSalut();

            long start = System.currentTimeMillis();
            try {
                final Where where = null;
                final Integer firstResult = 1;
                final Integer maxResults = 1;
                entitatLogicaEjb.select(where, firstResult, maxResults);
                estatBaseDeDades.setEstat(EstatSalutEnum.UP);
            } catch (Exception e) {
                estatBaseDeDades.setEstat(EstatSalutEnum.ERROR);
                e.printStackTrace();
            }

            long end = System.currentTimeMillis();

            estatBaseDeDades.setLatencia((int) (end - start));

            sInfo.setEstatBaseDeDades(estatBaseDeDades);
        }

        {
            EstatSalut estatGlobal = new EstatSalut();

            long start = System.currentTimeMillis();
            try {
                String url = PropietatGlobalUtil.getAppUrl();

                // Fer una petició a l'endpoint de info per comprovar que respon correctament
                // emprant URL
                URL urlObj = new URL(url);
                urlObj.openStream().close();

                estatGlobal.setEstat(EstatSalutEnum.UP);
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();

                estatGlobal.setEstat(EstatSalutEnum.ERROR);
            }
            long end = System.currentTimeMillis();

            estatGlobal.setLatencia((int) (end - start));
            sInfo.setEstatGlobal(estatGlobal);

            sInfo.setInformacioSistema(MonitorHelper.getInfoSistema());
        }

        {
            List<IntegracioSalut> integracions = new java.util.ArrayList<>();

            
            for (String integracioCodi : INTEGRACIONS_PORTAFIB.keySet()) {

                

                IntegracioSalut integracio = new IntegracioSalut();
                integracio.setCodi(integracioCodi); 
                integracio.setEstat(EstatSalutEnum.UNKNOWN);
                // TODO calcular latència
                integracio.setLatencia(null);

                // IntegracioApp.SIG, "Signatura",
                // IntegracioApp.VFI, "Validació firma",                
                if (integracioCodi.equals(IntegracioApp.SIG.getCodi())
                        || integracioCodi.equals(IntegracioApp.VFI.getCodi())
                        || integracioCodi.equals(IntegracioApp_UPG)) {
                    // Ho farem a continuació
                    
                    Map<FinalProcess, List<Integer>> estadistiquesByFinalProcess = ESTADISTIQUES_BY_INTEGRACIOAPP.get(integracioCodi);

                    Timestamp avui = new Timestamp(System.currentTimeMillis());

                    Calendar cal = Calendar.getInstance();

                    Timestamp fa6dies;
                    if (dataPeriode != null) {
                        fa6dies = new Timestamp(dataPeriode.toInstant().toEpochMilli());
                    } else {

                        cal.add(Calendar.DAY_OF_YEAR, -6);

                        fa6dies = new Timestamp(cal.getTimeInMillis());
                        cal.add(Calendar.DAY_OF_YEAR, +6);
                    }

                    Timestamp faunmes;

                    if (dataTotal != null) {
                        faunmes = new Timestamp(dataTotal.toInstant().toEpochMilli());
                    } else {
                        cal.add(Calendar.MONTH, -1);

                        faunmes = new Timestamp(cal.getTimeInMillis());
                        cal.add(Calendar.MONTH, +1);
                    }

                    try {

                        // Cercar peticions d'aquesta integració 
                        IntegracioPeticions peticions = new IntegracioPeticions();
                        peticions.setEndpoint(null);
                        peticions.setPeticionsErrorUltimPeriode(
                                calculPeticions(estadistiquesByFinalProcess.get(FinalProcess.OK), fa6dies, avui));
                        peticions.setPeticionsOkUltimPeriode(
                                calculPeticions(estadistiquesByFinalProcess.get(FinalProcess.ERROR), fa6dies, avui));
                        peticions.setPeticionsPerEntorn(null); // TODO calcular map
                        peticions.setTempsMigUltimPeriode(-1);
                        peticions.setTotalError(
                                calculPeticions(estadistiquesByFinalProcess.get(FinalProcess.OK), faunmes, avui));
                        peticions.setTotalOk(
                                calculPeticions(estadistiquesByFinalProcess.get(FinalProcess.ERROR), faunmes, avui));
                        peticions.setTotalTempsMig(-1);
                        integracio.setPeticions(peticions);
                    } catch (I18NException e) {

                        String msg = "Error calculant l'estat de salut de la integració " + integracioCodi + ": "
                                + I18NLogicUtils.getMessage(e, new Locale(Configuracio.getDefaultLanguage()));

                        log.error(msg, e);
                        throw new RestException(msg, e);
                    }
                }

                integracions.add(integracio);

            }

            sInfo.setIntegracions(integracions);
        }

        {

            List<MissatgeSalut> missatges = new ArrayList<MissatgeSalut>();

            Timestamp faDosDies = new Timestamp(System.currentTimeMillis() - 2L * 24 * 3600 * 1000);

            // Peticions caducades
            try {
                Long count = peticioDeFirmaEjb.count(Where.AND(
                        PeticioDeFirmaFields.DATACADUCITAT.lessThan(new Timestamp(System.currentTimeMillis())),
                        PeticioDeFirmaFields.TIPUSESTATPETICIODEFIRMAID
                                .equal(ConstantsV2.TIPUSESTATPETICIODEFIRMA_ENPROCES)));

                if (count != null && count > 0) {
                    MissatgeSalut ms = new MissatgeSalut();
                    ms.setNivell(SalutNivell.WARN);
                    ms.setData(getDateTime());
                    ms.setMissatge("Hi ha " + count + " peticions de firma caducades.");
                    missatges.add(ms);
                }

            } catch (I18NException e) {

                String msg = "Error consultant les peticions de firma caducades: "
                        + I18NCommonUtils.getMessage(e, new Locale(Configuracio.getDefaultLanguage()));

                log.error(msg, e);

                MissatgeSalut ms = new MissatgeSalut();
                ms.setNivell(SalutNivell.ERROR);
                ms.setData(getDateTime());
                ms.setMissatge(msg);
                missatges.add(ms);
            }

            // Calcular CallBacks pendents 
            try {
                Long count = notificacioLogicaEjb.count(Where.AND(NotificacioWSFields.DATACREACIO.lessThan(faDosDies),
                        NotificacioWSFields.BLOQUEJADA.equal(false)));

                if (count != null && count > 0) {
                    MissatgeSalut ms = new MissatgeSalut();
                    ms.setNivell(SalutNivell.ERROR);
                    ms.setData(getDateTime());
                    ms.setMissatge("Hi ha " + count + " notificacions ws (Callback) pendents de més de 2 dies");
                    missatges.add(ms);
                }

            } catch (I18NException e) {

                String msg = "Error consultant les notificacions ws (Callback) pendents: "
                        + I18NCommonUtils.getMessage(e, new Locale(Configuracio.getDefaultLanguage()));

                log.error(msg, e);

                MissatgeSalut ms = new MissatgeSalut();
                ms.setNivell(SalutNivell.ERROR);
                ms.setData(getDateTime());
                ms.setMissatge(msg);
                missatges.add(ms);
            }

            // Missatges agrupats pendents des de fa més de 2 dies
            try {
                Long count = correuAgrupatLogicaEjb.count(CorreuAgrupatFields.DATACREACIO.lessThan(faDosDies));
                if (count != null && count > 0) {
                    MissatgeSalut ms = new MissatgeSalut();
                    ms.setNivell(SalutNivell.WARN);
                    ms.setData(getDateTime());
                    ms.setMissatge("Hi ha " + count + " missatges agrupats pendents de més de 2 dies");
                    missatges.add(ms);
                }
            } catch (I18NException e) {

                String msg = "Error consultant el missatges agrupats pendents de més de 2 dies: "
                        + I18NCommonUtils.getMessage(e, new Locale(Configuracio.getDefaultLanguage()));

                log.error(msg, e);

                MissatgeSalut ms = new MissatgeSalut();
                ms.setNivell(SalutNivell.ERROR);
                ms.setData(getDateTime());
                ms.setMissatge(msg);
                missatges.add(ms);
            }

            sInfo.setMissatges(missatges);
        }

        {

            List<SubsistemaSalut> subsistemesList = new java.util.ArrayList<>();

            for (String[] subsistema : SUBSISTEMES_PORTAFIB) {
                /*
                if (SUBSISTEMA_API_FIRMA_ASYNC.equals(subsistema[0])) {
                
                    SubsistemaSalut subSystemApiFirmaAsinc = new SubsistemaSalut();
                    subSystemApiFirmaAsinc.setCodi(SUBSISTEMA_API_FIRMA_ASYNC);
                    subSystemApiFirmaAsinc.setEstat(EstatSalutEnum.UP);
                    // TODO calcular latència
                    subSystemApiFirmaAsinc.setLatencia(null);
                
                    Timestamp avui = new Timestamp(System.currentTimeMillis());
                
                    Calendar cal = Calendar.getInstance();
                
                    cal.add(Calendar.MONTH, -1);
                
                    Timestamp faunmes;
                    if (dataPeriode != null) {
                        faunmes = new Timestamp(dataPeriode.toInstant().toEpochMilli());
                    } else {
                
                        faunmes = new Timestamp(cal.getTimeInMillis());
                    }
                
                    cal.add(Calendar.MONTH, -11);
                
                    Timestamp faunany;
                
                    if (dataTotal != null) {
                        faunany = new Timestamp(dataTotal.toInstant().toEpochMilli());
                    } else {
                        faunany = new Timestamp(cal.getTimeInMillis());
                    }
                
                    // Cercar peticions d'aquesta integració 
                
                    subSystemApiFirmaAsinc.setPeticionsErrorUltimPeriode(
                            calculPeticions(ConstantsV2.TIPUSESTATPETICIODEFIRMA_REBUTJAT, faunmes, avui));
                    subSystemApiFirmaAsinc.setPeticionsOkUltimPeriode(
                            calculPeticions(ConstantsV2.TIPUSESTATPETICIODEFIRMA_FIRMAT, faunmes, avui));
                    subSystemApiFirmaAsinc.setTempsMigUltimPeriode(-1);
                    subSystemApiFirmaAsinc.setTotalError(
                            calculPeticions(ConstantsV2.TIPUSESTATPETICIODEFIRMA_REBUTJAT, faunany, avui));
                    subSystemApiFirmaAsinc
                            .setTotalOk(calculPeticions(ConstantsV2.TIPUSESTATPETICIODEFIRMA_FIRMAT, faunany, avui));
                    subSystemApiFirmaAsinc.setTotalTempsMig(-1);
                
                    subsistemesList.add(subSystemApiFirmaAsinc);
                } else {
                    throw new InternalServerErrorException(
                            "No s'ha implementat el càlcul de peticions pel subsistema " + subsistema[0]);
                }
                */
            }

            sInfo.setSubsistemes(subsistemesList);
        }

        sInfo.setVersio(new Version().getVersion());

        return sInfo;
    }

    protected long calculPeticions(List<Integer> tipus, Timestamp from, Timestamp to) throws I18NException {

        /*
        
        long totalOK;
        
        Where w1 = PeticioDeFirmaFields.SOLICITANTUSUARIAPLICACIOID.isNotNull();
        Where w2 = PeticioDeFirmaFields.DATASOLICITUD.between(from, to);
        Where w3 = PeticioDeFirmaFields.TIPUSESTATPETICIODEFIRMAID.equal(estat);
        
        Where w = Where.AND(w1, w2, w3);
        try {
            totalOK = peticioDeFirmaEjb.count(w);
        } catch (I18NException e) {
            log.error(I18NCommonUtils.getMessage(e, new Locale(Configuracio.getDefaultLanguage())));
            totalOK = -1;
        }
        return totalOK;
        */

        Where wFromTo = EstadisticaFields.DATA.between(from, to);

        Where wTipus = EstadisticaFields.TIPUS.in(tipus);

        Double valorObj = estadisticaEjb.sumDecimal(EstadisticaFields.VALOR, Where.AND(wTipus, wFromTo));

        if (valorObj == null) {
            return 0;
        } else {
            return valorObj.longValue();
        }

    }

    protected OffsetDateTime getDateTime() {
        return OffsetDateTime.now();
    }

    public static String jbossVersionCache = null;

    protected String getJBossVersion() {

        if (jbossVersionCache == null) {
            String jbossVersion = null;
            try {
                ObjectName rootNameObjectName = new ObjectName("jboss.as:management-root=server");
                for (MBeanServer server : MBeanServerFactory.findMBeanServer(null)) {
                    if (server.isRegistered(rootNameObjectName)) {
                        jbossVersion = (String) server.getAttribute(rootNameObjectName, "product-version");
                        break;
                    }
                }

                if (jbossVersion == null) {
                    log.warn("JBOSS VERSION: No s'ha trobat el camp 'product-version'");
                }

            } catch (Exception e) {
                log.error("JBOSS VERSION: error no controlat " + e.getMessage(), e);
            }

            log.info("JBOSS VERSION: " + jbossVersion);
            if (jbossVersion != null) {
                jbossVersionCache = jbossVersion;
            }
        }

        return jbossVersionCache;

    }

}
