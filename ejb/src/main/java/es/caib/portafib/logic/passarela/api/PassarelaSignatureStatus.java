package es.caib.portafib.logic.passarela.api;

import org.fundaciobit.pluginsib.signature.api.StatusSignature;

/**
 * 
 * 
 * @author anadal
 *
 */
public class PassarelaSignatureStatus {

    /**
     * Status de la signatura. Els possibles valors són els definits a StatusSignature:
     * - StatusSignature.STATUS_INITIALIZING: La signatura s'està inicialitzant, encara no s'ha iniciat el procés de signatura.
     * - StatusSignature.STATUS_SIGNING: La signatura està en procés de signatura, s'està esperant a que el plugin de signatura retorni el resultat de la signatura.
     * - StatusSignature.STATUS_SIGNED: La signatura s'ha completat correctament, el plugin de signatura ha retornat un resultat de signatura amb èxit.
     * - StatusSignature.STATUS_ERROR: La signatura ha fallat, el plugin de signatura ha retornat un resultat de signatura amb error o s'ha produït una excepció durant el procés de signatura.
     * 
     */
    protected int status = StatusSignature.STATUS_INITIALIZING;

    /** En cas d'error, codi d'error encara que fins i tot en el cas d'error normalment valdrà null.
        Els valors d'aquest camp són enviats pels diferents plugins de firma, per tant poden variar
        molt en funció del plugin que s'hagi utilitzat. Per a més informació sobre els valors d'aquest
       camp, consultar la documentació del plugin de firma que s'hagi utilitzat.
    */
    // Estudiar la possibilitat de retornar CODI d'Error en API Firma Síncrona     #1157
    protected String errorCode;

    /** En cas d'error, missatge d'error */
    protected String errorMessage;

    /** En cas d'error, stack trace de l'error en format String */
    protected String errorStackTrace;

    /**
     * 
     */
    public PassarelaSignatureStatus() {
        super();
    }

    public PassarelaSignatureStatus(int status, String errorCode, String errorMessage, String errorStackTrace) {
        super();
        this.status = status;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorStackTrace = errorStackTrace;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorStackTrace() {
        return errorStackTrace;
    }

    public void setErrorStackTrace(String errorStackTrace) {
        this.errorStackTrace = errorStackTrace;
    }

}
