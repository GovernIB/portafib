<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${entitatFilterForm.contexte}"/>
  <c:set var="formName" value="entitat" />
  <c:set var="__theFilterForm" value="${entitatFilterForm}" />
  <c:if test="${empty entitatFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="entitat.entitat"/>
  </c:if>
  <c:if test="${not empty entitatFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${entitatFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty entitatFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="entitat.entitat"/>
  </c:if>
  <c:if test="${not empty entitatFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${entitatFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.entitat.submit();  
  }
</script>
