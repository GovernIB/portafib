
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="tipusFirmaFormTitle.jsp" %>


<form:form modelAttribute="tipusFirmaForm" method="${method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${tipusFirmaForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="tipusFirmaFormCorePre.jsp" %>
  <%@include file="tipusFirmaFormCore.jsp" %>

  <%@include file="tipusFirmaFormCorePost.jsp" %>

  <%@include file="tipusFirmaFormButtons.jsp" %>

  <c:if test="${tipusFirmaForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/tipusFirmaFormModificable.jsp" %>
  </c:if>

</form:form>


