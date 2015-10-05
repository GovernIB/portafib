<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
  <c:if test="${empty roleUsuariEntitatForm.entityNameCode}">
    <fmt:message var="entityname" key="roleUsuariEntitat.roleUsuariEntitat"/>
  </c:if>
  <c:if test="${not empty roleUsuariEntitatForm.entityNameCode}">
    <fmt:message var="entityname" key="${roleUsuariEntitatForm.entityNameCode}"/>
  </c:if>
  <c:if test="${not empty roleUsuariEntitatForm.titleCode}">
    <fmt:message key="${roleUsuariEntitatForm.titleCode}" >
      <fmt:param value="${roleUsuariEntitatForm.titleParam}" />
    </fmt:message>
  </c:if>
  <c:if test="${empty roleUsuariEntitatForm.titleCode}">
    <c:set var="keytitle" value="${roleUsuariEntitatForm.nou?'genapp.createtitle':(roleUsuariEntitatForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
  </c:if>
  
  <c:if test="${not empty roleUsuariEntitatForm.subTitleCode}">
  <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
<c:set var="subtitleTranslated" value="${fn:startsWith(roleUsuariEntitatForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(roleUsuariEntitatForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${roleUsuariEntitatForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
</div>