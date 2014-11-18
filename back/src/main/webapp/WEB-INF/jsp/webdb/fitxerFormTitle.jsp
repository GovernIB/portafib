<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
  <c:if test="${empty fitxerForm.entityNameCode}">
    <fmt:message var="entityname" key="fitxer.fitxer"/>
  </c:if>
  <c:if test="${not empty fitxerForm.entityNameCode}">
    <fmt:message var="entityname" key="${fitxerForm.entityNameCode}"/>
  </c:if>
  <c:if test="${not empty fitxerForm.titleCode}">
    <fmt:message key="${fitxerForm.titleCode}" >
      <fmt:param value="${fitxerForm.titleParam}" />
    </fmt:message>
  </c:if>
  <c:if test="${empty fitxerForm.titleCode}">
    <c:set var="keytitle" value="${fitxerForm.nou?'genapp.createtitle':(fitxerForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
  </c:if>
  
  <c:if test="${not empty fitxerForm.subTitleCode}">
      <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;"><fmt:message key="${fitxerForm.subTitleCode}" /></h5>
  </c:if>
</div>