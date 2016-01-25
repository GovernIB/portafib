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
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TipusMetadadaFields.TIPUSMETADADAID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,TipusMetadadaFields.TIPUSMETADADAID)? 'input-small uneditable-input' : 'input-small'}"   path="tipusMetadada.tipusMetadadaID"   />

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
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TipusMetadadaFields.NOM)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,TipusMetadadaFields.NOM)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="100" path="tipusMetadada.nom"   />

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
              <form:textarea rows="3" wrap="soft" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,TipusMetadadaFields.DESCRIPCIO)? 'true' : 'false'}" path="tipusMetadada.descripcio"  />
              <div class="btn-group" style="vertical-align: top;">
              <button class="btn btn-mini dropdown-toggle" data-toggle="dropdown">&nbsp;<span class="caret"></span></button>
              <ul class="dropdown-menu">
                <li><a href="#" onclick="javascript:var ta=document.getElementById('tipusMetadada.descripcio'); ta.wrap='off';" >No Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('tipusMetadada.descripcio'); ta.wrap='soft';">Soft Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('tipusMetadada.descripcio'); ta.wrap='hard';">Hard Wrap</a></li>
              </ul>
              </div>
           </td>
        </tr>
        </c:if>
        
