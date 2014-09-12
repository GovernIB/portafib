<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${plantillaFluxDeFirmesFilterForm.contexte}"/>
  <c:set var="formName" value="plantillaFluxDeFirmes" />
  <c:set var="__theFilterForm" value="${plantillaFluxDeFirmesFilterForm}" />
  <c:if test="${empty plantillaFluxDeFirmesFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="plantillaFluxDeFirmes.plantillaFluxDeFirmes"/>
  </c:if>
  <c:if test="${not empty plantillaFluxDeFirmesFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${plantillaFluxDeFirmesFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty plantillaFluxDeFirmesFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="plantillaFluxDeFirmes.plantillaFluxDeFirmes"/>
  </c:if>
  <c:if test="${not empty plantillaFluxDeFirmesFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${plantillaFluxDeFirmesFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.plantillaFluxDeFirmes.submit();  
  }
</script>
