package org.fundaciobit.plugins.signatureserver.afirmaserver;

import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class XMLUtil {

    static final DocumentBuilderFactory DBF = DocumentBuilderFactory.newInstance();

    public static boolean isXml(File file) throws ParserConfigurationException, IOException {
        try {
            DocumentBuilder builder = DBF.newDocumentBuilder();
            builder.parse(file);
            return true;
        } catch (SAXException e) {
            return false;
        }
    }

    public static boolean isXml(byte[] data) throws ParserConfigurationException, IOException {
        try {
            DocumentBuilder builder = DBF.newDocumentBuilder();
            builder.parse(new ByteArrayInputStream(data));
            return true;
        } catch (SAXException e) {
            return false;
        }
    }
}
