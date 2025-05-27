package es.caib.portafib.api.interna.secure.signature.v1.asyncsignatureonweb;


import es.caib.portafib.api.interna.secure.signature.v1.commons.Document;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

/**
 * 
 * @author anadal
 * 16 may 2025 11:13:33
 */
@Schema(description = "Objecte per afegir una annex a la Petició de firma. Actualment només s'accepten combinacions de (attch=true i sign=true) o (attch=false i sign=false).")
public class Annex {

    @Schema(description = "Fitxer físic",  requiredMode = RequiredMode.REQUIRED)
    protected Document annex;
    @Schema(description = "Indica si s'ha d'adjuntar al PDF",  requiredMode = RequiredMode.REQUIRED)
    protected boolean attach;
    @Schema(description = "Indica si s'ha de signar també l'annex",  requiredMode = RequiredMode.REQUIRED)
    protected boolean sign;

    public Annex() {
        super();
    }

    public Annex(Document annex, boolean attach, boolean sign) {
        super();
        this.annex = annex;
        this.attach = attach;
        this.sign = sign;
    }

    public Document getAnnex() {
        return annex;
    }

    public void setAnnex(Document annex) {
        this.annex = annex;
    }

    public boolean isAttach() {
        return attach;
    }

    public void setAttach(boolean attach) {
        this.attach = attach;
    }

    public boolean isSign() {
        return sign;
    }

    public void setSign(boolean sign) {
        this.sign = sign;
    }

}
