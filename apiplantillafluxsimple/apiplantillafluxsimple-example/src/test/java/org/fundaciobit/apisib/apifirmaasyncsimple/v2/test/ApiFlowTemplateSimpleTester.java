package org.fundaciobit.apisib.apifirmaasyncsimple.v2.test;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import org.fundaciobit.apisib.apiflowtemplatesimple.v1.ApiFlowTemplateSimple;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleEditFlowTemplateRequest;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFilterGetAllByFilter;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFlowTemplate;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFlowTemplateList;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFlowTemplateRequest;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleGetFlowResultResponse;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleGetTransactionIdRequest;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleKeyValue;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleStartTransactionRequest;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleStatus;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleViewFlowTemplateRequest;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.jersey.ApiFlowTemplateSimpleJersey;
import org.fundaciobit.apisib.core.exceptions.AbstractApisIBException;

/**
 * 
 * @author anadal(u80067)
 *
 */
public class ApiFlowTemplateSimpleTester {

  public static final Logger log = Logger.getLogger(ApiFlowTemplateSimpleTester.class);

  public static void main(String[] args) {

    ApiFlowTemplateSimple api = null;

    try {

      Properties prop = new Properties();

      prop.load(new FileInputStream(new File("./apiflowtemplatesimple.properties")));

      final String languageUI = getLanguage(prop);
      

      api = getApiFlowTemplateSimple(prop);
      
      String lastKey;

      // Crear Flux
      final boolean saveOnServer = isSaveOnServer(prop);
      if ((lastKey =testCrearFluxDeFirma(api, languageUI, saveOnServer)) == null) {
        return;
      };

      // Llistar Plantilles
     

      //lastKey = llistarFluxDeFirmes(api, languageUI);
      
      // Llistar Plantilles amb filtre
      //String name = "API REST";
      //String description = null;
      //lastKey = llistarFluxDeFirmesAmbFiltre(api, languageUI, name, description);

      // Mostrar Flux de Firmes
      // mostarFluxDeFirmes(api, languageUI, lastKey);
      
      // Editar Flux de Firmes
      //lastKey = "kWuDt8W-mTGUEawp66KjdA==";
      //editarFluxDeFirmes(api, languageUI, lastKey);

      // Mostrar detalls de flux
      //lastKey = "kWuDt8W-mTGUEawp66KjdA==";
      //descarregarFluxDeFirmesInfo(api, languageUI, lastKey);
      
      //lastKey = "CZm4Cx7uzSOZXGCAC_46vw==";
      //esborrarFluxDeFirmes(api, languageUI, lastKey);

    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
  }

  protected static void mostarFluxDeFirmes(ApiFlowTemplateSimple api, final String languageUI,
      String lastKey) throws AbstractApisIBException, IOException, URISyntaxException {
    FlowTemplateSimpleViewFlowTemplateRequest viewFlowRequest;
    viewFlowRequest = new FlowTemplateSimpleViewFlowTemplateRequest(languageUI, lastKey);
    String url = api.getUrlToViewFlowTemplate(viewFlowRequest);

    System.out.println();
    System.out.println("View Flow Template Url = " + url);

    if (Desktop.isDesktopSupported()) {
      Desktop.getDesktop().browse(new URI(url));
    } else {
      System.out.println("Per favor obri un Navegador i copia-li la URL anterior ...");
    }
  }
  
  
  protected static void editarFluxDeFirmes(ApiFlowTemplateSimple api, final String languageUI,
      String lastKey) throws AbstractApisIBException, IOException, URISyntaxException {
    FlowTemplateSimpleEditFlowTemplateRequest viewFlowRequest;
    viewFlowRequest = new FlowTemplateSimpleEditFlowTemplateRequest(languageUI, lastKey, "http://google.es");
    String url = api.getUrlToEditFlowTemplate(viewFlowRequest);

    System.out.println();
    System.out.println("Edit Flow Template Url = " + url);

    if (Desktop.isDesktopSupported()) {
      Desktop.getDesktop().browse(new URI(url));
    } else {
      System.out.println("Per favor obri un Navegador i copia-li la URL anterior ...");
    }
  }

  protected static void descarregarFluxDeFirmesInfo(ApiFlowTemplateSimple api,
      final String languageUI, String lastKey)
      throws AbstractApisIBException, IOException, URISyntaxException {
    
    FlowTemplateSimpleFlowTemplateRequest request;
    request = new FlowTemplateSimpleFlowTemplateRequest(languageUI, lastKey);

    FlowTemplateSimpleFlowTemplate flow = api.getFlowInfoByFlowTemplateID(request);

    System.out.println(" Flow Template Info = " + flow.getName());

    System.out.println(FlowTemplateSimpleFlowTemplate.toString(flow));

  }
  
  
  
  protected static void esborrarFluxDeFirmes(ApiFlowTemplateSimple api,
      final String languageUI, String lastKey)
      throws AbstractApisIBException, IOException, URISyntaxException {
    
    FlowTemplateSimpleFlowTemplateRequest flowTemplateRequest;
    flowTemplateRequest = new FlowTemplateSimpleFlowTemplateRequest(languageUI, lastKey);

    boolean esborrat = api.deleteFlowTemplate(flowTemplateRequest);

    System.out.println("Delete  Flow Template Info = " + esborrat);

  }


  
  
  
  protected static String llistarFluxDeFirmesAmbFiltre(ApiFlowTemplateSimple api,
      final String languageUI, final String name, final String description) throws AbstractApisIBException {
    
    
    FlowTemplateSimpleFilterGetAllByFilter filtre;
    filtre = new FlowTemplateSimpleFilterGetAllByFilter(languageUI, name, description);
    
    FlowTemplateSimpleFlowTemplateList listHolder = api.getAllFlowTemplatesByFilter(filtre);
    String lastKey = null;
    if (listHolder != null) {

      List<FlowTemplateSimpleKeyValue> list = listHolder.getList();

      System.out.println();
      System.out.println(" ---- LLISTAT PLANTILLES DE FLUX AMB FILTRE-----");

      for (FlowTemplateSimpleKeyValue flowTemplateSimpleKeyValue : list) {
        System.out.println("   " + flowTemplateSimpleKeyValue.getKey() + "  "
            + flowTemplateSimpleKeyValue.getValue());

        lastKey = flowTemplateSimpleKeyValue.getKey();

      }

    }
    return lastKey;
  }
  
  
  protected static String llistarFluxDeFirmes(ApiFlowTemplateSimple api,
      final String languageUI) throws AbstractApisIBException {
    FlowTemplateSimpleFlowTemplateList listHolder = api.getAllFlowTemplates(languageUI);
    String lastKey = null;
    if (listHolder != null) {

      List<FlowTemplateSimpleKeyValue> list = listHolder.getList();

      System.out.println();
      System.out.println(" ---- LLISTAT PLANTILLES DE FLUX -----");

      for (FlowTemplateSimpleKeyValue flowTemplateSimpleKeyValue : list) {
        System.out.println("   " + flowTemplateSimpleKeyValue.getKey() + "  "
            + flowTemplateSimpleKeyValue.getValue());

        lastKey = flowTemplateSimpleKeyValue.getKey();

      }

    }
    return lastKey;
  }

  public static String testCrearFluxDeFirma(ApiFlowTemplateSimple api, String languageUI,
      boolean saveOnServer) throws Exception {

    String transactionID = null;
    try {

      String name = "Prova des de API REST àáèéòó- " + System.currentTimeMillis();
      String descr = "test=true;\n" + "user=anadal\n";
      final boolean visibleDescription = false;

      FlowTemplateSimpleGetTransactionIdRequest transactionRequest;
      transactionRequest = new FlowTemplateSimpleGetTransactionIdRequest(languageUI,
          saveOnServer, name, descr, visibleDescription);

      // Enviam informació bàsica
      transactionID = api.getTransactionID(transactionRequest);

      System.out.println("Language      = |" + languageUI + "|");
      System.out.println("SaveOnServer  = |" + saveOnServer + "|");
      System.out.println("TransactionID = |" + transactionID + "|");

      int port = 1989 + (int) (Math.random() * 100.0);
      final String returnUrl = "http://localhost:" + port + "/returnurl/" + transactionID;
      
      // Per ara només suportam FULLVIEW
      FlowTemplateSimpleStartTransactionRequest startTransactionInfo;
      startTransactionInfo = new FlowTemplateSimpleStartTransactionRequest(transactionID,
          returnUrl);

      String redirectUrl = api.startTransaction(startTransactionInfo);

      System.out.println("RedirectUrl = " + redirectUrl);

      if (Desktop.isDesktopSupported()) {
        Desktop.getDesktop().browse(new URI(redirectUrl));
      } else {
        System.out.println("Per favor obri un Navegador i copia-li la URL anterior ...");
      }

      readFromSocket(port);

      FlowTemplateSimpleGetFlowResultResponse fullResult = api
          .getFlowTemplateResult(transactionID);

      FlowTemplateSimpleStatus transactionStatus = fullResult.getStatus();

      int status = transactionStatus.getStatus();

      switch (status) {

        case FlowTemplateSimpleStatus.STATUS_INITIALIZING: // = 0;
          throw new Exception(
              "S'ha rebut un estat inconsistent del procés de construcció del flux."
                  + " (Inialitzant). Consulti amb el seu administrador.");

        case FlowTemplateSimpleStatus.STATUS_IN_PROGRESS: // = 1;
          throw new Exception("S'ha rebut un estat inconsistent de construcció del flux "
              + " (En Progrés). Consulti amb el seu administrador.");

        case FlowTemplateSimpleStatus.STATUS_FINAL_ERROR: // = -1;
        {
          System.err.println(
              "Error durant la construcció del flux: " + transactionStatus.getErrorMessage());
          String desc = transactionStatus.getErrorStackTrace();
          if (desc != null) {
            System.err.println(desc);
          }
          return null;
        }

        case FlowTemplateSimpleStatus.STATUS_CANCELLED: // = -2;
        {
          System.err.println("L'usuari ha cancelat la construcció del flux");
          return null;
        }

        case FlowTemplateSimpleStatus.STATUS_FINAL_OK: // = 2;
        {
          FlowTemplateSimpleFlowTemplate flux = fullResult.getFlowInfo();

          System.out.println(FlowTemplateSimpleFlowTemplate.toString(flux));

          return flux.getIntermediateServerFlowTemplateId();

        } // Final Case Firma OK

        default: {
          System.err.println("Codi d'estat de finalització desconegut (" + status + ")");
          return null;
        }

      } // Final Switch Firma

    } finally {
      if (api != null && transactionID != null) {
        try {
          api.closeTransaction(transactionID);
        } catch (Throwable th) {
          th.printStackTrace();
        }
      }
    }

  }

  /**
   * 
   * @param port
   * @throws Exception
   */
  public static void readFromSocket(int port) throws Exception {

    ServerSocket serverSocket = new ServerSocket(port);
    System.err.println("Servidor escoltant al PORT: " + port);
    {
      Socket clientSocket = serverSocket.accept();
      System.err
          .println("Nou Client Connectat desde " + clientSocket.getRemoteSocketAddress());

      BufferedReader in = new BufferedReader(
          new InputStreamReader(clientSocket.getInputStream()));
      PrintWriter out = new PrintWriter(
          new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())), true);

      String s;
      System.err.println(" =========================== ");
      while ((s = in.readLine()) != null) {
        System.out.println(s);
        break;
      }
      System.err.println(" =========================== ");

      out.println("HTTP/1.0 200 OK");
      out.println("Content-Type: text/html");
      out.println("\r\n");
      out.println(
          "<html><body>OK (Revisi consola per saber l'estat final del proc&eacute;s de Firma)</body></html>");

      System.err.println("Connexio amb el client finalitzada.");
      out.flush();
      out.close();
      in.close();
      clientSocket.close();
    }

    serverSocket.close();
  }

  protected static String getLanguage(Properties testProperties) {
    return testProperties.getProperty("language");
  }

  protected static boolean isSaveOnServer(Properties testProperties) {
    return "true".equals(testProperties.getProperty("saveonserver"));
  }

  /**
   * 
   * @return
   * @throws Exception
   */
  public static ApiFlowTemplateSimple getApiFlowTemplateSimple(Properties testProperties)
      throws Exception {

    String host = testProperties.getProperty("endpoint");
    String username = testProperties.getProperty("username");
    System.out.println(" Connectant amb " + host + " emprant l'usuari " + username);

    return new ApiFlowTemplateSimpleJersey(host, username,
        testProperties.getProperty("password"));

  }

}
