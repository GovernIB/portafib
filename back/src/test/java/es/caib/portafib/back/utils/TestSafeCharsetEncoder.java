package es.caib.portafib.back.utils;

import es.caib.portafib.utils.ConstantsV2;
import junit.framework.Assert;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * @author areus
 */
public class TestSafeCharsetEncoder {

   private static final Logger log = Logger.getLogger(TestSafeCharsetEncoder.class);

   @Test
   public void testAsciiDefaultReplacement() {
      SafeCharsetEncoder encoder = SafeCharsetEncoder.getInstance(ConstantsV2.US_ASCII);

      Assert.assertEquals("Aixo es una prova",
              encoder.encode("Aixo es una prova"));

      Assert.assertEquals("Aix_ _s una prova",
              encoder.encode("Això és una prova"));
   }

   @Test
   public void testLatinDefaultReplacement() {
      SafeCharsetEncoder encoder = SafeCharsetEncoder.getInstance(ConstantsV2.ISO_8859_1);

      Assert.assertEquals("Això és una prova",
              encoder.encode("Això és una prova"));

      Assert.assertEquals("Això és _ una prova",
              encoder.encode("Això és € una prova"));
   }

   @Test
   public void testUtfDefaultReplacement() {
      SafeCharsetEncoder encoder = SafeCharsetEncoder.getInstance(ConstantsV2.UTF_8);

      Assert.assertEquals("Això és € una prova",
              encoder.encode("Això és € una prova"));
   }

   @Test
   public void testAsciiReplacement() {

      SafeCharsetEncoder encoder = SafeCharsetEncoder.getInstance(ConstantsV2.US_ASCII, "*");

      Assert.assertEquals("Aixo es una prova",
              encoder.encode("Aixo es una prova"));

      Assert.assertEquals("Aix* *s una prova",
              encoder.encode("Això és una prova"));
   }

   @Test
   public void testLatinReplacement() {
      SafeCharsetEncoder encoder = SafeCharsetEncoder.getInstance(ConstantsV2.ISO_8859_1, "*");

      Assert.assertEquals("Això és una prova",
              encoder.encode("Això és una prova"));

      Assert.assertEquals("Això és * una prova",
              encoder.encode("Això és € una prova"));
   }

   @Test
   public void testUtfReplacement() {
      SafeCharsetEncoder encoder = SafeCharsetEncoder.getInstance(ConstantsV2.UTF_8, "*");

      Assert.assertEquals("Això és € una prova",
              encoder.encode("Això és € una prova"));
   }

}
