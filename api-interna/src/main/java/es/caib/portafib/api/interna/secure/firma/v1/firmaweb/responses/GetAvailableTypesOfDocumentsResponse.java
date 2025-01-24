package es.caib.portafib.api.interna.secure.firma.v1.firmaweb.responses;

import java.util.List;

import es.caib.portafib.api.interna.secure.firma.v1.firmaweb.FirmaSimpleDocumentTypeInformation;

public class GetAvailableTypesOfDocumentsResponse {


    List<FirmaSimpleDocumentTypeInformation> getAvailableTypesOfDocumentsResponse;

    public GetAvailableTypesOfDocumentsResponse() {

    }

    public GetAvailableTypesOfDocumentsResponse(List<FirmaSimpleDocumentTypeInformation> getAvailableTypesOfDocumentsResponse) {
        this.getAvailableTypesOfDocumentsResponse = getAvailableTypesOfDocumentsResponse;
    }

    public List<FirmaSimpleDocumentTypeInformation> getGetAvailableTypesOfDocumentsResponse() {
        return getAvailableTypesOfDocumentsResponse;
    }

    public void setGetAvailableTypesOfDocumentsResponse(
            List<FirmaSimpleDocumentTypeInformation> getAvailableTypesOfDocumentsRequest) {
        this.getAvailableTypesOfDocumentsResponse = getAvailableTypesOfDocumentsRequest;
    }

   
}
