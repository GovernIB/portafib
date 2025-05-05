package es.caib.portafib.api.interna.secure.signature.v1.commons;

/**
 * Resultat d'una firma
 * 
 * @author anadal
 *
 */

public class SignatureStatus {

	protected String signID;

	protected ProcessStatus status;

	/**
	 * 
	 */
	public SignatureStatus() {
		super();
	}

	/**
	 * 
	 * @param status
	 * @param errorMessage
	 * @param errorStackTrace
	 * @param signID
	 * @param signedFile
	 */
	public SignatureStatus(String signID, ProcessStatus status) {
		super();
		this.signID = signID;
		this.status = status;
	}

	public ProcessStatus getStatus() {
		return status;
	}

	public void setStatus(ProcessStatus status) {
		this.status = status;
	}

	public String getSignID() {
		return signID;
	}

	public void setSignID(String signID) {
		this.signID = signID;
	}

}
