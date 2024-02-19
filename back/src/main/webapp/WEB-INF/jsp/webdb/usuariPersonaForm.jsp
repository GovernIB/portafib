
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="usuariPersonaForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="usuariPersonaFormTitle.jsp" %>
 
  <c:set var="contexte" value="${usuariPersonaForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="usuariPersonaFormCorePre.jsp" %>

  <%@include file="usuariPersonaFormCore.jsp" %>

  <%@include file="usuariPersonaFormCorePost.jsp" %>

  <%@include file="usuariPersonaFormButtons.jsp" %>

  <c:if test="${not empty usuariPersonaForm.sections}">
     <c:set var="__basename" value="usuariPersona" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${usuariPersonaForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/usuariPersonaFormModificable.jsp" %>
  </c:if>

</form:form>


