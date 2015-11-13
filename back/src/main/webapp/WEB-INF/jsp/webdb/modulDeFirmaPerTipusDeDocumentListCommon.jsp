<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${modulDeFirmaPerTipusDeDocumentFilterForm.contexte}"/>
  <c:set var="formName" value="modulDeFirmaPerTipusDeDocument" />
  <c:set var="__theFilterForm" value="${modulDeFirmaPerTipusDeDocumentFilterForm}" />
  <c:if test="${empty modulDeFirmaPerTipusDeDocumentFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="modulDeFirmaPerTipusDeDocument.modulDeFirmaPerTipusDeDocument"/>
  </c:if>
  <c:if test="${not empty modulDeFirmaPerTipusDeDocumentFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${modulDeFirmaPerTipusDeDocumentFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty modulDeFirmaPerTipusDeDocumentFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="modulDeFirmaPerTipusDeDocument.modulDeFirmaPerTipusDeDocument"/>
  </c:if>
  <c:if test="${not empty modulDeFirmaPerTipusDeDocumentFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${modulDeFirmaPerTipusDeDocumentFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.modulDeFirmaPerTipusDeDocument.submit();  
  }
</script>
