<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${colaboracioDelegacioFilterForm.contexte}"/>
  <c:set var="formName" value="colaboracioDelegacio" />
  <c:set var="__theFilterForm" value="${colaboracioDelegacioFilterForm}" />
  <c:if test="${empty colaboracioDelegacioFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="colaboracioDelegacio.colaboracioDelegacio"/>
  </c:if>
  <c:if test="${not empty colaboracioDelegacioFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${colaboracioDelegacioFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty colaboracioDelegacioFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="colaboracioDelegacio.colaboracioDelegacio"/>
  </c:if>
  <c:if test="${not empty colaboracioDelegacioFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${colaboracioDelegacioFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.colaboracioDelegacio.submit();  
  }
</script>
