package es.caib.portafib.api.interna.all.exemplereutilitzacio;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

// TODO A modificar pel Desenvolupador. Adaptar a les dades que hagi de tornar.
@JsonPropertyOrder({ "itemID", "nom", "valor", "data" })
@Schema(description = "Model de dades de Informació bàsica a retornar.")
public class ExempleInfo {

    @Schema(requiredMode = RequiredMode.REQUIRED, description = "Identificador intern de l'objecte")
    @JsonProperty("itemID")
    protected long itemID;

    @Schema(requiredMode = RequiredMode.REQUIRED, description = "Nom de l'entrada")
    @JsonProperty("name")
    protected String nom;

    @Schema( requiredMode = RequiredMode.NOT_REQUIRED, description = "Valor de l'entrada")
    @JsonProperty("value")
    protected String valor;

    @Schema(
            requiredMode = RequiredMode.REQUIRED,
            description = "Data en que es va fer crear aquest element",
            type = "string",
            format = "date-time",
            pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("date")
    java.sql.Timestamp data;

    public ExempleInfo() {
        super();
    }

    public ExempleInfo(long itemID, String nom, String valor, Timestamp data) {
        super();
        this.itemID = itemID;
        this.nom = nom;
        this.valor = valor;
        this.data = data;
    }

    public long getItemID() {
        return itemID;
    }

    public void setItemID(long itemID) {
        this.itemID = itemID;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public java.sql.Timestamp getData() {
        return data;
    }

    public void setData(java.sql.Timestamp data) {
        this.data = data;
    }

}
