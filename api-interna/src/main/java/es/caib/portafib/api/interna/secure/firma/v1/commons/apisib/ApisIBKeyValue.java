package es.caib.portafib.api.interna.secure.firma.v1.commons.apisib;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Objecte per afegir informació addicional sobre la firma o el firmant")
public class ApisIBKeyValue {

    @Schema(description = "Clau del valor", required = true)
    protected String key;
    @Schema(description = "Valor de la clau", required = true)
    protected String value;

    /**
    * 
    */
    public ApisIBKeyValue() {
        super();
    }

    /**
     * @param key
     * @param value
     */
    public ApisIBKeyValue(String key, String value) {
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
