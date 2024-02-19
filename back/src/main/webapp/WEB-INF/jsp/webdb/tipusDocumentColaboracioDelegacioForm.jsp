
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="tipusDocumentColaboracioDelegacioForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="tipusDocumentColaboracioDelegacioFormTitle.jsp" %>
 
  <c:set var="contexte" value="${tipusDocumentColaboracioDelegacioForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="tipusDocumentColaboracioDelegacioFormCorePre.jsp" %>

  <%@include file="tipusDocumentColaboracioDelegacioFormCore.jsp" %>

  <%@include file="tipusDocumentColaboracioDelegacioFormCorePost.jsp" %>

  <%@include file="tipusDocumentColaboracioDelegacioFormButtons.jsp" %>

  <c:if test="${not empty tipusDocumentColaboracioDelegacioForm.sections}">
     <c:set var="__basename" value="tipusDocumentColaboracioDelegacio" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${tipusDocumentColaboracioDelegacioForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/tipusDocumentColaboracioDelegacioFormModificable.jsp" %>
  </c:if>

</form:form>


