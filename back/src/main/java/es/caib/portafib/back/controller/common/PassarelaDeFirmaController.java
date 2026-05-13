package es.caib.portafib.back.controller.common;

import es.caib.portafib.back.controller.AbstractSignatureModuleController;
import es.caib.portafib.back.security.LoginInfo;
import es.caib.portafib.back.utils.PortaFIBSessionLocaleResolver;
import es.caib.portafib.back.utils.PortaFIBSignaturesSet;
import es.caib.portafib.persistence.EntitatJPA;
import es.caib.portafib.persistence.PeticioDeFirmaJPA;
import es.caib.portafib.persistence.UsuariAplicacioJPA;
import es.caib.portafib.logic.ConfiguracioUsuariAplicacioLogicaLocal;
import es.caib.portafib.logic.EstadisticaLogicaLocal;
import es.caib.portafib.logic.ModulDeFirmaWebPublicLogicaLocal;
import es.caib.portafib.logic.SegellDeTempsPublicLogicaLocal;
import es.caib.portafib.logic.UsuariAplicacioLogicaLocal;
import es.caib.portafib.logic.passarela.PassarelaDeFirmaWebLocal;
import es.caib.portafib.logic.passarela.PassarelaSignatureStatusWebInternalUse;
import es.caib.portafib.logic.passarela.PassarelaSignaturesSetWebInternalUse;
import es.caib.portafib.logic.passarela.api.PassarelaSignaturesSet;
import es.caib.portafib.logic.utils.I18NLogicUtils;
import es.caib.portafib.logic.utils.SignatureUtils;
import es.caib.portafib.commons.utils.Configuracio;
import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pluginsib.signature.api.FileInfoSignature;
import org.fundaciobit.pluginsib.signature.api.SignaturesSet;
import org.fundaciobit.pluginsib.signature.api.StatusSignature;
import org.fundaciobit.pluginsib.signatureweb.api.SignaturesSetWeb;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * 
 * @author anadal(u80067)
 *
 */
@Controller
@RequestMapping(value = PassarelaDeFirmaWebLocal.PASSARELA_CONTEXTPATH)
public class PassarelaDeFirmaController {

    protected final Logger log = Logger.getLogger(this.getClass());

    @EJB(mappedName = PassarelaDeFirmaWebLocal.JNDI_NAME)
    protected PassarelaDeFirmaWebLocal passarelaDeFirmaEjb;

    @EJB(mappedName = ModulDeFirmaWebPublicLogicaLocal.JNDI_NAME)
    protected ModulDeFirmaWebPublicLogicaLocal modulDeFirmaPublicEjb;

    @EJB(mappedName = SegellDeTempsPublicLogicaLocal.JNDI_NAME)
    protected SegellDeTempsPublicLogicaLocal segellDeTempsPublicEjb;

    @EJB(mappedName = ConfiguracioUsuariAplicacioLogicaLocal.JNDI_NAME)
    public ConfiguracioUsuariAplicacioLogicaLocal configuracioUsuariAplicacioLogicaLocalEjb;

    @EJB(mappedName = UsuariAplicacioLogicaLocal.JNDI_NAME)
    protected UsuariAplicacioLogicaLocal usuariAplicacioLogicaEjb;

    @EJB(mappedName = EstadisticaLogicaLocal.JNDI_NAME)
    protected EstadisticaLogicaLocal estadisticaLogicaEjb;

    /**
     * 
     */
    public PassarelaDeFirmaController() {
        super();
    }

