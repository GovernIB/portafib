
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="colaboracioDelegacioForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="colaboracioDelegacioFormTitle.jsp" %>
 
  <c:set var="contexte" value="${colaboracioDelegacioForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="colaboracioDelegacioFormCorePre.jsp" %>

  <%@include file="colaboracioDelegacioFormCore.jsp" %>

  <%@include file="colaboracioDelegacioFormCorePost.jsp" %>

  <%@include file="colaboracioDelegacioFormButtons.jsp" %>

  <c:if test="${not empty colaboracioDelegacioForm.sections}">
     <c:set var="__basename" value="colaboracioDelegacio" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${colaboracioDelegacioForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/colaboracioDelegacioFormModificable.jsp" %>
  </c:if>

</form:form>


