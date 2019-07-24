package es.caib.portafib.logic.passarela;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.ejb.EJB;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.plugins.signature.api.ISignaturePlugin;

import es.caib.portafib.ejb.EntitatLocal;
import es.caib.portafib.jpa.EntitatJPA;
import es.caib.portafib.jpa.UsuariAplicacioJPA;
import es.caib.portafib.logic.AbstractPluginLogicaLocal;
import es.caib.portafib.logic.passarela.api.PassarelaFileInfoSignature;
import es.caib.portafib.logic.utils.SignatureUtils;
import es.caib.portafib.model.fields.CodiBarresFields;
import es.caib.portafib.model.fields.EntitatFields;
import es.caib.portafib.utils.ConstantsPortaFIB;

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

    Integer segellDeTemps = entitatEjb.executeQueryOne(EntitatFields.POLITICASEGELLATDETEMPS,
        EntitatFields.ENTITATID.equal(entitatID));

    if (segellDeTemps == null) {
      return ConstantsPortaFIB.POLITICA_DE_SEGELLAT_DE_TEMPS_NOUSAR;
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
        
        /** El propi portaFIB és el que proveirà el Segellador de temps, però hem de saber
         *  si els plugins en si, internament suporten un Segellador de Temps extern 
         */        
        if (iSignaturePlugin.acceptExternalTimeStampGenerator(signType)) {
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
    final boolean debug = log.isDebugEnabled();
    try {
      //Recopilació de TOTS els plugins, per entitat + filtre per ID + filtre per codis
      List<T> plugins = getModulDeFirmaEJB().getPluginInstancesBy(entitatID,
          filterByPluginID, filterByPluginCode);
      
      for (T iSignaturePlugin : plugins) {
        String[] tipusA = iSignaturePlugin.getSupportedSignatureTypes(); 
        if (tipusA != null) {
          if (debug) {
            log.debug("getSupportedSignatureTypes()::Plugin["
               + iSignaturePlugin.getName(new Locale("ca")) + "]: " + Arrays.toString(tipusA));
          }
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
  
  
  /**
   * 
   * @param locale
   * @param entitatID
   * @param pfis
   * @param original
   * @param adaptat
   * @throws I18NException
   */
  public int processFileToSign(Locale locale, String entitatID,
      PassarelaFileInfoSignature pfis, File original, File adaptat, UsuariAplicacioJPA usrApp)
      throws I18NException {
    return SignatureUtils.processFileToSign(locale, entitatID, pfis, original, adaptat,
        this.entitatEjb, this.codiBarresEjb, usrApp);
    
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
