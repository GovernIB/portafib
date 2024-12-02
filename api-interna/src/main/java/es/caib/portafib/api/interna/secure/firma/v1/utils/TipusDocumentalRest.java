package es.caib.portafib.api.interna.secure.firma.v1.utils;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Model de dades d'un tipus documental")
public class TipusDocumentalRest {
    @Schema(description = "Identificador del tipus documental")
    protected long tipusDocumentID;

    @Schema(description = "Nom del tipus documental en l'idioma seleccionat")
    protected String nom;

    @Schema(description = "Codi NTI del tipus documental")
    protected Long tipusDocumentNTI;

    public TipusDocumentalRest() {
        super();
    }

    public TipusDocumentalRest(long tipusDocumentID, String nom, Long tipusDocumentNTI) {
        super();
        this.tipusDocumentID = tipusDocumentID;
        this.nom = nom;
        this.tipusDocumentNTI = tipusDocumentNTI;
    }

    public long getTipusDocumentID() {
        return tipusDocumentID;
    }

    public void setTipusDocumentID(long tipusDocumentID) {
        this.tipusDocumentID = tipusDocumentID;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Long getTipusDocumentNTI() {
        return tipusDocumentNTI;
    }

    public void setTipusDocumentNTI(Long tipusDocumentNTI) {
        this.tipusDocumentNTI = tipusDocumentNTI;
    }

}
