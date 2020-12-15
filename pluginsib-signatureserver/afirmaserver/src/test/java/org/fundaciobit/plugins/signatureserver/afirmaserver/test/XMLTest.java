package org.fundaciobit.plugins.signatureserver.afirmaserver.test;

import junit.framework.Assert;
import org.fundaciobit.plugins.signatureserver.afirmaserver.XMLUtil;
import org.fundaciobit.pluginsib.core.utils.FileUtils;
import org.junit.Test;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

public class XMLTest {

    @Test
    public void testIsXML() throws IOException, ParserConfigurationException {
        InputStream inputStream = getClass().getResourceAsStream("/testfiles/sample.xml");
        byte[] byteArray = FileUtils.toByteArray(inputStream);
        Assert.assertTrue(XMLUtil.isXml(byteArray));
    }

    @Test
    public void testIsXML2() throws IOException, ParserConfigurationException {
        InputStream inputStream = getClass().getResourceAsStream("/testfiles/prova.xml");
        byte[] byteArray = FileUtils.toByteArray(inputStream);
        Assert.assertTrue(XMLUtil.isXml(byteArray));
    }
}
