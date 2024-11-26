package es.caib.portafib.api.interna.secure.firma.v1.utils;

import java.util.List;

import es.caib.portafib.api.interna.secure.firma.v1.commons.apisib.ApisIBKeyValue;


public class AvailableLanguagesRest {

	public List<ApisIBKeyValue> list;

	public List<ApisIBKeyValue> getList() {
		return list;
	}

	public void setList(List<ApisIBKeyValue> list) {
		this.list = list;
	}

}
