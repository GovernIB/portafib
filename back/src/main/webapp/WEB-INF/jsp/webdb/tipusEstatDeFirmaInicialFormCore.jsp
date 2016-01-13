<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="TipusEstatDeFirmaInicialFields" className="es.caib.portafib.model.fields.TipusEstatDeFirmaInicialFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,TipusEstatDeFirmaInicialFields.TIPUSESTATDEFIRMAINICIALID)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[TipusEstatDeFirmaInicialFields.TIPUSESTATDEFIRMAINICIALID])?'tipusEstatDeFirmaInicial.tipusEstatDeFirmaInicialID':__theForm.labels[TipusEstatDeFirmaInicialFields.TIPUSESTATDEFIRMAINICIALID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[TipusEstatDeFirmaInicialFields.TIPUSESTATDEFIRMAINICIALID]}">
              <i class="icon-info-sign" title="${__theForm.help[TipusEstatDeFirmaInicialFields.TIPUSESTATDEFIRMAINICIALID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="tipusEstatDeFirmaInicial.tipusEstatDeFirmaInicialID" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TipusEstatDeFirmaInicialFields.TIPUSESTATDEFIRMAINICIALID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,TipusEstatDeFirmaInicialFields.TIPUSESTATDEFIRMAINICIALID)? 'input-large uneditable-input' : 'input-large'}"   path="tipusEstatDeFirmaInicial.tipusEstatDeFirmaInicialID"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TipusEstatDeFirmaInicialFields.NOM)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[TipusEstatDeFirmaInicialFields.NOM])?'tipusEstatDeFirmaInicial.nom':__theForm.labels[TipusEstatDeFirmaInicialFields.NOM]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[TipusEstatDeFirmaInicialFields.NOM]}">
              <i class="icon-info-sign" title="${__theForm.help[TipusEstatDeFirmaInicialFields.NOM]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="tipusEstatDeFirmaInicial.nom" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TipusEstatDeFirmaInicialFields.NOM)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,TipusEstatDeFirmaInicialFields.NOM)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="50" path="tipusEstatDeFirmaInicial.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TipusEstatDeFirmaInicialFields.DESCRIPCIO)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[TipusEstatDeFirmaInicialFields.DESCRIPCIO])?'tipusEstatDeFirmaInicial.descripcio':__theForm.labels[TipusEstatDeFirmaInicialFields.DESCRIPCIO]}" />
              <c:if test="${not empty __theForm.help[TipusEstatDeFirmaInicialFields.DESCRIPCIO]}">
              <i class="icon-info-sign" title="${__theForm.help[TipusEstatDeFirmaInicialFields.DESCRIPCIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="tipusEstatDeFirmaInicial.descripcio" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,TipusEstatDeFirmaInicialFields.DESCRIPCIO)? 'true' : 'false'}" path="tipusEstatDeFirmaInicial.descripcio"  />
           </td>
        </tr>
        </c:if>
        
