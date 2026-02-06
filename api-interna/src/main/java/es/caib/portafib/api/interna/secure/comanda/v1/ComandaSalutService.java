package es.caib.portafib.api.interna.secure.comanda.v1;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.fundaciobit.genapp.common.i18n.I18NCommonUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
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
import es.caib.comanda.model.server.monitoring.SubsistemaSalut;
import es.caib.comanda.ms.salut.helper.IntegracioApp;
import es.caib.comanda.ms.salut.helper.MonitorHelper;
import es.caib.comanda.ms.salut.helper.SalutHelper;
import es.caib.comanda.ms.salut.helper.SalutHelper.BuildInfo;
import es.caib.portafib.commons.utils.Configuracio;
import es.caib.portafib.commons.utils.Constants;
import es.caib.portafib.commons.utils.Version;
import es.caib.portafib.logic.utils.PropietatGlobalUtil;
import es.caib.portafib.model.fields.PeticioDeFirmaFields;
import es.caib.portafib.utils.ConstantsV2;
import io.swagger.annotations.ApiOperation;
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
    @RolesAllowed({ Constants.PFI_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    public SalutInfo salut() {
        SalutInfo sInfo = new SalutInfo();
        sInfo.setCodi("PFI");
        sInfo.setData(getDateTime());

        // TODO
        EstatSalut estatBaseDeDades = null;
        sInfo.setEstatBaseDeDades(estatBaseDeDades);

        // TODO 
        EstatSalut estatGlobal = new EstatSalut();
        estatGlobal.setEstat(EstatSalutEnum.UP);
        estatGlobal.setLatencia(null);
        sInfo.setEstatGlobal(estatGlobal);

        sInfo.setInformacioSistema(MonitorHelper.getInfoSistema());

        // TODO

        {
            IntegracioSalut integracio = new IntegracioSalut();
            integracio.setCodi("PFI_API_FIRMA_ASYNC");
            integracio.setEstat(EstatSalutEnum.UP);
            // TODO calcular latència
            integracio.setLatencia(null);

            Timestamp avui = new Timestamp(System.currentTimeMillis());

            Calendar cal = Calendar.getInstance();

            cal.add(Calendar.MONTH, -1);

            Timestamp faunmes = new Timestamp(cal.getTimeInMillis());

            cal.add(Calendar.MONTH, -11);

            Timestamp faunany = new Timestamp(cal.getTimeInMillis());

          

            // Cercar peticions d'aquesta integració 
            IntegracioPeticions peticions = new IntegracioPeticions();
            peticions.setEndpoint("/secure/secure/asyncsignatureonweb/v1/");
            peticions.setPeticionsErrorUltimPeriode(calculPeticions(ConstantsV2.TIPUSESTATPETICIODEFIRMA_REBUTJAT, faunmes, avui)); 
            peticions.setPeticionsOkUltimPeriode(calculPeticions(ConstantsV2.TIPUSESTATPETICIODEFIRMA_FIRMAT, faunmes, avui)); 
            peticions.setPeticionsPerEntorn(null); // TODO calcular map
            peticions.setTempsMigUltimPeriode(-1); 
            peticions.setTotalError(calculPeticions(ConstantsV2.TIPUSESTATPETICIODEFIRMA_REBUTJAT, faunany, avui));
            peticions.setTotalOk(calculPeticions(ConstantsV2.TIPUSESTATPETICIODEFIRMA_FIRMAT, faunany, avui)); 
            peticions.setTotalTempsMig(-1);
            integracio.setPeticions(peticions);

            List<IntegracioSalut> integracions = new java.util.ArrayList<>();
            integracions.add(integracio);

            sInfo.setIntegracions(integracions);
        }

        {
            // TODO
            MissatgeSalut ms = new MissatgeSalut();
            ms.setNivell(SalutNivell.ERROR);
            ms.setData(getDateTime());
            ms.setMissatge("No funciona l'integració amb el servei XYZ");

            List<MissatgeSalut> missatges = new ArrayList<MissatgeSalut>();
            missatges.add(ms);
            sInfo.setMissatges(missatges);
        }

        {
            // TODO
            List<SubsistemaSalut> subsistemes = null;
            sInfo.setSubsistemes(subsistemes);
        }

        sInfo.setVersio(new Version().getVersion());

        return sInfo;
    }

    protected long calculPeticions(int estat, Timestamp from, Timestamp to) {
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
    }

    protected OffsetDateTime getDateTime() {
        return OffsetDateTime.now();
    }

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
    public AppInfo salutInfo() {

        // TODO Utilitzar get BuildInfo

        //SalutHelper.BuildInfo.builder().

        AppInfo a = new AppInfo();

        {

            BuildInfo infoTmp = SalutHelper.BuildInfo.builder().build();
            a.revisio(infoTmp.getCommitId());
            a.jdkVersion(infoTmp.getBuildJDK());

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
                manual.setPath("Manual_de_Usuari_de_PortaFIB");
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

                Manual manual = new Manual();
                manual.setPath("Manual_de_Migració_de_APIsIB_a_Api_Interna");
                manual.setPath(
                        "https://github.com/GovernIB/portafib/raw/refs/heads/portafib-3.0/doc/Manual_de_Migraci%C3%B3_de_APIsIB_a_Api_Interna.odt");
                apiinterna.setManuals(List.of(manual));

                apiinterna.setNom("PortaFIB Backoffice/Frontoffice");
                apiinterna.setPath(urlBase + "/portafibapi/interna");

                contexts.add(apiinterna);
            }

            {
                ContextInfo restsimple = new ContextInfo();
                restsimple.setApi(null);
                restsimple.setCodi("PFI_API_SIMPLE_REST");

                String[][] manualsPaths = { { "Manual_de_RESTServices_de_PortaFIB",
                        "https://github.com/GovernIB/portafib/raw/refs/heads/portafib-3.0/doc/Manual_de_RESTServices_de_PortaFIB.odt" },

                        { "Manual_Integracio_API_Firma_Async_Simple_v2_0",
                                "https://github.com/GovernIB/portafib/raw/refs/heads/portafib-3.0/doc/Manual_Integracio_API_Firma_Async_Simple_v2_0.odt" },

                        { "Manual_Integracio_API_Firma_Simple_v1_0",
                                "https://github.com/GovernIB/portafib/raw/refs/heads/portafib-3.0/doc/Manual_Integracio_API_Firma_Simple_v1_0.odt" },
                        { "Manual_Integracio_API_Plantilla_Flux_Simple_v1_0",
                                "https://github.com/GovernIB/portafib/raw/refs/heads/portafib-3.0/doc/Manual_Integracio_API_Plantilla_Flux_Simple_v1_0.odt" },
                        { "Manual_de_RESTServices_de_PortaFIB",
                                "https://github.com/GovernIB/portafib/raw/refs/heads/portafib-3.0/doc/Manual_de_RESTServices_de_PortaFIB.odt" },
                        { "Model_de_classes_API_de_firma",
                                "https://github.com/GovernIB/portafib/raw/refs/heads/portafib-3.0/doc/Model_de_classes_API_de_firma.odt" }

                };

                for (String[] manualPath : manualsPaths) {
                    Manual manual = new Manual();
                    manual.setPath(manualPath[0]);
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

        {
            List<IntegracioInfo> list = new ArrayList<>();
            IntegracioApp[] integracioApps = { IntegracioApp.EVI, IntegracioApp.CDO, IntegracioApp.EML,
                    IntegracioApp.SIG, IntegracioApp.VFI, IntegracioApp.VIF, IntegracioApp.USR };

            for (IntegracioApp ia : integracioApps) {
                IntegracioInfo i1 = new IntegracioInfo();
                i1.setCodi(ia.name());
                i1.setNom(ia.getNom());
                list.add(i1);
            }

            a.setIntegracions(list);

        }

        a.setSubsistemes(null);

        a.versio(new Version().getVersion());

        a.setVersioJboss(getJBossVersion());

        return a;

    }

    public static String jbossVersionCache = null;

    public String getJBossVersion() {

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
