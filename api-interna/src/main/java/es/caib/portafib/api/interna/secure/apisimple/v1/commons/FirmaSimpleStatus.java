package es.caib.portafib.api.interna.secure.apisimple.v1.commons;


import es.caib.portafib.api.interna.secure.apisimple.v1.apisib.ApisIBStatus;

public class FirmaSimpleStatus extends ApisIBStatus {

	  public FirmaSimpleStatus() {
	    super();
	  }

	  public FirmaSimpleStatus(int status, String errorMessage, String errorStackTrace) {
	    super(status, errorMessage, errorStackTrace);
	  }

	}
