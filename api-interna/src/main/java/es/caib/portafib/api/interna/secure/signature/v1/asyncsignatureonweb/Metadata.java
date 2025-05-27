package es.caib.portafib.api.interna.secure.signature.v1.asyncsignatureonweb;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

/**
 * 
 * @author anadal
 * 20 may 2025 14:36:30
 */
@Schema(description = "Conté informació i valor d'una Metadada") 
public class Metadata {

    @Schema(description = "Nom de la metadada",  requiredMode = RequiredMode.REQUIRED)
    protected String name;
    @Schema(description = "Valor de la metadada",  requiredMode = RequiredMode.REQUIRED)
    protected String value;
    @Schema(description = "Descripció de la metadada",  requiredMode = RequiredMode.NOT_REQUIRED)
    protected String description = null;
    @Schema(description = "Format del valor de la metadada. Per defecte es considera un Sring. Els valors disponibles són:\r\n"
            + "    • MetadataConstants.STRING.getValue() = 0; \r\n"
            + "    • MetadataConstants.INTEGER.getValue() = 1;\r\n"
            + "    • MetadataConstants. DECIMAL.getValue() = 2;\r\n"
            + "    • MetadataConstants.BOOLEAN.getValue() = 3;\r\n"
            + "    • MetadataConstants.BASE64.getValue() = 4;\r\n"
            + "    • MetadataConstants.DATE.getValue() = 5; // ISO8601",  requiredMode = RequiredMode.NOT_REQUIRED)
    protected int type = MetadataConstants.STRING.getValue();

    public Metadata() {
        super();
    }

    public Metadata(String name, String value) {
        super();
        this.name = name;
        this.value = value;
    }

    public Metadata(String name, String value, String description, int type) {
        super();
        this.name = name;
        this.value = value;
        this.description = description;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
