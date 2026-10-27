<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<span class="llistaformularititol">
 <c:choose>
  <c:when test="${fn:startsWith(annexFirmatForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(annexFirmatForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty annexFirmatForm.titleCode}">
    <fmt:message key="${annexFirmatForm.titleCode}" >
      <fmt:param value="${annexFirmatForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty annexFirmatForm.entityNameCode}">
      <fmt:message var="entityname" key="annexFirmat.annexFirmat"/>
    </c:if>
    <c:if test="${not empty annexFirmatForm.entityNameCode}">
      <fmt:message var="entityname" key="${annexFirmatForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${annexFirmatForm.nou?'genapp.createtitle':(annexFirmatForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></span>
  <c:if test="${not empty annexFirmatForm.subTitleCode}">
<span class="llistaformularisubtitol">
<c:set var="subtitleTranslated" value="${fn:startsWith(annexFirmatForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(annexFirmatForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${annexFirmatForm.subTitleCode}" />
</c:if>
</span>
  </c:if>
</div>