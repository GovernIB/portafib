<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PeticioDeFirmaUsuariEntitatFields" className="es.caib.portafib.model.fields.PeticioDeFirmaUsuariEntitatFields"/>
  
  <c:set var="__theForm" value="${peticioDeFirmaUsuariEntitatForm}"/>

  <div class="module_content">
    <div class="tab_container">
    
    <c:forEach items="${__theForm.hiddenFields}" var="hiddenFieldF" >
      <c:set  var="hiddenField" value="${hiddenFieldF.javaName}" />
      <c:if test="${gen:hasProperty(__theForm.peticioDeFirmaUsuariEntitat,hiddenField)}">
        <form:errors path="peticioDeFirmaUsuariEntitat.${hiddenField}" cssClass="errorField alert alert-error" />
        <form:hidden path="peticioDeFirmaUsuariEntitat.${hiddenField}"/>
      </c:if>
    </c:forEach>
    <table class="tdformlabel table-condensed table table-bordered table-striped marTop10  " > 
    <tbody>      

