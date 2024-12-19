package es.caib.portafib.api.interna.secure.firma.v1.commons;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

/**
 * 
 * @author anadal
 * 18 dic 2024 15:08:13
 */
@Schema(description = "Objecte que representa un Document/Fitxer")
public class FirmaSimpleFile {

    @Schema(description = "Nom del fitxer.", requiredMode = RequiredMode.REQUIRED)
    protected java.lang.String nom;
    @Schema(description = "Tipus mime del fitxer.",  requiredMode = RequiredMode.NOT_REQUIRED, type = "string")
    protected java.lang.String mime;

    @Schema(
            description = "Contingut del fitxer. En llistats aquest camp vendrà buit.",
             requiredMode = RequiredMode.NOT_REQUIRED,
            type = "string",
            format = "byte")
    protected byte[] data;

    public FirmaSimpleFile() {
        super();
    }

    /** Constructor amb tots els camps */
    public FirmaSimpleFile(java.lang.String nom, java.lang.String mime, byte[] data) {
        this.nom = nom;
        this.mime = mime;
        this.data = data;
    }

    public java.lang.String getNom() {
        return (nom);
    };

    public void setNom(java.lang.String _nom_) {
        this.nom = _nom_;
    };

    public java.lang.String getMime() {
        return (mime);
    };

    public void setMime(java.lang.String _mime_) {
        this.mime = _mime_;
    };

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

}