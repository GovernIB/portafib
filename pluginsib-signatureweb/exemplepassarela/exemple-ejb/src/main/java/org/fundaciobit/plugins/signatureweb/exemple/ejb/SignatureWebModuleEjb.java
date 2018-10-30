package org.fundaciobit.plugins.signatureweb.exemple.ejb;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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
import org.fundaciobit.plugins.signature.api.FileInfoSignature;
import org.fundaciobit.plugins.signature.api.StatusSignaturesSet;
import org.fundaciobit.plugins.signature.utils.Plugin;
import org.fundaciobit.plugins.signatureweb.exemple.ejb.utils.ExempleSignaturesSet;
import org.fundaciobit.plugins.signatureweb.exemple.ejb.utils.SignatureWebPluginManager;


/**
 *
 * @author anadal
 *
 */
@Stateless(name = "SignatureWebModuleEJB")
public class SignatureWebModuleEjb implements SignatureWebModuleLocal {

  protected static Logger log = Logger.getLogger(SignatureWebModuleEjb.class);
  

  private static final Map<String, ExempleSignaturesSet> signaturesSetsMap = new HashMap<String, ExempleSignaturesSet>();


  private static long lastCheckFirmesCaducades = 0;

  @Override
  public List<Plugin> getAllPluginsFiltered(HttpServletRequest request, String signaturesSetID) throws Exception {


    ExempleSignaturesSet signaturesSet = getSignaturesSet(request, signaturesSetID);

    Map<Plugin, ISignatureWebPlugin> allPlugins = getAllPlugins();


    // 2.- Passa el filtre ...
    final Map<String, Object> parameters = new HashMap<String, Object>();
    List<Plugin> pluginsFiltered = new ArrayList<Plugin>();  
    for (Plugin pluginDeFirma : allPlugins.keySet()) {
      ISignatureWebPlugin signaturePlugin =  allPlugins.get(pluginDeFirma);
      String error = signaturePlugin.filter(request, signaturesSet, parameters);
      if (error == null) {
          pluginsFiltered.add(pluginDeFirma);
      } else {
          // Exclude Plugin
          log.info("Exclos plugin [" + pluginDeFirma.getNom() + "]: " + error);
      }
      
    }

    return  pluginsFiltered;
  
  }




