<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${notificacioWSFilterForm.contexte}"/>
  <c:set var="formName" value="notificacioWS" />
  <c:set var="__theFilterForm" value="${notificacioWSFilterForm}" />
  <c:if test="${empty notificacioWSFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="notificacioWS.notificacioWS"/>
  </c:if>
  <c:if test="${not empty notificacioWSFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${notificacioWSFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty notificacioWSFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="notificacioWS.notificacioWS"/>
  </c:if>
  <c:if test="${not empty notificacioWSFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${notificacioWSFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.notificacioWS.submit();  
  }
</script>
