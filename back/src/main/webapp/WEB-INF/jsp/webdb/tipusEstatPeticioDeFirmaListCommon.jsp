<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${tipusEstatPeticioDeFirmaFilterForm.contexte}"/>
  <c:set var="formName" value="tipusEstatPeticioDeFirma" />
  <c:set var="__theFilterForm" value="${tipusEstatPeticioDeFirmaFilterForm}" />
  <c:if test="${empty tipusEstatPeticioDeFirmaFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="tipusEstatPeticioDeFirma.tipusEstatPeticioDeFirma"/>
  </c:if>
  <c:if test="${not empty tipusEstatPeticioDeFirmaFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${tipusEstatPeticioDeFirmaFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty tipusEstatPeticioDeFirmaFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="tipusEstatPeticioDeFirma.tipusEstatPeticioDeFirma"/>
  </c:if>
  <c:if test="${not empty tipusEstatPeticioDeFirmaFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${tipusEstatPeticioDeFirmaFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.tipusEstatPeticioDeFirma.submit();  
  }
</script>
