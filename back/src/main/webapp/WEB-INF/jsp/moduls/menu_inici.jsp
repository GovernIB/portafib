<%@page import="es.caib.portafib.utils.ConstantsV2"%>
confi<%@page import="es.caib.portafib.back.security.LoginInfo"%>
<%@page import="es.caib.portafib.logic.utils.PropietatGlobalUtil"%>
<%@page import="es.caib.portafib.utils.Configuracio"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<%

Boolean autofirma = PropietatGlobalUtil.getAutofirmaAllowed(LoginInfo.getInstance().getEntitatID());
if (autofirma == null) {
  autofirma = request.isUserInRole("ROLE_AUTOFIRMA");
}
pageContext.setAttribute("autofirma", autofirma);

String androidApk = Configuracio.getAndroidApk();
pageContext.setAttribute("androidApk", androidApk);
%>
<c:set var="url" value="${urlActual}" />
<div>
  <h5><fmt:message key="menuinici" /></h5>
  <ul class="tree" style="margin: 3px; padding: 0px;">

    <li style="list-style-type: disc; list-style-position: inside;">
      <a href="<c:url value="/common/principal.html"/>">
        <span style="${(fn:contains(url, 'principal'))? "font-weight: bold;" : ""}"><fmt:message key="pagina.principal" /></span>
      </a>
    </li>
    
    <c:if test="${not empty loginInfo.entitatID && autofirma}" >
    <hr  style="margin-top: 6px;  margin-bottom: 6px;" />
    <li style="list-style-type: disc; list-style-position: inside;">
      <a href="<c:url value="/common/autofirma/list"/>">
        <span style="${(fn:contains(url, 'autofirma'))? "font-weight: bold;" : ""} }"><fmt:message key="autofirma.gestio" /></span>
      </a>
    </li>
    </c:if>

   <%-- RebreAvis --%>
   <sec:authorize access="hasAnyRole('ROLE_SOLI', 'ROLE_DEST', 'ROLE_COLA', 'ROLE_DELE', 'ROLE_REVI')">
      <hr  style="margin-top: 6px;  margin-bottom: 6px;" />
      <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/common/rebreAvis/list/1"/>" ><span style="${(fn:contains(url, 'rebreAvis/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" ><fmt:message key="notificaciocorreu.plural" /></span></a></li>
   </sec:authorize>

   <c:if test="${not empty loginInfo.usuariPersona}" >
       <hr  style="margin-top: 6px;  margin-bottom: 6px;" />
       <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/common/configuracio/usuaripersona/${loginInfo.usuariPersona.usuariPersonaID}/edit"/>" ><span style="${(fn:contains(url, 'configuracio/usuaripersona') && fn:contains(url, '/edit'))? "font-weight: bold;" : ""}" ><fmt:message key="inici.configuracio.usuaripersona" /></span></a></li>

       <c:if test="${not empty loginInfo.entitatID}" >
            <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/common/configuracio/usuarientitat/${loginInfo.usuariEntitatID}/edit"/>" ><span style="${(fn:contains(url, 'configuracio/usuarientitat') && fn:contains(url, '/edit'))? "font-weight: bold;" : ""}" ><fmt:message key="inici.configuracio.usuarientitat" /></span></a></li>
       </c:if>
   </c:if>

   <sec:authorize access="hasRole('ROLE_USER')">

       <sec:authorize access="hasAnyRole('ROLE_SOLI', 'ROLE_DEST', 'ROLE_ADEN')">
          <hr  style="margin-top: 6px;  margin-bottom: 6px;" />
          <li style="list-style-type: disc; list-style-position: inside;" ><a href="<c:url value="/common/usuariEntitatFavorit/list/1"/>" ><span style="${(fn:contains(url, 'usuariEntitatFavorit/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" ><fmt:message key="favorit.gestio" /></span></a></li>
       </sec:authorize>

        <%-- Carrecs --%>
        <sec:authorize access="hasAnyRole('ROLE_SOLI', 'ROLE_DEST', 'ROLE_COLA', 'ROLE_DELE', 'ROLE_ADEN')">
          <hr  style="margin-top: 6px;  margin-bottom: 6px;" />
          <li style="list-style-type: disc; list-style-position: inside;"><a href="<c:url value="/common/carrecs/list/1"/>" ><span style="${(fn:contains(url, 'carrecs/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" ><fmt:message key="carrec.llistat" /></span></a></li>
        </sec:authorize>
    </sec:authorize>

    <hr  style="margin-top: 6px;  margin-bottom: 6px;" />
    <li style="list-style-type: disc; list-style-position: inside;"><a target="_blank" href="<c:url value="/doc/Manual_de_Usuari_de_PortaFIB.pdf"/>" ><fmt:message key="manualusuari" /></a></li>

    <c:if test="${not empty androidApk}" >
        <hr  style="margin-top: 6px;  margin-bottom: 6px;" />
        <li style="list-style-type: disc; list-style-position: inside;">
            <a target="_blank" href="<c:url value="/common/app.html"/>" ><fmt:message key="appmobil" /></a>
        </li>
    </c:if>
   
  </ul>
</div>
