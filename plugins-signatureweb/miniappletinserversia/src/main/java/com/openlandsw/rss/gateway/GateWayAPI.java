package com.openlandsw.rss.gateway;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.fundaciobit.plugins.signatureweb.miniappletinserversia.MiniAppletInServerSIASignatureWebPlugin;
import org.fundaciobit.plugins.utils.FileUtils;

/**
 * 
 * @author anadal
 *
 */
public class GateWayAPI {
  
  protected static final Logger log = Logger.getLogger(GateWayAPI.class);


  /*
  DataAuthByLevelTransactionResult dataAuthByLevelTransaction(String id_transaction)

  
  
  RSSListOwnerCertificatesResult listOwnerCertificates(String owner)
  */
  
  public DataTransactionResult dataTransaction(String id_transaction) {
    return null;
  }

  public EndTransactionResult endTransaction(String id_transaction) {
    return null;
  }
    
  public QueryCertificatesResult queryCertificates(String owner) {
    return null;
  }
    
  
  public QueryCertificatesResult queryCertificatesFiltered(String owner, int filterOperation) {

    Map<File, Properties> certificates = getCertificatesOfUser(owner);
    
    
    List<CertificateInfo> certsInfo = new ArrayList<CertificateInfo>();
    
    for(File file : certificates.keySet()) {
      
      Properties prop = certificates.get(file);
      
      byte[] binCert = null;
      try {
        binCert = FileUtils.readFromFile(new File(file, FILENAME_CERT));
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      
      String dnName = prop.getProperty(PROPERTY_SUBJECT);
      
      CertificateInfo certInfo = new CertificateInfo(binCert, dnName);
      
      certsInfo.add(certInfo);

      

    }
    
    

    QueryCertificatesResult qcr = new QueryCertificatesResult(certsInfo, owner);

    return qcr;
    
  }
  

  public void setConfig(Properties propertiesConfig) {
    
    
  }

  /*
  void  setPathFileConfig(String pathFileConfig) Establece la ruta al fichero de configuración Si no se establece, por defecto se busca en la raiz del classpath con el nombre /gatewayapi.properties
  StartAuthTransactionResult startAuthByLevelTransaction(String owner, int authLevel, DataToAuth datatoauth, ParameterAux[] parametersAux)
  
  StartAuthTransactionResult   startAuthTransaction(String owner, DataToSign datatosign, ParameterAux[] parametersAux)
  
  StartOpTransactionResult   startOpTransaction(String owner, StartOperationInfo operationInfo, ParameterAux[] parametersAux)
  */
  
  public  StartTransactionResult startTransaction(String owner, DataToSign datatosign, Object parametersAux) {
    return null;
  }
  
  
  
  
  // ================================================================
  
  
  

  public static final String FIELD_P12PASSWORD = "p12password";
  
  public  static final String PROPERTY_P12PASSWORD_ENCRIPTED = "p12password_encripted";
  
  public  static final String FILENAME_PROPERTIES = "cert.properties";
  public  static final String FILENAME_P12 = "cert.p12";
  public  static final String FILENAME_CERT = "cert.cer";
  
  
  public  static final String PROPERTY_VALID_TO = "validto";

  public  static final String PROPERTY_VALID_FROM = "validfrom";

  public  static final String PROPERTY_ISSUER = "issuer";

  public  static final String PROPERTY_SUBJECT = "subject";

  public  static final String FIELD_PIN = "pin";


  private File getUserNamePath(String username) {
    return new File(getPropertyBasePath(), username);
  }
  
  private File getPropertyBasePath() {
    return miniappletInServerSIABasePath;
  }
  
  
  private File miniappletInServerSIABasePath = new File("D:\\dades\\dades\\CarpetesPersonals\\Programacio\\portafib-files\\postgresql_1.1\\MINIAPPLETINSERVER");


  /**
   * 
   * @return Key del map apunta a un Directori
   */
  private Map<File, Properties> getCertificatesOfUser(String username) {

    File userPath = getUserNamePath(username);

    Map<File, Properties> certificates = new HashMap<File, Properties>();

    if (userPath.exists()) {

      File[] dirs = userPath.listFiles(new FileFilter() {
        @Override
        public boolean accept(File pathname) {
          return pathname.isDirectory();
        }
      });

      for (File dir : dirs) {

        try {

          Properties prop = readPropertiesFromCertPath(dir);

          if (prop == null) {
            if (!FileUtils.deleteRecursive(dir)) {
              log.warn("No s'ha pogut borrar el directori " + dir.getAbsolutePath()
                  + ". S'haura d'eliminat manualment");
            }
            continue;
          }

          certificates.put(dir.getAbsoluteFile(), prop);

        } catch (Exception e) {
          FileUtils.deleteRecursive(dir);
          // TODO 
          e.printStackTrace();
          continue;
        }

      }
    }

    return certificates;

  }

  private Properties readPropertiesFromCertPath(File dir) throws FileNotFoundException,
      IOException {
    

    File props = new File(dir, FILENAME_PROPERTIES);

    
    return MiniAppletInServerSIASignatureWebPlugin.readPropertiesFromFile(props);
  }

  
  
}
