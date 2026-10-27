<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PluginFirmaWebPerUsuariAplicacioFields" className="es.caib.portafib.model.fields.PluginFirmaWebPerUsuariAplicacioFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginFirmaWebPerUsuariAplicacioFields.USUARIAPLICACIOID)}">
        <tr id="pluginFirmaWebPerUsuariAplicacio_usuariAplicacioID_rowid">
          <td id="pluginFirmaWebPerUsuariAplicacio_usuariAplicacioID_columnlabelid">
            <label for="pluginFirmaWebPerUsuariAplicacio.usuariAplicacioID">
              <fmt:message key="${(empty __theForm.labels[PluginFirmaWebPerUsuariAplicacioFields.USUARIAPLICACIOID])?'pluginFirmaWebPerUsuariAplicacio.usuariAplicacioID':__theForm.labels[PluginFirmaWebPerUsuariAplicacioFields.USUARIAPLICACIOID]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[PluginFirmaWebPerUsuariAplicacioFields.USUARIAPLICACIOID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginFirmaWebPerUsuariAplicacioFields.USUARIAPLICACIOID]}" ></i>
              </c:if>
            </td>
          <td id="pluginFirmaWebPerUsuariAplicacio_usuariAplicacioID_columnvalueid">
          <form:errors path="pluginFirmaWebPerUsuariAplicacio.usuariAplicacioID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PluginFirmaWebPerUsuariAplicacioFields.USUARIAPLICACIOID)}" >
          <form:hidden path="pluginFirmaWebPerUsuariAplicacio.usuariAplicacioID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.pluginFirmaWebPerUsuariAplicacio.usuariAplicacioID,__theForm.listOfUsuariAplicacioForUsuariAplicacioID)}" aria-label="pluginFirmaWebPerUsuariAplicacio.usuariAplicacioID"   required aria-required="true"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PluginFirmaWebPerUsuariAplicacioFields.USUARIAPLICACIOID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="pluginFirmaWebPerUsuariAplicacio.usuariAplicacioID" cssClass="form-control col-md-9-optional"  required="required"  aria-required="true"  onchange="if(typeof onChangeUsuariAplicacioID == 'function') {  onChangeUsuariAplicacioID(this); };">


            <c:forEach items="${__theForm.listOfUsuariAplicacioForUsuariAplicacioID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginFirmaWebPerUsuariAplicacioFields.PLUGINFIRMAWEBID)}">
        <tr id="pluginFirmaWebPerUsuariAplicacio_pluginFirmaWebID_rowid">
          <td id="pluginFirmaWebPerUsuariAplicacio_pluginFirmaWebID_columnlabelid">
            <label for="pluginFirmaWebPerUsuariAplicacio.pluginFirmaWebID">
              <fmt:message key="${(empty __theForm.labels[PluginFirmaWebPerUsuariAplicacioFields.PLUGINFIRMAWEBID])?'pluginFirmaWebPerUsuariAplicacio.pluginFirmaWebID':__theForm.labels[PluginFirmaWebPerUsuariAplicacioFields.PLUGINFIRMAWEBID]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[PluginFirmaWebPerUsuariAplicacioFields.PLUGINFIRMAWEBID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginFirmaWebPerUsuariAplicacioFields.PLUGINFIRMAWEBID]}" ></i>
              </c:if>
            </td>
          <td id="pluginFirmaWebPerUsuariAplicacio_pluginFirmaWebID_columnvalueid">
          <form:errors path="pluginFirmaWebPerUsuariAplicacio.pluginFirmaWebID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PluginFirmaWebPerUsuariAplicacioFields.PLUGINFIRMAWEBID)}" >
          <form:hidden path="pluginFirmaWebPerUsuariAplicacio.pluginFirmaWebID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.pluginFirmaWebPerUsuariAplicacio.pluginFirmaWebID,__theForm.listOfPluginForPluginFirmaWebID)}" aria-label="pluginFirmaWebPerUsuariAplicacio.pluginFirmaWebID"   required aria-required="true"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PluginFirmaWebPerUsuariAplicacioFields.PLUGINFIRMAWEBID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="pluginFirmaWebPerUsuariAplicacio.pluginFirmaWebID" cssClass="form-control col-md-9-optional"  required="required"  aria-required="true"  onchange="if(typeof onChangePluginFirmaWebID == 'function') {  onChangePluginFirmaWebID(this); };">


            <c:forEach items="${__theForm.listOfPluginForPluginFirmaWebID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginFirmaWebPerUsuariAplicacioFields.ACCIO)}">
        <tr id="pluginFirmaWebPerUsuariAplicacio_accio_rowid">
          <td id="pluginFirmaWebPerUsuariAplicacio_accio_columnlabelid">
            <label for="pluginFirmaWebPerUsuariAplicacio.accio">
              <fmt:message key="${(empty __theForm.labels[PluginFirmaWebPerUsuariAplicacioFields.ACCIO])?'pluginFirmaWebPerUsuariAplicacio.accio':__theForm.labels[PluginFirmaWebPerUsuariAplicacioFields.ACCIO]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[PluginFirmaWebPerUsuariAplicacioFields.ACCIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginFirmaWebPerUsuariAplicacioFields.ACCIO]}" ></i>
              </c:if>
            </td>
          <td id="pluginFirmaWebPerUsuariAplicacio_accio_columnvalueid">
          <form:errors path="pluginFirmaWebPerUsuariAplicacio.accio" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PluginFirmaWebPerUsuariAplicacioFields.ACCIO)}" >
          <form:hidden path="pluginFirmaWebPerUsuariAplicacio.accio"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.pluginFirmaWebPerUsuariAplicacio.accio,__theForm.listOfValuesForAccio)}" aria-label="pluginFirmaWebPerUsuariAplicacio.accio"   required aria-required="true"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PluginFirmaWebPerUsuariAplicacioFields.ACCIO)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="pluginFirmaWebPerUsuariAplicacio.accio" cssClass="form-control col-md-9-optional"  required="required"  aria-required="true"  onchange="if(typeof onChangeAccio == 'function') {  onChangeAccio(this); };">


            <c:forEach items="${__theForm.listOfValuesForAccio}" var="tmp">
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
        
