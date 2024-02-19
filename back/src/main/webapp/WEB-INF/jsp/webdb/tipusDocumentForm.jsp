
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="tipusDocumentForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="tipusDocumentFormTitle.jsp" %>
 
  <c:set var="contexte" value="${tipusDocumentForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="tipusDocumentFormCorePre.jsp" %>

  <%@include file="tipusDocumentFormCore.jsp" %>

  <%@include file="tipusDocumentFormCorePost.jsp" %>

  <%@include file="tipusDocumentFormButtons.jsp" %>

  <c:if test="${not empty tipusDocumentForm.sections}">
     <c:set var="__basename" value="tipusDocument" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${tipusDocumentForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/tipusDocumentFormModificable.jsp" %>
  </c:if>

</form:form>


