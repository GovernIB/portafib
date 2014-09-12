
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="tipusEstatDeFirmaInicialFormTitle.jsp" %>


<form:form modelAttribute="tipusEstatDeFirmaInicialForm" method="${method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${tipusEstatDeFirmaInicialForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="tipusEstatDeFirmaInicialFormCorePre.jsp" %>
  <%@include file="tipusEstatDeFirmaInicialFormCore.jsp" %>

  <%@include file="tipusEstatDeFirmaInicialFormCorePost.jsp" %>

  <%@include file="tipusEstatDeFirmaInicialFormButtons.jsp" %>

  <c:if test="${tipusEstatDeFirmaInicialForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/tipusEstatDeFirmaInicialFormModificable.jsp" %>
  </c:if>

</form:form>


