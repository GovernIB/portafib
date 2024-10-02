package es.caib.portafib.api.interna.secure.apisimple.v1.commons;

import es.caib.portafib.api.interna.secure.apisimple.v1.apisib.ApisIBFile;

public class FirmaSimpleFile extends ApisIBFile {

	  public FirmaSimpleFile() {
	    super();
	  }

	  public FirmaSimpleFile(ApisIBFile apisibfile) {
	    super(apisibfile);
	  }

	  public FirmaSimpleFile(String nom, String mime, byte[] data) {
	    super(nom, mime, data);
	  }

	}