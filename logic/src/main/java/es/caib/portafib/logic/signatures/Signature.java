package es.caib.portafib.logic.signatures;

import java.security.cert.X509Certificate;
import java.util.Date;

public class Signature {

    private final X509Certificate certificate;
    private final String signerName;
    private final String signerAdministrationId;
    private final Date signingTime;

    Signature(X509Certificate certificate, String signerName, String signerAdministrationId, Date signingTime) {
        this.certificate = certificate;
        this.signerName = signerName;
        this.signerAdministrationId = signerAdministrationId;
        this.signingTime = signingTime;
    }

    public X509Certificate getCertificate() {
        return certificate;
    }

    public String getSignerName() {
        return signerName;
    }

    public String getSignerAdministrationId() {
        return signerAdministrationId;
    }

    public Date getSigningTime() {
        return signingTime;
    }

    @Override
    public String toString() {
        return "Signature{" +
                "signerName='" + signerName + '\'' +
                ", signerAdministrationId='" + signerAdministrationId + '\'' +
                ", signingTime=" + signingTime +
                '}';
    }
}
