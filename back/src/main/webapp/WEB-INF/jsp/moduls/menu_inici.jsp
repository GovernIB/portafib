<%@ page contentType="text/html;charset=UTF-8" language="java"
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<c:set var="url" value="${urlActual}" />
<div>
  <h5><fmt:message key="menuinici" /></h5>
  <ul class="tree" style="margin: 3px; padding: 0px;">

    <li style="list-style-type: disc; list-style-position: inside;">
      <a href="<c:url value="/common/principal.html"/>">
        <span style="${(fn:contains(url, 'principal'))? "font-weight: bold;" : ""}"><fmt:message key="pagina.principal" /></span>
      </a>
    </li>

    <c:if test="${not empty loginInfo.entitatID}" >
    <hr  style="margin-top: 6px;  margin-bottom: 6px;" />
    <li style="list-style-type: disc; list-style-position: inside;">
      <a href="<c:url value="/common/autofirma"/>">
        <span style="${(fn:contains(url, 'autofirma'))? "font-weight: bold;" : ""} }"><fmt:message key="autofirma" /></span>
      </a>
    </li>
    </c:if>

   <%-- RebreAvis --%>
   <sec:authorize access="hasAnyRole('ROLE_SOLI', 'ROLE_DEST', 'ROLE_COLA', 'ROLE_DELE')">
      <hr  style="margin-top: 6px;  margin-bottom: 6px;" />
      <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/common/rebreAvis/list/1"/>" ><span style="${(fn:contains(url, 'rebreAvis/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" ><fmt:message key="notificaciocorreu.plural" /></span></a></li>
   </sec:authorize>

   <hr  style="margin-top: 6px;  margin-bottom: 6px;" />
   <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/common/configuracio/usuaripersona/${loginInfo.usuariPersona.usuariPersonaID}/edit"/>" ><span style="${(fn:contains(url, 'configuracio/usuaripersona') && fn:contains(url, '/edit'))? "font-weight: bold;" : ""}" ><fmt:message key="inici.configuracio.usuaripersona" /></span></a></li>

   <c:if test="${not empty loginInfo.entitatID}" >
        <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/common/configuracio/usuarientitat/${loginInfo.usuariEntitatID}/edit"/>" ><span style="${(fn:contains(url, 'configuracio/usuarientitat') && fn:contains(url, '/edit'))? "font-weight: bold;" : ""}" ><fmt:message key="inici.configuracio.usuarientitat" /></span></a></li>
   </c:if>
   <sec:authorize access="hasAnyRole('ROLE_SOLI', 'ROLE_DEST', 'ROLE_ADEN')">
      <hr  style="margin-top: 6px;  margin-bottom: 6px;" />
      <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/common/usuariEntitatFavorit/list/1"/>" ><span style="${(fn:contains(url, 'usuariEntitatFavorit/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" ><fmt:message key="favorit.gestio" /></span></a></li>
   </sec:authorize>
   
    <%-- Carrecs --%>
	<sec:authorize access="hasAnyRole('ROLE_SOLI', 'ROLE_DEST', 'ROLE_COLA', 'ROLE_DELE', 'ROLE_ADEN')">
      <hr  style="margin-top: 6px;  margin-bottom: 6px;" />
      <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/common/carrecs/list/1"/>" ><span style="${(fn:contains(url, 'carrecs/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" ><fmt:message key="carrec.llistat" /></span></a></li>
    </sec:authorize>


    <hr  style="margin-top: 6px;  margin-bottom: 6px;" />
    <li style="list-style-type: disc; list-style-position: inside;"><a target="_blank" href="<c:url value="/doc/Manual_de_Usuari_de_PortaFIB.pdf"/>" ><fmt:message key="manualusuari" /></a></li>
   
  </ul>
</div>
