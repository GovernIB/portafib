
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="roleFormTitle.jsp" %>


<form:form modelAttribute="roleForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${roleForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="roleFormCorePre.jsp" %>
  <%@include file="roleFormCore.jsp" %>

  <%@include file="roleFormCorePost.jsp" %>

  <%@include file="roleFormButtons.jsp" %>

  <c:if test="${roleForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/roleFormModificable.jsp" %>
  </c:if>

</form:form>


