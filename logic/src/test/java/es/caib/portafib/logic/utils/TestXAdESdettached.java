package es.caib.portafib.logic.utils;

import es.caib.portafib.logic.utils.datasource.FileDataSource;
import es.caib.portafib.logic.utils.datasource.IPortaFIBDataSource;
import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.net.URL;

/**
 * Classe de test de comprovació de XAdES dettached
 */
public class TestXAdESdettached {
  
  public static final Logger log = Logger.getLogger(TestXAdESdettached.class);

  private boolean processXadesDettachedWithOriginalDocumentAsSibling(String resource) throws Exception, I18NException {
     log.info("processant: " + resource);
     URL url = getClass().getResource(resource);
     IPortaFIBDataSource signatureData = new FileDataSource(new File(url.toURI()));
     return ValidationsXAdES.isXadesDettachedWithOriginalDocumentAsSibling(signatureData);
  }

  @Test
  public void testXadesDettachedWithOriginalDocumentAsSibling() throws Exception, I18NException {

     /* Documents de xades attached o dettached però no internal dettached */
     Assert.assertFalse(processXadesDettachedWithOriginalDocumentAsSibling("/xades_attached_binary_autofirma.xsig"));
     Assert.assertFalse(processXadesDettachedWithOriginalDocumentAsSibling("/xades_dettached_binary_europe.xml"));

     /* Documents xades internaly dettached */
     Assert.assertTrue(processXadesDettachedWithOriginalDocumentAsSibling("/xades_idettached_binary_autofirma.xsig"));
     Assert.assertTrue(processXadesDettachedWithOriginalDocumentAsSibling("/xades_idettached_xml_autofirma.xsig"));
     Assert.assertTrue(processXadesDettachedWithOriginalDocumentAsSibling("/xades_idettached_xml_europe.xml"));

  }
}
