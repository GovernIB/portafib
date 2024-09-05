package es.caib.portafib.api.interna.all.tipusdocumentals.v1;

import java.util.List;

public class LlistaTipusDocumentalRest {
	
	public List<TipusDocumentalRest> data;
	
	public String language;
	
	public LlistaTipusDocumentalRest() {
		
	}

    public LlistaTipusDocumentalRest(List<TipusDocumentalRest> data, String language) {
        this.data=data;
        this.language=language;
    }
}
