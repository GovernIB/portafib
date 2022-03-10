<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="CustodiaInfoFields" className="es.caib.portafib.model.fields.CustodiaInfoFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,CustodiaInfoFields.NOMPLANTILLA)}">
        <tr id="custodiaInfo_nomPlantilla_rowid">
          <td id="custodiaInfo_nomPlantilla_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[CustodiaInfoFields.NOMPLANTILLA])?'custodiaInfo.nomPlantilla':__theForm.labels[CustodiaInfoFields.NOMPLANTILLA]}" />
             </label>
              <c:if test="${not empty __theForm.help[CustodiaInfoFields.NOMPLANTILLA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[CustodiaInfoFields.NOMPLANTILLA]}" ></i>
              </c:if>
            </td>
          <td id="custodiaInfo_nomPlantilla_columnvalueid">
            <form:errors path="custodiaInfo.nomPlantilla" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.NOMPLANTILLA)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.NOMPLANTILLA)? ' uneditable-input' : ''}"  style="" maxlength="255" path="custodiaInfo.nomPlantilla"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CustodiaInfoFields.CUSTODIADOCUMENTID)}">
        <tr id="custodiaInfo_custodiaDocumentID_rowid">
          <td id="custodiaInfo_custodiaDocumentID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[CustodiaInfoFields.CUSTODIADOCUMENTID])?'custodiaInfo.custodiaDocumentID':__theForm.labels[CustodiaInfoFields.CUSTODIADOCUMENTID]}" />
             </label>
              <c:if test="${not empty __theForm.help[CustodiaInfoFields.CUSTODIADOCUMENTID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[CustodiaInfoFields.CUSTODIADOCUMENTID]}" ></i>
              </c:if>
            </td>
          <td id="custodiaInfo_custodiaDocumentID_columnvalueid">
            <form:errors path="custodiaInfo.custodiaDocumentID" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.CUSTODIADOCUMENTID)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.CUSTODIADOCUMENTID)? ' uneditable-input' : ''}"  style="" maxlength="255" path="custodiaInfo.custodiaDocumentID"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CustodiaInfoFields.PLUGINID)}">
        <tr id="custodiaInfo_pluginID_rowid">
          <td id="custodiaInfo_pluginID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[CustodiaInfoFields.PLUGINID])?'custodiaInfo.pluginID':__theForm.labels[CustodiaInfoFields.PLUGINID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[CustodiaInfoFields.PLUGINID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[CustodiaInfoFields.PLUGINID]}" ></i>
              </c:if>
            </td>
          <td id="custodiaInfo_pluginID_columnvalueid">
          <form:errors path="custodiaInfo.pluginID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.PLUGINID)}" >
          <form:hidden path="custodiaInfo.pluginID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.custodiaInfo.pluginID,__theForm.listOfPluginForPluginID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.PLUGINID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="custodiaInfo_pluginID"  onchange="if(typeof onChangePluginID == 'function') {  onChangePluginID(this); };"  cssClass="form-control col-md-9-optional" path="custodiaInfo.pluginID">
            <c:forEach items="${__theForm.listOfPluginForPluginID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CustodiaInfoFields.CUSTODIAPLUGINPARAMETERS)}">
        <tr id="custodiaInfo_custodiaPluginParameters_rowid">
          <td id="custodiaInfo_custodiaPluginParameters_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[CustodiaInfoFields.CUSTODIAPLUGINPARAMETERS])?'custodiaInfo.custodiaPluginParameters':__theForm.labels[CustodiaInfoFields.CUSTODIAPLUGINPARAMETERS]}" />
             </label>
              <c:if test="${not empty __theForm.help[CustodiaInfoFields.CUSTODIAPLUGINPARAMETERS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[CustodiaInfoFields.CUSTODIAPLUGINPARAMETERS]}" ></i>
              </c:if>
            </td>
          <td id="custodiaInfo_custodiaPluginParameters_columnvalueid">
              <form:errors path="custodiaInfo.custodiaPluginParameters" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.CUSTODIAPLUGINPARAMETERS)? 'true' : 'false'}" path="custodiaInfo.custodiaPluginParameters"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_custodiaPluginParameters" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_custodiaPluginParameters" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('custodiaInfo.custodiaPluginParameters'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('custodiaInfo.custodiaPluginParameters'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('custodiaInfo.custodiaPluginParameters'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_custodiaPluginParameters').on('click', function(){
					var valor = ($('#dropdownMenuContainer_custodiaPluginParameters').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_custodiaPluginParameters').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CustodiaInfoFields.CUSTODIAR)}">
        <tr id="custodiaInfo_custodiar_rowid">
          <td id="custodiaInfo_custodiar_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[CustodiaInfoFields.CUSTODIAR])?'custodiaInfo.custodiar':__theForm.labels[CustodiaInfoFields.CUSTODIAR]}" />
             </label>
              <c:if test="${not empty __theForm.help[CustodiaInfoFields.CUSTODIAR]}">
              <i class="fas fa-info-circle" title="${__theForm.help[CustodiaInfoFields.CUSTODIAR]}" ></i>
              </c:if>
            </td>
          <td id="custodiaInfo_custodiar_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.CUSTODIAR)}" >
              <form:errors path="custodiaInfo.custodiar" cssClass="errorField alert alert-danger" />
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.CUSTODIAR)? 'false' : 'true'}" path="custodiaInfo.custodiar" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.CUSTODIAR)}" >
                <fmt:message key="genapp.checkbox.${__theForm.custodiaInfo.custodiar}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CustodiaInfoFields.PAGINES)}">
        <tr id="custodiaInfo_pagines_rowid">
          <td id="custodiaInfo_pagines_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[CustodiaInfoFields.PAGINES])?'custodiaInfo.pagines':__theForm.labels[CustodiaInfoFields.PAGINES]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[CustodiaInfoFields.PAGINES]}">
              <i class="fas fa-info-circle" title="${__theForm.help[CustodiaInfoFields.PAGINES]}" ></i>
              </c:if>
            </td>
          <td id="custodiaInfo_pagines_columnvalueid">
            <form:errors path="custodiaInfo.pagines" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.PAGINES)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.PAGINES)? ' uneditable-input' : ''}"  style="" maxlength="255" path="custodiaInfo.pagines"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CustodiaInfoFields.MISSATGE)}">
        <tr id="custodiaInfo_missatge_rowid">
          <td id="custodiaInfo_missatge_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[CustodiaInfoFields.MISSATGE])?'custodiaInfo.missatge':__theForm.labels[CustodiaInfoFields.MISSATGE]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[CustodiaInfoFields.MISSATGE]}">
              <i class="fas fa-info-circle" title="${__theForm.help[CustodiaInfoFields.MISSATGE]}" ></i>
              </c:if>
            </td>
          <td id="custodiaInfo_missatge_columnvalueid">
              <form:errors path="custodiaInfo.missatge" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.MISSATGE)? 'true' : 'false'}" path="custodiaInfo.missatge"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_missatge" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_missatge" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('custodiaInfo.missatge'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('custodiaInfo.missatge'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('custodiaInfo.missatge'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_missatge').on('click', function(){
					var valor = ($('#dropdownMenuContainer_missatge').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_missatge').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CustodiaInfoFields.MISSATGEPOSICIOPAGINAID)}">
        <tr id="custodiaInfo_missatgePosicioPaginaID_rowid">
          <td id="custodiaInfo_missatgePosicioPaginaID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[CustodiaInfoFields.MISSATGEPOSICIOPAGINAID])?'custodiaInfo.missatgePosicioPaginaID':__theForm.labels[CustodiaInfoFields.MISSATGEPOSICIOPAGINAID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[CustodiaInfoFields.MISSATGEPOSICIOPAGINAID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[CustodiaInfoFields.MISSATGEPOSICIOPAGINAID]}" ></i>
              </c:if>
            </td>
          <td id="custodiaInfo_missatgePosicioPaginaID_columnvalueid">
          <form:errors path="custodiaInfo.missatgePosicioPaginaID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.MISSATGEPOSICIOPAGINAID)}" >
          <form:hidden path="custodiaInfo.missatgePosicioPaginaID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.custodiaInfo.missatgePosicioPaginaID,__theForm.listOfValuesForMissatgePosicioPaginaID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.MISSATGEPOSICIOPAGINAID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="custodiaInfo_missatgePosicioPaginaID"  onchange="if(typeof onChangeMissatgePosicioPaginaID == 'function') {  onChangeMissatgePosicioPaginaID(this); };"  cssClass="form-control col-md-9-optional" path="custodiaInfo.missatgePosicioPaginaID">
            <c:forEach items="${__theForm.listOfValuesForMissatgePosicioPaginaID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CustodiaInfoFields.CODIBARRESID)}">
        <tr id="custodiaInfo_codiBarresID_rowid">
          <td id="custodiaInfo_codiBarresID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[CustodiaInfoFields.CODIBARRESID])?'custodiaInfo.codiBarresID':__theForm.labels[CustodiaInfoFields.CODIBARRESID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[CustodiaInfoFields.CODIBARRESID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[CustodiaInfoFields.CODIBARRESID]}" ></i>
              </c:if>
            </td>
          <td id="custodiaInfo_codiBarresID_columnvalueid">
          <form:errors path="custodiaInfo.codiBarresID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.CODIBARRESID)}" >
          <form:hidden path="custodiaInfo.codiBarresID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.custodiaInfo.codiBarresID,__theForm.listOfCodiBarresForCodiBarresID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.CODIBARRESID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="custodiaInfo_codiBarresID"  onchange="if(typeof onChangeCodiBarresID == 'function') {  onChangeCodiBarresID(this); };"  cssClass="form-control col-md-9-optional" path="custodiaInfo.codiBarresID">
            <c:forEach items="${__theForm.listOfCodiBarresForCodiBarresID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CustodiaInfoFields.CODIBARRESPOSICIOPAGINAID)}">
        <tr id="custodiaInfo_codiBarresPosicioPaginaID_rowid">
          <td id="custodiaInfo_codiBarresPosicioPaginaID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[CustodiaInfoFields.CODIBARRESPOSICIOPAGINAID])?'custodiaInfo.codiBarresPosicioPaginaID':__theForm.labels[CustodiaInfoFields.CODIBARRESPOSICIOPAGINAID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[CustodiaInfoFields.CODIBARRESPOSICIOPAGINAID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[CustodiaInfoFields.CODIBARRESPOSICIOPAGINAID]}" ></i>
              </c:if>
            </td>
          <td id="custodiaInfo_codiBarresPosicioPaginaID_columnvalueid">
          <form:errors path="custodiaInfo.codiBarresPosicioPaginaID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.CODIBARRESPOSICIOPAGINAID)}" >
          <form:hidden path="custodiaInfo.codiBarresPosicioPaginaID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.custodiaInfo.codiBarresPosicioPaginaID,__theForm.listOfValuesForCodiBarresPosicioPaginaID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.CODIBARRESPOSICIOPAGINAID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="custodiaInfo_codiBarresPosicioPaginaID"  onchange="if(typeof onChangeCodiBarresPosicioPaginaID == 'function') {  onChangeCodiBarresPosicioPaginaID(this); };"  cssClass="form-control col-md-9-optional" path="custodiaInfo.codiBarresPosicioPaginaID">
            <c:forEach items="${__theForm.listOfValuesForCodiBarresPosicioPaginaID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CustodiaInfoFields.CODIBARRESTEXT)}">
        <tr id="custodiaInfo_codiBarresText_rowid">
          <td id="custodiaInfo_codiBarresText_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[CustodiaInfoFields.CODIBARRESTEXT])?'custodiaInfo.codiBarresText':__theForm.labels[CustodiaInfoFields.CODIBARRESTEXT]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[CustodiaInfoFields.CODIBARRESTEXT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[CustodiaInfoFields.CODIBARRESTEXT]}" ></i>
              </c:if>
            </td>
          <td id="custodiaInfo_codiBarresText_columnvalueid">
            <form:errors path="custodiaInfo.codiBarresText" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.CODIBARRESTEXT)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.CODIBARRESTEXT)? ' uneditable-input' : ''}"  style="" maxlength="255" path="custodiaInfo.codiBarresText"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CustodiaInfoFields.USUARIENTITATID)}">
        <tr id="custodiaInfo_usuariEntitatID_rowid">
          <td id="custodiaInfo_usuariEntitatID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[CustodiaInfoFields.USUARIENTITATID])?'custodiaInfo.usuariEntitatID':__theForm.labels[CustodiaInfoFields.USUARIENTITATID]}" />
             </label>
              <c:if test="${not empty __theForm.help[CustodiaInfoFields.USUARIENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[CustodiaInfoFields.USUARIENTITATID]}" ></i>
              </c:if>
            </td>
          <td id="custodiaInfo_usuariEntitatID_columnvalueid">
          <form:errors path="custodiaInfo.usuariEntitatID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.USUARIENTITATID)}" >
          <form:hidden path="custodiaInfo.usuariEntitatID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.custodiaInfo.usuariEntitatID,__theForm.listOfUsuariEntitatForUsuariEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.USUARIENTITATID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="custodiaInfo_usuariEntitatID"  onchange="if(typeof onChangeUsuariEntitatID == 'function') {  onChangeUsuariEntitatID(this); };"  cssClass="form-control col-md-9-optional" path="custodiaInfo.usuariEntitatID">
            <c:forEach items="${__theForm.listOfUsuariEntitatForUsuariEntitatID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.custodiaInfo.usuariEntitatID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.custodiaInfo.usuariEntitatID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CustodiaInfoFields.USUARIAPLICACIOID)}">
        <tr id="custodiaInfo_usuariAplicacioID_rowid">
          <td id="custodiaInfo_usuariAplicacioID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[CustodiaInfoFields.USUARIAPLICACIOID])?'custodiaInfo.usuariAplicacioID':__theForm.labels[CustodiaInfoFields.USUARIAPLICACIOID]}" />
             </label>
              <c:if test="${not empty __theForm.help[CustodiaInfoFields.USUARIAPLICACIOID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[CustodiaInfoFields.USUARIAPLICACIOID]}" ></i>
              </c:if>
            </td>
          <td id="custodiaInfo_usuariAplicacioID_columnvalueid">
          <form:errors path="custodiaInfo.usuariAplicacioID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.USUARIAPLICACIOID)}" >
          <form:hidden path="custodiaInfo.usuariAplicacioID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.custodiaInfo.usuariAplicacioID,__theForm.listOfUsuariAplicacioForUsuariAplicacioID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.USUARIAPLICACIOID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="custodiaInfo_usuariAplicacioID"  onchange="if(typeof onChangeUsuariAplicacioID == 'function') {  onChangeUsuariAplicacioID(this); };"  cssClass="form-control col-md-9-optional" path="custodiaInfo.usuariAplicacioID">
            <c:forEach items="${__theForm.listOfUsuariAplicacioForUsuariAplicacioID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.custodiaInfo.usuariAplicacioID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.custodiaInfo.usuariAplicacioID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CustodiaInfoFields.ENTITATID)}">
        <tr id="custodiaInfo_entitatID_rowid">
          <td id="custodiaInfo_entitatID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[CustodiaInfoFields.ENTITATID])?'custodiaInfo.entitatID':__theForm.labels[CustodiaInfoFields.ENTITATID]}" />
             </label>
              <c:if test="${not empty __theForm.help[CustodiaInfoFields.ENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[CustodiaInfoFields.ENTITATID]}" ></i>
              </c:if>
            </td>
          <td id="custodiaInfo_entitatID_columnvalueid">
          <form:errors path="custodiaInfo.entitatID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.ENTITATID)}" >
          <form:hidden path="custodiaInfo.entitatID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.custodiaInfo.entitatID,__theForm.listOfEntitatForEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.ENTITATID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="custodiaInfo_entitatID"  onchange="if(typeof onChangeEntitatID == 'function') {  onChangeEntitatID(this); };"  cssClass="form-control col-md-9-optional" path="custodiaInfo.entitatID">
            <c:forEach items="${__theForm.listOfEntitatForEntitatID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.custodiaInfo.entitatID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.custodiaInfo.entitatID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CustodiaInfoFields.TITOLPETICIO)}">
        <tr id="custodiaInfo_titolPeticio_rowid">
          <td id="custodiaInfo_titolPeticio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[CustodiaInfoFields.TITOLPETICIO])?'custodiaInfo.titolPeticio':__theForm.labels[CustodiaInfoFields.TITOLPETICIO]}" />
             </label>
              <c:if test="${not empty __theForm.help[CustodiaInfoFields.TITOLPETICIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[CustodiaInfoFields.TITOLPETICIO]}" ></i>
              </c:if>
            </td>
          <td id="custodiaInfo_titolPeticio_columnvalueid">
            <form:errors path="custodiaInfo.titolPeticio" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.TITOLPETICIO)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.TITOLPETICIO)? ' uneditable-input' : ''}"  style="" maxlength="255" path="custodiaInfo.titolPeticio"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CustodiaInfoFields.DATACUSTODIA)}">
        <tr id="custodiaInfo_dataCustodia_rowid">
          <td id="custodiaInfo_dataCustodia_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[CustodiaInfoFields.DATACUSTODIA])?'custodiaInfo.dataCustodia':__theForm.labels[CustodiaInfoFields.DATACUSTODIA]}" />
             </label>
              <c:if test="${not empty __theForm.help[CustodiaInfoFields.DATACUSTODIA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[CustodiaInfoFields.DATACUSTODIA]}" ></i>
              </c:if>
            </td>
          <td id="custodiaInfo_dataCustodia_columnvalueid">
    <form:errors path="custodiaInfo.dataCustodia" cssClass="errorField alert alert-danger" />
            <div class="form-group">
                <div class="input-group date" id="custodiaInfo_dataCustodia" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.DATACUSTODIA)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#custodiaInfo_dataCustodia" path="custodiaInfo.dataCustodia" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.DATACUSTODIA)}" >
                    <div class="input-group-append"  data-target="#custodiaInfo_dataCustodia"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#custodiaInfo_dataCustodia').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
        </script>           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CustodiaInfoFields.EDITABLE)}">
        <tr id="custodiaInfo_editable_rowid">
          <td id="custodiaInfo_editable_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[CustodiaInfoFields.EDITABLE])?'custodiaInfo.editable':__theForm.labels[CustodiaInfoFields.EDITABLE]}" />
             </label>
              <c:if test="${not empty __theForm.help[CustodiaInfoFields.EDITABLE]}">
              <i class="fas fa-info-circle" title="${__theForm.help[CustodiaInfoFields.EDITABLE]}" ></i>
              </c:if>
            </td>
          <td id="custodiaInfo_editable_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.EDITABLE)}" >
              <form:errors path="custodiaInfo.editable" cssClass="errorField alert alert-danger" />
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.EDITABLE)? 'false' : 'true'}" path="custodiaInfo.editable" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.EDITABLE)}" >
                <fmt:message key="genapp.checkbox.${__theForm.custodiaInfo.editable}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CustodiaInfoFields.CSV)}">
        <tr id="custodiaInfo_csv_rowid">
          <td id="custodiaInfo_csv_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[CustodiaInfoFields.CSV])?'custodiaInfo.csv':__theForm.labels[CustodiaInfoFields.CSV]}" />
             </label>
              <c:if test="${not empty __theForm.help[CustodiaInfoFields.CSV]}">
              <i class="fas fa-info-circle" title="${__theForm.help[CustodiaInfoFields.CSV]}" ></i>
              </c:if>
            </td>
          <td id="custodiaInfo_csv_columnvalueid">
            <form:errors path="custodiaInfo.csv" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.CSV)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.CSV)? ' uneditable-input' : ''}"  style="" maxlength="500" path="custodiaInfo.csv"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CustodiaInfoFields.CSVVALIDATIONWEB)}">
        <tr id="custodiaInfo_csvValidationWeb_rowid">
          <td id="custodiaInfo_csvValidationWeb_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[CustodiaInfoFields.CSVVALIDATIONWEB])?'custodiaInfo.csvValidationWeb':__theForm.labels[CustodiaInfoFields.CSVVALIDATIONWEB]}" />
             </label>
              <c:if test="${not empty __theForm.help[CustodiaInfoFields.CSVVALIDATIONWEB]}">
              <i class="fas fa-info-circle" title="${__theForm.help[CustodiaInfoFields.CSVVALIDATIONWEB]}" ></i>
              </c:if>
            </td>
          <td id="custodiaInfo_csvValidationWeb_columnvalueid">
           <c:if test="${gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.CSVVALIDATIONWEB)}">

             <c:if test="${ not empty __theForm.custodiaInfo.csvValidationWeb}">
               <a href="${__theForm.custodiaInfo.csvValidationWeb}" target="_blank">${__theForm.custodiaInfo.csvValidationWeb}</a>

             </c:if>
           </c:if>

           <c:if test="${not (gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.CSVVALIDATIONWEB))}">

            <form:errors path="custodiaInfo.csvValidationWeb" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.CSVVALIDATIONWEB)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.CSVVALIDATIONWEB)? ' uneditable-input' : ''}"  style="" maxlength="500" path="custodiaInfo.csvValidationWeb"   />

           </c:if>

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CustodiaInfoFields.CSVGENERATIONDEFINITION)}">
        <tr id="custodiaInfo_csvGenerationDefinition_rowid">
          <td id="custodiaInfo_csvGenerationDefinition_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[CustodiaInfoFields.CSVGENERATIONDEFINITION])?'custodiaInfo.csvGenerationDefinition':__theForm.labels[CustodiaInfoFields.CSVGENERATIONDEFINITION]}" />
             </label>
              <c:if test="${not empty __theForm.help[CustodiaInfoFields.CSVGENERATIONDEFINITION]}">
              <i class="fas fa-info-circle" title="${__theForm.help[CustodiaInfoFields.CSVGENERATIONDEFINITION]}" ></i>
              </c:if>
            </td>
          <td id="custodiaInfo_csvGenerationDefinition_columnvalueid">
           <c:if test="${gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.CSVGENERATIONDEFINITION)}">

             <c:if test="${ not empty __theForm.custodiaInfo.csvGenerationDefinition}">
               <a href="${__theForm.custodiaInfo.csvGenerationDefinition}" target="_blank">${__theForm.custodiaInfo.csvGenerationDefinition}</a>

             </c:if>
           </c:if>

           <c:if test="${not (gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.CSVGENERATIONDEFINITION))}">

            <form:errors path="custodiaInfo.csvGenerationDefinition" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.CSVGENERATIONDEFINITION)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.CSVGENERATIONDEFINITION)? ' uneditable-input' : ''}"  style="" maxlength="500" path="custodiaInfo.csvGenerationDefinition"   />

           </c:if>

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CustodiaInfoFields.URLFITXERCUSTODIAT)}">
        <tr id="custodiaInfo_urlFitxerCustodiat_rowid">
          <td id="custodiaInfo_urlFitxerCustodiat_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[CustodiaInfoFields.URLFITXERCUSTODIAT])?'custodiaInfo.urlFitxerCustodiat':__theForm.labels[CustodiaInfoFields.URLFITXERCUSTODIAT]}" />
             </label>
              <c:if test="${not empty __theForm.help[CustodiaInfoFields.URLFITXERCUSTODIAT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[CustodiaInfoFields.URLFITXERCUSTODIAT]}" ></i>
              </c:if>
            </td>
          <td id="custodiaInfo_urlFitxerCustodiat_columnvalueid">
           <c:if test="${gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.URLFITXERCUSTODIAT)}">

             <c:if test="${ not empty __theForm.custodiaInfo.urlFitxerCustodiat}">
               <a href="${__theForm.custodiaInfo.urlFitxerCustodiat}" target="_blank">${__theForm.custodiaInfo.urlFitxerCustodiat}</a>

             </c:if>
           </c:if>

           <c:if test="${not (gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.URLFITXERCUSTODIAT))}">

            <form:errors path="custodiaInfo.urlFitxerCustodiat" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.URLFITXERCUSTODIAT)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.URLFITXERCUSTODIAT)? ' uneditable-input' : ''}"  style="" maxlength="500" path="custodiaInfo.urlFitxerCustodiat"   />

           </c:if>

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CustodiaInfoFields.ORIGINALFILEDIRECTURL)}">
        <tr id="custodiaInfo_originalFileDirectUrl_rowid">
          <td id="custodiaInfo_originalFileDirectUrl_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[CustodiaInfoFields.ORIGINALFILEDIRECTURL])?'custodiaInfo.originalFileDirectUrl':__theForm.labels[CustodiaInfoFields.ORIGINALFILEDIRECTURL]}" />
             </label>
              <c:if test="${not empty __theForm.help[CustodiaInfoFields.ORIGINALFILEDIRECTURL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[CustodiaInfoFields.ORIGINALFILEDIRECTURL]}" ></i>
              </c:if>
            </td>
          <td id="custodiaInfo_originalFileDirectUrl_columnvalueid">
           <c:if test="${gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.ORIGINALFILEDIRECTURL)}">

             <c:if test="${ not empty __theForm.custodiaInfo.originalFileDirectUrl}">
               <a href="${__theForm.custodiaInfo.originalFileDirectUrl}" target="_blank">${__theForm.custodiaInfo.originalFileDirectUrl}</a>

             </c:if>
           </c:if>

           <c:if test="${not (gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.ORIGINALFILEDIRECTURL))}">

            <form:errors path="custodiaInfo.originalFileDirectUrl" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.ORIGINALFILEDIRECTURL)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.ORIGINALFILEDIRECTURL)? ' uneditable-input' : ''}"  style="" maxlength="500" path="custodiaInfo.originalFileDirectUrl"   />

           </c:if>

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CustodiaInfoFields.PRINTABLEFILEDIRECTURL)}">
        <tr id="custodiaInfo_printableFileDirectUrl_rowid">
          <td id="custodiaInfo_printableFileDirectUrl_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[CustodiaInfoFields.PRINTABLEFILEDIRECTURL])?'custodiaInfo.printableFileDirectUrl':__theForm.labels[CustodiaInfoFields.PRINTABLEFILEDIRECTURL]}" />
             </label>
              <c:if test="${not empty __theForm.help[CustodiaInfoFields.PRINTABLEFILEDIRECTURL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[CustodiaInfoFields.PRINTABLEFILEDIRECTURL]}" ></i>
              </c:if>
            </td>
          <td id="custodiaInfo_printableFileDirectUrl_columnvalueid">
           <c:if test="${gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.PRINTABLEFILEDIRECTURL)}">

             <c:if test="${ not empty __theForm.custodiaInfo.printableFileDirectUrl}">
               <a href="${__theForm.custodiaInfo.printableFileDirectUrl}" target="_blank">${__theForm.custodiaInfo.printableFileDirectUrl}</a>

             </c:if>
           </c:if>

           <c:if test="${not (gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.PRINTABLEFILEDIRECTURL))}">

            <form:errors path="custodiaInfo.printableFileDirectUrl" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.PRINTABLEFILEDIRECTURL)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.PRINTABLEFILEDIRECTURL)? ' uneditable-input' : ''}"  style="" maxlength="500" path="custodiaInfo.printableFileDirectUrl"   />

           </c:if>

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CustodiaInfoFields.ENIFILEDIRECTURL)}">
        <tr id="custodiaInfo_eniFileDirectUrl_rowid">
          <td id="custodiaInfo_eniFileDirectUrl_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[CustodiaInfoFields.ENIFILEDIRECTURL])?'custodiaInfo.eniFileDirectUrl':__theForm.labels[CustodiaInfoFields.ENIFILEDIRECTURL]}" />
             </label>
              <c:if test="${not empty __theForm.help[CustodiaInfoFields.ENIFILEDIRECTURL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[CustodiaInfoFields.ENIFILEDIRECTURL]}" ></i>
              </c:if>
            </td>
          <td id="custodiaInfo_eniFileDirectUrl_columnvalueid">
           <c:if test="${gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.ENIFILEDIRECTURL)}">

             <c:if test="${ not empty __theForm.custodiaInfo.eniFileDirectUrl}">
               <a href="${__theForm.custodiaInfo.eniFileDirectUrl}" target="_blank">${__theForm.custodiaInfo.eniFileDirectUrl}</a>

             </c:if>
           </c:if>

           <c:if test="${not (gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.ENIFILEDIRECTURL))}">

            <form:errors path="custodiaInfo.eniFileDirectUrl" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.ENIFILEDIRECTURL)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.ENIFILEDIRECTURL)? ' uneditable-input' : ''}"  style="" maxlength="500" path="custodiaInfo.eniFileDirectUrl"   />

           </c:if>

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CustodiaInfoFields.EXPEDIENTARXIUID)}">
        <tr id="custodiaInfo_expedientArxiuId_rowid">
          <td id="custodiaInfo_expedientArxiuId_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[CustodiaInfoFields.EXPEDIENTARXIUID])?'custodiaInfo.expedientArxiuId':__theForm.labels[CustodiaInfoFields.EXPEDIENTARXIUID]}" />
             </label>
              <c:if test="${not empty __theForm.help[CustodiaInfoFields.EXPEDIENTARXIUID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[CustodiaInfoFields.EXPEDIENTARXIUID]}" ></i>
              </c:if>
            </td>
          <td id="custodiaInfo_expedientArxiuId_columnvalueid">
            <form:errors path="custodiaInfo.expedientArxiuId" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.EXPEDIENTARXIUID)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.EXPEDIENTARXIUID)? ' uneditable-input' : ''}"  style="" maxlength="250" path="custodiaInfo.expedientArxiuId"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CustodiaInfoFields.DOCUMENTARXIUID)}">
        <tr id="custodiaInfo_documentArxiuId_rowid">
          <td id="custodiaInfo_documentArxiuId_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[CustodiaInfoFields.DOCUMENTARXIUID])?'custodiaInfo.documentArxiuId':__theForm.labels[CustodiaInfoFields.DOCUMENTARXIUID]}" />
             </label>
              <c:if test="${not empty __theForm.help[CustodiaInfoFields.DOCUMENTARXIUID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[CustodiaInfoFields.DOCUMENTARXIUID]}" ></i>
              </c:if>
            </td>
          <td id="custodiaInfo_documentArxiuId_columnvalueid">
            <form:errors path="custodiaInfo.documentArxiuId" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.DOCUMENTARXIUID)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,CustodiaInfoFields.DOCUMENTARXIUID)? ' uneditable-input' : ''}"  style="" maxlength="250" path="custodiaInfo.documentArxiuId"   />

           </td>
        </tr>
        </c:if>
        
