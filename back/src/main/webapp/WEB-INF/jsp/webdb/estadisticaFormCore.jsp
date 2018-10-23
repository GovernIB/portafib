<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="EstadisticaFields" className="es.caib.portafib.model.fields.EstadisticaFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstadisticaFields.DATA)}">
        <tr id="estadistica_data_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EstadisticaFields.DATA])?'estadistica.data':__theForm.labels[EstadisticaFields.DATA]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EstadisticaFields.DATA]}">
              <i class="icon-info-sign" title="${__theForm.help[EstadisticaFields.DATA]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="estadistica.data" cssClass="errorField alert alert-error" />
              <div id="data" class="input-append">
                <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EstadisticaFields.DATA)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,EstadisticaFields.DATA)? 'input-medium uneditable-input' : 'input-medium'}"  path="estadistica.data" />
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,EstadisticaFields.DATA)}" >
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstadisticaFields.TIPUS)}">
        <tr id="estadistica_tipus_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EstadisticaFields.TIPUS])?'estadistica.tipus':__theForm.labels[EstadisticaFields.TIPUS]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EstadisticaFields.TIPUS]}">
              <i class="icon-info-sign" title="${__theForm.help[EstadisticaFields.TIPUS]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="estadistica.tipus" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EstadisticaFields.TIPUS)}" >
          <form:hidden path="estadistica.tipus"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.estadistica.tipus,__theForm.listOfValuesForTipus)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EstadisticaFields.TIPUS)}" >
          <form:select id="estadistica_tipus"  onchange="if(typeof onChangeTipus == 'function') {  onChangeTipus(this); };"  cssClass="input-xxlarge" path="estadistica.tipus">
            <c:forEach items="${__theForm.listOfValuesForTipus}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstadisticaFields.ENTITATID)}">
        <tr id="estadistica_entitatID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EstadisticaFields.ENTITATID])?'estadistica.entitatID':__theForm.labels[EstadisticaFields.ENTITATID]}" />
              <c:if test="${not empty __theForm.help[EstadisticaFields.ENTITATID]}">
              <i class="icon-info-sign" title="${__theForm.help[EstadisticaFields.ENTITATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="estadistica.entitatID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EstadisticaFields.ENTITATID)}" >
          <form:hidden path="estadistica.entitatID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.estadistica.entitatID,__theForm.listOfEntitatForEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EstadisticaFields.ENTITATID)}" >
          <form:select id="estadistica_entitatID"  onchange="if(typeof onChangeEntitatID == 'function') {  onChangeEntitatID(this); };"  cssClass="input-xxlarge" path="estadistica.entitatID">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstadisticaFields.VALOR)}">
        <tr id="estadistica_valor_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EstadisticaFields.VALOR])?'estadistica.valor':__theForm.labels[EstadisticaFields.VALOR]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EstadisticaFields.VALOR]}">
              <i class="icon-info-sign" title="${__theForm.help[EstadisticaFields.VALOR]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="estadistica.valor" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EstadisticaFields.VALOR)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,EstadisticaFields.VALOR)? 'input-medium uneditable-input' : 'input-medium'}"   path="estadistica.valor"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstadisticaFields.USUARIAPLICACIOID)}">
        <tr id="estadistica_usuariAplicacioID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EstadisticaFields.USUARIAPLICACIOID])?'estadistica.usuariAplicacioID':__theForm.labels[EstadisticaFields.USUARIAPLICACIOID]}" />
              <c:if test="${not empty __theForm.help[EstadisticaFields.USUARIAPLICACIOID]}">
              <i class="icon-info-sign" title="${__theForm.help[EstadisticaFields.USUARIAPLICACIOID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="estadistica.usuariAplicacioID" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EstadisticaFields.USUARIAPLICACIOID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,EstadisticaFields.USUARIAPLICACIOID)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="101" path="estadistica.usuariAplicacioID"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstadisticaFields.USUARIENTITATID)}">
        <tr id="estadistica_usuariEntitatID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EstadisticaFields.USUARIENTITATID])?'estadistica.usuariEntitatID':__theForm.labels[EstadisticaFields.USUARIENTITATID]}" />
              <c:if test="${not empty __theForm.help[EstadisticaFields.USUARIENTITATID]}">
              <i class="icon-info-sign" title="${__theForm.help[EstadisticaFields.USUARIENTITATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="estadistica.usuariEntitatID" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EstadisticaFields.USUARIENTITATID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,EstadisticaFields.USUARIENTITATID)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="101" path="estadistica.usuariEntitatID"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstadisticaFields.PARAMETRES)}">
        <tr id="estadistica_parametres_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EstadisticaFields.PARAMETRES])?'estadistica.parametres':__theForm.labels[EstadisticaFields.PARAMETRES]}" />
              <c:if test="${not empty __theForm.help[EstadisticaFields.PARAMETRES]}">
              <i class="icon-info-sign" title="${__theForm.help[EstadisticaFields.PARAMETRES]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="estadistica.parametres" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,EstadisticaFields.PARAMETRES)? 'true' : 'false'}" path="estadistica.parametres"  />
              <div class="btn-group" style="vertical-align: top;">
              <button class="btn btn-mini dropdown-toggle" data-toggle="dropdown">&nbsp;<span class="caret"></span></button>
              <ul class="dropdown-menu">
                <li><a href="#" onclick="javascript:var ta=document.getElementById('estadistica.parametres'); ta.wrap='off';" >No Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('estadistica.parametres'); ta.wrap='soft';">Soft Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('estadistica.parametres'); ta.wrap='hard';">Hard Wrap</a></li>
              </ul>
              </div>
           </td>
        </tr>
        </c:if>
        
