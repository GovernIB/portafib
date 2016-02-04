<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
 <c:choose>
  <c:when test="${fn:startsWith(annexForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(annexForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty annexForm.titleCode}">
    <fmt:message key="${annexForm.titleCode}" >
      <fmt:param value="${annexForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty annexForm.entityNameCode}">
      <fmt:message var="entityname" key="annex.annex"/>
    </c:if>
    <c:if test="${not empty annexForm.entityNameCode}">
      <fmt:message var="entityname" key="${annexForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${annexForm.nou?'genapp.createtitle':(annexForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose>
  
  <c:if test="${not empty annexForm.subTitleCode}">
  <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
<c:set var="subtitleTranslated" value="${fn:startsWith(annexForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(annexForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${annexForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
</div>