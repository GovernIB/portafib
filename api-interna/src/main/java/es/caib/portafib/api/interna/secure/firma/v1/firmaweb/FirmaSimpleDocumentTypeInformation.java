package es.caib.portafib.api.interna.secure.firma.v1.firmaweb;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

/**
 * 
 * @author anadal(u80067)
 *
 */

public class FirmaSimpleDocumentTypeInformation {
    @Schema(
            description = "Identificador de Tipus Documental",
            example = "",
            requiredMode = RequiredMode.REQUIRED)
	protected long documentType;
    
    @Schema(
            description = "Nom del Tipus Documental",
            example = "",
            requiredMode = RequiredMode.REQUIRED)
	protected String name;
    
    @Schema(
            description = "Identificador de Tipus Documental associat als valors ENI",
            example = "",
            requiredMode = RequiredMode.REQUIRED)
	protected long documentTypeBase;

	/**
	 *
	 */
	public FirmaSimpleDocumentTypeInformation() {
		super();
	}

	public FirmaSimpleDocumentTypeInformation(long documentType, String name, long documentTypeBase) {
		super();
		this.documentType = documentType;
		this.name = name;
		this.documentTypeBase = documentTypeBase;
	}

	public long getDocumentType() {
		return documentType;
	}

	public void setDocumentType(long documentType) {
		this.documentType = documentType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getDocumentTypeBase() {
		return documentTypeBase;
	}

	public void setDocumentTypeBase(long documentTypeBase) {
		this.documentTypeBase = documentTypeBase;
	}

}
