package es.caib.portafib.api.interna.all.infoversion;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author anadal
 */
@JsonPropertyOrder({ "versio", "caib" })
@Schema(description = "Model de dades de Informació bàsica a retornar.")
public class InfoVersion {

    @Schema(required = true, description = "Versió")
    @JsonProperty("version")
    private String version;

    @Schema(required = true, description = "Data compilació")
    @JsonProperty("buildTime")
    private String buildTime;

    @Schema(required = true, description = "Versió de JDK en que s'ha compilat")
    @JsonProperty("jdkVersion")
    private String jdkVersion;

    @Schema(required = true, description = "És un servidor de la CAIB")
    @JsonProperty("caib")
    private boolean caib;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public boolean isCaib() {
        return caib;
    }

    public void setCaib(boolean caib) {
        this.caib = caib;
    }

    public String getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(String buildTime) {
        this.buildTime = buildTime;
    }

    public String getJdkVersion() {
        return jdkVersion;
    }

    public void setJdkVersion(String jdkVersion) {
        this.jdkVersion = jdkVersion;
    }

}
