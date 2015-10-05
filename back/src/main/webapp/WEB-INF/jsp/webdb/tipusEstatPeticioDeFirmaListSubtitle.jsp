<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:if test="${not empty __theFilterForm.subTitleCode}">
<h5 style="line-height: 10px; margin-top: -10px; margin-bottom: 10px;">
<c:set var="subtitleTranslated" value="${fn:startsWith(__theFilterForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(__theFilterForm.subTitleCode, '=')}" escapeXml="false" />
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${__theFilterForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
