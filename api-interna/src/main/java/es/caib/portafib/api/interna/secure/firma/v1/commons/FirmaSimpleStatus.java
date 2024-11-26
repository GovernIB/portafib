package es.caib.portafib.api.interna.secure.firma.v1.commons;

import es.caib.portafib.commons.utils.Constants;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;

public class FirmaSimpleStatus {

    /*public enum STATUS {
    	  INITIALIZING (0),
    	  IN_PROGRESS (1),
    	  FINAL_OK (2),
    	  FINAL_ERROR (-1),
    	  CANCELLED (-2);
    	  
    	 public final int value;
    	 
    	 public int getValue() {
    		 return this.value;
    	 }
    	
    	  private STATUS (int i) {
    		  this.value=i;
    	  }
      }*/

    @Schema(
            description = "Codi d'estat d'una petició de firma que indica un que s'esta inicialitzant",
            nullable = false,
            defaultValue = "" + Constants.STATUS_INITIALIZING,
            implementation = Integer.class,
            required = true,
            accessMode = AccessMode.READ_ONLY)
    public final int STATUS_INITIALIZING = Constants.STATUS_INITIALIZING;

    @Schema(
            description = "Codi d'estat d'una petició de firma que indica un que esta en procés",
            nullable = false,
            defaultValue = "" + Constants.STATUS_IN_PROGRESS,
            implementation = Integer.class,
            required = true,
            accessMode = AccessMode.READ_ONLY)
    public final int STATUS_IN_PROGRESS = Constants.STATUS_IN_PROGRESS;

    @Schema(
            description = "Codi d'estat d'una petició de firma que indica que ha finalitzat correctament",
            nullable = false,
            defaultValue = "" + Constants.STATUS_FINAL_OK,
            implementation = Integer.class,
            required = true,
            accessMode = AccessMode.READ_ONLY)
    public final int STATUS_FINAL_OK = Constants.STATUS_FINAL_OK;

    @Schema(
            description = "Codi d'estat d'una petició de firma que indica que ha finalitzat amb errors",
            nullable = false,
            defaultValue = "" + Constants.STATUS_FINAL_ERROR,
            implementation = Integer.class,
            required = true,
            accessMode = AccessMode.READ_ONLY)
    public final int STATUS_FINAL_ERROR = Constants.STATUS_FINAL_ERROR;

    @Schema(
            description = "Codi d'estat d'una petició de firma que indica que ha sigut cancelada",
            nullable = false,
            defaultValue = "" + Constants.STATUS_CANCELLED,
            implementation = Integer.class,
            required = true,
            accessMode = AccessMode.READ_ONLY)
    public final int STATUS_CANCELLED = Constants.STATUS_CANCELLED;

    protected int status = STATUS_INITIALIZING;

    protected String errorMessage;

    protected String errorStackTrace;

    public FirmaSimpleStatus() {
        super();
    }

    public FirmaSimpleStatus(int status, String errorMessage, String errorStackTrace) {
        super();
        this.status = status;
        this.errorMessage = errorMessage;
        this.errorStackTrace = errorStackTrace;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
