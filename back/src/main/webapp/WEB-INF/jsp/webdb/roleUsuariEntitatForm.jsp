
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="roleUsuariEntitatFormTitle.jsp" %>


<form:form modelAttribute="roleUsuariEntitatForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${roleUsuariEntitatForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="roleUsuariEntitatFormCorePre.jsp" %>

  <%@include file="roleUsuariEntitatFormCore.jsp" %>

  <%@include file="roleUsuariEntitatFormCorePost.jsp" %>

  <%@include file="roleUsuariEntitatFormButtons.jsp" %>

  <c:if test="${not empty roleUsuariEntitatForm.sections}">
     <c:set var="__basename" value="roleUsuariEntitat" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${roleUsuariEntitatForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/roleUsuariEntitatFormModificable.jsp" %>
  </c:if>

</form:form>


