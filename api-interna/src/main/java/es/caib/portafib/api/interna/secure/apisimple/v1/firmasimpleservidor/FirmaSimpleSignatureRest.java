package es.caib.portafib.api.interna.secure.apisimple.v1.firmasimpleservidor;

import es.caib.portafib.api.interna.secure.apisimple.v1.commons.FirmaSimpleFile;
import es.caib.portafib.api.interna.secure.apisimple.v1.commons.FirmaSimpleSignedFileInfo;
import es.caib.portafib.api.interna.secure.apisimple.v1.commons.FirmaSimpleStatus;

public class FirmaSimpleSignatureRest {

	
	  protected String signID;

	  protected FirmaSimpleStatus status;

	  protected FirmaSimpleFile signedFile;

	  protected FirmaSimpleSignedFileInfo signedFileInfo;
	  
	  public FirmaSimpleSignatureRest() {
		    super();
		  }

		  public FirmaSimpleSignatureRest(String signID, FirmaSimpleStatus status,
		      FirmaSimpleFile signedFile, FirmaSimpleSignedFileInfo signedFileInfo) {
		    super();
		    this.signID = signID;
		    this.status = status;
		    this.signedFile = signedFile;
		    this.signedFileInfo = signedFileInfo;
		  }

		  public FirmaSimpleFile getSignedFile() {
		    return signedFile;
		  }

		  public void setSignedFile(FirmaSimpleFile signedFile) {
		    this.signedFile = signedFile;
		  }

		  public String getSignID() {
		    return signID;
		  }

		  public void setSignID(String signID) {
		    this.signID = signID;
		  }

		  public FirmaSimpleStatus getStatus() {
		    return status;
		  }

		  public void setStatus(FirmaSimpleStatus status) {
		    this.status = status;
		  }

		  public FirmaSimpleSignedFileInfo getSignedFileInfo() {
		    return signedFileInfo;
		  }

		  public void setSignedFileInfo(FirmaSimpleSignedFileInfo signedFileInfo) {
		    this.signedFileInfo = signedFileInfo;
		  }

}
