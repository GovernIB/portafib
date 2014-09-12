
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="tipusEstatDeFirmaFinalFormTitle.jsp" %>


<form:form modelAttribute="tipusEstatDeFirmaFinalForm" method="${method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${tipusEstatDeFirmaFinalForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="tipusEstatDeFirmaFinalFormCorePre.jsp" %>
  <%@include file="tipusEstatDeFirmaFinalFormCore.jsp" %>

  <%@include file="tipusEstatDeFirmaFinalFormCorePost.jsp" %>

  <%@include file="tipusEstatDeFirmaFinalFormButtons.jsp" %>

  <c:if test="${tipusEstatDeFirmaFinalForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/tipusEstatDeFirmaFinalFormModificable.jsp" %>
  </c:if>

</form:form>


