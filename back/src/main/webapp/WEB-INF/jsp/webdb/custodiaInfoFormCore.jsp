<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="CustodiaInfoFields" className="es.caib.portafib.model.fields.CustodiaInfoFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,CustodiaInfoFields.NOMPLANTILLA)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[CustodiaInfoFields.NOMPLANTILLA])?'custodiaInfo.nomPlantilla':__theForm.labels[CustodiaInfoFields.NOMPLANTILLA]}" />
              <c:if test="${not empty __theForm.help[CustodiaInfoFields.NOMPLANTILLA]}">
              <i class="icon-info-sign" title="${__theForm.help[CustodiaInfoFields.NOMPLANTILLA]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="custodiaInfo.nomPlantilla" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.NOMPLANTILLA)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.NOMPLANTILLA)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="255" path="custodiaInfo.nomPlantilla"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CustodiaInfoFields.CUSTODIADOCUMENTID)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[CustodiaInfoFields.CUSTODIADOCUMENTID])?'custodiaInfo.custodiaDocumentID':__theForm.labels[CustodiaInfoFields.CUSTODIADOCUMENTID]}" />
              <c:if test="${not empty __theForm.help[CustodiaInfoFields.CUSTODIADOCUMENTID]}">
              <i class="icon-info-sign" title="${__theForm.help[CustodiaInfoFields.CUSTODIADOCUMENTID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="custodiaInfo.custodiaDocumentID" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.CUSTODIADOCUMENTID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.CUSTODIADOCUMENTID)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="255" path="custodiaInfo.custodiaDocumentID"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CustodiaInfoFields.PLUGINID)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[CustodiaInfoFields.PLUGINID])?'custodiaInfo.pluginID':__theForm.labels[CustodiaInfoFields.PLUGINID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[CustodiaInfoFields.PLUGINID]}">
              <i class="icon-info-sign" title="${__theForm.help[CustodiaInfoFields.PLUGINID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="custodiaInfo.pluginID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.PLUGINID)}" >
          <form:hidden path="custodiaInfo.pluginID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.custodiaInfo.pluginID,__theForm.listOfPluginForPluginID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.PLUGINID)}" >
          <form:select id="custodiaInfo_pluginID"  onchange="if(typeof onChangePluginID == 'function') {  onChangePluginID(this); };"  cssClass="input-xxlarge" path="custodiaInfo.pluginID">
            <c:forEach items="${__theForm.listOfPluginForPluginID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CustodiaInfoFields.CUSTODIAPLUGINPARAMETERS)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[CustodiaInfoFields.CUSTODIAPLUGINPARAMETERS])?'custodiaInfo.custodiaPluginParameters':__theForm.labels[CustodiaInfoFields.CUSTODIAPLUGINPARAMETERS]}" />
              <c:if test="${not empty __theForm.help[CustodiaInfoFields.CUSTODIAPLUGINPARAMETERS]}">
              <i class="icon-info-sign" title="${__theForm.help[CustodiaInfoFields.CUSTODIAPLUGINPARAMETERS]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="custodiaInfo.custodiaPluginParameters" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.CUSTODIAPLUGINPARAMETERS)? 'true' : 'false'}" path="custodiaInfo.custodiaPluginParameters"  />
              <div class="btn-group" style="vertical-align: top;">
              <button class="btn btn-mini dropdown-toggle" data-toggle="dropdown">&nbsp;<span class="caret"></span></button>
              <ul class="dropdown-menu">
                <li><a href="#" onclick="javascript:var ta=document.getElementById('custodiaInfo.custodiaPluginParameters'); ta.wrap='off';" >No Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('custodiaInfo.custodiaPluginParameters'); ta.wrap='soft';">Soft Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('custodiaInfo.custodiaPluginParameters'); ta.wrap='hard';">Hard Wrap</a></li>
              </ul>
              </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CustodiaInfoFields.CUSTODIAR)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[CustodiaInfoFields.CUSTODIAR])?'custodiaInfo.custodiar':__theForm.labels[CustodiaInfoFields.CUSTODIAR]}" />
              <c:if test="${not empty __theForm.help[CustodiaInfoFields.CUSTODIAR]}">
              <i class="icon-info-sign" title="${__theForm.help[CustodiaInfoFields.CUSTODIAR]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.CUSTODIAR)}" >
              <form:errors path="custodiaInfo.custodiar" cssClass="errorField alert alert-error" />
              <form:checkbox onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.CUSTODIAR)? 'false' : 'true'}" path="custodiaInfo.custodiar" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.CUSTODIAR)}" >
                <fmt:message key="genapp.checkbox.${__theForm.custodiaInfo.custodiar}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CustodiaInfoFields.URLFITXERCUSTODIAT)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[CustodiaInfoFields.URLFITXERCUSTODIAT])?'custodiaInfo.urlFitxerCustodiat':__theForm.labels[CustodiaInfoFields.URLFITXERCUSTODIAT]}" />
              <c:if test="${not empty __theForm.help[CustodiaInfoFields.URLFITXERCUSTODIAT]}">
              <i class="icon-info-sign" title="${__theForm.help[CustodiaInfoFields.URLFITXERCUSTODIAT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
           <c:if test="${gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.URLFITXERCUSTODIAT)}">

             <c:if test="${ not empty __theForm.custodiaInfo.urlFitxerCustodiat}">
               <a href="${__theForm.custodiaInfo.urlFitxerCustodiat}" target="_blank">${__theForm.custodiaInfo.urlFitxerCustodiat}</a>

             </c:if>
           </c:if>

           <c:if test="${not (gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.URLFITXERCUSTODIAT))}">

            <form:errors path="custodiaInfo.urlFitxerCustodiat" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.URLFITXERCUSTODIAT)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.URLFITXERCUSTODIAT)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="500" path="custodiaInfo.urlFitxerCustodiat"   />

           </c:if>

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CustodiaInfoFields.PAGINES)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[CustodiaInfoFields.PAGINES])?'custodiaInfo.pagines':__theForm.labels[CustodiaInfoFields.PAGINES]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[CustodiaInfoFields.PAGINES]}">
              <i class="icon-info-sign" title="${__theForm.help[CustodiaInfoFields.PAGINES]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="custodiaInfo.pagines" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.PAGINES)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.PAGINES)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="255" path="custodiaInfo.pagines"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CustodiaInfoFields.MISSATGE)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[CustodiaInfoFields.MISSATGE])?'custodiaInfo.missatge':__theForm.labels[CustodiaInfoFields.MISSATGE]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[CustodiaInfoFields.MISSATGE]}">
              <i class="icon-info-sign" title="${__theForm.help[CustodiaInfoFields.MISSATGE]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="custodiaInfo.missatge" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.MISSATGE)? 'true' : 'false'}" path="custodiaInfo.missatge"  />
              <div class="btn-group" style="vertical-align: top;">
              <button class="btn btn-mini dropdown-toggle" data-toggle="dropdown">&nbsp;<span class="caret"></span></button>
              <ul class="dropdown-menu">
                <li><a href="#" onclick="javascript:var ta=document.getElementById('custodiaInfo.missatge'); ta.wrap='off';" >No Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('custodiaInfo.missatge'); ta.wrap='soft';">Soft Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('custodiaInfo.missatge'); ta.wrap='hard';">Hard Wrap</a></li>
              </ul>
              </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CustodiaInfoFields.MISSATGEPOSICIOPAGINAID)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[CustodiaInfoFields.MISSATGEPOSICIOPAGINAID])?'custodiaInfo.missatgePosicioPaginaID':__theForm.labels[CustodiaInfoFields.MISSATGEPOSICIOPAGINAID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[CustodiaInfoFields.MISSATGEPOSICIOPAGINAID]}">
              <i class="icon-info-sign" title="${__theForm.help[CustodiaInfoFields.MISSATGEPOSICIOPAGINAID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="custodiaInfo.missatgePosicioPaginaID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.MISSATGEPOSICIOPAGINAID)}" >
          <form:hidden path="custodiaInfo.missatgePosicioPaginaID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.custodiaInfo.missatgePosicioPaginaID,__theForm.listOfPosicioPaginaForMissatgePosicioPaginaID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.MISSATGEPOSICIOPAGINAID)}" >
          <form:select id="custodiaInfo_missatgePosicioPaginaID"  onchange="if(typeof onChangeMissatgePosicioPaginaID == 'function') {  onChangeMissatgePosicioPaginaID(this); };"  cssClass="input-xxlarge" path="custodiaInfo.missatgePosicioPaginaID">
            <c:forEach items="${__theForm.listOfPosicioPaginaForMissatgePosicioPaginaID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CustodiaInfoFields.CODIBARRESID)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[CustodiaInfoFields.CODIBARRESID])?'custodiaInfo.codiBarresID':__theForm.labels[CustodiaInfoFields.CODIBARRESID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[CustodiaInfoFields.CODIBARRESID]}">
              <i class="icon-info-sign" title="${__theForm.help[CustodiaInfoFields.CODIBARRESID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="custodiaInfo.codiBarresID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.CODIBARRESID)}" >
          <form:hidden path="custodiaInfo.codiBarresID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.custodiaInfo.codiBarresID,__theForm.listOfCodiBarresForCodiBarresID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.CODIBARRESID)}" >
          <form:select id="custodiaInfo_codiBarresID"  onchange="if(typeof onChangeCodiBarresID == 'function') {  onChangeCodiBarresID(this); };"  cssClass="input-xxlarge" path="custodiaInfo.codiBarresID">
            <c:forEach items="${__theForm.listOfCodiBarresForCodiBarresID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CustodiaInfoFields.CODIBARRESPOSICIOPAGINAID)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[CustodiaInfoFields.CODIBARRESPOSICIOPAGINAID])?'custodiaInfo.codiBarresPosicioPaginaID':__theForm.labels[CustodiaInfoFields.CODIBARRESPOSICIOPAGINAID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[CustodiaInfoFields.CODIBARRESPOSICIOPAGINAID]}">
              <i class="icon-info-sign" title="${__theForm.help[CustodiaInfoFields.CODIBARRESPOSICIOPAGINAID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="custodiaInfo.codiBarresPosicioPaginaID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.CODIBARRESPOSICIOPAGINAID)}" >
          <form:hidden path="custodiaInfo.codiBarresPosicioPaginaID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.custodiaInfo.codiBarresPosicioPaginaID,__theForm.listOfPosicioPaginaForCodiBarresPosicioPaginaID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.CODIBARRESPOSICIOPAGINAID)}" >
          <form:select id="custodiaInfo_codiBarresPosicioPaginaID"  onchange="if(typeof onChangeCodiBarresPosicioPaginaID == 'function') {  onChangeCodiBarresPosicioPaginaID(this); };"  cssClass="input-xxlarge" path="custodiaInfo.codiBarresPosicioPaginaID">
            <c:forEach items="${__theForm.listOfPosicioPaginaForCodiBarresPosicioPaginaID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CustodiaInfoFields.CODIBARRESTEXT)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[CustodiaInfoFields.CODIBARRESTEXT])?'custodiaInfo.codiBarresText':__theForm.labels[CustodiaInfoFields.CODIBARRESTEXT]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[CustodiaInfoFields.CODIBARRESTEXT]}">
              <i class="icon-info-sign" title="${__theForm.help[CustodiaInfoFields.CODIBARRESTEXT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="custodiaInfo.codiBarresText" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.CODIBARRESTEXT)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.CODIBARRESTEXT)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="255" path="custodiaInfo.codiBarresText"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CustodiaInfoFields.USUARIENTITATID)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[CustodiaInfoFields.USUARIENTITATID])?'custodiaInfo.usuariEntitatID':__theForm.labels[CustodiaInfoFields.USUARIENTITATID]}" />
              <c:if test="${not empty __theForm.help[CustodiaInfoFields.USUARIENTITATID]}">
              <i class="icon-info-sign" title="${__theForm.help[CustodiaInfoFields.USUARIENTITATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="custodiaInfo.usuariEntitatID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.USUARIENTITATID)}" >
          <form:hidden path="custodiaInfo.usuariEntitatID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.custodiaInfo.usuariEntitatID,__theForm.listOfUsuariEntitatForUsuariEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.USUARIENTITATID)}" >
          <form:select id="custodiaInfo_usuariEntitatID"  onchange="if(typeof onChangeUsuariEntitatID == 'function') {  onChangeUsuariEntitatID(this); };"  cssClass="input-xxlarge" path="custodiaInfo.usuariEntitatID">
          <%-- El camp pot ser null, per la qual cosa afegim una entrada buida --%>
          <form:option value="" ></form:option>
            <c:forEach items="${__theForm.listOfUsuariEntitatForUsuariEntitatID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CustodiaInfoFields.USUARIAPLICACIOID)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[CustodiaInfoFields.USUARIAPLICACIOID])?'custodiaInfo.usuariAplicacioID':__theForm.labels[CustodiaInfoFields.USUARIAPLICACIOID]}" />
              <c:if test="${not empty __theForm.help[CustodiaInfoFields.USUARIAPLICACIOID]}">
              <i class="icon-info-sign" title="${__theForm.help[CustodiaInfoFields.USUARIAPLICACIOID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="custodiaInfo.usuariAplicacioID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.USUARIAPLICACIOID)}" >
          <form:hidden path="custodiaInfo.usuariAplicacioID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.custodiaInfo.usuariAplicacioID,__theForm.listOfUsuariAplicacioForUsuariAplicacioID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.USUARIAPLICACIOID)}" >
          <form:select id="custodiaInfo_usuariAplicacioID"  onchange="if(typeof onChangeUsuariAplicacioID == 'function') {  onChangeUsuariAplicacioID(this); };"  cssClass="input-xxlarge" path="custodiaInfo.usuariAplicacioID">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CustodiaInfoFields.ENTITATID)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[CustodiaInfoFields.ENTITATID])?'custodiaInfo.entitatID':__theForm.labels[CustodiaInfoFields.ENTITATID]}" />
              <c:if test="${not empty __theForm.help[CustodiaInfoFields.ENTITATID]}">
              <i class="icon-info-sign" title="${__theForm.help[CustodiaInfoFields.ENTITATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="custodiaInfo.entitatID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.ENTITATID)}" >
          <form:hidden path="custodiaInfo.entitatID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.custodiaInfo.entitatID,__theForm.listOfEntitatForEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.ENTITATID)}" >
          <form:select id="custodiaInfo_entitatID"  onchange="if(typeof onChangeEntitatID == 'function') {  onChangeEntitatID(this); };"  cssClass="input-xxlarge" path="custodiaInfo.entitatID">
          <%-- El camp pot ser null, per la qual cosa afegim una entrada buida --%>
          <form:option value="" ></form:option>
            <c:forEach items="${__theForm.listOfEntitatForEntitatID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CustodiaInfoFields.TITOLPETICIO)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[CustodiaInfoFields.TITOLPETICIO])?'custodiaInfo.titolPeticio':__theForm.labels[CustodiaInfoFields.TITOLPETICIO]}" />
              <c:if test="${not empty __theForm.help[CustodiaInfoFields.TITOLPETICIO]}">
              <i class="icon-info-sign" title="${__theForm.help[CustodiaInfoFields.TITOLPETICIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="custodiaInfo.titolPeticio" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.TITOLPETICIO)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.TITOLPETICIO)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="255" path="custodiaInfo.titolPeticio"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CustodiaInfoFields.DATACUSTODIA)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[CustodiaInfoFields.DATACUSTODIA])?'custodiaInfo.dataCustodia':__theForm.labels[CustodiaInfoFields.DATACUSTODIA]}" />
              <c:if test="${not empty __theForm.help[CustodiaInfoFields.DATACUSTODIA]}">
              <i class="icon-info-sign" title="${__theForm.help[CustodiaInfoFields.DATACUSTODIA]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="custodiaInfo.dataCustodia" cssClass="errorField alert alert-error" />
              <div id="dataCustodia" class="input-append">
                <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.DATACUSTODIA)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.DATACUSTODIA)? 'input-medium uneditable-input' : 'input-medium'}"  path="custodiaInfo.dataCustodia" />
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.DATACUSTODIA)}" >
                <span class="add-on">
                  <i data-time-icon="icon-time" data-date-icon="icon-calendar">
                  </i>
                </span>
              </c:if>
              </div>
              <script type="text/javascript">                
                $(function() {
                  $('#dataCustodia').datetimepicker({
                    language: '${lang}',
                    pick12HourFormat: <c:out value="${fn:contains(gen:getDateTimePattern(), 'a')?'true' : 'false'}"/>,
                    format:  '${gen:getJSDateTimePattern()}',
                    pickTime: true,
                    weekStart: ${gen:getFirstDayOfTheWeek()}
                  });
                });
              </script>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CustodiaInfoFields.EDITABLE)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[CustodiaInfoFields.EDITABLE])?'custodiaInfo.editable':__theForm.labels[CustodiaInfoFields.EDITABLE]}" />
              <c:if test="${not empty __theForm.help[CustodiaInfoFields.EDITABLE]}">
              <i class="icon-info-sign" title="${__theForm.help[CustodiaInfoFields.EDITABLE]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.EDITABLE)}" >
              <form:errors path="custodiaInfo.editable" cssClass="errorField alert alert-error" />
              <form:checkbox onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.EDITABLE)? 'false' : 'true'}" path="custodiaInfo.editable" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.EDITABLE)}" >
                <fmt:message key="genapp.checkbox.${__theForm.custodiaInfo.editable}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
