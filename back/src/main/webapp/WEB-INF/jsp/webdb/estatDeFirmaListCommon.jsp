<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${estatDeFirmaFilterForm.contexte}"/>
  <c:set var="formName" value="estatDeFirma" />
  <c:set var="__theFilterForm" value="${estatDeFirmaFilterForm}" />
  <c:if test="${empty estatDeFirmaFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="estatDeFirma.estatDeFirma"/>
  </c:if>
  <c:if test="${not empty estatDeFirmaFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${estatDeFirmaFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty estatDeFirmaFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="estatDeFirma.estatDeFirma"/>
  </c:if>
  <c:if test="${not empty estatDeFirmaFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${estatDeFirmaFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.estatDeFirma.submit();  
  }
</script>
