<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${revisorDeDestinatariFilterForm.contexte}"/>
  <c:set var="formName" value="revisorDeDestinatari" />
  <c:set var="__theFilterForm" value="${revisorDeDestinatariFilterForm}" />
  <c:if test="${empty revisorDeDestinatariFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="revisorDeDestinatari.revisorDeDestinatari"/>
  </c:if>
  <c:if test="${not empty revisorDeDestinatariFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${revisorDeDestinatariFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty revisorDeDestinatariFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="revisorDeDestinatari.revisorDeDestinatari"/>
  </c:if>
  <c:if test="${not empty revisorDeDestinatariFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${revisorDeDestinatariFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.revisorDeDestinatari.submit();  
  }
</script>
