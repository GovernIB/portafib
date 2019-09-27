package es.caib.portafib.logic.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.crypto.dsig.Reference;

import net.java.xades.security.xml.XMLSignatureElement;

import org.apache.xml.security.keys.KeyInfo;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


import es.gob.afirma.signature.SigningException;
import es.gob.afirma.signature.xades.IdRegister;
import es.gob.afirma.transformers.TransformersException;

import org.w3c.dom.Node;
import org.w3c.dom.Text;

import es.gob.afirma.utils.UtilsXML;



//import org.apache.jcp.xml.dsig.internal.dom.DOMReference;
import org.apache.log4j.Logger;
import org.apache.xml.security.signature.XMLSignatureInput;
import org.apache.xml.security.utils.resolver.ResourceResolver;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pluginsib.core.utils.Base64;
import org.jcp.xml.dsig.internal.dom.DOMReference;

/**
 * 
 * @author anadal(u80067)
 *
 */
public class ValidationsXAdES {
  
  
  static {
    org.apache.xml.security.Init.init();
  }

  protected static final Logger log = Logger.getLogger(ValidationsXAdES.class);

  /**
   * Attribute that represents signature node name (Signature).
   */
  public static final String SIGNATURE_NODE_NAME = "Signature";

  /**
   * Attribute that represents URI that define namespace of XMLDSig signatures .
   */
  public static final String DSIGNNS = "http://www.w3.org/2000/09/xmldsig#";

