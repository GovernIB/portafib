<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${custodiaInfoFilterForm.contexte}"/>
  <c:set var="formName" value="custodiaInfo" />
  <c:set var="__theFilterForm" value="${custodiaInfoFilterForm}" />
  <c:if test="${empty custodiaInfoFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="custodiaInfo.custodiaInfo"/>
  </c:if>
  <c:if test="${not empty custodiaInfoFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${custodiaInfoFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty custodiaInfoFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="custodiaInfo.custodiaInfo"/>
  </c:if>
  <c:if test="${not empty custodiaInfoFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${custodiaInfoFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.custodiaInfo.submit();  
  }
</script>
