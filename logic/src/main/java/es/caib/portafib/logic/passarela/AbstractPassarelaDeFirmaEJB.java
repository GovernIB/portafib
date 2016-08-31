package es.caib.portafib.logic.passarela;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.plugins.signature.api.ISignaturePlugin;

import es.caib.portafib.ejb.EntitatLocal;
import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.logic.AbstractPluginLogicaLocal;
import es.caib.portafib.logic.utils.PdfUtils;
import es.caib.portafib.logic.utils.StampCustodiaInfo;
import es.caib.portafib.logic.utils.StampTaulaDeFirmes;
import es.caib.portafib.model.entity.Fitxer;
import es.caib.portafib.model.fields.CodiBarresFields;
import es.caib.portafib.model.fields.EntitatFields;
import es.caib.portafib.utils.Constants;

/**
 * 
 * @author anadal
 *
 */
public abstract class AbstractPassarelaDeFirmaEJB<T extends ISignaturePlugin> implements AbstractPassarelaDeFirmaLocal {

  protected final Logger log = Logger.getLogger(this.getClass());

  @EJB(mappedName = EntitatLocal.JNDI_NAME)
  protected EntitatLocal entitatEjb;

  @EJB(mappedName = es.caib.portafib.ejb.CodiBarresLocal.JNDI_NAME)
  protected es.caib.portafib.ejb.CodiBarresLocal codiBarresEjb;

  @Override
  public List<String> getSupportedBarCodeTypes() throws I18NException {
     return codiBarresEjb.executeQuery(CodiBarresFields.NOM, null);
  }
  

  @Override
  public EntitatJPA getEntitat(String entitatID) throws I18NException {
    return entitatEjb.findByPrimaryKey(entitatID);
  }

  @Override
  public int getTimeStampPolicy(String entitatID) throws I18NException {

    Integer segellDeTemps = entitatEjb.executeQueryOne(EntitatFields.SEGELLDETEMPSVIAWEB,
        EntitatFields.ENTITATID.equal(entitatID));

    if (segellDeTemps == null) {
      return Constants.SEGELLDETEMPSVIAWEB_NOUSAR;
    } else {
      return segellDeTemps;
    }

  }
  
  
  protected abstract AbstractPluginLogicaLocal<T> getModulDeFirmaEJB();
  
  
  
  @Override
  public boolean providesTimeStampGenerator(String signType, String entitatID, 
      List<Long> filterByPluginID, List<String> filterByPluginCode) {
   
    
    
    try {
      //Recopilació de TOTS els plugins, per entitat + filtre per ID + filtre per codis
      List<T> plugins = getModulDeFirmaEJB().getPluginInstancesBy(entitatID,
          filterByPluginID, filterByPluginCode);
      
      for (T iSignaturePlugin : plugins) {
        if (iSignaturePlugin.providesTimeStampGenerator(signType)) {
          return true;
        }
      }
      
    } catch (I18NException e) {
      log.error("Error desconegut intentant esbrinar els plugins que suporten "
          + "Segellat de Temps per tipus de firma " + signType + ": " + e.getMessage() , e);
    }
    return false;
    
    
  }
  
  
  
  
  

  // Recopilació de TOTS els plugins, per entitat + filtre per ID + filtre per codis
  @Override
  public String[] getSupportedSignatureTypes(String entitatID, 
      List<Long> filterByPluginID, List<String> filterByPluginCode) {
    // TODO Falta CADes, ...

    Set<String> tipus = new HashSet<String>();
    
    try {
      //Recopilació de TOTS els plugins, per entitat + filtre per ID + filtre per codis
      List<T> plugins = getModulDeFirmaEJB().getPluginInstancesBy(entitatID,
          filterByPluginID, filterByPluginCode);
      
      for (T iSignaturePlugin : plugins) {
        String[] tipusA = iSignaturePlugin.getSupportedSignatureTypes(); 
        if (tipusA != null) {
          tipus.addAll(Arrays.asList(tipusA));
        }
      }
      
      return tipus.toArray(new String[tipus.size()]);
      
    } catch (I18NException e) {
      log.error("Error desconegut intentant esbrinar els tipus de firma"
          + " que suporten els plugins: " + e.getMessage() , e);
      return new String[0];
    }
    
    
    
  }

