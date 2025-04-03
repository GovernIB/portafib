package es.caib.portafib.back.utils;

import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 * 
 * @author anadal
 *
 */
public class PortaFIBMaxUploadSizeExceededException extends MaxUploadSizeExceededException {

    final String msgCode;

    final String redirectTo;

    /**
     * @param msg
     */
    public PortaFIBMaxUploadSizeExceededException(Throwable cause, long maxSize, String msgCode, String redirectTo) {
        super(maxSize, cause);
        this.msgCode = msgCode;
        this.redirectTo = redirectTo;
    }

    public String getMsgCode() {
        return msgCode;
    }

    public String getRedirectTo() {
        return redirectTo;
    }

}
