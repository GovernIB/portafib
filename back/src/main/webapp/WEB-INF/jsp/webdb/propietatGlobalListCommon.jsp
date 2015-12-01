<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${propietatGlobalFilterForm.contexte}"/>
  <c:set var="formName" value="propietatGlobal" />
  <c:set var="__theFilterForm" value="${propietatGlobalFilterForm}" />
  <c:if test="${empty propietatGlobalFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="propietatGlobal.propietatGlobal"/>
  </c:if>
  <c:if test="${not empty propietatGlobalFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${propietatGlobalFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty propietatGlobalFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="propietatGlobal.propietatGlobal"/>
  </c:if>
  <c:if test="${not empty propietatGlobalFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${propietatGlobalFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.propietatGlobal.submit();  
  }
</script>
