<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<span class="llistaformularititol">
 <c:choose>
  <c:when test="${fn:startsWith(traduccioForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(traduccioForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty traduccioForm.titleCode}">
    <fmt:message key="${traduccioForm.titleCode}" >
      <fmt:param value="${traduccioForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty traduccioForm.entityNameCode}">
      <fmt:message var="entityname" key="traduccio.traduccio"/>
    </c:if>
    <c:if test="${not empty traduccioForm.entityNameCode}">
      <fmt:message var="entityname" key="${traduccioForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${traduccioForm.nou?'genapp.createtitle':(traduccioForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></span>
  <c:if test="${not empty traduccioForm.subTitleCode}">
<span class="llistaformularisubtitol">
<c:set var="subtitleTranslated" value="${fn:startsWith(traduccioForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(traduccioForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${traduccioForm.subTitleCode}" />
</c:if>
</span>
  </c:if>
</div>