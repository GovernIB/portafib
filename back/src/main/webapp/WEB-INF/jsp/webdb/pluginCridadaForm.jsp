
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="pluginCridadaFormTitle.jsp" %>


<form:form modelAttribute="pluginCridadaForm" method="${method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${pluginCridadaForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="pluginCridadaFormCorePre.jsp" %>
  <%@include file="pluginCridadaFormCore.jsp" %>

  <%@include file="pluginCridadaFormCorePost.jsp" %>

  <%@include file="pluginCridadaFormButtons.jsp" %>

  <c:if test="${pluginCridadaForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/pluginCridadaFormModificable.jsp" %>
  </c:if>

</form:form>


