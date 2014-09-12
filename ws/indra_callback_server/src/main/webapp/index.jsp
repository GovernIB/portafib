<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="es.indra.www.portafirmasmcgdws.mcgdws.Rejection"%>
<%@page import="es.caib.portafib.callback.impl.MCGDWSSoapBindingImpl"%>
<%@page import="es.indra.www.portafirmasmcgdws.mcgdws.Attributes"%>
<%@page import="es.indra.www.portafirmasmcgdws.mcgdws.Certificate"%>
<%@page import="es.indra.www.portafirmasmcgdws.mcgdws.Delegate"%>
<%@page import="es.indra.www.portafirmasmcgdws.mcgdws.Signer"%>
<%@page import="es.indra.www.portafirmasmcgdws.mcgdws.Document"%>
<%@page import="es.indra.www.portafirmasmcgdws.mcgdws.Application"%>
<%@page import="es.indra.www.portafirmasmcgdws.mcgdws.CallbackRequest"%>
<%@page import="es.caib.portafib.callback.impl.CallBackStorage.CallBackInfo"%>
<%@page import="java.util.Set"%>
<%@page import="es.caib.portafib.callback.impl.CallBackStorage"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%!

public String getState(int state) {
  switch (state) {
    case MCGDWSSoapBindingImpl.DOCUMENTO_BLOQUEADO:
      return "PAUSAT";
      
    
    case MCGDWSSoapBindingImpl.DOCUMENTO_PENDIENTE:
      return "ENPROCES";
     
    
    case MCGDWSSoapBindingImpl.DOCUMENTO_FINALIZADO:
      return "FINALITZAT";
     
    
    case MCGDWSSoapBindingImpl.DOCUMENTO_RECHAZADO:
      return "REBUTJAT";
     
    
    default:
      return "Documento con estado desconocido: " + state;
      
    }

}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Llistat de callbacks INDRA</title>
<style>
body {font-family: Arial;}
</style>
</head>
<body>
<h2>Llistat de Callbacks Rebuts</h2> <h3>(<a href="./v0/PortafirmasCallBack?wsdl">WSDL</a>) </h3>
<table border="1" cellpadding="0" cellspacing="0">
<thead>
   <tr style="background-color: gray; font-weight:bold; color:white; ">
     <td>Data</td>
     <td>Versi&oacute;</td>
     <td>App. ID</td>
     <td>Doc. ID</td>
     <td>Document Atributs</td>
     <td>Firma Info.</td>
     <td>Firma Certificat</td>
     <td>Firma Delegat</td>
     <td>Firma Rebuig</td>
   </tr>
</thead>
<%
SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

Set<CallBackInfo> llistat = CallBackStorage.getAllCallbacks();
for(CallBackInfo info: llistat) {

  CallbackRequest callback = info.getCallBackRequest();

  Application app = callback.getApplication(); 
  Document doc = app.getDocument(); // Peticio de Firma
  
  Attributes atributes = doc.getAttributes();
  
  Signer signer = doc.getSigner(); // = Firma
  Delegate delegat = null;
  Rejection rebuig = null;
  Certificate certificat = null;
  if (signer != null) {
    delegat = signer.getDelegate();  
    rebuig = signer.getRejection();
    certificat = signer.getCertificate();
  }

%>
<tr>
  <td><%=sdf.format(info.getData())%></td>
  <td><%=callback.getVersion()%></td>
  <td><%=app.getId() %></td>
  <td><%=doc.getId()%></td>
  
  <td>
     Estat: <%=getState(atributes.getState().getValue()) %><br>
     T&iacute;tol: <%=atributes.getTitle() %><br>     
     LastUpdate: <%=sdf.format(new Date(atributes.getDateLastUpdate().getTimeInMillis()))%><br>
     External Data: <%=atributes.getExternalData() %>
  </td>
  
  <td>
     <% if (signer == null) { %>
     -
     <% } else { %>
     ID: <%=signer.getId()%><br>
     Data: <%=sdf.format(new Date( signer.getDate().getTimeInMillis())) %><br>
     <% }%>
  </td>
  <td>
     <% if (certificat == null) { %>
     -
     <% } else { %>
     Nom: <b><%= certificat.getSubject()%></b><br>
     Emissior: <b><%=certificat.getIssuer() %></b><br>
     Serial Number: <b><%= certificat.getSerialnumber()%></b>
     <% }%>
  </td>
  <td>    
     <% if (delegat == null) { %> 
       -
     <% } else { %>
      <%=delegat.getId() %><br>
     <% } %>
  </td>
  <td>
     <% if (rebuig == null) { %> 
       -
     <% } else { %>
       Codi:<%=rebuig.getCode() %><br>
       Desc:<%=rebuig.getDescription() %><br>
     <% } %>
  </td>
</tr>


<% } %>

</table>
</body>
</html>