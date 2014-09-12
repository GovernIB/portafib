
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="usuariEntitatFormTitle.jsp" %>


<form:form modelAttribute="usuariEntitatForm" method="${method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${usuariEntitatForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="usuariEntitatFormCorePre.jsp" %>
  <%@include file="usuariEntitatFormCore.jsp" %>

  <%@include file="usuariEntitatFormCorePost.jsp" %>

  <%@include file="usuariEntitatFormButtons.jsp" %>

  <c:if test="${usuariEntitatForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/usuariEntitatFormModificable.jsp" %>
  </c:if>

</form:form>


