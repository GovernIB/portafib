<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${pluginCridadaFilterForm.contexte}"/>
  <c:set var="formName" value="pluginCridada" />
  <c:set var="__theFilterForm" value="${pluginCridadaFilterForm}" />
  <c:if test="${empty pluginCridadaFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="pluginCridada.pluginCridada"/>
  </c:if>
  <c:if test="${not empty pluginCridadaFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${pluginCridadaFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty pluginCridadaFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="pluginCridada.pluginCridada"/>
  </c:if>
  <c:if test="${not empty pluginCridadaFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${pluginCridadaFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.pluginCridada.submit();  
  }
</script>
