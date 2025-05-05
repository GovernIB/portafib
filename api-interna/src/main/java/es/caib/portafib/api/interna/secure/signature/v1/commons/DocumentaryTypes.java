package es.caib.portafib.api.interna.secure.signature.v1.commons;

import java.util.List;

/**
 * 
 * @author anadal
 * 2 may 2025 7:41:03
 */
public class DocumentaryTypes {

    List<DocumentaryType> documentaryTypes;

    public DocumentaryTypes() {
    }

    public DocumentaryTypes(List<DocumentaryType> documentaryTypes) {
        this.documentaryTypes = documentaryTypes;
    }

    public List<DocumentaryType> getDocumentaryTypes() {
        return documentaryTypes;
    }

    public void setDocumentaryTypes(List<DocumentaryType> documentaryTypes) {
        this.documentaryTypes = documentaryTypes;
    }

}
