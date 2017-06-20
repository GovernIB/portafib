<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="TipusNotificacioFields" className="es.caib.portafib.model.fields.TipusNotificacioFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,TipusNotificacioFields.TIPUSNOTIFICACIOID)}">
        <tr id="tipusNotificacio_tipusNotificacioID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[TipusNotificacioFields.TIPUSNOTIFICACIOID])?'tipusNotificacio.tipusNotificacioID':__theForm.labels[TipusNotificacioFields.TIPUSNOTIFICACIOID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[TipusNotificacioFields.TIPUSNOTIFICACIOID]}">
              <i class="icon-info-sign" title="${__theForm.help[TipusNotificacioFields.TIPUSNOTIFICACIOID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="tipusNotificacio.tipusNotificacioID" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TipusNotificacioFields.TIPUSNOTIFICACIOID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,TipusNotificacioFields.TIPUSNOTIFICACIOID)? 'input-large uneditable-input' : 'input-large'}"   path="tipusNotificacio.tipusNotificacioID"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TipusNotificacioFields.NOM)}">
        <tr id="tipusNotificacio_nom_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[TipusNotificacioFields.NOM])?'tipusNotificacio.nom':__theForm.labels[TipusNotificacioFields.NOM]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[TipusNotificacioFields.NOM]}">
              <i class="icon-info-sign" title="${__theForm.help[TipusNotificacioFields.NOM]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="tipusNotificacio.nom" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TipusNotificacioFields.NOM)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,TipusNotificacioFields.NOM)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="50" path="tipusNotificacio.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TipusNotificacioFields.DESCRIPCIO)}">
        <tr id="tipusNotificacio_descripcio_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[TipusNotificacioFields.DESCRIPCIO])?'tipusNotificacio.descripcio':__theForm.labels[TipusNotificacioFields.DESCRIPCIO]}" />
              <c:if test="${not empty __theForm.help[TipusNotificacioFields.DESCRIPCIO]}">
              <i class="icon-info-sign" title="${__theForm.help[TipusNotificacioFields.DESCRIPCIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="tipusNotificacio.descripcio" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,TipusNotificacioFields.DESCRIPCIO)? 'true' : 'false'}" path="tipusNotificacio.descripcio"  />
              <div class="btn-group" style="vertical-align: top;">
              <button class="btn btn-mini dropdown-toggle" data-toggle="dropdown">&nbsp;<span class="caret"></span></button>
              <ul class="dropdown-menu">
                <li><a href="#" onclick="javascript:var ta=document.getElementById('tipusNotificacio.descripcio'); ta.wrap='off';" >No Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('tipusNotificacio.descripcio'); ta.wrap='soft';">Soft Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('tipusNotificacio.descripcio'); ta.wrap='hard';">Hard Wrap</a></li>
              </ul>
              </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TipusNotificacioFields.ESAVIS)}">
        <tr id="tipusNotificacio_esAvis_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[TipusNotificacioFields.ESAVIS])?'tipusNotificacio.esAvis':__theForm.labels[TipusNotificacioFields.ESAVIS]}" />
              <c:if test="${not empty __theForm.help[TipusNotificacioFields.ESAVIS]}">
              <i class="icon-info-sign" title="${__theForm.help[TipusNotificacioFields.ESAVIS]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,TipusNotificacioFields.ESAVIS)}" >
              <form:select cssClass="input-medium" onchange="if(typeof onChangeEsAvis == 'function') {  onChangeEsAvis(this); };"  path="tipusNotificacio.esAvis">
                <form:option value=""><fmt:message key="genapp.checkbox." /></form:option>
                <form:option value="true" ><fmt:message key="genapp.checkbox.true" /></form:option>
                <form:option value="false" ><fmt:message key="genapp.checkbox.false" /></form:option>
              </form:select>
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,TipusNotificacioFields.ESAVIS)}" >
                <fmt:message key="genapp.checkbox.${__theForm.tipusNotificacio.esAvis}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
