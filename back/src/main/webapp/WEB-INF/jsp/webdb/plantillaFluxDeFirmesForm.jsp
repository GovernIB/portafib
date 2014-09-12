
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="plantillaFluxDeFirmesFormTitle.jsp" %>


<form:form modelAttribute="plantillaFluxDeFirmesForm" method="${method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${plantillaFluxDeFirmesForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="plantillaFluxDeFirmesFormCorePre.jsp" %>
  <%@include file="plantillaFluxDeFirmesFormCore.jsp" %>

  <%@include file="plantillaFluxDeFirmesFormCorePost.jsp" %>

  <%@include file="plantillaFluxDeFirmesFormButtons.jsp" %>

  <c:if test="${plantillaFluxDeFirmesForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/plantillaFluxDeFirmesFormModificable.jsp" %>
  </c:if>

</form:form>


