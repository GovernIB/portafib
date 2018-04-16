<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
 <c:choose>
  <c:when test="${fn:startsWith(usuariEntitatRevisorForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(usuariEntitatRevisorForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty usuariEntitatRevisorForm.titleCode}">
    <fmt:message key="${usuariEntitatRevisorForm.titleCode}" >
      <fmt:param value="${usuariEntitatRevisorForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty usuariEntitatRevisorForm.entityNameCode}">
      <fmt:message var="entityname" key="usuariEntitatRevisor.usuariEntitatRevisor"/>
    </c:if>
    <c:if test="${not empty usuariEntitatRevisorForm.entityNameCode}">
      <fmt:message var="entityname" key="${usuariEntitatRevisorForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${usuariEntitatRevisorForm.nou?'genapp.createtitle':(usuariEntitatRevisorForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose>
  
  <c:if test="${not empty usuariEntitatRevisorForm.subTitleCode}">
  <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
<c:set var="subtitleTranslated" value="${fn:startsWith(usuariEntitatRevisorForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(usuariEntitatRevisorForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${usuariEntitatRevisorForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
</div>