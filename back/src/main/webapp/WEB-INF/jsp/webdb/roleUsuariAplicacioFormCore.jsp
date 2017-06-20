<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="RoleUsuariAplicacioFields" className="es.caib.portafib.model.fields.RoleUsuariAplicacioFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,RoleUsuariAplicacioFields.ROLEID)}">
        <tr id="roleUsuariAplicacio_roleID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[RoleUsuariAplicacioFields.ROLEID])?'roleUsuariAplicacio.roleID':__theForm.labels[RoleUsuariAplicacioFields.ROLEID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[RoleUsuariAplicacioFields.ROLEID]}">
              <i class="icon-info-sign" title="${__theForm.help[RoleUsuariAplicacioFields.ROLEID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="roleUsuariAplicacio.roleID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,RoleUsuariAplicacioFields.ROLEID)}" >
          <form:hidden path="roleUsuariAplicacio.roleID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.roleUsuariAplicacio.roleID,__theForm.listOfRoleForRoleID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,RoleUsuariAplicacioFields.ROLEID)}" >
          <form:select id="roleUsuariAplicacio_roleID"  onchange="if(typeof onChangeRoleID == 'function') {  onChangeRoleID(this); };"  cssClass="input-xxlarge" path="roleUsuariAplicacio.roleID">
            <c:forEach items="${__theForm.listOfRoleForRoleID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,RoleUsuariAplicacioFields.USUARIAPLICACIOID)}">
        <tr id="roleUsuariAplicacio_usuariAplicacioID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[RoleUsuariAplicacioFields.USUARIAPLICACIOID])?'roleUsuariAplicacio.usuariAplicacioID':__theForm.labels[RoleUsuariAplicacioFields.USUARIAPLICACIOID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[RoleUsuariAplicacioFields.USUARIAPLICACIOID]}">
              <i class="icon-info-sign" title="${__theForm.help[RoleUsuariAplicacioFields.USUARIAPLICACIOID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="roleUsuariAplicacio.usuariAplicacioID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,RoleUsuariAplicacioFields.USUARIAPLICACIOID)}" >
          <form:hidden path="roleUsuariAplicacio.usuariAplicacioID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.roleUsuariAplicacio.usuariAplicacioID,__theForm.listOfUsuariAplicacioForUsuariAplicacioID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,RoleUsuariAplicacioFields.USUARIAPLICACIOID)}" >
          <form:select id="roleUsuariAplicacio_usuariAplicacioID"  onchange="if(typeof onChangeUsuariAplicacioID == 'function') {  onChangeUsuariAplicacioID(this); };"  cssClass="input-xxlarge" path="roleUsuariAplicacio.usuariAplicacioID">
            <c:forEach items="${__theForm.listOfUsuariAplicacioForUsuariAplicacioID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
