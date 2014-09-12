<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${mitjaDeComunicacioFilterForm.contexte}"/>
  <c:set var="formName" value="mitjaDeComunicacio" />
  <c:set var="__theFilterForm" value="${mitjaDeComunicacioFilterForm}" />
  <c:if test="${empty mitjaDeComunicacioFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="mitjaDeComunicacio.mitjaDeComunicacio"/>
  </c:if>
  <c:if test="${not empty mitjaDeComunicacioFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${mitjaDeComunicacioFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty mitjaDeComunicacioFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="mitjaDeComunicacio.mitjaDeComunicacio"/>
  </c:if>
  <c:if test="${not empty mitjaDeComunicacioFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${mitjaDeComunicacioFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.mitjaDeComunicacio.submit();  
  }
</script>
