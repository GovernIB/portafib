<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PluginFields" className="es.caib.portafib.model.fields.PluginFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginFields.CODI)}">
        <tr id="plugin_codi_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginFields.CODI])?'plugin.codi':__theForm.labels[PluginFields.CODI]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PluginFields.CODI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginFields.CODI]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="plugin.codi" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PluginFields.CODI)? 'true' : 'false'}" cssClass="form-control col-md-9-optional ${gen:contains(__theForm.readOnlyFields ,PluginFields.CODI)? ' uneditable-input' : ''}"  style="" maxlength="255" path="plugin.codi"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginFields.NOMID)}">
        <tr id="plugin_nomID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginFields.NOMID])?'plugin.nomID':__theForm.labels[PluginFields.NOMID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PluginFields.NOMID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginFields.NOMID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
       <form:errors path="plugin.nom" cssClass="errorField alert alert-danger" />
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
               <form:errors path="plugin.nom.traduccions['${idioma.idiomaID}'].valor" cssClass="errorField alert alert-danger"/>
               <form:input path="plugin.nom.traduccions['${idioma.idiomaID}'].valor" cssClass="form-control col-md-9-optional ${gen:contains(__theForm.readOnlyFields ,PluginFields.NOMID)? ' uneditable-input' : ''}" readonly="${gen:contains(__theForm.readOnlyFields ,PluginFields.NOMID)}" maxlength="4000" />
           </div>
           </c:forEach>
         </div>
       </div>

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginFields.DESCRIPCIOCURTAID)}">
        <tr id="plugin_descripcioCurtaID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginFields.DESCRIPCIOCURTAID])?'plugin.descripcioCurtaID':__theForm.labels[PluginFields.DESCRIPCIOCURTAID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PluginFields.DESCRIPCIOCURTAID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginFields.DESCRIPCIOCURTAID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
       <form:errors path="plugin.descripcioCurta" cssClass="errorField alert alert-danger" />
       <div class="row-fluid col-md-9-optional">
         <ul class="nav nav-tabs" style="margin: 0 15px -1px;">
             <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
            <li class="nav-item ">
                 <a class="nav-link ${(counter.index == 0)? 'active':''}" href="#${counter.index}_tab_descripcioCurta_${idioma.idiomaID}" data-toggle="tab">${idioma.nom}</a>
            </li>
          </c:forEach>
           
         </ul>
         <div class="tab-content well well-white" style="padding:8px;margin:0px;">
           <c:forEach items="${__theForm.idiomesTraduccio}" var="idioma" varStatus="counter">
           <div class="tab-pane ${(counter.index == 0)? 'active':'' }" id="${counter.index}_tab_descripcioCurta_${idioma.idiomaID}">
               <form:errors path="plugin.descripcioCurta.traduccions['${idioma.idiomaID}'].valor" cssClass="errorField alert alert-danger"/>
               <form:input path="plugin.descripcioCurta.traduccions['${idioma.idiomaID}'].valor" cssClass="form-control col-md-9-optional ${gen:contains(__theForm.readOnlyFields ,PluginFields.DESCRIPCIOCURTAID)? ' uneditable-input' : ''}" readonly="${gen:contains(__theForm.readOnlyFields ,PluginFields.DESCRIPCIOCURTAID)}" maxlength="4000" />
           </div>
           </c:forEach>
         </div>
       </div>

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginFields.CLASSE)}">
        <tr id="plugin_classe_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginFields.CLASSE])?'plugin.classe':__theForm.labels[PluginFields.CLASSE]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PluginFields.CLASSE]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginFields.CLASSE]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="plugin.classe" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PluginFields.CLASSE)? 'true' : 'false'}" cssClass="form-control col-md-9-optional ${gen:contains(__theForm.readOnlyFields ,PluginFields.CLASSE)? ' uneditable-input' : ''}"  style="" maxlength="255" path="plugin.classe"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginFields.ORDRE)}">
        <tr id="plugin_ordre_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginFields.ORDRE])?'plugin.ordre':__theForm.labels[PluginFields.ORDRE]}" />
              <c:if test="${not empty __theForm.help[PluginFields.ORDRE]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginFields.ORDRE]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="plugin.ordre" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PluginFields.ORDRE)? 'true' : 'false'}" cssClass="form-control col-md-9-optional ${gen:contains(__theForm.readOnlyFields ,PluginFields.ORDRE)? ' uneditable-input' : ''}"  style=""  path="plugin.ordre"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginFields.TIPUS)}">
        <tr id="plugin_tipus_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginFields.TIPUS])?'plugin.tipus':__theForm.labels[PluginFields.TIPUS]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PluginFields.TIPUS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginFields.TIPUS]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="plugin.tipus" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PluginFields.TIPUS)}" >
          <form:hidden path="plugin.tipus"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.plugin.tipus,__theForm.listOfValuesForTipus)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PluginFields.TIPUS)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="plugin_tipus"  onchange="if(typeof onChangeTipus == 'function') {  onChangeTipus(this); };"  cssClass="form-control col-md-9-optional" path="plugin.tipus">
            <c:forEach items="${__theForm.listOfValuesForTipus}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginFields.PROPERTIESADMIN)}">
        <tr id="plugin_propertiesAdmin_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginFields.PROPERTIESADMIN])?'plugin.propertiesAdmin':__theForm.labels[PluginFields.PROPERTIESADMIN]}" />
              <c:if test="${not empty __theForm.help[PluginFields.PROPERTIESADMIN]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginFields.PROPERTIESADMIN]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="plugin.propertiesAdmin" cssClass="errorField alert alert-danger" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,PluginFields.PROPERTIESADMIN)? 'true' : 'false'}" path="plugin.propertiesAdmin"  />
      <div id="dropdownMenuButton_propertiesAdmin" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_propertiesAdmin" class="dropdown-menu">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('plugin.propertiesAdmin'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('plugin.propertiesAdmin'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('plugin.propertiesAdmin'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_propertiesAdmin').on('click', function(){
					var valor = ($('#dropdownMenuContainer_propertiesAdmin').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_propertiesAdmin').css('display', valor);
                 return false;
				});
      </script>           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginFields.PROPERTIESENTITAT)}">
        <tr id="plugin_propertiesEntitat_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginFields.PROPERTIESENTITAT])?'plugin.propertiesEntitat':__theForm.labels[PluginFields.PROPERTIESENTITAT]}" />
              <c:if test="${not empty __theForm.help[PluginFields.PROPERTIESENTITAT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginFields.PROPERTIESENTITAT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="plugin.propertiesEntitat" cssClass="errorField alert alert-danger" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,PluginFields.PROPERTIESENTITAT)? 'true' : 'false'}" path="plugin.propertiesEntitat"  />
      <div id="dropdownMenuButton_propertiesEntitat" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_propertiesEntitat" class="dropdown-menu">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('plugin.propertiesEntitat'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('plugin.propertiesEntitat'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('plugin.propertiesEntitat'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_propertiesEntitat').on('click', function(){
					var valor = ($('#dropdownMenuContainer_propertiesEntitat').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_propertiesEntitat').css('display', valor);
                 return false;
				});
      </script>           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginFields.POLITICADEUS)}">
        <tr id="plugin_politicaDeUs_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginFields.POLITICADEUS])?'plugin.politicaDeUs':__theForm.labels[PluginFields.POLITICADEUS]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PluginFields.POLITICADEUS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginFields.POLITICADEUS]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="plugin.politicaDeUs" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PluginFields.POLITICADEUS)}" >
          <form:hidden path="plugin.politicaDeUs"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.plugin.politicaDeUs,__theForm.listOfValuesForPoliticaDeUs)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PluginFields.POLITICADEUS)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="plugin_politicaDeUs"  onchange="if(typeof onChangePoliticaDeUs == 'function') {  onChangePoliticaDeUs(this); };"  cssClass="form-control col-md-9-optional" path="plugin.politicaDeUs">
            <c:forEach items="${__theForm.listOfValuesForPoliticaDeUs}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginFields.ENTITATID)}">
        <tr id="plugin_entitatID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginFields.ENTITATID])?'plugin.entitatID':__theForm.labels[PluginFields.ENTITATID]}" />
              <c:if test="${not empty __theForm.help[PluginFields.ENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginFields.ENTITATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="plugin.entitatID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PluginFields.ENTITATID)}" >
          <form:hidden path="plugin.entitatID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.plugin.entitatID,__theForm.listOfEntitatForEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PluginFields.ENTITATID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="plugin_entitatID"  onchange="if(typeof onChangeEntitatID == 'function') {  onChangeEntitatID(this); };"  cssClass="form-control col-md-9-optional" path="plugin.entitatID">
            <c:forEach items="${__theForm.listOfEntitatForEntitatID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.plugin.entitatID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.plugin.entitatID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginFields.ACTIU)}">
        <tr id="plugin_actiu_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginFields.ACTIU])?'plugin.actiu':__theForm.labels[PluginFields.ACTIU]}" />
              <c:if test="${not empty __theForm.help[PluginFields.ACTIU]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginFields.ACTIU]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PluginFields.ACTIU)}" >
              <form:errors path="plugin.actiu" cssClass="errorField alert alert-danger" />
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,PluginFields.ACTIU)? 'false' : 'true'}" path="plugin.actiu" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PluginFields.ACTIU)}" >
                <fmt:message key="genapp.checkbox.${__theForm.plugin.actiu}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginFields.POLITICAMOSTRARPROPIETATS)}">
        <tr id="plugin_politicaMostrarPropietats_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginFields.POLITICAMOSTRARPROPIETATS])?'plugin.politicaMostrarPropietats':__theForm.labels[PluginFields.POLITICAMOSTRARPROPIETATS]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PluginFields.POLITICAMOSTRARPROPIETATS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginFields.POLITICAMOSTRARPROPIETATS]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="plugin.politicaMostrarPropietats" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PluginFields.POLITICAMOSTRARPROPIETATS)}" >
          <form:hidden path="plugin.politicaMostrarPropietats"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.plugin.politicaMostrarPropietats,__theForm.listOfValuesForPoliticaMostrarPropietats)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PluginFields.POLITICAMOSTRARPROPIETATS)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="plugin_politicaMostrarPropietats"  onchange="if(typeof onChangePoliticaMostrarPropietats == 'function') {  onChangePoliticaMostrarPropietats(this); };"  cssClass="form-control col-md-9-optional" path="plugin.politicaMostrarPropietats">
            <c:forEach items="${__theForm.listOfValuesForPoliticaMostrarPropietats}" var="tmp">
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
        
