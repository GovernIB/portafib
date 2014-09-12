<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${permisUsuariPlantillaFilterForm.contexte}"/>
  <c:set var="formName" value="permisUsuariPlantilla" />
  <c:set var="__theFilterForm" value="${permisUsuariPlantillaFilterForm}" />
  <c:if test="${empty permisUsuariPlantillaFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="permisUsuariPlantilla.permisUsuariPlantilla"/>
  </c:if>
  <c:if test="${not empty permisUsuariPlantillaFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${permisUsuariPlantillaFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty permisUsuariPlantillaFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="permisUsuariPlantilla.permisUsuariPlantilla"/>
  </c:if>
  <c:if test="${not empty permisUsuariPlantillaFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${permisUsuariPlantillaFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.permisUsuariPlantilla.submit();  
  }
</script>
