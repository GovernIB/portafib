<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
 <c:choose>
  <c:when test="${fn:startsWith(firmaForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(firmaForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty firmaForm.titleCode}">
    <fmt:message key="${firmaForm.titleCode}" >
      <fmt:param value="${firmaForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty firmaForm.entityNameCode}">
      <fmt:message var="entityname" key="firma.firma"/>
    </c:if>
    <c:if test="${not empty firmaForm.entityNameCode}">
      <fmt:message var="entityname" key="${firmaForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${firmaForm.nou?'genapp.createtitle':(firmaForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose>
  
  <c:if test="${not empty firmaForm.subTitleCode}">
  <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
<c:set var="subtitleTranslated" value="${fn:startsWith(firmaForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(firmaForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${firmaForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
</div>