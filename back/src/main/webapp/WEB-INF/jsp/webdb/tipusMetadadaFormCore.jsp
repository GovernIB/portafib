<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="TipusMetadadaFields" className="es.caib.portafib.model.fields.TipusMetadadaFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,TipusMetadadaFields.TIPUSMETADADAID)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[TipusMetadadaFields.TIPUSMETADADAID])?'tipusMetadada.tipusMetadadaID':__theForm.labels[TipusMetadadaFields.TIPUSMETADADAID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[TipusMetadadaFields.TIPUSMETADADAID]}">
              <i class="icon-info-sign" title="${__theForm.help[TipusMetadadaFields.TIPUSMETADADAID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="tipusMetadada.tipusMetadadaID" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TipusMetadadaFields.TIPUSMETADADAID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,TipusMetadadaFields.TIPUSMETADADAID)? 'input-small uneditable-input' : 'input-small'}"   path="tipusMetadada.tipusMetadadaID" />
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TipusMetadadaFields.NOM)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[TipusMetadadaFields.NOM])?'tipusMetadada.nom':__theForm.labels[TipusMetadadaFields.NOM]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[TipusMetadadaFields.NOM]}">
              <i class="icon-info-sign" title="${__theForm.help[TipusMetadadaFields.NOM]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="tipusMetadada.nom" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TipusMetadadaFields.NOM)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,TipusMetadadaFields.NOM)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="100" path="tipusMetadada.nom" />
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TipusMetadadaFields.DESCRIPCIO)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[TipusMetadadaFields.DESCRIPCIO])?'tipusMetadada.descripcio':__theForm.labels[TipusMetadadaFields.DESCRIPCIO]}" />
              <c:if test="${not empty __theForm.help[TipusMetadadaFields.DESCRIPCIO]}">
              <i class="icon-info-sign" title="${__theForm.help[TipusMetadadaFields.DESCRIPCIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="tipusMetadada.descripcio" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" readonly="${ gen:contains(__theForm.readOnlyFields ,TipusMetadadaFields.DESCRIPCIO)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,TipusMetadadaFields.DESCRIPCIO)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"   path="tipusMetadada.descripcio"  />
           </td>
        </tr>
        </c:if>
        
