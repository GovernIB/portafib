
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="firmaFormTitle.jsp" %>


<form:form modelAttribute="firmaForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${firmaForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="firmaFormCorePre.jsp" %>
  <%@include file="firmaFormCore.jsp" %>

  <%@include file="firmaFormCorePost.jsp" %>

  <%@include file="firmaFormButtons.jsp" %>

  <c:if test="${firmaForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/firmaFormModificable.jsp" %>
  </c:if>

</form:form>


