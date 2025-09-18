<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${pseudonimFilterForm.contexte}"/>
  <c:set var="formName" value="pseudonim" />
  <c:set var="__theFilterForm" value="${pseudonimFilterForm}" />
  <c:if test="${empty pseudonimFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="pseudonim.pseudonim"/>
  </c:if>
  <c:if test="${not empty pseudonimFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${pseudonimFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty pseudonimFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="pseudonim.pseudonim"/>
  </c:if>
  <c:if test="${not empty pseudonimFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${pseudonimFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.pseudonim.submit();  
  }
</script>
