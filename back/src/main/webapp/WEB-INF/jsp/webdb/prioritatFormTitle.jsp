<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
 <c:choose>
  <c:when test="${fn:startsWith(prioritatForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(prioritatForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty prioritatForm.titleCode}">
    <fmt:message key="${prioritatForm.titleCode}" >
      <fmt:param value="${prioritatForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty prioritatForm.entityNameCode}">
      <fmt:message var="entityname" key="prioritat.prioritat"/>
    </c:if>
    <c:if test="${not empty prioritatForm.entityNameCode}">
      <fmt:message var="entityname" key="${prioritatForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${prioritatForm.nou?'genapp.createtitle':(prioritatForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose>
  
  <c:if test="${not empty prioritatForm.subTitleCode}">
  <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
<c:set var="subtitleTranslated" value="${fn:startsWith(prioritatForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(prioritatForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${prioritatForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
</div>