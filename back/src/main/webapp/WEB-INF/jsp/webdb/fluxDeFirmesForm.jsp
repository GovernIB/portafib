
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="fluxDeFirmesFormTitle.jsp" %>


<form:form modelAttribute="fluxDeFirmesForm" method="${method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${fluxDeFirmesForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="fluxDeFirmesFormCorePre.jsp" %>
  <%@include file="fluxDeFirmesFormCore.jsp" %>

  <%@include file="fluxDeFirmesFormCorePost.jsp" %>

  <%@include file="fluxDeFirmesFormButtons.jsp" %>

  <c:if test="${fluxDeFirmesForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/fluxDeFirmesFormModificable.jsp" %>
  </c:if>

</form:form>


