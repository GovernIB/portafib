<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${prioritatFilterForm.contexte}"/>
  <c:set var="formName" value="prioritat" />
  <c:set var="__theFilterForm" value="${prioritatFilterForm}" />
  <c:if test="${empty prioritatFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="prioritat.prioritat"/>
  </c:if>
  <c:if test="${not empty prioritatFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${prioritatFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty prioritatFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="prioritat.prioritat"/>
  </c:if>
  <c:if test="${not empty prioritatFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${prioritatFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.prioritat.submit();  
  }
</script>
