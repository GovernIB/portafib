<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${grupEntitatUsuariEntitatFilterForm.contexte}"/>
  <c:set var="formName" value="grupEntitatUsuariEntitat" />
  <c:set var="__theFilterForm" value="${grupEntitatUsuariEntitatFilterForm}" />
  <c:if test="${empty grupEntitatUsuariEntitatFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="grupEntitatUsuariEntitat.grupEntitatUsuariEntitat"/>
  </c:if>
  <c:if test="${not empty grupEntitatUsuariEntitatFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${grupEntitatUsuariEntitatFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty grupEntitatUsuariEntitatFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="grupEntitatUsuariEntitat.grupEntitatUsuariEntitat"/>
  </c:if>
  <c:if test="${not empty grupEntitatUsuariEntitatFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${grupEntitatUsuariEntitatFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.grupEntitatUsuariEntitat.submit();  
  }
</script>
