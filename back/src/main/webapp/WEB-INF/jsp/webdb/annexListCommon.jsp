<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${annexFilterForm.contexte}"/>
  <c:set var="formName" value="annex" />
  <c:set var="__theFilterForm" value="${annexFilterForm}" />
  <c:if test="${empty annexFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="annex.annex"/>
  </c:if>
  <c:if test="${not empty annexFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${annexFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty annexFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="annex.annex"/>
  </c:if>
  <c:if test="${not empty annexFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${annexFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.annex.submit();  
  }
</script>
