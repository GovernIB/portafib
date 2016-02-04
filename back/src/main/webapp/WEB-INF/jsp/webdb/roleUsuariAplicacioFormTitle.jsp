<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
 <c:choose>
  <c:when test="${fn:startsWith(roleUsuariAplicacioForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(roleUsuariAplicacioForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty roleUsuariAplicacioForm.titleCode}">
    <fmt:message key="${roleUsuariAplicacioForm.titleCode}" >
      <fmt:param value="${roleUsuariAplicacioForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty roleUsuariAplicacioForm.entityNameCode}">
      <fmt:message var="entityname" key="roleUsuariAplicacio.roleUsuariAplicacio"/>
    </c:if>
    <c:if test="${not empty roleUsuariAplicacioForm.entityNameCode}">
      <fmt:message var="entityname" key="${roleUsuariAplicacioForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${roleUsuariAplicacioForm.nou?'genapp.createtitle':(roleUsuariAplicacioForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose>
  
  <c:if test="${not empty roleUsuariAplicacioForm.subTitleCode}">
  <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
<c:set var="subtitleTranslated" value="${fn:startsWith(roleUsuariAplicacioForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(roleUsuariAplicacioForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${roleUsuariAplicacioForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
</div>