<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${tipusEstatDeFirmaFinalFilterForm.contexte}"/>
  <c:set var="formName" value="tipusEstatDeFirmaFinal" />
  <c:set var="__theFilterForm" value="${tipusEstatDeFirmaFinalFilterForm}" />
  <c:if test="${empty tipusEstatDeFirmaFinalFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="tipusEstatDeFirmaFinal.tipusEstatDeFirmaFinal"/>
  </c:if>
  <c:if test="${not empty tipusEstatDeFirmaFinalFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${tipusEstatDeFirmaFinalFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty tipusEstatDeFirmaFinalFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="tipusEstatDeFirmaFinal.tipusEstatDeFirmaFinal"/>
  </c:if>
  <c:if test="${not empty tipusEstatDeFirmaFinalFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${tipusEstatDeFirmaFinalFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.tipusEstatDeFirmaFinal.submit();  
  }
</script>
