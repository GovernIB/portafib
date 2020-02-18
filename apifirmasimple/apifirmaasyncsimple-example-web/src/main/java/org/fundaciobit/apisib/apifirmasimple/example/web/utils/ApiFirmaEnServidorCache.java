package org.fundaciobit.apisib.apifirmasimple.example.web.utils;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.fundaciobit.apisib.apifirmasimple.v1.ApiFirmaEnServidorSimple;
import org.fundaciobit.apisib.apifirmasimple.v1.jersey.ApiFirmaEnServidorSimpleJersey;
import org.fundaciobit.apisib.core.exceptions.AbstractApisIBException;

/**
 * 
 * @author anadal
 *
 */
public class ApiFirmaEnServidorCache {

    protected static final Logger log = Logger.getLogger(ApiFirmaEnServidorCache.class);

    public static final String BASE_PACKAGE = "apifirmasimple.exempleweb.signature_server.";

    protected static final String API_FIRMA_EN_SERVIDOR_CACHE_SESSION = "API_FIRMA_EN_SERVIDOR_CACHE_SESSION";

    public static ApiFirmaEnServidorSimple getApiFirmaEnServidorSimple(HttpServletRequest request) {

        ApiFirmaEnServidorSimple api = (ApiFirmaEnServidorSimple) request.getSession()
                .getAttribute(API_FIRMA_EN_SERVIDOR_CACHE_SESSION);

        if (api != null) {
            return api;
        }

        Properties prop = System.getProperties();

        String endPoint = prop.getProperty(BASE_PACKAGE + "endpoint");
        String username = prop.getProperty(BASE_PACKAGE + "username");
        String password = prop.getProperty(BASE_PACKAGE + "password");

        if (endPoint == null || username == null || password == null) {
            return null;
        } else {
            log.info("\n Web (firma web) => \n" + endPoint);
            api = setApiFirmaEnServidorSimple(request, endPoint, username, password);
            return api;
        }
    }

    public static ApiFirmaEnServidorSimple setApiFirmaEnServidorSimple(HttpServletRequest request, String endPoint,
            String username, String password) {

        ApiFirmaEnServidorSimple api = new ApiFirmaEnServidorSimpleJersey(endPoint, username, password);

        String languageUI = "ca";
        try {
            api.getAvailableProfiles(languageUI);

            request.getSession().setAttribute(API_FIRMA_EN_SERVIDOR_CACHE_SESSION, api);

            return api;
        } catch (AbstractApisIBException e) {
            String msg = "Aquesta configuració no és correcte: " + e.getMessage();

            log.error(msg, e);

            HtmlUtils.saveMessageError(request, msg);
            return null;
        }

    }

}
