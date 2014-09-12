
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="colaboracioDelegacioFormTitle.jsp" %>


<form:form modelAttribute="colaboracioDelegacioForm" method="${method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${colaboracioDelegacioForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="colaboracioDelegacioFormCorePre.jsp" %>
  <%@include file="colaboracioDelegacioFormCore.jsp" %>

  <%@include file="colaboracioDelegacioFormCorePost.jsp" %>

  <%@include file="colaboracioDelegacioFormButtons.jsp" %>

  <c:if test="${colaboracioDelegacioForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/colaboracioDelegacioFormModificable.jsp" %>
  </c:if>

</form:form>


