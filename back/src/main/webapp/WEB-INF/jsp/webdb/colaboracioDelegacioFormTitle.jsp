<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(colaboracioDelegacioForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(colaboracioDelegacioForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty colaboracioDelegacioForm.titleCode}">
    <fmt:message key="${colaboracioDelegacioForm.titleCode}" >
      <fmt:param value="${colaboracioDelegacioForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty colaboracioDelegacioForm.entityNameCode}">
      <fmt:message var="entityname" key="colaboracioDelegacio.colaboracioDelegacio"/>
    </c:if>
    <c:if test="${not empty colaboracioDelegacioForm.entityNameCode}">
      <fmt:message var="entityname" key="${colaboracioDelegacioForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${colaboracioDelegacioForm.nou?'genapp.createtitle':(colaboracioDelegacioForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty colaboracioDelegacioForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(colaboracioDelegacioForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(colaboracioDelegacioForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${colaboracioDelegacioForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>