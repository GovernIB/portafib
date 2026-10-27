<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PluginCridadaFields" className="es.caib.portafib.model.fields.PluginCridadaFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginCridadaFields.ENTITATID)}">
        <tr id="pluginCridada_entitatID_rowid">
          <td id="pluginCridada_entitatID_columnlabelid">
            <label for="pluginCridada.entitatID">
              <fmt:message key="${(empty __theForm.labels[PluginCridadaFields.ENTITATID])?'pluginCridada.entitatID':__theForm.labels[PluginCridadaFields.ENTITATID]}" />
             </label>
              <c:if test="${not empty __theForm.help[PluginCridadaFields.ENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginCridadaFields.ENTITATID]}" ></i>
              </c:if>
            </td>
          <td id="pluginCridada_entitatID_columnvalueid">
          <form:errors path="pluginCridada.entitatID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.ENTITATID)}" >
          <form:hidden path="pluginCridada.entitatID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.pluginCridada.entitatID,__theForm.listOfEntitatForEntitatID)}" aria-label="pluginCridada.entitatID"   />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.ENTITATID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="pluginCridada.entitatID" cssClass="form-control col-md-9-optional"  onchange="if(typeof onChangeEntitatID == 'function') {  onChangeEntitatID(this); };">


            <c:forEach items="${__theForm.listOfEntitatForEntitatID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>

                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.pluginCridada.entitatID }">
                  <option value="" selected></option>

              </c:if>
              <c:if test="${not empty __theForm.pluginCridada.entitatID }">

                  <option value="" ></option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginCridadaFields.DATA)}">
        <tr id="pluginCridada_data_rowid">
          <td id="pluginCridada_data_columnlabelid">
            <label for="pluginCridada.data">
              <fmt:message key="${(empty __theForm.labels[PluginCridadaFields.DATA])?'pluginCridada.data':__theForm.labels[PluginCridadaFields.DATA]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[PluginCridadaFields.DATA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginCridadaFields.DATA]}" ></i>
              </c:if>
            </td>
          <td id="pluginCridada_data_columnvalueid">
    <form:errors path="pluginCridada.data" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="pluginCridada_dataDiv" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.DATA)? 'readonly' : ''}" cssClass="form-control datetimepicker-input"  data-target="#pluginCridada_dataDiv" path="pluginCridada.data"   required="required"  aria-required="true"  />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.DATA)}" >
                    <div class="input-group-append"  data-target="#pluginCridada_dataDiv"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script>
            $(function () {
                $('#pluginCridada_dataDiv').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
        </script>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginCridadaFields.PLUGINID)}">
        <tr id="pluginCridada_pluginID_rowid">
          <td id="pluginCridada_pluginID_columnlabelid">
            <label for="pluginCridada.pluginID">
              <fmt:message key="${(empty __theForm.labels[PluginCridadaFields.PLUGINID])?'pluginCridada.pluginID':__theForm.labels[PluginCridadaFields.PLUGINID]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[PluginCridadaFields.PLUGINID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginCridadaFields.PLUGINID]}" ></i>
              </c:if>
            </td>
          <td id="pluginCridada_pluginID_columnvalueid">
          <form:errors path="pluginCridada.pluginID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.PLUGINID)}" >
          <form:hidden path="pluginCridada.pluginID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.pluginCridada.pluginID,__theForm.listOfPluginForPluginID)}" aria-label="pluginCridada.pluginID"   required aria-required="true"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.PLUGINID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="pluginCridada.pluginID" cssClass="form-control col-md-9-optional"  required="required"  aria-required="true"  onchange="if(typeof onChangePluginID == 'function') {  onChangePluginID(this); };">


            <c:forEach items="${__theForm.listOfPluginForPluginID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginCridadaFields.METODEPLUGIN)}">
        <tr id="pluginCridada_metodePlugin_rowid">
          <td id="pluginCridada_metodePlugin_columnlabelid">
            <label for="pluginCridada.metodePlugin">
              <fmt:message key="${(empty __theForm.labels[PluginCridadaFields.METODEPLUGIN])?'pluginCridada.metodePlugin':__theForm.labels[PluginCridadaFields.METODEPLUGIN]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[PluginCridadaFields.METODEPLUGIN]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginCridadaFields.METODEPLUGIN]}" ></i>
              </c:if>
            </td>
          <td id="pluginCridada_metodePlugin_columnvalueid">
            <form:errors path="pluginCridada.metodePlugin" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.METODEPLUGIN)? 'readonly' : ''}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.METODEPLUGIN)? ' uneditable-input' : ''}"  style="" maxlength="100" path="pluginCridada.metodePlugin"  required="required"  aria-required="true"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginCridadaFields.PARAMETRESTEXT)}">
        <tr id="pluginCridada_parametresText_rowid">
          <td id="pluginCridada_parametresText_columnlabelid">
            <label for="pluginCridada.parametresText">
              <fmt:message key="${(empty __theForm.labels[PluginCridadaFields.PARAMETRESTEXT])?'pluginCridada.parametresText':__theForm.labels[PluginCridadaFields.PARAMETRESTEXT]}" />
             </label>
              <c:if test="${not empty __theForm.help[PluginCridadaFields.PARAMETRESTEXT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginCridadaFields.PARAMETRESTEXT]}" ></i>
              </c:if>
            </td>
          <td id="pluginCridada_parametresText_columnvalueid">
              <form:errors path="pluginCridada.parametresText" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.PARAMETRESTEXT)? 'readonly' : ''}" path="pluginCridada.parametresText"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_parametresText" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;" aria-label="Opcions de format"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_parametresText" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('pluginCridada.parametresText'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('pluginCridada.parametresText'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('pluginCridada.parametresText'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script>
			$('#dropdownMenuButton_parametresText').on('click', function(){
					var valor = ($('#dropdownMenuContainer_parametresText').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_parametresText').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginCridadaFields.PARAMETRESFITXERID)}">
        <tr id="pluginCridada_parametresFitxerID_rowid">
          <td id="pluginCridada_parametresFitxerID_columnlabelid">
            <label for="pluginCridada.parametresFitxerID">
              <fmt:message key="${(empty __theForm.labels[PluginCridadaFields.PARAMETRESFITXERID])?'pluginCridada.parametresFitxerID':__theForm.labels[PluginCridadaFields.PARAMETRESFITXERID]}" />
             </label>
              <c:if test="${not empty __theForm.help[PluginCridadaFields.PARAMETRESFITXERID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginCridadaFields.PARAMETRESFITXERID]}" ></i>
              </c:if>
            </td>
          <td id="pluginCridada_parametresFitxerID_columnvalueid">
              <form:errors path="pluginCridada.parametresFitxerID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.PARAMETRESFITXERID)}" >
              <a target="_blank" href="<c:url value="${pfi:fileUrl(__theForm.pluginCridada.parametresFitxer)}"/>">${__theForm.pluginCridada.parametresFitxer.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.PARAMETRESFITXERID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <input id="pluginCridada.parametresFitxerID" name="parametresFitxerID" class="custom-file-input form-control  ${gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.PARAMETRESFITXERID)? ' uneditable-input' : ''}"  type="file"  />
                  <span id="parametresFitxerID-custom-file-label2" class="custom-file-label" >
                    <c:if test="${ empty __theForm.pluginCridada.parametresFitxer}">
                    <fmt:message key="genapp.form.file.select"/>
                    </c:if>
                  </span>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.pluginCridada.parametresFitxer}">
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="parametresFitxerID-custom-file-label">
                  <small>              <a target="_blank" href="<c:url value="${pfi:fileUrl(__theForm.pluginCridada.parametresFitxer)}"/>">${__theForm.pluginCridada.parametresFitxer.nom}</a>
</small>
                  </span>
                  <span class="input-group-text" >
                        <form:checkbox id="parametresFitxerIDDelete" path="parametresFitxerIDDelete"/>
                        <label style="margin-bottom:0px" for="parametresFitxerIDDelete">
                        <small style="color:#000">&nbsp;<fmt:message key="genapp.form.file.delete"/></small>
                        </label>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="parametresFitxerID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                </c:otherwise>
                </c:choose>
                <script>
					$('#pluginCridada\\.parametresFitxerID').on('change', function(){
						var ruta = $('#pluginCridada\\.parametresFitxerID').val(); 
						var rutaArray = ruta.split('\\');
						$('#parametresFitxerID-custom-file-label').css('display','block');
						$('#parametresFitxerID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
                       $('#parametresFitxerID-custom-file-label2').html(rutaArray[rutaArray.length - 1]);
                   });
				</script>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginCridadaFields.RETORNTEXT)}">
        <tr id="pluginCridada_retornText_rowid">
          <td id="pluginCridada_retornText_columnlabelid">
            <label for="pluginCridada.retornText">
              <fmt:message key="${(empty __theForm.labels[PluginCridadaFields.RETORNTEXT])?'pluginCridada.retornText':__theForm.labels[PluginCridadaFields.RETORNTEXT]}" />
             </label>
              <c:if test="${not empty __theForm.help[PluginCridadaFields.RETORNTEXT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginCridadaFields.RETORNTEXT]}" ></i>
              </c:if>
            </td>
          <td id="pluginCridada_retornText_columnvalueid">
              <form:errors path="pluginCridada.retornText" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.RETORNTEXT)? 'readonly' : ''}" path="pluginCridada.retornText"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_retornText" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;" aria-label="Opcions de format"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_retornText" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('pluginCridada.retornText'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('pluginCridada.retornText'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('pluginCridada.retornText'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script>
			$('#dropdownMenuButton_retornText').on('click', function(){
					var valor = ($('#dropdownMenuContainer_retornText').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_retornText').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginCridadaFields.RETORNFITXERID)}">
        <tr id="pluginCridada_retornFitxerID_rowid">
          <td id="pluginCridada_retornFitxerID_columnlabelid">
            <label for="pluginCridada.retornFitxerID">
              <fmt:message key="${(empty __theForm.labels[PluginCridadaFields.RETORNFITXERID])?'pluginCridada.retornFitxerID':__theForm.labels[PluginCridadaFields.RETORNFITXERID]}" />
             </label>
              <c:if test="${not empty __theForm.help[PluginCridadaFields.RETORNFITXERID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginCridadaFields.RETORNFITXERID]}" ></i>
              </c:if>
            </td>
          <td id="pluginCridada_retornFitxerID_columnvalueid">
              <form:errors path="pluginCridada.retornFitxerID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.RETORNFITXERID)}" >
              <a target="_blank" href="<c:url value="${pfi:fileUrl(__theForm.pluginCridada.retornFitxer)}"/>">${__theForm.pluginCridada.retornFitxer.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.RETORNFITXERID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <input id="pluginCridada.retornFitxerID" name="retornFitxerID" class="custom-file-input form-control  ${gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.RETORNFITXERID)? ' uneditable-input' : ''}"  type="file"  />
                  <span id="retornFitxerID-custom-file-label2" class="custom-file-label" >
                    <c:if test="${ empty __theForm.pluginCridada.retornFitxer}">
                    <fmt:message key="genapp.form.file.select"/>
                    </c:if>
                  </span>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.pluginCridada.retornFitxer}">
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="retornFitxerID-custom-file-label">
                  <small>              <a target="_blank" href="<c:url value="${pfi:fileUrl(__theForm.pluginCridada.retornFitxer)}"/>">${__theForm.pluginCridada.retornFitxer.nom}</a>
</small>
                  </span>
                  <span class="input-group-text" >
                        <form:checkbox id="retornFitxerIDDelete" path="retornFitxerIDDelete"/>
                        <label style="margin-bottom:0px" for="retornFitxerIDDelete">
                        <small style="color:#000">&nbsp;<fmt:message key="genapp.form.file.delete"/></small>
                        </label>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="retornFitxerID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                </c:otherwise>
                </c:choose>
                <script>
					$('#pluginCridada\\.retornFitxerID').on('change', function(){
						var ruta = $('#pluginCridada\\.retornFitxerID').val(); 
						var rutaArray = ruta.split('\\');
						$('#retornFitxerID-custom-file-label').css('display','block');
						$('#retornFitxerID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
                       $('#retornFitxerID-custom-file-label2').html(rutaArray[rutaArray.length - 1]);
                   });
				</script>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginCridadaFields.TIPUSTESULTAT)}">
        <tr id="pluginCridada_tipusTesultat_rowid">
          <td id="pluginCridada_tipusTesultat_columnlabelid">
            <label for="pluginCridada.tipusTesultat">
              <fmt:message key="${(empty __theForm.labels[PluginCridadaFields.TIPUSTESULTAT])?'pluginCridada.tipusTesultat':__theForm.labels[PluginCridadaFields.TIPUSTESULTAT]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[PluginCridadaFields.TIPUSTESULTAT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginCridadaFields.TIPUSTESULTAT]}" ></i>
              </c:if>
            </td>
          <td id="pluginCridada_tipusTesultat_columnvalueid">
            <form:errors path="pluginCridada.tipusTesultat" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.TIPUSTESULTAT)? 'readonly' : ''}" cssClass="w-25 form-control  ${gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.TIPUSTESULTAT)? ' uneditable-input' : ''}"  style=""  path="pluginCridada.tipusTesultat"  required="required"  aria-required="true"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginCridadaFields.TEMPSEXECUCIO)}">
        <tr id="pluginCridada_tempsExecucio_rowid">
          <td id="pluginCridada_tempsExecucio_columnlabelid">
            <label for="pluginCridada.tempsExecucio">
              <fmt:message key="${(empty __theForm.labels[PluginCridadaFields.TEMPSEXECUCIO])?'pluginCridada.tempsExecucio':__theForm.labels[PluginCridadaFields.TEMPSEXECUCIO]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[PluginCridadaFields.TEMPSEXECUCIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginCridadaFields.TEMPSEXECUCIO]}" ></i>
              </c:if>
            </td>
          <td id="pluginCridada_tempsExecucio_columnvalueid">
            <form:errors path="pluginCridada.tempsExecucio" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.TEMPSEXECUCIO)? 'readonly' : ''}" cssClass="w-25 form-control  ${gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.TEMPSEXECUCIO)? ' uneditable-input' : ''}"  style=""  path="pluginCridada.tempsExecucio"  required="required"  aria-required="true"   />

           </td>
        </tr>
        </c:if>
        
