<%@page import="org.springframework.context.i18n.LocaleContextHolder"%>
<%@page import="java.util.Locale"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp"
%><%@ taglib prefix="tiles"  uri="http://tiles.apache.org/tags-tiles" 
%>

<div class="row-fluid container nav-container">
  <c:if test="${not empty loginInfo.entitatID}" >
  <div class="logo pull-left" >
    <a href="<c:out value="${loginInfo.entitat.web}" />" target="_blank">
    <img src="<c:url value="${pfi:fileUrl(loginInfo.entitat.logoWeb)}"/>"  title="${loginInfo.entitat.nom}" alt="${loginInfo.entitat.nom}" />
    </a>
  </div>
  </c:if>
  
  <div class="aplication-logo pull-left">
  <%-- 
    <c:set var="browser" value="${header['User-Agent']}" scope="session"/>
    <c:set var="isIE" value="${fn:contains(header['User-Agent'], 'MSIE')}"/>
    <c:if test="${isIE }" >
    <img src="<c:url value="/img/portafib_ie.png"/>"  alt="PortaFIB" title="PortaFIB"/>
    </c:if>
    <c:if test="${not isIE }" >
     
     width="50%"  height="50%"
     --%>
     <%--
    </c:if>
    width="116"  height="61"  style="width:116px; height:61px;"
     --%>
    <img src="<c:url value="/img/app-logo-header.png"/>"   alt="PortaFIB" title="PortaFIB"/>

  </div>
  
  <div class="pull-right main-menu">
    <ul class="user-nav pull-right dropdown">

      <c:if test="${not empty loginInfo.entitatID}" > 
       
      <li>
         <a class="dropdown-toggle" data-toggle="dropdown" href="#">
           <i class="icon-home icon-white"></i><fmt:message key="estitat_entitats" /> <span class="caret"></span>
         </a>
  
        <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
        <li><a class="" href="#"><i> ${loginInfo.entitat.nom} </i> </a></li>
        
        <c:forEach var="entry" items="${loginInfo.entitats}">
           <c:if test="${not(entry.key eq loginInfo.entitatID)}">
           <li><a tabindex="-1" href="<c:url value="/canviarEntitat/${entry.key}"/>"> ${entry.value.nom}</a></li>
           </c:if>
        </c:forEach>
        </ul>
      </li>

      <li>
         <a href="<c:out value="${loginInfo.entitat.web}" />" target="_blank">
         ${loginInfo.entitat.nom}
         </a>
      </li>

      </c:if>
      
      <li>
        <a class="dropdown-toggle" data-toggle="dropdown" href="#"> 
          <i class="icon-user icon-white"></i>
          ${loginInfo.usuariPersona.nom}&nbsp;${loginInfo.usuariPersona.llinatges}
          <span class="caret"></span>
        </a>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
          <li><a tabindex="-1" href="<c:url value="/common/configuracio/usuaripersona/${pageContext.request.userPrincipal.name}/edit"/>"><fmt:message key="configuracio" /></a></li>
          <%--
          <li><a tabindex="-1" href="<c:url value="/j_spring_security_logout" />">Sortir</a></li>
           --%>
        </ul>
      </li>

    </ul>
    <div class="clearfix"></div>

    <div style="text-align: left; width: ${(17 + 5)* fn:length(idiomes) }px;" class="pull-right">
    <c:forEach  var="idioma"  items="${idiomes}" varStatus="status">
       <%--i:${status.index}  | c:${status.count} | l:[{$idioma}]  --%>
       <a href="<c:url value="${urlActual}"><c:param name="lang" value="${idioma.idiomaID}"/></c:url>">
          <img src="<c:url value="/img/${idioma.idiomaID}_petit_${lang eq idioma.idiomaID? 'on' : 'off'}.gif"/>" alt="${idioma.nom}" width="17"
            height="14" border="0" />
       </a>
    </c:forEach>

    </div>
  </div>

</div>
<%--
</header> <!-- end of header bar -->

<section id="secondary_bar">
    <div class="user">
        <p>${pageContext.request.remoteUser}
        <%--${pageContext.request.isUserInRole['PFI_USER']} --%>
<%--<sec:authorize access="hasRole('ROLE_USER')">
            This content will only be visible to users who have
            the "supervisor" authority in their list of
        </sec:authorize>--%>
<%-- <br/></p> --%>
<%--<a class="logout_user" href="<c:url value="/logout.htm"/>" title="Logout">Sortir</a>--%>
<%-- </div>
    <div class="breadcrumbs_container">
        <article class="breadcrumbs"><a href="<c:url value="/principal.html"/>">Inici</a> </article><br>
        Data: <fmt:formatDate dateStyle="short" value="${data}"/>        
    </div>
    
</section><!-- end of secondary bar -->

--%>