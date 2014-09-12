
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="tipusEstatPeticioDeFirmaFormTitle.jsp" %>


<form:form modelAttribute="tipusEstatPeticioDeFirmaForm" method="${method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${tipusEstatPeticioDeFirmaForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="tipusEstatPeticioDeFirmaFormCorePre.jsp" %>
  <%@include file="tipusEstatPeticioDeFirmaFormCore.jsp" %>

  <%@include file="tipusEstatPeticioDeFirmaFormCorePost.jsp" %>

  <%@include file="tipusEstatPeticioDeFirmaFormButtons.jsp" %>

  <c:if test="${tipusEstatPeticioDeFirmaForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/tipusEstatPeticioDeFirmaFormModificable.jsp" %>
  </c:if>

</form:form>


