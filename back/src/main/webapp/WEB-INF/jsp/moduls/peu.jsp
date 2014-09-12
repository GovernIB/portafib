<%@page import="es.caib.portafib.logic.utils.LogicUtils"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
 %><%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
<table border=0 cellpadding="0" cellspacing="0" width="100%">

 <tr>
 <td width="40%" valign="top">
   <div class="pull-left colophon">
     <b><fmt:message key="app.nom" /> v<%=LogicUtils.getVersio()%></b><br/>
     <i><a href="http://otaeweb.ibit.org/" target="_blank"><fmt:message key="desenvolupatper" /></a></i><br/>
     <!-- Button to trigger modal -->
     <c:if test="${not empty loginInfo.entitatID}" > 
     <small><a href="#modalAjuda" role="button" data-toggle="modal"><fmt:message key="ajuda.necessitau" /></a></small>
     </c:if>
   </div>
 </td>

 <td width="20%" valign="top">
   <div class="center" style=" margin-top: 20px;">
     <c:if test="${not empty loginInfo.entitatID}" > 
     <c:out value="${loginInfo.entitat.adrezaHtml}" escapeXml="false" />
     </c:if>
   </div>
 </td>

 <td width="40%" valign="top">
  <div class="pull-right govern-footer">
    <c:if test="${not empty loginInfo.entitatID}" >
    <a href="<c:out value="${loginInfo.entitat.web}" />" target="_blank">
    <img src="<c:url value="${pfi:fileUrl(loginInfo.entitat.logoWebPeu)}"/>"  alt="${loginInfo.entitat.nom }" />
    </a>
    </c:if>
  </div>
 </td>
 </tr> 
</table> 
  
  <c:if test="${not empty loginInfo.entitatID}" > 
     
    <!-- Modal -->
    <div id="modalAjuda" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="<fmt:message key="ajuda.titol" />" aria-hidden="true">
    <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="myModalLabel"><fmt:message key="ajuda.titol" /></h3>
    </div>
    <div class="modal-body">
    <p><fmt:message key="ajuda.missatge" /></p>
     <ul>
     <c:if test="${not empty loginInfo.entitat.suportTelefon }"> 
        <li><fmt:message key="ajuda.viatelefon"/> ${loginInfo.entitat.suportTelefon} </li>
     </c:if>
     
     <c:if test="${not empty loginInfo.entitat.suportWeb }"> 
        <li><fmt:message key="ajuda.viaweb"/> <a target="_blank" href="${loginInfo.entitat.suportWeb}">${loginInfo.entitat.suportWeb} </a> </li>
     </c:if>
     
     <c:if test="${not empty loginInfo.entitat.suportEmail }"> 
        <li><fmt:message key="ajuda.viaemail"/><a href="mailto: ${loginInfo.entitat.suportEmail}"> ${loginInfo.entitat.suportEmail}</a></li>
     </c:if>
     </ul>
    
    </div>    
    </div>
    
  </c:if>