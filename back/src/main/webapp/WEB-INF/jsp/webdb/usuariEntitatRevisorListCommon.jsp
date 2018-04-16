<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${usuariEntitatRevisorFilterForm.contexte}"/>
  <c:set var="formName" value="usuariEntitatRevisor" />
  <c:set var="__theFilterForm" value="${usuariEntitatRevisorFilterForm}" />
  <c:if test="${empty usuariEntitatRevisorFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="usuariEntitatRevisor.usuariEntitatRevisor"/>
  </c:if>
  <c:if test="${not empty usuariEntitatRevisorFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${usuariEntitatRevisorFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty usuariEntitatRevisorFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="usuariEntitatRevisor.usuariEntitatRevisor"/>
  </c:if>
  <c:if test="${not empty usuariEntitatRevisorFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${usuariEntitatRevisorFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.usuariEntitatRevisor.submit();  
  }
</script>
