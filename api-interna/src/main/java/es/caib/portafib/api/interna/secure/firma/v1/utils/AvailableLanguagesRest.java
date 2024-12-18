package es.caib.portafib.api.interna.secure.firma.v1.utils;

import java.util.List;

import es.caib.portafib.api.interna.secure.firma.v1.commons.KeyValue;

public class AvailableLanguagesRest {

    public List<KeyValue> list;

    public List<KeyValue> getList() {
        return list;
    }

    public void setList(List<KeyValue> list) {
        this.list = list;
    }

}