  /**
   * Obtains original document from a signature given.
   * 
   * @param eSignature
   *          electronic signature.
   * @return orginal document in byte [] format.
   * @throws SigningException
   *           if method fails.
   * @throws TransformersException
   *           if method fails.
   */
  public static byte[] getOriginalDocumentOfXadesAttachedSignature(InputStream in) throws I18NException {

    try {

      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      dbf.setNamespaceAware(true);
      Document eSignature = dbf.newDocumentBuilder().parse(in);

      // LOGGER.debug(Language.getResIntegra(ILogConstantKeys.XS_LOG041));
      byte[] result = null;
      // Obtención de cualquiera de las firmas para obtener el documento
      // original.
      NodeList signNodeList = eSignature.getElementsByTagNameNS(XMLSignature.XMLNS,
          SIGNATURE_NODE_NAME);
      if (signNodeList.getLength() == 0) {
        throw new Exception("No hi ha firma");
      }
      // Se selecciona la primera firma.
      Element signatureNode = (Element) signNodeList.item(0);
      // registro de los id de los nodos
      IdRegister.registerElements(signatureNode);

      XMLSignature xmlSign;
      try {
        xmlSign = new XMLSignatureElement((Element) signatureNode).getXMLSignature();
      } catch (MarshalException e1) {
        throw new Exception("Error parsejant firma: " + e1.getMessage(), e1);
      }

      // Obtención de la referencia del documento original.
      List<?> references = xmlSign.getSignedInfo().getReferences();
      XMLSignatureInput xmlObjectInput = null;
      
      log.info(" XYZ ZZZ ZZZ ValidationsXAdES result A001 => References => " + references.size());
      
      for (Object tmp : references) {
        Reference ref = (Reference) tmp;
        Attr uriAttr = (Attr) ((DOMReference) ref).getHere();

        ResourceResolver res;
        try {
          res = ResourceResolver.getInstance(uriAttr, null);
          xmlObjectInput = res.resolve(uriAttr, null);
        } catch (Exception e) {
          log.error("ValidationsXAdES result A002 => FOR-CONTINUE => " + e.getMessage());
          continue;
        }

        Node dsObject = xmlObjectInput.getSubNode();

        if ("ds:Object".equals(dsObject.getNodeName())
            && !"QualifyingProperties".equals(dsObject.getFirstChild().getLocalName())) {
          NodeList nodeListObject = dsObject.getChildNodes();
          if (nodeListObject.getLength() == 1) {

            Node children = dsObject.getFirstChild();
            result = transformNode(children);

          } else {

            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < nodeListObject.getLength(); i++) {
              Node children = nodeListObject.item(i);
              byte[] nodeValue = transformNode(children);
              if (nodeValue != null) {
                buffer.append(new String(nodeValue));
              }
            }
            result = buffer.toString().getBytes();

          }
          
          if (result != null) {
            int options = 0;
            try {
              result = Base64.decode(result, 0, result.length, options);
            } catch (Exception e) {
              // Deu ser XML
              log.error("ValidationsXAdES result A3 => " + result + ": " + e.getMessage());
            }

          }

          return result;
        }
      }
      return result;

    } catch (Exception e) {

      // XYZ ZZZ TRA
      throw new I18NException("genapp.comodi",
          "Error desconegut intentant extreure document original d´una firma XAdES: "
              + e.getMessage());

    }

  }

  /**
   * Transforms a node with childrens in a string.
   * 
   * @param node
   *          xml element to transform
   * @return string in byte[] format.
   * @throws TransformersException
   *           if method fails.
   */
  private static byte[] transformNode(Node node) throws TransformersException {
    if (node.getNodeType() == Node.TEXT_NODE || node.getNodeType() == Node.CDATA_SECTION_NODE) {
      String textValue = ((Text) node).getData();
      return textValue == null ? null : textValue.getBytes();
    } else if (node.getNodeType() == Node.ELEMENT_NODE) {
      return UtilsXML.transformDOMtoString((Element) node, true).getBytes();
    } else {
      return null;
    }

  }

  /**
   * Verifies a xades signature.
   * 
   * @param eSignature
   *          digital signature.
   * @return true if signature is valid, false otherwise.
   * @throws SigningException
   *           an error happends.
   */
  public static X509Certificate[] getCertificatesOfXadesSignature(InputStream eSignature)
      throws I18NException {

    try {

      org.apache.xml.security.Init.init();

      // Transformación de la firma electrónica a objeto
      // org.w3c.dom.Document
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      dbf.setNamespaceAware(true);
      dbf.setAttribute("http://xml.org/sax/features/namespaces", Boolean.TRUE);
      DocumentBuilder db = dbf.newDocumentBuilder();
      Document doc = db.parse(eSignature);
      NodeList nl = doc.getElementsByTagNameNS(DSIGNNS, SIGNATURE_NODE_NAME);

      List<X509Certificate> certificates = new ArrayList<X509Certificate>();

      // registro de los atributos de tipo ID
      IdRegister.registerElements(doc.getDocumentElement());
      for (int i = 0; i < nl.getLength(); i++) {
        Element sigElement = (Element) nl.item(i);
        org.apache.xml.security.signature.XMLSignature signature = new org.apache.xml.security.signature.XMLSignature(
            sigElement, "");

        // Obtención del certificado o clave pública de la firma para
        // verificar su autenticidad y no repudio.
        KeyInfo keyInfo = signature.getKeyInfo();
        if (keyInfo != null) {
          X509Certificate cert = keyInfo.getX509Certificate();
          if (cert != null) {
            // Validamos la firma usando un certificado X509

          } else {
            // No encontramos un Certificado intentamos validar por
            // la cláve
            // pública
            PublicKey pk = keyInfo.getPublicKey();

            CertificateFactory cf = CertificateFactory.getInstance("X509");
            byte[] pubKeyAsBytes = pk.getEncoded();
            cert = (X509Certificate) cf.generateCertificate(new ByteArrayInputStream(
                pubKeyAsBytes));

          }
          certificates.add(cert);
        }
      }

      return certificates.toArray(new X509Certificate[certificates.size()]);

    } catch (Exception e) {
      // XYZ ZZZ TRA
      throw new I18NException("genapp.comodi",
          "Error desconegut obtenint Certificats d'una firma XAdES: " + e.getMessage());
    }
  }

}
