
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="prioritatFormTitle.jsp" %>


<form:form modelAttribute="prioritatForm" method="${method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${prioritatForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="prioritatFormCorePre.jsp" %>
  <%@include file="prioritatFormCore.jsp" %>

  <%@include file="prioritatFormCorePost.jsp" %>

  <%@include file="prioritatFormButtons.jsp" %>

  <c:if test="${prioritatForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/prioritatFormModificable.jsp" %>
  </c:if>

</form:form>


