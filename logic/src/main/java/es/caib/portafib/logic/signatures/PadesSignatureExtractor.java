package es.caib.portafib.logic.signatures;

import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.security.PdfPKCS7;
import es.caib.portafib.logic.utils.datasource.IPortaFIBDataSource;
import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;

import java.io.IOException;
import java.io.InputStream;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

public class PadesSignatureExtractor implements SignatureExtractor {

    private static final Logger log = Logger.getLogger(PadesSignatureExtractor.class);

    private final SignatureFactory signatureFactory = SignatureFactory.getInstance();

    @Override
    public List<Signature> extract(IPortaFIBDataSource source) throws I18NException {

        InputStream inputStream = source.getInputStream();
        try {
            PdfReader reader = new PdfReader(inputStream);
            AcroFields af = reader.getAcroFields();
            ArrayList<String> signatureNames = af.getSignatureNames();

            List<Signature> signatureList = new ArrayList<Signature>(signatureNames.size());

            for (String name : signatureNames) {
                PdfPKCS7 pk = af.verifySignature(name);
                X509Certificate cert = pk.getSigningCertificate();
                signatureList.add(signatureFactory.getSignature(cert, pk.getSignDate().getTime()));
            }

            reader.close();

            return signatureList;

        } catch (IOException e) {
            log.error("Error obtenint signatures PADES", e);
            throw new I18NException("genapp.comodi",
                    "Error desconegut obtenint Certificats d'una firma PADES: " + e.getMessage());
        } finally {
            try {
                inputStream.close();
            } catch (IOException ignored) {}
        }
    }
}
