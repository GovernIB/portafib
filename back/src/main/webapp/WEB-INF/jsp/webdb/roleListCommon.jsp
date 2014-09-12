<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${roleFilterForm.contexte}"/>
  <c:set var="formName" value="role" />
  <c:set var="__theFilterForm" value="${roleFilterForm}" />
  <c:if test="${empty roleFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="role.role"/>
  </c:if>
  <c:if test="${not empty roleFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${roleFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty roleFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="role.role"/>
  </c:if>
  <c:if test="${not empty roleFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${roleFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.role.submit();  
  }
</script>
