<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${usuariPersonaFilterForm.contexte}"/>
  <c:set var="formName" value="usuariPersona" />
  <c:set var="__theFilterForm" value="${usuariPersonaFilterForm}" />
  <c:if test="${empty usuariPersonaFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="usuariPersona.usuariPersona"/>
  </c:if>
  <c:if test="${not empty usuariPersonaFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${usuariPersonaFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty usuariPersonaFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="usuariPersona.usuariPersona"/>
  </c:if>
  <c:if test="${not empty usuariPersonaFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${usuariPersonaFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.usuariPersona.submit();  
  }
</script>
