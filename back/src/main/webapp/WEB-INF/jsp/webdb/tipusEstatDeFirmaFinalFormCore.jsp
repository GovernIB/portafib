<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="TipusEstatDeFirmaFinalFields" className="es.caib.portafib.model.fields.TipusEstatDeFirmaFinalFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,TipusEstatDeFirmaFinalFields.TIPUSESTATDEFIRMAFINALID)}">
        <tr id="tipusEstatDeFirmaFinal_tipusEstatDeFirmaFinalID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[TipusEstatDeFirmaFinalFields.TIPUSESTATDEFIRMAFINALID])?'tipusEstatDeFirmaFinal.tipusEstatDeFirmaFinalID':__theForm.labels[TipusEstatDeFirmaFinalFields.TIPUSESTATDEFIRMAFINALID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[TipusEstatDeFirmaFinalFields.TIPUSESTATDEFIRMAFINALID]}">
              <i class="icon-info-sign" title="${__theForm.help[TipusEstatDeFirmaFinalFields.TIPUSESTATDEFIRMAFINALID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="tipusEstatDeFirmaFinal.tipusEstatDeFirmaFinalID" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TipusEstatDeFirmaFinalFields.TIPUSESTATDEFIRMAFINALID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,TipusEstatDeFirmaFinalFields.TIPUSESTATDEFIRMAFINALID)? 'input-large uneditable-input' : 'input-large'}"   path="tipusEstatDeFirmaFinal.tipusEstatDeFirmaFinalID"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TipusEstatDeFirmaFinalFields.NOM)}">
        <tr id="tipusEstatDeFirmaFinal_nom_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[TipusEstatDeFirmaFinalFields.NOM])?'tipusEstatDeFirmaFinal.nom':__theForm.labels[TipusEstatDeFirmaFinalFields.NOM]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[TipusEstatDeFirmaFinalFields.NOM]}">
              <i class="icon-info-sign" title="${__theForm.help[TipusEstatDeFirmaFinalFields.NOM]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="tipusEstatDeFirmaFinal.nom" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TipusEstatDeFirmaFinalFields.NOM)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,TipusEstatDeFirmaFinalFields.NOM)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="50" path="tipusEstatDeFirmaFinal.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TipusEstatDeFirmaFinalFields.DESCRIPCIO)}">
        <tr id="tipusEstatDeFirmaFinal_descripcio_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[TipusEstatDeFirmaFinalFields.DESCRIPCIO])?'tipusEstatDeFirmaFinal.descripcio':__theForm.labels[TipusEstatDeFirmaFinalFields.DESCRIPCIO]}" />
              <c:if test="${not empty __theForm.help[TipusEstatDeFirmaFinalFields.DESCRIPCIO]}">
              <i class="icon-info-sign" title="${__theForm.help[TipusEstatDeFirmaFinalFields.DESCRIPCIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="tipusEstatDeFirmaFinal.descripcio" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,TipusEstatDeFirmaFinalFields.DESCRIPCIO)? 'true' : 'false'}" path="tipusEstatDeFirmaFinal.descripcio"  />
              <div class="btn-group" style="vertical-align: top;">
              <button class="btn btn-mini dropdown-toggle" data-toggle="dropdown">&nbsp;<span class="caret"></span></button>
              <ul class="dropdown-menu">
                <li><a href="#" onclick="javascript:var ta=document.getElementById('tipusEstatDeFirmaFinal.descripcio'); ta.wrap='off';" >No Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('tipusEstatDeFirmaFinal.descripcio'); ta.wrap='soft';">Soft Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('tipusEstatDeFirmaFinal.descripcio'); ta.wrap='hard';">Hard Wrap</a></li>
              </ul>
              </div>
           </td>
        </tr>
        </c:if>
        
