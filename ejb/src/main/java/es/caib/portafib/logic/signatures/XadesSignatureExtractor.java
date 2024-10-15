package es.caib.portafib.logic.signatures;

import es.caib.portafib.logic.utils.datasource.IPortaFIBDataSource;
import org.apache.log4j.Logger;
import org.apache.xml.security.keys.KeyInfo;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class XadesSignatureExtractor implements SignatureExtractor {

    private static final Logger log = Logger.getLogger(XadesSignatureExtractor.class);

    private static final DocumentBuilderFactory DBF;

    static {
        DBF = DocumentBuilderFactory.newInstance();
        DBF.setNamespaceAware(true);

        org.apache.xml.security.Init.init();
    }

    private final SignatureFactory signatureFactory = SignatureFactory.getInstance();

    @Override
    public List<Signature> extract(IPortaFIBDataSource source) throws I18NException {

        InputStream inputStream = source.getInputStream();
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

            DocumentBuilder db = DBF.newDocumentBuilder();
            Document doc = db.parse(inputStream);

            NodeList nl = doc.getElementsByTagNameNS(XMLSignature.XMLNS, "Signature");

            List<Signature> signatures = new ArrayList<Signature>(nl.getLength());
            for (int i = 0; i < nl.getLength(); i++) {

                Element sigElement = (Element) nl.item(i);
                org.apache.xml.security.signature.XMLSignature signature =
                        new org.apache.xml.security.signature.XMLSignature(sigElement, "");

                // Intenta trobar l'element "xades:SigningTime" que ha d'estar dins un dels "ds:Object"
                Date signingTime = null;
                for (int j = 0; j < signature.getObjectLength(); j++) {
                    Element objectElement = signature.getObjectItem(j).getElement();
                    NodeList stNodeList = objectElement.getElementsByTagNameNS(
                            "http://uri.etsi.org/01903/v1.3.2#",
                            "SigningTime");

                    if (stNodeList.getLength() > 0) {
                        String value = stNodeList.item(0).getTextContent();
                        signingTime = dateFormat.parse(value);
                    }
                }

                // Obtención del certificado o clave pública de la firma
                KeyInfo keyInfo = signature.getKeyInfo();
                X509Certificate cert = keyInfo.getX509Certificate();
                if (cert == null) {
                    // No encontramos un Certificado intentamos obtener con la clave pública
                    PublicKey pk = keyInfo.getPublicKey();
                    CertificateFactory cf = CertificateFactory.getInstance("X509");
                    byte[] pubKeyAsBytes = pk.getEncoded();
                    cert = (X509Certificate) cf.generateCertificate(new ByteArrayInputStream(
                            pubKeyAsBytes));

                }
                signatures.add(signatureFactory.getSignature(cert, signingTime, null));
            }

            return signatures;

        } catch (SAXException e) {
            // no és un document XML
            return Collections.emptyList();

        } catch (Exception e) {
            log.error("Error obtenint signatures XADES", e);
            throw new I18NException("genapp.comodi",
                    "Error desconegut obtenint Certificats d'una firma XAdES: " + e.getMessage());
        } finally {
            try {
                inputStream.close();
            } catch (IOException ignored) {}
        }
    }

}
