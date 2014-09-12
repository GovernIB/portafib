<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${permisGrupPlantillaFilterForm.contexte}"/>
  <c:set var="formName" value="permisGrupPlantilla" />
  <c:set var="__theFilterForm" value="${permisGrupPlantillaFilterForm}" />
  <c:if test="${empty permisGrupPlantillaFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="permisGrupPlantilla.permisGrupPlantilla"/>
  </c:if>
  <c:if test="${not empty permisGrupPlantillaFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${permisGrupPlantillaFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty permisGrupPlantillaFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="permisGrupPlantilla.permisGrupPlantilla"/>
  </c:if>
  <c:if test="${not empty permisGrupPlantillaFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${permisGrupPlantillaFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.permisGrupPlantilla.submit();  
  }
</script>
