<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="RoleFields" className="es.caib.portafib.model.fields.RoleFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,RoleFields.ROLEID)}">
        <tr id="role_roleID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[RoleFields.ROLEID])?'role.roleID':__theForm.labels[RoleFields.ROLEID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[RoleFields.ROLEID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[RoleFields.ROLEID]}" ></i>
              </c:if>
            </td>
            <td>
            <form:errors path="role.roleID" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,RoleFields.ROLEID)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,RoleFields.ROLEID)? ' uneditable-input' : ''}"  style="" maxlength="50" path="role.roleID"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,RoleFields.NOM)}">
        <tr id="role_nom_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[RoleFields.NOM])?'role.nom':__theForm.labels[RoleFields.NOM]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[RoleFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[RoleFields.NOM]}" ></i>
              </c:if>
            </td>
            <td>
            <form:errors path="role.nom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,RoleFields.NOM)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,RoleFields.NOM)? ' uneditable-input' : ''}"  style="" maxlength="50" path="role.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,RoleFields.DESCRIPCIO)}">
        <tr id="role_descripcio_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[RoleFields.DESCRIPCIO])?'role.descripcio':__theForm.labels[RoleFields.DESCRIPCIO]}" />
             </label>
              <c:if test="${not empty __theForm.help[RoleFields.DESCRIPCIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[RoleFields.DESCRIPCIO]}" ></i>
              </c:if>
            </td>
            <td>
              <form:errors path="role.descripcio" cssClass="errorField alert alert-danger" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,RoleFields.DESCRIPCIO)? 'true' : 'false'}" path="role.descripcio"  />
      <div id="dropdownMenuButton_descripcio" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_descripcio" class="dropdown-menu">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('role.descripcio'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('role.descripcio'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('role.descripcio'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_descripcio').on('click', function(){
					var valor = ($('#dropdownMenuContainer_descripcio').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_descripcio').css('display', valor);
                 return false;
				});
      </script>           </td>
        </tr>
        </c:if>
        
