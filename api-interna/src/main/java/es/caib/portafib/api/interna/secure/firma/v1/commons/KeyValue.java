package es.caib.portafib.api.interna.secure.firma.v1.commons;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

@Schema(description = "Objecte per afegir informació addicional sobre la firma o el firmant")
public class KeyValue {

    @Schema(description = "Clau del valor",  requiredMode = RequiredMode.REQUIRED)
    protected String key;
    @Schema(description = "Valor de la clau",  requiredMode = RequiredMode.REQUIRED)
    protected String value;

    /**
    * 
    */
    public KeyValue() {
        super();
    }

    /**
     * @param key
     * @param value
     */
    public KeyValue(String key, String value) {
        super();
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
