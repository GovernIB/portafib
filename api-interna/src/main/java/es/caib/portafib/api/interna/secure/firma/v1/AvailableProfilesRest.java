package es.caib.portafib.api.interna.secure.firma.v1;

import java.util.ArrayList;
import java.util.List;

public class AvailableProfilesRest {
	
	public List<AvailableProfile> data;
	
	public String language;
	
	public AvailableProfilesRest() {
		this.data = new ArrayList<AvailableProfile>();
	}

    public AvailableProfilesRest(List<AvailableProfile> data, String language) {
        this.data=data;
        this.language=language;
    }
}
