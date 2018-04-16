<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${pluginFirmaWebPerUsuariEntitatFilterForm.contexte}"/>
  <c:set var="formName" value="pluginFirmaWebPerUsuariEntitat" />
  <c:set var="__theFilterForm" value="${pluginFirmaWebPerUsuariEntitatFilterForm}" />
  <c:if test="${empty pluginFirmaWebPerUsuariEntitatFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="pluginFirmaWebPerUsuariEntitat.pluginFirmaWebPerUsuariEntitat"/>
  </c:if>
  <c:if test="${not empty pluginFirmaWebPerUsuariEntitatFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${pluginFirmaWebPerUsuariEntitatFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty pluginFirmaWebPerUsuariEntitatFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="pluginFirmaWebPerUsuariEntitat.pluginFirmaWebPerUsuariEntitat"/>
  </c:if>
  <c:if test="${not empty pluginFirmaWebPerUsuariEntitatFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${pluginFirmaWebPerUsuariEntitatFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.pluginFirmaWebPerUsuariEntitat.submit();  
  }
</script>
