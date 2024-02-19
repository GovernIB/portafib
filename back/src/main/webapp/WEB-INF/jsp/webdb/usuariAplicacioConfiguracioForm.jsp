
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="usuariAplicacioConfiguracioForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="usuariAplicacioConfiguracioFormTitle.jsp" %>
 
  <c:set var="contexte" value="${usuariAplicacioConfiguracioForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="usuariAplicacioConfiguracioFormCorePre.jsp" %>

  <%@include file="usuariAplicacioConfiguracioFormCore.jsp" %>

  <%@include file="usuariAplicacioConfiguracioFormCorePost.jsp" %>

  <%@include file="usuariAplicacioConfiguracioFormButtons.jsp" %>

  <c:if test="${not empty usuariAplicacioConfiguracioForm.sections}">
     <c:set var="__basename" value="usuariAplicacioConfiguracio" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${usuariAplicacioConfiguracioForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/usuariAplicacioConfiguracioFormModificable.jsp" %>
  </c:if>

</form:form>


