<%@page import="org.apache.commons.io.FileUtils"%>
<%@page import="java.io.InputStream"
%><%@page import="java.io.ByteArrayOutputStream"
%><%@page import="java.net.URL"
%><%@page import="java.io.File"
%><%@page import="org.apache.log4j.Logger"
%><%@page import="java.net.HttpURLConnection"
%><%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%><%!
  
  protected final Logger log = Logger.getLogger(this.getClass());
  
  private static final int BUFFER_SIZE = 4096;
  
  
  private static final boolean REDIRECT = true;
  
  
  private static final boolean CACHE = false;
  
  
  
  public static Boolean quefer = null; 

  public static String contentDeployJava = null;
  
  public static String downloadDeployJava(String urlPath,  HttpServletRequest request) {
    if (contentDeployJava == null) {
      try {
        URL url = new URL(urlPath);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        int responseCode = httpConn.getResponseCode();
    
        // always check HTTP response code first
        if (responseCode == HttpURLConnection.HTTP_OK) {
          // opens input stream from the HTTP connection
          InputStream inputStream = httpConn.getInputStream();
         
          // opens an output stream to save into file
          ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      
          int bytesRead = -1;
          byte[] buffer = new byte[BUFFER_SIZE];
          while ((bytesRead = inputStream.read(buffer)) != -1) {
              outputStream.write(buffer, 0, bytesRead);
          }

          inputStream.close();

          setContentDeployJava(new String(outputStream.toByteArray()), request);
          
        }
      } catch(Throwable th) {
        
      }
    }
    
    return contentDeployJava;

  }
  
  
  public static void setContentDeployJava(String cdj, HttpServletRequest request) {
    contentDeployJava = cdj.replace("http://java.com/dt-redirect",
        request.getContextPath() + "/common/senseSuportJava");
  }
  
  
  
  /*
  public static boolean exists(String URLName){
    try {
      HttpURLConnection.setFollowRedirects(false);
      // note : you may also need
      //        HttpURLConnection.setInstanceFollowRedirects(false)
      HttpURLConnection con = (HttpURLConnection) new URL(URLName).openConnection();
      con.setRequestMethod("HEAD");
      return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
    }
    catch (Exception e) {
       e.printStackTrace();
       return false;
    }
  }
  */

%><%

   if (quefer == null) {

     final String url = "http://java.com/js/deployJava.js";
     String content = downloadDeployJava(url, request);
     if (content == null) {

       try {
         
         //System.out.println(" Context Path = " + request.getContextPath());
         
         String localPath = "/js/deployJava.js";

         File localJS = new File(session.getServletContext().getRealPath(localPath));
         
         //System.out.println("localJS: " + localJS.getAbsolutePath());
       
         setContentDeployJava(FileUtils.readFileToString(localJS), request);
         
         log.info("deployjava.jsp  ==> utilitzam CACHE del contingut de " + localPath);
         quefer = CACHE;
         
       } catch(Throwable th) {
          log.info("deployjava.jsp  ==> utilitzam REDIRECT a /js/deployJava.js");
          quefer = REDIRECT;
       }
     } else {
       log.info("deployjava.jsp  ==> utilitzam CACHE del contingut de " + url);
       quefer = CACHE;
     }
   }

  if (quefer == REDIRECT) {
    response.sendRedirect(request.getContextPath() + "/js/deployJava.js");
  } else {
    // CACHE
    out.write(contentDeployJava);
    out.flush();
  }
%>