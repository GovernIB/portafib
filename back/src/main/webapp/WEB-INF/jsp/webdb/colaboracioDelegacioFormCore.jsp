<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="ColaboracioDelegacioFields" className="es.caib.portafib.model.fields.ColaboracioDelegacioFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,ColaboracioDelegacioFields.DESTINATARIID)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[ColaboracioDelegacioFields.DESTINATARIID])?'colaboracioDelegacio.destinatariID':__theForm.labels[ColaboracioDelegacioFields.DESTINATARIID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[ColaboracioDelegacioFields.DESTINATARIID]}">
              <i class="icon-info-sign" title="${__theForm.help[ColaboracioDelegacioFields.DESTINATARIID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="colaboracioDelegacio.destinatariID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,ColaboracioDelegacioFields.DESTINATARIID)}" >
          <form:hidden path="colaboracioDelegacio.destinatariID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.colaboracioDelegacio.destinatariID,__theForm.listOfUsuariEntitatForDestinatariID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,ColaboracioDelegacioFields.DESTINATARIID)}" >
          <form:select id="colaboracioDelegacio_destinatariID"  onchange="if(typeof onChangeDestinatariID == 'function') {  onChangeDestinatariID(this); };"  cssClass="input-xxlarge" path="colaboracioDelegacio.destinatariID">
            <c:forEach items="${__theForm.listOfUsuariEntitatForDestinatariID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ColaboracioDelegacioFields.COLABORADORDELEGATID)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[ColaboracioDelegacioFields.COLABORADORDELEGATID])?'colaboracioDelegacio.colaboradorDelegatID':__theForm.labels[ColaboracioDelegacioFields.COLABORADORDELEGATID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[ColaboracioDelegacioFields.COLABORADORDELEGATID]}">
              <i class="icon-info-sign" title="${__theForm.help[ColaboracioDelegacioFields.COLABORADORDELEGATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="colaboracioDelegacio.colaboradorDelegatID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,ColaboracioDelegacioFields.COLABORADORDELEGATID)}" >
          <form:hidden path="colaboracioDelegacio.colaboradorDelegatID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.colaboracioDelegacio.colaboradorDelegatID,__theForm.listOfUsuariEntitatForColaboradorDelegatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,ColaboracioDelegacioFields.COLABORADORDELEGATID)}" >
          <form:select id="colaboracioDelegacio_colaboradorDelegatID"  onchange="if(typeof onChangeColaboradorDelegatID == 'function') {  onChangeColaboradorDelegatID(this); };"  cssClass="input-xxlarge" path="colaboracioDelegacio.colaboradorDelegatID">
            <c:forEach items="${__theForm.listOfUsuariEntitatForColaboradorDelegatID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ColaboracioDelegacioFields.ESDELEGAT)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[ColaboracioDelegacioFields.ESDELEGAT])?'colaboracioDelegacio.esDelegat':__theForm.labels[ColaboracioDelegacioFields.ESDELEGAT]}" />
              <c:if test="${not empty __theForm.help[ColaboracioDelegacioFields.ESDELEGAT]}">
              <i class="icon-info-sign" title="${__theForm.help[ColaboracioDelegacioFields.ESDELEGAT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,ColaboracioDelegacioFields.ESDELEGAT)}" >
              <form:errors path="colaboracioDelegacio.esDelegat" cssClass="errorField alert alert-error" />
              <form:checkbox onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,ColaboracioDelegacioFields.ESDELEGAT)? 'false' : 'true'}" path="colaboracioDelegacio.esDelegat" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,ColaboracioDelegacioFields.ESDELEGAT)}" >
                <fmt:message key="genapp.checkbox.${__theForm.colaboracioDelegacio.esDelegat}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ColaboracioDelegacioFields.MOTIU)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[ColaboracioDelegacioFields.MOTIU])?'colaboracioDelegacio.motiu':__theForm.labels[ColaboracioDelegacioFields.MOTIU]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[ColaboracioDelegacioFields.MOTIU]}">
              <i class="icon-info-sign" title="${__theForm.help[ColaboracioDelegacioFields.MOTIU]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="colaboracioDelegacio.motiu" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ColaboracioDelegacioFields.MOTIU)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,ColaboracioDelegacioFields.MOTIU)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="60" path="colaboracioDelegacio.motiu"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ColaboracioDelegacioFields.DESCRIPCIO)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[ColaboracioDelegacioFields.DESCRIPCIO])?'colaboracioDelegacio.descripcio':__theForm.labels[ColaboracioDelegacioFields.DESCRIPCIO]}" />
              <c:if test="${not empty __theForm.help[ColaboracioDelegacioFields.DESCRIPCIO]}">
              <i class="icon-info-sign" title="${__theForm.help[ColaboracioDelegacioFields.DESCRIPCIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="colaboracioDelegacio.descripcio" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,ColaboracioDelegacioFields.DESCRIPCIO)? 'true' : 'false'}" path="colaboracioDelegacio.descripcio"  />
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ColaboracioDelegacioFields.DATAINICI)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[ColaboracioDelegacioFields.DATAINICI])?'colaboracioDelegacio.dataInici':__theForm.labels[ColaboracioDelegacioFields.DATAINICI]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[ColaboracioDelegacioFields.DATAINICI]}">
              <i class="icon-info-sign" title="${__theForm.help[ColaboracioDelegacioFields.DATAINICI]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="colaboracioDelegacio.dataInici" cssClass="errorField alert alert-error" />
              <div id="dataInici" class="input-append">
                <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ColaboracioDelegacioFields.DATAINICI)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,ColaboracioDelegacioFields.DATAINICI)? 'input-medium uneditable-input' : 'input-medium'}"  path="colaboracioDelegacio.dataInici" />
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,ColaboracioDelegacioFields.DATAINICI)}" >
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ColaboracioDelegacioFields.DATAFI)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[ColaboracioDelegacioFields.DATAFI])?'colaboracioDelegacio.dataFi':__theForm.labels[ColaboracioDelegacioFields.DATAFI]}" />
              <c:if test="${not empty __theForm.help[ColaboracioDelegacioFields.DATAFI]}">
              <i class="icon-info-sign" title="${__theForm.help[ColaboracioDelegacioFields.DATAFI]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="colaboracioDelegacio.dataFi" cssClass="errorField alert alert-error" />
              <div id="dataFi" class="input-append">
                <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ColaboracioDelegacioFields.DATAFI)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,ColaboracioDelegacioFields.DATAFI)? 'input-medium uneditable-input' : 'input-medium'}"  path="colaboracioDelegacio.dataFi" />
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,ColaboracioDelegacioFields.DATAFI)}" >
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ColaboracioDelegacioFields.ACTIVA)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[ColaboracioDelegacioFields.ACTIVA])?'colaboracioDelegacio.activa':__theForm.labels[ColaboracioDelegacioFields.ACTIVA]}" />
              <c:if test="${not empty __theForm.help[ColaboracioDelegacioFields.ACTIVA]}">
              <i class="icon-info-sign" title="${__theForm.help[ColaboracioDelegacioFields.ACTIVA]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,ColaboracioDelegacioFields.ACTIVA)}" >
              <form:errors path="colaboracioDelegacio.activa" cssClass="errorField alert alert-error" />
              <form:checkbox onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,ColaboracioDelegacioFields.ACTIVA)? 'false' : 'true'}" path="colaboracioDelegacio.activa" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,ColaboracioDelegacioFields.ACTIVA)}" >
                <fmt:message key="genapp.checkbox.${__theForm.colaboracioDelegacio.activa}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ColaboracioDelegacioFields.REVISOR)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[ColaboracioDelegacioFields.REVISOR])?'colaboracioDelegacio.revisor':__theForm.labels[ColaboracioDelegacioFields.REVISOR]}" />
              <c:if test="${not empty __theForm.help[ColaboracioDelegacioFields.REVISOR]}">
              <i class="icon-info-sign" title="${__theForm.help[ColaboracioDelegacioFields.REVISOR]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,ColaboracioDelegacioFields.REVISOR)}" >
              <form:errors path="colaboracioDelegacio.revisor" cssClass="errorField alert alert-error" />
              <form:checkbox onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,ColaboracioDelegacioFields.REVISOR)? 'false' : 'true'}" path="colaboracioDelegacio.revisor" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,ColaboracioDelegacioFields.REVISOR)}" >
                <fmt:message key="genapp.checkbox.${__theForm.colaboracioDelegacio.revisor}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ColaboracioDelegacioFields.MOTIUDESHABILITADA)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[ColaboracioDelegacioFields.MOTIUDESHABILITADA])?'colaboracioDelegacio.motiuDeshabilitada':__theForm.labels[ColaboracioDelegacioFields.MOTIUDESHABILITADA]}" />
              <c:if test="${not empty __theForm.help[ColaboracioDelegacioFields.MOTIUDESHABILITADA]}">
              <i class="icon-info-sign" title="${__theForm.help[ColaboracioDelegacioFields.MOTIUDESHABILITADA]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="colaboracioDelegacio.motiuDeshabilitada" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,ColaboracioDelegacioFields.MOTIUDESHABILITADA)? 'true' : 'false'}" path="colaboracioDelegacio.motiuDeshabilitada"  />
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ColaboracioDelegacioFields.FITXERAUTORITZACIOID)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[ColaboracioDelegacioFields.FITXERAUTORITZACIOID])?'colaboracioDelegacio.fitxerAutoritzacioID':__theForm.labels[ColaboracioDelegacioFields.FITXERAUTORITZACIOID]}" />
              <c:if test="${not empty __theForm.help[ColaboracioDelegacioFields.FITXERAUTORITZACIOID]}">
              <i class="icon-info-sign" title="${__theForm.help[ColaboracioDelegacioFields.FITXERAUTORITZACIOID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="colaboracioDelegacio.fitxerAutoritzacioID" cssClass="errorField alert alert-error" />
              <div class="fileupload fileupload-new" data-provides="fileupload" style="margin-bottom: 0px">
                <div class="input-append">
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,ColaboracioDelegacioFields.FITXERAUTORITZACIOID)}" >
                    <div class="uneditable-input span3">
                      <i class="icon-file fileupload-exists"></i>
                      <span class="fileupload-preview"></span>
                    </div>
                    <span class="btn btn-file">
                      <span class="fileupload-new"><fmt:message key="genapp.form.file.select"/></span>
                      <span class="fileupload-exists"><fmt:message key="genapp.form.file.change"/></span>
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ColaboracioDelegacioFields.FITXERAUTORITZACIOID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,ColaboracioDelegacioFields.FITXERAUTORITZACIOID)? 'input uneditable-input' : 'input'}"  path="fitxerAutoritzacioID" type="file" />
                    </span>
                    <a href="#" class="btn fileupload-exists" data-dismiss="fileupload"><fmt:message key="genapp.form.file.unselect"/></a>
                    <span class="add-on">&nbsp;</span>
                </c:if>
                <c:if test="${not empty __theForm.colaboracioDelegacio.fitxerAutoritzacio}">
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,ColaboracioDelegacioFields.FITXERAUTORITZACIOID)}" >
                    <span class="add-on">
                        <form:checkbox path="fitxerAutoritzacioIDDelete"/>
                        <fmt:message key="genapp.form.file.delete"/>
                    </span>
                    <span class="add-on">&nbsp;</span>   
                </c:if>
                    <span class="add-on">
                        <a target="_blank" href="<c:url value="${pfi:fileUrl(__theForm.colaboracioDelegacio.fitxerAutoritzacio)}"/>">${__theForm.colaboracioDelegacio.fitxerAutoritzacio.nom}</a>
                    </span>
                </c:if>
                </div>
              </div>
           </td>
        </tr>
        </c:if>
        
