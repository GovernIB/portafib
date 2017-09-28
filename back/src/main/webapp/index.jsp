<%@page import="es.caib.portafib.logic.utils.PropietatGlobalUtil"
%><%@page import="org.apache.log4j.Logger"
%><%@page import="es.caib.portafib.utils.Configuracio"
%><%@ page contentType="text/html;charset=UTF-8" language="java" 
%><%@page import="java.io.InputStream"
%><%@page import="java.util.Properties"
%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" 
%><%!

protected final Logger log = Logger.getLogger(getClass());

public static Boolean existsSecureContextStatic = null;

public boolean existsSecureContext() {
  if (existsSecureContextStatic == null) {
    try {  
      InputStream propData = getClass().getClassLoader().getResourceAsStream("APP-INF/classes/clientcert.properties");
      if (propData == null) {
        existsSecureContextStatic = false;
      } else {
        
        Properties properties = new Properties(); 
        properties.load(propData);
        String value = properties.getProperty("portafib.clientcert");
        existsSecureContextStatic = "true".equals(value);
      }
    } catch(Exception e) {
      existsSecureContextStatic = false;    
    }
  }
  return existsSecureContextStatic.booleanValue();
}


%><%
    String context = request.getContextPath();

  
    if (PropietatGlobalUtil.isAutomaticRedirect()) {
         
       boolean isDebug = log.isDebugEnabled();
       if (isDebug) {
         log.debug("request.getContextPath() ==> " + context);
       }
      
        //TODO ficar dins una variable estatica
        boolean existsSecureContext = existsSecureContext(); 
        
        if (isDebug) {
          log.debug("existsSecureContext ==> " + existsSecureContext);
        }
        
        if (existsSecureContext) {
          
          final String scheme = request.getScheme();
          
          if (isDebug) {
            log.debug("scheme ==> " + scheme);
          }
          final boolean thisContextIsClientCert = "/portafib/s".equals(context);
          
          if (thisContextIsClientCert && "http".equals(scheme)) {
            // Hem de passar a portafib
            context = "/portafib";
          } else {
            // Hem de passar a portafibs
            if (!thisContextIsClientCert && "https".equals(scheme)) {
              context ="/portafib/s";
            }
          }
        }
    }
    
    request.getSession().setAttribute("theContext", context);


%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head></head>
<body>
<c:redirect context="${theContext}" url="/common/principal.html"/>
</body>
</html>
