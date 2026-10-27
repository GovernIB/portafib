<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="NotificacioWSFields" className="es.caib.portafib.model.fields.NotificacioWSFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,NotificacioWSFields.PETICIODEFIRMAID)}">
        <tr id="notificacioWS_peticioDeFirmaID_rowid">
          <td id="notificacioWS_peticioDeFirmaID_columnlabelid">
            <label for="notificacioWS.peticioDeFirmaID">
              <fmt:message key="${(empty __theForm.labels[NotificacioWSFields.PETICIODEFIRMAID])?'notificacioWS.peticioDeFirmaID':__theForm.labels[NotificacioWSFields.PETICIODEFIRMAID]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[NotificacioWSFields.PETICIODEFIRMAID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[NotificacioWSFields.PETICIODEFIRMAID]}" ></i>
              </c:if>
            </td>
          <td id="notificacioWS_peticioDeFirmaID_columnvalueid">
            <form:errors path="notificacioWS.peticioDeFirmaID" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,NotificacioWSFields.PETICIODEFIRMAID)? 'readonly' : ''}" cssClass="w-25 form-control  ${gen:contains(__theForm.readOnlyFields ,NotificacioWSFields.PETICIODEFIRMAID)? ' uneditable-input' : ''}"  style=""  path="notificacioWS.peticioDeFirmaID"  required="required"  aria-required="true"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,NotificacioWSFields.TIPUSNOTIFICACIOID)}">
        <tr id="notificacioWS_tipusNotificacioID_rowid">
          <td id="notificacioWS_tipusNotificacioID_columnlabelid">
            <label for="notificacioWS.tipusNotificacioID">
              <fmt:message key="${(empty __theForm.labels[NotificacioWSFields.TIPUSNOTIFICACIOID])?'notificacioWS.tipusNotificacioID':__theForm.labels[NotificacioWSFields.TIPUSNOTIFICACIOID]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[NotificacioWSFields.TIPUSNOTIFICACIOID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[NotificacioWSFields.TIPUSNOTIFICACIOID]}" ></i>
              </c:if>
            </td>
          <td id="notificacioWS_tipusNotificacioID_columnvalueid">
          <form:errors path="notificacioWS.tipusNotificacioID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,NotificacioWSFields.TIPUSNOTIFICACIOID)}" >
          <form:hidden path="notificacioWS.tipusNotificacioID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.notificacioWS.tipusNotificacioID,__theForm.listOfTipusNotificacioForTipusNotificacioID)}" aria-label="notificacioWS.tipusNotificacioID"   required aria-required="true"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,NotificacioWSFields.TIPUSNOTIFICACIOID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="notificacioWS.tipusNotificacioID" cssClass="form-control col-md-9-optional"  required="required"  aria-required="true"  onchange="if(typeof onChangeTipusNotificacioID == 'function') {  onChangeTipusNotificacioID(this); };">


            <c:forEach items="${__theForm.listOfTipusNotificacioForTipusNotificacioID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,NotificacioWSFields.DATACREACIO)}">
        <tr id="notificacioWS_dataCreacio_rowid">
          <td id="notificacioWS_dataCreacio_columnlabelid">
            <label for="notificacioWS.dataCreacio">
              <fmt:message key="${(empty __theForm.labels[NotificacioWSFields.DATACREACIO])?'notificacioWS.dataCreacio':__theForm.labels[NotificacioWSFields.DATACREACIO]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[NotificacioWSFields.DATACREACIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[NotificacioWSFields.DATACREACIO]}" ></i>
              </c:if>
            </td>
          <td id="notificacioWS_dataCreacio_columnvalueid">
    <form:errors path="notificacioWS.dataCreacio" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="notificacioWS_dataCreacioDiv" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,NotificacioWSFields.DATACREACIO)? 'readonly' : ''}" cssClass="form-control datetimepicker-input"  data-target="#notificacioWS_dataCreacioDiv" path="notificacioWS.dataCreacio"   required="required"  aria-required="true"  />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,NotificacioWSFields.DATACREACIO)}" >
                    <div class="input-group-append"  data-target="#notificacioWS_dataCreacioDiv"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script>
            $(function () {
                $('#notificacioWS_dataCreacioDiv').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
        </script>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,NotificacioWSFields.DATAENVIAMENT)}">
        <tr id="notificacioWS_dataEnviament_rowid">
          <td id="notificacioWS_dataEnviament_columnlabelid">
            <label for="notificacioWS.dataEnviament">
              <fmt:message key="${(empty __theForm.labels[NotificacioWSFields.DATAENVIAMENT])?'notificacioWS.dataEnviament':__theForm.labels[NotificacioWSFields.DATAENVIAMENT]}" />
             </label>
              <c:if test="${not empty __theForm.help[NotificacioWSFields.DATAENVIAMENT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[NotificacioWSFields.DATAENVIAMENT]}" ></i>
              </c:if>
            </td>
          <td id="notificacioWS_dataEnviament_columnvalueid">
    <form:errors path="notificacioWS.dataEnviament" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="notificacioWS_dataEnviamentDiv" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,NotificacioWSFields.DATAENVIAMENT)? 'readonly' : ''}" cssClass="form-control datetimepicker-input"  data-target="#notificacioWS_dataEnviamentDiv" path="notificacioWS.dataEnviament"   />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,NotificacioWSFields.DATAENVIAMENT)}" >
                    <div class="input-group-append"  data-target="#notificacioWS_dataEnviamentDiv"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script>
            $(function () {
                $('#notificacioWS_dataEnviamentDiv').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
        </script>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,NotificacioWSFields.DESCRIPCIO)}">
        <tr id="notificacioWS_descripcio_rowid">
          <td id="notificacioWS_descripcio_columnlabelid">
            <label for="notificacioWS.descripcio">
              <fmt:message key="${(empty __theForm.labels[NotificacioWSFields.DESCRIPCIO])?'notificacioWS.descripcio':__theForm.labels[NotificacioWSFields.DESCRIPCIO]}" />
             </label>
              <c:if test="${not empty __theForm.help[NotificacioWSFields.DESCRIPCIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[NotificacioWSFields.DESCRIPCIO]}" ></i>
              </c:if>
            </td>
          <td id="notificacioWS_descripcio_columnvalueid">
              <form:errors path="notificacioWS.descripcio" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,NotificacioWSFields.DESCRIPCIO)? 'readonly' : ''}" path="notificacioWS.descripcio"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_descripcio" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;" aria-label="Opcions de format"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_descripcio" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('notificacioWS.descripcio'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('notificacioWS.descripcio'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('notificacioWS.descripcio'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script>
			$('#dropdownMenuButton_descripcio').on('click', function(){
					var valor = ($('#dropdownMenuContainer_descripcio').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_descripcio').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,NotificacioWSFields.BLOQUEJADA)}">
        <tr id="notificacioWS_bloquejada_rowid">
          <td id="notificacioWS_bloquejada_columnlabelid">
            <label for="notificacioWS.bloquejada">
              <fmt:message key="${(empty __theForm.labels[NotificacioWSFields.BLOQUEJADA])?'notificacioWS.bloquejada':__theForm.labels[NotificacioWSFields.BLOQUEJADA]}" />
             </label>
              <c:if test="${not empty __theForm.help[NotificacioWSFields.BLOQUEJADA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[NotificacioWSFields.BLOQUEJADA]}" ></i>
              </c:if>
            </td>
          <td id="notificacioWS_bloquejada_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,NotificacioWSFields.BLOQUEJADA)}" >
              <form:select cssClass="form-control col-md-6" onchange="if(typeof onChangeBloquejada == 'function') {  onChangeBloquejada(this); };"  path="notificacioWS.bloquejada">
                <form:option value=""><fmt:message key="genapp.checkbox." /></form:option>
                <form:option value="true" ><fmt:message key="genapp.checkbox.true" /></form:option>
                <form:option value="false" ><fmt:message key="genapp.checkbox.false" /></form:option>
              </form:select>
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,NotificacioWSFields.BLOQUEJADA)}" >
                <fmt:message key="genapp.checkbox.${__theForm.notificacioWS.bloquejada}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,NotificacioWSFields.ERROR)}">
        <tr id="notificacioWS_error_rowid">
          <td id="notificacioWS_error_columnlabelid">
            <label for="notificacioWS.error">
              <fmt:message key="${(empty __theForm.labels[NotificacioWSFields.ERROR])?'notificacioWS.error':__theForm.labels[NotificacioWSFields.ERROR]}" />
             </label>
              <c:if test="${not empty __theForm.help[NotificacioWSFields.ERROR]}">
              <i class="fas fa-info-circle" title="${__theForm.help[NotificacioWSFields.ERROR]}" ></i>
              </c:if>
            </td>
          <td id="notificacioWS_error_columnvalueid">
              <form:errors path="notificacioWS.error" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,NotificacioWSFields.ERROR)? 'readonly' : ''}" path="notificacioWS.error"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_error" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;" aria-label="Opcions de format"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_error" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('notificacioWS.error'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('notificacioWS.error'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('notificacioWS.error'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script>
			$('#dropdownMenuButton_error').on('click', function(){
					var valor = ($('#dropdownMenuContainer_error').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_error').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,NotificacioWSFields.DATAERROR)}">
        <tr id="notificacioWS_dataError_rowid">
          <td id="notificacioWS_dataError_columnlabelid">
            <label for="notificacioWS.dataError">
              <fmt:message key="${(empty __theForm.labels[NotificacioWSFields.DATAERROR])?'notificacioWS.dataError':__theForm.labels[NotificacioWSFields.DATAERROR]}" />
             </label>
              <c:if test="${not empty __theForm.help[NotificacioWSFields.DATAERROR]}">
              <i class="fas fa-info-circle" title="${__theForm.help[NotificacioWSFields.DATAERROR]}" ></i>
              </c:if>
            </td>
          <td id="notificacioWS_dataError_columnvalueid">
    <form:errors path="notificacioWS.dataError" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="notificacioWS_dataErrorDiv" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,NotificacioWSFields.DATAERROR)? 'readonly' : ''}" cssClass="form-control datetimepicker-input"  data-target="#notificacioWS_dataErrorDiv" path="notificacioWS.dataError"   />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,NotificacioWSFields.DATAERROR)}" >
                    <div class="input-group-append"  data-target="#notificacioWS_dataErrorDiv"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script>
            $(function () {
                $('#notificacioWS_dataErrorDiv').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
        </script>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,NotificacioWSFields.REINTENTS)}">
        <tr id="notificacioWS_reintents_rowid">
          <td id="notificacioWS_reintents_columnlabelid">
            <label for="notificacioWS.reintents">
              <fmt:message key="${(empty __theForm.labels[NotificacioWSFields.REINTENTS])?'notificacioWS.reintents':__theForm.labels[NotificacioWSFields.REINTENTS]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[NotificacioWSFields.REINTENTS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[NotificacioWSFields.REINTENTS]}" ></i>
              </c:if>
            </td>
          <td id="notificacioWS_reintents_columnvalueid">
            <form:errors path="notificacioWS.reintents" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,NotificacioWSFields.REINTENTS)? 'readonly' : ''}" cssClass="w-25 form-control  ${gen:contains(__theForm.readOnlyFields ,NotificacioWSFields.REINTENTS)? ' uneditable-input' : ''}"  style=""  path="notificacioWS.reintents"  required="required"  aria-required="true"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,NotificacioWSFields.USUARIAPLICACIOID)}">
        <tr id="notificacioWS_usuariAplicacioID_rowid">
          <td id="notificacioWS_usuariAplicacioID_columnlabelid">
            <label for="notificacioWS.usuariAplicacioID">
              <fmt:message key="${(empty __theForm.labels[NotificacioWSFields.USUARIAPLICACIOID])?'notificacioWS.usuariAplicacioID':__theForm.labels[NotificacioWSFields.USUARIAPLICACIOID]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[NotificacioWSFields.USUARIAPLICACIOID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[NotificacioWSFields.USUARIAPLICACIOID]}" ></i>
              </c:if>
            </td>
          <td id="notificacioWS_usuariAplicacioID_columnvalueid">
            <form:errors path="notificacioWS.usuariAplicacioID" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,NotificacioWSFields.USUARIAPLICACIOID)? 'readonly' : ''}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,NotificacioWSFields.USUARIAPLICACIOID)? ' uneditable-input' : ''}"  style="" maxlength="101" path="notificacioWS.usuariAplicacioID"  required="required"  aria-required="true"   />

           </td>
        </tr>
        </c:if>
        
