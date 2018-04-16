<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="UsuariEntitatRevisorFields" className="es.caib.portafib.model.fields.UsuariEntitatRevisorFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariEntitatRevisorFields.USUARIENTITATID)}">
        <tr id="usuariEntitatRevisor_usuariEntitatID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariEntitatRevisorFields.USUARIENTITATID])?'usuariEntitatRevisor.usuariEntitatID':__theForm.labels[UsuariEntitatRevisorFields.USUARIENTITATID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[UsuariEntitatRevisorFields.USUARIENTITATID]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariEntitatRevisorFields.USUARIENTITATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="usuariEntitatRevisor.usuariEntitatID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariEntitatRevisorFields.USUARIENTITATID)}" >
          <form:hidden path="usuariEntitatRevisor.usuariEntitatID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.usuariEntitatRevisor.usuariEntitatID,__theForm.listOfUsuariEntitatForUsuariEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariEntitatRevisorFields.USUARIENTITATID)}" >
          <form:select id="usuariEntitatRevisor_usuariEntitatID"  onchange="if(typeof onChangeUsuariEntitatID == 'function') {  onChangeUsuariEntitatID(this); };"  cssClass="input-xxlarge" path="usuariEntitatRevisor.usuariEntitatID">
            <c:forEach items="${__theForm.listOfUsuariEntitatForUsuariEntitatID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariEntitatRevisorFields.ACTIU)}">
        <tr id="usuariEntitatRevisor_actiu_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariEntitatRevisorFields.ACTIU])?'usuariEntitatRevisor.actiu':__theForm.labels[UsuariEntitatRevisorFields.ACTIU]}" />
              <c:if test="${not empty __theForm.help[UsuariEntitatRevisorFields.ACTIU]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariEntitatRevisorFields.ACTIU]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariEntitatRevisorFields.ACTIU)}" >
              <form:errors path="usuariEntitatRevisor.actiu" cssClass="errorField alert alert-error" />
              <form:checkbox onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,UsuariEntitatRevisorFields.ACTIU)? 'false' : 'true'}" path="usuariEntitatRevisor.actiu" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariEntitatRevisorFields.ACTIU)}" >
                <fmt:message key="genapp.checkbox.${__theForm.usuariEntitatRevisor.actiu}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
