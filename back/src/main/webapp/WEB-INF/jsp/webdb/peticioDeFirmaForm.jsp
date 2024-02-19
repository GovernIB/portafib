
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="peticioDeFirmaForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="peticioDeFirmaFormTitle.jsp" %>
 
  <c:set var="contexte" value="${peticioDeFirmaForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="peticioDeFirmaFormCorePre.jsp" %>

  <%@include file="peticioDeFirmaFormCore.jsp" %>

  <%@include file="peticioDeFirmaFormCorePost.jsp" %>

  <%@include file="peticioDeFirmaFormButtons.jsp" %>

  <c:if test="${not empty peticioDeFirmaForm.sections}">
     <c:set var="__basename" value="peticioDeFirma" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${peticioDeFirmaForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/peticioDeFirmaFormModificable.jsp" %>
  </c:if>

</form:form>


