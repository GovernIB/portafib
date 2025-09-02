<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="CorreuAgrupatFields" className="es.caib.portafib.model.fields.CorreuAgrupatFields"/>
  
  <c:set var="__theForm" value="${correuAgrupatForm}"/>

  <div class="module_content">
    <div class="tab_container">
    
    <c:if test="${not __theForm.view}">    <c:forEach items="${__theForm.hiddenFields}" var="hiddenFieldF" >
      <c:set  var="hiddenField" value="${hiddenFieldF.javaName}" />
      <c:if test="${gen:hasProperty(__theForm.correuAgrupat,hiddenField)}">
        <form:errors path="correuAgrupat.${hiddenField}" cssClass="errorField alert alert-danger" />
        <form:hidden path="correuAgrupat.${hiddenField}"/>
      </c:if>
    </c:forEach>
    </c:if>

    <form:errors cssClass="errorField alert alert-danger" delimiter="&lt;p/&gt;" />
    <table id="correuAgrupat_tableid" class="tdformlabel table-sm table table-bordered table-striped marTop10 table-genapp table-genapp-form" > 
    <tbody>      

