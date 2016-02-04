<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
 <c:choose>
  <c:when test="${fn:startsWith(entitatForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(entitatForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty entitatForm.titleCode}">
    <fmt:message key="${entitatForm.titleCode}" >
      <fmt:param value="${entitatForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty entitatForm.entityNameCode}">
      <fmt:message var="entityname" key="entitat.entitat"/>
    </c:if>
    <c:if test="${not empty entitatForm.entityNameCode}">
      <fmt:message var="entityname" key="${entitatForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${entitatForm.nou?'genapp.createtitle':(entitatForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose>
  
  <c:if test="${not empty entitatForm.subTitleCode}">
  <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
<c:set var="subtitleTranslated" value="${fn:startsWith(entitatForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(entitatForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${entitatForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
</div>