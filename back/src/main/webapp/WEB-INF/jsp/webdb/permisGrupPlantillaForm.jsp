
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="permisGrupPlantillaFormTitle.jsp" %>


<form:form modelAttribute="permisGrupPlantillaForm" method="${method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${permisGrupPlantillaForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="permisGrupPlantillaFormCorePre.jsp" %>
  <%@include file="permisGrupPlantillaFormCore.jsp" %>

  <%@include file="permisGrupPlantillaFormCorePost.jsp" %>

  <%@include file="permisGrupPlantillaFormButtons.jsp" %>

  <c:if test="${permisGrupPlantillaForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/permisGrupPlantillaFormModificable.jsp" %>
  </c:if>

</form:form>


