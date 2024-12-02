package es.caib.portafib.api.interna.secure.firma.v1.utils;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resposta per la petició de llistats de tipus documentals.")
public class LlistaTipusDocumentalRest {
    
    @Schema(description = "Llistat de tipus documentals disponibles")
	public List<TipusDocumentalRest> data;
    
    @Schema(description = "Llenguatge seleccionat per la resposta")
	public String language;
    
	public LlistaTipusDocumentalRest() {

	}

	public LlistaTipusDocumentalRest(List<TipusDocumentalRest> data, String language) {
		this.data = data;
		this.language = language;
	}
}
