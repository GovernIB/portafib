
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="algorismeDeFirmaFormTitle.jsp" %>


<form:form modelAttribute="algorismeDeFirmaForm" method="${method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${algorismeDeFirmaForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="algorismeDeFirmaFormCorePre.jsp" %>
  <%@include file="algorismeDeFirmaFormCore.jsp" %>

  <%@include file="algorismeDeFirmaFormCorePost.jsp" %>

  <%@include file="algorismeDeFirmaFormButtons.jsp" %>

  <c:if test="${algorismeDeFirmaForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/algorismeDeFirmaFormModificable.jsp" %>
  </c:if>

</form:form>


