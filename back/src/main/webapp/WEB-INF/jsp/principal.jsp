<%@page import="org.fundaciobit.plugins.webutils.AbstractWebPlugin"%>
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
   Role ROLE_AUTOFIRMA: <%=request.isUserInRole("ROLE_AUTOFIRMA") %><br/>
   Role ROLE_USER: <%=request.isUserInRole("ROLE_USER") %><br/>
   Role ROLE_ADMIN: <%=request.isUserInRole("ROLE_ADMIN") %><br/>
   Role ROLE_DEST: <%=request.isUserInRole("ROLE_DEST") %><br/>
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
    
    <hr>

    Authentication mechanism:<%=request.getAuthType()%> <br/>

    <hr>
    
    <%= AbstractWebPlugin.servletRequestInfoToStr(request).replace("\n", "<br/>\n") %>
 
</c:if>
  