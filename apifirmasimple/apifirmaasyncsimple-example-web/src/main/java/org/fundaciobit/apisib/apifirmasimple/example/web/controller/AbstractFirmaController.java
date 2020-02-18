package org.fundaciobit.apisib.apifirmasimple.example.web.controller;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.fundaciobit.apisib.apifirmasimple.example.web.form.FirmaForm;
import org.fundaciobit.apisib.apifirmasimple.example.web.utils.ApiFirmaEnServidorCache;
import org.fundaciobit.apisib.apifirmasimple.example.web.utils.ApiFirmaWebCache;
import org.fundaciobit.apisib.apifirmasimple.example.web.utils.HtmlUtils;
import org.fundaciobit.apisib.apifirmasimple.example.web.utils.InfoGlobal;
import org.fundaciobit.apisib.apifirmasimple.v1.ApiFirmaEnServidorSimple;
import org.fundaciobit.apisib.apifirmasimple.v1.ApiFirmaWebSimple;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleAddFileToSignRequest;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleAvailableProfile;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleCommonInfo;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFileInfoSignature;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleGetSignatureResultRequest;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleGetTransactionStatusResponse;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignDocumentRequest;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignatureResult;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignatureStatus;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignedFileInfo;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleStartTransactionRequest;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleStatus;
import org.fundaciobit.apisib.core.exceptions.AbstractApisIBException;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author anadal
 *
 */

public abstract class AbstractFirmaController {

    /** Logger for this class and subclasses */
    protected final Logger log = Logger.getLogger(getClass());


