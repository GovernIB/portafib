<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="ColaboracioDelegacioFields" className="es.caib.portafib.model.fields.ColaboracioDelegacioFields"/>
  
  <c:set var="__theForm" value="${colaboracioDelegacioForm}"/>

  <div class="module_content">
    <div class="tab_container">
    
    <c:if test="${not __theForm.view}">    <c:forEach items="${__theForm.hiddenFields}" var="hiddenFieldF" >
      <c:set  var="hiddenField" value="${hiddenFieldF.javaName}" />
      <c:if test="${gen:hasProperty(__theForm.colaboracioDelegacio,hiddenField)}">
        <form:errors path="colaboracioDelegacio.${hiddenField}" cssClass="errorField alert alert-error" />
        <form:hidden path="colaboracioDelegacio.${hiddenField}"/>
      </c:if>
    </c:forEach>
    </c:if>

    <form:errors cssClass="errorField alert alert-error" delimiter="&lt;p/&gt;" />
    <table class="tdformlabel table-condensed table table-bordered table-striped marTop10  " > 
    <tbody>      

