
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="grupEntitatFormTitle.jsp" %>


<form:form modelAttribute="grupEntitatForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${grupEntitatForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="grupEntitatFormCorePre.jsp" %>

  <%@include file="grupEntitatFormCore.jsp" %>

  <%@include file="grupEntitatFormCorePost.jsp" %>

  <%@include file="grupEntitatFormButtons.jsp" %>

  <c:if test="${not empty grupEntitatForm.sections}">
     <c:set var="__basename" value="grupEntitat" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${grupEntitatForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/grupEntitatFormModificable.jsp" %>
  </c:if>

</form:form>


