
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="firmaForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="firmaFormTitle.jsp" %>
 
  <c:set var="contexte" value="${firmaForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="firmaFormCorePre.jsp" %>

  <%@include file="firmaFormCore.jsp" %>

  <%@include file="firmaFormCorePost.jsp" %>

  <%@include file="firmaFormButtons.jsp" %>

  <c:if test="${not empty firmaForm.sections}">
     <c:set var="__basename" value="firma" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${firmaForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/firmaFormModificable.jsp" %>
  </c:if>

</form:form>


