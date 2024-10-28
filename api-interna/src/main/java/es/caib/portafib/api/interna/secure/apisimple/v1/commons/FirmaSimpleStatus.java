package es.caib.portafib.api.interna.secure.apisimple.v1.commons;

import es.caib.portafib.api.interna.secure.apisimple.v1.apisib.ApisIBStatus;
import io.swagger.v3.oas.annotations.media.Schema;

public class FirmaSimpleStatus{
		
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
	
		
	  public static final int STATUS_INITIALIZING = 0;
	  
	  public static final int STATUS_IN_PROGRESS = 1;

	  public static final int STATUS_FINAL_OK = 2;
	  
	  public static final int STATUS_FINAL_ERROR = -1;
	  
	  public static final int STATUS_CANCELLED = -2;
	  
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


