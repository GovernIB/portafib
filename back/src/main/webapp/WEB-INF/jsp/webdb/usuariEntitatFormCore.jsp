<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="UsuariEntitatFields" className="es.caib.portafib.model.fields.UsuariEntitatFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariEntitatFields.USUARIENTITATID)}">
        <tr id="usuariEntitat_usuariEntitatID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariEntitatFields.USUARIENTITATID])?'usuariEntitat.usuariEntitatID':__theForm.labels[UsuariEntitatFields.USUARIENTITATID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[UsuariEntitatFields.USUARIENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariEntitatFields.USUARIENTITATID]}" ></i>
              </c:if>
            </td>
            <td>
            <form:errors path="usuariEntitat.usuariEntitatID" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.USUARIENTITATID)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.USUARIENTITATID)? ' uneditable-input' : ''}"  style="" maxlength="101" path="usuariEntitat.usuariEntitatID"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariEntitatFields.CARREC)}">
        <tr id="usuariEntitat_carrec_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariEntitatFields.CARREC])?'usuariEntitat.carrec':__theForm.labels[UsuariEntitatFields.CARREC]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariEntitatFields.CARREC]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariEntitatFields.CARREC]}" ></i>
              </c:if>
            </td>
            <td>
            <form:errors path="usuariEntitat.carrec" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.CARREC)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.CARREC)? ' uneditable-input' : ''}"  style="" maxlength="150" path="usuariEntitat.carrec"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariEntitatFields.USUARIPERSONAID)}">
        <tr id="usuariEntitat_usuariPersonaID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariEntitatFields.USUARIPERSONAID])?'usuariEntitat.usuariPersonaID':__theForm.labels[UsuariEntitatFields.USUARIPERSONAID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[UsuariEntitatFields.USUARIPERSONAID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariEntitatFields.USUARIPERSONAID]}" ></i>
              </c:if>
            </td>
            <td>
          <form:errors path="usuariEntitat.usuariPersonaID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.USUARIPERSONAID)}" >
          <form:hidden path="usuariEntitat.usuariPersonaID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.usuariEntitat.usuariPersonaID,__theForm.listOfUsuariPersonaForUsuariPersonaID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.USUARIPERSONAID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="usuariEntitat_usuariPersonaID"  onchange="if(typeof onChangeUsuariPersonaID == 'function') {  onChangeUsuariPersonaID(this); };"  cssClass="form-control col-md-9-optional" path="usuariEntitat.usuariPersonaID">
            <c:forEach items="${__theForm.listOfUsuariPersonaForUsuariPersonaID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariEntitatFields.ENTITATID)}">
        <tr id="usuariEntitat_entitatID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariEntitatFields.ENTITATID])?'usuariEntitat.entitatID':__theForm.labels[UsuariEntitatFields.ENTITATID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[UsuariEntitatFields.ENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariEntitatFields.ENTITATID]}" ></i>
              </c:if>
            </td>
            <td>
          <form:errors path="usuariEntitat.entitatID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.ENTITATID)}" >
          <form:hidden path="usuariEntitat.entitatID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.usuariEntitat.entitatID,__theForm.listOfEntitatForEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.ENTITATID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="usuariEntitat_entitatID"  onchange="if(typeof onChangeEntitatID == 'function') {  onChangeEntitatID(this); };"  cssClass="form-control col-md-9-optional" path="usuariEntitat.entitatID">
            <c:forEach items="${__theForm.listOfEntitatForEntitatID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariEntitatFields.ACTIU)}">
        <tr id="usuariEntitat_actiu_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariEntitatFields.ACTIU])?'usuariEntitat.actiu':__theForm.labels[UsuariEntitatFields.ACTIU]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariEntitatFields.ACTIU]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariEntitatFields.ACTIU]}" ></i>
              </c:if>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.ACTIU)}" >
              <form:errors path="usuariEntitat.actiu" cssClass="errorField alert alert-danger" />
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.ACTIU)? 'false' : 'true'}" path="usuariEntitat.actiu" />
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
             </label>
              <c:if test="${not empty __theForm.help[UsuariEntitatFields.EMAIL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariEntitatFields.EMAIL]}" ></i>
              </c:if>
            </td>
            <td>
            <form:errors path="usuariEntitat.email" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.EMAIL)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.EMAIL)? ' uneditable-input' : ''}"  style="" maxlength="100" path="usuariEntitat.email"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariEntitatFields.LOGOSEGELLID)}">
        <tr id="usuariEntitat_logoSegellID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariEntitatFields.LOGOSEGELLID])?'usuariEntitat.logoSegellID':__theForm.labels[UsuariEntitatFields.LOGOSEGELLID]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariEntitatFields.LOGOSEGELLID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariEntitatFields.LOGOSEGELLID]}" ></i>
              </c:if>
            </td>
            <td>
              <form:errors path="usuariEntitat.logoSegellID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.LOGOSEGELLID)}" >
              <a target="_blank" href="<c:url value="${pfi:fileUrl(logoSegellID.logoSegellID)}"/>">${logoSegellID.logoSegellID.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.LOGOSEGELLID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.LOGOSEGELLID)? 'true' : 'false'}" cssClass="custom-file-input form-control  ${gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.LOGOSEGELLID)? ' uneditable-input' : ''}"   path="logoSegellID" type="file" />
                  <label class="custom-file-label" for="logoSegellID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.usuariEntitat.logoSegell}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${pfi:fileUrl(__theForm.usuariEntitat.logoSegell)}"/>">${__theForm.usuariEntitat.logoSegell.nom}</a>
