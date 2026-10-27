<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PluginFirmaWebPerUsuariEntitatFields" className="es.caib.portafib.model.fields.PluginFirmaWebPerUsuariEntitatFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginFirmaWebPerUsuariEntitatFields.USUARIENTITATID)}">
        <tr id="pluginFirmaWebPerUsuariEntitat_usuariEntitatID_rowid">
          <td id="pluginFirmaWebPerUsuariEntitat_usuariEntitatID_columnlabelid">
            <label for="pluginFirmaWebPerUsuariEntitat.usuariEntitatID">
              <fmt:message key="${(empty __theForm.labels[PluginFirmaWebPerUsuariEntitatFields.USUARIENTITATID])?'pluginFirmaWebPerUsuariEntitat.usuariEntitatID':__theForm.labels[PluginFirmaWebPerUsuariEntitatFields.USUARIENTITATID]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[PluginFirmaWebPerUsuariEntitatFields.USUARIENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginFirmaWebPerUsuariEntitatFields.USUARIENTITATID]}" ></i>
              </c:if>
            </td>
          <td id="pluginFirmaWebPerUsuariEntitat_usuariEntitatID_columnvalueid">
          <form:errors path="pluginFirmaWebPerUsuariEntitat.usuariEntitatID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PluginFirmaWebPerUsuariEntitatFields.USUARIENTITATID)}" >
          <form:hidden path="pluginFirmaWebPerUsuariEntitat.usuariEntitatID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.pluginFirmaWebPerUsuariEntitat.usuariEntitatID,__theForm.listOfUsuariEntitatForUsuariEntitatID)}" aria-label="pluginFirmaWebPerUsuariEntitat.usuariEntitatID"   required aria-required="true"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PluginFirmaWebPerUsuariEntitatFields.USUARIENTITATID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="pluginFirmaWebPerUsuariEntitat.usuariEntitatID" cssClass="form-control col-md-9-optional"  required="required"  aria-required="true"  onchange="if(typeof onChangeUsuariEntitatID == 'function') {  onChangeUsuariEntitatID(this); };">


            <c:forEach items="${__theForm.listOfUsuariEntitatForUsuariEntitatID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginFirmaWebPerUsuariEntitatFields.PLUGINFIRMAWEBID)}">
        <tr id="pluginFirmaWebPerUsuariEntitat_pluginFirmaWebID_rowid">
          <td id="pluginFirmaWebPerUsuariEntitat_pluginFirmaWebID_columnlabelid">
            <label for="pluginFirmaWebPerUsuariEntitat.pluginFirmaWebID">
              <fmt:message key="${(empty __theForm.labels[PluginFirmaWebPerUsuariEntitatFields.PLUGINFIRMAWEBID])?'pluginFirmaWebPerUsuariEntitat.pluginFirmaWebID':__theForm.labels[PluginFirmaWebPerUsuariEntitatFields.PLUGINFIRMAWEBID]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[PluginFirmaWebPerUsuariEntitatFields.PLUGINFIRMAWEBID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginFirmaWebPerUsuariEntitatFields.PLUGINFIRMAWEBID]}" ></i>
              </c:if>
            </td>
          <td id="pluginFirmaWebPerUsuariEntitat_pluginFirmaWebID_columnvalueid">
          <form:errors path="pluginFirmaWebPerUsuariEntitat.pluginFirmaWebID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PluginFirmaWebPerUsuariEntitatFields.PLUGINFIRMAWEBID)}" >
          <form:hidden path="pluginFirmaWebPerUsuariEntitat.pluginFirmaWebID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.pluginFirmaWebPerUsuariEntitat.pluginFirmaWebID,__theForm.listOfPluginForPluginFirmaWebID)}" aria-label="pluginFirmaWebPerUsuariEntitat.pluginFirmaWebID"   required aria-required="true"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PluginFirmaWebPerUsuariEntitatFields.PLUGINFIRMAWEBID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="pluginFirmaWebPerUsuariEntitat.pluginFirmaWebID" cssClass="form-control col-md-9-optional"  required="required"  aria-required="true"  onchange="if(typeof onChangePluginFirmaWebID == 'function') {  onChangePluginFirmaWebID(this); };">


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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginFirmaWebPerUsuariEntitatFields.ACCIO)}">
        <tr id="pluginFirmaWebPerUsuariEntitat_accio_rowid">
          <td id="pluginFirmaWebPerUsuariEntitat_accio_columnlabelid">
            <label for="pluginFirmaWebPerUsuariEntitat.accio">
              <fmt:message key="${(empty __theForm.labels[PluginFirmaWebPerUsuariEntitatFields.ACCIO])?'pluginFirmaWebPerUsuariEntitat.accio':__theForm.labels[PluginFirmaWebPerUsuariEntitatFields.ACCIO]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[PluginFirmaWebPerUsuariEntitatFields.ACCIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginFirmaWebPerUsuariEntitatFields.ACCIO]}" ></i>
              </c:if>
            </td>
          <td id="pluginFirmaWebPerUsuariEntitat_accio_columnvalueid">
          <form:errors path="pluginFirmaWebPerUsuariEntitat.accio" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PluginFirmaWebPerUsuariEntitatFields.ACCIO)}" >
          <form:hidden path="pluginFirmaWebPerUsuariEntitat.accio"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.pluginFirmaWebPerUsuariEntitat.accio,__theForm.listOfValuesForAccio)}" aria-label="pluginFirmaWebPerUsuariEntitat.accio"   required aria-required="true"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PluginFirmaWebPerUsuariEntitatFields.ACCIO)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="pluginFirmaWebPerUsuariEntitat.accio" cssClass="form-control col-md-9-optional"  required="required"  aria-required="true"  onchange="if(typeof onChangeAccio == 'function') {  onChangeAccio(this); };">


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
        
