<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="ModulDeFirmaPerTipusDeDocumentFields" className="es.caib.portafib.model.fields.ModulDeFirmaPerTipusDeDocumentFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,ModulDeFirmaPerTipusDeDocumentFields.TIPUSDOCUMENTID)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[ModulDeFirmaPerTipusDeDocumentFields.TIPUSDOCUMENTID])?'modulDeFirmaPerTipusDeDocument.tipusDocumentID':__theForm.labels[ModulDeFirmaPerTipusDeDocumentFields.TIPUSDOCUMENTID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[ModulDeFirmaPerTipusDeDocumentFields.TIPUSDOCUMENTID]}">
              <i class="icon-info-sign" title="${__theForm.help[ModulDeFirmaPerTipusDeDocumentFields.TIPUSDOCUMENTID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="modulDeFirmaPerTipusDeDocument.tipusDocumentID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,ModulDeFirmaPerTipusDeDocumentFields.TIPUSDOCUMENTID)}" >
          <form:hidden path="modulDeFirmaPerTipusDeDocument.tipusDocumentID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.modulDeFirmaPerTipusDeDocument.tipusDocumentID,__theForm.listOfTipusDocumentForTipusDocumentID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,ModulDeFirmaPerTipusDeDocumentFields.TIPUSDOCUMENTID)}" >
          <form:select id="modulDeFirmaPerTipusDeDocument_tipusDocumentID"  onchange="if(typeof onChangeTipusDocumentID == 'function') {  onChangeTipusDocumentID(this); };"  cssClass="input-xxlarge" path="modulDeFirmaPerTipusDeDocument.tipusDocumentID">
            <c:forEach items="${__theForm.listOfTipusDocumentForTipusDocumentID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ModulDeFirmaPerTipusDeDocumentFields.MODULDEFIRMAID)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[ModulDeFirmaPerTipusDeDocumentFields.MODULDEFIRMAID])?'modulDeFirmaPerTipusDeDocument.modulDeFirmaID':__theForm.labels[ModulDeFirmaPerTipusDeDocumentFields.MODULDEFIRMAID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[ModulDeFirmaPerTipusDeDocumentFields.MODULDEFIRMAID]}">
              <i class="icon-info-sign" title="${__theForm.help[ModulDeFirmaPerTipusDeDocumentFields.MODULDEFIRMAID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="modulDeFirmaPerTipusDeDocument.modulDeFirmaID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,ModulDeFirmaPerTipusDeDocumentFields.MODULDEFIRMAID)}" >
          <form:hidden path="modulDeFirmaPerTipusDeDocument.modulDeFirmaID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.modulDeFirmaPerTipusDeDocument.modulDeFirmaID,__theForm.listOfModulDeFirmaForModulDeFirmaID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,ModulDeFirmaPerTipusDeDocumentFields.MODULDEFIRMAID)}" >
          <form:select id="modulDeFirmaPerTipusDeDocument_modulDeFirmaID"  onchange="if(typeof onChangeModulDeFirmaID == 'function') {  onChangeModulDeFirmaID(this); };"  cssClass="input-xxlarge" path="modulDeFirmaPerTipusDeDocument.modulDeFirmaID">
            <c:forEach items="${__theForm.listOfModulDeFirmaForModulDeFirmaID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ModulDeFirmaPerTipusDeDocumentFields.NOM)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[ModulDeFirmaPerTipusDeDocumentFields.NOM])?'modulDeFirmaPerTipusDeDocument.nom':__theForm.labels[ModulDeFirmaPerTipusDeDocumentFields.NOM]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[ModulDeFirmaPerTipusDeDocumentFields.NOM]}">
              <i class="icon-info-sign" title="${__theForm.help[ModulDeFirmaPerTipusDeDocumentFields.NOM]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="modulDeFirmaPerTipusDeDocument.nom" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ModulDeFirmaPerTipusDeDocumentFields.NOM)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,ModulDeFirmaPerTipusDeDocumentFields.NOM)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="100" path="modulDeFirmaPerTipusDeDocument.nom"   />

           </td>
        </tr>
        </c:if>
        
