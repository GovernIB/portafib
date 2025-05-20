package es.caib.portafib.api.interna.secure.signature.v1.asyncsignatureonweb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import es.caib.portafib.api.interna.secure.signature.v1.commons.Document;

/**
 * 
 * @author anadal
 * 16 may 2025 11:13:33
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Annex {

    protected Document annex;
    protected boolean attach;
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
