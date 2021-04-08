<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Set"%>
<%@page import="es.caib.portafib.utils.Constants"%>
<%@page import="es.caib.portafib.callback.beans.v1.PortaFIBEvent"%>
<%@page import="es.caib.portafib.callback.beans.v1.Actor"%>
<%@page import="es.caib.portafib.callback.beans.v1.Sign"%>
<%@page import="es.caib.portafib.callback.beans.v1.SigningRequest"%>
<%@page import="es.caib.portafib.callback.beans.v1.tester.PortaFIBEventStore"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%!

public String getEstatPeticio(int state) {
  switch (state) {
  
  case Constants.TIPUSESTATPETICIODEFIRMA_NOINICIAT:
    return "NO_INICIADA";
  
  
  case (int)Constants.TIPUSESTATPETICIODEFIRMA_ENPROCES:
    return "EN_PROCES";
   
  
  case (int)Constants.TIPUSESTATPETICIODEFIRMA_FIRMAT:
    return "FIRMAT";
   
  
  case (int)Constants.TIPUSESTATPETICIODEFIRMA_REBUTJAT:
    return "REBUTJAT";
    
    
  case (int)Constants.TIPUSESTATPETICIODEFIRMA_PAUSAT:
    return "PAUSAT";
    
  
  
  default:
    return "Estat de petició desconegut: " + state;

  
  }
}




/*
public static final long NOTIFICACIOAVIS_REQUERIT_PER_VALIDAR = 10;
public static final long NOTIFICACIOAVIS_DESCARTAT_PER_VALIDAR = 15;
public static final long NOTIFICACIOAVIS_REQUERIT_PER_FIRMAR = 20;
public static final long NOTIFICACIOAVIS_DESCARTAT_PER_FIRMAR = 25;
public static final long NOTIFICACIOAVIS_VALIDAT = 30;
public static final long NOTIFICACIOAVIS_INVALIDAT = 40;
*/
public String getTipusEvent(int state) {
  switch (state) {
    
    case (int)Constants.NOTIFICACIOAVIS_PETICIO_EN_PROCES:
      return "ENPROCES";
     
    
    case (int)Constants.NOTIFICACIOAVIS_PETICIO_FIRMADA:
      return "FIRMADA";
     
    
    case (int)Constants.NOTIFICACIOAVIS_PETICIO_REBUTJADA:
      return "REBUTJAT";
      
      
    case (int)Constants.NOTIFICACIOAVIS_PETICIO_PAUSADA:
      return "PAUSAT";
      
    
    case (int)Constants.NOTIFICACIOAVIS_FIRMA_PARCIAL:
      return "FIRMA PARCIAL";
    
    default:
      return "Event desconegut: " + state;
      
    }

}

%><%

String action = request.getParameter("action");
if (action != null && action.equals("clean")) {
  PortaFIBEventStore.llistat.clear();
  session.setAttribute("callbackmsg", "Llistat Borrat Correctament");
  response.sendRedirect("index.jsp");
}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Llistat de Callbacks PortaFIB v1</title>
<style>
body {font-family: Arial; font-size: 8;}
</style>
</head>
<body>
<table border="0" cellspacing="10">
<tr>
  <td><h2>Llistat de Callbacks Rebuts</h2></td>
  <td>&nbsp;</td>
  <td><h3>(<a target="_blank" href="../cb/v1/PortaFIBCallBack?wsdl">WSDL</a>) </h3></td>
  <td>&nbsp;</td>
  <td><h3>(<a target="_blank" href="../cbrest/v1/versio">Versi&oacute; REST</a>) </h3></td>
  <td>&nbsp;</td>
  <td><h3>(<a target="_blank" href="../cbrest/v1/event">CallBack REST</a>) </h3></td>
  <td>&nbsp;</td>
  <td><a href="index.jsp?action=clean">Fer Net</a></td>
</tr>
</table>
<%
  String msg = (String)session.getAttribute("callbackmsg");
  if (msg != null) {
    %>
    <%=msg%>
    <%
    session.removeAttribute("callbackmsg");
  }
%>
<table border="1" cellpadding="2" cellspacing="2">
<thead>
   <tr style="background-color: gray; font-weight:bold; color:white; ">
     <td>Data</td>
     <td>Ver.</td>
     <td>Entitat</td>
     <td>Applicaci&oacute;</td>
     <td>Tipus</td>
     <td>Petici&oacute;</td>
     <td>Firma</td>
     <td>Actor</td>
     <td>Nombre</td>
   </tr>
</thead>
<%
SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

Set<PortaFIBEvent> llistat = PortaFIBEventStore.llistat;
int count = 0;
for(PortaFIBEvent info: llistat) {
%>
<tr>
  <td><%=sdf.format(info.getEventDate())%></td>
  <td><%=info.getVersion()%></td>
  <td><%=info.getEntityID()%></td>
  <td><%=info.getApplicationID()%></td>
  <td><%=getTipusEvent(info.getEventTypeID()) %></td>
  <td>
  <%
    SigningRequest peticio = info.getSigningRequest();
  
    if (peticio == null) {
  %>
  -
  <% } else { %>

  <b>ID:</b><%=peticio.getID()%><br/>
  <b>Titol:</b> <%=peticio.getTitle()%><br/>
  <b>Estat:</b> <%=getEstatPeticio(peticio.getState())%><br/>
  <b>Info:</b> <%=peticio.getAdditionalInformation()%>
  <%   if (peticio.getRejectionReason() != null) { %>
  <br/><b>Rebuig:</b> <%= peticio.getRejectionReason()%>
  <%   } %>
  <%   if (peticio.getCustodyURL() != null) { %>
  <br/><b>Custodia:</b>  <a href="<%= peticio.getCustodyURL()%>" target="_blank"><%= peticio.getCustodyURL()%></a> 
  <%   } %>
  <% } %>
  </td>
 
 
 <td>
   <%
     Sign firma = info.getSign();
   %>
   <% if (firma == null) { %>
   -
   <% } else { %>
    <b>ID:</b> <%=firma.getID() %><br/>
    <b>S/N:</b> <%=firma.getSerialNumber() %><br/>
    <b>Issuer:</b> <small><%=firma.getIssuer() %></small><br/>
    <b>Subject:</b> <small><%=firma.getSubject() %></small><br/>
   <% }%>
   </td>
   
   <td>
  <% Actor actor = info.getActor();  %>     
  <% if (actor == null) { %>
     -
     
  <% } else { %>
     <b>ID:</b>  <%=actor.getID()%><br/>
     <b>NIF:</b> <%=actor.getAdministrationID()%><br/>
     <b>Name:</b> <%=actor.getName()%>
  <% } %>
  </td>
  <td><%= ++count %></td>
 </tr>

<% } %>

</table>
</body>
</html>