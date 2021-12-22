<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(rebreAvisForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(rebreAvisForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty rebreAvisForm.titleCode}">
    <fmt:message key="${rebreAvisForm.titleCode}" >
      <fmt:param value="${rebreAvisForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty rebreAvisForm.entityNameCode}">
      <fmt:message var="entityname" key="rebreAvis.rebreAvis"/>
    </c:if>
    <c:if test="${not empty rebreAvisForm.entityNameCode}">
      <fmt:message var="entityname" key="${rebreAvisForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${rebreAvisForm.nou?'genapp.createtitle':(rebreAvisForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty rebreAvisForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(rebreAvisForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(rebreAvisForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${rebreAvisForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>