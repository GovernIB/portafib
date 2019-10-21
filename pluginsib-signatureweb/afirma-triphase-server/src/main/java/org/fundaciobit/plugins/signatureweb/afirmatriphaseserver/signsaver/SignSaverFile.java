package org.fundaciobit.plugins.signatureweb.afirmatriphaseserver.signsaver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.fundaciobit.plugins.signatureweb.afirmatriphaseserver.AfirmaTriphaseSignatureWebPlugin;
import org.fundaciobit.plugins.signatureweb.afirmatriphaseserver.AfirmaTriphaseSignatureWebPlugin.Item;

import es.gob.afirma.signers.batch.SignSaver;
import es.gob.afirma.signers.batch.SingleSign;

/**
 * 
 * @author anadal
 *
 */
public class SignSaverFile implements SignSaver {
  public static final String PROP_FILENAME = "FileName";
    
  // KEY -> ID de firma || VALUE => Indica la data d'expiració
  private static final Map<String, Long> processedFiles = new HashMap<String, Long>();
  
  
  private Logger log = Logger.getLogger(SignSaverFile.class);
  
  private String filename;
  
  private boolean debug;

  public SignSaverFile(String targetFileName) { //, String *** afirmaTriphaseSignatureWebPluginInstance) {
    if (targetFileName == null) {
      throw new IllegalArgumentException("El nombre de fichero no puede ser nulo");
    }
    this.filename = targetFileName;
  }

  public SignSaverFile() {
  }

  @Override
  public Properties getConfig() {
    Properties p = new Properties();
    p.put(PROP_FILENAME, this.filename);
    p.put("debug", String.valueOf(this.debug));
    return p;
  }

  @Override
  public void saveSign(SingleSign sign, byte[] dataToSave) throws IOException {

    FileOutputStream o = new FileOutputStream(this.filename);
    o.write(dataToSave);
    o.flush();
    o.close();

    if (this.debug) {
      Item item = AfirmaTriphaseSignatureWebPlugin.decodeSignatureItemID(sign.getId());
      log.info(
       " ------- SignSaverFile::saveSign(" + sign.getId() + ") ---------\n"
       + " FINAL SAVE IN this.filename =" + this.filename + "\n"
       + " sign.getId() = " +  sign.getId() + "\n"
       + " SignaturesSetID = " +  item.signaturesSetID + "\n"
       + " index = " +  item.index + "\n"
       + " sign.getSignFormat() = " + sign.getSignFormat() + "\n"
       + " dataToSave.length = " +  dataToSave.length + "\n"
       );
    }

    synchronized (PROP_FILENAME) {
      processedFiles.put(sign.getId(), System.currentTimeMillis() + FIVE_MINUTES);
    }

  }

  @Override
  public void init(Properties config) {
    if (config == null) {
      throw new IllegalArgumentException("La configuracion no puede ser nula");
    }
    String file = config.getProperty(PROP_FILENAME);
    if (file == null) {
      throw new IllegalArgumentException(
          "Es obligarorio que la configuracion incluya un valor para la propiedad " + PROP_FILENAME);
    }

    this.filename = file;
    this.debug = "true".equals(config.getProperty("debug"));
    
    if (debug) {
      log.info("Inicialitzat SignSaverFile amb fitxer: " + this.filename);
    }
    
  }

  @Override
  public void rollback(SingleSign sign) {

    if (log.isDebugEnabled()) {
      log.debug("ENTRA A ROLBACK:  sign.getId() = " +  sign.getId());
    }
    File file = new File(this.filename);
    if (file.exists()) {
      file.delete();
    }
  }
  
  
  public static final long FIVE_MINUTES = 5 * 60 * 1000;
  
  private static long nextCheck = System.currentTimeMillis() + FIVE_MINUTES;
  
  public static List<String> checkProcessedFiles(List<String> ids) {
    
    List<String> pending = new ArrayList<String>();
    
    synchronized (PROP_FILENAME) {
      
      for (String id : ids) {
         if (!processedFiles.containsKey(id)) {
           pending.add(id);
         }
      }

    }
    
    return pending;
    
  }
  
  
  public static void removeProcessedFiles(List<String> ids) {
    synchronized (PROP_FILENAME) {
      for (String id : ids) {
        processedFiles.remove(id);  
      }
      // Eliminar expirades
      final long current = System.currentTimeMillis(); 
      if (current > nextCheck) {
        List<String> toRemove = new ArrayList<String>();

        for(Map.Entry<String, Long> value : processedFiles.entrySet() ) {
          if (current > value.getValue()) {
            toRemove.add(value.getKey());
          }
        }

        for (String id : toRemove) {
          processedFiles.remove(id);  
        }

        nextCheck = System.currentTimeMillis() + FIVE_MINUTES;
      }
      
      
    }
  }
  
}
