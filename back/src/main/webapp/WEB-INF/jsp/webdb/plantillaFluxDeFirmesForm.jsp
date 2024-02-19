
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="plantillaFluxDeFirmesForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="plantillaFluxDeFirmesFormTitle.jsp" %>
 
  <c:set var="contexte" value="${plantillaFluxDeFirmesForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="plantillaFluxDeFirmesFormCorePre.jsp" %>

  <%@include file="plantillaFluxDeFirmesFormCore.jsp" %>

  <%@include file="plantillaFluxDeFirmesFormCorePost.jsp" %>

  <%@include file="plantillaFluxDeFirmesFormButtons.jsp" %>

  <c:if test="${not empty plantillaFluxDeFirmesForm.sections}">
     <c:set var="__basename" value="plantillaFluxDeFirmes" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${plantillaFluxDeFirmesForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/plantillaFluxDeFirmesFormModificable.jsp" %>
  </c:if>

</form:form>


