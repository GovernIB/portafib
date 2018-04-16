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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginCridadaFields.TIPUSPLUGIN)}">
        <tr id="pluginCridada_tipusPlugin_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginCridadaFields.TIPUSPLUGIN])?'pluginCridada.tipusPlugin':__theForm.labels[PluginCridadaFields.TIPUSPLUGIN]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PluginCridadaFields.TIPUSPLUGIN]}">
              <i class="icon-info-sign" title="${__theForm.help[PluginCridadaFields.TIPUSPLUGIN]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="pluginCridada.tipusPlugin" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.TIPUSPLUGIN)}" >
          <form:hidden path="pluginCridada.tipusPlugin"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.pluginCridada.tipusPlugin,__theForm.listOfValuesForTipusPlugin)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.TIPUSPLUGIN)}" >
          <form:select id="pluginCridada_tipusPlugin"  onchange="if(typeof onChangeTipusPlugin == 'function') {  onChangeTipusPlugin(this); };"  cssClass="input-xxlarge" path="pluginCridada.tipusPlugin">
            <c:forEach items="${__theForm.listOfValuesForTipusPlugin}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginCridadaFields.DADESPLUGIN)}">
        <tr id="pluginCridada_dadesPlugin_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginCridadaFields.DADESPLUGIN])?'pluginCridada.dadesPlugin':__theForm.labels[PluginCridadaFields.DADESPLUGIN]}" />
              <c:if test="${not empty __theForm.help[PluginCridadaFields.DADESPLUGIN]}">
              <i class="icon-info-sign" title="${__theForm.help[PluginCridadaFields.DADESPLUGIN]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="pluginCridada.dadesPlugin" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.DADESPLUGIN)? 'true' : 'false'}" path="pluginCridada.dadesPlugin"  />
              <div class="btn-group" style="vertical-align: top;">
              <button class="btn btn-mini dropdown-toggle" data-toggle="dropdown">&nbsp;<span class="caret"></span></button>
              <ul class="dropdown-menu">
                <li><a href="#" onclick="javascript:var ta=document.getElementById('pluginCridada.dadesPlugin'); ta.wrap='off';" >No Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('pluginCridada.dadesPlugin'); ta.wrap='soft';">Soft Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('pluginCridada.dadesPlugin'); ta.wrap='hard';">Hard Wrap</a></li>
              </ul>
              </div>
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginCridadaFields.DADESCRIDADA)}">
        <tr id="pluginCridada_dadesCridada_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginCridadaFields.DADESCRIDADA])?'pluginCridada.dadesCridada':__theForm.labels[PluginCridadaFields.DADESCRIDADA]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PluginCridadaFields.DADESCRIDADA]}">
              <i class="icon-info-sign" title="${__theForm.help[PluginCridadaFields.DADESCRIDADA]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="pluginCridada.dadesCridada" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.DADESCRIDADA)? 'true' : 'false'}" path="pluginCridada.dadesCridada"  />
              <div class="btn-group" style="vertical-align: top;">
              <button class="btn btn-mini dropdown-toggle" data-toggle="dropdown">&nbsp;<span class="caret"></span></button>
              <ul class="dropdown-menu">
                <li><a href="#" onclick="javascript:var ta=document.getElementById('pluginCridada.dadesCridada'); ta.wrap='off';" >No Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('pluginCridada.dadesCridada'); ta.wrap='soft';">Soft Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('pluginCridada.dadesCridada'); ta.wrap='hard';">Hard Wrap</a></li>
              </ul>
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginCridadaFields.RESULTAT)}">
        <tr id="pluginCridada_resultat_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginCridadaFields.RESULTAT])?'pluginCridada.resultat':__theForm.labels[PluginCridadaFields.RESULTAT]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PluginCridadaFields.RESULTAT]}">
              <i class="icon-info-sign" title="${__theForm.help[PluginCridadaFields.RESULTAT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="pluginCridada.resultat" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.RESULTAT)? 'true' : 'false'}" path="pluginCridada.resultat"  />
              <div class="btn-group" style="vertical-align: top;">
              <button class="btn btn-mini dropdown-toggle" data-toggle="dropdown">&nbsp;<span class="caret"></span></button>
              <ul class="dropdown-menu">
                <li><a href="#" onclick="javascript:var ta=document.getElementById('pluginCridada.resultat'); ta.wrap='off';" >No Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('pluginCridada.resultat'); ta.wrap='soft';">Soft Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('pluginCridada.resultat'); ta.wrap='hard';">Hard Wrap</a></li>
              </ul>
              </div>
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
        
