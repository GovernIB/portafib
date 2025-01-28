package es.caib.portafib.back.controller.common;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Calendar;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.bouncycastle.tsp.TimeStampRequest;
import org.bouncycastle.tsp.TimeStampToken;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pluginsib.signatureserver.api.ISignatureServerPlugin;
import org.fundaciobit.pluginsib.timestamp.api.ITimeStampPlugin;
import org.fundaciobit.pluginsib.core.v3.utils.FileUtils;
import org.fundaciobit.pluginsib.signature.api.FileInfoSignature;
import org.fundaciobit.pluginsib.signature.api.ITimeStampGenerator;
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

    //@EJB(mappedName = ModulDeFirmaServidorLogicaLocal.JNDI_NAME)
    //protected ModulDeFirmaServidorLogicaLocal modulDeFirmaEnServidorEjb;

    @EJB(mappedName = SegellDeTempsLogicaLocal.JNDI_NAME)
    protected SegellDeTempsLogicaLocal modulDeSegellDeTempsEjb;

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

        //                              {plugin}/signaturesSetID/signIndex
        // uri = /common/timestampgenerator/666/1466408733012144/0
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

        final long pluginID = Long.parseLong(split[0]);
        final String signaturesSetID = split[1];
        final int signatureIndex = Integer.parseInt(split[2]);

        ITimeStampPlugin segellDeTempsPlugin;
        try {
            segellDeTempsPlugin = modulDeSegellDeTempsEjb.getInstanceByPluginID(pluginID);
        } catch (I18NException e) {
            String msg = I18NUtils.tradueix("plugin.segelldetemps.noexist", String.valueOf(pluginID));
            log.error(msg);
            response.sendError(HttpServletResponse.SC_NOT_FOUND, msg);
            return;
        }
        if (segellDeTempsPlugin == null) {
            String msg = I18NUtils.tradueix("plugin.segelldetemps.noexist", String.valueOf(pluginID));
            log.error(msg);
            response.sendError(HttpServletResponse.SC_NOT_FOUND, msg);
            return;
        }

        InputStream is = request.getInputStream();
        byte[] inputRequest = FileUtils.toByteArray(is);

        TimeStampRequest tsr = new TimeStampRequest(inputRequest);

        byte[] inputData = tsr.getMessageImprintDigest();

        BigInteger time = tsr.getNonce();

        try {
            final Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(time.longValue());
            byte[] returnedData = segellDeTempsPlugin.getTimeStampDirect(inputData, cal);

            if (returnedData != null) {
                log.info("requestTimeStamp:: returnedData LEN= " + returnedData.length);
                log.info("requestTimeStamp:: returnedData DATA= " + new String(returnedData));
                response.getOutputStream().write(returnedData);
            } else {
                String msg = "El generador de TimeStamp per la petició " + signaturesSetID + "[" + signatureIndex
                        + "] ha retornat null";
                log.error(msg, new Exception());
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }

        } catch (Exception e) {
            log.error("Error greu cridant el TimeStampGenerator: " + e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }

        /*
        try {
            TimeStampRequest tsr = new TimeStampRequest(inputRequest);
        
            BigInteger time = tsr.getNonce();
        
            final Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(time.longValue());
            // 0 No ha funcionat
            // byte[] inputData = tsr.getMessageImprintDigest();
            // byte[] timeStamp = segellDeTempsPlugin.getTimeStampDirect(inputData, cal);  
        
            //  0No ha funcionat
            // byte[] inputData = tsr.getEncoded();
            // byte[] timeStamp = segellDeTempsPlugin.getTimeStampDirect(inputData, cal);  
        
            
        
            // 1 No ha funcionat
            //byte[] inputData = tsr.getMessageImprintDigest();
            //TimeStampToken tst = segellDeTempsPlugin.getTimeStamp(inputData, cal);
            //byte[] timeStamp = tst.getEncoded();
            
            // 1 No ha funcionat
            //byte[] inputData = tsr.getEncoded();
            //TimeStampToken tst = segellDeTempsPlugin.getTimeStamp(inputData, cal);
            //byte[] timeStamp = tst.getEncoded();
            
            // 2 No ha funcionat
            //byte[] inputData = tsr.getMessageImprintDigest();
            //TimeStampToken tst = segellDeTempsPlugin.getTimeStamp(inputData, cal);
            //byte[] timeStamp = tst.getTimeStampInfo().getMessageImprintDigest();
            
            
            // 2 No ha funcionat
            //byte[] inputData = tsr.getEncoded();
            //TimeStampToken tst = segellDeTempsPlugin.getTimeStamp(inputData, cal);
            //byte[] timeStamp = tst.getTimeStampInfo().getMessageImprintDigest();
            
            // 3 No ha funcionat
            //byte[] inputData = tsr.getMessageImprintDigest();
            //TimeStampToken tst = segellDeTempsPlugin.getTimeStamp(inputData, cal);
            //byte[] timeStamp = tst.getTimeStampInfo().getEncoded();
            
            // 3 No ha funcionat
            byte[] inputData = tsr.getEncoded();
            TimeStampToken tst = segellDeTempsPlugin.getTimeStamp(inputData, cal);
            byte[] timeStamp = tst.getTimeStampInfo().getEncoded();
        
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
        */

        /*
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
        */
    }

}
