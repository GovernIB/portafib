<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${usuariAplicacioConfiguracioFilterForm.contexte}"/>
  <c:set var="formName" value="usuariAplicacioConfiguracio" />
  <c:set var="__theFilterForm" value="${usuariAplicacioConfiguracioFilterForm}" />
  <c:if test="${empty usuariAplicacioConfiguracioFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="usuariAplicacioConfiguracio.usuariAplicacioConfiguracio"/>
  </c:if>
  <c:if test="${not empty usuariAplicacioConfiguracioFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${usuariAplicacioConfiguracioFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty usuariAplicacioConfiguracioFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="usuariAplicacioConfiguracio.usuariAplicacioConfiguracio"/>
  </c:if>
  <c:if test="${not empty usuariAplicacioConfiguracioFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${usuariAplicacioConfiguracioFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.usuariAplicacioConfiguracio.submit();  
  }
</script>
