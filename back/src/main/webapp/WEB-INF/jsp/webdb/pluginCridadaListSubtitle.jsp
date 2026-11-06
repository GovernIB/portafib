<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:if test="${not empty __theFilterForm.subTitleCode}">
<p style="margin-top:-5px; margin-bottom:15px; font-style:italic; font-size: 1rem;font-weight: 500;line-height: 1.2;">
<c:set var="subtitleTranslated" value="${fn:startsWith(__theFilterForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(__theFilterForm.subTitleCode, '=')}" escapeXml="false" />
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${__theFilterForm.subTitleCode}" />
</c:if>
</p>
  </c:if>
