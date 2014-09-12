
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="permisUsuariPlantillaFormTitle.jsp" %>


<form:form modelAttribute="permisUsuariPlantillaForm" method="${method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${permisUsuariPlantillaForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="permisUsuariPlantillaFormCorePre.jsp" %>
  <%@include file="permisUsuariPlantillaFormCore.jsp" %>

  <%@include file="permisUsuariPlantillaFormCorePost.jsp" %>

  <%@include file="permisUsuariPlantillaFormButtons.jsp" %>

  <c:if test="${permisUsuariPlantillaForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/permisUsuariPlantillaFormModificable.jsp" %>
  </c:if>

</form:form>


