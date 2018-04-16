<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="TipusDocumentFields" className="es.caib.portafib.model.fields.TipusDocumentFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,TipusDocumentFields.TIPUSDOCUMENTID)}">
        <tr id="tipusDocument_tipusDocumentID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[TipusDocumentFields.TIPUSDOCUMENTID])?'tipusDocument.tipusDocumentID':__theForm.labels[TipusDocumentFields.TIPUSDOCUMENTID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[TipusDocumentFields.TIPUSDOCUMENTID]}">
              <i class="icon-info-sign" title="${__theForm.help[TipusDocumentFields.TIPUSDOCUMENTID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="tipusDocument.tipusDocumentID" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,TipusDocumentFields.TIPUSDOCUMENTID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,TipusDocumentFields.TIPUSDOCUMENTID)? 'input-large uneditable-input' : 'input-large'}"   path="tipusDocument.tipusDocumentID"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TipusDocumentFields.NOMID)}">
        <tr id="tipusDocument_nomID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[TipusDocumentFields.NOMID])?'tipusDocument.nomID':__theForm.labels[TipusDocumentFields.NOMID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[TipusDocumentFields.NOMID]}">
              <i class="icon-info-sign" title="${__theForm.help[TipusDocumentFields.NOMID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
       <form:errors path="tipusDocument.nom" cssClass="errorField alert alert-error" />
       <div class="tabbable">
         <ul class="nav nav-tabs" style="margin-bottom: 3px;">
             <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
               <li class="${(counter.index == 0)? 'active':''}"  ><a href="#${counter.index}_tab_nom_${idioma.idiomaID}" data-toggle="tab">${idioma.nom}</a></li>
           </c:forEach>
           
         </ul>
         <div class="tab-content">
           <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
           <div class="tab-pane ${(counter.index == 0)? 'active':'' }" id="${counter.index}_tab_nom_${idioma.idiomaID}">
               <form:errors path="tipusDocument.nom.traduccions['${idioma.idiomaID}'].valor" cssClass="errorField alert alert-error"/>
               <form:input path="tipusDocument.nom.traduccions['${idioma.idiomaID}'].valor" readonly="${ gen:contains(__theForm.readOnlyFields ,TipusDocumentFields.NOMID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,TipusDocumentFields.NOMID)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="4000" />
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
              <i class="icon-info-sign" title="${__theForm.help[TipusDocumentFields.TIPUSDOCUMENTBASEID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="tipusDocument.tipusDocumentBaseID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,TipusDocumentFields.TIPUSDOCUMENTBASEID)}" >
          <form:hidden path="tipusDocument.tipusDocumentBaseID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.tipusDocument.tipusDocumentBaseID,__theForm.listOfValuesForTipusDocumentBaseID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,TipusDocumentFields.TIPUSDOCUMENTBASEID)}" >
          <form:select id="tipusDocument_tipusDocumentBaseID"  onchange="if(typeof onChangeTipusDocumentBaseID == 'function') {  onChangeTipusDocumentBaseID(this); };"  cssClass="input-xxlarge" path="tipusDocument.tipusDocumentBaseID">
            <c:forEach items="${__theForm.listOfValuesForTipusDocumentBaseID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
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
              <i class="icon-info-sign" title="${__theForm.help[TipusDocumentFields.DESCRIPCIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="tipusDocument.descripcio" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,TipusDocumentFields.DESCRIPCIO)? 'true' : 'false'}" path="tipusDocument.descripcio"  />
              <div class="btn-group" style="vertical-align: top;">
              <button class="btn btn-mini dropdown-toggle" data-toggle="dropdown">&nbsp;<span class="caret"></span></button>
              <ul class="dropdown-menu">
                <li><a href="#" onclick="javascript:var ta=document.getElementById('tipusDocument.descripcio'); ta.wrap='off';" >No Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('tipusDocument.descripcio'); ta.wrap='soft';">Soft Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('tipusDocument.descripcio'); ta.wrap='hard';">Hard Wrap</a></li>
              </ul>
              </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TipusDocumentFields.USUARIAPLICACIOID)}">
        <tr id="tipusDocument_usuariAplicacioID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[TipusDocumentFields.USUARIAPLICACIOID])?'tipusDocument.usuariAplicacioID':__theForm.labels[TipusDocumentFields.USUARIAPLICACIOID]}" />
              <c:if test="${not empty __theForm.help[TipusDocumentFields.USUARIAPLICACIOID]}">
              <i class="icon-info-sign" title="${__theForm.help[TipusDocumentFields.USUARIAPLICACIOID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="tipusDocument.usuariAplicacioID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,TipusDocumentFields.USUARIAPLICACIOID)}" >
          <form:hidden path="tipusDocument.usuariAplicacioID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.tipusDocument.usuariAplicacioID,__theForm.listOfUsuariAplicacioForUsuariAplicacioID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,TipusDocumentFields.USUARIAPLICACIOID)}" >
          <form:select id="tipusDocument_usuariAplicacioID"  onchange="if(typeof onChangeUsuariAplicacioID == 'function') {  onChangeUsuariAplicacioID(this); };"  cssClass="input-xxlarge" path="tipusDocument.usuariAplicacioID">
          <%-- El camp pot ser null, per la qual cosa afegim una entrada buida --%>
          <form:option value="" ></form:option>
            <c:forEach items="${__theForm.listOfUsuariAplicacioForUsuariAplicacioID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
