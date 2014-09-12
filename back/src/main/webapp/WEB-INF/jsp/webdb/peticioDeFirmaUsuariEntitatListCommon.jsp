<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${peticioDeFirmaUsuariEntitatFilterForm.contexte}"/>
  <c:set var="formName" value="peticioDeFirmaUsuariEntitat" />
  <c:set var="__theFilterForm" value="${peticioDeFirmaUsuariEntitatFilterForm}" />
  <c:if test="${empty peticioDeFirmaUsuariEntitatFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="peticioDeFirmaUsuariEntitat.peticioDeFirmaUsuariEntitat"/>
  </c:if>
  <c:if test="${not empty peticioDeFirmaUsuariEntitatFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${peticioDeFirmaUsuariEntitatFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty peticioDeFirmaUsuariEntitatFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="peticioDeFirmaUsuariEntitat.peticioDeFirmaUsuariEntitat"/>
  </c:if>
  <c:if test="${not empty peticioDeFirmaUsuariEntitatFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${peticioDeFirmaUsuariEntitatFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.peticioDeFirmaUsuariEntitat.submit();  
  }
</script>
