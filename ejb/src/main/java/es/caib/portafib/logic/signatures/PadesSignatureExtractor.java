package es.caib.portafib.logic.signatures;

import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.security.PdfPKCS7;
import es.caib.portafib.logic.utils.datasource.IPortaFIBDataSource;
import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pluginsib.core.v3.utils.CertificateUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

/**
 * Serveis per mostrar les firmes d'un document PDF en la vista completa de Destinatari
 * @author anadal
 * 14 oct 2024 13:27:04
 */
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
                // Sembla que IText no parseja bé el X509Certificate, per això obtenim el seus bytes i el recarregam
                byte[] certificateBytes = pk.getSigningCertificate().getEncoded();
                X509Certificate cert = CertificateUtils.decodeCertificate(new ByteArrayInputStream(certificateBytes));
                signatureList.add(signatureFactory.getSignature(cert, pk.getSignDate().getTime(), pk.isTsp()));
            }

            reader.close();

            return signatureList;

        } catch (IOException e) {
            log.error("Error obtenint signatures PADES", e);
            throw new I18NException("genapp.comodi",
                    "Error desconegut obtenint Certificats d'una firma PADES: " + e.getMessage());
        } catch (CertificateEncodingException e) {
            log.error("Error obtenint signatures PADES", e);
            throw new I18NException("genapp.comodi",
                    "Error desconegut parsejant Certificats d'una firma PADES: " + e.getMessage());
        } catch (Exception e) {
            log.error("Error obtenint signatures PADES", e);
            throw new I18NException("genapp.comodi",
                    "Error desconegut parsejant Certificats d'una firma PADES: " + e.getMessage());
        } finally {
            try {
                inputStream.close();
            } catch (IOException ignored) {}
        }
    }
}