  // Recopilació de TOTS els plugins, per entitat + filtre per ID + filtre per codis
  @Override
  public String[] getSupportedSignatureAlgorithms(String signType, String entitatID, 
      List<Long> filterByPluginID, List<String> filterByPluginCode) {
   
    Set<String> tipusAlgo = new HashSet<String>();
    
    try {
      //Recopilació de TOTS els plugins, per entitat + filtre per ID + filtre per codis
      List<T> plugins = getModulDeFirmaEJB().getPluginInstancesBy(entitatID,
          filterByPluginID, filterByPluginCode);
      
      for (T iSignatureServerPlugin : plugins) {
        String[] tipusA = iSignatureServerPlugin.getSupportedSignatureAlgorithms(signType); 
        if (tipusA != null) {
          tipusAlgo.addAll(Arrays.asList(tipusA));
        }
      }
      
      return tipusAlgo.toArray(new String[tipusAlgo.size()]);
      
    } catch (I18NException e) {
      log.error("Error desconegut intentant esbrinar els algorismes de firma acceptats "
          + " pel tipus de firma " + signType + " que suporten els plugins: "
          + e.getMessage() , e);
      return new String[0];
    }
    
    
  }

  // -----------------------------------------------------------------
  // -----------------------------------------------------------------
  // --- UTILITATS FITXERS PADES: conversio i taula de firmes --------
  // -----------------------------------------------------------------
  // -----------------------------------------------------------------

  /**
   * 
   * @param usuariEntitat
   * @param id
   * @param autoFirmaForm
   * @return
   */
  protected void convertirDocumentAPDF(Fitxer srcInfo, File src, File dst)
      throws I18NException {

    try {

      Fitxer fitxerConvertit = PdfUtils.convertToPDF(src, srcInfo);

      if (fitxerConvertit == srcInfo) {
        // Es un PDF. Movem l'original a l'adaptat
        FileUtils.moveFile(src, dst);
      } else {
        // No és un PDF, ho substituim pel fitxer convertit

        InputStream is = fitxerConvertit.getData().getInputStream();

        FileUtils.copyInputStreamToFile(is, dst);

      }
      // OK

    } catch (Exception e) {
      log.error("Error desconegut convertint document a pdf: " + e.getMessage(), e);
      throw new I18NException("formatfitxer.conversio.error", new I18NArgumentString(
          e.getMessage()));
    }

  }

  protected void afegirTaulaDeFirmesCodiSegurVerificacio(File fitxerPDF,
      StampTaulaDeFirmes stampTaulaDeFirmes, StampCustodiaInfo stampCustodiaInfo)
          throws I18NException {

    // La pujada de fitxers des d'autofirma ho gestiona la classe
    // PortaFIBCommonsMultipartResolver
    final Long maxSizeFitxerAdaptat = null;
    try {
      File tmpDest = File.createTempFile("Passarela_Taula_de_Firmes", ".pdf");

      PdfUtils.add_TableSign_Attachments_CustodyInfo(fitxerPDF, tmpDest, null,
          maxSizeFitxerAdaptat, stampTaulaDeFirmes, stampCustodiaInfo);

      // Destí no pot existir !!!
      fitxerPDF.delete();

      FileUtils.moveFile(tmpDest, fitxerPDF);
    } catch (Exception e) {
      // TODO traduir
      String msg = "Error desconegut afegint taula de firmes a fitxer ("
          + fitxerPDF.getAbsolutePath() + "): " + e.getMessage();
      throw new I18NException("error.unknown", msg);
    }
  }

  
  // -----------------------------------------------------------------
  // -----------------------------------------------------------------
  // -------------- DIRECTORI DE FITXERS TEMPORALS -------------------
  // -----------------------------------------------------------------
  // -----------------------------------------------------------------
  
  protected abstract String getPassarelaBasePath();
  
  public File getFitxerOriginalPath(String transactionID, String signID) {
    File p = new File(getPassarelaBasePath() + File.separatorChar + transactionID
        + File.separatorChar + signID);
    p.mkdirs();
    return new File(p, "original");
  }

  public File getFitxerAdaptatPath(String transactionID, String signID) {
    File p = new File(getPassarelaBasePath() + File.separatorChar + transactionID
        + File.separatorChar + signID);
    p.mkdirs();
    return new File(p, "adaptat");
  }

  
  public File getFitxerFirmatPath(String transactionID, String signID) {
    File p = new File(getPassarelaBasePath() + File.separatorChar + transactionID
        + File.separatorChar + signID);
    p.mkdirs();
    return new File(p, "firmat");
  }

  
  public File getTransactionPath(String transactionID) {
    File p = new File(getPassarelaBasePath() + File.separatorChar + transactionID);
    p.mkdirs();
    return p;
  }
  
}
