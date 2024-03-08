package es.caib.portafib.api.interna.all.exemplepublic;

import java.util.Objects;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Exemple de Pojo
 *
 * @author anadal
 */
@Schema(name = "ExamplePojo")
public class ExemplePojo {

    private String versio;

    @JsonbCreator
    public ExemplePojo(@JsonbProperty("versio")
    String versio) {
        Objects.requireNonNull(versio, "versio no pot ser null");
        this.versio = versio;
    }

    public void setVersio(String versio) {
        this.versio = versio;
    }

    public String getVersio() {
        return versio;
    }

    @Override
    public String toString() {
        return "Versio: " + versio;
    }

}
