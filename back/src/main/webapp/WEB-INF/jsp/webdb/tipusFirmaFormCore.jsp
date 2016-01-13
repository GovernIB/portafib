<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="TipusFirmaFields" className="es.caib.portafib.model.fields.TipusFirmaFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,TipusFirmaFields.TIPUSFIRMAID)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[TipusFirmaFields.TIPUSFIRMAID])?'tipusFirma.tipusFirmaID':__theForm.labels[TipusFirmaFields.TIPUSFIRMAID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[TipusFirmaFields.TIPUSFIRMAID]}">
              <i class="icon-info-sign" title="${__theForm.help[TipusFirmaFields.TIPUSFIRMAID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="tipusFirma.tipusFirmaID" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TipusFirmaFields.TIPUSFIRMAID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,TipusFirmaFields.TIPUSFIRMAID)? 'input-large uneditable-input' : 'input-large'}"   path="tipusFirma.tipusFirmaID"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TipusFirmaFields.NOM)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[TipusFirmaFields.NOM])?'tipusFirma.nom':__theForm.labels[TipusFirmaFields.NOM]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[TipusFirmaFields.NOM]}">
              <i class="icon-info-sign" title="${__theForm.help[TipusFirmaFields.NOM]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="tipusFirma.nom" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TipusFirmaFields.NOM)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,TipusFirmaFields.NOM)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="50" path="tipusFirma.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TipusFirmaFields.SUPORTADA)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[TipusFirmaFields.SUPORTADA])?'tipusFirma.suportada':__theForm.labels[TipusFirmaFields.SUPORTADA]}" />
              <c:if test="${not empty __theForm.help[TipusFirmaFields.SUPORTADA]}">
              <i class="icon-info-sign" title="${__theForm.help[TipusFirmaFields.SUPORTADA]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,TipusFirmaFields.SUPORTADA)}" >
              <form:errors path="tipusFirma.suportada" cssClass="errorField alert alert-error" />
              <form:checkbox onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,TipusFirmaFields.SUPORTADA)? 'false' : 'true'}" path="tipusFirma.suportada" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,TipusFirmaFields.SUPORTADA)}" >
                <fmt:message key="genapp.checkbox.${__theForm.tipusFirma.suportada}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TipusFirmaFields.DESCRIPCIO)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[TipusFirmaFields.DESCRIPCIO])?'tipusFirma.descripcio':__theForm.labels[TipusFirmaFields.DESCRIPCIO]}" />
              <c:if test="${not empty __theForm.help[TipusFirmaFields.DESCRIPCIO]}">
              <i class="icon-info-sign" title="${__theForm.help[TipusFirmaFields.DESCRIPCIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="tipusFirma.descripcio" cssClass="errorField alert alert-error" />
              <form:textarea cssClass="input-xxlarge ${gen:contains(__theForm.readOnlyFields ,TipusFirmaFields.DESCRIPCIO)? 'mceEditorReadOnly':'mceEditor'}" path="tipusFirma.descripcio"  />
           </td>
        </tr>
        </c:if>
        
