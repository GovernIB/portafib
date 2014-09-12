<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${tipusDocumentFilterForm.contexte}"/>
  <c:set var="formName" value="tipusDocument" />
  <c:set var="__theFilterForm" value="${tipusDocumentFilterForm}" />
  <c:if test="${empty tipusDocumentFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="tipusDocument.tipusDocument"/>
  </c:if>
  <c:if test="${not empty tipusDocumentFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${tipusDocumentFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty tipusDocumentFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="tipusDocument.tipusDocument"/>
  </c:if>
  <c:if test="${not empty tipusDocumentFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${tipusDocumentFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.tipusDocument.submit();  
  }
</script>
