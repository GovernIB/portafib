package es.caib.portafib.api.interna.secure.apisimple.v1;

public class TipusDocumentalRest {
	protected long tipusDocumentID;

    protected String nom;

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
