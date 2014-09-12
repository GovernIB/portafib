
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="entitatFormTitle.jsp" %>


<form:form modelAttribute="entitatForm" method="${method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${entitatForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="entitatFormCorePre.jsp" %>
  <%@include file="entitatFormCore.jsp" %>

  <%@include file="entitatFormCorePost.jsp" %>

  <%@include file="entitatFormButtons.jsp" %>

  <c:if test="${entitatForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/entitatFormModificable.jsp" %>
  </c:if>

</form:form>


