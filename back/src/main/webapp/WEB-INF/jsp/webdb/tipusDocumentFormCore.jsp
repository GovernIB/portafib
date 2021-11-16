<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="TipusDocumentFields" className="es.caib.portafib.model.fields.TipusDocumentFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,TipusDocumentFields.TIPUSDOCUMENTID)}">
        <tr id="tipusDocument_tipusDocumentID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[TipusDocumentFields.TIPUSDOCUMENTID])?'tipusDocument.tipusDocumentID':__theForm.labels[TipusDocumentFields.TIPUSDOCUMENTID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[TipusDocumentFields.TIPUSDOCUMENTID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TipusDocumentFields.TIPUSDOCUMENTID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="tipusDocument.tipusDocumentID" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TipusDocumentFields.TIPUSDOCUMENTID)? 'true' : 'false'}" cssClass="form-control col-md-9-optional ${gen:contains(__theForm.readOnlyFields ,TipusDocumentFields.TIPUSDOCUMENTID)? ' uneditable-input' : ''}"  style=""  path="tipusDocument.tipusDocumentID"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TipusDocumentFields.NOMID)}">
        <tr id="tipusDocument_nomID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[TipusDocumentFields.NOMID])?'tipusDocument.nomID':__theForm.labels[TipusDocumentFields.NOMID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[TipusDocumentFields.NOMID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TipusDocumentFields.NOMID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
       <form:errors path="tipusDocument.nom" cssClass="errorField alert alert-danger" />
       <div class="row-fluid col-md-9-optional">
         <ul class="nav nav-tabs" style="margin: 0 15px -1px;">
             <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
            <li class="nav-item ">
                 <a class="nav-link ${(counter.index == 0)? 'active':''}" href="#${counter.index}_tab_nom_${idioma.idiomaID}" data-toggle="tab">${idioma.nom}</a>
            </li>
          </c:forEach>
           
         </ul>
         <div class="tab-content well well-white" style="padding:8px;margin:0px;">
           <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
           <div class="tab-pane ${(counter.index == 0)? 'active':'' }" id="${counter.index}_tab_nom_${idioma.idiomaID}">
               <form:errors path="tipusDocument.nom.traduccions['${idioma.idiomaID}'].valor" cssClass="errorField alert alert-danger"/>
               <form:input path="tipusDocument.nom.traduccions['${idioma.idiomaID}'].valor" cssClass="form-control col-md-9-optional ${gen:contains(__theForm.readOnlyFields ,TipusDocumentFields.NOMID)? ' uneditable-input' : ''}" readonly="${gen:contains(__theForm.readOnlyFields ,TipusDocumentFields.NOMID)}" maxlength="4000" />
           </div>
           </c:forEach>
         </div>
       </div>

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TipusDocumentFields.TIPUSDOCUMENTBASEID)}">
        <tr id="tipusDocument_tipusDocumentBaseID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[TipusDocumentFields.TIPUSDOCUMENTBASEID])?'tipusDocument.tipusDocumentBaseID':__theForm.labels[TipusDocumentFields.TIPUSDOCUMENTBASEID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[TipusDocumentFields.TIPUSDOCUMENTBASEID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TipusDocumentFields.TIPUSDOCUMENTBASEID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="tipusDocument.tipusDocumentBaseID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,TipusDocumentFields.TIPUSDOCUMENTBASEID)}" >
          <form:hidden path="tipusDocument.tipusDocumentBaseID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.tipusDocument.tipusDocumentBaseID,__theForm.listOfValuesForTipusDocumentBaseID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,TipusDocumentFields.TIPUSDOCUMENTBASEID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="tipusDocument_tipusDocumentBaseID"  onchange="if(typeof onChangeTipusDocumentBaseID == 'function') {  onChangeTipusDocumentBaseID(this); };"  cssClass="form-control col-md-9-optional" path="tipusDocument.tipusDocumentBaseID">
            <c:forEach items="${__theForm.listOfValuesForTipusDocumentBaseID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TipusDocumentFields.DESCRIPCIO)}">
        <tr id="tipusDocument_descripcio_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[TipusDocumentFields.DESCRIPCIO])?'tipusDocument.descripcio':__theForm.labels[TipusDocumentFields.DESCRIPCIO]}" />
              <c:if test="${not empty __theForm.help[TipusDocumentFields.DESCRIPCIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TipusDocumentFields.DESCRIPCIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="tipusDocument.descripcio" cssClass="errorField alert alert-danger" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,TipusDocumentFields.DESCRIPCIO)? 'true' : 'false'}" path="tipusDocument.descripcio"  />
      <div id="dropdownMenuButton_descripcio" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_descripcio" class="dropdown-menu">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('tipusDocument.descripcio'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('tipusDocument.descripcio'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('tipusDocument.descripcio'); ta.wrap='hard';">Hard Wrap</a>
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TipusDocumentFields.USUARIAPLICACIOID)}">
        <tr id="tipusDocument_usuariAplicacioID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[TipusDocumentFields.USUARIAPLICACIOID])?'tipusDocument.usuariAplicacioID':__theForm.labels[TipusDocumentFields.USUARIAPLICACIOID]}" />
              <c:if test="${not empty __theForm.help[TipusDocumentFields.USUARIAPLICACIOID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[TipusDocumentFields.USUARIAPLICACIOID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="tipusDocument.usuariAplicacioID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,TipusDocumentFields.USUARIAPLICACIOID)}" >
          <form:hidden path="tipusDocument.usuariAplicacioID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.tipusDocument.usuariAplicacioID,__theForm.listOfUsuariAplicacioForUsuariAplicacioID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,TipusDocumentFields.USUARIAPLICACIOID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="tipusDocument_usuariAplicacioID"  onchange="if(typeof onChangeUsuariAplicacioID == 'function') {  onChangeUsuariAplicacioID(this); };"  cssClass="form-control col-md-9-optional" path="tipusDocument.usuariAplicacioID">
            <c:forEach items="${__theForm.listOfUsuariAplicacioForUsuariAplicacioID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.tipusDocument.usuariAplicacioID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.tipusDocument.usuariAplicacioID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
