<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PluginCridadaFields" className="es.caib.portafib.model.fields.PluginCridadaFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginCridadaFields.ENTITATID)}">
        <tr id="pluginCridada_entitatID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginCridadaFields.ENTITATID])?'pluginCridada.entitatID':__theForm.labels[PluginCridadaFields.ENTITATID]}" />
              <c:if test="${not empty __theForm.help[PluginCridadaFields.ENTITATID]}">
              <i class="icon-info-sign" title="${__theForm.help[PluginCridadaFields.ENTITATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="pluginCridada.entitatID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.ENTITATID)}" >
          <form:hidden path="pluginCridada.entitatID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.pluginCridada.entitatID,__theForm.listOfEntitatForEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.ENTITATID)}" >
          <form:select id="pluginCridada_entitatID"  onchange="if(typeof onChangeEntitatID == 'function') {  onChangeEntitatID(this); };"  cssClass="input-xxlarge" path="pluginCridada.entitatID">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginCridadaFields.DATA)}">
        <tr id="pluginCridada_data_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginCridadaFields.DATA])?'pluginCridada.data':__theForm.labels[PluginCridadaFields.DATA]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PluginCridadaFields.DATA]}">
              <i class="icon-info-sign" title="${__theForm.help[PluginCridadaFields.DATA]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="pluginCridada.data" cssClass="errorField alert alert-error" />
              <div id="data" class="input-append">
                <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.DATA)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.DATA)? 'input-medium uneditable-input' : 'input-medium'}"  path="pluginCridada.data" />
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.DATA)}" >
                <span class="add-on">
                  <i data-time-icon="icon-time" data-date-icon="icon-calendar">
                  </i>
                </span>
              </c:if>
              </div>
              <script type="text/javascript">                
                $(function() {
                  $('#data').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginCridadaFields.PLUGINID)}">
        <tr id="pluginCridada_pluginID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginCridadaFields.PLUGINID])?'pluginCridada.pluginID':__theForm.labels[PluginCridadaFields.PLUGINID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PluginCridadaFields.PLUGINID]}">
              <i class="icon-info-sign" title="${__theForm.help[PluginCridadaFields.PLUGINID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="pluginCridada.pluginID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.PLUGINID)}" >
          <form:hidden path="pluginCridada.pluginID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.pluginCridada.pluginID,__theForm.listOfPluginForPluginID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.PLUGINID)}" >
          <form:select id="pluginCridada_pluginID"  onchange="if(typeof onChangePluginID == 'function') {  onChangePluginID(this); };"  cssClass="input-xxlarge" path="pluginCridada.pluginID">
            <c:forEach items="${__theForm.listOfPluginForPluginID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginCridadaFields.METODEPLUGIN)}">
        <tr id="pluginCridada_metodePlugin_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginCridadaFields.METODEPLUGIN])?'pluginCridada.metodePlugin':__theForm.labels[PluginCridadaFields.METODEPLUGIN]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PluginCridadaFields.METODEPLUGIN]}">
              <i class="icon-info-sign" title="${__theForm.help[PluginCridadaFields.METODEPLUGIN]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="pluginCridada.metodePlugin" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.METODEPLUGIN)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.METODEPLUGIN)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="100" path="pluginCridada.metodePlugin"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginCridadaFields.PARAMETRESTEXT)}">
        <tr id="pluginCridada_parametresText_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginCridadaFields.PARAMETRESTEXT])?'pluginCridada.parametresText':__theForm.labels[PluginCridadaFields.PARAMETRESTEXT]}" />
              <c:if test="${not empty __theForm.help[PluginCridadaFields.PARAMETRESTEXT]}">
              <i class="icon-info-sign" title="${__theForm.help[PluginCridadaFields.PARAMETRESTEXT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="pluginCridada.parametresText" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.PARAMETRESTEXT)? 'true' : 'false'}" path="pluginCridada.parametresText"  />
              <div class="btn-group" style="vertical-align: top;">
              <button class="btn btn-mini dropdown-toggle" data-toggle="dropdown">&nbsp;<span class="caret"></span></button>
              <ul class="dropdown-menu">
                <li><a href="#" onclick="javascript:var ta=document.getElementById('pluginCridada.parametresText'); ta.wrap='off';" >No Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('pluginCridada.parametresText'); ta.wrap='soft';">Soft Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('pluginCridada.parametresText'); ta.wrap='hard';">Hard Wrap</a></li>
              </ul>
              </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginCridadaFields.PARAMETRESFITXERID)}">
        <tr id="pluginCridada_parametresFitxerID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginCridadaFields.PARAMETRESFITXERID])?'pluginCridada.parametresFitxerID':__theForm.labels[PluginCridadaFields.PARAMETRESFITXERID]}" />
              <c:if test="${not empty __theForm.help[PluginCridadaFields.PARAMETRESFITXERID]}">
              <i class="icon-info-sign" title="${__theForm.help[PluginCridadaFields.PARAMETRESFITXERID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="pluginCridada.parametresFitxerID" cssClass="errorField alert alert-error" />
              <div class="fileupload fileupload-new" data-provides="fileupload" style="margin-bottom: 0px">
                <div class="input-append">
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.PARAMETRESFITXERID)}" >
                    <div class="uneditable-input span3">
                      <i class="icon-file fileupload-exists"></i>
                      <span class="fileupload-preview"></span>
                    </div>
                    <span class="btn btn-file">
                      <span class="fileupload-new"><fmt:message key="genapp.form.file.select"/></span>
                      <span class="fileupload-exists"><fmt:message key="genapp.form.file.change"/></span>
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.PARAMETRESFITXERID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.PARAMETRESFITXERID)? 'input uneditable-input' : 'input'}"  path="parametresFitxerID" type="file" />
                    </span>
                    <a href="#" class="btn fileupload-exists" data-dismiss="fileupload"><fmt:message key="genapp.form.file.unselect"/></a>
                    <span class="add-on">&nbsp;</span>
                </c:if>
                <c:if test="${not empty __theForm.pluginCridada.parametresFitxer}">
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.PARAMETRESFITXERID)}" >
                    <span class="add-on">
                        <form:checkbox path="parametresFitxerIDDelete"/>
                        <fmt:message key="genapp.form.file.delete"/>
                    </span>
                    <span class="add-on">&nbsp;</span>   
                </c:if>
                    <span class="add-on">
                        <a target="_blank" href="<c:url value="${pfi:fileUrl(__theForm.pluginCridada.parametresFitxer)}"/>">${__theForm.pluginCridada.parametresFitxer.nom}</a>
                    </span>
                </c:if>
                </div>
              </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginCridadaFields.RETORNTEXT)}">
        <tr id="pluginCridada_retornText_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginCridadaFields.RETORNTEXT])?'pluginCridada.retornText':__theForm.labels[PluginCridadaFields.RETORNTEXT]}" />
              <c:if test="${not empty __theForm.help[PluginCridadaFields.RETORNTEXT]}">
              <i class="icon-info-sign" title="${__theForm.help[PluginCridadaFields.RETORNTEXT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="pluginCridada.retornText" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.RETORNTEXT)? 'true' : 'false'}" path="pluginCridada.retornText"  />
              <div class="btn-group" style="vertical-align: top;">
              <button class="btn btn-mini dropdown-toggle" data-toggle="dropdown">&nbsp;<span class="caret"></span></button>
              <ul class="dropdown-menu">
                <li><a href="#" onclick="javascript:var ta=document.getElementById('pluginCridada.retornText'); ta.wrap='off';" >No Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('pluginCridada.retornText'); ta.wrap='soft';">Soft Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('pluginCridada.retornText'); ta.wrap='hard';">Hard Wrap</a></li>
              </ul>
              </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginCridadaFields.RETORNFITXERID)}">
        <tr id="pluginCridada_retornFitxerID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginCridadaFields.RETORNFITXERID])?'pluginCridada.retornFitxerID':__theForm.labels[PluginCridadaFields.RETORNFITXERID]}" />
              <c:if test="${not empty __theForm.help[PluginCridadaFields.RETORNFITXERID]}">
              <i class="icon-info-sign" title="${__theForm.help[PluginCridadaFields.RETORNFITXERID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="pluginCridada.retornFitxerID" cssClass="errorField alert alert-error" />
              <div class="fileupload fileupload-new" data-provides="fileupload" style="margin-bottom: 0px">
                <div class="input-append">
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.RETORNFITXERID)}" >
                    <div class="uneditable-input span3">
                      <i class="icon-file fileupload-exists"></i>
                      <span class="fileupload-preview"></span>
                    </div>
                    <span class="btn btn-file">
                      <span class="fileupload-new"><fmt:message key="genapp.form.file.select"/></span>
                      <span class="fileupload-exists"><fmt:message key="genapp.form.file.change"/></span>
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.RETORNFITXERID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.RETORNFITXERID)? 'input uneditable-input' : 'input'}"  path="retornFitxerID" type="file" />
                    </span>
                    <a href="#" class="btn fileupload-exists" data-dismiss="fileupload"><fmt:message key="genapp.form.file.unselect"/></a>
                    <span class="add-on">&nbsp;</span>
                </c:if>
                <c:if test="${not empty __theForm.pluginCridada.retornFitxer}">
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.RETORNFITXERID)}" >
                    <span class="add-on">
                        <form:checkbox path="retornFitxerIDDelete"/>
                        <fmt:message key="genapp.form.file.delete"/>
                    </span>
                    <span class="add-on">&nbsp;</span>   
                </c:if>
                    <span class="add-on">
                        <a target="_blank" href="<c:url value="${pfi:fileUrl(__theForm.pluginCridada.retornFitxer)}"/>">${__theForm.pluginCridada.retornFitxer.nom}</a>
                    </span>
                </c:if>
                </div>
              </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginCridadaFields.TIPUSTESULTAT)}">
        <tr id="pluginCridada_tipusTesultat_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginCridadaFields.TIPUSTESULTAT])?'pluginCridada.tipusTesultat':__theForm.labels[PluginCridadaFields.TIPUSTESULTAT]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PluginCridadaFields.TIPUSTESULTAT]}">
              <i class="icon-info-sign" title="${__theForm.help[PluginCridadaFields.TIPUSTESULTAT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="pluginCridada.tipusTesultat" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.TIPUSTESULTAT)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.TIPUSTESULTAT)? 'input-mini uneditable-input' : 'input-mini'}"   path="pluginCridada.tipusTesultat"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginCridadaFields.TEMPSEXECUCIO)}">
        <tr id="pluginCridada_tempsExecucio_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginCridadaFields.TEMPSEXECUCIO])?'pluginCridada.tempsExecucio':__theForm.labels[PluginCridadaFields.TEMPSEXECUCIO]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PluginCridadaFields.TEMPSEXECUCIO]}">
              <i class="icon-info-sign" title="${__theForm.help[PluginCridadaFields.TEMPSEXECUCIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="pluginCridada.tempsExecucio" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.TEMPSEXECUCIO)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.TEMPSEXECUCIO)? 'input-mini uneditable-input' : 'input-mini'}"   path="pluginCridada.tempsExecucio"   />

           </td>
        </tr>
        </c:if>
        
