<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="UsuariAplicacioConfiguracioFields" className="es.caib.portafib.model.fields.UsuariAplicacioConfiguracioFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.USUARIAPLICACIOID)}">
        <tr id="usuariAplicacioConfiguracio_usuariAplicacioID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.USUARIAPLICACIOID])?'usuariAplicacioConfiguracio.usuariAplicacioID':__theForm.labels[UsuariAplicacioConfiguracioFields.USUARIAPLICACIOID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.USUARIAPLICACIOID]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.USUARIAPLICACIOID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="usuariAplicacioConfiguracio.usuariAplicacioID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.USUARIAPLICACIOID)}" >
          <form:hidden path="usuariAplicacioConfiguracio.usuariAplicacioID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.usuariAplicacioConfiguracio.usuariAplicacioID,__theForm.listOfUsuariAplicacioForUsuariAplicacioID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.USUARIAPLICACIOID)}" >
          <form:select id="usuariAplicacioConfiguracio_usuariAplicacioID"  onchange="if(typeof onChangeUsuariAplicacioID == 'function') {  onChangeUsuariAplicacioID(this); };"  cssClass="input-xxlarge" path="usuariAplicacioConfiguracio.usuariAplicacioID">
            <c:forEach items="${__theForm.listOfUsuariAplicacioForUsuariAplicacioID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.USPOLITICADETIRMA)}">
        <tr id="usuariAplicacioConfiguracio_usPoliticaDeTirma_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.USPOLITICADETIRMA])?'usuariAplicacioConfiguracio.usPoliticaDeTirma':__theForm.labels[UsuariAplicacioConfiguracioFields.USPOLITICADETIRMA]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.USPOLITICADETIRMA]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.USPOLITICADETIRMA]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="usuariAplicacioConfiguracio.usPoliticaDeTirma" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.USPOLITICADETIRMA)}" >
          <form:hidden path="usuariAplicacioConfiguracio.usPoliticaDeTirma"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.usuariAplicacioConfiguracio.usPoliticaDeTirma,__theForm.listOfValuesForUsPoliticaDeTirma)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.USPOLITICADETIRMA)}" >
          <form:select id="usuariAplicacioConfiguracio_usPoliticaDeTirma"  onchange="if(typeof onChangeUsPoliticaDeTirma == 'function') {  onChangeUsPoliticaDeTirma(this); };"  cssClass="input-xxlarge" path="usuariAplicacioConfiguracio.usPoliticaDeTirma">
            <c:forEach items="${__theForm.listOfValuesForUsPoliticaDeTirma}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIER)}">
        <tr id="usuariAplicacioConfiguracio_policyIdentifier_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.POLICYIDENTIFIER])?'usuariAplicacioConfiguracio.policyIdentifier':__theForm.labels[UsuariAplicacioConfiguracioFields.POLICYIDENTIFIER]}" />
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.POLICYIDENTIFIER]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.POLICYIDENTIFIER]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="usuariAplicacioConfiguracio.policyIdentifier" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIER)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIER)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="100" path="usuariAplicacioConfiguracio.policyIdentifier"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASH)}">
        <tr id="usuariAplicacioConfiguracio_policyIdentifierHash_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASH])?'usuariAplicacioConfiguracio.policyIdentifierHash':__theForm.labels[UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASH]}" />
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASH]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASH]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="usuariAplicacioConfiguracio.policyIdentifierHash" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASH)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASH)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="256" path="usuariAplicacioConfiguracio.policyIdentifierHash"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASHALGORITHM)}">
        <tr id="usuariAplicacioConfiguracio_policyIdentifierHashAlgorithm_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASHALGORITHM])?'usuariAplicacioConfiguracio.policyIdentifierHashAlgorithm':__theForm.labels[UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASHALGORITHM]}" />
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASHALGORITHM]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASHALGORITHM]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="usuariAplicacioConfiguracio.policyIdentifierHashAlgorithm" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASHALGORITHM)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.POLICYIDENTIFIERHASHALGORITHM)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="50" path="usuariAplicacioConfiguracio.policyIdentifierHashAlgorithm"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.POLICYURLDOCUMENT)}">
        <tr id="usuariAplicacioConfiguracio_policyUrlDocument_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.POLICYURLDOCUMENT])?'usuariAplicacioConfiguracio.policyUrlDocument':__theForm.labels[UsuariAplicacioConfiguracioFields.POLICYURLDOCUMENT]}" />
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.POLICYURLDOCUMENT]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.POLICYURLDOCUMENT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="usuariAplicacioConfiguracio.policyUrlDocument" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.POLICYURLDOCUMENT)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.POLICYURLDOCUMENT)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="255" path="usuariAplicacioConfiguracio.policyUrlDocument"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.FILTRECERTIFICATS)}">
        <tr id="usuariAplicacioConfiguracio_filtreCertificats_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.FILTRECERTIFICATS])?'usuariAplicacioConfiguracio.filtreCertificats':__theForm.labels[UsuariAplicacioConfiguracioFields.FILTRECERTIFICATS]}" />
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.FILTRECERTIFICATS]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.FILTRECERTIFICATS]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="usuariAplicacioConfiguracio.filtreCertificats" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.FILTRECERTIFICATS)? 'true' : 'false'}" path="usuariAplicacioConfiguracio.filtreCertificats"  />
              <div class="btn-group" style="vertical-align: top;">
              <button class="btn btn-mini dropdown-toggle" data-toggle="dropdown">&nbsp;<span class="caret"></span></button>
              <ul class="dropdown-menu">
                <li><a href="#" onclick="javascript:var ta=document.getElementById('usuariAplicacioConfiguracio.filtreCertificats'); ta.wrap='off';" >No Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('usuariAplicacioConfiguracio.filtreCertificats'); ta.wrap='soft';">Soft Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('usuariAplicacioConfiguracio.filtreCertificats'); ta.wrap='hard';">Hard Wrap</a></li>
              </ul>
              </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.TIPUSOPERACIOFIRMA)}">
        <tr id="usuariAplicacioConfiguracio_tipusOperacioFirma_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.TIPUSOPERACIOFIRMA])?'usuariAplicacioConfiguracio.tipusOperacioFirma':__theForm.labels[UsuariAplicacioConfiguracioFields.TIPUSOPERACIOFIRMA]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.TIPUSOPERACIOFIRMA]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.TIPUSOPERACIOFIRMA]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="usuariAplicacioConfiguracio.tipusOperacioFirma" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.TIPUSOPERACIOFIRMA)}" >
          <form:hidden path="usuariAplicacioConfiguracio.tipusOperacioFirma"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.usuariAplicacioConfiguracio.tipusOperacioFirma,__theForm.listOfValuesForTipusOperacioFirma)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.TIPUSOPERACIOFIRMA)}" >
          <form:select id="usuariAplicacioConfiguracio_tipusOperacioFirma"  onchange="if(typeof onChangeTipusOperacioFirma == 'function') {  onChangeTipusOperacioFirma(this); };"  cssClass="input-xxlarge" path="usuariAplicacioConfiguracio.tipusOperacioFirma">
            <c:forEach items="${__theForm.listOfValuesForTipusOperacioFirma}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.TIPUSFIRMAID)}">
        <tr id="usuariAplicacioConfiguracio_tipusFirmaID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.TIPUSFIRMAID])?'usuariAplicacioConfiguracio.tipusFirmaID':__theForm.labels[UsuariAplicacioConfiguracioFields.TIPUSFIRMAID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.TIPUSFIRMAID]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.TIPUSFIRMAID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="usuariAplicacioConfiguracio.tipusFirmaID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.TIPUSFIRMAID)}" >
          <form:hidden path="usuariAplicacioConfiguracio.tipusFirmaID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.usuariAplicacioConfiguracio.tipusFirmaID,__theForm.listOfTipusFirmaForTipusFirmaID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.TIPUSFIRMAID)}" >
          <form:select id="usuariAplicacioConfiguracio_tipusFirmaID"  onchange="if(typeof onChangeTipusFirmaID == 'function') {  onChangeTipusFirmaID(this); };"  cssClass="input-xxlarge" path="usuariAplicacioConfiguracio.tipusFirmaID">
            <c:forEach items="${__theForm.listOfTipusFirmaForTipusFirmaID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.ALGORISMEDEFIRMAID)}">
        <tr id="usuariAplicacioConfiguracio_algorismeDeFirmaID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.ALGORISMEDEFIRMAID])?'usuariAplicacioConfiguracio.algorismeDeFirmaID':__theForm.labels[UsuariAplicacioConfiguracioFields.ALGORISMEDEFIRMAID]}" />
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.ALGORISMEDEFIRMAID]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.ALGORISMEDEFIRMAID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="usuariAplicacioConfiguracio.algorismeDeFirmaID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.ALGORISMEDEFIRMAID)}" >
          <form:hidden path="usuariAplicacioConfiguracio.algorismeDeFirmaID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.usuariAplicacioConfiguracio.algorismeDeFirmaID,__theForm.listOfAlgorismeDeFirmaForAlgorismeDeFirmaID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.ALGORISMEDEFIRMAID)}" >
          <form:select id="usuariAplicacioConfiguracio_algorismeDeFirmaID"  onchange="if(typeof onChangeAlgorismeDeFirmaID == 'function') {  onChangeAlgorismeDeFirmaID(this); };"  cssClass="input-xxlarge" path="usuariAplicacioConfiguracio.algorismeDeFirmaID">
          <%-- El camp pot ser null, per la qual cosa afegim una entrada buida --%>
          <form:option value="" ></form:option>
            <c:forEach items="${__theForm.listOfAlgorismeDeFirmaForAlgorismeDeFirmaID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.MODEDEFIRMA)}">
        <tr id="usuariAplicacioConfiguracio_modeDeFirma_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.MODEDEFIRMA])?'usuariAplicacioConfiguracio.modeDeFirma':__theForm.labels[UsuariAplicacioConfiguracioFields.MODEDEFIRMA]}" />
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.MODEDEFIRMA]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.MODEDEFIRMA]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.MODEDEFIRMA)}" >
              <form:select cssClass="input-medium" onchange="if(typeof onChangeModeDeFirma == 'function') {  onChangeModeDeFirma(this); };"  path="usuariAplicacioConfiguracio.modeDeFirma">
                <form:option value="true" ><fmt:message key="modefirma.true" /></form:option>
                <form:option value="false" ><fmt:message key="modefirma.false" /></form:option>
              </form:select>
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.MODEDEFIRMA)}" >
                <fmt:message key="modefirma.${__theForm.usuariAplicacioConfiguracio.modeDeFirma}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.MOTIUDELEGACIOID)}">
        <tr id="usuariAplicacioConfiguracio_motiuDelegacioID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.MOTIUDELEGACIOID])?'usuariAplicacioConfiguracio.motiuDelegacioID':__theForm.labels[UsuariAplicacioConfiguracioFields.MOTIUDELEGACIOID]}" />
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.MOTIUDELEGACIOID]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.MOTIUDELEGACIOID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
       <form:errors path="usuariAplicacioConfiguracio.motiuDelegacio" cssClass="errorField alert alert-error" />
       <div class="tabbable">
         <ul class="nav nav-tabs" style="margin-bottom: 3px;">
             <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
               <li class="${(counter.index == 0)? 'active':''}"  ><a href="#${counter.index}_tab_motiuDelegacio_${idioma.idiomaID}" data-toggle="tab">${idioma.nom}</a></li>
           </c:forEach>
           
         </ul>
         <div class="tab-content">
           <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
           <div class="tab-pane ${(counter.index == 0)? 'active':'' }" id="${counter.index}_tab_motiuDelegacio_${idioma.idiomaID}">
               <form:errors path="usuariAplicacioConfiguracio.motiuDelegacio.traduccions['${idioma.idiomaID}'].valor" cssClass="errorField alert alert-error"/>
               <form:input path="usuariAplicacioConfiguracio.motiuDelegacio.traduccions['${idioma.idiomaID}'].valor" readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.MOTIUDELEGACIOID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.MOTIUDELEGACIOID)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="4000" />
           </div>
           </c:forEach>
         </div>
       </div>

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.FIRMATPERFORMATID)}">
        <tr id="usuariAplicacioConfiguracio_firmatPerFormatID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.FIRMATPERFORMATID])?'usuariAplicacioConfiguracio.firmatPerFormatID':__theForm.labels[UsuariAplicacioConfiguracioFields.FIRMATPERFORMATID]}" />
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.FIRMATPERFORMATID]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.FIRMATPERFORMATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
       <form:errors path="usuariAplicacioConfiguracio.firmatPerFormat" cssClass="errorField alert alert-error" />
       <div class="tabbable">
         <ul class="nav nav-tabs" style="margin-bottom: 3px;">
             <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
               <li class="${(counter.index == 0)? 'active':''}"  ><a href="#${counter.index}_tab_firmatPerFormat_${idioma.idiomaID}" data-toggle="tab">${idioma.nom}</a></li>
           </c:forEach>
           
         </ul>
         <div class="tab-content">
           <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
           <div class="tab-pane ${(counter.index == 0)? 'active':'' }" id="${counter.index}_tab_firmatPerFormat_${idioma.idiomaID}">
               <form:errors path="usuariAplicacioConfiguracio.firmatPerFormat.traduccions['${idioma.idiomaID}'].valor" cssClass="errorField alert alert-error"/>
               <form:input path="usuariAplicacioConfiguracio.firmatPerFormat.traduccions['${idioma.idiomaID}'].valor" readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.FIRMATPERFORMATID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.FIRMATPERFORMATID)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="4000" />
           </div>
           </c:forEach>
         </div>
       </div>

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.CUSTODIAINFOID)}">
        <tr id="usuariAplicacioConfiguracio_custodiaInfoID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.CUSTODIAINFOID])?'usuariAplicacioConfiguracio.custodiaInfoID':__theForm.labels[UsuariAplicacioConfiguracioFields.CUSTODIAINFOID]}" />
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.CUSTODIAINFOID]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.CUSTODIAINFOID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="usuariAplicacioConfiguracio.custodiaInfoID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.CUSTODIAINFOID)}" >
          <form:hidden path="usuariAplicacioConfiguracio.custodiaInfoID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.usuariAplicacioConfiguracio.custodiaInfoID,__theForm.listOfCustodiaInfoForCustodiaInfoID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.CUSTODIAINFOID)}" >
          <form:select id="usuariAplicacioConfiguracio_custodiaInfoID"  onchange="if(typeof onChangeCustodiaInfoID == 'function') {  onChangeCustodiaInfoID(this); };"  cssClass="input-xxlarge" path="usuariAplicacioConfiguracio.custodiaInfoID">
          <%-- El camp pot ser null, per la qual cosa afegim una entrada buida --%>
          <form:option value="" ></form:option>
            <c:forEach items="${__theForm.listOfCustodiaInfoForCustodiaInfoID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.POSICIOTAULAFIRMESID)}">
        <tr id="usuariAplicacioConfiguracio_posicioTaulaFirmesID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.POSICIOTAULAFIRMESID])?'usuariAplicacioConfiguracio.posicioTaulaFirmesID':__theForm.labels[UsuariAplicacioConfiguracioFields.POSICIOTAULAFIRMESID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.POSICIOTAULAFIRMESID]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.POSICIOTAULAFIRMESID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="usuariAplicacioConfiguracio.posicioTaulaFirmesID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.POSICIOTAULAFIRMESID)}" >
          <form:hidden path="usuariAplicacioConfiguracio.posicioTaulaFirmesID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.usuariAplicacioConfiguracio.posicioTaulaFirmesID,__theForm.listOfPosicioTaulaFirmesForPosicioTaulaFirmesID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.POSICIOTAULAFIRMESID)}" >
          <form:select id="usuariAplicacioConfiguracio_posicioTaulaFirmesID"  onchange="if(typeof onChangePosicioTaulaFirmesID == 'function') {  onChangePosicioTaulaFirmesID(this); };"  cssClass="input-xxlarge" path="usuariAplicacioConfiguracio.posicioTaulaFirmesID">
            <c:forEach items="${__theForm.listOfPosicioTaulaFirmesForPosicioTaulaFirmesID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.PLUGINSEGELLATID)}">
        <tr id="usuariAplicacioConfiguracio_pluginSegellatID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.PLUGINSEGELLATID])?'usuariAplicacioConfiguracio.pluginSegellatID':__theForm.labels[UsuariAplicacioConfiguracioFields.PLUGINSEGELLATID]}" />
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.PLUGINSEGELLATID]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.PLUGINSEGELLATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="usuariAplicacioConfiguracio.pluginSegellatID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.PLUGINSEGELLATID)}" >
          <form:hidden path="usuariAplicacioConfiguracio.pluginSegellatID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.usuariAplicacioConfiguracio.pluginSegellatID,__theForm.listOfPluginForPluginSegellatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.PLUGINSEGELLATID)}" >
          <form:select id="usuariAplicacioConfiguracio_pluginSegellatID"  onchange="if(typeof onChangePluginSegellatID == 'function') {  onChangePluginSegellatID(this); };"  cssClass="input-xxlarge" path="usuariAplicacioConfiguracio.pluginSegellatID">
          <%-- El camp pot ser null, per la qual cosa afegim una entrada buida --%>
          <form:option value="" ></form:option>
            <c:forEach items="${__theForm.listOfPluginForPluginSegellatID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.PLUGINFIRMASERVIDORID)}">
        <tr id="usuariAplicacioConfiguracio_pluginFirmaServidorID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.PLUGINFIRMASERVIDORID])?'usuariAplicacioConfiguracio.pluginFirmaServidorID':__theForm.labels[UsuariAplicacioConfiguracioFields.PLUGINFIRMASERVIDORID]}" />
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.PLUGINFIRMASERVIDORID]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.PLUGINFIRMASERVIDORID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="usuariAplicacioConfiguracio.pluginFirmaServidorID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.PLUGINFIRMASERVIDORID)}" >
          <form:hidden path="usuariAplicacioConfiguracio.pluginFirmaServidorID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.usuariAplicacioConfiguracio.pluginFirmaServidorID,__theForm.listOfPluginForPluginFirmaServidorID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.PLUGINFIRMASERVIDORID)}" >
          <form:select id="usuariAplicacioConfiguracio_pluginFirmaServidorID"  onchange="if(typeof onChangePluginFirmaServidorID == 'function') {  onChangePluginFirmaServidorID(this); };"  cssClass="input-xxlarge" path="usuariAplicacioConfiguracio.pluginFirmaServidorID">
          <%-- El camp pot ser null, per la qual cosa afegim una entrada buida --%>
          <form:option value="" ></form:option>
            <c:forEach items="${__theForm.listOfPluginForPluginFirmaServidorID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.HTMLPERLLISTARPLUGINSFIRMAWEB)}">
        <tr id="usuariAplicacioConfiguracio_htmlPerLlistarPluginsFirmaWeb_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.HTMLPERLLISTARPLUGINSFIRMAWEB])?'usuariAplicacioConfiguracio.htmlPerLlistarPluginsFirmaWeb':__theForm.labels[UsuariAplicacioConfiguracioFields.HTMLPERLLISTARPLUGINSFIRMAWEB]}" />
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.HTMLPERLLISTARPLUGINSFIRMAWEB]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.HTMLPERLLISTARPLUGINSFIRMAWEB]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="usuariAplicacioConfiguracio.htmlPerLlistarPluginsFirmaWeb" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.HTMLPERLLISTARPLUGINSFIRMAWEB)? 'true' : 'false'}" path="usuariAplicacioConfiguracio.htmlPerLlistarPluginsFirmaWeb"  />
              <div class="btn-group" style="vertical-align: top;">
              <button class="btn btn-mini dropdown-toggle" data-toggle="dropdown">&nbsp;<span class="caret"></span></button>
              <ul class="dropdown-menu">
                <li><a href="#" onclick="javascript:var ta=document.getElementById('usuariAplicacioConfiguracio.htmlPerLlistarPluginsFirmaWeb'); ta.wrap='off';" >No Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('usuariAplicacioConfiguracio.htmlPerLlistarPluginsFirmaWeb'); ta.wrap='soft';">Soft Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('usuariAplicacioConfiguracio.htmlPerLlistarPluginsFirmaWeb'); ta.wrap='hard';">Hard Wrap</a></li>
              </ul>
              </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.LOGINCERTIFICATEID)}">
        <tr id="usuariAplicacioConfiguracio_loginCertificateID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.LOGINCERTIFICATEID])?'usuariAplicacioConfiguracio.loginCertificateID':__theForm.labels[UsuariAplicacioConfiguracioFields.LOGINCERTIFICATEID]}" />
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.LOGINCERTIFICATEID]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.LOGINCERTIFICATEID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="usuariAplicacioConfiguracio.loginCertificateID" cssClass="errorField alert alert-error" />
              <div class="fileupload fileupload-new" data-provides="fileupload" style="margin-bottom: 0px">
                <div class="input-append">
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.LOGINCERTIFICATEID)}" >
                    <div class="uneditable-input span3">
                      <i class="icon-file fileupload-exists"></i>
                      <span class="fileupload-preview"></span>
                    </div>
                    <span class="btn btn-file">
                      <span class="fileupload-new"><fmt:message key="genapp.form.file.select"/></span>
                      <span class="fileupload-exists"><fmt:message key="genapp.form.file.change"/></span>
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.LOGINCERTIFICATEID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.LOGINCERTIFICATEID)? 'input uneditable-input' : 'input'}"  path="loginCertificateID" type="file" />
                    </span>
                    <a href="#" class="btn fileupload-exists" data-dismiss="fileupload"><fmt:message key="genapp.form.file.unselect"/></a>
                    <span class="add-on">&nbsp;</span>
                </c:if>
                <c:if test="${not empty __theForm.usuariAplicacioConfiguracio.loginCertificate}">
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.LOGINCERTIFICATEID)}" >
                    <span class="add-on">
                        <form:checkbox path="loginCertificateIDDelete"/>
                        <fmt:message key="genapp.form.file.delete"/>
                    </span>
                    <span class="add-on">&nbsp;</span>   
                </c:if>
                    <span class="add-on">
                        <a target="_blank" href="<c:url value="${pfi:fileUrl(__theForm.usuariAplicacioConfiguracio.loginCertificate)}"/>">${__theForm.usuariAplicacioConfiguracio.loginCertificate.nom}</a>
                    </span>
                </c:if>
                </div>
              </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.COMPROVARNIFFIRMA)}">
        <tr id="usuariAplicacioConfiguracio_comprovarNifFirma_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.COMPROVARNIFFIRMA])?'usuariAplicacioConfiguracio.comprovarNifFirma':__theForm.labels[UsuariAplicacioConfiguracioFields.COMPROVARNIFFIRMA]}" />
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.COMPROVARNIFFIRMA]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.COMPROVARNIFFIRMA]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.COMPROVARNIFFIRMA)}" >
              <form:select cssClass="input-medium" onchange="if(typeof onChangeComprovarNifFirma == 'function') {  onChangeComprovarNifFirma(this); };"  path="usuariAplicacioConfiguracio.comprovarNifFirma">
                <form:option value=""><fmt:message key="definitenentitat." /></form:option>
                <form:option value="true" ><fmt:message key="definitenentitat.true" /></form:option>
                <form:option value="false" ><fmt:message key="definitenentitat.false" /></form:option>
              </form:select>
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.COMPROVARNIFFIRMA)}" >
                <fmt:message key="definitenentitat.${__theForm.usuariAplicacioConfiguracio.comprovarNifFirma}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.CHECKCANVIATDOCFIRMAT)}">
        <tr id="usuariAplicacioConfiguracio_checkCanviatDocFirmat_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.CHECKCANVIATDOCFIRMAT])?'usuariAplicacioConfiguracio.checkCanviatDocFirmat':__theForm.labels[UsuariAplicacioConfiguracioFields.CHECKCANVIATDOCFIRMAT]}" />
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.CHECKCANVIATDOCFIRMAT]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.CHECKCANVIATDOCFIRMAT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.CHECKCANVIATDOCFIRMAT)}" >
              <form:select cssClass="input-medium" onchange="if(typeof onChangeCheckCanviatDocFirmat == 'function') {  onChangeCheckCanviatDocFirmat(this); };"  path="usuariAplicacioConfiguracio.checkCanviatDocFirmat">
                <form:option value=""><fmt:message key="definitenentitat." /></form:option>
                <form:option value="true" ><fmt:message key="definitenentitat.true" /></form:option>
                <form:option value="false" ><fmt:message key="definitenentitat.false" /></form:option>
              </form:select>
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.CHECKCANVIATDOCFIRMAT)}" >
                <fmt:message key="definitenentitat.${__theForm.usuariAplicacioConfiguracio.checkCanviatDocFirmat}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.VALIDARFIRMA)}">
        <tr id="usuariAplicacioConfiguracio_validarFirma_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.VALIDARFIRMA])?'usuariAplicacioConfiguracio.validarFirma':__theForm.labels[UsuariAplicacioConfiguracioFields.VALIDARFIRMA]}" />
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.VALIDARFIRMA]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.VALIDARFIRMA]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.VALIDARFIRMA)}" >
              <form:select cssClass="input-medium" onchange="if(typeof onChangeValidarFirma == 'function') {  onChangeValidarFirma(this); };"  path="usuariAplicacioConfiguracio.validarFirma">
                <form:option value=""><fmt:message key="definitenentitat." /></form:option>
                <form:option value="true" ><fmt:message key="definitenentitat.true" /></form:option>
                <form:option value="false" ><fmt:message key="definitenentitat.false" /></form:option>
              </form:select>
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.VALIDARFIRMA)}" >
                <fmt:message key="definitenentitat.${__theForm.usuariAplicacioConfiguracio.validarFirma}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioConfiguracioFields.VALIDARCERTIFICAT)}">
        <tr id="usuariAplicacioConfiguracio_validarCertificat_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioConfiguracioFields.VALIDARCERTIFICAT])?'usuariAplicacioConfiguracio.validarCertificat':__theForm.labels[UsuariAplicacioConfiguracioFields.VALIDARCERTIFICAT]}" />
              <c:if test="${not empty __theForm.help[UsuariAplicacioConfiguracioFields.VALIDARCERTIFICAT]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioConfiguracioFields.VALIDARCERTIFICAT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.VALIDARCERTIFICAT)}" >
              <form:select cssClass="input-medium" onchange="if(typeof onChangeValidarCertificat == 'function') {  onChangeValidarCertificat(this); };"  path="usuariAplicacioConfiguracio.validarCertificat">
                <form:option value=""><fmt:message key="definitenentitat." /></form:option>
                <form:option value="true" ><fmt:message key="definitenentitat.true" /></form:option>
                <form:option value="false" ><fmt:message key="definitenentitat.false" /></form:option>
              </form:select>
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioConfiguracioFields.VALIDARCERTIFICAT)}" >
                <fmt:message key="definitenentitat.${__theForm.usuariAplicacioConfiguracio.validarCertificat}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
