<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${fluxDeFirmesFilterForm.contexte}"/>
  <c:set var="formName" value="fluxDeFirmes" />
  <c:set var="__theFilterForm" value="${fluxDeFirmesFilterForm}" />
  <c:if test="${empty fluxDeFirmesFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="fluxDeFirmes.fluxDeFirmes"/>
  </c:if>
  <c:if test="${not empty fluxDeFirmesFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${fluxDeFirmesFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty fluxDeFirmesFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="fluxDeFirmes.fluxDeFirmes"/>
  </c:if>
  <c:if test="${not empty fluxDeFirmesFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${fluxDeFirmesFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.fluxDeFirmes.submit();  
  }
</script>
