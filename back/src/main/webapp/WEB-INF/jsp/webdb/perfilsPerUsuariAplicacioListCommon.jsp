<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${perfilsPerUsuariAplicacioFilterForm.contexte}"/>
  <c:set var="formName" value="perfilsPerUsuariAplicacio" />
  <c:set var="__theFilterForm" value="${perfilsPerUsuariAplicacioFilterForm}" />
  <c:if test="${empty perfilsPerUsuariAplicacioFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="perfilsPerUsuariAplicacio.perfilsPerUsuariAplicacio"/>
  </c:if>
  <c:if test="${not empty perfilsPerUsuariAplicacioFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${perfilsPerUsuariAplicacioFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty perfilsPerUsuariAplicacioFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="perfilsPerUsuariAplicacio.perfilsPerUsuariAplicacio"/>
  </c:if>
  <c:if test="${not empty perfilsPerUsuariAplicacioFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${perfilsPerUsuariAplicacioFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.perfilsPerUsuariAplicacio.submit();  
  }
</script>
