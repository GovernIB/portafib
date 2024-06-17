package es.caib.portafib.back.controller.common;

import java.io.IOException;
import java.io.InputStream;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pluginsib.signatureserver.api.ISignatureServerPlugin;
import org.fundaciobit.pluginsib.core.v3.utils.FileUtils;
import org.fundaciobit.pluginsib.utils.webutils.AbstractWebPlugin;
import org.springframework.stereotype.Controller;

import es.caib.portafib.logic.ModulDeFirmaServidorLogicaLocal;
import es.caib.portafib.logic.SegellDeTempsLogicaLocal;

/**
 *
 * La URL que processa aquest servlet esta definida dins la constant 
 * CONTEXTWEB_FOR_TIMESTAMP_GENERATOR_PER_FIRMA_EN_SERVIDOR de la classe SegellDeTempsLogicaLocal.
 * Si es modifica aquesta classe s'ha d'adaptar el web.xml 
 * @author anadal
 *
 */
@Controller
public class TimeStampGeneratorPerFirmaEnServidorServlet extends HttpServlet {

    protected Logger log = Logger.getLogger(getClass());

    @EJB(mappedName = ModulDeFirmaServidorLogicaLocal.JNDI_NAME)
    protected ModulDeFirmaServidorLogicaLocal modulDeFirmaEnServidorEjb;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processServlet(request, response, false);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processServlet(request, response, true);
    }

    protected void processServlet(HttpServletRequest request, HttpServletResponse response, boolean isPost)
            throws ServletException, IOException {

        final boolean debug = log.isDebugEnabled();

        if (debug) {
            log.debug(AbstractWebPlugin.servletRequestInfoToStr(request));
        }

        //                                {plugin}/signaturesSetID/signIndex
        // uri = /common/timestampgenerator/666/1466408733012148444/0
        String uri = request.getRequestURI();

        if (debug) {
            log.debug(" uri = " + uri);
        }

        // BASE = /common/timestampgenerator
        final String BASE = SegellDeTempsLogicaLocal.CONTEXTWEB_FOR_TIMESTAMP_GENERATOR_PER_FIRMA_EN_SERVIDOR;
        int index = uri.indexOf(BASE);

        if (index == -1) {
            String msg = "URL base incorrecte !!!! Esperat " + BASE + ". URI = " + uri;
            throw new IOException(msg);
        }

        // pluginAndSetIDAndSignID = 666/1466408733012148444/-1
        String pluginAndSetIDAndSignID = uri.substring(index + BASE.length() + 1);

        log.info("Petició de segellat de temps pluginAndSetIDAndSignID = " + pluginAndSetIDAndSignID);

        String[] split = pluginAndSetIDAndSignID.split("/");

        if (log.isDebugEnabled()) {
            for (int i = 0; i < split.length; i++) {
                log.info("SPLIT[" + i + "] = " + split[i]);
            }
        }

        long pluginID = Long.parseLong(split[0]);
        String signaturesSetID = split[1];
        int signatureIndex = Integer.parseInt(split[2]);

        ISignatureServerPlugin signaturePlugin;
        try {
            signaturePlugin = modulDeFirmaEnServidorEjb.getInstanceByPluginID(pluginID);
        } catch (I18NException e) {
            String msg = I18NUtils.tradueix("plugin.signatureweb.noexist", String.valueOf(pluginID));
            log.error(msg);
            response.sendError(HttpServletResponse.SC_NOT_FOUND, msg);
            return;
        }
        if (signaturePlugin == null) {
            String msg = I18NUtils.tradueix("plugin.signatureweb.noexist", String.valueOf(pluginID));
            log.error(msg);
            response.sendError(HttpServletResponse.SC_NOT_FOUND, msg);
            return;
        }

        InputStream is = request.getInputStream();
        byte[] inputRequest = FileUtils.toByteArray(is);

        byte[] timeStamp;
        try {
            timeStamp = signaturePlugin.generateTimeStamp(signaturesSetID, signatureIndex, inputRequest);

            if (timeStamp == null) {
                String msg = "El generador de TimeStamp per la petició " + signaturesSetID + "[" + signatureIndex
                        + "] ha retornat null";
                log.error(msg);
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            } else {
                response.getOutputStream().write(timeStamp);
            }

        } catch (Exception e) {
            String msg = "Error greu cridant el TimeStampGenerator per la petició " + signaturesSetID + "["
                    + signatureIndex + "]: " + e.getMessage();
            log.error(msg, e);
            response.sendError(HttpServletResponse.SC_NOT_FOUND);

        }
    }

}
