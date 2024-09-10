package es.caib.portafib.callback.beans.v1;

import java.math.BigInteger;

/**
 * 
 * @author anadal
 *
 */
public class Sign {
    
    private long iD;

    private BigInteger serialNumber;

    private String issuer;

    private String subject;

    public BigInteger getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(BigInteger serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public long getID() {
        return iD;
    }

    public void setID(long iD) {
        this.iD = iD;
    }

}
