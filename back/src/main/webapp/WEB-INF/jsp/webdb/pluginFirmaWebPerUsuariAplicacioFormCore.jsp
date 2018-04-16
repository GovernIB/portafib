<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PluginFirmaWebPerUsuariAplicacioFields" className="es.caib.portafib.model.fields.PluginFirmaWebPerUsuariAplicacioFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginFirmaWebPerUsuariAplicacioFields.USUARIAPLICACIOID)}">
        <tr id="pluginFirmaWebPerUsuariAplicacio_usuariAplicacioID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginFirmaWebPerUsuariAplicacioFields.USUARIAPLICACIOID])?'pluginFirmaWebPerUsuariAplicacio.usuariAplicacioID':__theForm.labels[PluginFirmaWebPerUsuariAplicacioFields.USUARIAPLICACIOID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PluginFirmaWebPerUsuariAplicacioFields.USUARIAPLICACIOID]}">
              <i class="icon-info-sign" title="${__theForm.help[PluginFirmaWebPerUsuariAplicacioFields.USUARIAPLICACIOID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="pluginFirmaWebPerUsuariAplicacio.usuariAplicacioID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PluginFirmaWebPerUsuariAplicacioFields.USUARIAPLICACIOID)}" >
          <form:hidden path="pluginFirmaWebPerUsuariAplicacio.usuariAplicacioID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.pluginFirmaWebPerUsuariAplicacio.usuariAplicacioID,__theForm.listOfUsuariAplicacioForUsuariAplicacioID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PluginFirmaWebPerUsuariAplicacioFields.USUARIAPLICACIOID)}" >
          <form:select id="pluginFirmaWebPerUsuariAplicacio_usuariAplicacioID"  onchange="if(typeof onChangeUsuariAplicacioID == 'function') {  onChangeUsuariAplicacioID(this); };"  cssClass="input-xxlarge" path="pluginFirmaWebPerUsuariAplicacio.usuariAplicacioID">
            <c:forEach items="${__theForm.listOfUsuariAplicacioForUsuariAplicacioID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginFirmaWebPerUsuariAplicacioFields.PLUGINFIRMAWEBID)}">
        <tr id="pluginFirmaWebPerUsuariAplicacio_pluginFirmaWebID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginFirmaWebPerUsuariAplicacioFields.PLUGINFIRMAWEBID])?'pluginFirmaWebPerUsuariAplicacio.pluginFirmaWebID':__theForm.labels[PluginFirmaWebPerUsuariAplicacioFields.PLUGINFIRMAWEBID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PluginFirmaWebPerUsuariAplicacioFields.PLUGINFIRMAWEBID]}">
              <i class="icon-info-sign" title="${__theForm.help[PluginFirmaWebPerUsuariAplicacioFields.PLUGINFIRMAWEBID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="pluginFirmaWebPerUsuariAplicacio.pluginFirmaWebID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PluginFirmaWebPerUsuariAplicacioFields.PLUGINFIRMAWEBID)}" >
          <form:hidden path="pluginFirmaWebPerUsuariAplicacio.pluginFirmaWebID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.pluginFirmaWebPerUsuariAplicacio.pluginFirmaWebID,__theForm.listOfPluginForPluginFirmaWebID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PluginFirmaWebPerUsuariAplicacioFields.PLUGINFIRMAWEBID)}" >
          <form:select id="pluginFirmaWebPerUsuariAplicacio_pluginFirmaWebID"  onchange="if(typeof onChangePluginFirmaWebID == 'function') {  onChangePluginFirmaWebID(this); };"  cssClass="input-xxlarge" path="pluginFirmaWebPerUsuariAplicacio.pluginFirmaWebID">
            <c:forEach items="${__theForm.listOfPluginForPluginFirmaWebID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginFirmaWebPerUsuariAplicacioFields.ACCIO)}">
        <tr id="pluginFirmaWebPerUsuariAplicacio_accio_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginFirmaWebPerUsuariAplicacioFields.ACCIO])?'pluginFirmaWebPerUsuariAplicacio.accio':__theForm.labels[PluginFirmaWebPerUsuariAplicacioFields.ACCIO]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[PluginFirmaWebPerUsuariAplicacioFields.ACCIO]}">
              <i class="icon-info-sign" title="${__theForm.help[PluginFirmaWebPerUsuariAplicacioFields.ACCIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="pluginFirmaWebPerUsuariAplicacio.accio" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PluginFirmaWebPerUsuariAplicacioFields.ACCIO)}" >
          <form:hidden path="pluginFirmaWebPerUsuariAplicacio.accio"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.pluginFirmaWebPerUsuariAplicacio.accio,__theForm.listOfValuesForAccio)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PluginFirmaWebPerUsuariAplicacioFields.ACCIO)}" >
          <form:select id="pluginFirmaWebPerUsuariAplicacio_accio"  onchange="if(typeof onChangeAccio == 'function') {  onChangeAccio(this); };"  cssClass="input-xxlarge" path="pluginFirmaWebPerUsuariAplicacio.accio">
            <c:forEach items="${__theForm.listOfValuesForAccio}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
