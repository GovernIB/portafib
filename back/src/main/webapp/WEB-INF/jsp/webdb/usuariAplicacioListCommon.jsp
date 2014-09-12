<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${usuariAplicacioFilterForm.contexte}"/>
  <c:set var="formName" value="usuariAplicacio" />
  <c:set var="__theFilterForm" value="${usuariAplicacioFilterForm}" />
  <c:if test="${empty usuariAplicacioFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="usuariAplicacio.usuariAplicacio"/>
  </c:if>
  <c:if test="${not empty usuariAplicacioFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${usuariAplicacioFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty usuariAplicacioFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="usuariAplicacio.usuariAplicacio"/>
  </c:if>
  <c:if test="${not empty usuariAplicacioFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${usuariAplicacioFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.usuariAplicacio.submit();  
  }
</script>
