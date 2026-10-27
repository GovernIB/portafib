<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="UsuariEntitatFields" className="es.caib.portafib.model.fields.UsuariEntitatFields"/>
  
  <c:set var="__theForm" value="${usuariEntitatForm}"/>

  <div class="module_content">
    <div class="tab_container">
    
    <c:if test="${not __theForm.view}">    <c:forEach items="${__theForm.hiddenFields}" var="hiddenFieldF" >
      <c:set  var="hiddenField" value="${hiddenFieldF.javaName}" />
      <c:if test="${gen:hasProperty(__theForm.usuariEntitat,hiddenField)}">
        <form:errors path="usuariEntitat.${hiddenField}" cssClass="errorField alert alert-danger" />
        <form:hidden path="usuariEntitat.${hiddenField}"/>
      </c:if>
    </c:forEach>
    </c:if>

<small class="text-muted" style="font-size:0.8em"><span class="text-danger" aria-hidden="true">*</span> &nbsp;<fmt:message key="info.camp.obligatori"/></small>
    <form:errors cssClass="errorField alert alert-danger" delimiter="&lt;p/&gt;" />
    <table id="usuariEntitat_tableid" role="presentation" class="tdformlabel table-sm table table-bordered table-striped marTop10 table-genapp table-genapp-form">
    <tbody>      

