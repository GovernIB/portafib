<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
  <c:if test="${empty rebreAvisForm.entityNameCode}">
    <fmt:message var="entityname" key="rebreAvis.rebreAvis"/>
  </c:if>
  <c:if test="${not empty rebreAvisForm.entityNameCode}">
    <fmt:message var="entityname" key="${rebreAvisForm.entityNameCode}"/>
  </c:if>
  <c:if test="${not empty rebreAvisForm.titleCode}">
    <fmt:message key="${rebreAvisForm.titleCode}" >
      <fmt:param value="${rebreAvisForm.titleParam}" />
    </fmt:message>
  </c:if>
  <c:if test="${empty rebreAvisForm.titleCode}">
    <c:set var="keytitle" value="${rebreAvisForm.nou?'genapp.createtitle':(rebreAvisForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
  </c:if>
  
  <c:if test="${not empty rebreAvisForm.subTitleCode}">
  <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
<c:set var="subtitleTranslated" value="${fn:startsWith(rebreAvisForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(rebreAvisForm.subTitleCode, '=')}"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${rebreAvisForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
</div>