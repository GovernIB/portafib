<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${modulDeFirmaFilterForm.contexte}"/>
  <c:set var="formName" value="modulDeFirma" />
  <c:set var="__theFilterForm" value="${modulDeFirmaFilterForm}" />
  <c:if test="${empty modulDeFirmaFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="modulDeFirma.modulDeFirma"/>
  </c:if>
  <c:if test="${not empty modulDeFirmaFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${modulDeFirmaFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty modulDeFirmaFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="modulDeFirma.modulDeFirma"/>
  </c:if>
  <c:if test="${not empty modulDeFirmaFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${modulDeFirmaFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.modulDeFirma.submit();  
  }
</script>
