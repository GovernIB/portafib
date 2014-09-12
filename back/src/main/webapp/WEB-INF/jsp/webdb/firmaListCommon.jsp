<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${firmaFilterForm.contexte}"/>
  <c:set var="formName" value="firma" />
  <c:set var="__theFilterForm" value="${firmaFilterForm}" />
  <c:if test="${empty firmaFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="firma.firma"/>
  </c:if>
  <c:if test="${not empty firmaFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${firmaFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty firmaFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="firma.firma"/>
  </c:if>
  <c:if test="${not empty firmaFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${firmaFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.firma.submit();  
  }
</script>
