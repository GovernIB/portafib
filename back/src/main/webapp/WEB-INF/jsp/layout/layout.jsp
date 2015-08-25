<%@page import="es.caib.portafib.back.security.LoginInfo"
%><%@ page contentType="text/html;charset=UTF-8" language="java"
%><%@include file="/WEB-INF/jsp/moduls/includes.jsp"
%>
<un:useConstants var="LoginInfo" className="es.caib.portafib.back.security.LoginInfo" />
<un:useConstants var="Constants" className="es.caib.portafib.utils.Constants" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="<c:out value="${pageContext.response.locale.language}"/>"  lang="<c:out value="${pageContext.response.locale.language}"/>">
<head>
<c:if test="${loginInfo.needConfigUser}" >
  <%
   LoginInfo.getInstance().setNeedConfigUser(false);
  %>
  <c:redirect url="/common/configuracio/usuaripersona/${loginInfo.usuariPersona.usuariPersonaID }/edit"/>
</c:if>

<c:if test="${not empty loginInfo.entitatID}" >
  <link href="<c:url value="${pfi:fileUrl(loginInfo.entitat.favicon)}"/>" rel="shortcut icon" type="image/x-icon" />
</c:if>

<%@ include file="/WEB-INF/jsp/moduls/imports.jsp"%>
</head>
<body>

  <!--  INICI CAPÇALERA -->
  
  <tiles:insertAttribute name="cap">
    <tiles:putAttribute name="data" value="${data}" />
  </tiles:insertAttribute>


  <!--  PIPELLES -->
  <div class="row-fluid container main">
    
    <ul class="nav nav-tabs custom-submenu">
    
    <li ${(empty pipella)?'class="active"' : '' } >
        <a href="<c:url value="/canviarPipella/"/>"><fmt:message key="inici" /></a>
    </li> 


    <c:forEach var="rolG" items="${loginInfo.roles}">
    <c:set var="rol" value="${rolG.authority}"/>
    <c:if test="${not(rol eq 'ROLE_USER')}">
    <li class="${(pipella eq rol)?'active' : '' }${(rol eq 'ROLE_COLA')?' dropdown' : '' }">
    
    <c:url var="linktab" value="/canviarPipella/${rol}"/>
    <c:set var="href" value="href=\"${linktab}\"" />
    
        <%-- MMMENU  
       <c:if test="${rol eq 'ROLE_COLA' }">
            <c:set var="href" value="href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\""/>
       </c:if>
       --%>
       
       <a ${href}><fmt:message key="${rol}" />
       <c:if test="${not(empty avisos[rol])}">
         &nbsp; <span class="badge badge-warning">${avisos[rol]}</span>
       </c:if>
        <%-- MMMENU
       <c:if test="${rol eq 'ROLE_COLA' }">
         &nbsp;<b class="caret"></b>
        </c:if>
         --%>
       </a>
        
       <%-- MMMENU  
       <c:if test="${rol eq 'ROLE_COLA' }">
         <ul class="dropdown-menu">
         
            <li><a href="#" onclick="javascript:goTo('<c:url value="${Constants.CONTEXT_COLA_ESTATFIRMA_PENDENT}/list"/>');" data-toggle="tab"><fmt:message key="colaboracio.pendent.plural" /></a></li>
            
            <li><a href="#" onclick="javascript:goTo('<c:url value="${Constants.CONTEXT_COLA_ESTATFIRMA_VALIDAT}/list"/>');" data-toggle="tab"><fmt:message key="colaboracio.acceptada.plural" /></a></li>
            <li><a href="#" onclick="javascript:goTo('<c:url value="${Constants.CONTEXT_COLA_ESTATFIRMA_INVALIDAT}/list"/>');" data-toggle="tab"><fmt:message key="colaboracio.noacceptada.plural" /></a></li>
            <li><a href="#" onclick="javascript:goTo('<c:url value="${Constants.CONTEXT_COLA_ESTATFIRMA_DESCARTAT}/list"/>');" data-toggle="tab"><fmt:message key="colaboracio.ignorada.plural" /></a></li>
         </ul>
       </c:if>
       --%>
       
    </li>
    </c:if>  
    </c:forEach>

    <sec:authorize access="hasRole('ROLE_ADMIN')">
    <li ${(pipella eq 'webdb')?'class="active"' : '' }>
       <a href="<c:url value="/canviarPipella/webdb"/>"> WebDatabase</a>
    </li>
    </sec:authorize>

    <c:if test="${pfi:isDesenvolupament()}">
    <li ${(pipella eq 'desenvolupament')?'class="active"' : '' }>
       <a href="<c:url value="/canviarPipella/desenvolupament"/>"> Desenvolupament</a>
    </li>
    </c:if>
     
    </ul>


    <!-- INICI MENU + CONTINGUT -->
    <div class="well well-white" style="padding:10px">
    <tiles:insertAttribute name="menu_i_contingut" >
       <%--  <tiles:insertTemplate template="/WEB-INF/jsp/moduls/menu_i_contingut.jsp"/>  --%>
       <tiles:putAttribute name="menu" value="${menu_tile}" />
       <tiles:putAttribute name="contingut" value="${contingut_tile}"  />
    </tiles:insertAttribute>
    <!-- FINAL MENU + CONTINGUT -->
    </div>

  <!-- FINAL DIV PIPELLES -->
  </div>

  <div class="container row-fluid">
    <tiles:insertAttribute name="peu">     
    </tiles:insertAttribute>
  </div>

</body>
</html>
