<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
  <c:if test="${empty colaboracioDelegacioForm.entityNameCode}">
    <fmt:message var="entityname" key="colaboracioDelegacio.colaboracioDelegacio"/>
  </c:if>
  <c:if test="${not empty colaboracioDelegacioForm.entityNameCode}">
    <fmt:message var="entityname" key="${colaboracioDelegacioForm.entityNameCode}"/>
  </c:if>
  <c:if test="${not empty colaboracioDelegacioForm.titleCode}">
    <fmt:message key="${colaboracioDelegacioForm.titleCode}" >
      <fmt:param value="${colaboracioDelegacioForm.titleParam}" />
    </fmt:message>
  </c:if>
  <c:if test="${empty colaboracioDelegacioForm.titleCode}">
    <c:set var="keytitle" value="${colaboracioDelegacioForm.nou?'genapp.createtitle':(colaboracioDelegacioForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
  </c:if>
  
  <c:if test="${not empty colaboracioDelegacioForm.subTitleCode}">
      <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;"><fmt:message key="${colaboracioDelegacioForm.subTitleCode}" /></h5>
  </c:if>
</div>