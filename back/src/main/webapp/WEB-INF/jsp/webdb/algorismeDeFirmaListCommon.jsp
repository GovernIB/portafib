<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${algorismeDeFirmaFilterForm.contexte}"/>
  <c:set var="formName" value="algorismeDeFirma" />
  <c:set var="__theFilterForm" value="${algorismeDeFirmaFilterForm}" />
  <c:if test="${empty algorismeDeFirmaFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="algorismeDeFirma.algorismeDeFirma"/>
  </c:if>
  <c:if test="${not empty algorismeDeFirmaFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${algorismeDeFirmaFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty algorismeDeFirmaFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="algorismeDeFirma.algorismeDeFirma"/>
  </c:if>
  <c:if test="${not empty algorismeDeFirmaFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${algorismeDeFirmaFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.algorismeDeFirma.submit();  
  }
</script>