    @RequestMapping(value = "/start/{transactionID}", method = RequestMethod.GET)
    public ModelAndView passarelaGet(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("transactionID")
            String signaturesSetID) throws Exception, I18NException {

        PassarelaSignaturesSetWebInternalUse ssf = passarelaDeFirmaEjb
                .getSignaturesSetFullByTransactionID(signaturesSetID);

        if (ssf == null) {
            // XYZ ZZZ Enviar a pàgina que digui que algua cosa ha passat
            // XYZ ZZZ TODO Traduir
            final String msg = "La transacció amb ID " + signaturesSetID + " no existeix o ja ha caducat.";
            log.error(msg, new Exception());

            throw new Exception(msg);
        }

        // Problema de Fitxer Buit retornat per plugin i warning "ALREADY CONTAINS KEY !!!!"   #1081
        {
            if (ssf.getStartDate() != null) {
                //Utils.printRequestInfo(request);
                String msgBase = "Algú ja ha accedit a aquesta url amb transacció ID igual " + signaturesSetID;
                String msgLog = "PASSARELA:: " + msgBase + ". Revisi de no obrir més d'una vegada aquesta url "
                        + request.getRequestURL() + " (usrapp = " + ssf.getApplicationID() + " | NIF = "
                        + ssf.getSignaturesSet().getCommonInfoSignature().getAdministrationID() + ")";
                log.error(msgLog);
                String msgError = msgBase + ". Si us plau torni a intentar la signatura.";
                throw new Exception(msgError);
            }

            ssf.setStartDate(new Date());
        }

        // Passarela només pot tenir una sola Configuracio
        try {

            PassarelaSignaturesSet pss = ssf.getSignaturesSet();

            EntitatJPA entitat = passarelaDeFirmaEjb.getEntitat(ssf.getEntitatID());

            UsuariAplicacioJPA usrApp = usuariAplicacioLogicaEjb.findByPrimaryKey(ssf.getApplicationID());

            Set<String> timeStampUrls = new HashSet<String>();
            SignaturesSet ss = SignatureUtils.passarelaSignaturesSetToSignaturesSet(passarelaDeFirmaEjb,
                    segellDeTempsPublicEjb, pss, usrApp, ssf.getPerfilDeFirma(), ssf.getConfigBySignID(), entitat,
                    timeStampUrls);

            // Vull suposar que abans de 10 minuts haurà firmat
            java.util.Date caducitat = pss.getExpiryDate();

            String relativeControllerBase = AbstractSignatureModuleController.getRelativeControllerBase(request,
                    PassarelaDeFirmaWebLocal.PASSARELA_CONTEXTPATH);
            final String urlFinal = response.encodeURL(relativeControllerBase
                    + PassarelaDeFirmaWebLocal.PASSARELA_CONTEXTPATH_FINAL + "/" + signaturesSetID);

            PortaFIBSignaturesSet signaturesSet = new PortaFIBSignaturesSet(signaturesSetID, caducitat,
                    ss.getCommonInfoSignature(), ss.getFileInfoSignatureArray(), ssf.getOriginalNumberOfSignsArray(),
                    ssf.getApplicationID(), entitat, urlFinal, !ssf.isFullView(), ssf.getBaseUrl());

            // Filtres definits en l'Aplicació CLient
            List<Long> filterByPluginsID = pss.getCommonInfoSignature().getAcceptedPlugins();
            if (filterByPluginsID != null && filterByPluginsID.size() == 0) {
                filterByPluginsID = null;
            }
            signaturesSet.setFilterByPluginID(filterByPluginsID);

            // No tenim cap restricció de plugins per tipus de document
            signaturesSet.setPluginsFirmaBySignatureID(null);

            // Afegir usuariAplicació per #173
            // En passarela l'aplicació és la mateixa per totes les signatures.
            for (FileInfoSignature fis : ss.getFileInfoSignatureArray()) {
                signaturesSet.getApplicationBySignatureID().put(fis.getSignID(), ssf.getApplicationID());
            }

            final String view = "PluginDeFirmaContenidor_Passarela";

            ModelAndView mav = AbstractSignatureModuleController.startPublicSignatureProcess(request, response, view,
                    signaturesSet);

            LoginInfo loginInfo = null;
            try {
                loginInfo = LoginInfo.getInstance();
            } catch (Throwable e) {
            }

            String idioma = signaturesSet.getCommonInfoSignature().getLanguageUI();

            if (idioma == null || idioma.trim().length() == 0) {
                idioma = Configuracio.getDefaultLanguage();
            }
            if (loginInfo == null || loginInfo.getUsuariAplicacio() != null) {
                PortaFIBSessionLocaleResolver.setLocaleManually(request, idioma);
                mav.addObject("lang", idioma);
            }

            if (log.isDebugEnabled()) {
                log.debug(" ===startPublicSignatureProcess() ==> idioma " + idioma);
                log.debug(" ===startPublicSignatureProcess() ==> signaturesSetID: " + signaturesSetID);
                log.debug(" ===startPublicSignatureProcess() ==> urlFinal: " + signaturesSet.getUrlFinal());
            }

            // En passarela de firma requerim dins d'un frame
            mav.addObject("fullView", ssf.isFullView());

            return mav;

        } catch (Throwable th) {

            String msg;

            if (th instanceof I18NException) {
                I18NException i18ne = (I18NException) th;

                Locale loc = new Locale(ssf.getSignaturesSet().getCommonInfoSignature().getLanguageUI());

                msg = I18NLogicUtils.getMessage(i18ne, loc);

            } else {
                msg = th.getMessage();
            }

            log.error(msg, th);

            ssf.setStatus(StatusSignature.STATUS_FINAL_ERROR);
            ssf.setErrorMessage(msg);
            ssf.setErrorCode(null);

            StringWriter trace = new StringWriter();
            th.printStackTrace(new java.io.PrintWriter(trace));
            ssf.setErrorStackTrace(trace.toString());

            return new ModelAndView(
                    new RedirectView(ssf.getSignaturesSet().getCommonInfoSignature().getUrlFinal(), false));

        }

    }

    /** 
     * Quan acaba el mòdul de firma mostram espera de validacions de firma
     * @param request
     * @param response
     * @param transactionID
     * @return
     * @throws Exception
     */
    @RequestMapping(value = PassarelaDeFirmaWebLocal.PASSARELA_CONTEXTPATH_FINAL + "/{transactionID}")
    public ModelAndView finalProcesDeFirma(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("transactionID")
            String transactionID) throws Exception {

        log.debug("PASSA PER PassarelaDeFirmaController::finalProcesDeFirma[" + transactionID + "]");

        ModelAndView mav = new ModelAndView("passarela_wait");

        mav.addObject("finalURL", PassarelaDeFirmaWebLocal.PASSARELA_CONTEXTPATH
                + PassarelaDeFirmaWebLocal.PASSARELA_CONTEXTPATH_FINAL + "Real/" + transactionID);

        return mav;

    }

