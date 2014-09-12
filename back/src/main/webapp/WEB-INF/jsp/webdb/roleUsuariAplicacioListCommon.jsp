<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${roleUsuariAplicacioFilterForm.contexte}"/>
  <c:set var="formName" value="roleUsuariAplicacio" />
  <c:set var="__theFilterForm" value="${roleUsuariAplicacioFilterForm}" />
  <c:if test="${empty roleUsuariAplicacioFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="roleUsuariAplicacio.roleUsuariAplicacio"/>
  </c:if>
  <c:if test="${not empty roleUsuariAplicacioFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${roleUsuariAplicacioFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty roleUsuariAplicacioFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="roleUsuariAplicacio.roleUsuariAplicacio"/>
  </c:if>
  <c:if test="${not empty roleUsuariAplicacioFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${roleUsuariAplicacioFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.roleUsuariAplicacio.submit();  
  }
</script>
