<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${tipusFirmaFilterForm.contexte}"/>
  <c:set var="formName" value="tipusFirma" />
  <c:set var="__theFilterForm" value="${tipusFirmaFilterForm}" />
  <c:if test="${empty tipusFirmaFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="tipusFirma.tipusFirma"/>
  </c:if>
  <c:if test="${not empty tipusFirmaFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${tipusFirmaFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty tipusFirmaFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="tipusFirma.tipusFirma"/>
  </c:if>
  <c:if test="${not empty tipusFirmaFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${tipusFirmaFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.tipusFirma.submit();  
  }
</script>
