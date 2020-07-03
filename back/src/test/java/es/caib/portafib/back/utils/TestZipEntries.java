package es.caib.portafib.back.utils;

import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author areus
 */
public class TestZipEntries {

   private static final Logger log = Logger.getLogger(TestZipEntries.class);

   @Test
   public void testZipEntries() throws IOException {
      log.info("testZipEntries");

      ZipProducer zipProducer = ZipProducer.getInstance(new File("."));
      zipProducer.addEntry("Això és un fitxer.xml", new File("pom.xml"));
      zipProducer.addEntry("Aix_ és un fitxer.xml", new File("pom.xml"));
      zipProducer.addEntry("Això _s un fitxer.xml", new File("pom.xml"));
      try {
         FileOutputStream fileOutputStream = new FileOutputStream("prova.zip");
         zipProducer.transferTo(fileOutputStream);
         fileOutputStream.close();
      } finally {
         zipProducer.cleanUp();
      }
   }
}
