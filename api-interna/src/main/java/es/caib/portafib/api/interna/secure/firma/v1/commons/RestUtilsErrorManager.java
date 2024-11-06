package es.caib.portafib.api.interna.secure.firma.v1.commons;

import java.io.PrintWriter;
import java.io.StringWriter;

import es.caib.portafib.logic.generator.IdGeneratorFactory;

import org.fundaciobit.apisib.core.beans.ApisIBError;
import org.fundaciobit.apisib.core.exceptions.ApisIBServerException;
import org.fundaciobit.genapp.common.i18n.I18NException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * 
 * @author anadal
 * @author areus
 */
public class RestUtilsErrorManager extends RestUtils {

    public ResponseEntity<ApisIBError> generateServerError(String msg) {
        return generateServerError(msg, null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<ApisIBError> generateServerError(String msg, HttpStatus status) {
        return generateServerError(msg, null, status);
    }

    public ResponseEntity<ApisIBError> generateServerError(String msg, Throwable th) {
        return generateServerError(msg, th, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<ApisIBError> generateServerError(String msg, Throwable th, HttpStatus status) {
        String sStackTrace = null;
        if (th != null) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            th.printStackTrace(pw);
            sStackTrace = sw.toString();
        }

        return new ResponseEntity<ApisIBError>(new ApisIBError(msg, ApisIBServerException.class.getName(), sStackTrace),
                status);
    }
    
    protected String internalGetTransacction() {
        String transactionID = IdGeneratorFactory.getGenerator().generate();
        if (log.isDebugEnabled()) {
            log.debug("Creada transacció amb ID = [" + transactionID + "]");
        }
        return transactionID;
    }

}