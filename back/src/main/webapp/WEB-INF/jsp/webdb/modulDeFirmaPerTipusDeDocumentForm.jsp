
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="modulDeFirmaPerTipusDeDocumentFormTitle.jsp" %>


<form:form modelAttribute="modulDeFirmaPerTipusDeDocumentForm" method="${method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${modulDeFirmaPerTipusDeDocumentForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="modulDeFirmaPerTipusDeDocumentFormCorePre.jsp" %>
  <%@include file="modulDeFirmaPerTipusDeDocumentFormCore.jsp" %>

  <%@include file="modulDeFirmaPerTipusDeDocumentFormCorePost.jsp" %>

  <%@include file="modulDeFirmaPerTipusDeDocumentFormButtons.jsp" %>

  <c:if test="${modulDeFirmaPerTipusDeDocumentForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/modulDeFirmaPerTipusDeDocumentFormModificable.jsp" %>
  </c:if>

</form:form>


