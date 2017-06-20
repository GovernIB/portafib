<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="TipusDocumentColaboracioDelegacioFields" className="es.caib.portafib.model.fields.TipusDocumentColaboracioDelegacioFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,TipusDocumentColaboracioDelegacioFields.COLABORACIODELEGACIOID)}">
        <tr id="tipusDocumentColaboracioDelegacio_colaboracioDelegacioID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[TipusDocumentColaboracioDelegacioFields.COLABORACIODELEGACIOID])?'tipusDocumentColaboracioDelegacio.colaboracioDelegacioID':__theForm.labels[TipusDocumentColaboracioDelegacioFields.COLABORACIODELEGACIOID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[TipusDocumentColaboracioDelegacioFields.COLABORACIODELEGACIOID]}">
              <i class="icon-info-sign" title="${__theForm.help[TipusDocumentColaboracioDelegacioFields.COLABORACIODELEGACIOID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="tipusDocumentColaboracioDelegacio.colaboracioDelegacioID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,TipusDocumentColaboracioDelegacioFields.COLABORACIODELEGACIOID)}" >
          <form:hidden path="tipusDocumentColaboracioDelegacio.colaboracioDelegacioID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.tipusDocumentColaboracioDelegacio.colaboracioDelegacioID,__theForm.listOfColaboracioDelegacioForColaboracioDelegacioID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,TipusDocumentColaboracioDelegacioFields.COLABORACIODELEGACIOID)}" >
          <form:select id="tipusDocumentColaboracioDelegacio_colaboracioDelegacioID"  onchange="if(typeof onChangeColaboracioDelegacioID == 'function') {  onChangeColaboracioDelegacioID(this); };"  cssClass="input-xxlarge" path="tipusDocumentColaboracioDelegacio.colaboracioDelegacioID">
            <c:forEach items="${__theForm.listOfColaboracioDelegacioForColaboracioDelegacioID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,TipusDocumentColaboracioDelegacioFields.TIPUSDOCUMENTID)}">
        <tr id="tipusDocumentColaboracioDelegacio_tipusDocumentID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[TipusDocumentColaboracioDelegacioFields.TIPUSDOCUMENTID])?'tipusDocumentColaboracioDelegacio.tipusDocumentID':__theForm.labels[TipusDocumentColaboracioDelegacioFields.TIPUSDOCUMENTID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[TipusDocumentColaboracioDelegacioFields.TIPUSDOCUMENTID]}">
              <i class="icon-info-sign" title="${__theForm.help[TipusDocumentColaboracioDelegacioFields.TIPUSDOCUMENTID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="tipusDocumentColaboracioDelegacio.tipusDocumentID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,TipusDocumentColaboracioDelegacioFields.TIPUSDOCUMENTID)}" >
          <form:hidden path="tipusDocumentColaboracioDelegacio.tipusDocumentID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.tipusDocumentColaboracioDelegacio.tipusDocumentID,__theForm.listOfTipusDocumentForTipusDocumentID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,TipusDocumentColaboracioDelegacioFields.TIPUSDOCUMENTID)}" >
          <form:select id="tipusDocumentColaboracioDelegacio_tipusDocumentID"  onchange="if(typeof onChangeTipusDocumentID == 'function') {  onChangeTipusDocumentID(this); };"  cssClass="input-xxlarge" path="tipusDocumentColaboracioDelegacio.tipusDocumentID">
            <c:forEach items="${__theForm.listOfTipusDocumentForTipusDocumentID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
