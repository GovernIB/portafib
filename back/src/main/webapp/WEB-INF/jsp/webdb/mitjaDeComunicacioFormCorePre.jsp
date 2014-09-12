<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="MitjaDeComunicacioFields" className="es.caib.portafib.model.fields.MitjaDeComunicacioFields"/>
  
  <c:set var="__theForm" value="${mitjaDeComunicacioForm}"/>

  <div class="module_content">
    <div class="tab_container">
    
    <c:forEach items="${__theForm.hiddenFields}" var="hiddenFieldF" >
      <c:set  var="hiddenField" value="${hiddenFieldF.javaName}" />
      <c:if test="${gen:hasProperty(__theForm.mitjaDeComunicacio,hiddenField)}">
        <form:errors path="mitjaDeComunicacio.${hiddenField}" cssClass="errorField alert alert-error" />
        <form:hidden path="mitjaDeComunicacio.${hiddenField}"/>
      </c:if>
    </c:forEach>
    <table class="tdformlabel table-condensed table table-bordered table-striped marTop10  " > 
    <tbody>      

