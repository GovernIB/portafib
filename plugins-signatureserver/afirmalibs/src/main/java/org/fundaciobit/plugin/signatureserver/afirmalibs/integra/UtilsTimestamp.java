package org.fundaciobit.plugin.signatureserver.afirmalibs.integra;

import org.apache.log4j.Logger;

/*
import es.gob.afirma.signature.SigningException;
import es.gob.afirma.transformers.TransformersException;
import es.gob.afirma.transformers.TransformersFacade;
import es.gob.afirma.tsaServiceInvoker.TSAServiceInvokerException;
import es.gob.afirma.tsaServiceInvoker.TSAServiceInvokerFacade;
import es.gob.afirma.utils.CryptoUtil;
import es.gob.afirma.utils.KeyValueSelector;
*/
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.crypto.KeySelector;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.XMLValidateContext;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerFactoryConfigurationError;

/*
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.apache.xml.security.c14n.Canonicalizer;
*/
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.DERObjectIdentifier;
/*
import org.bouncycastle.asn1.DEREncodable;
import org.bouncycastle.asn1.DERObject;
import org.bouncycastle.asn1.DERObjectIdentifier;
*/
import org.bouncycastle.asn1.cms.Attribute;
import org.bouncycastle.asn1.cms.AttributeTable;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.tsp.MessageImprint;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.SignerInformation;
import org.bouncycastle.cms.SignerInformationVerifier;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentVerifierProvider;
import org.bouncycastle.operator.DigestCalculatorProvider;
import org.bouncycastle.operator.jcajce.JcaContentVerifierProviderBuilder;
import org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder;
import org.bouncycastle.tsp.TSPException;
import org.bouncycastle.tsp.TSPValidationException;
import org.bouncycastle.tsp.TimeStampToken;
import org.bouncycastle.tsp.TimeStampTokenInfo;
import org.bouncycastle.util.encoders.Base64;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


public class UtilsTimestamp {

  public static Logger LOGGER = Logger.getLogger(UtilsTimestamp.class);
  
  
  public static TimeStampToken getTimeStampToken(SignerInformation signerInformation) throws Exception {
    LOGGER.info("TSU044: inici");
    try {
        if (signerInformation == null) {
            String errorMsg = "TSU025: signerInformation == null ";
            LOGGER.error((Object)errorMsg);
            throw new IllegalArgumentException(errorMsg);
        }
        AttributeTable unsignedAttributes = signerInformation.getUnsignedAttributes();
        if (unsignedAttributes != null && unsignedAttributes.get((DERObjectIdentifier)PKCSObjectIdentifiers.id_aa_signatureTimeStampToken) != null) {
            Attribute attributeTimeStampToken = unsignedAttributes.get((DERObjectIdentifier)PKCSObjectIdentifiers.id_aa_signatureTimeStampToken);
            try {
                //CMSSignedData csd =  new CMSSignedData(attributeTimeStampToken.getAttrValues().getObjectAt(0).getDERObject().getDEREncoded());
                CMSSignedData csd =  new CMSSignedData(attributeTimeStampToken.getAttrValues().getObjectAt(0).toASN1Primitive().getEncoded());
                    //getDERObject().getDEREncoded());
                TimeStampToken timeStampToken = new TimeStampToken(csd);
                return timeStampToken;
            } catch (Exception e) {
                String errorMsg = "TSU026: " + e.getMessage();
                LOGGER.error((Object)errorMsg);
                throw new Exception(errorMsg, e);
            }
        }
        TimeStampToken attributeTimeStampToken = null;
        return attributeTimeStampToken;
    }
    finally {
        LOGGER.info("TSU045: Final");
    }
}
  
  
  
  /*
  public static void validateASN1Timestamp(TimeStampToken tst) throws SigningException {
    LOGGER.info((Object)Language.getResIntegra("TSU042"));
    try {
        if (tst == null) {
            String errorMsg = Language.getResIntegra("TSU014");
            LOGGER.error((Object)errorMsg);
            throw new IllegalArgumentException(errorMsg);
        }
        X509Certificate signingCertificate = UtilsTimestamp.getSigningCertificate(tst);
        LOGGER.info((Object)Language.getResIntegra("TSU021"));
        try {
            JcaContentVerifierProviderBuilder jcaContentVerifierProviderBuilder = new JcaContentVerifierProviderBuilder();
            jcaContentVerifierProviderBuilder.setProvider(BouncyCastleProvider.PROVIDER_NAME);
            ContentVerifierProvider contentVerifierProvider = jcaContentVerifierProviderBuilder.build(signingCertificate);
            JcaDigestCalculatorProviderBuilder digestCalculatorProviderBuilder = new JcaDigestCalculatorProviderBuilder();
            digestCalculatorProviderBuilder.setProvider(BouncyCastleProvider.PROVIDER_NAME);
            DigestCalculatorProvider digestCalculatorProvider = digestCalculatorProviderBuilder.build();
            SignerInformationVerifier signerInformationVerifier = new SignerInformationVerifier(contentVerifierProvider, digestCalculatorProvider);
            tst.validate(signerInformationVerifier);
        }
        catch (TSPValidationException e) {
            String errorMsg = Language.getResIntegra("TSU022");
            LOGGER.error((Object)errorMsg);
            throw new SigningException(errorMsg, (Throwable)e);
        }
        catch (Exception e) {
            String errorMsg = Language.getResIntegra("TSU023");
            LOGGER.error((Object)errorMsg, (Throwable)e);
            throw new SigningException(errorMsg, e);
        }
    }
    finally {
        LOGGER.info((Object)Language.getResIntegra("TSU043"));
    }
}
  */
}
