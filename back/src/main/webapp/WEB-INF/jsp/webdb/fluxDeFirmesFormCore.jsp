<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="FluxDeFirmesFields" className="es.caib.portafib.model.fields.FluxDeFirmesFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,FluxDeFirmesFields.NOM)}">
        <tr id="fluxDeFirmes_nom_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[FluxDeFirmesFields.NOM])?'fluxDeFirmes.nom':__theForm.labels[FluxDeFirmesFields.NOM]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[FluxDeFirmesFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[FluxDeFirmesFields.NOM]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="fluxDeFirmes.nom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,FluxDeFirmesFields.NOM)? 'true' : 'false'}" cssClass="form-control ${gen:contains(__theForm.readOnlyFields ,FluxDeFirmesFields.NOM)? ' uneditable-input' : ''}"  style="" maxlength="255" path="fluxDeFirmes.nom"   />

           </td>
        </tr>
        </c:if>
        
