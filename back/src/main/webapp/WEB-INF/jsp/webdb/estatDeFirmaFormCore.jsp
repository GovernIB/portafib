<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="EstatDeFirmaFields" className="es.caib.portafib.model.fields.EstatDeFirmaFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstatDeFirmaFields.FIRMAID)}">
        <tr id="estatDeFirma_firmaID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EstatDeFirmaFields.FIRMAID])?'estatDeFirma.firmaID':__theForm.labels[EstatDeFirmaFields.FIRMAID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EstatDeFirmaFields.FIRMAID]}">
              <i class="icon-info-sign" title="${__theForm.help[EstatDeFirmaFields.FIRMAID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="estatDeFirma.firmaID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EstatDeFirmaFields.FIRMAID)}" >
          <form:hidden path="estatDeFirma.firmaID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.estatDeFirma.firmaID,__theForm.listOfFirmaForFirmaID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EstatDeFirmaFields.FIRMAID)}" >
          <form:select id="estatDeFirma_firmaID"  onchange="if(typeof onChangeFirmaID == 'function') {  onChangeFirmaID(this); };"  cssClass="input-xxlarge" path="estatDeFirma.firmaID">
            <c:forEach items="${__theForm.listOfFirmaForFirmaID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstatDeFirmaFields.USUARIENTITATID)}">
        <tr id="estatDeFirma_usuariEntitatID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EstatDeFirmaFields.USUARIENTITATID])?'estatDeFirma.usuariEntitatID':__theForm.labels[EstatDeFirmaFields.USUARIENTITATID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EstatDeFirmaFields.USUARIENTITATID]}">
              <i class="icon-info-sign" title="${__theForm.help[EstatDeFirmaFields.USUARIENTITATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="estatDeFirma.usuariEntitatID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EstatDeFirmaFields.USUARIENTITATID)}" >
          <form:hidden path="estatDeFirma.usuariEntitatID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.estatDeFirma.usuariEntitatID,__theForm.listOfUsuariEntitatForUsuariEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EstatDeFirmaFields.USUARIENTITATID)}" >
          <form:select id="estatDeFirma_usuariEntitatID"  onchange="if(typeof onChangeUsuariEntitatID == 'function') {  onChangeUsuariEntitatID(this); };"  cssClass="input-xxlarge" path="estatDeFirma.usuariEntitatID">
            <c:forEach items="${__theForm.listOfUsuariEntitatForUsuariEntitatID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstatDeFirmaFields.DATAINICI)}">
        <tr id="estatDeFirma_dataInici_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EstatDeFirmaFields.DATAINICI])?'estatDeFirma.dataInici':__theForm.labels[EstatDeFirmaFields.DATAINICI]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EstatDeFirmaFields.DATAINICI]}">
              <i class="icon-info-sign" title="${__theForm.help[EstatDeFirmaFields.DATAINICI]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="estatDeFirma.dataInici" cssClass="errorField alert alert-error" />
              <div id="dataInici" class="input-append">
                <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EstatDeFirmaFields.DATAINICI)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,EstatDeFirmaFields.DATAINICI)? 'input-medium uneditable-input' : 'input-medium'}"  path="estatDeFirma.dataInici" />
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,EstatDeFirmaFields.DATAINICI)}" >
                <span class="add-on">
                  <i data-time-icon="icon-time" data-date-icon="icon-calendar">
                  </i>
                </span>
              </c:if>
              </div>
              <script type="text/javascript">                
                $(function() {
                  $('#dataInici').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstatDeFirmaFields.DATAFI)}">
        <tr id="estatDeFirma_dataFi_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EstatDeFirmaFields.DATAFI])?'estatDeFirma.dataFi':__theForm.labels[EstatDeFirmaFields.DATAFI]}" />
              <c:if test="${not empty __theForm.help[EstatDeFirmaFields.DATAFI]}">
              <i class="icon-info-sign" title="${__theForm.help[EstatDeFirmaFields.DATAFI]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="estatDeFirma.dataFi" cssClass="errorField alert alert-error" />
              <div id="dataFi" class="input-append">
                <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EstatDeFirmaFields.DATAFI)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,EstatDeFirmaFields.DATAFI)? 'input-medium uneditable-input' : 'input-medium'}"  path="estatDeFirma.dataFi" />
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,EstatDeFirmaFields.DATAFI)}" >
                <span class="add-on">
                  <i data-time-icon="icon-time" data-date-icon="icon-calendar">
                  </i>
                </span>
              </c:if>
              </div>
              <script type="text/javascript">                
                $(function() {
                  $('#dataFi').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID)}">
        <tr id="estatDeFirma_tipusEstatDeFirmaInicialID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID])?'estatDeFirma.tipusEstatDeFirmaInicialID':__theForm.labels[EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID]}">
              <i class="icon-info-sign" title="${__theForm.help[EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="estatDeFirma.tipusEstatDeFirmaInicialID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID)}" >
          <form:hidden path="estatDeFirma.tipusEstatDeFirmaInicialID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.estatDeFirma.tipusEstatDeFirmaInicialID,__theForm.listOfValuesForTipusEstatDeFirmaInicialID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID)}" >
          <form:select id="estatDeFirma_tipusEstatDeFirmaInicialID"  onchange="if(typeof onChangeTipusEstatDeFirmaInicialID == 'function') {  onChangeTipusEstatDeFirmaInicialID(this); };"  cssClass="input-xxlarge" path="estatDeFirma.tipusEstatDeFirmaInicialID">
            <c:forEach items="${__theForm.listOfValuesForTipusEstatDeFirmaInicialID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstatDeFirmaFields.TIPUSESTATDEFIRMAFINALID)}">
        <tr id="estatDeFirma_tipusEstatDeFirmaFinalID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EstatDeFirmaFields.TIPUSESTATDEFIRMAFINALID])?'estatDeFirma.tipusEstatDeFirmaFinalID':__theForm.labels[EstatDeFirmaFields.TIPUSESTATDEFIRMAFINALID]}" />
              <c:if test="${not empty __theForm.help[EstatDeFirmaFields.TIPUSESTATDEFIRMAFINALID]}">
              <i class="icon-info-sign" title="${__theForm.help[EstatDeFirmaFields.TIPUSESTATDEFIRMAFINALID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="estatDeFirma.tipusEstatDeFirmaFinalID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EstatDeFirmaFields.TIPUSESTATDEFIRMAFINALID)}" >
          <form:hidden path="estatDeFirma.tipusEstatDeFirmaFinalID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.estatDeFirma.tipusEstatDeFirmaFinalID,__theForm.listOfValuesForTipusEstatDeFirmaFinalID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EstatDeFirmaFields.TIPUSESTATDEFIRMAFINALID)}" >
          <form:select id="estatDeFirma_tipusEstatDeFirmaFinalID"  onchange="if(typeof onChangeTipusEstatDeFirmaFinalID == 'function') {  onChangeTipusEstatDeFirmaFinalID(this); };"  cssClass="input-xxlarge" path="estatDeFirma.tipusEstatDeFirmaFinalID">
          <%-- El camp pot ser null, per la qual cosa afegim una entrada buida --%>
          <form:option value="" ></form:option>
            <c:forEach items="${__theForm.listOfValuesForTipusEstatDeFirmaFinalID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstatDeFirmaFields.COLABORACIODELEGACIOID)}">
        <tr id="estatDeFirma_colaboracioDelegacioID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EstatDeFirmaFields.COLABORACIODELEGACIOID])?'estatDeFirma.colaboracioDelegacioID':__theForm.labels[EstatDeFirmaFields.COLABORACIODELEGACIOID]}" />
              <c:if test="${not empty __theForm.help[EstatDeFirmaFields.COLABORACIODELEGACIOID]}">
              <i class="icon-info-sign" title="${__theForm.help[EstatDeFirmaFields.COLABORACIODELEGACIOID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="estatDeFirma.colaboracioDelegacioID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EstatDeFirmaFields.COLABORACIODELEGACIOID)}" >
          <form:hidden path="estatDeFirma.colaboracioDelegacioID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.estatDeFirma.colaboracioDelegacioID,__theForm.listOfColaboracioDelegacioForColaboracioDelegacioID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EstatDeFirmaFields.COLABORACIODELEGACIOID)}" >
          <form:select id="estatDeFirma_colaboracioDelegacioID"  onchange="if(typeof onChangeColaboracioDelegacioID == 'function') {  onChangeColaboracioDelegacioID(this); };"  cssClass="input-xxlarge" path="estatDeFirma.colaboracioDelegacioID">
          <%-- El camp pot ser null, per la qual cosa afegim una entrada buida --%>
          <form:option value="" ></form:option>
            <c:forEach items="${__theForm.listOfColaboracioDelegacioForColaboracioDelegacioID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstatDeFirmaFields.DESCRIPCIO)}">
        <tr id="estatDeFirma_descripcio_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EstatDeFirmaFields.DESCRIPCIO])?'estatDeFirma.descripcio':__theForm.labels[EstatDeFirmaFields.DESCRIPCIO]}" />
              <c:if test="${not empty __theForm.help[EstatDeFirmaFields.DESCRIPCIO]}">
              <i class="icon-info-sign" title="${__theForm.help[EstatDeFirmaFields.DESCRIPCIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="estatDeFirma.descripcio" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,EstatDeFirmaFields.DESCRIPCIO)? 'true' : 'false'}" path="estatDeFirma.descripcio"  />
              <div class="btn-group" style="vertical-align: top;">
              <button class="btn btn-mini dropdown-toggle" data-toggle="dropdown">&nbsp;<span class="caret"></span></button>
              <ul class="dropdown-menu">
                <li><a href="#" onclick="javascript:var ta=document.getElementById('estatDeFirma.descripcio'); ta.wrap='off';" >No Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('estatDeFirma.descripcio'); ta.wrap='soft';">Soft Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('estatDeFirma.descripcio'); ta.wrap='hard';">Hard Wrap</a></li>
              </ul>
              </div>
           </td>
        </tr>
        </c:if>
        
