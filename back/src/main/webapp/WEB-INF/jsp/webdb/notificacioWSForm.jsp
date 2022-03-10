
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="notificacioWSFormTitle.jsp" %>


<form:form modelAttribute="notificacioWSForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${notificacioWSForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="notificacioWSFormCorePre.jsp" %>

  <%@include file="notificacioWSFormCore.jsp" %>

  <%@include file="notificacioWSFormCorePost.jsp" %>

  <%@include file="notificacioWSFormButtons.jsp" %>

  <c:if test="${not empty notificacioWSForm.sections}">
     <c:set var="__basename" value="notificacioWS" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${notificacioWSForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/notificacioWSFormModificable.jsp" %>
  </c:if>

</form:form>


