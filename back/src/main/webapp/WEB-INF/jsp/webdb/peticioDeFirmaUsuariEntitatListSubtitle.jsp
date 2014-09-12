<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:if test="${not empty peticioDeFirmaUsuariEntitatFilterForm.subTitleCode}">
<h5 style="line-height: 10px; margin-top: -10px; margin-bottom: 10px;">
<c:set var="subtitleTranslated" value="${fn:startsWith(peticioDeFirmaUsuariEntitatFilterForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(peticioDeFirmaUsuariEntitatFilterForm.subTitleCode, '=')}"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${peticioDeFirmaUsuariEntitatFilterForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
