package es.caib.portafib.logic.utils;

import es.caib.portafib.logic.utils.datasource.IPortaFIBDataSource;
import es.gob.afirma.signature.SigningException;
import es.gob.afirma.signature.xades.IdRegister;
import es.gob.afirma.transformers.TransformersException;
import es.gob.afirma.utils.UtilsXML;
import net.java.xades.security.xml.XMLSignatureElement;
import org.apache.log4j.Logger;
import org.apache.xml.security.keys.KeyInfo;
import org.apache.xml.security.signature.XMLSignatureInput;
import org.apache.xml.security.utils.resolver.ResourceResolver;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pluginsib.core.utils.Base64;
import org.jcp.xml.dsig.internal.dom.DOMReference;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//import org.apache.jcp.xml.dsig.internal.dom.DOMReference;

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
   * Segons l'estàndard xmldsig (https://www.w3.org/TR/xmldsig-core1/) apartat 2, una firma dettached ho pot ser d'un
   * objecte extern referenciat amb la URI, o un objecte local que està dins el mateix document com "sibling element"
   * per tant, comparteix pare amb la firma.
   * Aquest mètode retorna true si es dona aquest cas, i el document dettached inclou l'objecte referenciat dins la URI
   * al mateix document al mateix nivell que la signatura.
   * @param signatureData datasource amb la firma
   * @return true si l'objecte firmat està inclòs com un element al mateix nivell que la firma. false en cas contrari.
   * @throws I18NException
   */
  public static boolean isXadesDettachedWithOriginalDocumentAsSibling(IPortaFIBDataSource signatureData) throws I18NException {
    InputStream in = signatureData.getInputStream();
    try {
       log.info("isXadesDettachedWithOriginalDocumentAsSibling?");

       DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
       dbf.setNamespaceAware(true);

       Document document = dbf.newDocumentBuilder().parse(in);

       NodeList signNodeList = document.getElementsByTagNameNS(XMLSignature.XMLNS, ValidationsXAdES.SIGNATURE_NODE_NAME);
       if (signNodeList.getLength() == 0) {
          log.warn("No s'ha trobat firma");
          throw new Exception("No hi ha firma");
       }

       // Se selecciona la primera firma.
       Element signatureNode = (Element) signNodeList.item(0);

       Node parentNode = signatureNode.getParentNode();
       if (parentNode == document) {
          log.info("No tenim node pare. Per tant no inclou el document.");
          return false;
       }

       // Tenim node pare, per tant anam bé
       log.info("Tenim parentNode: " + parentNode.getNodeName());

       Element signedInfoNode = (Element) signatureNode.getElementsByTagNameNS(
             XMLSignature.XMLNS, "SignedInfo").item(0);

       NodeList references = signedInfoNode.getElementsByTagNameNS(XMLSignature.XMLNS, "Reference");

       // Llista de possibles identificadors que contenen la firma
       Set<String> identificadors = new HashSet<String>();
       for (int i = 0; i < references.getLength(); i++) {
          Element reference = (Element) references.item(i);
          String type = reference.getAttribute("Type");
          String uri = reference.getAttribute("URI");

          // URIs possibles són les que:
          // el Type o bé existeix o bé val "http://www.w3.org/2000/09/xmldsig#Object"
          // la URI comença amb '#', cosa que indica que és una referència dins el mateix document
          if (type.isEmpty() || type.equals("http://www.w3.org/2000/09/xmldsig#Object")) {
             if (uri != null && uri.charAt(0) == '#') {
                log.info("Afegim possible URI: " + uri);
                identificadors.add(uri.substring(1));
             }
          }
       }

       NodeList siblings = parentNode.getChildNodes();
       for (int i = 0; i < siblings.getLength(); i++) {
          Element sibling = (Element) siblings.item(i);
          // Miram si qualcun dels 'siblings' de la signatura té un identificador dels possibles
          if (sibling != signatureNode) { // Descaram el node mateix de signatura
             String id = sibling.getAttribute("Id");
             if (id.isEmpty()) { // El node que està al mateix nivell pot emprar "id" enlloc de "Id"
                id = sibling.getAttribute("id");
             }
             if (identificadors.contains(id)) {
                log.info("Hem trobat el sibling amb l'identificador: " + id);
                return true;
             }
          }
       }
       log.info("Sibling no trobat dins els identificadors possibles");
       return false;

    } catch (Exception e) {
      // XYZ ZZZ TRA
      throw new I18NException("genapp.comodi",
            "Error desconegut intentant determinar si un XAdES dettached inclou el document original com a element: "
                  + e.getMessage());

    } finally {
       try {
          in.close();
       } catch(IOException ignored) {}
    }
  }

  /**
   * Obtains original document from a signature given.
   * 
   * @param in
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
