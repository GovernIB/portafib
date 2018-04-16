<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${revisorDeFirmaFilterForm.contexte}"/>
  <c:set var="formName" value="revisorDeFirma" />
  <c:set var="__theFilterForm" value="${revisorDeFirmaFilterForm}" />
  <c:if test="${empty revisorDeFirmaFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="revisorDeFirma.revisorDeFirma"/>
  </c:if>
  <c:if test="${not empty revisorDeFirmaFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${revisorDeFirmaFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty revisorDeFirmaFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="revisorDeFirma.revisorDeFirma"/>
  </c:if>
  <c:if test="${not empty revisorDeFirmaFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${revisorDeFirmaFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.revisorDeFirma.submit();  
  }
</script>
