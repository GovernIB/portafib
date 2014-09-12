
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="posicioTaulaFirmesFormTitle.jsp" %>


<form:form modelAttribute="posicioTaulaFirmesForm" method="${method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${posicioTaulaFirmesForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="posicioTaulaFirmesFormCorePre.jsp" %>
  <%@include file="posicioTaulaFirmesFormCore.jsp" %>

  <%@include file="posicioTaulaFirmesFormCorePost.jsp" %>

  <%@include file="posicioTaulaFirmesFormButtons.jsp" %>

  <c:if test="${posicioTaulaFirmesForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/posicioTaulaFirmesFormModificable.jsp" %>
  </c:if>

</form:form>


