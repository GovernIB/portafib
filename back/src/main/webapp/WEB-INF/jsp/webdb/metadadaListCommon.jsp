<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${metadadaFilterForm.contexte}"/>
  <c:set var="formName" value="metadada" />
  <c:set var="__theFilterForm" value="${metadadaFilterForm}" />
  <c:if test="${empty metadadaFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="metadada.metadada"/>
  </c:if>
  <c:if test="${not empty metadadaFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${metadadaFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty metadadaFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="metadada.metadada"/>
  </c:if>
  <c:if test="${not empty metadadaFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${metadadaFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.metadada.submit();  
  }
</script>
