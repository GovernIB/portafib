<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<span class="llistaformularititol">
 <c:choose>
  <c:when test="${fn:startsWith(roleUsuariEntitatForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(roleUsuariEntitatForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty roleUsuariEntitatForm.titleCode}">
    <fmt:message key="${roleUsuariEntitatForm.titleCode}" >
      <fmt:param value="${roleUsuariEntitatForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty roleUsuariEntitatForm.entityNameCode}">
      <fmt:message var="entityname" key="roleUsuariEntitat.roleUsuariEntitat"/>
    </c:if>
    <c:if test="${not empty roleUsuariEntitatForm.entityNameCode}">
      <fmt:message var="entityname" key="${roleUsuariEntitatForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${roleUsuariEntitatForm.nou?'genapp.createtitle':(roleUsuariEntitatForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></span>
  <c:if test="${not empty roleUsuariEntitatForm.subTitleCode}">
<span class="llistaformularisubtitol">
<c:set var="subtitleTranslated" value="${fn:startsWith(roleUsuariEntitatForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(roleUsuariEntitatForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${roleUsuariEntitatForm.subTitleCode}" />
</c:if>
</span>
  </c:if>
</div>