</small>
                  </span>
                  <span class="input-group-text" id="">
                        <form:checkbox path="logoSegellIDDelete"/>
                        <small><fmt:message key="genapp.form.file.delete"/></small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="logoSegellID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
					$('#logoSegellID').on('change', function(){
						var ruta = $('#logoSegellID').val(); 
						var rutaArray = ruta.split('\\');
						$('#logoSegellID-custom-file-label').css('display','block');
						$('#logoSegellID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
					});
				</script>                </c:otherwise>
                </c:choose>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariEntitatFields.PREDETERMINAT)}">
        <tr id="usuariEntitat_predeterminat_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariEntitatFields.PREDETERMINAT])?'usuariEntitat.predeterminat':__theForm.labels[UsuariEntitatFields.PREDETERMINAT]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariEntitatFields.PREDETERMINAT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariEntitatFields.PREDETERMINAT]}" ></i>
              </c:if>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.PREDETERMINAT)}" >
              <form:errors path="usuariEntitat.predeterminat" cssClass="errorField alert alert-danger" />
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.PREDETERMINAT)? 'false' : 'true'}" path="usuariEntitat.predeterminat" />
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
             </label>
              <c:if test="${not empty __theForm.help[UsuariEntitatFields.REBRETOTSELSAVISOS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariEntitatFields.REBRETOTSELSAVISOS]}" ></i>
              </c:if>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.REBRETOTSELSAVISOS)}" >
              <form:errors path="usuariEntitat.rebreTotsElsAvisos" cssClass="errorField alert alert-danger" />
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.REBRETOTSELSAVISOS)? 'false' : 'true'}" path="usuariEntitat.rebreTotsElsAvisos" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.REBRETOTSELSAVISOS)}" >
                <fmt:message key="genapp.checkbox.${__theForm.usuariEntitat.rebreTotsElsAvisos}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariEntitatFields.POLITICADEPLUGINFIRMAWEB)}">
        <tr id="usuariEntitat_politicaDePluginFirmaWeb_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariEntitatFields.POLITICADEPLUGINFIRMAWEB])?'usuariEntitat.politicaDePluginFirmaWeb':__theForm.labels[UsuariEntitatFields.POLITICADEPLUGINFIRMAWEB]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[UsuariEntitatFields.POLITICADEPLUGINFIRMAWEB]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariEntitatFields.POLITICADEPLUGINFIRMAWEB]}" ></i>
              </c:if>
            </td>
            <td>
          <form:errors path="usuariEntitat.politicaDePluginFirmaWeb" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.POLITICADEPLUGINFIRMAWEB)}" >
          <form:hidden path="usuariEntitat.politicaDePluginFirmaWeb"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.usuariEntitat.politicaDePluginFirmaWeb,__theForm.listOfValuesForPoliticaDePluginFirmaWeb)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.POLITICADEPLUGINFIRMAWEB)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="usuariEntitat_politicaDePluginFirmaWeb"  onchange="if(typeof onChangePoliticaDePluginFirmaWeb == 'function') {  onChangePoliticaDePluginFirmaWeb(this); };"  cssClass="form-control col-md-9-optional" path="usuariEntitat.politicaDePluginFirmaWeb">
            <c:forEach items="${__theForm.listOfValuesForPoliticaDePluginFirmaWeb}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariEntitatFields.POLITICACUSTODIA)}">
        <tr id="usuariEntitat_politicaCustodia_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariEntitatFields.POLITICACUSTODIA])?'usuariEntitat.politicaCustodia':__theForm.labels[UsuariEntitatFields.POLITICACUSTODIA]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[UsuariEntitatFields.POLITICACUSTODIA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariEntitatFields.POLITICACUSTODIA]}" ></i>
              </c:if>
            </td>
            <td>
          <form:errors path="usuariEntitat.politicaCustodia" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.POLITICACUSTODIA)}" >
          <form:hidden path="usuariEntitat.politicaCustodia"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.usuariEntitat.politicaCustodia,__theForm.listOfValuesForPoliticaCustodia)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.POLITICACUSTODIA)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="usuariEntitat_politicaCustodia"  onchange="if(typeof onChangePoliticaCustodia == 'function') {  onChangePoliticaCustodia(this); };"  cssClass="form-control col-md-9-optional" path="usuariEntitat.politicaCustodia">
            <c:forEach items="${__theForm.listOfValuesForPoliticaCustodia}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariEntitatFields.CUSTODIAINFOID)}">
        <tr id="usuariEntitat_custodiaInfoID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariEntitatFields.CUSTODIAINFOID])?'usuariEntitat.custodiaInfoID':__theForm.labels[UsuariEntitatFields.CUSTODIAINFOID]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariEntitatFields.CUSTODIAINFOID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariEntitatFields.CUSTODIAINFOID]}" ></i>
              </c:if>
            </td>
            <td>
          <form:errors path="usuariEntitat.custodiaInfoID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.CUSTODIAINFOID)}" >
          <form:hidden path="usuariEntitat.custodiaInfoID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.usuariEntitat.custodiaInfoID,__theForm.listOfCustodiaInfoForCustodiaInfoID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.CUSTODIAINFOID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="usuariEntitat_custodiaInfoID"  onchange="if(typeof onChangeCustodiaInfoID == 'function') {  onChangeCustodiaInfoID(this); };"  cssClass="form-control col-md-9-optional" path="usuariEntitat.custodiaInfoID">
            <c:forEach items="${__theForm.listOfCustodiaInfoForCustodiaInfoID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.usuariEntitat.custodiaInfoID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.usuariEntitat.custodiaInfoID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
