
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="tipusNotificacioFormTitle.jsp" %>


<form:form modelAttribute="tipusNotificacioForm" method="${method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${tipusNotificacioForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="tipusNotificacioFormCorePre.jsp" %>
  <%@include file="tipusNotificacioFormCore.jsp" %>

  <%@include file="tipusNotificacioFormCorePost.jsp" %>

  <%@include file="tipusNotificacioFormButtons.jsp" %>

  <c:if test="${tipusNotificacioForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/tipusNotificacioFormModificable.jsp" %>
  </c:if>

</form:form>

