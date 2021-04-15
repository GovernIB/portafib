<%@page import="es.caib.portafib.back.security.LoginInfo"
%><%@ page contentType="text/html;charset=UTF-8" language="java"
%><%@include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="LoginInfo" className="es.caib.portafib.back.security.LoginInfo" />
<un:useConstants var="Constants" className="es.caib.portafib.utils.Constants" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
  xml:lang="<c:out value="${pageContext.response.locale.language}"/>"
  lang="<c:out value="${pageContext.response.locale.language}"/>">
<head>
<tiles:importAttribute name="contingut" />
<c:if test="${loginInfo.needConfigUser}">
  <%
    LoginInfo.getInstance().setNeedConfigUser(false);
  %>
  <c:redirect
    url="/common/configuracio/usuaripersona/${loginInfo.usuariPersona.usuariPersonaID}/edit" />
</c:if>

<c:if test="${not empty loginInfo.entitatID}">
  <link href="<c:url value="${pfi:fileUrl(loginInfo.entitat.favicon)}"/>"
    rel="shortcut icon" type="image/x-icon" />
</c:if>

<%@ include file="/WEB-INF/jsp/moduls/imports.jsp"%>
</head>
<body>

  <!--  INICI CAPÇALERA -->
  <tiles:insertAttribute name="cap">
    <tiles:putAttribute name="data" value="${data}" />
  </tiles:insertAttribute>

  <div class="row-fluid container main">

    <!-- INICI MENU + CONTINGUT style="padding:5px"-->
    <div class="well well-white">

      <!--  CONTINGUT  -->
      <div class="span12">

        <!--  Missatges  -->
        <jsp:include page="/WEB-INF/jsp/moduls/missatges.jsp" />

        <!-- Contingut de la pagina -->
        <tiles:insertAttribute name="contingut">
        </tiles:insertAttribute>

        <!-- FINAL DIV CONTINGUT -->
      </div>

      <div class="clearfix"></div>

      <!-- FINAL MENU + CONTINGUT -->
    </div>

  </div>

  <div class="container row-fluid">
    <tiles:insertAttribute name="peu">
    </tiles:insertAttribute>
  </div>

</body>
</html>