    @RequestMapping(value = PassarelaDeFirmaWebLocal.PASSARELA_CONTEXTPATH_FINAL + "Real" + "/{transactionID}")
    public ModelAndView finalProcesDeFirmaReal(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("transactionID")
            String transactionID) throws Exception, I18NException {

        final boolean debug = log.isDebugEnabled();

        if (debug) {
            log.debug(" ===finalProcesDeFirma() ==> signaturesSetID: " + transactionID);
        }

        SignaturesSetWeb ss;
        boolean administrationIdCanBeValidatedFromPlugin;
        boolean willCanCheckIfSignedDocumentWasAlteredAfterSignature;
        Long signaturePluginID;
        {
            PortaFIBSignaturesSet pss = AbstractSignatureModuleController.getPortaFIBSignaturesSet(request,
                    transactionID, modulDeFirmaPublicEjb);
            signaturePluginID = pss.getSelectedPluginID();
            administrationIdCanBeValidatedFromPlugin = modulDeFirmaPublicEjb
                    .administrationIdCanBeValidatedFromPlugin(signaturePluginID);

            willCanCheckIfSignedDocumentWasAlteredAfterSignature = modulDeFirmaPublicEjb
                    .willCanCheckIfSignedDocumentWasAlteredAfterSignatureFromPlugin(signaturePluginID);
            ss = pss;
        }

        // TODO Comprovar si ss és null i mostrar missatge de firma caducada

        PassarelaSignaturesSetWebInternalUse ssf = passarelaDeFirmaEjb.finalProcesDeFirma(transactionID, ss,
                administrationIdCanBeValidatedFromPlugin, willCanCheckIfSignedDocumentWasAlteredAfterSignature);
        ssf.setSignaturePluginId(signaturePluginID);

        afegirEstadistiques(ssf);

        // Eliminam la informació dins SignatureModuleController ja que tenim gurardada la
        // informació dins la capa EJB
        AbstractSignatureModuleController.closeSignaturesSet(request, transactionID, modulDeFirmaPublicEjb);

        final String url = ssf.getSignaturesSet().getCommonInfoSignature().getUrlFinal();

        log.debug("PassarelaDeFirmaController::finalProcessDeFirma(); => URL redirect = " + url);

        return new ModelAndView(new RedirectView(url));

    }

    public final void afegirEstadistiques(PassarelaSignaturesSetWebInternalUse ssf) {

        try {

            int val1 = ssf.getPeticioFirmaBySignID().size();

            Collection<PeticioDeFirmaJPA> firmes = ssf.getPeticioFirmaBySignID().values();

            /*
            for (PeticioDeFirmaJPA peticiodeFirma : firmes) {
            
                log.info("\n\n ESTADISTIQUES: Origen " + peticiodeFirma.getOrigenPeticioDeFirma() + " i applicationID "
                        + ssf.getApplicationID());
            }
            */

            final int globalStatus = ssf.getStatus();
            final int totalfirmes = val1;

            int suma_cancelled = 0;
            int suma_error = 0;
            int suma_ok = 0;

            switch (globalStatus) {
                case StatusSignature.STATUS_CANCELLED:
                    suma_cancelled = totalfirmes;
                break;

                // Si arribam aqui amb init o signing ho entenem coma un error.
                case StatusSignature.STATUS_INITIALIZING:
                case StatusSignature.STATUS_IN_PROGRESS:
                case StatusSignature.STATUS_FINAL_ERROR:
                    suma_error = totalfirmes;
                break;

                // Transacció ha finalitzat OK, hem de revisar cada firma
                case StatusSignature.STATUS_FINAL_OK:

                    for (PassarelaSignatureStatusWebInternalUse s : ssf.getStatusBySignatureID().values()) {

                        switch (s.getStatus()) {

                            case StatusSignature.STATUS_FINAL_OK:
                                suma_ok++;
                            break;

                            case StatusSignature.STATUS_CANCELLED:
                                suma_cancelled++;
                            break;

                            // Si arribam aqui amb init o signing ho entenem coma un error.
                            case StatusSignature.STATUS_INITIALIZING:
                            case StatusSignature.STATUS_IN_PROGRESS:
                            case StatusSignature.STATUS_FINAL_ERROR:
                                suma_error++;
                            break;

                        }
                    }

                break;

                default:
                    log.warn("Status de la petició de firma desconegut: " + globalStatus);
            }

            // Obtenir origen de la peticio de firma
            PeticioDeFirmaJPA peticiodeFirma = firmes.iterator().next();

            int origen = peticiodeFirma.getOrigenPeticioDeFirma();
            String entitatID = ssf.getEntitatID();
            String applicationID = ssf.getApplicationID();

            estadisticaLogicaEjb.createEstadistica(origen, entitatID, applicationID, suma_ok, suma_cancelled,
                    suma_error);

        } catch (Throwable th) {
            log.error("Error afegint estadístiques de la petició de firma: " + th.getMessage(), th);
        }

    }

}
