
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="usuariEntitatFavoritFormTitle.jsp" %>


<form:form modelAttribute="usuariEntitatFavoritForm" method="${method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${usuariEntitatFavoritForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="usuariEntitatFavoritFormCorePre.jsp" %>
  <%@include file="usuariEntitatFavoritFormCore.jsp" %>

  <%@include file="usuariEntitatFavoritFormCorePost.jsp" %>

  <%@include file="usuariEntitatFavoritFormButtons.jsp" %>

  <c:if test="${usuariEntitatFavoritForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/usuariEntitatFavoritFormModificable.jsp" %>
  </c:if>

</form:form>


