<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${tipusNotificacioFilterForm.contexte}"/>
  <c:set var="formName" value="tipusNotificacio" />
  <c:set var="__theFilterForm" value="${tipusNotificacioFilterForm}" />
  <c:if test="${empty tipusNotificacioFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="tipusNotificacio.tipusNotificacio"/>
  </c:if>
  <c:if test="${not empty tipusNotificacioFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${tipusNotificacioFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty tipusNotificacioFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="tipusNotificacio.tipusNotificacio"/>
  </c:if>
  <c:if test="${not empty tipusNotificacioFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${tipusNotificacioFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.tipusNotificacio.submit();  
  }
</script>
