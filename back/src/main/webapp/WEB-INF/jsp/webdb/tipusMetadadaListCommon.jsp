<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${tipusMetadadaFilterForm.contexte}"/>
  <c:set var="formName" value="tipusMetadada" />
  <c:set var="__theFilterForm" value="${tipusMetadadaFilterForm}" />
  <c:if test="${empty tipusMetadadaFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="tipusMetadada.tipusMetadada"/>
  </c:if>
  <c:if test="${not empty tipusMetadadaFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${tipusMetadadaFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty tipusMetadadaFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="tipusMetadada.tipusMetadada"/>
  </c:if>
  <c:if test="${not empty tipusMetadadaFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${tipusMetadadaFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.tipusMetadada.submit();  
  }
</script>
