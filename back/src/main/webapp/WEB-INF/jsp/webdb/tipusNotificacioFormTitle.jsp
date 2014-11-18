<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
  <c:if test="${empty tipusNotificacioForm.entityNameCode}">
    <fmt:message var="entityname" key="tipusNotificacio.tipusNotificacio"/>
  </c:if>
  <c:if test="${not empty tipusNotificacioForm.entityNameCode}">
    <fmt:message var="entityname" key="${tipusNotificacioForm.entityNameCode}"/>
  </c:if>
  <c:if test="${not empty tipusNotificacioForm.titleCode}">
    <fmt:message key="${tipusNotificacioForm.titleCode}" >
      <fmt:param value="${tipusNotificacioForm.titleParam}" />
    </fmt:message>
  </c:if>
  <c:if test="${empty tipusNotificacioForm.titleCode}">
    <c:set var="keytitle" value="${tipusNotificacioForm.nou?'genapp.createtitle':(tipusNotificacioForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
  </c:if>
  
  <c:if test="${not empty tipusNotificacioForm.subTitleCode}">
      <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;"><fmt:message key="${tipusNotificacioForm.subTitleCode}" /></h5>
  </c:if>
</div>