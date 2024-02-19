
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="fluxDeFirmesForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="fluxDeFirmesFormTitle.jsp" %>
 
  <c:set var="contexte" value="${fluxDeFirmesForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="fluxDeFirmesFormCorePre.jsp" %>

  <%@include file="fluxDeFirmesFormCore.jsp" %>

  <%@include file="fluxDeFirmesFormCorePost.jsp" %>

  <%@include file="fluxDeFirmesFormButtons.jsp" %>

  <c:if test="${not empty fluxDeFirmesForm.sections}">
     <c:set var="__basename" value="fluxDeFirmes" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${fluxDeFirmesForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/fluxDeFirmesFormModificable.jsp" %>
  </c:if>

</form:form>


