<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
  <c:if test="${empty codiBarresForm.entityNameCode}">
    <fmt:message var="entityname" key="codiBarres.codiBarres"/>
  </c:if>
  <c:if test="${not empty codiBarresForm.entityNameCode}">
    <fmt:message var="entityname" key="${codiBarresForm.entityNameCode}"/>
  </c:if>
  <c:if test="${not empty codiBarresForm.titleCode}">
    <fmt:message key="${codiBarresForm.titleCode}" >
      <fmt:param value="${codiBarresForm.titleParam}" />
    </fmt:message>
  </c:if>
  <c:if test="${empty codiBarresForm.titleCode}">
    <c:set var="keytitle" value="${codiBarresForm.nou?'genapp.createtitle':(codiBarresForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
  </c:if>
  
  <c:if test="${not empty codiBarresForm.subTitleCode}">
      <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;"><fmt:message key="${codiBarresForm.subTitleCode}" /></h5>
  </c:if>
</div>