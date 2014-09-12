<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${grupEntitatFilterForm.contexte}"/>
  <c:set var="formName" value="grupEntitat" />
  <c:set var="__theFilterForm" value="${grupEntitatFilterForm}" />
  <c:if test="${empty grupEntitatFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="grupEntitat.grupEntitat"/>
  </c:if>
  <c:if test="${not empty grupEntitatFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${grupEntitatFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty grupEntitatFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="grupEntitat.grupEntitat"/>
  </c:if>
  <c:if test="${not empty grupEntitatFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${grupEntitatFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.grupEntitat.submit();  
  }
</script>
