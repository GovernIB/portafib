package es.caib.portafib.logic.misc;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.portafib.logic.utils.EmailInfo;
import es.caib.portafib.logic.utils.EmailUtil;

// 
/**
 * 
 * @author anadal
 *
 */
public class EnviarCorreusAgrupatsUtils {
  
  protected static final Logger log = Logger.getLogger(EnviarCorreusAgrupatsUtils.class);

  // TODO Això ho ha de llegir de BBDD
  /**
   * Conte fitxers del tipus [usuari-entitat-id]_[eventid]. Si existeix aquest
   * fitxer llavors indica que el mails dirigits a [usuari-entitat-id] que
   * contenguin un avis del tipus [eventid] s'han d'agrupar.
   */
  public static final String BASE_PATH_REBREAVIS = "REBREAVIS";

  public static final String BASE_PATH_AGRUPAR_CORREUS = "CORREUSAGRUPATS";

  public static boolean isAgruparCorreus(final String usuariEntitatId, final long eventID) {
    File rebreAvisGrupatFile = getFile(usuariEntitatId, eventID, BASE_PATH_REBREAVIS);
    return rebreAvisGrupatFile.exists();
  }

  public static void setAgruparCorreus(final String usuariEntitatId, final long eventID,
      boolean agruparCorreus) {
    File rebreAvisGrupatFile = getFile(usuariEntitatId, eventID, BASE_PATH_REBREAVIS);

    if (agruparCorreus) {
      try {
        new java.io.FileOutputStream(rebreAvisGrupatFile).close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    } else {
      rebreAvisGrupatFile.delete();
    }

  }

  public static File getFile(final String usuariEntitatId, final long eventID,
      final String basePath) {
    
    File rebreAvisDir = new File(FileSystemManager.getFilesPath(), basePath);
    rebreAvisDir.mkdirs();
    if (usuariEntitatId == null) {
      return rebreAvisDir;
    } else {
      String filename = usuariEntitatId + "_" + eventID;
      File rebreAvisGrupatFile = new File(rebreAvisDir, filename);
      return rebreAvisGrupatFile;
    }
  }

  public static File saveAvisAgrupat(final String usuariEntitatId, final long eventID,
      EmailInfo emailInfo) throws Exception {
    
    synchronized (BASE_PATH_AGRUPAR_CORREUS) {

    File f = getFile(usuariEntitatId, eventID, BASE_PATH_AGRUPAR_CORREUS);

    FileOutputStream fos = new FileOutputStream(f, true);
    PrintStream ps = new PrintStream(fos);

    JAXBContext jc = JAXBContext.newInstance(EmailInfo.class);
    Marshaller m = jc.createMarshaller();

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    m.marshal(emailInfo, baos);

    ps.println(URLEncoder.encode(new String(baos.toByteArray()), "UTF-8"));

    ps.flush();
    ps.close();

    fos.flush();
    fos.close();

    return f;
    
    }

  }

  public static void enviarAvisosAgrupats() throws Exception {
    
    final boolean isDebug = log.isDebugEnabled();
    
    if (isDebug) { 
      log.debug("Iniciant enviament de correus agrupats ...");
    }
    
    File[] emailsAgrupats;
    synchronized (BASE_PATH_AGRUPAR_CORREUS) {
      
      File dirAgrupats = getFile(null, 0, BASE_PATH_AGRUPAR_CORREUS);

      emailsAgrupats = dirAgrupats.listFiles();
     
    }

    for (File file : emailsAgrupats) {
      String filename = file.getName();
      if (isDebug) { 
        log.debug("Processant fitxer de correus agrupats: " + filename);
      }
      
      EmailInfo email;
      synchronized (BASE_PATH_AGRUPAR_CORREUS) {
            
        List<EmailInfo> list = readEmailsFromFile(file);
        
        if (list != null && !list.isEmpty()) {
          
          email = list.get(0);
          
          StringBuffer html = new StringBuffer();
          for (EmailInfo emailInfo : list) {
            if (html.length() != 0) {
              html.append("<br/><br/><hr/><br/><br/>");
            }
            html.append(emailInfo.getMessage());
          }
          
          email.setMessage(html.toString());
        } else {
          email = null;
        }
        
        
        
        if(!file.delete()) {
          log.error("NO PUC BORRARR FITXER [" + file.getAbsolutePath() + "]", new Exception());
        };
        
      }
      
      if (email != null) {
        email.setHtml(true);
        // Això evita que ho torni a guardar per enviar més endavant 
        email.setUsuariEntitatID(null);
        email.setEventID(-1);
        ArrayList<EmailInfo> one = new ArrayList<EmailInfo>();
        one.add(email);
        try {
          EmailUtil.enviarMails(one);
          if (isDebug) { 
            log.debug("Enviat correu agrupat a " + email.getSubject());
          }
        } catch (I18NException e) {
          log.error("Error enviant correu a " + email.getSubject() + " - " 
            + email.getUsuariEntitatID() + "(" + e.getMessage() + "):\n" 
            + email.getMessage(), e);
        }
        // Per no saturar (1) el servidor, (2) ni l'enviament de correus (3) ni la firma de sol·licituds 
        Thread.sleep(1000);
      }
    
    }
    
    if (isDebug) { 
      log.debug("Final enviament de correus agrupats ...");
    }
  }

  protected static List<EmailInfo> readEmailsFromFile(File f) throws FileNotFoundException,
      JAXBException, IOException, UnsupportedEncodingException {

    if (!f.exists()) {
      return new ArrayList<EmailInfo>();
    }

    FileReader fr = new FileReader(f);
    BufferedReader br = new BufferedReader(fr);
    Object obj;

    JAXBContext jc = JAXBContext.newInstance(EmailInfo.class);
    Unmarshaller u = jc.createUnmarshaller();

    ArrayList<EmailInfo> list = new ArrayList<EmailInfo>();
    do {

      String lineEnc = br.readLine();

      if (lineEnc == null) {
        break;
      }

      String lineXml = URLDecoder.decode(lineEnc, "UTF-8");

      obj = u.unmarshal(new ByteArrayInputStream(lineXml.getBytes()));

      if (obj != null) {
        list.add(((EmailInfo) obj));
      }

    } while (true);

    br.close();
    fr.close();

    return list;
  }

  

  
  // TEST
  public static void main(String[] args) {
    try {

      EmailInfo info1 = new EmailInfo(234L, "anadal@fundaciobit.org", "Holaà cáarïacol·la",
          "HOLA CARAC OAL MESSAGE", true, "fundaciobit_anadal", 0L);

      EmailInfo info2 = new EmailInfo(2345L, "anadal22@fundaciobit.org", "Hòla caraöcoçñla 2",
          "HOLA CARAC\r\n OAL MESSAGE\r552\n422", true, "fundaciobit_anadal", 0L);
      File f = new File("c:\\tmp\\hola.xml");

      EmailInfo[] emails = { info1, info2 };

      for (EmailInfo emailInfo : emails) {
        FileOutputStream fos = new FileOutputStream(f, true);
        PrintStream ps = new PrintStream(fos);

        JAXBContext jc = JAXBContext.newInstance(EmailInfo.class);
        // Unmarshaller u = jc.createUnmarshaller();
        // Object element = u.unmarshal( new File( "foo.xml" ) );
        Marshaller m = jc.createMarshaller();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        m.marshal(emailInfo, baos);

        ps.println(URLEncoder.encode(new String(baos.toByteArray()), "UTF-8"));

        ps.flush();
        ps.close();

        fos.flush();
        fos.close();
      }

      FileReader fr = new FileReader(f);
      BufferedReader br = new BufferedReader(fr);
      Object obj;

      JAXBContext jc = JAXBContext.newInstance(EmailInfo.class);
      Unmarshaller u = jc.createUnmarshaller();

      do {

        String lineEnc = br.readLine();

        if (lineEnc == null) {
          break;
        }

        String lineXml = URLDecoder.decode(lineEnc, "UTF-8");

        obj = u.unmarshal(new ByteArrayInputStream(lineXml.getBytes()));

        if (obj != null) {
          System.out.println(" INFO READ = " + ((EmailInfo) obj).getSubject());
        }

      } while (true);
      br.close();
      fr.close();

    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }

  }

}
