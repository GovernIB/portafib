<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${correuAgrupatFilterForm.contexte}"/>
  <c:set var="formName" value="correuAgrupat" />
  <c:set var="__theFilterForm" value="${correuAgrupatFilterForm}" />
  <c:if test="${empty correuAgrupatFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="correuAgrupat.correuAgrupat"/>
  </c:if>
  <c:if test="${not empty correuAgrupatFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${correuAgrupatFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty correuAgrupatFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="correuAgrupat.correuAgrupat"/>
  </c:if>
  <c:if test="${not empty correuAgrupatFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${correuAgrupatFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.correuAgrupat.submit();  
  }
</script>
