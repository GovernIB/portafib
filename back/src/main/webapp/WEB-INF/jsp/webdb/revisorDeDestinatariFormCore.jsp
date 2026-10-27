<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="RevisorDeDestinatariFields" className="es.caib.portafib.model.fields.RevisorDeDestinatariFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,RevisorDeDestinatariFields.DESTINATARIID)}">
        <tr id="revisorDeDestinatari_destinatariID_rowid">
          <td id="revisorDeDestinatari_destinatariID_columnlabelid">
            <label for="revisorDeDestinatari.destinatariID">
              <fmt:message key="${(empty __theForm.labels[RevisorDeDestinatariFields.DESTINATARIID])?'revisorDeDestinatari.destinatariID':__theForm.labels[RevisorDeDestinatariFields.DESTINATARIID]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[RevisorDeDestinatariFields.DESTINATARIID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[RevisorDeDestinatariFields.DESTINATARIID]}" ></i>
              </c:if>
            </td>
          <td id="revisorDeDestinatari_destinatariID_columnvalueid">
          <form:errors path="revisorDeDestinatari.destinatariID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,RevisorDeDestinatariFields.DESTINATARIID)}" >
          <form:hidden path="revisorDeDestinatari.destinatariID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.revisorDeDestinatari.destinatariID,__theForm.listOfUsuariEntitatForDestinatariID)}" aria-label="revisorDeDestinatari.destinatariID"   required aria-required="true"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,RevisorDeDestinatariFields.DESTINATARIID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="revisorDeDestinatari.destinatariID" cssClass="form-control col-md-9-optional"  required="required"  aria-required="true"  onchange="if(typeof onChangeDestinatariID == 'function') {  onChangeDestinatariID(this); };">


            <c:forEach items="${__theForm.listOfUsuariEntitatForDestinatariID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,RevisorDeDestinatariFields.REVISORID)}">
        <tr id="revisorDeDestinatari_revisorID_rowid">
          <td id="revisorDeDestinatari_revisorID_columnlabelid">
            <label for="revisorDeDestinatari.revisorID">
              <fmt:message key="${(empty __theForm.labels[RevisorDeDestinatariFields.REVISORID])?'revisorDeDestinatari.revisorID':__theForm.labels[RevisorDeDestinatariFields.REVISORID]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[RevisorDeDestinatariFields.REVISORID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[RevisorDeDestinatariFields.REVISORID]}" ></i>
              </c:if>
            </td>
          <td id="revisorDeDestinatari_revisorID_columnvalueid">
          <form:errors path="revisorDeDestinatari.revisorID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,RevisorDeDestinatariFields.REVISORID)}" >
          <form:hidden path="revisorDeDestinatari.revisorID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.revisorDeDestinatari.revisorID,__theForm.listOfUsuariEntitatForRevisorID)}" aria-label="revisorDeDestinatari.revisorID"   required aria-required="true"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,RevisorDeDestinatariFields.REVISORID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="revisorDeDestinatari.revisorID" cssClass="form-control col-md-9-optional"  required="required"  aria-required="true"  onchange="if(typeof onChangeRevisorID == 'function') {  onChangeRevisorID(this); };">


            <c:forEach items="${__theForm.listOfUsuariEntitatForRevisorID}" var="tmp">
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
        
