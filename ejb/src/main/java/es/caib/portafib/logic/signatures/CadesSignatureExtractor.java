package es.caib.portafib.logic.signatures;

import es.caib.portafib.logic.utils.datasource.IPortaFIBDataSource;
import org.apache.log4j.Logger;
import org.bouncycastle.asn1.ASN1UTCTime;
import org.bouncycastle.asn1.cms.Attribute;
import org.bouncycastle.asn1.cms.CMSAttributes;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.SignerInformation;
import org.bouncycastle.cms.SignerInformationStore;
import org.bouncycastle.util.Store;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pluginsib.core.utils.CertificateUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

public class CadesSignatureExtractor implements SignatureExtractor {

    private static final Logger log = Logger.getLogger(CadesSignatureExtractor.class);

    private final SignatureFactory signatureFactory = SignatureFactory.getInstance();

    @Override
    //@SuppressWarnings("unchecked")
    public List<Signature> extract(IPortaFIBDataSource source) throws I18NException {

        InputStream inputStream = source.getInputStream();
        try {
            CMSSignedData cmsSignedData = getSignedData(inputStream);
            if (cmsSignedData == null) {
                return Collections.emptyList();
            }

            Store<X509CertificateHolder> certStore = cmsSignedData.getCertificates();
            SignerInformationStore signersStore = cmsSignedData.getSignerInfos();
            Collection<SignerInformation> signers = signersStore.getSigners();

            List<Signature> signatures = new ArrayList<Signature>(signers.size());
            for (SignerInformation signer : signers) {
                Collection<X509CertificateHolder> certCollection = certStore.getMatches(signer.getSID());
                X509CertificateHolder certHolder = certCollection.iterator().next();
                X509Certificate cert = CertificateUtils
                        .decodeCertificate(new ByteArrayInputStream(certHolder.getEncoded()));

                Attribute signingTime = signer.getSignedAttributes().get(CMSAttributes.signingTime);
                Date date = null;
                if (signingTime != null) {
                    Enumeration<?> en = signingTime.getAttrValues().getObjects();
                    while (en.hasMoreElements()) {
                        Object obj = en.nextElement();
                        if (obj instanceof ASN1UTCTime) {
                            ASN1UTCTime asn1Time = (ASN1UTCTime) obj;
                            date = asn1Time.getDate();
                            break;
                        }
                    }
                }
                signatures.add(signatureFactory.getSignature(cert, date));
            }

            return signatures;

        } catch (Exception e) {
            log.error("Error obtenint signatures CADES", e);
            throw new I18NException("genapp.comodi",
                    "Error desconegut obtenint Certificats d'una firma CAdES: " + e.getMessage());
        } finally {
            try {
                inputStream.close();
            } catch (IOException ignored) {
            }
        }
    }

    private CMSSignedData getSignedData(InputStream inputStream) {
        try {
            return new CMSSignedData(inputStream);
        } catch (CMSException e) {
            return null;
        }
    }

}
