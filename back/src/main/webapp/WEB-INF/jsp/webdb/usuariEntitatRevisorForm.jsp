
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="usuariEntitatRevisorFormTitle.jsp" %>


<form:form modelAttribute="usuariEntitatRevisorForm" method="${method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${usuariEntitatRevisorForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="usuariEntitatRevisorFormCorePre.jsp" %>
  <%@include file="usuariEntitatRevisorFormCore.jsp" %>

  <%@include file="usuariEntitatRevisorFormCorePost.jsp" %>

  <%@include file="usuariEntitatRevisorFormButtons.jsp" %>

  <c:if test="${usuariEntitatRevisorForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/usuariEntitatRevisorFormModificable.jsp" %>
  </c:if>

</form:form>


