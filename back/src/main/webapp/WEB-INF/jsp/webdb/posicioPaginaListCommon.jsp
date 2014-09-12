<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${posicioPaginaFilterForm.contexte}"/>
  <c:set var="formName" value="posicioPagina" />
  <c:set var="__theFilterForm" value="${posicioPaginaFilterForm}" />
  <c:if test="${empty posicioPaginaFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="posicioPagina.posicioPagina"/>
  </c:if>
  <c:if test="${not empty posicioPaginaFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${posicioPaginaFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty posicioPaginaFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="posicioPagina.posicioPagina"/>
  </c:if>
  <c:if test="${not empty posicioPaginaFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${posicioPaginaFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.posicioPagina.submit();  
  }
</script>
