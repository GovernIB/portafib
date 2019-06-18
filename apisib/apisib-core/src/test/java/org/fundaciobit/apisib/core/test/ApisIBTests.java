package org.fundaciobit.apisib.core.test;

import java.io.IOException;

import junit.framework.Assert;

/**
 * 
 * @author anadal
 *
 */
public class ApisIBTests {

  @org.junit.Test
  public void test() throws IOException {

    String text = "to be encoded";
    String textencoded = "to be encoded";

    Assert.assertEquals(text, textencoded);

  }

}
