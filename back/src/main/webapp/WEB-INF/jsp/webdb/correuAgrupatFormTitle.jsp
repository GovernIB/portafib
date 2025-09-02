<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(correuAgrupatForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(correuAgrupatForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty correuAgrupatForm.titleCode}">
    <fmt:message key="${correuAgrupatForm.titleCode}" >
      <fmt:param value="${correuAgrupatForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty correuAgrupatForm.entityNameCode}">
      <fmt:message var="entityname" key="correuAgrupat.correuAgrupat"/>
    </c:if>
    <c:if test="${not empty correuAgrupatForm.entityNameCode}">
      <fmt:message var="entityname" key="${correuAgrupatForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${correuAgrupatForm.nou?'genapp.createtitle':(correuAgrupatForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty correuAgrupatForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(correuAgrupatForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(correuAgrupatForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${correuAgrupatForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>