<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
 <c:choose>
  <c:when test="${fn:startsWith(custodiaInfoForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(custodiaInfoForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty custodiaInfoForm.titleCode}">
    <fmt:message key="${custodiaInfoForm.titleCode}" >
      <fmt:param value="${custodiaInfoForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty custodiaInfoForm.entityNameCode}">
      <fmt:message var="entityname" key="custodiaInfo.custodiaInfo"/>
    </c:if>
    <c:if test="${not empty custodiaInfoForm.entityNameCode}">
      <fmt:message var="entityname" key="${custodiaInfoForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${custodiaInfoForm.nou?'genapp.createtitle':(custodiaInfoForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose>
  
  <c:if test="${not empty custodiaInfoForm.subTitleCode}">
  <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
<c:set var="subtitleTranslated" value="${fn:startsWith(custodiaInfoForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(custodiaInfoForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${custodiaInfoForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
</div>