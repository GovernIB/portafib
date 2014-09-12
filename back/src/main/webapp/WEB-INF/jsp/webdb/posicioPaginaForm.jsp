
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="posicioPaginaFormTitle.jsp" %>


<form:form modelAttribute="posicioPaginaForm" method="${method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${posicioPaginaForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="posicioPaginaFormCorePre.jsp" %>
  <%@include file="posicioPaginaFormCore.jsp" %>

  <%@include file="posicioPaginaFormCorePost.jsp" %>

  <%@include file="posicioPaginaFormButtons.jsp" %>

  <c:if test="${posicioPaginaForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/posicioPaginaFormModificable.jsp" %>
  </c:if>

</form:form>


