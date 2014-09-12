<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${rebreAvisFilterForm.contexte}"/>
  <c:set var="formName" value="rebreAvis" />
  <c:set var="__theFilterForm" value="${rebreAvisFilterForm}" />
  <c:if test="${empty rebreAvisFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="rebreAvis.rebreAvis"/>
  </c:if>
  <c:if test="${not empty rebreAvisFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${rebreAvisFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty rebreAvisFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="rebreAvis.rebreAvis"/>
  </c:if>
  <c:if test="${not empty rebreAvisFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${rebreAvisFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.rebreAvis.submit();  
  }
</script>
