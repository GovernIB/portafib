<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PluginFirmaWebPerUsuariEntitatFields" className="es.caib.portafib.model.fields.PluginFirmaWebPerUsuariEntitatFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginFirmaWebPerUsuariEntitatFields.USUARIENTITATID)}">
        <tr id="pluginFirmaWebPerUsuariEntitat_usuariEntitatID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginFirmaWebPerUsuariEntitatFields.USUARIENTITATID])?'pluginFirmaWebPerUsuariEntitat.usuariEntitatID':__theForm.labels[PluginFirmaWebPerUsuariEntitatFields.USUARIENTITATID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[PluginFirmaWebPerUsuariEntitatFields.USUARIENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginFirmaWebPerUsuariEntitatFields.USUARIENTITATID]}" ></i>
              </c:if>
            </td>
            <td>
          <form:errors path="pluginFirmaWebPerUsuariEntitat.usuariEntitatID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PluginFirmaWebPerUsuariEntitatFields.USUARIENTITATID)}" >
          <form:hidden path="pluginFirmaWebPerUsuariEntitat.usuariEntitatID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.pluginFirmaWebPerUsuariEntitat.usuariEntitatID,__theForm.listOfUsuariEntitatForUsuariEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PluginFirmaWebPerUsuariEntitatFields.USUARIENTITATID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="pluginFirmaWebPerUsuariEntitat_usuariEntitatID"  onchange="if(typeof onChangeUsuariEntitatID == 'function') {  onChangeUsuariEntitatID(this); };"  cssClass="form-control col-md-9-optional" path="pluginFirmaWebPerUsuariEntitat.usuariEntitatID">
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
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginFirmaWebPerUsuariEntitatFields.PLUGINFIRMAWEBID])?'pluginFirmaWebPerUsuariEntitat.pluginFirmaWebID':__theForm.labels[PluginFirmaWebPerUsuariEntitatFields.PLUGINFIRMAWEBID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[PluginFirmaWebPerUsuariEntitatFields.PLUGINFIRMAWEBID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginFirmaWebPerUsuariEntitatFields.PLUGINFIRMAWEBID]}" ></i>
              </c:if>
            </td>
            <td>
          <form:errors path="pluginFirmaWebPerUsuariEntitat.pluginFirmaWebID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PluginFirmaWebPerUsuariEntitatFields.PLUGINFIRMAWEBID)}" >
          <form:hidden path="pluginFirmaWebPerUsuariEntitat.pluginFirmaWebID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.pluginFirmaWebPerUsuariEntitat.pluginFirmaWebID,__theForm.listOfPluginForPluginFirmaWebID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PluginFirmaWebPerUsuariEntitatFields.PLUGINFIRMAWEBID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="pluginFirmaWebPerUsuariEntitat_pluginFirmaWebID"  onchange="if(typeof onChangePluginFirmaWebID == 'function') {  onChangePluginFirmaWebID(this); };"  cssClass="form-control col-md-9-optional" path="pluginFirmaWebPerUsuariEntitat.pluginFirmaWebID">
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
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginFirmaWebPerUsuariEntitatFields.ACCIO])?'pluginFirmaWebPerUsuariEntitat.accio':__theForm.labels[PluginFirmaWebPerUsuariEntitatFields.ACCIO]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[PluginFirmaWebPerUsuariEntitatFields.ACCIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginFirmaWebPerUsuariEntitatFields.ACCIO]}" ></i>
              </c:if>
            </td>
            <td>
          <form:errors path="pluginFirmaWebPerUsuariEntitat.accio" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PluginFirmaWebPerUsuariEntitatFields.ACCIO)}" >
          <form:hidden path="pluginFirmaWebPerUsuariEntitat.accio"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.pluginFirmaWebPerUsuariEntitat.accio,__theForm.listOfValuesForAccio)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PluginFirmaWebPerUsuariEntitatFields.ACCIO)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="pluginFirmaWebPerUsuariEntitat_accio"  onchange="if(typeof onChangeAccio == 'function') {  onChangeAccio(this); };"  cssClass="form-control col-md-9-optional" path="pluginFirmaWebPerUsuariEntitat.accio">
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
        
