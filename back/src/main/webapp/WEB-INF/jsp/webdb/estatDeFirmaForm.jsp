
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="estatDeFirmaFormTitle.jsp" %>


<form:form modelAttribute="estatDeFirmaForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${estatDeFirmaForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="estatDeFirmaFormCorePre.jsp" %>
  <%@include file="estatDeFirmaFormCore.jsp" %>

  <%@include file="estatDeFirmaFormCorePost.jsp" %>

  <%@include file="estatDeFirmaFormButtons.jsp" %>

  <c:if test="${estatDeFirmaForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/estatDeFirmaFormModificable.jsp" %>
  </c:if>

</form:form>


