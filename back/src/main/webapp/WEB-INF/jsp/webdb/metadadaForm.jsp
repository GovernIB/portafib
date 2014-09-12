
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="metadadaFormTitle.jsp" %>


<form:form modelAttribute="metadadaForm" method="${method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${metadadaForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="metadadaFormCorePre.jsp" %>
  <%@include file="metadadaFormCore.jsp" %>

  <%@include file="metadadaFormCorePost.jsp" %>

  <%@include file="metadadaFormButtons.jsp" %>

  <c:if test="${metadadaForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/metadadaFormModificable.jsp" %>
  </c:if>

</form:form>


