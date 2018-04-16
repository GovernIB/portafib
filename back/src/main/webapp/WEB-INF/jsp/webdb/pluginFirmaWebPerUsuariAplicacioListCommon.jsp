<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${pluginFirmaWebPerUsuariAplicacioFilterForm.contexte}"/>
  <c:set var="formName" value="pluginFirmaWebPerUsuariAplicacio" />
  <c:set var="__theFilterForm" value="${pluginFirmaWebPerUsuariAplicacioFilterForm}" />
  <c:if test="${empty pluginFirmaWebPerUsuariAplicacioFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="pluginFirmaWebPerUsuariAplicacio.pluginFirmaWebPerUsuariAplicacio"/>
  </c:if>
  <c:if test="${not empty pluginFirmaWebPerUsuariAplicacioFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${pluginFirmaWebPerUsuariAplicacioFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty pluginFirmaWebPerUsuariAplicacioFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="pluginFirmaWebPerUsuariAplicacio.pluginFirmaWebPerUsuariAplicacio"/>
  </c:if>
  <c:if test="${not empty pluginFirmaWebPerUsuariAplicacioFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${pluginFirmaWebPerUsuariAplicacioFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.pluginFirmaWebPerUsuariAplicacio.submit();  
  }
</script>
