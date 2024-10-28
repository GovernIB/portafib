package es.caib.portafib.api.interna.secure.apisimple.v1.utils;

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
