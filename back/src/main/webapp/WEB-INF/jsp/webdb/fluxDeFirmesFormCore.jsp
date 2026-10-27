<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="FluxDeFirmesFields" className="es.caib.portafib.model.fields.FluxDeFirmesFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,FluxDeFirmesFields.NOM)}">
        <tr id="fluxDeFirmes_nom_rowid">
          <td id="fluxDeFirmes_nom_columnlabelid">
            <label for="fluxDeFirmes.nom">
              <fmt:message key="${(empty __theForm.labels[FluxDeFirmesFields.NOM])?'fluxDeFirmes.nom':__theForm.labels[FluxDeFirmesFields.NOM]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[FluxDeFirmesFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FluxDeFirmesFields.NOM]}" ></i>
              </c:if>
            </td>
          <td id="fluxDeFirmes_nom_columnvalueid">
            <form:errors path="fluxDeFirmes.nom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FluxDeFirmesFields.NOM)? 'readonly' : ''}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,FluxDeFirmesFields.NOM)? ' uneditable-input' : ''}"  style="" maxlength="255" path="fluxDeFirmes.nom"  required="required"  aria-required="true"   />

           </td>
        </tr>
        </c:if>
        
