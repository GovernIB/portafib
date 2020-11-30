package org.fundaciobit.signatureweb.fire;

import java.io.File;
import java.io.FileInputStream;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Properties;

import org.fundaciobit.plugins.signatureweb.fire.FIReSignatureWebPlugin;

import es.gob.clavefirma.client.HttpCertificateBlockedException;

/**
 * 
 * @author anadal
 *
 */
public class TestListCertificates {

  public static void main(String[] args) {

    try {

      Properties testProperties = new Properties();
      testProperties.load(new FileInputStream(new File("test.properties")));

      Properties config = new Properties();
      config.load(new FileInputStream(new File("./doc/fire.properties")));

      for (Object key : config.keySet()) {
        System.out.println("CONFIG[" + key + "] => " + config.getProperty((String) key));
      }

      final String packageBase = "es.caib.portafib.";

      FIReSignatureWebPlugin firePlugin = new FIReSignatureWebPlugin(packageBase, config);

      String fireUsersStr = testProperties.getProperty("fireusers");

      String[] fireUsers = fireUsersStr.split(",");
      /*
       * { "00001", // usuari OK "00002", // usuari sense certificat "00003", //
       * // usuari amb certificat bloquejat "00004", // usuari amb registre
       * dèbil };
       */

      for (String userFire : fireUsers) {

        System.out.println(" =====  USER " + userFire + "  =========");
        List<X509Certificate> list;
        try {
          list = firePlugin.listCertificates(userFire);
        } catch (HttpCertificateBlockedException cbe) {
          System.err.println("   << TE CERTIFICATS BLOQUEJATS >>");
          continue;
        } catch (es.gob.clavefirma.client.HttpWeakRegistryException wre) {
          System.err.println("   << USUARI TE REGISTRE DEBIL i NO POT TENIR CERTIFICATS >>");
          continue;
        } catch (Exception e) {
          System.err.println("   << ERROR DESCONEGUT: " + e.getMessage() + " >>");
          continue;
        }

        if (list.isEmpty()) {
          System.err.println("   << NO TE CERTIFICATS >>");
        } else {

          for (X509Certificate x509Certificate : list) {
            System.out.println("   - " + x509Certificate.getSubjectDN());
            System.out.println("       [" + x509Certificate.getIssuerDN() + "]");
          }
        }

      }

    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();

    }

  }
}
