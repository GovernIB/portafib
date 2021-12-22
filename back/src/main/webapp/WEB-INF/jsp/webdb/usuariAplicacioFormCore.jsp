<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="UsuariAplicacioFields" className="es.caib.portafib.model.fields.UsuariAplicacioFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioFields.USUARIAPLICACIOID)}">
        <tr id="usuariAplicacio_usuariAplicacioID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioFields.USUARIAPLICACIOID])?'usuariAplicacio.usuariAplicacioID':__theForm.labels[UsuariAplicacioFields.USUARIAPLICACIOID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioFields.USUARIAPLICACIOID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioFields.USUARIAPLICACIOID]}" ></i>
              </c:if>
            </td>
            <td>
            <form:errors path="usuariAplicacio.usuariAplicacioID" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.USUARIAPLICACIOID)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.USUARIAPLICACIOID)? ' uneditable-input' : ''}"  style="" maxlength="101" path="usuariAplicacio.usuariAplicacioID"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioFields.ENTITATID)}">
        <tr id="usuariAplicacio_entitatID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioFields.ENTITATID])?'usuariAplicacio.entitatID':__theForm.labels[UsuariAplicacioFields.ENTITATID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioFields.ENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioFields.ENTITATID]}" ></i>
              </c:if>
            </td>
            <td>
          <form:errors path="usuariAplicacio.entitatID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.ENTITATID)}" >
          <form:hidden path="usuariAplicacio.entitatID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.usuariAplicacio.entitatID,__theForm.listOfEntitatForEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.ENTITATID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="usuariAplicacio_entitatID"  onchange="if(typeof onChangeEntitatID == 'function') {  onChangeEntitatID(this); };"  cssClass="form-control col-md-9-optional" path="usuariAplicacio.entitatID">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioFields.EMAILADMIN)}">
        <tr id="usuariAplicacio_emailAdmin_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioFields.EMAILADMIN])?'usuariAplicacio.emailAdmin':__theForm.labels[UsuariAplicacioFields.EMAILADMIN]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioFields.EMAILADMIN]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioFields.EMAILADMIN]}" ></i>
              </c:if>
            </td>
            <td>
            <form:errors path="usuariAplicacio.emailAdmin" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.EMAILADMIN)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.EMAILADMIN)? ' uneditable-input' : ''}"  style="" maxlength="100" path="usuariAplicacio.emailAdmin"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioFields.CALLBACKVERSIO)}">
        <tr id="usuariAplicacio_callbackVersio_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioFields.CALLBACKVERSIO])?'usuariAplicacio.callbackVersio':__theForm.labels[UsuariAplicacioFields.CALLBACKVERSIO]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioFields.CALLBACKVERSIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioFields.CALLBACKVERSIO]}" ></i>
              </c:if>
            </td>
            <td>
          <form:errors path="usuariAplicacio.callbackVersio" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.CALLBACKVERSIO)}" >
          <form:hidden path="usuariAplicacio.callbackVersio"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.usuariAplicacio.callbackVersio,__theForm.listOfValuesForCallbackVersio)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.CALLBACKVERSIO)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="usuariAplicacio_callbackVersio"  onchange="if(typeof onChangeCallbackVersio == 'function') {  onChangeCallbackVersio(this); };"  cssClass="form-control col-md-9-optional" path="usuariAplicacio.callbackVersio">
            <c:forEach items="${__theForm.listOfValuesForCallbackVersio}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioFields.CALLBACKURL)}">
        <tr id="usuariAplicacio_callbackURL_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioFields.CALLBACKURL])?'usuariAplicacio.callbackURL':__theForm.labels[UsuariAplicacioFields.CALLBACKURL]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioFields.CALLBACKURL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioFields.CALLBACKURL]}" ></i>
              </c:if>
            </td>
            <td>
            <form:errors path="usuariAplicacio.callbackURL" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.CALLBACKURL)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.CALLBACKURL)? ' uneditable-input' : ''}"  style="" maxlength="400" path="usuariAplicacio.callbackURL"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioFields.ACTIU)}">
        <tr id="usuariAplicacio_actiu_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioFields.ACTIU])?'usuariAplicacio.actiu':__theForm.labels[UsuariAplicacioFields.ACTIU]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioFields.ACTIU]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioFields.ACTIU]}" ></i>
              </c:if>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.ACTIU)}" >
              <form:errors path="usuariAplicacio.actiu" cssClass="errorField alert alert-danger" />
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.ACTIU)? 'false' : 'true'}" path="usuariAplicacio.actiu" />
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
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioFields.IDIOMAID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioFields.IDIOMAID]}" ></i>
              </c:if>
            </td>
            <td>
          <form:errors path="usuariAplicacio.idiomaID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.IDIOMAID)}" >
          <form:hidden path="usuariAplicacio.idiomaID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.usuariAplicacio.idiomaID,__theForm.listOfIdiomaForIdiomaID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.IDIOMAID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="usuariAplicacio_idiomaID"  onchange="if(typeof onChangeIdiomaID == 'function') {  onChangeIdiomaID(this); };"  cssClass="form-control col-md-9-optional" path="usuariAplicacio.idiomaID">
            <c:forEach items="${__theForm.listOfIdiomaForIdiomaID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioFields.DESCRIPCIO)}">
        <tr id="usuariAplicacio_descripcio_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioFields.DESCRIPCIO])?'usuariAplicacio.descripcio':__theForm.labels[UsuariAplicacioFields.DESCRIPCIO]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioFields.DESCRIPCIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioFields.DESCRIPCIO]}" ></i>
              </c:if>
            </td>
            <td>
              <form:errors path="usuariAplicacio.descripcio" cssClass="errorField alert alert-danger" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.DESCRIPCIO)? 'true' : 'false'}" path="usuariAplicacio.descripcio"  />
      <div id="dropdownMenuButton_descripcio" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_descripcio" class="dropdown-menu">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('usuariAplicacio.descripcio'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('usuariAplicacio.descripcio'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('usuariAplicacio.descripcio'); ta.wrap='hard';">Hard Wrap</a>
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioFields.LOGOSEGELLID)}">
        <tr id="usuariAplicacio_logoSegellID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioFields.LOGOSEGELLID])?'usuariAplicacio.logoSegellID':__theForm.labels[UsuariAplicacioFields.LOGOSEGELLID]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioFields.LOGOSEGELLID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioFields.LOGOSEGELLID]}" ></i>
              </c:if>
            </td>
            <td>
              <form:errors path="usuariAplicacio.logoSegellID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.LOGOSEGELLID)}" >
              <a target="_blank" href="<c:url value="${pfi:fileUrl(logoSegellID.logoSegellID)}"/>">${logoSegellID.logoSegellID.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.LOGOSEGELLID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.LOGOSEGELLID)? 'true' : 'false'}" cssClass="custom-file-input form-control  ${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.LOGOSEGELLID)? ' uneditable-input' : ''}"   path="logoSegellID" type="file" />
                  <label class="custom-file-label" for="logoSegellID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.usuariAplicacio.logoSegell}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${pfi:fileUrl(__theForm.usuariAplicacio.logoSegell)}"/>">${__theForm.usuariAplicacio.logoSegell.nom}</a>
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioFields.POLITICADEPLUGINFIRMAWEB)}">
        <tr id="usuariAplicacio_politicaDePluginFirmaWeb_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioFields.POLITICADEPLUGINFIRMAWEB])?'usuariAplicacio.politicaDePluginFirmaWeb':__theForm.labels[UsuariAplicacioFields.POLITICADEPLUGINFIRMAWEB]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioFields.POLITICADEPLUGINFIRMAWEB]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioFields.POLITICADEPLUGINFIRMAWEB]}" ></i>
              </c:if>
            </td>
            <td>
          <form:errors path="usuariAplicacio.politicaDePluginFirmaWeb" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.POLITICADEPLUGINFIRMAWEB)}" >
          <form:hidden path="usuariAplicacio.politicaDePluginFirmaWeb"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.usuariAplicacio.politicaDePluginFirmaWeb,__theForm.listOfValuesForPoliticaDePluginFirmaWeb)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.POLITICADEPLUGINFIRMAWEB)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="usuariAplicacio_politicaDePluginFirmaWeb"  onchange="if(typeof onChangePoliticaDePluginFirmaWeb == 'function') {  onChangePoliticaDePluginFirmaWeb(this); };"  cssClass="form-control col-md-9-optional" path="usuariAplicacio.politicaDePluginFirmaWeb">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioFields.POLITICACUSTODIA)}">
        <tr id="usuariAplicacio_politicaCustodia_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioFields.POLITICACUSTODIA])?'usuariAplicacio.politicaCustodia':__theForm.labels[UsuariAplicacioFields.POLITICACUSTODIA]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioFields.POLITICACUSTODIA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioFields.POLITICACUSTODIA]}" ></i>
              </c:if>
            </td>
            <td>
          <form:errors path="usuariAplicacio.politicaCustodia" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.POLITICACUSTODIA)}" >
          <form:hidden path="usuariAplicacio.politicaCustodia"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.usuariAplicacio.politicaCustodia,__theForm.listOfValuesForPoliticaCustodia)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.POLITICACUSTODIA)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="usuariAplicacio_politicaCustodia"  onchange="if(typeof onChangePoliticaCustodia == 'function') {  onChangePoliticaCustodia(this); };"  cssClass="form-control col-md-9-optional" path="usuariAplicacio.politicaCustodia">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioFields.CUSTODIAINFOID)}">
        <tr id="usuariAplicacio_custodiaInfoID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioFields.CUSTODIAINFOID])?'usuariAplicacio.custodiaInfoID':__theForm.labels[UsuariAplicacioFields.CUSTODIAINFOID]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioFields.CUSTODIAINFOID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioFields.CUSTODIAINFOID]}" ></i>
              </c:if>
            </td>
            <td>
          <form:errors path="usuariAplicacio.custodiaInfoID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.CUSTODIAINFOID)}" >
          <form:hidden path="usuariAplicacio.custodiaInfoID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.usuariAplicacio.custodiaInfoID,__theForm.listOfCustodiaInfoForCustodiaInfoID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.CUSTODIAINFOID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="usuariAplicacio_custodiaInfoID"  onchange="if(typeof onChangeCustodiaInfoID == 'function') {  onChangeCustodiaInfoID(this); };"  cssClass="form-control col-md-9-optional" path="usuariAplicacio.custodiaInfoID">
            <c:forEach items="${__theForm.listOfCustodiaInfoForCustodiaInfoID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.usuariAplicacio.custodiaInfoID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.usuariAplicacio.custodiaInfoID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariAplicacioFields.CREARUSUARIS)}">
        <tr id="usuariAplicacio_crearUsuaris_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariAplicacioFields.CREARUSUARIS])?'usuariAplicacio.crearUsuaris':__theForm.labels[UsuariAplicacioFields.CREARUSUARIS]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariAplicacioFields.CREARUSUARIS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariAplicacioFields.CREARUSUARIS]}" ></i>
              </c:if>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.CREARUSUARIS)}" >
              <form:errors path="usuariAplicacio.crearUsuaris" cssClass="errorField alert alert-danger" />
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.CREARUSUARIS)? 'false' : 'true'}" path="usuariAplicacio.crearUsuaris" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariAplicacioFields.CREARUSUARIS)}" >
                <fmt:message key="genapp.checkbox.${__theForm.usuariAplicacio.crearUsuaris}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
