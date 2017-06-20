<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="RoleFields" className="es.caib.portafib.model.fields.RoleFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,RoleFields.ROLEID)}">
        <tr id="role_roleID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[RoleFields.ROLEID])?'role.roleID':__theForm.labels[RoleFields.ROLEID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[RoleFields.ROLEID]}">
              <i class="icon-info-sign" title="${__theForm.help[RoleFields.ROLEID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="role.roleID" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,RoleFields.ROLEID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,RoleFields.ROLEID)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="50" path="role.roleID"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,RoleFields.NOM)}">
        <tr id="role_nom_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[RoleFields.NOM])?'role.nom':__theForm.labels[RoleFields.NOM]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[RoleFields.NOM]}">
              <i class="icon-info-sign" title="${__theForm.help[RoleFields.NOM]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="role.nom" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,RoleFields.NOM)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,RoleFields.NOM)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="50" path="role.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,RoleFields.DESCRIPCIO)}">
        <tr id="role_descripcio_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[RoleFields.DESCRIPCIO])?'role.descripcio':__theForm.labels[RoleFields.DESCRIPCIO]}" />
              <c:if test="${not empty __theForm.help[RoleFields.DESCRIPCIO]}">
              <i class="icon-info-sign" title="${__theForm.help[RoleFields.DESCRIPCIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="role.descripcio" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,RoleFields.DESCRIPCIO)? 'true' : 'false'}" path="role.descripcio"  />
              <div class="btn-group" style="vertical-align: top;">
              <button class="btn btn-mini dropdown-toggle" data-toggle="dropdown">&nbsp;<span class="caret"></span></button>
              <ul class="dropdown-menu">
                <li><a href="#" onclick="javascript:var ta=document.getElementById('role.descripcio'); ta.wrap='off';" >No Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('role.descripcio'); ta.wrap='soft';">Soft Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('role.descripcio'); ta.wrap='hard';">Hard Wrap</a></li>
              </ul>
              </div>
           </td>
        </tr>
        </c:if>
        
