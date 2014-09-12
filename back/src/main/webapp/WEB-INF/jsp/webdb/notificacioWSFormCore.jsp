<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="NotificacioWSFields" className="es.caib.portafib.model.fields.NotificacioWSFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,NotificacioWSFields.PETICIODEFIRMAID)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[NotificacioWSFields.PETICIODEFIRMAID])?'notificacioWS.peticioDeFirmaID':__theForm.labels[NotificacioWSFields.PETICIODEFIRMAID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[NotificacioWSFields.PETICIODEFIRMAID]}">
              <i class="icon-info-sign" title="${__theForm.help[NotificacioWSFields.PETICIODEFIRMAID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="notificacioWS.peticioDeFirmaID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,NotificacioWSFields.PETICIODEFIRMAID)}" >
          <form:hidden path="notificacioWS.peticioDeFirmaID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.notificacioWS.peticioDeFirmaID,__theForm.listOfPeticioDeFirmaForPeticioDeFirmaID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,NotificacioWSFields.PETICIODEFIRMAID)}" >
          <form:select id="notificacioWS_peticioDeFirmaID"  onchange="if(typeof onChangePeticioDeFirmaID == 'function') {  onChangePeticioDeFirmaID(this); };"  cssClass="input-xxlarge" path="notificacioWS.peticioDeFirmaID">
            <c:forEach items="${__theForm.listOfPeticioDeFirmaForPeticioDeFirmaID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,NotificacioWSFields.TIPUSNOTIFICACIOID)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[NotificacioWSFields.TIPUSNOTIFICACIOID])?'notificacioWS.tipusNotificacioID':__theForm.labels[NotificacioWSFields.TIPUSNOTIFICACIOID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[NotificacioWSFields.TIPUSNOTIFICACIOID]}">
              <i class="icon-info-sign" title="${__theForm.help[NotificacioWSFields.TIPUSNOTIFICACIOID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="notificacioWS.tipusNotificacioID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,NotificacioWSFields.TIPUSNOTIFICACIOID)}" >
          <form:hidden path="notificacioWS.tipusNotificacioID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.notificacioWS.tipusNotificacioID,__theForm.listOfTipusNotificacioForTipusNotificacioID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,NotificacioWSFields.TIPUSNOTIFICACIOID)}" >
          <form:select id="notificacioWS_tipusNotificacioID"  onchange="if(typeof onChangeTipusNotificacioID == 'function') {  onChangeTipusNotificacioID(this); };"  cssClass="input-xxlarge" path="notificacioWS.tipusNotificacioID">
            <c:forEach items="${__theForm.listOfTipusNotificacioForTipusNotificacioID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,NotificacioWSFields.DATACREACIO)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[NotificacioWSFields.DATACREACIO])?'notificacioWS.dataCreacio':__theForm.labels[NotificacioWSFields.DATACREACIO]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[NotificacioWSFields.DATACREACIO]}">
              <i class="icon-info-sign" title="${__theForm.help[NotificacioWSFields.DATACREACIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="notificacioWS.dataCreacio" cssClass="errorField alert alert-error" />
              <div id="dataCreacio" class="input-append">
                <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,NotificacioWSFields.DATACREACIO)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,NotificacioWSFields.DATACREACIO)? 'input-medium uneditable-input' : 'input-medium'}"  path="notificacioWS.dataCreacio" />
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,NotificacioWSFields.DATACREACIO)}" >
                <span class="add-on">
                  <i data-time-icon="icon-time" data-date-icon="icon-calendar">
                  </i>
                </span>
              </c:if>
              </div>
              <script type="text/javascript">                
                $(function() {
                  $('#dataCreacio').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,NotificacioWSFields.DATAENVIAMENT)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[NotificacioWSFields.DATAENVIAMENT])?'notificacioWS.dataEnviament':__theForm.labels[NotificacioWSFields.DATAENVIAMENT]}" />
              <c:if test="${not empty __theForm.help[NotificacioWSFields.DATAENVIAMENT]}">
              <i class="icon-info-sign" title="${__theForm.help[NotificacioWSFields.DATAENVIAMENT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="notificacioWS.dataEnviament" cssClass="errorField alert alert-error" />
              <div id="dataEnviament" class="input-append">
                <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,NotificacioWSFields.DATAENVIAMENT)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,NotificacioWSFields.DATAENVIAMENT)? 'input-medium uneditable-input' : 'input-medium'}"  path="notificacioWS.dataEnviament" />
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,NotificacioWSFields.DATAENVIAMENT)}" >
                <span class="add-on">
                  <i data-time-icon="icon-time" data-date-icon="icon-calendar">
                  </i>
                </span>
              </c:if>
              </div>
              <script type="text/javascript">                
                $(function() {
                  $('#dataEnviament').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,NotificacioWSFields.DESCRIPCIO)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[NotificacioWSFields.DESCRIPCIO])?'notificacioWS.descripcio':__theForm.labels[NotificacioWSFields.DESCRIPCIO]}" />
              <c:if test="${not empty __theForm.help[NotificacioWSFields.DESCRIPCIO]}">
              <i class="icon-info-sign" title="${__theForm.help[NotificacioWSFields.DESCRIPCIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="notificacioWS.descripcio" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" readonly="${ gen:contains(__theForm.readOnlyFields ,NotificacioWSFields.DESCRIPCIO)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,NotificacioWSFields.DESCRIPCIO)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"   path="notificacioWS.descripcio"  />
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,NotificacioWSFields.BLOQUEJADA)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[NotificacioWSFields.BLOQUEJADA])?'notificacioWS.bloquejada':__theForm.labels[NotificacioWSFields.BLOQUEJADA]}" />
              <c:if test="${not empty __theForm.help[NotificacioWSFields.BLOQUEJADA]}">
              <i class="icon-info-sign" title="${__theForm.help[NotificacioWSFields.BLOQUEJADA]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,NotificacioWSFields.BLOQUEJADA)}" >
              <form:select cssClass="input-medium" onchange="if(typeof onChangeBloquejada == 'function') {  onChangeBloquejada(this); };"  path="notificacioWS.bloquejada">
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
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[NotificacioWSFields.ERROR])?'notificacioWS.error':__theForm.labels[NotificacioWSFields.ERROR]}" />
              <c:if test="${not empty __theForm.help[NotificacioWSFields.ERROR]}">
              <i class="icon-info-sign" title="${__theForm.help[NotificacioWSFields.ERROR]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="notificacioWS.error" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" readonly="${ gen:contains(__theForm.readOnlyFields ,NotificacioWSFields.ERROR)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,NotificacioWSFields.ERROR)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"   path="notificacioWS.error"  />
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,NotificacioWSFields.DATAERROR)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[NotificacioWSFields.DATAERROR])?'notificacioWS.dataError':__theForm.labels[NotificacioWSFields.DATAERROR]}" />
              <c:if test="${not empty __theForm.help[NotificacioWSFields.DATAERROR]}">
              <i class="icon-info-sign" title="${__theForm.help[NotificacioWSFields.DATAERROR]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="notificacioWS.dataError" cssClass="errorField alert alert-error" />
              <div id="dataError" class="input-append">
                <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,NotificacioWSFields.DATAERROR)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,NotificacioWSFields.DATAERROR)? 'input-medium uneditable-input' : 'input-medium'}"  path="notificacioWS.dataError" />
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,NotificacioWSFields.DATAERROR)}" >
                <span class="add-on">
                  <i data-time-icon="icon-time" data-date-icon="icon-calendar">
                  </i>
                </span>
              </c:if>
              </div>
              <script type="text/javascript">                
                $(function() {
                  $('#dataError').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,NotificacioWSFields.REINTENTS)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[NotificacioWSFields.REINTENTS])?'notificacioWS.reintents':__theForm.labels[NotificacioWSFields.REINTENTS]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[NotificacioWSFields.REINTENTS]}">
              <i class="icon-info-sign" title="${__theForm.help[NotificacioWSFields.REINTENTS]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="notificacioWS.reintents" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,NotificacioWSFields.REINTENTS)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,NotificacioWSFields.REINTENTS)? 'input-mini uneditable-input' : 'input-mini'}"   path="notificacioWS.reintents" />
           </td>
        </tr>
        </c:if>
        
