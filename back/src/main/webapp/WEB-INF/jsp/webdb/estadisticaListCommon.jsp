<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${estadisticaFilterForm.contexte}"/>
  <c:set var="formName" value="estadistica" />
  <c:set var="__theFilterForm" value="${estadisticaFilterForm}" />
  <c:if test="${empty estadisticaFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="estadistica.estadistica"/>
  </c:if>
  <c:if test="${not empty estadisticaFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${estadisticaFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty estadisticaFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="estadistica.estadistica"/>
  </c:if>
  <c:if test="${not empty estadisticaFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${estadisticaFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.estadistica.submit();  
  }
</script>
