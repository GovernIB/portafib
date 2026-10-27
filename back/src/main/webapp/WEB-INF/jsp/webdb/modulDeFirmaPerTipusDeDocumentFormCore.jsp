<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="ModulDeFirmaPerTipusDeDocumentFields" className="es.caib.portafib.model.fields.ModulDeFirmaPerTipusDeDocumentFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,ModulDeFirmaPerTipusDeDocumentFields.NOM)}">
        <tr id="modulDeFirmaPerTipusDeDocument_nom_rowid">
          <td id="modulDeFirmaPerTipusDeDocument_nom_columnlabelid">
            <label for="modulDeFirmaPerTipusDeDocument.nom">
              <fmt:message key="${(empty __theForm.labels[ModulDeFirmaPerTipusDeDocumentFields.NOM])?'modulDeFirmaPerTipusDeDocument.nom':__theForm.labels[ModulDeFirmaPerTipusDeDocumentFields.NOM]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[ModulDeFirmaPerTipusDeDocumentFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ModulDeFirmaPerTipusDeDocumentFields.NOM]}" ></i>
              </c:if>
            </td>
          <td id="modulDeFirmaPerTipusDeDocument_nom_columnvalueid">
            <form:errors path="modulDeFirmaPerTipusDeDocument.nom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ModulDeFirmaPerTipusDeDocumentFields.NOM)? 'readonly' : ''}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,ModulDeFirmaPerTipusDeDocumentFields.NOM)? ' uneditable-input' : ''}"  style="" maxlength="100" path="modulDeFirmaPerTipusDeDocument.nom"  required="required"  aria-required="true"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ModulDeFirmaPerTipusDeDocumentFields.TIPUSDOCUMENTID)}">
        <tr id="modulDeFirmaPerTipusDeDocument_tipusDocumentID_rowid">
          <td id="modulDeFirmaPerTipusDeDocument_tipusDocumentID_columnlabelid">
            <label for="modulDeFirmaPerTipusDeDocument.tipusDocumentID">
              <fmt:message key="${(empty __theForm.labels[ModulDeFirmaPerTipusDeDocumentFields.TIPUSDOCUMENTID])?'modulDeFirmaPerTipusDeDocument.tipusDocumentID':__theForm.labels[ModulDeFirmaPerTipusDeDocumentFields.TIPUSDOCUMENTID]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[ModulDeFirmaPerTipusDeDocumentFields.TIPUSDOCUMENTID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ModulDeFirmaPerTipusDeDocumentFields.TIPUSDOCUMENTID]}" ></i>
              </c:if>
            </td>
          <td id="modulDeFirmaPerTipusDeDocument_tipusDocumentID_columnvalueid">
          <form:errors path="modulDeFirmaPerTipusDeDocument.tipusDocumentID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,ModulDeFirmaPerTipusDeDocumentFields.TIPUSDOCUMENTID)}" >
          <form:hidden path="modulDeFirmaPerTipusDeDocument.tipusDocumentID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.modulDeFirmaPerTipusDeDocument.tipusDocumentID,__theForm.listOfTipusDocumentForTipusDocumentID)}" aria-label="modulDeFirmaPerTipusDeDocument.tipusDocumentID"   required aria-required="true"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,ModulDeFirmaPerTipusDeDocumentFields.TIPUSDOCUMENTID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="modulDeFirmaPerTipusDeDocument.tipusDocumentID" cssClass="form-control col-md-9-optional"  required="required"  aria-required="true"  onchange="if(typeof onChangeTipusDocumentID == 'function') {  onChangeTipusDocumentID(this); };">


            <c:forEach items="${__theForm.listOfTipusDocumentForTipusDocumentID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ModulDeFirmaPerTipusDeDocumentFields.PLUGINID)}">
        <tr id="modulDeFirmaPerTipusDeDocument_pluginID_rowid">
          <td id="modulDeFirmaPerTipusDeDocument_pluginID_columnlabelid">
            <label for="modulDeFirmaPerTipusDeDocument.pluginID">
              <fmt:message key="${(empty __theForm.labels[ModulDeFirmaPerTipusDeDocumentFields.PLUGINID])?'modulDeFirmaPerTipusDeDocument.pluginID':__theForm.labels[ModulDeFirmaPerTipusDeDocumentFields.PLUGINID]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[ModulDeFirmaPerTipusDeDocumentFields.PLUGINID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ModulDeFirmaPerTipusDeDocumentFields.PLUGINID]}" ></i>
              </c:if>
            </td>
          <td id="modulDeFirmaPerTipusDeDocument_pluginID_columnvalueid">
          <form:errors path="modulDeFirmaPerTipusDeDocument.pluginID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,ModulDeFirmaPerTipusDeDocumentFields.PLUGINID)}" >
          <form:hidden path="modulDeFirmaPerTipusDeDocument.pluginID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.modulDeFirmaPerTipusDeDocument.pluginID,__theForm.listOfPluginForPluginID)}" aria-label="modulDeFirmaPerTipusDeDocument.pluginID"   required aria-required="true"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,ModulDeFirmaPerTipusDeDocumentFields.PLUGINID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="modulDeFirmaPerTipusDeDocument.pluginID" cssClass="form-control col-md-9-optional"  required="required"  aria-required="true"  onchange="if(typeof onChangePluginID == 'function') {  onChangePluginID(this); };">


            <c:forEach items="${__theForm.listOfPluginForPluginID}" var="tmp">
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
        
