<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="BitacolaFields" className="es.caib.portafib.model.fields.BitacolaFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,BitacolaFields.ENTITATID)}">
        <tr id="bitacola_entitatid_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[BitacolaFields.ENTITATID])?'bitacola.entitatid':__theForm.labels[BitacolaFields.ENTITATID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[BitacolaFields.ENTITATID]}">
              <i class="icon-info-sign" title="${__theForm.help[BitacolaFields.ENTITATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="bitacola.entitatid" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,BitacolaFields.ENTITATID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,BitacolaFields.ENTITATID)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="50" path="bitacola.entitatid"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,BitacolaFields.USUARIID)}">
        <tr id="bitacola_usuariid_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[BitacolaFields.USUARIID])?'bitacola.usuariid':__theForm.labels[BitacolaFields.USUARIID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[BitacolaFields.USUARIID]}">
              <i class="icon-info-sign" title="${__theForm.help[BitacolaFields.USUARIID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="bitacola.usuariid" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,BitacolaFields.USUARIID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,BitacolaFields.USUARIID)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="101" path="bitacola.usuariid"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,BitacolaFields.DATA)}">
        <tr id="bitacola_data_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[BitacolaFields.DATA])?'bitacola.data':__theForm.labels[BitacolaFields.DATA]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[BitacolaFields.DATA]}">
              <i class="icon-info-sign" title="${__theForm.help[BitacolaFields.DATA]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="bitacola.data" cssClass="errorField alert alert-error" />
              <div id="data" class="input-append">
                <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,BitacolaFields.DATA)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,BitacolaFields.DATA)? 'input-medium uneditable-input' : 'input-medium'}"  path="bitacola.data" />
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,BitacolaFields.DATA)}" >
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,BitacolaFields.TIPUSOBJECTE)}">
        <tr id="bitacola_tipusObjecte_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[BitacolaFields.TIPUSOBJECTE])?'bitacola.tipusObjecte':__theForm.labels[BitacolaFields.TIPUSOBJECTE]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[BitacolaFields.TIPUSOBJECTE]}">
              <i class="icon-info-sign" title="${__theForm.help[BitacolaFields.TIPUSOBJECTE]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="bitacola.tipusObjecte" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,BitacolaFields.TIPUSOBJECTE)}" >
          <form:hidden path="bitacola.tipusObjecte"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.bitacola.tipusObjecte,__theForm.listOfValuesForTipusObjecte)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,BitacolaFields.TIPUSOBJECTE)}" >
          <form:select id="bitacola_tipusObjecte"  onchange="if(typeof onChangeTipusObjecte == 'function') {  onChangeTipusObjecte(this); };"  cssClass="input-xxlarge" path="bitacola.tipusObjecte">
            <c:forEach items="${__theForm.listOfValuesForTipusObjecte}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,BitacolaFields.OBJECTEID)}">
        <tr id="bitacola_objecteid_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[BitacolaFields.OBJECTEID])?'bitacola.objecteid':__theForm.labels[BitacolaFields.OBJECTEID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[BitacolaFields.OBJECTEID]}">
              <i class="icon-info-sign" title="${__theForm.help[BitacolaFields.OBJECTEID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="bitacola.objecteid" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,BitacolaFields.OBJECTEID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,BitacolaFields.OBJECTEID)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="100" path="bitacola.objecteid"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,BitacolaFields.TIPUSOPERACIO)}">
        <tr id="bitacola_tipusOperacio_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[BitacolaFields.TIPUSOPERACIO])?'bitacola.tipusOperacio':__theForm.labels[BitacolaFields.TIPUSOPERACIO]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[BitacolaFields.TIPUSOPERACIO]}">
              <i class="icon-info-sign" title="${__theForm.help[BitacolaFields.TIPUSOPERACIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="bitacola.tipusOperacio" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,BitacolaFields.TIPUSOPERACIO)}" >
          <form:hidden path="bitacola.tipusOperacio"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.bitacola.tipusOperacio,__theForm.listOfValuesForTipusOperacio)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,BitacolaFields.TIPUSOPERACIO)}" >
          <form:select id="bitacola_tipusOperacio"  onchange="if(typeof onChangeTipusOperacio == 'function') {  onChangeTipusOperacio(this); };"  cssClass="input-xxlarge" path="bitacola.tipusOperacio">
            <c:forEach items="${__theForm.listOfValuesForTipusOperacio}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,BitacolaFields.DESCRIPCIO)}">
        <tr id="bitacola_descripcio_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[BitacolaFields.DESCRIPCIO])?'bitacola.descripcio':__theForm.labels[BitacolaFields.DESCRIPCIO]}" />
              <c:if test="${not empty __theForm.help[BitacolaFields.DESCRIPCIO]}">
              <i class="icon-info-sign" title="${__theForm.help[BitacolaFields.DESCRIPCIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="bitacola.descripcio" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,BitacolaFields.DESCRIPCIO)? 'true' : 'false'}" path="bitacola.descripcio"  />
              <div class="btn-group" style="vertical-align: top;">
              <button class="btn btn-mini dropdown-toggle" data-toggle="dropdown">&nbsp;<span class="caret"></span></button>
              <ul class="dropdown-menu">
                <li><a href="#" onclick="javascript:var ta=document.getElementById('bitacola.descripcio'); ta.wrap='off';" >No Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('bitacola.descripcio'); ta.wrap='soft';">Soft Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('bitacola.descripcio'); ta.wrap='hard';">Hard Wrap</a></li>
              </ul>
              </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,BitacolaFields.OBJECTESERIALITZAT)}">
        <tr id="bitacola_objecteSerialitzat_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[BitacolaFields.OBJECTESERIALITZAT])?'bitacola.objecteSerialitzat':__theForm.labels[BitacolaFields.OBJECTESERIALITZAT]}" />
              <c:if test="${not empty __theForm.help[BitacolaFields.OBJECTESERIALITZAT]}">
              <i class="icon-info-sign" title="${__theForm.help[BitacolaFields.OBJECTESERIALITZAT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="bitacola.objecteSerialitzat" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,BitacolaFields.OBJECTESERIALITZAT)? 'true' : 'false'}" path="bitacola.objecteSerialitzat"  />
              <div class="btn-group" style="vertical-align: top;">
              <button class="btn btn-mini dropdown-toggle" data-toggle="dropdown">&nbsp;<span class="caret"></span></button>
              <ul class="dropdown-menu">
                <li><a href="#" onclick="javascript:var ta=document.getElementById('bitacola.objecteSerialitzat'); ta.wrap='off';" >No Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('bitacola.objecteSerialitzat'); ta.wrap='soft';">Soft Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('bitacola.objecteSerialitzat'); ta.wrap='hard';">Hard Wrap</a></li>
              </ul>
              </div>
           </td>
        </tr>
        </c:if>
        
