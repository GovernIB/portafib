package es.caib.portafib.api.interna.secure.firma.v1;

import java.util.List;

import es.caib.portafib.api.interna.secure.apisimple.v1.apisib.ApisIBKeyValue;


public class AvailableLanguagesRest {
	
	public List<ApisIBKeyValue> list;

	public List<ApisIBKeyValue> getList() {
		return list;
	}

	public void setList(List<ApisIBKeyValue> list) {
		this.list = list;
	}
	
	
}
