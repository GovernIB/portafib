<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="TipusEstatPeticioDeFirmaFields" className="es.caib.portafib.model.fields.TipusEstatPeticioDeFirmaFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,TipusEstatPeticioDeFirmaFields.TIPUSESTATPETICIODEFIRMAID)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[TipusEstatPeticioDeFirmaFields.TIPUSESTATPETICIODEFIRMAID])?'tipusEstatPeticioDeFirma.tipusEstatPeticioDeFirmaID':__theForm.labels[TipusEstatPeticioDeFirmaFields.TIPUSESTATPETICIODEFIRMAID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[TipusEstatPeticioDeFirmaFields.TIPUSESTATPETICIODEFIRMAID]}">
              <i class="icon-info-sign" title="${__theForm.help[TipusEstatPeticioDeFirmaFields.TIPUSESTATPETICIODEFIRMAID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="tipusEstatPeticioDeFirma.tipusEstatPeticioDeFirmaID" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TipusEstatPeticioDeFirmaFields.TIPUSESTATPETICIODEFIRMAID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,TipusEstatPeticioDeFirmaFields.TIPUSESTATPETICIODEFIRMAID)? 'input-large uneditable-input' : 'input-large'}"   path="tipusEstatPeticioDeFirma.tipusEstatPeticioDeFirmaID"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TipusEstatPeticioDeFirmaFields.NOM)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[TipusEstatPeticioDeFirmaFields.NOM])?'tipusEstatPeticioDeFirma.nom':__theForm.labels[TipusEstatPeticioDeFirmaFields.NOM]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[TipusEstatPeticioDeFirmaFields.NOM]}">
              <i class="icon-info-sign" title="${__theForm.help[TipusEstatPeticioDeFirmaFields.NOM]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="tipusEstatPeticioDeFirma.nom" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TipusEstatPeticioDeFirmaFields.NOM)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,TipusEstatPeticioDeFirmaFields.NOM)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="50" path="tipusEstatPeticioDeFirma.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TipusEstatPeticioDeFirmaFields.DESCRIPCIO)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[TipusEstatPeticioDeFirmaFields.DESCRIPCIO])?'tipusEstatPeticioDeFirma.descripcio':__theForm.labels[TipusEstatPeticioDeFirmaFields.DESCRIPCIO]}" />
              <c:if test="${not empty __theForm.help[TipusEstatPeticioDeFirmaFields.DESCRIPCIO]}">
              <i class="icon-info-sign" title="${__theForm.help[TipusEstatPeticioDeFirmaFields.DESCRIPCIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="tipusEstatPeticioDeFirma.descripcio" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" readonly="${ gen:contains(__theForm.readOnlyFields ,TipusEstatPeticioDeFirmaFields.DESCRIPCIO)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,TipusEstatPeticioDeFirmaFields.DESCRIPCIO)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"   path="tipusEstatPeticioDeFirma.descripcio"  />
           </td>
        </tr>
        </c:if>
        
