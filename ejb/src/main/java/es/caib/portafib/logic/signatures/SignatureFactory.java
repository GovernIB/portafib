package es.caib.portafib.logic.signatures;

import es.caib.portafib.logic.utils.DNIUtils;
import org.fundaciobit.pluginsib.core.v3.utils.CertificateUtils;

import java.security.cert.X509Certificate;
import java.util.Date;

public class SignatureFactory {

    private static final SignatureFactory INSTANCE = new SignatureFactory();

    private SignatureFactory() {}

    public static SignatureFactory getInstance() {
        return INSTANCE;
    }

    public Signature getSignature(X509Certificate certificate, Date date, Boolean isTimeStamp) {
        if (certificate == null) {
            throw new IllegalArgumentException("certificate is null");
        }

        Signature.Builder builder = new Signature.Builder()
                .signingTime(date)
                .isTimeStamp(isTimeStamp)
                .signerName(CertificateUtils.getSubjectCorrectName(certificate))
                .signerAdministrationId(DNIUtils.getDNI(certificate));

        try {
            String[] empresaNif = CertificateUtils.getEmpresaNIFNom(certificate);
            if (empresaNif != null) {
                builder.organizationAdministrationId(empresaNif[0])
                        .organizationName(empresaNif[1]);
            }
        } catch (Exception ignore) {}

        return builder.build();
    }
}
