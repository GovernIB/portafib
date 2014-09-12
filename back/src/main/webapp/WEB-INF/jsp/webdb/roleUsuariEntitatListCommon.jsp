<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${roleUsuariEntitatFilterForm.contexte}"/>
  <c:set var="formName" value="roleUsuariEntitat" />
  <c:set var="__theFilterForm" value="${roleUsuariEntitatFilterForm}" />
  <c:if test="${empty roleUsuariEntitatFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="roleUsuariEntitat.roleUsuariEntitat"/>
  </c:if>
  <c:if test="${not empty roleUsuariEntitatFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${roleUsuariEntitatFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty roleUsuariEntitatFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="roleUsuariEntitat.roleUsuariEntitat"/>
  </c:if>
  <c:if test="${not empty roleUsuariEntitatFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${roleUsuariEntitatFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.roleUsuariEntitat.submit();  
  }
</script>
