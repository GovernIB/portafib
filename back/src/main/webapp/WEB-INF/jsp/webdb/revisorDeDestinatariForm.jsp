
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="revisorDeDestinatariForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="revisorDeDestinatariFormTitle.jsp" %>
 
  <c:set var="contexte" value="${revisorDeDestinatariForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="revisorDeDestinatariFormCorePre.jsp" %>

  <%@include file="revisorDeDestinatariFormCore.jsp" %>

  <%@include file="revisorDeDestinatariFormCorePost.jsp" %>

  <%@include file="revisorDeDestinatariFormButtons.jsp" %>

  <c:if test="${not empty revisorDeDestinatariForm.sections}">
     <c:set var="__basename" value="revisorDeDestinatari" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${revisorDeDestinatariForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/revisorDeDestinatariFormModificable.jsp" %>
  </c:if>

</form:form>


