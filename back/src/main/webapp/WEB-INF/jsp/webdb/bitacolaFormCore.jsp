<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="BitacolaFields" className="es.caib.portafib.model.fields.BitacolaFields"/>
  
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,BitacolaFields.DESCRIPCIO)}">
        <tr id="bitacola_descripcio_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[BitacolaFields.DESCRIPCIO])?'bitacola.descripcio':__theForm.labels[BitacolaFields.DESCRIPCIO]}" /> &nbsp;(*)
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,BitacolaFields.PETICIODEFIRMAID)}">
        <tr id="bitacola_peticioDeFirmaID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[BitacolaFields.PETICIODEFIRMAID])?'bitacola.peticioDeFirmaID':__theForm.labels[BitacolaFields.PETICIODEFIRMAID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[BitacolaFields.PETICIODEFIRMAID]}">
              <i class="icon-info-sign" title="${__theForm.help[BitacolaFields.PETICIODEFIRMAID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="bitacola.peticioDeFirmaID" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,BitacolaFields.PETICIODEFIRMAID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,BitacolaFields.PETICIODEFIRMAID)? 'input-mini uneditable-input' : 'input-mini'}"   path="bitacola.peticioDeFirmaID"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,BitacolaFields.USUARIENTITATID)}">
        <tr id="bitacola_usuariEntitatID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[BitacolaFields.USUARIENTITATID])?'bitacola.usuariEntitatID':__theForm.labels[BitacolaFields.USUARIENTITATID]}" />
              <c:if test="${not empty __theForm.help[BitacolaFields.USUARIENTITATID]}">
              <i class="icon-info-sign" title="${__theForm.help[BitacolaFields.USUARIENTITATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="bitacola.usuariEntitatID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,BitacolaFields.USUARIENTITATID)}" >
          <form:hidden path="bitacola.usuariEntitatID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.bitacola.usuariEntitatID,__theForm.listOfUsuariEntitatForUsuariEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,BitacolaFields.USUARIENTITATID)}" >
          <form:select id="bitacola_usuariEntitatID"  onchange="if(typeof onChangeUsuariEntitatID == 'function') {  onChangeUsuariEntitatID(this); };"  cssClass="input-xxlarge" path="bitacola.usuariEntitatID">
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
        
