package es.caib.portafib.api.interna.secure.firma.v1.commons;

import java.util.List;

import es.caib.portafib.api.interna.secure.firma.v1.firmaenservidor.FirmaSimpleSignatureResponse;

/**
 * 
 * @author anadal
 *
 */
public class FirmaSimpleSignDocumentsResponse {

	protected FirmaSimpleStatus statusSignatureProcess;

	protected List<FirmaSimpleSignatureResponse> results;

	/**
	 * 
	 */
	public FirmaSimpleSignDocumentsResponse() {
		super();
	}

	/**
	 * @param results
	 */
	public FirmaSimpleSignDocumentsResponse(FirmaSimpleStatus statusSignatureProcess,
			List<FirmaSimpleSignatureResponse> results) {
		super();
		this.statusSignatureProcess = statusSignatureProcess;
		this.results = results;
	}

	public List<FirmaSimpleSignatureResponse> getResults() {
		return results;
	}

	public void setResults(List<FirmaSimpleSignatureResponse> results) {
		this.results = results;
	}

	public FirmaSimpleStatus getStatusSignatureProcess() {
		return statusSignatureProcess;
	}

	public void setStatusSignatureProcess(FirmaSimpleStatus statusSignatureProcess) {
		this.statusSignatureProcess = statusSignatureProcess;
	}

}