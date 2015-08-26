<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
  <c:if test="${empty roleForm.entityNameCode}">
    <fmt:message var="entityname" key="role.role"/>
  </c:if>
  <c:if test="${not empty roleForm.entityNameCode}">
    <fmt:message var="entityname" key="${roleForm.entityNameCode}"/>
  </c:if>
  <c:if test="${not empty roleForm.titleCode}">
    <fmt:message key="${roleForm.titleCode}" >
      <fmt:param value="${roleForm.titleParam}" />
    </fmt:message>
  </c:if>
  <c:if test="${empty roleForm.titleCode}">
    <c:set var="keytitle" value="${roleForm.nou?'genapp.createtitle':(roleForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
  </c:if>
  
  <c:if test="${not empty roleForm.subTitleCode}">
  <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
<c:set var="subtitleTranslated" value="${fn:startsWith(roleForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(roleForm.subTitleCode, '=')}"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${roleForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
</div>