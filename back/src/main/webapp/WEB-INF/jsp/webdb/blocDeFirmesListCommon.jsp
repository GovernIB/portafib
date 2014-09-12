<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${blocDeFirmesFilterForm.contexte}"/>
  <c:set var="formName" value="blocDeFirmes" />
  <c:set var="__theFilterForm" value="${blocDeFirmesFilterForm}" />
  <c:if test="${empty blocDeFirmesFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="blocDeFirmes.blocDeFirmes"/>
  </c:if>
  <c:if test="${not empty blocDeFirmesFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${blocDeFirmesFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty blocDeFirmesFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="blocDeFirmes.blocDeFirmes"/>
  </c:if>
  <c:if test="${not empty blocDeFirmesFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${blocDeFirmesFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.blocDeFirmes.submit();  
  }
</script>
