package es.caib.portafib.api.interna.secure.firma.v1.Utils;

import java.util.List;

public class LlistaTipusDocumentalRest {

	public List<TipusDocumentalRest> data;

	public String language;

	public LlistaTipusDocumentalRest() {

	}

	public LlistaTipusDocumentalRest(List<TipusDocumentalRest> data, String language) {
		this.data = data;
		this.language = language;
	}
}
