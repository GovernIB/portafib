<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${tipusDocumentColaboracioDelegacioFilterForm.contexte}"/>
  <c:set var="formName" value="tipusDocumentColaboracioDelegacio" />
  <c:set var="__theFilterForm" value="${tipusDocumentColaboracioDelegacioFilterForm}" />
  <c:if test="${empty tipusDocumentColaboracioDelegacioFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="tipusDocumentColaboracioDelegacio.tipusDocumentColaboracioDelegacio"/>
  </c:if>
  <c:if test="${not empty tipusDocumentColaboracioDelegacioFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${tipusDocumentColaboracioDelegacioFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty tipusDocumentColaboracioDelegacioFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="tipusDocumentColaboracioDelegacio.tipusDocumentColaboracioDelegacio"/>
  </c:if>
  <c:if test="${not empty tipusDocumentColaboracioDelegacioFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${tipusDocumentColaboracioDelegacioFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.tipusDocumentColaboracioDelegacio.submit();  
  }
</script>
