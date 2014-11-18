<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
  <c:if test="${empty grupEntitatUsuariEntitatForm.entityNameCode}">
    <fmt:message var="entityname" key="grupEntitatUsuariEntitat.grupEntitatUsuariEntitat"/>
  </c:if>
  <c:if test="${not empty grupEntitatUsuariEntitatForm.entityNameCode}">
    <fmt:message var="entityname" key="${grupEntitatUsuariEntitatForm.entityNameCode}"/>
  </c:if>
  <c:if test="${not empty grupEntitatUsuariEntitatForm.titleCode}">
    <fmt:message key="${grupEntitatUsuariEntitatForm.titleCode}" >
      <fmt:param value="${grupEntitatUsuariEntitatForm.titleParam}" />
    </fmt:message>
  </c:if>
  <c:if test="${empty grupEntitatUsuariEntitatForm.titleCode}">
    <c:set var="keytitle" value="${grupEntitatUsuariEntitatForm.nou?'genapp.createtitle':(grupEntitatUsuariEntitatForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
  </c:if>
  
  <c:if test="${not empty grupEntitatUsuariEntitatForm.subTitleCode}">
      <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;"><fmt:message key="${grupEntitatUsuariEntitatForm.subTitleCode}" /></h5>
  </c:if>
</div>