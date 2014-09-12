<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bootom:10px">
  <c:if test="${empty grupEntitatForm.entityNameCode}">
    <fmt:message var="entityname" key="grupEntitat.grupEntitat"/>
  </c:if>
  <c:if test="${not empty grupEntitatForm.entityNameCode}">
    <fmt:message var="entityname" key="${grupEntitatForm.entityNameCode}"/>
  </c:if>
  <c:if test="${not empty grupEntitatForm.titleCode}">
    <fmt:message key="${grupEntitatForm.titleCode}" >
      <fmt:param value="${grupEntitatForm.titleParam}" />
    </fmt:message>
  </c:if>
  <c:if test="${empty grupEntitatForm.titleCode}">
    <c:set var="keytitle" value="${grupEntitatForm.nou?'genapp.createtitle':(grupEntitatForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
  </c:if>
  
  <c:if test="${not empty grupEntitatForm.subTitleCode}">
      <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;"><fmt:message key="${grupEntitatForm.subTitleCode}" /></h5>
  </c:if>
</div>