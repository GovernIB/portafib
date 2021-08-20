
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="peticioDeFirmaFormTitle.jsp" %>


<form:form modelAttribute="peticioDeFirmaForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${peticioDeFirmaForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="peticioDeFirmaFormCorePre.jsp" %>
  <%@include file="peticioDeFirmaFormCore.jsp" %>

  <%@include file="peticioDeFirmaFormCorePost.jsp" %>

  <%@include file="peticioDeFirmaFormButtons.jsp" %>

  <c:if test="${peticioDeFirmaForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/peticioDeFirmaFormModificable.jsp" %>
  </c:if>

</form:form>


