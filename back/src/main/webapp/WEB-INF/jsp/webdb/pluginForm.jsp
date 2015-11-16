
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="pluginFormTitle.jsp" %>


<form:form modelAttribute="pluginForm" method="${method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${pluginForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="pluginFormCorePre.jsp" %>
  <%@include file="pluginFormCore.jsp" %>

  <%@include file="pluginFormCorePost.jsp" %>

  <%@include file="pluginFormButtons.jsp" %>

  <c:if test="${pluginForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/pluginFormModificable.jsp" %>
  </c:if>

</form:form>


