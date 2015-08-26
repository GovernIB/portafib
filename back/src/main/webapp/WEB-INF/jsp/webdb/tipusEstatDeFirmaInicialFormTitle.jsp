<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
  <c:if test="${empty tipusEstatDeFirmaInicialForm.entityNameCode}">
    <fmt:message var="entityname" key="tipusEstatDeFirmaInicial.tipusEstatDeFirmaInicial"/>
  </c:if>
  <c:if test="${not empty tipusEstatDeFirmaInicialForm.entityNameCode}">
    <fmt:message var="entityname" key="${tipusEstatDeFirmaInicialForm.entityNameCode}"/>
  </c:if>
  <c:if test="${not empty tipusEstatDeFirmaInicialForm.titleCode}">
    <fmt:message key="${tipusEstatDeFirmaInicialForm.titleCode}" >
      <fmt:param value="${tipusEstatDeFirmaInicialForm.titleParam}" />
    </fmt:message>
  </c:if>
  <c:if test="${empty tipusEstatDeFirmaInicialForm.titleCode}">
    <c:set var="keytitle" value="${tipusEstatDeFirmaInicialForm.nou?'genapp.createtitle':(tipusEstatDeFirmaInicialForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
  </c:if>
  
  <c:if test="${not empty tipusEstatDeFirmaInicialForm.subTitleCode}">
  <br/><h5 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;">
<c:set var="subtitleTranslated" value="${fn:startsWith(tipusEstatDeFirmaInicialForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(tipusEstatDeFirmaInicialForm.subTitleCode, '=')}"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${tipusEstatDeFirmaInicialForm.subTitleCode}" />
</c:if>
</h5>
  </c:if>
</div>