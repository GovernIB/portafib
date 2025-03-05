package es.caib.portafib.back.controller.rest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;

/**
 * 
 * @author anadal(u80067)
 *
 */
public class RestUtils {

    protected final Logger log = Logger.getLogger(getClass());

    public HttpHeaders addAccessControllAllowOrigin() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        return headers;
    }

}
