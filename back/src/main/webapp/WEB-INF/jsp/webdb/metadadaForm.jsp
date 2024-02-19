
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="metadadaForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="metadadaFormTitle.jsp" %>
 
  <c:set var="contexte" value="${metadadaForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="metadadaFormCorePre.jsp" %>

  <%@include file="metadadaFormCore.jsp" %>

  <%@include file="metadadaFormCorePost.jsp" %>

  <%@include file="metadadaFormButtons.jsp" %>

  <c:if test="${not empty metadadaForm.sections}">
     <c:set var="__basename" value="metadada" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${metadadaForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/metadadaFormModificable.jsp" %>
  </c:if>

</form:form>


