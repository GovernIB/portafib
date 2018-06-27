package org.fundaciobit.plugins.signatureserver.afirmaserver.test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.fundaciobit.plugins.signature.api.constants.SignatureTypeFormEnumForUpgrade;
import org.fundaciobit.plugins.signatureserver.afirmaserver.AfirmaServerSignatureServerPlugin;
import org.fundaciobit.plugins.signatureserver.afirmaserver.ValidateSignatureConstants;
import org.fundaciobit.plugins.signatureserver.api.ISignatureServerPlugin;
import org.fundaciobit.plugins.utils.FileUtils;

/**
 * Tiquet (a.10) Afegir mètodes d'extensió de firma: upgradeSignature() i
 * supportUpgradeSignature() #167 https://github.com/GovernIB/portafib/issues/167
 * 
 * @author anadal
 *
 */
public class UpgradeSignatureTest implements ValidateSignatureConstants {

  Logger log = Logger.getLogger(UpgradeSignatureTest.class);

  public static void main(String[] args) {
    try {

      Properties pluginProperties = new Properties();
      pluginProperties.load(new FileInputStream(new File("./config/plugin.properties.caib")));

      final String propertyKeyBase = "org.fundaciobit.exemple.signatureserverplugins.3.";

      ISignatureServerPlugin plugin;
      plugin = new org.fundaciobit.plugins.signatureserver.afirmaserver.AfirmaServerSignatureServerPlugin(
          propertyKeyBase, pluginProperties);

      //XTrustProvider.install();

      upgradePDF2PDF_LTV(plugin);

      upgradeCADES2CADES_T(plugin);

      upgradeCADES2CADES_LTA(plugin);

      System.out.println("Final OK");

    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }

  }

  public static void upgradeCADES2CADES_LTA(ISignatureServerPlugin plugin) throws IOException,
      Exception, FileNotFoundException {

    final String resSrc = "testfiles/foto.jpg_cades_attached.csig";
    final String resDst = "foto.jpg_cades_LTA_attached.csig";

    SignatureTypeFormEnumForUpgrade upgradeSignFormatProfile;
    upgradeSignFormatProfile = SignatureTypeFormEnumForUpgrade.CAdES_LTA_LEVEL;

    upgrade(plugin, resSrc, resDst, upgradeSignFormatProfile);
  }

  public static void upgradeCADES2CADES_T(ISignatureServerPlugin plugin) throws IOException,
      Exception, FileNotFoundException {

    final String resSrc = "testfiles/foto.jpg_cades_attached.csig";
    final String resDst = "foto.jpg_cades_T_attached.csig";

    SignatureTypeFormEnumForUpgrade upgradeSignFormatProfile;
    upgradeSignFormatProfile = SignatureTypeFormEnumForUpgrade.CAdES_T;

    upgrade(plugin, resSrc, resDst, upgradeSignFormatProfile);
  }

  public static void upgradePDF2PDF_LTV(ISignatureServerPlugin plugin) throws IOException,
      Exception, FileNotFoundException {

    final String resSrc = "testfiles/hola_signat.pdf";
    final String resDst = "hola_upgraded.pdf";

    SignatureTypeFormEnumForUpgrade upgradeSignFormatProfile;
    upgradeSignFormatProfile = SignatureTypeFormEnumForUpgrade.PAdES_LTV;

    upgrade(plugin, resSrc, resDst, upgradeSignFormatProfile);
  }

  private static void upgrade(ISignatureServerPlugin plugin, final String resSrc,
      final String resDst, SignatureTypeFormEnumForUpgrade upgradeSignFormatProfile)
      throws IOException, Exception, FileNotFoundException {
    InputStream is = FileUtils.readResource(UpgradeSignatureTest.class, resSrc);

    ByteArrayOutputStream baos = new ByteArrayOutputStream();

    FileUtils.copy(is, baos);

    byte[] signature = baos.toByteArray();

    byte[] upgradeData = ((AfirmaServerSignatureServerPlugin) plugin).upgradeSignature(
        signature, upgradeSignFormatProfile);

    FileOutputStream fos = new FileOutputStream(new File(resDst));
    fos.write(upgradeData);
    fos.flush();
    fos.close();
  }

}
