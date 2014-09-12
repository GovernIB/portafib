
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="usuariPersonaFormTitle.jsp" %>


<form:form modelAttribute="usuariPersonaForm" method="${method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${usuariPersonaForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="usuariPersonaFormCorePre.jsp" %>
  <%@include file="usuariPersonaFormCore.jsp" %>

  <%@include file="usuariPersonaFormCorePost.jsp" %>

  <%@include file="usuariPersonaFormButtons.jsp" %>

  <c:if test="${usuariPersonaForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/usuariPersonaFormModificable.jsp" %>
  </c:if>

</form:form>


