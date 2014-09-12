<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="TipusNotificacioFields" className="es.caib.portafib.model.fields.TipusNotificacioFields"/>
  
  <c:set var="__theForm" value="${tipusNotificacioForm}"/>

  <div class="module_content">
    <div class="tab_container">
    
    <c:forEach items="${__theForm.hiddenFields}" var="hiddenFieldF" >
      <c:set  var="hiddenField" value="${hiddenFieldF.javaName}" />
      <c:if test="${gen:hasProperty(__theForm.tipusNotificacio,hiddenField)}">
        <form:errors path="tipusNotificacio.${hiddenField}" cssClass="errorField alert alert-error" />
        <form:hidden path="tipusNotificacio.${hiddenField}"/>
      </c:if>
    </c:forEach>
    <table class="tdformlabel table-condensed table table-bordered table-striped marTop10  " > 
    <tbody>      

