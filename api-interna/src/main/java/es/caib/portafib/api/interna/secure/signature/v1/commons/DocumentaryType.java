package es.caib.portafib.api.interna.secure.signature.v1.commons;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

/**
 * 
 * @author anadal(u80067)
 *
 */
@Schema(description = "Model de dades d'un tipus documental")
public class DocumentaryType {
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
	protected Long documentTypeBase;

	/**
	 *
	 */
	public DocumentaryType() {
		super();
	}

	public DocumentaryType(long documentType, String name, Long documentTypeBase) {
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

	public Long getDocumentTypeBase() {
		return documentTypeBase;
	}

	public void setDocumentTypeBase(Long documentTypeBase) {
		this.documentTypeBase = documentTypeBase;
	}

}
