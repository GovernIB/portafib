
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="revisorDeFirmaFormTitle.jsp" %>


<form:form modelAttribute="revisorDeFirmaForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${revisorDeFirmaForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="revisorDeFirmaFormCorePre.jsp" %>

  <%@include file="revisorDeFirmaFormCore.jsp" %>

  <%@include file="revisorDeFirmaFormCorePost.jsp" %>

  <%@include file="revisorDeFirmaFormButtons.jsp" %>

  <c:if test="${not empty revisorDeFirmaForm.sections}">
     <c:set var="__basename" value="revisorDeFirma" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${revisorDeFirmaForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/revisorDeFirmaFormModificable.jsp" %>
  </c:if>

</form:form>


