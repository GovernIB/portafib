package org.fundaciobit.plugins.signatureserver.afirmaserver;


import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * 
 * @author anadal
 *
 */
public class ManualXAdESParserOfResponse {


  public static Map<String,Object> parseXAdES(String inXMLStr) throws ParserConfigurationException,
      SAXException, IOException, Exception {
    Map<String,Object> resultProperties = new HashMap<String, Object>();

    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder db = null;
    
      db = dbf.newDocumentBuilder();
      InputSource is = new InputSource(new StringReader(inXMLStr));

      Document doc = db.parse(is);

      // System.out.println(doc.getDocumentElement().getTextContent().substring(0, 300));
      
      
    
      listNodes("", doc.getDocumentElement(), "", resultProperties);
      
      return resultProperties;
  }
  
  static void listNodes(String base, Node node, String indent,
      Map<String,Object> properties) throws Exception {
    String nodeName = node.getNodeName();
    
    //System.out.println(indent + nodeName + " Node, type is "
    //    + node.getClass().getName() + ":"  + node.getClass().isInstance(CharacterData.class));
    //System.out.println(indent + " " + node.getNodeValue());
    
    String baseorig =new String(base);
    
    if (!nodeName.equals("dss:VerifyResponse")) {
      base = base + (base.isEmpty()?"":"/") + nodeName;
    }
    
    if (base.equals("dss:OptionalOutputs/dss:UpdatedSignature/dss:SignatureObject/ds:Signature")) {
      
      //new FileOutputStream("c:\\tmp\\AA_NodeTOString.xml").write(node2String(node).getBytes());
      
      //new FileOutputStream("c:\\tmp\\AA_TextContent.xml").write(node.getTextContent().getBytes());
      
      properties.put("dss:SignatureObject/ds:Signature", node2String(node));
      
      //
      
      return;
    }
    
    if (base.equals("dss:OptionalOutputs/dss:UpdatedSignature/dss:SignatureObject/dss:Base64Signature")) {
      
      //new FileOutputStream("c:\\tmp\\AA_NodeTOString.xml").write(node2String(node).getBytes());
      
      //new FileOutputStream("c:\\tmp\\AA_TextContent.xml").write(node.getTextContent().getBytes());
      
      properties.put("dss:SignatureObject/dss:Base64Signature", node2String(node));
      
      //dss:SignatureObject/dss:Base64Signature
      
      return;
    }
    
    NodeList list = node.getChildNodes();
    
    if (list.getLength() == 0) {
       //System.err.println(indent + baseorig + "=> ]" + node.getTextContent() + "[");
       
       properties.put(baseorig, node.getTextContent());
       
    } else {
      // XYZ  ZZZ
      //System.out.println(indent + "Child Nodes of " + nodeName + " are:");
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
