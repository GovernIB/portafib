<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${peticioDeFirmaFilterForm.contexte}"/>
  <c:set var="formName" value="peticioDeFirma" />
  <c:set var="__theFilterForm" value="${peticioDeFirmaFilterForm}" />
  <c:if test="${empty peticioDeFirmaFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="peticioDeFirma.peticioDeFirma"/>
  </c:if>
  <c:if test="${not empty peticioDeFirmaFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${peticioDeFirmaFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty peticioDeFirmaFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="peticioDeFirma.peticioDeFirma"/>
  </c:if>
  <c:if test="${not empty peticioDeFirmaFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${peticioDeFirmaFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.peticioDeFirma.submit();  
  }
</script>
