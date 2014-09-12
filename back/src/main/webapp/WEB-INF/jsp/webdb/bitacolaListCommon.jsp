<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${bitacolaFilterForm.contexte}"/>
  <c:set var="formName" value="bitacola" />
  <c:set var="__theFilterForm" value="${bitacolaFilterForm}" />
  <c:if test="${empty bitacolaFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="bitacola.bitacola"/>
  </c:if>
  <c:if test="${not empty bitacolaFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${bitacolaFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty bitacolaFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="bitacola.bitacola"/>
  </c:if>
  <c:if test="${not empty bitacolaFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${bitacolaFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.bitacola.submit();  
  }
</script>
