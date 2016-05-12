package org.fundaciobit.plugins.signatureweb.exemple.ejb;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.fundaciobit.plugins.signatureweb.api.ISignatureWebPlugin;
import org.fundaciobit.plugins.signatureweb.api.StatusSignaturesSet;
import org.fundaciobit.plugins.signatureweb.api.IUploadedFile;
import org.fundaciobit.plugins.signatureweb.exemple.ejb.utils.ExempleSignaturesSet;
import org.fundaciobit.plugins.signatureweb.exemple.ejb.utils.Plugin;
import org.fundaciobit.plugins.signatureweb.exemple.ejb.utils.SignatureWebPluginManager;


/**
 *
 * @author anadal
 *
 */
@Stateless(name = "SignatureModuleEJB")
public class SignatureModuleEjb implements SignatureModuleLocal {

  protected static Logger log = Logger.getLogger(SignatureModuleEjb.class);

  
  @Override
  public List<Plugin> getAllPluginsFiltered(HttpServletRequest request, String signaturesSetID) throws Exception {


    ExempleSignaturesSet signaturesSet = getSignaturesSet(request, signaturesSetID);
    
 
    // TODO CHECK signature Set
    List<Plugin> plugins = SignatureWebPluginManager.getAllPlugins();
    if (plugins == null || plugins.size() == 0) {
      String msg = "S'ha produit un error llegint els plugins o no se n'han definit.";
      throw new Exception(msg);
    }
    
    List<Plugin> pluginsFiltered = new ArrayList<Plugin>();
    
    ISignatureWebPlugin signaturePlugin;
 
    
    for (Plugin pluginDeFirma : plugins) {
      // 1.- Es pot instanciar el plugin ?
      signaturePlugin = SignatureWebPluginManager.getInstanceByPluginID(
            pluginDeFirma.getPluginID());
      
      
      if (signaturePlugin == null) {
        throw new Exception("No s'ha pogut instanciar Plugin amb ID " + pluginDeFirma.getPluginID());
      }


      // 2.- Passa el filtre ...
      
      if (signaturePlugin.filter(request, signaturesSet)) {
          pluginsFiltered.add(pluginDeFirma);
      } else {
          // Exclude Plugin
          log.info("Exclos plugin [" + pluginDeFirma.getNom() + "]: NO PASSA FILTRE");
      }
      
    }

    return  pluginsFiltered;
  
  }

  
  

  
  public ExempleSignaturesSet finalProcesDeFirma(HttpServletRequest request,
      String signaturesSetID) throws Exception {

    ExempleSignaturesSet pss = getSignaturesSet(request, signaturesSetID);

    // Check pss is null
    if (pss == null) {
      String msg = "moduldefirma.caducat: " + signaturesSetID;
      throw new Exception(msg);
    }

    StatusSignaturesSet sss = pss.getStatusSignaturesSet();
    if (sss.getStatus() == StatusSignaturesSet.STATUS_INITIALIZING 
        || sss.getStatus() ==  StatusSignaturesSet.STATUS_IN_PROGRESS) {
      // Vull presuposar que si i que el mòdul de firma s'ha oblidat d'indicar aquest fet ???
      sss.setStatus(StatusSignaturesSet.STATUS_FINAL_OK);
    }

    return pss;
    
  }



  
  public String signDocuments(
      HttpServletRequest request, String absoluteRequestPluginBasePath,
      String relativeRequestPluginBasePath,
      
      String signaturesSetID) throws Exception {
    
    ExempleSignaturesSet signaturesSet = getSignaturesSet(request, signaturesSetID);
    

    Long pluginID = signaturesSet.getPluginID();    
    
    log.info("SMC :: showsignaturemodule: PluginID = " + pluginID);
    log.info("SMC :: showsignaturemodule: signaturesSetID = " + signaturesSet.getSignaturesSetID());
    

 // El plugin existeix?
    ISignatureWebPlugin signaturePlugin;
    
    signaturePlugin = SignatureWebPluginManager.getInstanceByPluginID(pluginID);

    if (signaturePlugin == null) {
      String msg = "plugin.signatureweb.noexist: " + String.valueOf(pluginID);
      throw new Exception( msg);
    }

    String urlToPluginWebPage;
    urlToPluginWebPage = signaturePlugin.signDocuments(request, absoluteRequestPluginBasePath,
        relativeRequestPluginBasePath, signaturesSet);

    return urlToPluginWebPage;
   
  }


 
  