    @InitBinder("firmaForm")
    public void initBinder(WebDataBinder binder) {

        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));

    }

    @ModelAttribute("isFirmaWeb")
    public final boolean isFirmaWeb() {
        return isWeb();
    }

    public abstract boolean isWeb();

    @ModelAttribute("contextWeb")
    public abstract String getContextWeb();

    @ModelAttribute("firmaCodeLabel")
    public String getFirmaCodeLabel() {
        return isWeb() ? "firmaweb" : "firmaservidor";
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public ModelAndView firmaGet(HttpServletRequest request) throws Exception {

        request.removeAttribute("peticions");

        FirmaForm form = new FirmaForm();

        if (isWeb()) {

            ApiFirmaWebSimple api = ApiFirmaWebCache.getApiFirmaWebSimple(request);
            if (api == null) {
                return new ModelAndView(new RedirectView("/web/configuracio", true));
            }

            form.setUsername("anadal");
            form.setNif("12345678X");
            form.setEmail("myemail@servermail.org");

        } else {

            ApiFirmaEnServidorSimple api = ApiFirmaEnServidorCache.getApiFirmaEnServidorSimple(request);
            if (api == null) {
                return new ModelAndView(new RedirectView("/server/configuracio", true));
            }
        }

        if (isWeb()) {
            ApiFirmaWebSimple apiWeb = ApiFirmaWebCache.getApiFirmaWebSimple(request);

            List<FirmaSimpleAvailableProfile> perfils = apiWeb.getAvailableProfiles("ca");

            if (perfils == null || perfils.size() == 0) {
                log.info("NO HI HA PERFILS !!!");
            } else {
                for (FirmaSimpleAvailableProfile p : perfils) {
                    log.info(" P[" + p.getCode() + "] " + p.getName() + " => " + p.getDescription());
                }
            }

            form.setProfiles(perfils);
        } else {
            ApiFirmaEnServidorSimple apiServidor = ApiFirmaEnServidorCache.getApiFirmaEnServidorSimple(request);
            form.setProfiles(apiServidor.getAvailableProfiles("ca"));
        }

        String txt = "Per petició de firma pròpia";
        form.setMotiu(txt);

        form.setVisualitzacio(FirmaForm.VISUALITZACIO_FULLVIEW);

        // TODO XYZ Això s'ha de fer a la part CLIENT !!!!!
        String city = urlToText(new URL("http://ip-api.com/line/?fields=city"));
        byte ptext[] = city.getBytes();
        String location = new String(ptext, "UTF-8");

        form.setLocation(location);

        ModelAndView mav = new ModelAndView("firmaForm");
        mav.addObject(form);

        return mav;

    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public ModelAndView firmaPost(HttpServletRequest request, HttpServletResponse response,
            @Valid @ModelAttribute("firmaForm") FirmaForm form, BindingResult bindingResult) throws Exception {

        if (bindingResult.hasErrors()) {

            log.warn("Validador té errors !!!!");

            ModelAndView mav = new ModelAndView("firmaForm");
            mav.addObject(form);
            return mav;
        }

        CommonsMultipartFile[] fitxers = new CommonsMultipartFile[] { form.getFitxerAFirmarID(),
                form.getFitxerAFirmarID2(), form.getFitxerAFirmarID3(), form.getFitxerAFirmarID4() };

        ApiFirmaWebSimple apiWeb = null;
        String transactionID = null;

        final boolean esWeb = isWeb();

        try {

            if (esWeb) {
                apiWeb = ApiFirmaWebCache.getApiFirmaWebSimple(request);
            }

            final String username = form.getUsername();
            final String administrationID = form.getNif();
            final String signerEmail = form.getEmail();

            log.info("Username: ]" + username + "[");
            log.info("administrationID: ]" + administrationID + "[");
            log.info("signerEmail: ]" + signerEmail + "[");

            final String reason = form.getMotiu();
            final String location = form.getLocation();

            // Només es suporta una firma
            final int signNumber = 1;

            final String langUI = form.getLangUI();
            final String langDoc = form.getLangDoc();

            FirmaSimpleCommonInfo commonInfoSignature;
            commonInfoSignature = new FirmaSimpleCommonInfo(form.getProfile(), langUI, username, administrationID,
                    signerEmail);

            if (esWeb) {
                // Enviam la part comu de la transacció
                transactionID = apiWeb.getTransactionID(commonInfoSignature);
                log.info("TransactionID = |" + transactionID + "|");
            }

            Map<String, InfoGlobal> peticions = new HashMap<String, InfoGlobal>();
            List<FirmaSimpleFileInfoSignature> fileInfoSignatureList = new ArrayList<FirmaSimpleFileInfoSignature>();

            for (int i = 0; i < fitxers.length; i++) {

                CommonsMultipartFile cmf = fitxers[i];

                if (cmf == null || cmf.isEmpty()) {
                    continue;
                }

                // No ha de contenir caracters no suportats en URLs
                final String signID = "F" + (i + 1);

                InputStream is = cmf.getInputStream();
                String mimeTypeFitxer = cmf.getContentType();
                byte[] dataFitxer = IOUtils.toByteArray(is);

                is.close();
                String nomFitxer = cmf.getOriginalFilename();

                FirmaSimpleFile fileToSign = new FirmaSimpleFile(nomFitxer, mimeTypeFitxer, dataFitxer);

                long tipusDocumentalID = 99; // =TD99

                FirmaSimpleFileInfoSignature fileInfoSignature = new FirmaSimpleFileInfoSignature(fileToSign, signID,
                        fileToSign.getNom(), reason, location, signNumber, langDoc, tipusDocumentalID);

                fileInfoSignatureList.add(fileInfoSignature);

                peticions.put(fileInfoSignature.getSignID(), new InfoGlobal(fileInfoSignature));

                if (esWeb) {
                    apiWeb.addFileToSign(new FirmaSimpleAddFileToSignRequest(transactionID, fileInfoSignature));
                }
            }

            FirmaSimpleFileInfoSignature[] fileInfoSignatureArray;
            fileInfoSignatureArray = fileInfoSignatureList
                    .toArray(new FirmaSimpleFileInfoSignature[fileInfoSignatureList.size()]);

            // Esbrinam SI WEB O SERVER
            if (esWeb) {

                // Es Web
                String view;
                if (FirmaForm.VISUALITZACIO_FULLVIEW.equals(form.getVisualitzacio())) {
                    view = FirmaSimpleStartTransactionRequest.VIEW_FULLSCREEN;
                } else {
                    view = FirmaSimpleStartTransactionRequest.VIEW_IFRAME;
                }

                log.info("HOSTURL => " + form.getHostUrl());

                String hostUrl = form.getHostUrl();
                String absoluteBase = hostUrl.substring(0, hostUrl.indexOf("/firmaweb/form"))
                        + FirmaWebController.CONTEXTWEB;

                final String returnUrl;
                boolean isFullView = FirmaSimpleStartTransactionRequest.VIEW_FULLSCREEN.equals(view);
                if (isFullView) {
                    returnUrl = absoluteBase + "/finalWeb/" + transactionID;
                } else {
                    returnUrl = absoluteBase + "/finalWebIFrame/" + transactionID;
                }

                FirmaSimpleStartTransactionRequest startTransactionInfo;
                startTransactionInfo = new FirmaSimpleStartTransactionRequest(transactionID, returnUrl, view);

                String redirectUrl = apiWeb.startTransaction(startTransactionInfo);

                request.getSession().setAttribute("peticions", peticions);

                if (isFullView) {
                    ModelAndView mav = new ModelAndView("redirect:" + redirectUrl);
                    return mav;
                } else {
                    request.getSession().setAttribute("redirectUrl", redirectUrl);
                    ModelAndView mav = new ModelAndView("redirect:" + getContextWeb() + "/viewiniframe");
                    return mav;
                }

            } else {
                // Es servidor
                ApiFirmaEnServidorSimple apiServidor = ApiFirmaEnServidorCache.getApiFirmaEnServidorSimple(request);

                List<FirmaSimpleSignatureResult> list = new ArrayList<FirmaSimpleSignatureResult>();

                for (FirmaSimpleFileInfoSignature infoSign : fileInfoSignatureArray) {

                    FirmaSimpleSignatureResult result;
                    try {

                        FirmaSimpleSignDocumentRequest fes;
                        fes = new FirmaSimpleSignDocumentRequest(commonInfoSignature, infoSign);

                        result = apiServidor.signDocument(fes);

                    } catch (AbstractApisIBException e) {

                        StringWriter sw = new StringWriter();
                        PrintWriter pw = new PrintWriter(sw);
                        e.printStackTrace(pw);

                        FirmaSimpleStatus status;

                        status = new FirmaSimpleStatus(FirmaSimpleStatus.STATUS_FINAL_ERROR, e.getMessage(),
                                sw.toString());

                        result = new FirmaSimpleSignatureResult(infoSign.getSignID(), status, null, null);
                    }

                    list.add(result);
                }

                return finalProcesDeFirmaServer(request, response, list, peticions);

            } // Final if -Web-EnServidor
        } catch (AbstractApisIBException e) {

            log.error(e.getMessage(), e);

            HtmlUtils.saveMessageError(request, e.getMessage());

            ModelAndView mav = new ModelAndView("firmaForm");
            mav.addObject(form);

            return mav;

        } catch (Exception e) {

            String msg = "Error desconegut processant entrada de dades o inicialitzant el proces de firma: "
                    + e.getMessage();

            log.error(msg, e);

            HtmlUtils.saveMessageError(request, msg);

            try {

                // Només s'executa si es WEB
                if (apiWeb != null && transactionID != null) {
                    try {
                        apiWeb.closeTransaction(transactionID);
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }

            } catch (Exception e2) {
                e2.printStackTrace();
            }

            ModelAndView mav = new ModelAndView("firmaForm");
            mav.addObject(form);

            return mav;
        }

    }

    @RequestMapping(value = "/viewiniframe", method = RequestMethod.GET)
    public ModelAndView viewInIframe(HttpServletRequest request) throws Exception {

        String redirectUrl = (String) request.getSession().getAttribute("redirectUrl");
        if (log.isDebugEnabled()) {
            log.debug("ENTRA A /viewiniframe => redirectUrl: " + redirectUrl);
        }

        ModelAndView mav = new ModelAndView("firmasimpleweb_iframe");
        mav.addObject("urlToIFrameCode", redirectUrl);
        return mav;

    }

    public String urlToText(URL url) {

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            StringBuffer b = new StringBuffer();
            int c;
            while ((c = in.read()) != -1) {
                b.append((char) c);
            }

            in.close();

            return b.toString();

        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping(value = "/finalWebIFrame/{transactionID}")
    public ModelAndView finalProcesDeFirmaWebIFrame(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("transactionID") String transactionID) throws Exception {

        if (log.isDebugEnabled()) {
            log.debug("ENTRA A finalProcesDeFirmaWebIFrame[" + transactionID + "]");
        }

        ModelAndView mav = new ModelAndView("firmasimpleweb_iframe_final");
        mav.addObject("URL_FINAL", request.getContextPath() + getContextWeb() + "/finalWeb/" + transactionID);
        return mav;

    }

    @RequestMapping(value = "/finalWeb/{transactionID}")
    public ModelAndView finalProcesDeFirmaWeb(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("transactionID") String transactionID) throws Exception {

        ApiFirmaWebSimple api = null;
        try {
            api = ApiFirmaWebCache.getApiFirmaWebSimple(request);

            FirmaSimpleGetTransactionStatusResponse fullTransactionStatus;
            fullTransactionStatus = api.getTransactionStatus(transactionID);

            FirmaSimpleStatus transactionStatus = fullTransactionStatus.getTransactionStatus();

            int status = transactionStatus.getStatus();

            switch (status) {

                case FirmaSimpleStatus.STATUS_INITIALIZING: // = 0;
                    HtmlUtils.saveMessageError(request,
                            "S'ha rebut un estat inconsistent del proces de firma"
                                    + " (inicialitzant). Pot ser s'hagi produït un error en el Plugin de Firma."
                                    + " Consulti amb el seu administrador.");
                    return new ModelAndView(new RedirectView("/", true));

                case FirmaSimpleStatus.STATUS_IN_PROGRESS: // = 1;
                    HtmlUtils.saveMessageError(request,
                            "S'ha rebut un estat inconsistent del proces de firma"
                                    + " (en progrés). Pot ser s'hagi produït un error en el Plugin de Firma."
                                    + " Consulti amb el seu administrador.");
                    return new ModelAndView(new RedirectView("/", true));

                case FirmaSimpleStatus.STATUS_FINAL_ERROR: // = -1;
                {
                    String msg = "Error durant la realització de les firmes de la transacció " + transactionID + ": "
                            + transactionStatus.getErrorMessage();
                    String desc = transactionStatus.getErrorStackTrace();
                    if (desc != null) {
                        msg = msg + "<br/>" + desc;
                    }

                    log.error(msg);

                    HtmlUtils.saveMessageError(request, msg);

                    return new ModelAndView(new RedirectView("/", true));
                }

                case FirmaSimpleStatus.STATUS_CANCELLED: // = -2;
                {
                    String msg = "Durant el procés de firmes," + " l'usuari ha cancelat la transacció amb ID "
                            + transactionID + ".";
                    HtmlUtils.saveMessageWarning(request, msg);
                    log.error(msg);
                    return new ModelAndView(new RedirectView("/", true));
                }

                case FirmaSimpleStatus.STATUS_FINAL_OK: // = 2;
                {

                    Map<String, InfoGlobal> peticions = (Map<String, InfoGlobal>) request.getSession()
                            .getAttribute("peticions");

                    List<FirmaSimpleSignatureStatus> results = fullTransactionStatus.getSignaturesStatusList();

                    log.info(" ===== WEB PETICIONS [" + peticions + "] =========");
                    log.info(" ===== WEB RESULTATS [" + results.size() + "] =========");

                    for (FirmaSimpleSignatureStatus signatureStatus : results) {

                        String signID = signatureStatus.getSignID();

                        log.info(" ------ WEB SIGNID ]" + signID + "[ ==> " + signatureStatus.getStatus().getStatus());

                        InfoGlobal infoGlobal = peticions.get(signID);

                        FirmaSimpleSignatureResult fssr;
                        fssr = api.getSignatureResult(new FirmaSimpleGetSignatureResultRequest(transactionID, signID));

                        infoGlobal.setResultat(fssr);

                        if (fssr.getSignedFileInfo() != null) {
                            infoGlobal
                                    .setSignatureDetails(FirmaSimpleSignedFileInfo.toString(fssr.getSignedFileInfo()));
                        }

                    } // Final for de fitxers firmats

                    ModelAndView mav = new ModelAndView("firmaFinal");
                    return mav;

                } // Final Case Firma OK

            } // Final Switch Global

            HtmlUtils.saveMessageError(request, "No s'han pogut recuperar informació de"
                    + " resultats ja que segons el servidor la petició està Inicialitzant-se o En procés");
            return new ModelAndView(new RedirectView("/", true));

        } catch (Exception e) {

            String missatge = "Rebut un error intentant processar el resultat de la transacció de firma de fitxers: "
                    + e.getMessage();
            log.error(missatge, e);
            HtmlUtils.saveMessageError(request, missatge);
            return new ModelAndView(new RedirectView("/", true));

        } finally {
            try {

                if (api != null && transactionID != null) {
                    try {
                        api.closeTransaction(transactionID);
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }

            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

    }

    public ModelAndView finalProcesDeFirmaServer(HttpServletRequest request, HttpServletResponse response,
            List<FirmaSimpleSignatureResult> list, Map<String, InfoGlobal> peticions) throws Exception {

        for (FirmaSimpleSignatureResult firmaSimpleSignDocumentResponse : list) {

            FirmaSimpleStatus transactionStatus = firmaSimpleSignDocumentResponse.getStatus();

            int status = transactionStatus.getStatus();

            log.info(" =============================== ");
            log.info(" ------ SIGN ID ==> " + firmaSimpleSignDocumentResponse.getSignID());
            log.info(" ------ SIGN STATUS ==> " + transactionStatus.getStatus());

            switch (status) {

                case FirmaSimpleStatus.STATUS_INITIALIZING: // = 0;
                    if (transactionStatus.getErrorMessage() == null) {
                        // XYZ ZZZ Traduir
                        transactionStatus.setErrorMessage(
                                "Error desconegut ja que el proces de firma indica que està en un estat Initializing (???)");
                    }
                break;

                case FirmaSimpleStatus.STATUS_IN_PROGRESS: // = 1;
                    if (transactionStatus.getErrorMessage() == null) {
                        // XYZ ZZZ Traduir
                        transactionStatus.setErrorMessage(
                                "Error desconegut ja que el proces de firma indica que està en un estat EnProgres (???)");
                    }
                break;

                case FirmaSimpleStatus.STATUS_FINAL_ERROR: // = -1;

                    if (transactionStatus.getErrorMessage() == null) {
                        // XYZ ZZZ Traduir
                        transactionStatus.setErrorMessage("Error desconegut durant la realització de les firmes.");
                    }
                break;

                case FirmaSimpleStatus.STATUS_CANCELLED: // = -2;

                    if (transactionStatus.getErrorMessage() == null) {
                        // XYZ ZZZ Traduir
                        transactionStatus.setErrorMessage("S'ha cancelat el procés de firmes");
                    }

                break;

                case FirmaSimpleStatus.STATUS_FINAL_OK: // = 2;
                {

                    InfoGlobal infoGlobal = peticions.get(firmaSimpleSignDocumentResponse.getSignID());
                    infoGlobal.setResultat(firmaSimpleSignDocumentResponse);

                    if (firmaSimpleSignDocumentResponse.getSignedFileInfo() != null) {
                        infoGlobal.setSignatureDetails(FirmaSimpleSignedFileInfo
                                .toString(firmaSimpleSignDocumentResponse.getSignedFileInfo()));
                    }

                } // Final Case Firma OK
                break;

                default:

                    transactionStatus.setStatus(FirmaSimpleStatus.STATUS_FINAL_ERROR);
                    if (transactionStatus.getErrorMessage() == null) {
                        // XYZ ZZZ Traduir
                        transactionStatus.setErrorMessage(
                                "Estat desconegut (" + status + ")" + " despres del procés de firma en servidor");
                    }

            } // Final Switch Global

        } // Firma For

        request.getSession().setAttribute("peticions", peticions);

        ModelAndView mav = new ModelAndView("firmaFinal");
        return mav;

    }

    @RequestMapping(value = "/download/original/{signID}", method = RequestMethod.GET)
    public void downloadOriginal(@PathVariable("signID") String signID, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        Map<String, InfoGlobal> peticions = (Map<String, InfoGlobal>) request.getSession().getAttribute("peticions");

        InfoGlobal infoGlobal = peticions.get(signID);

        FirmaSimpleFile fsf = infoGlobal.getPeticio().getFileToSign();

        if (fsf == null) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            return;
        }

        String filename = fsf.getNom();

        {
            response.setContentType(fsf.getMime());

            response.setHeader("Content-Disposition", "inline; filename=\"" + filename + "\"");
            response.setContentLength((int) fsf.getData().length);

            java.io.OutputStream output = response.getOutputStream();

            IOUtils.copy(new ByteArrayInputStream(fsf.getData()), output);

            output.close();
        }
    }

    /**
     * Descàrrega del document firmat
     * 
     * @param id
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/download/signed/{signID}", method = RequestMethod.GET)
    public void downloadSigned(@PathVariable("signID") String signID, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        Map<String, InfoGlobal> peticions = (Map<String, InfoGlobal>) request.getSession().getAttribute("peticions");

        log.info("downloadSigned:: peticions = " + peticions);

        log.info("downloadSigned:: signID = |" + signID + "|");

        InfoGlobal infoGlobal = peticions.get(signID);

        log.info("downloadSigned:: infoGlobal = |" + infoGlobal + "|");

        FirmaSimpleFile fsf = infoGlobal.getResultat().getSignedFile();

        if (fsf == null) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            return;
        }

        String filename = fsf.getNom();

        {
            response.setContentType(fsf.getMime());

            response.setHeader("Content-Disposition", "inline; filename=\"" + filename + "\"");
            response.setContentLength((int) fsf.getData().length);

            java.io.OutputStream output = response.getOutputStream();

            IOUtils.copy(new ByteArrayInputStream(fsf.getData()), output);

            output.close();
        }

    }

    protected static String getAbsoluteURLBase(HttpServletRequest request) {

        return request.getScheme() + "://" + request.getServerName() + ":" + +request.getServerPort()
                + request.getContextPath();

    }

    public static String getRelativeURLBase(HttpServletRequest request) {
        return request.getContextPath();
    }

    protected static String getAbsoluteControllerBase(HttpServletRequest request, String webContext) {
        return getAbsoluteURLBase(request) + webContext;
    }

    public static String getRelativeControllerBase(HttpServletRequest request, String webContext) {
        return getRelativeURLBase(request) + webContext;
    }

}
