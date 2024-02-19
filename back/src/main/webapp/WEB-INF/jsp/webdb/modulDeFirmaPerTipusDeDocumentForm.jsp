
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="modulDeFirmaPerTipusDeDocumentForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="modulDeFirmaPerTipusDeDocumentFormTitle.jsp" %>
 
  <c:set var="contexte" value="${modulDeFirmaPerTipusDeDocumentForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="modulDeFirmaPerTipusDeDocumentFormCorePre.jsp" %>

  <%@include file="modulDeFirmaPerTipusDeDocumentFormCore.jsp" %>

  <%@include file="modulDeFirmaPerTipusDeDocumentFormCorePost.jsp" %>

  <%@include file="modulDeFirmaPerTipusDeDocumentFormButtons.jsp" %>

  <c:if test="${not empty modulDeFirmaPerTipusDeDocumentForm.sections}">
     <c:set var="__basename" value="modulDeFirmaPerTipusDeDocument" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${modulDeFirmaPerTipusDeDocumentForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/modulDeFirmaPerTipusDeDocumentFormModificable.jsp" %>
  </c:if>

</form:form>


