<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="RoleUsuariEntitatFields" className="es.caib.portafib.model.fields.RoleUsuariEntitatFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,RoleUsuariEntitatFields.ROLEID)}">
        <tr id="roleUsuariEntitat_roleID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[RoleUsuariEntitatFields.ROLEID])?'roleUsuariEntitat.roleID':__theForm.labels[RoleUsuariEntitatFields.ROLEID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[RoleUsuariEntitatFields.ROLEID]}">
              <i class="icon-info-sign" title="${__theForm.help[RoleUsuariEntitatFields.ROLEID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="roleUsuariEntitat.roleID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,RoleUsuariEntitatFields.ROLEID)}" >
          <form:hidden path="roleUsuariEntitat.roleID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.roleUsuariEntitat.roleID,__theForm.listOfRoleForRoleID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,RoleUsuariEntitatFields.ROLEID)}" >
          <form:select id="roleUsuariEntitat_roleID"  onchange="if(typeof onChangeRoleID == 'function') {  onChangeRoleID(this); };"  cssClass="input-xxlarge" path="roleUsuariEntitat.roleID">
            <c:forEach items="${__theForm.listOfRoleForRoleID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,RoleUsuariEntitatFields.USUARIENTITATID)}">
        <tr id="roleUsuariEntitat_usuariEntitatID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[RoleUsuariEntitatFields.USUARIENTITATID])?'roleUsuariEntitat.usuariEntitatID':__theForm.labels[RoleUsuariEntitatFields.USUARIENTITATID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[RoleUsuariEntitatFields.USUARIENTITATID]}">
              <i class="icon-info-sign" title="${__theForm.help[RoleUsuariEntitatFields.USUARIENTITATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="roleUsuariEntitat.usuariEntitatID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,RoleUsuariEntitatFields.USUARIENTITATID)}" >
          <form:hidden path="roleUsuariEntitat.usuariEntitatID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.roleUsuariEntitat.usuariEntitatID,__theForm.listOfUsuariEntitatForUsuariEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,RoleUsuariEntitatFields.USUARIENTITATID)}" >
          <form:select id="roleUsuariEntitat_usuariEntitatID"  onchange="if(typeof onChangeUsuariEntitatID == 'function') {  onChangeUsuariEntitatID(this); };"  cssClass="input-xxlarge" path="roleUsuariEntitat.usuariEntitatID">
            <c:forEach items="${__theForm.listOfUsuariEntitatForUsuariEntitatID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
