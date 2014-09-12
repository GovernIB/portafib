<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bootom:10px">
  <c:if test="${empty annexForm.entityNameCode}">
    <fmt:message var="entityname" key="annex.annex"/>
  </c:if>
  <c:if test="${not empty annexForm.entityNameCode}">
    <fmt:message var="entityname" key="${annexForm.entityNameCode}"/>
  </c:if>
  <c:if test="${not empty annexForm.titleCode}">
    <fmt:message key="${annexForm.titleCode}" >
      <fmt:param value="${annexForm.titleParam}" />
    </fmt:message>
  </c:if>
  <c:if test="${empty annexForm.titleCode}">
    <c:set var="keytitle" value="${annexForm.nou?'genapp.createtitle':(annexForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
  </c:if>
  
  <c:if test="${not empty annexForm.subTitleCode}">
      <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;"><fmt:message key="${annexForm.subTitleCode}" /></h5>
  </c:if>
</div>