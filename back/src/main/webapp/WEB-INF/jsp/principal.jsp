<%@page import="org.springframework.security.core.Authentication"
%><%@page import="org.springframework.security.core.context.SecurityContext"
%><%@page import="org.springframework.security.core.context.SecurityContextHolder"
%><%@ page language="java" 
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp" 
%>
<div class="clear"></div>
<div class="spacer"></div>

<div>
<br/>
<center>
<img src="<c:url value="/img/app-logo.png"/>"  alt="PortaFIB" title="PortaFIB"/>

<br/>
<br/>
<fmt:message key="portada" />

<br/>
<br/>
<table border="0" >
<tr>
<td valign="top">
<a href="http://dgtic.caib.es/" target="_blank">
<img src="<c:url value="/img/dgidt.jpg"/>"  alt="DGIDT" title="DGIDT"/>
</a>
</td>
<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td valign="top">
<a href="http://blog.fundaciobit.org/category/admindigital/" target="_blank">
<img src="<c:url value="/img/fundaciobit.jpg"/>"  alt="Fundaci&oacute; Bit" title="Fundaci&oacute; Bit"/>
</a>
</td>
</tr>
</table>
<br/>
</center>

</div>


<c:if test="${pfi:isDesenvolupament()}">

    
   Usuari Login: <b>${pageContext.request.remoteUser}</b><br>
   Usuari Persona ID: <b>${loginInfo.usuariPersona.usuariPersonaID}</b><br>
   Usuari Persona: <b>${loginInfo.usuariPersona.nom} ${loginInfo.usuariPersona.llinatges}</b><br>
   Usuari-Entitat ID: <b>${loginInfo.usuariEntitatID}</b><br>  
   Entitat ID: <b>${loginInfo.entitatID}</b><br>
   Nom Entitat: <b>${loginInfo.entitat.nom}</b><br>
   Email Usuari Persona : <b>${loginInfo.usuariPersona.email}</b><br>
   
   Email Usuari Entitat : <b>
   <c:if test="${not empty loginInfo.usuariEntitat}">
      ${loginInfo.usuariEntitat.email}</b><br>
   </c:if>
   <c:if test="${empty loginInfo.usuariEntitat}">
      -
   </c:if>
<hr>
   
   Roles: <b>
   <c:forEach var="rol" items="${loginInfo.roles}">
      "${rol.authority}"  
   </c:forEach>
   </b><br>
   
   <hr>
   Role PFI_AUTOFIRMA: <%=request.isUserInRole("PFI_AUTOFIRMA") %><br/>
   Role ROLE_AUTOFIRMA: <%=request.isUserInRole("ROLE_AUTOFIRMA") %><br/>
   Role PFI_USER: <%=request.isUserInRole("PFI_USER") %><br/>
   Role ROLE_USER: <%=request.isUserInRole("ROLE_USER") %><br/>
   <hr>
   
  <%  
  SecurityContext sc = SecurityContextHolder.getContext(); 
  Authentication au = sc.getAuthentication();  
  out.print("DETAILS CLASS: " + au.getDetails());
  %> 
  <hr>  
    <sec:authorize access="hasRole('ROLE_DEST')">
         ++++++++   hasRole('ROLE_DEST') <br>
    </sec:authorize>
    <sec:authorize access="hasRole('ROLE_USER')">
         ++++++++   hasRole('ROLE_USER') <br>
    </sec:authorize>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
         ++++++++   hasRole('ROLE_ADMIN') <br>
    </sec:authorize>
 
</c:if>
  