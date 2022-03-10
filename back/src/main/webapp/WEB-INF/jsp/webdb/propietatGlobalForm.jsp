
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="propietatGlobalFormTitle.jsp" %>


<form:form modelAttribute="propietatGlobalForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${propietatGlobalForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="propietatGlobalFormCorePre.jsp" %>

  <%@include file="propietatGlobalFormCore.jsp" %>

  <%@include file="propietatGlobalFormCorePost.jsp" %>

  <%@include file="propietatGlobalFormButtons.jsp" %>

  <c:if test="${not empty propietatGlobalForm.sections}">
     <c:set var="__basename" value="propietatGlobal" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${propietatGlobalForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/propietatGlobalFormModificable.jsp" %>
  </c:if>

</form:form>


