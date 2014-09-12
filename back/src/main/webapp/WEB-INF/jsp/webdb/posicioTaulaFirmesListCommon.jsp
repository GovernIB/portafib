<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${posicioTaulaFirmesFilterForm.contexte}"/>
  <c:set var="formName" value="posicioTaulaFirmes" />
  <c:set var="__theFilterForm" value="${posicioTaulaFirmesFilterForm}" />
  <c:if test="${empty posicioTaulaFirmesFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="posicioTaulaFirmes.posicioTaulaFirmes"/>
  </c:if>
  <c:if test="${not empty posicioTaulaFirmesFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${posicioTaulaFirmesFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty posicioTaulaFirmesFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="posicioTaulaFirmes.posicioTaulaFirmes"/>
  </c:if>
  <c:if test="${not empty posicioTaulaFirmesFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${posicioTaulaFirmesFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.posicioTaulaFirmes.submit();  
  }
</script>
