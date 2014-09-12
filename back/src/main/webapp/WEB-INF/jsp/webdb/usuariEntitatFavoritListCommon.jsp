<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${usuariEntitatFavoritFilterForm.contexte}"/>
  <c:set var="formName" value="usuariEntitatFavorit" />
  <c:set var="__theFilterForm" value="${usuariEntitatFavoritFilterForm}" />
  <c:if test="${empty usuariEntitatFavoritFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="usuariEntitatFavorit.usuariEntitatFavorit"/>
  </c:if>
  <c:if test="${not empty usuariEntitatFavoritFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${usuariEntitatFavoritFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty usuariEntitatFavoritFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="usuariEntitatFavorit.usuariEntitatFavorit"/>
  </c:if>
  <c:if test="${not empty usuariEntitatFavoritFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${usuariEntitatFavoritFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.usuariEntitatFavorit.submit();  
  }
</script>
