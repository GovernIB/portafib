package es.caib.portafib.rest.controller;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.apisib.core.beans.ApisIBError;
import org.jboss.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author anadal
 * 27 may 2025 10:58:55
 */
@ControllerAdvice
@RestController
public class RestExceptionHandler {
    protected Logger log = Logger.getLogger(RestExceptionHandler.class);

    @RequestMapping("/error")
    @ResponseBody
    public ResponseEntity<ApisIBError> handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Throwable ex = (Exception) request.getAttribute("javax.servlet.error.exception");
        String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");

        if (ex instanceof org.springframework.web.util.NestedServletException) {
            ex = (Exception) ((org.springframework.web.util.NestedServletException) ex).getRootCause();
        }

        //log.error("\n\n\n  ====  ENTRA A @RequestMapping(\"/error\") ==== \n\n\n");

        String msg = "Error no controlat cridant a un servei REST(" + requestUri + "): " + ex.getMessage() + "\n";

        log.error(msg, ex);

        return RestUtilsErrorManager.generateServerError(msg, ex,
                statusCode != null ? HttpStatus.valueOf(statusCode) : HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
