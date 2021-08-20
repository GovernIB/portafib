
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="idiomaFormTitle.jsp" %>


<form:form modelAttribute="idiomaForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${idiomaForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="idiomaFormCorePre.jsp" %>
  <%@include file="idiomaFormCore.jsp" %>

  <%@include file="idiomaFormCorePost.jsp" %>

  <%@include file="idiomaFormButtons.jsp" %>

  <c:if test="${idiomaForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/idiomaFormModificable.jsp" %>
  </c:if>

</form:form>


