<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="UsuariEntitatFields" className="es.caib.portafib.model.fields.UsuariEntitatFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariEntitatFields.USUARIENTITATID)}">
        <tr id="usuariEntitat_usuariEntitatID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariEntitatFields.USUARIENTITATID])?'usuariEntitat.usuariEntitatID':__theForm.labels[UsuariEntitatFields.USUARIENTITATID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[UsuariEntitatFields.USUARIENTITATID]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariEntitatFields.USUARIENTITATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="usuariEntitat.usuariEntitatID" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.USUARIENTITATID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.USUARIENTITATID)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="101" path="usuariEntitat.usuariEntitatID"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariEntitatFields.CARREC)}">
        <tr id="usuariEntitat_carrec_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariEntitatFields.CARREC])?'usuariEntitat.carrec':__theForm.labels[UsuariEntitatFields.CARREC]}" />
              <c:if test="${not empty __theForm.help[UsuariEntitatFields.CARREC]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariEntitatFields.CARREC]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="usuariEntitat.carrec" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.CARREC)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.CARREC)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="150" path="usuariEntitat.carrec"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariEntitatFields.USUARIPERSONAID)}">
        <tr id="usuariEntitat_usuariPersonaID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariEntitatFields.USUARIPERSONAID])?'usuariEntitat.usuariPersonaID':__theForm.labels[UsuariEntitatFields.USUARIPERSONAID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[UsuariEntitatFields.USUARIPERSONAID]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariEntitatFields.USUARIPERSONAID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="usuariEntitat.usuariPersonaID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.USUARIPERSONAID)}" >
          <form:hidden path="usuariEntitat.usuariPersonaID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.usuariEntitat.usuariPersonaID,__theForm.listOfUsuariPersonaForUsuariPersonaID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.USUARIPERSONAID)}" >
          <form:select id="usuariEntitat_usuariPersonaID"  onchange="if(typeof onChangeUsuariPersonaID == 'function') {  onChangeUsuariPersonaID(this); };"  cssClass="input-xxlarge" path="usuariEntitat.usuariPersonaID">
            <c:forEach items="${__theForm.listOfUsuariPersonaForUsuariPersonaID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariEntitatFields.ENTITATID)}">
        <tr id="usuariEntitat_entitatID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariEntitatFields.ENTITATID])?'usuariEntitat.entitatID':__theForm.labels[UsuariEntitatFields.ENTITATID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[UsuariEntitatFields.ENTITATID]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariEntitatFields.ENTITATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="usuariEntitat.entitatID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.ENTITATID)}" >
          <form:hidden path="usuariEntitat.entitatID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.usuariEntitat.entitatID,__theForm.listOfEntitatForEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.ENTITATID)}" >
          <form:select id="usuariEntitat_entitatID"  onchange="if(typeof onChangeEntitatID == 'function') {  onChangeEntitatID(this); };"  cssClass="input-xxlarge" path="usuariEntitat.entitatID">
            <c:forEach items="${__theForm.listOfEntitatForEntitatID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariEntitatFields.ACTIU)}">
        <tr id="usuariEntitat_actiu_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariEntitatFields.ACTIU])?'usuariEntitat.actiu':__theForm.labels[UsuariEntitatFields.ACTIU]}" />
              <c:if test="${not empty __theForm.help[UsuariEntitatFields.ACTIU]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariEntitatFields.ACTIU]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.ACTIU)}" >
              <form:errors path="usuariEntitat.actiu" cssClass="errorField alert alert-error" />
              <form:checkbox onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.ACTIU)? 'false' : 'true'}" path="usuariEntitat.actiu" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.ACTIU)}" >
                <fmt:message key="genapp.checkbox.${__theForm.usuariEntitat.actiu}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariEntitatFields.EMAIL)}">
        <tr id="usuariEntitat_email_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariEntitatFields.EMAIL])?'usuariEntitat.email':__theForm.labels[UsuariEntitatFields.EMAIL]}" />
              <c:if test="${not empty __theForm.help[UsuariEntitatFields.EMAIL]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariEntitatFields.EMAIL]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="usuariEntitat.email" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.EMAIL)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.EMAIL)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="100" path="usuariEntitat.email"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariEntitatFields.LOGOSEGELLID)}">
        <tr id="usuariEntitat_logoSegellID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariEntitatFields.LOGOSEGELLID])?'usuariEntitat.logoSegellID':__theForm.labels[UsuariEntitatFields.LOGOSEGELLID]}" />
              <c:if test="${not empty __theForm.help[UsuariEntitatFields.LOGOSEGELLID]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariEntitatFields.LOGOSEGELLID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="usuariEntitat.logoSegellID" cssClass="errorField alert alert-error" />
              <div class="fileupload fileupload-new" data-provides="fileupload" style="margin-bottom: 0px">
                <div class="input-append">
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.LOGOSEGELLID)}" >
                    <div class="uneditable-input span3">
                      <i class="icon-file fileupload-exists"></i>
                      <span class="fileupload-preview"></span>
                    </div>
                    <span class="btn btn-file">
                      <span class="fileupload-new"><fmt:message key="genapp.form.file.select"/></span>
                      <span class="fileupload-exists"><fmt:message key="genapp.form.file.change"/></span>
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.LOGOSEGELLID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.LOGOSEGELLID)? 'input uneditable-input' : 'input'}"  path="logoSegellID" type="file" />
                    </span>
                    <a href="#" class="btn fileupload-exists" data-dismiss="fileupload"><fmt:message key="genapp.form.file.unselect"/></a>
                    <span class="add-on">&nbsp;</span>
                </c:if>
                <c:if test="${not empty __theForm.usuariEntitat.logoSegell}">
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.LOGOSEGELLID)}" >
                    <span class="add-on">
                        <form:checkbox path="logoSegellIDDelete"/>
                        <fmt:message key="genapp.form.file.delete"/>
                    </span>
                    <span class="add-on">&nbsp;</span>   
                </c:if>
                    <span class="add-on">
                        <a target="_blank" href="<c:url value="${pfi:fileUrl(__theForm.usuariEntitat.logoSegell)}"/>">${__theForm.usuariEntitat.logoSegell.nom}</a>
                    </span>
                </c:if>
                </div>
              </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariEntitatFields.PREDETERMINAT)}">
        <tr id="usuariEntitat_predeterminat_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariEntitatFields.PREDETERMINAT])?'usuariEntitat.predeterminat':__theForm.labels[UsuariEntitatFields.PREDETERMINAT]}" />
              <c:if test="${not empty __theForm.help[UsuariEntitatFields.PREDETERMINAT]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariEntitatFields.PREDETERMINAT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.PREDETERMINAT)}" >
              <form:errors path="usuariEntitat.predeterminat" cssClass="errorField alert alert-error" />
              <form:checkbox onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.PREDETERMINAT)? 'false' : 'true'}" path="usuariEntitat.predeterminat" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.PREDETERMINAT)}" >
                <fmt:message key="genapp.checkbox.${__theForm.usuariEntitat.predeterminat}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariEntitatFields.REBRETOTSELSAVISOS)}">
        <tr id="usuariEntitat_rebreTotsElsAvisos_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariEntitatFields.REBRETOTSELSAVISOS])?'usuariEntitat.rebreTotsElsAvisos':__theForm.labels[UsuariEntitatFields.REBRETOTSELSAVISOS]}" />
              <c:if test="${not empty __theForm.help[UsuariEntitatFields.REBRETOTSELSAVISOS]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariEntitatFields.REBRETOTSELSAVISOS]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.REBRETOTSELSAVISOS)}" >
              <form:errors path="usuariEntitat.rebreTotsElsAvisos" cssClass="errorField alert alert-error" />
              <form:checkbox onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.REBRETOTSELSAVISOS)? 'false' : 'true'}" path="usuariEntitat.rebreTotsElsAvisos" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.REBRETOTSELSAVISOS)}" >
                <fmt:message key="genapp.checkbox.${__theForm.usuariEntitat.rebreTotsElsAvisos}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariEntitatFields.POTCUSTODIAR)}">
        <tr id="usuariEntitat_potCustodiar_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariEntitatFields.POTCUSTODIAR])?'usuariEntitat.potCustodiar':__theForm.labels[UsuariEntitatFields.POTCUSTODIAR]}" />
              <c:if test="${not empty __theForm.help[UsuariEntitatFields.POTCUSTODIAR]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariEntitatFields.POTCUSTODIAR]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.POTCUSTODIAR)}" >
              <form:select cssClass="input-medium" onchange="if(typeof onChangePotCustodiar == 'function') {  onChangePotCustodiar(this); };"  path="usuariEntitat.potCustodiar">
                <form:option value=""><fmt:message key="potcustodiar." /></form:option>
                <form:option value="true" ><fmt:message key="potcustodiar.true" /></form:option>
                <form:option value="false" ><fmt:message key="potcustodiar.false" /></form:option>
              </form:select>
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.POTCUSTODIAR)}" >
                <fmt:message key="potcustodiar.${__theForm.usuariEntitat.potCustodiar}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
