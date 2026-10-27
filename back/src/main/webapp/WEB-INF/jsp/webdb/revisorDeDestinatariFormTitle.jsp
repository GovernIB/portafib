<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<span class="llistaformularititol">
 <c:choose>
  <c:when test="${fn:startsWith(revisorDeDestinatariForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(revisorDeDestinatariForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty revisorDeDestinatariForm.titleCode}">
    <fmt:message key="${revisorDeDestinatariForm.titleCode}" >
      <fmt:param value="${revisorDeDestinatariForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty revisorDeDestinatariForm.entityNameCode}">
      <fmt:message var="entityname" key="revisorDeDestinatari.revisorDeDestinatari"/>
    </c:if>
    <c:if test="${not empty revisorDeDestinatariForm.entityNameCode}">
      <fmt:message var="entityname" key="${revisorDeDestinatariForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${revisorDeDestinatariForm.nou?'genapp.createtitle':(revisorDeDestinatariForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></span>
  <c:if test="${not empty revisorDeDestinatariForm.subTitleCode}">
<span class="llistaformularisubtitol">
<c:set var="subtitleTranslated" value="${fn:startsWith(revisorDeDestinatariForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(revisorDeDestinatariForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${revisorDeDestinatariForm.subTitleCode}" />
</c:if>
</span>
  </c:if>
</div>