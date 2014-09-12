<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${tipusEstatDeFirmaInicialFilterForm.contexte}"/>
  <c:set var="formName" value="tipusEstatDeFirmaInicial" />
  <c:set var="__theFilterForm" value="${tipusEstatDeFirmaInicialFilterForm}" />
  <c:if test="${empty tipusEstatDeFirmaInicialFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="tipusEstatDeFirmaInicial.tipusEstatDeFirmaInicial"/>
  </c:if>
  <c:if test="${not empty tipusEstatDeFirmaInicialFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${tipusEstatDeFirmaInicialFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty tipusEstatDeFirmaInicialFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="tipusEstatDeFirmaInicial.tipusEstatDeFirmaInicial"/>
  </c:if>
  <c:if test="${not empty tipusEstatDeFirmaInicialFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${tipusEstatDeFirmaInicialFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.tipusEstatDeFirmaInicial.submit();  
  }
</script>
