<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="RevisorDeFirmaFields" className="es.caib.portafib.model.fields.RevisorDeFirmaFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,RevisorDeFirmaFields.USUARIENTITATREVISORID)}">
        <tr id="revisorDeFirma_usuariEntitatRevisorID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[RevisorDeFirmaFields.USUARIENTITATREVISORID])?'revisorDeFirma.usuariEntitatRevisorID':__theForm.labels[RevisorDeFirmaFields.USUARIENTITATREVISORID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[RevisorDeFirmaFields.USUARIENTITATREVISORID]}">
              <i class="icon-info-sign" title="${__theForm.help[RevisorDeFirmaFields.USUARIENTITATREVISORID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="revisorDeFirma.usuariEntitatRevisorID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,RevisorDeFirmaFields.USUARIENTITATREVISORID)}" >
          <form:hidden path="revisorDeFirma.usuariEntitatRevisorID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.revisorDeFirma.usuariEntitatRevisorID,__theForm.listOfUsuariEntitatRevisorForUsuariEntitatRevisorID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,RevisorDeFirmaFields.USUARIENTITATREVISORID)}" >
          <form:select id="revisorDeFirma_usuariEntitatRevisorID"  onchange="if(typeof onChangeUsuariEntitatRevisorID == 'function') {  onChangeUsuariEntitatRevisorID(this); };"  cssClass="input-xxlarge" path="revisorDeFirma.usuariEntitatRevisorID">
            <c:forEach items="${__theForm.listOfUsuariEntitatRevisorForUsuariEntitatRevisorID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,RevisorDeFirmaFields.FIRMAID)}">
        <tr id="revisorDeFirma_firmaID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[RevisorDeFirmaFields.FIRMAID])?'revisorDeFirma.firmaID':__theForm.labels[RevisorDeFirmaFields.FIRMAID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[RevisorDeFirmaFields.FIRMAID]}">
              <i class="icon-info-sign" title="${__theForm.help[RevisorDeFirmaFields.FIRMAID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="revisorDeFirma.firmaID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,RevisorDeFirmaFields.FIRMAID)}" >
          <form:hidden path="revisorDeFirma.firmaID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.revisorDeFirma.firmaID,__theForm.listOfFirmaForFirmaID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,RevisorDeFirmaFields.FIRMAID)}" >
          <form:select id="revisorDeFirma_firmaID"  onchange="if(typeof onChangeFirmaID == 'function') {  onChangeFirmaID(this); };"  cssClass="input-xxlarge" path="revisorDeFirma.firmaID">
            <c:forEach items="${__theForm.listOfFirmaForFirmaID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,RevisorDeFirmaFields.OBLIGATORI)}">
        <tr id="revisorDeFirma_obligatori_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[RevisorDeFirmaFields.OBLIGATORI])?'revisorDeFirma.obligatori':__theForm.labels[RevisorDeFirmaFields.OBLIGATORI]}" />
              <c:if test="${not empty __theForm.help[RevisorDeFirmaFields.OBLIGATORI]}">
              <i class="icon-info-sign" title="${__theForm.help[RevisorDeFirmaFields.OBLIGATORI]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,RevisorDeFirmaFields.OBLIGATORI)}" >
              <form:errors path="revisorDeFirma.obligatori" cssClass="errorField alert alert-error" />
              <form:checkbox onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,RevisorDeFirmaFields.OBLIGATORI)? 'false' : 'true'}" path="revisorDeFirma.obligatori" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,RevisorDeFirmaFields.OBLIGATORI)}" >
                <fmt:message key="genapp.checkbox.${__theForm.revisorDeFirma.obligatori}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
