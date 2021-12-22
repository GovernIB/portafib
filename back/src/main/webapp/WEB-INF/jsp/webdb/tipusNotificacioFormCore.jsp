<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="TipusNotificacioFields" className="es.caib.portafib.model.fields.TipusNotificacioFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,TipusNotificacioFields.TIPUSNOTIFICACIOID)}">
        <tr id="tipusNotificacio_tipusNotificacioID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[TipusNotificacioFields.TIPUSNOTIFICACIOID])?'tipusNotificacio.tipusNotificacioID':__theForm.labels[TipusNotificacioFields.TIPUSNOTIFICACIOID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TipusNotificacioFields.TIPUSNOTIFICACIOID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TipusNotificacioFields.TIPUSNOTIFICACIOID]}" ></i>
              </c:if>
            </td>
            <td>
            <form:errors path="tipusNotificacio.tipusNotificacioID" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TipusNotificacioFields.TIPUSNOTIFICACIOID)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,TipusNotificacioFields.TIPUSNOTIFICACIOID)? ' uneditable-input' : ''}"  style=""  path="tipusNotificacio.tipusNotificacioID"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TipusNotificacioFields.NOM)}">
        <tr id="tipusNotificacio_nom_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[TipusNotificacioFields.NOM])?'tipusNotificacio.nom':__theForm.labels[TipusNotificacioFields.NOM]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[TipusNotificacioFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TipusNotificacioFields.NOM]}" ></i>
              </c:if>
            </td>
            <td>
            <form:errors path="tipusNotificacio.nom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TipusNotificacioFields.NOM)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,TipusNotificacioFields.NOM)? ' uneditable-input' : ''}"  style="" maxlength="50" path="tipusNotificacio.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TipusNotificacioFields.DESCRIPCIO)}">
        <tr id="tipusNotificacio_descripcio_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[TipusNotificacioFields.DESCRIPCIO])?'tipusNotificacio.descripcio':__theForm.labels[TipusNotificacioFields.DESCRIPCIO]}" />
             </label>
              <c:if test="${not empty __theForm.help[TipusNotificacioFields.DESCRIPCIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TipusNotificacioFields.DESCRIPCIO]}" ></i>
              </c:if>
            </td>
            <td>
              <form:errors path="tipusNotificacio.descripcio" cssClass="errorField alert alert-danger" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,TipusNotificacioFields.DESCRIPCIO)? 'true' : 'false'}" path="tipusNotificacio.descripcio"  />
      <div id="dropdownMenuButton_descripcio" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_descripcio" class="dropdown-menu">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('tipusNotificacio.descripcio'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('tipusNotificacio.descripcio'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('tipusNotificacio.descripcio'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_descripcio').on('click', function(){
					var valor = ($('#dropdownMenuContainer_descripcio').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_descripcio').css('display', valor);
                 return false;
				});
      </script>           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TipusNotificacioFields.ESAVIS)}">
        <tr id="tipusNotificacio_esAvis_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[TipusNotificacioFields.ESAVIS])?'tipusNotificacio.esAvis':__theForm.labels[TipusNotificacioFields.ESAVIS]}" />
             </label>
              <c:if test="${not empty __theForm.help[TipusNotificacioFields.ESAVIS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TipusNotificacioFields.ESAVIS]}" ></i>
              </c:if>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,TipusNotificacioFields.ESAVIS)}" >
              <form:select cssClass="form-control col-md-6" onchange="if(typeof onChangeEsAvis == 'function') {  onChangeEsAvis(this); };"  path="tipusNotificacio.esAvis">
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
        
