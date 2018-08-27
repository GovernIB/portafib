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
import org.fundaciobit.plugins.signatureserver.api.ISignatureServerPlugin;
import org.fundaciobit.plugins.utils.FileUtils;

/**
 * Tiquet (a.10) Afegir mètodes d'extensió de firma: upgradeSignature() i
 * supportUpgradeSignature() #167 https://github.com/GovernIB/portafib/issues/167
 * 
 * @author anadal
 *
 */
public class UpgradeSignatureTest  {

  Logger log = Logger.getLogger(UpgradeSignatureTest.class);

  public static void main(String[] args) {
    try {

      Properties pluginProperties = new Properties();
      pluginProperties.load(new FileInputStream(new File("./config/plugin.properties")));

      final String propertyKeyBase = "org.fundaciobit.exemple.signatureserverplugins.3.";

      ISignatureServerPlugin plugin;
      plugin = new org.fundaciobit.plugins.signatureserver.afirmaserver.AfirmaServerSignatureServerPlugin(
          propertyKeyBase, pluginProperties);

      //XTrustProvider.install();

//      upgradePDF2PDF_LTV(plugin);
//
      upgradeCADES2CADES_T(plugin);
//
      upgradeCADES2CADES_LTA(plugin);
//      
//      upgradeXADEES_XML2XADES_LTA(plugin);
//      
//      upgradeXADEES_Binary2XADES_XL(plugin);
      
//      upgradeXADEESManifest2XADES_LTA(plugin);

      System.out.println("Final OK");

    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }

  }
  
  
  public static void upgradeXADEES_XML2XADES_LTA(ISignatureServerPlugin plugin) throws IOException,
  Exception, FileNotFoundException {

    final String resSrc = "testfiles/sample.xml_signed.xsig";
    
    //final String resSrc = "testfiles/sample_xades_attached_firmat.xml";
    final String resDst = "sample.xml_signed.xsig_extends_XAdES_LTA.xsig";
    
    SignatureTypeFormEnumForUpgrade upgradeSignFormatProfile;
    upgradeSignFormatProfile = SignatureTypeFormEnumForUpgrade.XAdES_LTA_LEVEL;
    
    upgrade(plugin, resSrc, resDst, upgradeSignFormatProfile);
  }
  
  
  public static void upgradeXADEES_Binary2XADES_XL(ISignatureServerPlugin plugin) throws IOException,
  Exception, FileNotFoundException {

    final String resSrc = "testfiles/foto_xades_attached_firmat.xsig";
    
    //final String resSrc = "testfiles/sample_xades_attached_firmat.xml";
    final String resDst = "foto_xades_attached_firmat_extends_XAdES_XL.xsig";
    
    SignatureTypeFormEnumForUpgrade upgradeSignFormatProfile;
    upgradeSignFormatProfile = SignatureTypeFormEnumForUpgrade.XAdES_XL;
    
    upgrade(plugin, resSrc, resDst, upgradeSignFormatProfile);
  }

  
  
  public static void upgradeXADEESManifest2XADES_LTA(ISignatureServerPlugin plugin) throws IOException,
    Exception, FileNotFoundException {

    final String resSrc = "testfiles/ORVE_firma0.xsig";
    
    //final String resSrc = "testfiles/sample_xades_attached_firmat.xml";
    final String resDst = "ORVE_firma0_extends_XAdES_LTA.xsig";
    
    SignatureTypeFormEnumForUpgrade upgradeSignFormatProfile;
    upgradeSignFormatProfile = SignatureTypeFormEnumForUpgrade.XAdES_LTA_LEVEL;
    
    upgrade(plugin, resSrc, resDst, upgradeSignFormatProfile);
  }


  public static void upgradeCADES2CADES_LTA(ISignatureServerPlugin plugin) throws IOException,
      Exception, FileNotFoundException {

    final String resSrc = "testfiles/foto.jpg_cades_detached.csig";
    final String resDst = "foto.jpg_cades_LTA_detached.csig";

    SignatureTypeFormEnumForUpgrade upgradeSignFormatProfile;
    upgradeSignFormatProfile = SignatureTypeFormEnumForUpgrade.CAdES_LTA_LEVEL;

    upgrade(plugin, resSrc, resDst, upgradeSignFormatProfile);
  }

  public static void upgradeCADES2CADES_T(ISignatureServerPlugin plugin) throws IOException,
      Exception, FileNotFoundException {

    final String resSrc = "testfiles/foto.jpg_cades_detached.csig";
    final String resDst = "foto.jpg_cades_T_detached.csig";

    SignatureTypeFormEnumForUpgrade upgradeSignFormatProfile;
    upgradeSignFormatProfile = SignatureTypeFormEnumForUpgrade.CAdES_T;

    upgrade(plugin, resSrc, resDst, upgradeSignFormatProfile);
  }

  public static void upgradePDF2PDF_LTV(ISignatureServerPlugin plugin) throws IOException,
      Exception, FileNotFoundException {

    final String resSrc = "testfiles/hola_signat.pdf";
    final String resDst = "hola.pdf_upgraded_to_PADES_LTV.pdf";

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
    
    //System.out.println(" XYZ ZZZ SIGNATURE = " + signature);
    //System.out.println(" XYZ ZZZ SIGNATURE = " + signature.length);

    byte[] upgradeData = ((AfirmaServerSignatureServerPlugin) plugin).upgradeSignature(
        signature, upgradeSignFormatProfile, null);
    
    File resultsDir = new File("results");
    resultsDir.mkdirs();

    FileOutputStream fos = new FileOutputStream(new File(resultsDir,resDst));
    fos.write(upgradeData);
    fos.flush();
    fos.close();
  }

}