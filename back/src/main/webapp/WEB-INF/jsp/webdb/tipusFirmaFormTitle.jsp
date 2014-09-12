<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bootom:10px">
  <c:if test="${empty tipusFirmaForm.entityNameCode}">
    <fmt:message var="entityname" key="tipusFirma.tipusFirma"/>
  </c:if>
  <c:if test="${not empty tipusFirmaForm.entityNameCode}">
    <fmt:message var="entityname" key="${tipusFirmaForm.entityNameCode}"/>
  </c:if>
  <c:if test="${not empty tipusFirmaForm.titleCode}">
    <fmt:message key="${tipusFirmaForm.titleCode}" >
      <fmt:param value="${tipusFirmaForm.titleParam}" />
    </fmt:message>
  </c:if>
  <c:if test="${empty tipusFirmaForm.titleCode}">
    <c:set var="keytitle" value="${tipusFirmaForm.nou?'genapp.createtitle':(tipusFirmaForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
  </c:if>
  
  <c:if test="${not empty tipusFirmaForm.subTitleCode}">
      <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;"><fmt:message key="${tipusFirmaForm.subTitleCode}" /></h5>
  </c:if>
</div>