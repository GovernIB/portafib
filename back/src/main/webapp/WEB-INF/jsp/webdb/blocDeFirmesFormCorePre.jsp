<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="BlocDeFirmesFields" className="es.caib.portafib.model.fields.BlocDeFirmesFields"/>
  
  <c:set var="__theForm" value="${blocDeFirmesForm}"/>

  <div class="module_content">
    <div class="tab_container">
    
    <c:if test="${not __theForm.view}">    <c:forEach items="${__theForm.hiddenFields}" var="hiddenFieldF" >
      <c:set  var="hiddenField" value="${hiddenFieldF.javaName}" />
      <c:if test="${gen:hasProperty(__theForm.blocDeFirmes,hiddenField)}">
        <form:errors path="blocDeFirmes.${hiddenField}" cssClass="errorField alert alert-error" />
        <form:hidden path="blocDeFirmes.${hiddenField}"/>
      </c:if>
    </c:forEach>
    </c:if>    <table class="tdformlabel table-condensed table table-bordered table-striped marTop10  " > 
    <tbody>      

