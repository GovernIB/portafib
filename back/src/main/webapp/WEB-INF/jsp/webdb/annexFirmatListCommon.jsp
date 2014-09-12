<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${annexFirmatFilterForm.contexte}"/>
  <c:set var="formName" value="annexFirmat" />
  <c:set var="__theFilterForm" value="${annexFirmatFilterForm}" />
  <c:if test="${empty annexFirmatFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="annexFirmat.annexFirmat"/>
  </c:if>
  <c:if test="${not empty annexFirmatFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${annexFirmatFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty annexFirmatFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="annexFirmat.annexFirmat"/>
  </c:if>
  <c:if test="${not empty annexFirmatFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${annexFirmatFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.annexFirmat.submit();  
  }
</script>
