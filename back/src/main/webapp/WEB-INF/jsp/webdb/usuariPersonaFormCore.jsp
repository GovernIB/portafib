<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="UsuariPersonaFields" className="es.caib.portafib.model.fields.UsuariPersonaFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariPersonaFields.USUARIPERSONAID)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariPersonaFields.USUARIPERSONAID])?'usuariPersona.usuariPersonaID':__theForm.labels[UsuariPersonaFields.USUARIPERSONAID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[UsuariPersonaFields.USUARIPERSONAID]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariPersonaFields.USUARIPERSONAID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="usuariPersona.usuariPersonaID" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariPersonaFields.USUARIPERSONAID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,UsuariPersonaFields.USUARIPERSONAID)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="50" path="usuariPersona.usuariPersonaID"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariPersonaFields.NOM)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariPersonaFields.NOM])?'usuariPersona.nom':__theForm.labels[UsuariPersonaFields.NOM]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[UsuariPersonaFields.NOM]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariPersonaFields.NOM]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="usuariPersona.nom" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariPersonaFields.NOM)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,UsuariPersonaFields.NOM)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="50" path="usuariPersona.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariPersonaFields.LLINATGES)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariPersonaFields.LLINATGES])?'usuariPersona.llinatges':__theForm.labels[UsuariPersonaFields.LLINATGES]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[UsuariPersonaFields.LLINATGES]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariPersonaFields.LLINATGES]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="usuariPersona.llinatges" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariPersonaFields.LLINATGES)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,UsuariPersonaFields.LLINATGES)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="100" path="usuariPersona.llinatges"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariPersonaFields.EMAIL)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariPersonaFields.EMAIL])?'usuariPersona.email':__theForm.labels[UsuariPersonaFields.EMAIL]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[UsuariPersonaFields.EMAIL]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariPersonaFields.EMAIL]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="usuariPersona.email" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariPersonaFields.EMAIL)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,UsuariPersonaFields.EMAIL)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="100" path="usuariPersona.email"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariPersonaFields.NIF)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariPersonaFields.NIF])?'usuariPersona.nif':__theForm.labels[UsuariPersonaFields.NIF]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[UsuariPersonaFields.NIF]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariPersonaFields.NIF]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="usuariPersona.nif" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariPersonaFields.NIF)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,UsuariPersonaFields.NIF)? 'input-small uneditable-input' : 'input-small'}"  maxlength="9" path="usuariPersona.nif"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariPersonaFields.IDIOMAID)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariPersonaFields.IDIOMAID])?'usuariPersona.idiomaID':__theForm.labels[UsuariPersonaFields.IDIOMAID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[UsuariPersonaFields.IDIOMAID]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariPersonaFields.IDIOMAID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="usuariPersona.idiomaID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariPersonaFields.IDIOMAID)}" >
          <form:hidden path="usuariPersona.idiomaID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.usuariPersona.idiomaID,__theForm.listOfIdiomaForIdiomaID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariPersonaFields.IDIOMAID)}" >
          <form:select id="usuariPersona_idiomaID"  onchange="if(typeof onChangeIdiomaID == 'function') {  onChangeIdiomaID(this); };"  cssClass="input-xxlarge" path="usuariPersona.idiomaID">
            <c:forEach items="${__theForm.listOfIdiomaForIdiomaID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariPersonaFields.RUBRICAID)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariPersonaFields.RUBRICAID])?'usuariPersona.rubricaID':__theForm.labels[UsuariPersonaFields.RUBRICAID]}" />
              <c:if test="${not empty __theForm.help[UsuariPersonaFields.RUBRICAID]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariPersonaFields.RUBRICAID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="usuariPersona.rubricaID" cssClass="errorField alert alert-error" />
              <div class="fileupload fileupload-new" data-provides="fileupload" style="margin-bottom: 0px">
                <div class="input-append">
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariPersonaFields.RUBRICAID)}" >
                    <div class="uneditable-input span3">
                      <i class="icon-file fileupload-exists"></i>
                      <span class="fileupload-preview"></span>
                    </div>
                    <span class="btn btn-file">
                      <span class="fileupload-new"><fmt:message key="genapp.form.file.select"/></span>
                      <span class="fileupload-exists"><fmt:message key="genapp.form.file.change"/></span>
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariPersonaFields.RUBRICAID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,UsuariPersonaFields.RUBRICAID)? 'input uneditable-input' : 'input'}"  path="rubricaID" type="file" />
                    </span>
                    <a href="#" class="btn fileupload-exists" data-dismiss="fileupload"><fmt:message key="genapp.form.file.unselect"/></a>
                    <span class="add-on">&nbsp;</span>
                </c:if>
                <c:if test="${not empty __theForm.usuariPersona.rubrica}">
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariPersonaFields.RUBRICAID)}" >
                    <span class="add-on">
                        <form:checkbox path="rubricaIDDelete"/>
                        <fmt:message key="genapp.form.file.delete"/>
                    </span>
                    <span class="add-on">&nbsp;</span>   
                </c:if>
                    <span class="add-on">
                        <a target="_blank" href="<c:url value="${pfi:fileUrl(__theForm.usuariPersona.rubrica)}"/>">${__theForm.usuariPersona.rubrica.nom}</a>
                    </span>
                </c:if>
                </div>
              </div>
           </td>
        </tr>
        </c:if>
        
