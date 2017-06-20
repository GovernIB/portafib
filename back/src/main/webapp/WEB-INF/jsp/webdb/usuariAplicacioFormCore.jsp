<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="UsuariAplicacioFields" className="es.caib.portafib.model.fields.UsuariAplicacioFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioFields.USUARIAPLICACIOID)}">
        <tr id="usuariAplicacio_usuariAplicacioID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioFields.USUARIAPLICACIOID])?'usuariAplicacio.usuariAplicacioID':__theForm.labels[UsuariAplicacioFields.USUARIAPLICACIOID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[UsuariAplicacioFields.USUARIAPLICACIOID]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioFields.USUARIAPLICACIOID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="usuariAplicacio.usuariAplicacioID" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.USUARIAPLICACIOID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.USUARIAPLICACIOID)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="101" path="usuariAplicacio.usuariAplicacioID"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioFields.CONTRASENYA)}">
        <tr id="usuariAplicacio_contrasenya_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioFields.CONTRASENYA])?'usuariAplicacio.contrasenya':__theForm.labels[UsuariAplicacioFields.CONTRASENYA]}" />
              <c:if test="${not empty __theForm.help[UsuariAplicacioFields.CONTRASENYA]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioFields.CONTRASENYA]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="usuariAplicacio.contrasenya" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.CONTRASENYA)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.CONTRASENYA)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="50" path="usuariAplicacio.contrasenya"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioFields.ENTITATID)}">
        <tr id="usuariAplicacio_entitatID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioFields.ENTITATID])?'usuariAplicacio.entitatID':__theForm.labels[UsuariAplicacioFields.ENTITATID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[UsuariAplicacioFields.ENTITATID]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioFields.ENTITATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="usuariAplicacio.entitatID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.ENTITATID)}" >
          <form:hidden path="usuariAplicacio.entitatID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.usuariAplicacio.entitatID,__theForm.listOfEntitatForEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.ENTITATID)}" >
          <form:select id="usuariAplicacio_entitatID"  onchange="if(typeof onChangeEntitatID == 'function') {  onChangeEntitatID(this); };"  cssClass="input-xxlarge" path="usuariAplicacio.entitatID">
            <c:forEach items="${__theForm.listOfEntitatForEntitatID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioFields.EMAILADMIN)}">
        <tr id="usuariAplicacio_emailAdmin_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioFields.EMAILADMIN])?'usuariAplicacio.emailAdmin':__theForm.labels[UsuariAplicacioFields.EMAILADMIN]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[UsuariAplicacioFields.EMAILADMIN]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioFields.EMAILADMIN]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="usuariAplicacio.emailAdmin" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.EMAILADMIN)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.EMAILADMIN)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="100" path="usuariAplicacio.emailAdmin"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioFields.CALLBACKVERSIO)}">
        <tr id="usuariAplicacio_callbackVersio_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioFields.CALLBACKVERSIO])?'usuariAplicacio.callbackVersio':__theForm.labels[UsuariAplicacioFields.CALLBACKVERSIO]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[UsuariAplicacioFields.CALLBACKVERSIO]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioFields.CALLBACKVERSIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="usuariAplicacio.callbackVersio" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.CALLBACKVERSIO)}" >
          <form:hidden path="usuariAplicacio.callbackVersio"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.usuariAplicacio.callbackVersio,__theForm.listOfValuesForCallbackVersio)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.CALLBACKVERSIO)}" >
          <form:select id="usuariAplicacio_callbackVersio"  onchange="if(typeof onChangeCallbackVersio == 'function') {  onChangeCallbackVersio(this); };"  cssClass="input-xxlarge" path="usuariAplicacio.callbackVersio">
            <c:forEach items="${__theForm.listOfValuesForCallbackVersio}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioFields.CALLBACKURL)}">
        <tr id="usuariAplicacio_callbackURL_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioFields.CALLBACKURL])?'usuariAplicacio.callbackURL':__theForm.labels[UsuariAplicacioFields.CALLBACKURL]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[UsuariAplicacioFields.CALLBACKURL]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioFields.CALLBACKURL]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="usuariAplicacio.callbackURL" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.CALLBACKURL)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.CALLBACKURL)? 'input-xxlarge uneditable-input' : 'input-xxlarge'}"  maxlength="400" path="usuariAplicacio.callbackURL"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioFields.ACTIU)}">
        <tr id="usuariAplicacio_actiu_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioFields.ACTIU])?'usuariAplicacio.actiu':__theForm.labels[UsuariAplicacioFields.ACTIU]}" />
              <c:if test="${not empty __theForm.help[UsuariAplicacioFields.ACTIU]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioFields.ACTIU]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.ACTIU)}" >
              <form:errors path="usuariAplicacio.actiu" cssClass="errorField alert alert-error" />
              <form:checkbox onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.ACTIU)? 'false' : 'true'}" path="usuariAplicacio.actiu" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.ACTIU)}" >
                <fmt:message key="genapp.checkbox.${__theForm.usuariAplicacio.actiu}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioFields.IDIOMAID)}">
        <tr id="usuariAplicacio_idiomaID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioFields.IDIOMAID])?'usuariAplicacio.idiomaID':__theForm.labels[UsuariAplicacioFields.IDIOMAID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[UsuariAplicacioFields.IDIOMAID]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioFields.IDIOMAID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="usuariAplicacio.idiomaID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.IDIOMAID)}" >
          <form:hidden path="usuariAplicacio.idiomaID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.usuariAplicacio.idiomaID,__theForm.listOfIdiomaForIdiomaID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.IDIOMAID)}" >
          <form:select id="usuariAplicacio_idiomaID"  onchange="if(typeof onChangeIdiomaID == 'function') {  onChangeIdiomaID(this); };"  cssClass="input-xxlarge" path="usuariAplicacio.idiomaID">
            <c:forEach items="${__theForm.listOfIdiomaForIdiomaID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioFields.DESCRIPCIO)}">
        <tr id="usuariAplicacio_descripcio_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioFields.DESCRIPCIO])?'usuariAplicacio.descripcio':__theForm.labels[UsuariAplicacioFields.DESCRIPCIO]}" />
              <c:if test="${not empty __theForm.help[UsuariAplicacioFields.DESCRIPCIO]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioFields.DESCRIPCIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="usuariAplicacio.descripcio" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.DESCRIPCIO)? 'true' : 'false'}" path="usuariAplicacio.descripcio"  />
              <div class="btn-group" style="vertical-align: top;">
              <button class="btn btn-mini dropdown-toggle" data-toggle="dropdown">&nbsp;<span class="caret"></span></button>
              <ul class="dropdown-menu">
                <li><a href="#" onclick="javascript:var ta=document.getElementById('usuariAplicacio.descripcio'); ta.wrap='off';" >No Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('usuariAplicacio.descripcio'); ta.wrap='soft';">Soft Wrap</a></li>
                <li><a href="#" onclick="javascript:var ta=document.getElementById('usuariAplicacio.descripcio'); ta.wrap='hard';">Hard Wrap</a></li>
              </ul>
              </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioFields.LOGOSEGELLID)}">
        <tr id="usuariAplicacio_logoSegellID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioFields.LOGOSEGELLID])?'usuariAplicacio.logoSegellID':__theForm.labels[UsuariAplicacioFields.LOGOSEGELLID]}" />
              <c:if test="${not empty __theForm.help[UsuariAplicacioFields.LOGOSEGELLID]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioFields.LOGOSEGELLID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="usuariAplicacio.logoSegellID" cssClass="errorField alert alert-error" />
              <div class="fileupload fileupload-new" data-provides="fileupload" style="margin-bottom: 0px">
                <div class="input-append">
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.LOGOSEGELLID)}" >
                    <div class="uneditable-input span3">
                      <i class="icon-file fileupload-exists"></i>
                      <span class="fileupload-preview"></span>
                    </div>
                    <span class="btn btn-file">
                      <span class="fileupload-new"><fmt:message key="genapp.form.file.select"/></span>
                      <span class="fileupload-exists"><fmt:message key="genapp.form.file.change"/></span>
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.LOGOSEGELLID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.LOGOSEGELLID)? 'input uneditable-input' : 'input'}"  path="logoSegellID" type="file" />
                    </span>
                    <a href="#" class="btn fileupload-exists" data-dismiss="fileupload"><fmt:message key="genapp.form.file.unselect"/></a>
                    <span class="add-on">&nbsp;</span>
                </c:if>
                <c:if test="${not empty __theForm.usuariAplicacio.logoSegell}">
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.LOGOSEGELLID)}" >
                    <span class="add-on">
                        <form:checkbox path="logoSegellIDDelete"/>
                        <fmt:message key="genapp.form.file.delete"/>
                    </span>
                    <span class="add-on">&nbsp;</span>   
                </c:if>
                    <span class="add-on">
                        <a target="_blank" href="<c:url value="${pfi:fileUrl(__theForm.usuariAplicacio.logoSegell)}"/>">${__theForm.usuariAplicacio.logoSegell.nom}</a>
                    </span>
                </c:if>
                </div>
              </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioFields.POTCUSTODIAR)}">
        <tr id="usuariAplicacio_potCustodiar_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioFields.POTCUSTODIAR])?'usuariAplicacio.potCustodiar':__theForm.labels[UsuariAplicacioFields.POTCUSTODIAR]}" />
              <c:if test="${not empty __theForm.help[UsuariAplicacioFields.POTCUSTODIAR]}">
              <i class="icon-info-sign" title="${__theForm.help[UsuariAplicacioFields.POTCUSTODIAR]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.POTCUSTODIAR)}" >
              <form:select cssClass="input-medium" onchange="if(typeof onChangePotCustodiar == 'function') {  onChangePotCustodiar(this); };"  path="usuariAplicacio.potCustodiar">
                <form:option value=""><fmt:message key="potcustodiar." /></form:option>
                <form:option value="true" ><fmt:message key="potcustodiar.true" /></form:option>
                <form:option value="false" ><fmt:message key="potcustodiar.false" /></form:option>
              </form:select>
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.POTCUSTODIAR)}" >
                <fmt:message key="potcustodiar.${__theForm.usuariAplicacio.potCustodiar}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
