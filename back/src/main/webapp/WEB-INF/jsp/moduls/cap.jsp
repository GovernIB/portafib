<%@ page contentType="text/html;charset=UTF-8" language="java"
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp"
%><%@ taglib prefix="tiles"  uri="http://tiles.apache.org/tags-tiles" 
%>
<div class="row-fluid ${isMobile?'container-mobile nav-container-mobile':'container nav-container'}" >
  <c:if test="${not empty loginInfo.entitatID}" >
  <div class="logo pull-left" >
    <a href="<c:out value="${loginInfo.entitat.web}" />" target="_blank">
    <img src="<c:url value="${pfi:fileUrl(loginInfo.entitat.logoWeb)}"/>"  title="${loginInfo.entitat.nom}" alt="${loginInfo.entitat.nom}" />
    </a>
  </div>
  </c:if>
  <div class="aplication-logo pull-left">
    <img src="<c:url value="/img/app-logo-header.png"/>"   alt="PortaFIB" title="PortaFIB"/>
  </div>
  <c:if test="${isMobile}">
   <div class="clearfix"></div>
 </c:if>
  
  <div class="${isMobile?'pull-left':'pull-right'} main-menu">
    <ul class="user-nav pull-right dropdown" style="${isMobile?'margin-left:10px;':''}">

      <c:if test="${not empty loginInfo.entitatID}" > 
      <c:if test="${loginInfo.usuariPersona.usuariIntern}">
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
      </c:if>

      <li>
         <a href="<c:out value="${loginInfo.entitat.web}" />" target="_blank">
         ${loginInfo.entitat.nom}
         </a>
      </li>

      </c:if>

   <c:if test="${not empty loginInfo.usuariPersona}" >
      <li>
        <a class="dropdown-toggle" data-toggle="dropdown" href="#"> 
          <i class="icon-user icon-white"></i>
          ${loginInfo.usuariPersona.nom}&nbsp;${loginInfo.usuariPersona.llinatges}
          <span class="caret"></span>
        </a>
        <c:if test="${loginInfo.usuariPersona.usuariIntern}">
        <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
          <li><a tabindex="-1" href="<c:url value="/common/configuracio/usuaripersona/${pageContext.request.userPrincipal.name}/edit"/>"><fmt:message key="configuracio" /></a></li>
          
          <c:if test="${not empty menuLogOutUrl}">
          <li><a tabindex="-1" href="<c:url value="${menuLogOutUrl}" />"><fmt:message key="sortir" /></a></li>
          </c:if>
        </ul>
        </c:if>
      </li>
   </c:if> 

    </ul>
    <div class="clearfix"></div>
    <c:if test="${not empty loginInfo}">
    <div style="text-align: left; width: ${(17 + 5)* fn:length(idiomes) }px;" class="pull-right">
    <c:forEach  var="idioma"  items="${idiomes}" varStatus="status">
       <%--i:${status.index}  | c:${status.count} | l:[{$idioma}]  --%>
       <a href="<c:url value="${urlActual}"><c:param name="lang" value="${idioma.idiomaID}"/></c:url>">
          <img src="<c:url value="/img/${idioma.idiomaID}_petit_${lang eq idioma.idiomaID? 'on' : 'off'}.gif"/>" alt="${idioma.nom}" width="17"
            height="14" border="0" />
       </a>
    </c:forEach>
  </c:if>
    </div>
  </div>
</div>