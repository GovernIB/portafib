package org.fundaciobit.plugins.signatureserver.afirmaserver;


import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author anadal
 *
 */
public class ManualXAdESParserOfResponse {

  public static Map<String,Object> parseXAdES(String inXMLStr) throws Exception {
    Map<String,Object> resultProperties = new HashMap<String, Object>();

    DocumentBuilder db = XMLUtil.DBF.newDocumentBuilder();
    InputSource is = new InputSource(new StringReader(inXMLStr));

      Document doc = db.parse(is);
    
      listNodes("", doc.getDocumentElement(), "", resultProperties);
      
      return resultProperties;
  }
  
  static void listNodes(String base, Node node, String indent,
      Map<String,Object> properties) throws Exception {
    String nodeName = node.getNodeName();
    
    String baseorig = base;
    
    if (!nodeName.equals("dss:VerifyResponse")) {
      base = base + (base.isEmpty()?"":"/") + nodeName;
    }
    
    if (base.equals("dss:OptionalOutputs/dss:UpdatedSignature/dss:SignatureObject/ds:Signature")) {
      properties.put("dss:SignatureObject/ds:Signature", node2String(node));
      return;
    }
    
    if (base.equals("dss:OptionalOutputs/dss:UpdatedSignature/dss:SignatureObject/dss:Base64Signature")) {
      properties.put("dss:SignatureObject/dss:Base64Signature", node2String(node));
      return;
    }
    
    NodeList list = node.getChildNodes();
    
    if (list.getLength() == 0) {
       properties.put(baseorig, node.getTextContent());
       
    } else {
      // XYZ  ZZZ
      for (int i = 0; i < list.getLength(); i++)
        listNodes(base, list.item(i), indent + " ", properties);
    }
  }
  
  
  static String node2String(Node node) throws Exception {
    // you may prefer to use single instances of Transformer, and
    // StringWriter rather than create each time. That would be up to your
    // judgement and whether your app is single threaded etc
    StreamResult xmlOutput = new StreamResult(new StringWriter());
    Transformer transformer = TransformerFactory.newInstance().newTransformer();
    transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
    transformer.transform(new DOMSource(node), xmlOutput);
    return xmlOutput.getWriter().toString();
}
  
}
