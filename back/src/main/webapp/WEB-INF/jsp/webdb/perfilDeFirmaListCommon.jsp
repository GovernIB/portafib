<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${perfilDeFirmaFilterForm.contexte}"/>
  <c:set var="formName" value="perfilDeFirma" />
  <c:set var="__theFilterForm" value="${perfilDeFirmaFilterForm}" />
  <c:if test="${empty perfilDeFirmaFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="perfilDeFirma.perfilDeFirma"/>
  </c:if>
  <c:if test="${not empty perfilDeFirmaFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${perfilDeFirmaFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty perfilDeFirmaFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="perfilDeFirma.perfilDeFirma"/>
  </c:if>
  <c:if test="${not empty perfilDeFirmaFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${perfilDeFirmaFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.perfilDeFirma.submit();  
  }
</script>