 @Override
  public Map<Plugin, ISignatureWebPlugin> getAllPlugins()
      throws Exception {
   
   // TODO CHECK signature Set
   List<Plugin> plugins = SignatureWebPluginManager.getInstance().getAllPlugins();
   if (plugins == null || plugins.size() == 0) {
     String msg = "S'ha produit un error llegint els plugins o no se n'han definit.";
     throw new Exception(msg);
   }
   
   
    Map<Plugin, ISignatureWebPlugin> allPlugins = new HashMap<Plugin, ISignatureWebPlugin>();
    
    for (Plugin pluginDeFirma : plugins) {
      // 1.- Es pot instanciar el plugin ?
      ISignatureWebPlugin signaturePlugin;
      signaturePlugin = SignatureWebPluginManager.getInstance().getInstanceByPluginID(
            pluginDeFirma.getPluginID());
      
      
      if (signaturePlugin == null) {
        throw new Exception("No s'ha pogut instanciar Plugin amb ID " + pluginDeFirma.getPluginID());
      }
      
      allPlugins.put(pluginDeFirma, signaturePlugin);
    }
    return allPlugins;
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
    
    signaturePlugin = SignatureWebPluginManager.getInstance().getInstanceByPluginID(pluginID);

    if (signaturePlugin == null) {
      String msg = "plugin.signatureweb.noexist: " + String.valueOf(pluginID);
      throw new Exception( msg);
    }

    String urlToPluginWebPage;
    final Map<String, Object> parameters = new HashMap<String, Object>();
    urlToPluginWebPage = signaturePlugin.signDocuments(request, absoluteRequestPluginBasePath,
        relativeRequestPluginBasePath, signaturesSet, parameters);

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
      String query, boolean isPost)  throws Exception {


//    
//    log.info("requestPlugin():: " + request.getPathInfo());
//    log.info("requestPlugin()::getRequestURI: " + request.getRequestURI());
//    log.info("requestPlugin()::getQueryString: " + request.getQueryString());
//   
// 
//    StackTraceElement[] trace = new Exception().getStackTrace();
//    for (int i = 0; i < 4; i++) {
//      log.info("TRACE " + trace[i].getClassName() + "::" + trace[i].getMethodName()+ " [" + trace[i].getLineNumber()+ "]");
//    }
//    
    
    ExempleSignaturesSet ss = getSignaturesSet(request, signaturesSetID);
    log.info("requestPlugin()::ExempleSignaturesSet ss = " + ss);
    
    
   
   if (ss == null) {
     response.sendError(HttpServletResponse.SC_REQUEST_URI_TOO_LONG, 
         "Proces de Firma amb ID " + signaturesSetID + " ha caducat !!!!");
     
     return;
   }
   
   
    
    long pluginID = ss.getPluginID();
    
    log.info(" ExempleSignaturesSet pluginID = ss.getPluginID(); =>  " + pluginID);
    
    ISignatureWebPlugin signaturePlugin;
    try {
      signaturePlugin = SignatureWebPluginManager.getInstance().getInstanceByPluginID(pluginID);
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
          signaturesSetID, signatureIndex, request, response);
    } else {
      signaturePlugin.requestGET(absoluteRequestPluginBasePath,
          relativeRequestPluginBasePath, query,
          signaturesSetID, signatureIndex, request, response);
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
        signaturePlugin = SignatureWebPluginManager.getInstance().getInstanceByPluginID(pluginID);
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
  
  private class Column {
    
    public static final int FLAG_CAP = 0;
    
    public static final int FLAG_SEGELL_TEMPS_INTERN = 1;
    public static final int FLAG_SEGELL_TEMPS_EXTERN = 2;
    
    public static final int FLAG_PADES_TAULA_FIRMES_INTERN = 3;
    
    public static final int FLAG_PADES_TAULA_FIRMES_EXTERN = 4;
    
    public static final int FLAG_PADES_ESTAMPAT_CSV_INTERN = 5;
    
    public static final int FLAG_PADES_ESTAMPAT_CSV_EXTERN = 6;
    
    public static final int FLAG_PADES_CODI_BARRES = 7;
    
    
    final String titol;
    
    final String signType;
    
    final int flag;

    /**
     * @param titol
     * @param signType
     * @param flag
     */
    public Column(String titol, String signType, int flag) {
      super();
      this.titol = titol;
      this.signType = signType;
      this.flag = flag;
    }
    
    
    

  }
  
  
  
  @Override
  public String[][] generateMatrixPluginInformation() throws Exception {

    final String[] signTypes = { FileInfoSignature.SIGN_TYPE_PADES,
        FileInfoSignature.SIGN_TYPE_XADES, FileInfoSignature.SIGN_TYPE_CADES,
        FileInfoSignature.SIGN_TYPE_SMIME };

    List<Column> columnes = new ArrayList<SignatureWebModuleEjb.Column>();

    final String CHECK = "<font color=\"green\">&#10004;</font>";

    final String NOCHECK = "<font color=\"red\">&#10008;</font>";

    Map<Plugin, ISignatureWebPlugin> map = this.getAllPlugins();

    // TITOLS

    for (String signType : signTypes) {

      columnes.add(new Column(signType, signType, Column.FLAG_CAP));
      columnes.add(new Column(signType + "-T<br/>Intern(*)", signType,
          Column.FLAG_SEGELL_TEMPS_INTERN));
      columnes.add(new Column(signType + "-T<br/>Extern(**)", signType,
          Column.FLAG_SEGELL_TEMPS_EXTERN));

      if (signType.equals(FileInfoSignature.SIGN_TYPE_PADES)) {
        columnes.add(new Column(signType + "<br/><small>Estampat R&uacute;brica</small><br/>Intern",
            signType, Column.FLAG_PADES_TAULA_FIRMES_INTERN));
        columnes.add(new Column(signType + "<br/><small>Estampat R&uacute;brica</small><br/>Extern",
            signType, Column.FLAG_PADES_TAULA_FIRMES_EXTERN));
        columnes.add(new Column(signType + "<br/><small>Estampat CSV</small><br/>Intern",
            signType, Column.FLAG_PADES_ESTAMPAT_CSV_INTERN));
        //columnes.add(new Column(signType + "<br/><small>Codi de Barres<br/>(CSV Intern)</small>", signType,
        //    Column.FLAG_PADES_CODI_BARRES));
        columnes.add(new Column(signType + "<br/><small>Estampat CSV</small><br/>Extern",
            signType, Column.FLAG_PADES_ESTAMPAT_CSV_EXTERN));

      }

    }

    // Plugins
    Map<Plugin, Map<Column, String>> valuesByPlugin = new HashMap<Plugin, Map<Column, String>>();
    for (Plugin plugin : map.keySet()) {

      ISignatureWebPlugin instancia = map.get(plugin);

      String[] types = instancia.getSupportedSignatureTypes();
      
      List<String> allTypes;
      if (types == null) {
        allTypes = new ArrayList<String>(); 
      } else {
        allTypes = Arrays.asList(types);
      }

      Map<Column, String> valors = new HashMap<SignatureWebModuleEjb.Column, String>();

      for (Column c : columnes) {

        switch (c.flag) {

        case Column.FLAG_CAP:
          valors.put(c, allTypes.contains(c.signType) ? CHECK : NOCHECK);
          break;

        case Column.FLAG_SEGELL_TEMPS_INTERN:
          boolean provides = instancia.providesTimeStampGenerator(c.signType);
          valors.put(c, provides ? CHECK : NOCHECK);
          break;

        case Column.FLAG_SEGELL_TEMPS_EXTERN:
          boolean external = instancia.acceptExternalTimeStampGenerator(c.signType);
          valors.put(c, external ? CHECK : NOCHECK);
          break;

        case Column.FLAG_PADES_TAULA_FIRMES_INTERN:
          boolean providest = instancia.providesRubricGenerator();
          valors.put(c, providest ? CHECK : NOCHECK);
          break;

        case Column.FLAG_PADES_TAULA_FIRMES_EXTERN:
          boolean externalt = instancia.acceptExternalTimeStampGenerator(c.signType);
          valors.put(c, externalt ? CHECK : NOCHECK);
          break;

        case Column.FLAG_PADES_ESTAMPAT_CSV_INTERN:
          boolean estampat_i = instancia.providesSecureVerificationCodeStamper();
          //valors.put(c, estampat_i ? CHECK : NOCHECK);
          
          if (estampat_i) {
            List<String> bctypes = instancia.getSupportedBarCodeTypes();
            if (bctypes == null) {
              bctypes = new ArrayList<String>();
            }

            String cb = Arrays.toString(bctypes.toArray());
            cb = cb.replace("[", "").replace("]", "").replace(",", "<br/>");
            valors.put(c, CHECK + "<br/><small>("+ cb + ")</small>");
          } else {
            valors.put(c, NOCHECK);
          }
          
          
          break;

        case Column.FLAG_PADES_ESTAMPAT_CSV_EXTERN:
          boolean estampat_e = instancia.acceptExternalSecureVerificationCodeStamper();
          valors.put(c, estampat_e ? CHECK : NOCHECK);
          break;

        case Column.FLAG_PADES_CODI_BARRES:

          boolean estampat_i2 = instancia.providesSecureVerificationCodeStamper();
          if (estampat_i2) {
            String cb = Arrays.toString(instancia.getSupportedBarCodeTypes().toArray());
            cb = cb.replace("[", "").replace("]", "").replace(",", "<br/>");
            valors.put(c, "<small>"+ cb + "</small>");
          } else {
            valors.put(c, "&nbsp;");
          }

          break;

        }

      }

      valuesByPlugin.put(plugin, valors);

    }

    String[][] matrix;

    matrix = new String[map.size() + 1][columnes.size() + 1];

    // TITOLS
    matrix[0][0] = "<b>Plugin</b>";
    int i = 1;
    for (Column c : columnes) {
      matrix[0][i] = c.titol;
      i++;
    }

    Set<Plugin> pluginsSet = map.keySet();
    List<Plugin> pluginsList = new ArrayList<Plugin>(pluginsSet);

    Collections.sort(pluginsList, new Comparator<Plugin>() {
      public int compare(Plugin o1, Plugin o2) {
        return o1.getDescripcioCurta().compareTo(o2.getDescripcioCurta());
      }
    });

    // Valors
    int row = 1;
    for (Plugin plugin : pluginsList) {

      matrix[row][0] = plugin.getDescripcioCurta();

      Map<Column, String> valors = valuesByPlugin.get(plugin);

      i = 1;
      for (Column c : columnes) {
        matrix[row][i] = valors.get(c);
        i++;
      }
      row++;
    }

    return matrix;
  }


}
