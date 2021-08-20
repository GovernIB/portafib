
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="tipusDocumentFormTitle.jsp" %>


<form:form modelAttribute="tipusDocumentForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${tipusDocumentForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="tipusDocumentFormCorePre.jsp" %>
  <%@include file="tipusDocumentFormCore.jsp" %>

  <%@include file="tipusDocumentFormCorePost.jsp" %>

  <%@include file="tipusDocumentFormButtons.jsp" %>

  <c:if test="${tipusDocumentForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/tipusDocumentFormModificable.jsp" %>
  </c:if>

</form:form>


