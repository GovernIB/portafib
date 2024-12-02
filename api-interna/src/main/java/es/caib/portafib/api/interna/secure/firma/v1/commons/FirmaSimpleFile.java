package es.caib.portafib.api.interna.secure.firma.v1.commons;

import es.caib.portafib.api.interna.secure.firma.v1.commons.apisib.ApisIBFile;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Document a signar")
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