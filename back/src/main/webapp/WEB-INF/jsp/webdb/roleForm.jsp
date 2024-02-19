
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="roleForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="roleFormTitle.jsp" %>
 
  <c:set var="contexte" value="${roleForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="roleFormCorePre.jsp" %>

  <%@include file="roleFormCore.jsp" %>

  <%@include file="roleFormCorePost.jsp" %>

  <%@include file="roleFormButtons.jsp" %>

  <c:if test="${not empty roleForm.sections}">
     <c:set var="__basename" value="role" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${roleForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/roleFormModificable.jsp" %>
  </c:if>

</form:form>