  /**
   * 
   * @param request
   * @param response
   * @param pluginID
   * @param signatureID
   * @param signatureIndex
   * @param query
   * @param isPost
   * @throws Exception
   * @throws Exception
   */
  public void requestPlugin(HttpServletRequest request, HttpServletResponse response,
      String absoluteRequestPluginBasePath, String relativeRequestPluginBasePath,
      String signaturesSetID, int signatureIndex, 
      String query, boolean isPost, Map<String, IUploadedFile> uploadedFiles)  throws Exception {


      
      ExempleSignaturesSet ss = getSignaturesSet(request, signaturesSetID);
      
   log.info(" ExempleSignaturesSet ss = " + ss);
    
    
    long pluginID = ss.getPluginID();
    
    log.info(" ExempleSignaturesSet pluginID = ss.getPluginID(); =>  " + pluginID);
    
    ISignatureWebPlugin signaturePlugin;
    try {
      signaturePlugin = SignatureWebPluginManager.getInstanceByPluginID(pluginID);
    } catch (Exception e) {
      
      String msg = "plugin.signatureweb.noexist: " +String.valueOf(pluginID);
      throw new Exception(msg);
    }
    if (signaturePlugin == null) {
      String msg = "plugin.signatureweb.noexist: " + String.valueOf(pluginID);
      throw new Exception(msg);
    }
    
   
   


    if (isPost) {
      signaturePlugin.requestPOST(absoluteRequestPluginBasePath,
          relativeRequestPluginBasePath, query, 
          signaturesSetID, signatureIndex, request, uploadedFiles, response);
    } else {
      signaturePlugin.requestGET(absoluteRequestPluginBasePath,
          relativeRequestPluginBasePath, query,
          signaturesSetID, signatureIndex, request, uploadedFiles, response);
    }

  }
  
  
  // -------------------------------------------------------------------------
  // -------------------------------------------------------------------------
  // ----------------------------- U T I L I T A T  S  ----------------------
  // -------------------------------------------------------------------------
  // -------------------------------------------------------------------------


  public void closeSignaturesSet(HttpServletRequest request, String signaturesSetID) {
    
    ExempleSignaturesSet pss = getSignaturesSet(request, signaturesSetID);
    
    if (pss == null) {
      log.warn("NO Existeix signaturesSetID igual a " + signaturesSetID);
      return;
    }
    
    closeSignaturesSet(request, pss);
  }
    
    
 private void closeSignaturesSet(HttpServletRequest request,  ExempleSignaturesSet pss) {
   
   Long pluginID = pss.getPluginID();
    
    final String signaturesSetID = pss.getSignaturesSetID();
    if (pluginID == null) {
      // Encara no s'ha asignat plugin al signatureset
    } else {

      ISignatureWebPlugin signaturePlugin = null;
      try {
        signaturePlugin = SignatureWebPluginManager.getInstanceByPluginID(pluginID);
      } catch (Exception e) {
        log.error(e.getMessage(), e);
        return;
      }
      if (signaturePlugin == null) {
        log.error("plugin.signatureweb.noexist: " + String.valueOf(pluginID));
      }
      
      try {
        signaturePlugin.closeSignaturesSet(request, signaturesSetID);
      } catch (Exception e) {
        log.error("Error borrant dades d'un SignaturesSet " + signaturesSetID 
            + ": " + e.getMessage(), e);
      }
    }
    signaturesSetsMap.remove(signaturesSetID);
  }
  

  private static final Map<String, ExempleSignaturesSet> signaturesSetsMap = new HashMap<String, ExempleSignaturesSet>();


  private static long lastCheckFirmesCaducades = 0;
  

  
  


  
  
  
 
  
  /**
   * Fa neteja
   * 
   * @param signaturesSetID
   * @return
   */
  public ExempleSignaturesSet getSignaturesSet(HttpServletRequest request,
      String signaturesSetID) {
    // Fer net peticions caducades SignaturesSet.getExpiryDate()
    // Check si existeix algun proces de firma caducat s'ha d'esborrar
    // Com a mínim cada minut es revisa si hi ha caducats
    Long now = System.currentTimeMillis();
    
    final long un_minut_en_ms =  60 * 60 * 1000;
    
    if (now + un_minut_en_ms > lastCheckFirmesCaducades) {
      lastCheckFirmesCaducades = now;
      List<ExempleSignaturesSet> keysToDelete = new ArrayList<ExempleSignaturesSet>();
      
      Set<String> ids = signaturesSetsMap.keySet();
      for (String id : ids) {
        ExempleSignaturesSet ss = signaturesSetsMap.get(id);
        if (now > ss.getExpiryDate().getTime()) {
          keysToDelete.add(ss);
          SimpleDateFormat sdf = new SimpleDateFormat();
          log.info("Tancant Signature SET amb ID = " + id + " a causa de que està caducat "
              + "( ARA: " + sdf.format(new Date(now)) + " | CADUCITAT: " + sdf.format(ss.getExpiryDate()) + ")");
        }
      }
      
      if (keysToDelete.size() != 0) {
        synchronized (signaturesSetsMap) {

          for (ExempleSignaturesSet pss : keysToDelete) {
            closeSignaturesSet(request, pss);
          }
        }
      }
    }

    return signaturesSetsMap.get(signaturesSetID);
  }





  @Override
  public void startSignatureProcess(ExempleSignaturesSet signaturesSet) {

    synchronized (signaturesSetsMap) {
     final String signaturesSetID = signaturesSet.getSignaturesSetID();

     signaturesSetsMap.put(signaturesSetID, signaturesSet);
    }
    
  }



}
