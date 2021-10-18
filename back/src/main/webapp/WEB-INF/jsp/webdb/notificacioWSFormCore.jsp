<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="NotificacioWSFields" className="es.caib.portafib.model.fields.NotificacioWSFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,NotificacioWSFields.PETICIODEFIRMAID)}">
        <tr id="notificacioWS_peticioDeFirmaID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[NotificacioWSFields.PETICIODEFIRMAID])?'notificacioWS.peticioDeFirmaID':__theForm.labels[NotificacioWSFields.PETICIODEFIRMAID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[NotificacioWSFields.PETICIODEFIRMAID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[NotificacioWSFields.PETICIODEFIRMAID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="notificacioWS.peticioDeFirmaID" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,NotificacioWSFields.PETICIODEFIRMAID)? 'true' : 'false'}" cssClass="form-control ${gen:contains(__theForm.readOnlyFields ,NotificacioWSFields.PETICIODEFIRMAID)? ' uneditable-input' : ''}"  style=""  path="notificacioWS.peticioDeFirmaID"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,NotificacioWSFields.TIPUSNOTIFICACIOID)}">
        <tr id="notificacioWS_tipusNotificacioID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[NotificacioWSFields.TIPUSNOTIFICACIOID])?'notificacioWS.tipusNotificacioID':__theForm.labels[NotificacioWSFields.TIPUSNOTIFICACIOID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[NotificacioWSFields.TIPUSNOTIFICACIOID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[NotificacioWSFields.TIPUSNOTIFICACIOID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="notificacioWS.tipusNotificacioID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,NotificacioWSFields.TIPUSNOTIFICACIOID)}" >
          <form:hidden path="notificacioWS.tipusNotificacioID"/>
          <input type="text" readonly="true" class="form-control input-xxlarge uneditable-input" value="${gen:findValue(__theForm.notificacioWS.tipusNotificacioID,__theForm.listOfTipusNotificacioForTipusNotificacioID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,NotificacioWSFields.TIPUSNOTIFICACIOID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="notificacioWS_tipusNotificacioID"  onchange="if(typeof onChangeTipusNotificacioID == 'function') {  onChangeTipusNotificacioID(this); };"  cssClass="form-control col-md-8" path="notificacioWS.tipusNotificacioID">
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
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[NotificacioWSFields.DATACREACIO])?'notificacioWS.dataCreacio':__theForm.labels[NotificacioWSFields.DATACREACIO]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[NotificacioWSFields.DATACREACIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[NotificacioWSFields.DATACREACIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="notificacioWS.dataCreacio" cssClass="errorField alert alert-danger" />
    <div class="container">
      <div class="row">
            <div class="form-group">
                <div class="input-group date" id="notificacioWS_dataCreacio" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,NotificacioWSFields.DATACREACIO)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#notificacioWS_dataCreacio" path="notificacioWS.dataCreacio" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,NotificacioWSFields.DATACREACIO)}" >
                    <div class="input-group-append"  data-target="#notificacioWS_dataCreacio"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
          <script type="text/javascript">
            $(function () {
                $('#notificacioWS_dataCreacio').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
          </script>        </div>
      </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,NotificacioWSFields.DATAENVIAMENT)}">
        <tr id="notificacioWS_dataEnviament_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[NotificacioWSFields.DATAENVIAMENT])?'notificacioWS.dataEnviament':__theForm.labels[NotificacioWSFields.DATAENVIAMENT]}" />
              <c:if test="${not empty __theForm.help[NotificacioWSFields.DATAENVIAMENT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[NotificacioWSFields.DATAENVIAMENT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="notificacioWS.dataEnviament" cssClass="errorField alert alert-danger" />
    <div class="container">
      <div class="row">
            <div class="form-group">
                <div class="input-group date" id="notificacioWS_dataEnviament" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,NotificacioWSFields.DATAENVIAMENT)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#notificacioWS_dataEnviament" path="notificacioWS.dataEnviament" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,NotificacioWSFields.DATAENVIAMENT)}" >
                    <div class="input-group-append"  data-target="#notificacioWS_dataEnviament"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
          <script type="text/javascript">
            $(function () {
                $('#notificacioWS_dataEnviament').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
          </script>        </div>
      </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,NotificacioWSFields.DESCRIPCIO)}">
        <tr id="notificacioWS_descripcio_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[NotificacioWSFields.DESCRIPCIO])?'notificacioWS.descripcio':__theForm.labels[NotificacioWSFields.DESCRIPCIO]}" />
              <c:if test="${not empty __theForm.help[NotificacioWSFields.DESCRIPCIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[NotificacioWSFields.DESCRIPCIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="notificacioWS.descripcio" cssClass="errorField alert alert-danger" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;max-width:90%;" cssClass="form-control " readonly="${ gen:contains(__theForm.readOnlyFields ,NotificacioWSFields.DESCRIPCIO)? 'true' : 'false'}" path="notificacioWS.descripcio"  />
      <div id="dropdownMenuButton_descripcio" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_descripcio" class="dropdown-menu">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('notificacioWS.descripcio'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('notificacioWS.descripcio'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('notificacioWS.descripcio'); ta.wrap='hard';">Hard Wrap</a>
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,NotificacioWSFields.BLOQUEJADA)}">
        <tr id="notificacioWS_bloquejada_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[NotificacioWSFields.BLOQUEJADA])?'notificacioWS.bloquejada':__theForm.labels[NotificacioWSFields.BLOQUEJADA]}" />
              <c:if test="${not empty __theForm.help[NotificacioWSFields.BLOQUEJADA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[NotificacioWSFields.BLOQUEJADA]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
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
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[NotificacioWSFields.ERROR])?'notificacioWS.error':__theForm.labels[NotificacioWSFields.ERROR]}" />
              <c:if test="${not empty __theForm.help[NotificacioWSFields.ERROR]}">
              <i class="fas fa-info-circle" title="${__theForm.help[NotificacioWSFields.ERROR]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="notificacioWS.error" cssClass="errorField alert alert-danger" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;max-width:90%;" cssClass="form-control " readonly="${ gen:contains(__theForm.readOnlyFields ,NotificacioWSFields.ERROR)? 'true' : 'false'}" path="notificacioWS.error"  />
      <div id="dropdownMenuButton_error" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_error" class="dropdown-menu">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('notificacioWS.error'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('notificacioWS.error'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('notificacioWS.error'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_error').on('click', function(){
					var valor = ($('#dropdownMenuContainer_error').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_error').css('display', valor);
                 return false;
				});
      </script>           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,NotificacioWSFields.DATAERROR)}">
        <tr id="notificacioWS_dataError_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[NotificacioWSFields.DATAERROR])?'notificacioWS.dataError':__theForm.labels[NotificacioWSFields.DATAERROR]}" />
              <c:if test="${not empty __theForm.help[NotificacioWSFields.DATAERROR]}">
              <i class="fas fa-info-circle" title="${__theForm.help[NotificacioWSFields.DATAERROR]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="notificacioWS.dataError" cssClass="errorField alert alert-danger" />
    <div class="container">
      <div class="row">
            <div class="form-group">
                <div class="input-group date" id="notificacioWS_dataError" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,NotificacioWSFields.DATAERROR)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#notificacioWS_dataError" path="notificacioWS.dataError" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,NotificacioWSFields.DATAERROR)}" >
                    <div class="input-group-append"  data-target="#notificacioWS_dataError"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
          <script type="text/javascript">
            $(function () {
                $('#notificacioWS_dataError').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
          </script>        </div>
      </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,NotificacioWSFields.REINTENTS)}">
        <tr id="notificacioWS_reintents_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[NotificacioWSFields.REINTENTS])?'notificacioWS.reintents':__theForm.labels[NotificacioWSFields.REINTENTS]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[NotificacioWSFields.REINTENTS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[NotificacioWSFields.REINTENTS]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="notificacioWS.reintents" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,NotificacioWSFields.REINTENTS)? 'true' : 'false'}" cssClass="form-control ${gen:contains(__theForm.readOnlyFields ,NotificacioWSFields.REINTENTS)? ' uneditable-input' : ''}"  style=""  path="notificacioWS.reintents"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,NotificacioWSFields.USUARIAPLICACIOID)}">
        <tr id="notificacioWS_usuariAplicacioID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[NotificacioWSFields.USUARIAPLICACIOID])?'notificacioWS.usuariAplicacioID':__theForm.labels[NotificacioWSFields.USUARIAPLICACIOID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[NotificacioWSFields.USUARIAPLICACIOID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[NotificacioWSFields.USUARIAPLICACIOID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="notificacioWS.usuariAplicacioID" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,NotificacioWSFields.USUARIAPLICACIOID)? 'true' : 'false'}" cssClass="form-control ${gen:contains(__theForm.readOnlyFields ,NotificacioWSFields.USUARIAPLICACIOID)? ' uneditable-input' : ''}"  style="" maxlength="101" path="notificacioWS.usuariAplicacioID"   />

           </td>
        </tr>
        </c:if>
        
