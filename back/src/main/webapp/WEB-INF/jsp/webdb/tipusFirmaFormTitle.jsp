<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
 <c:choose>
  <c:when test="${fn:startsWith(tipusFirmaForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(tipusFirmaForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty tipusFirmaForm.titleCode}">
    <fmt:message key="${tipusFirmaForm.titleCode}" >
      <fmt:param value="${tipusFirmaForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty tipusFirmaForm.entityNameCode}">
      <fmt:message var="entityname" key="tipusFirma.tipusFirma"/>
    </c:if>
    <c:if test="${not empty tipusFirmaForm.entityNameCode}">
      <fmt:message var="entityname" key="${tipusFirmaForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${tipusFirmaForm.nou?'genapp.createtitle':(tipusFirmaForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose>
  
  <c:if test="${not empty tipusFirmaForm.subTitleCode}">
  <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
<c:set var="subtitleTranslated" value="${fn:startsWith(tipusFirmaForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(tipusFirmaForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${tipusFirmaForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
</div>