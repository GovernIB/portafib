
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="pluginCridadaForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="pluginCridadaFormTitle.jsp" %>
 
  <c:set var="contexte" value="${pluginCridadaForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="pluginCridadaFormCorePre.jsp" %>

  <%@include file="pluginCridadaFormCore.jsp" %>

  <%@include file="pluginCridadaFormCorePost.jsp" %>

  <%@include file="pluginCridadaFormButtons.jsp" %>

  <c:if test="${not empty pluginCridadaForm.sections}">
     <c:set var="__basename" value="pluginCridada" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${pluginCridadaForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/pluginCridadaFormModificable.jsp" %>
  </c:if>

</form:form>


