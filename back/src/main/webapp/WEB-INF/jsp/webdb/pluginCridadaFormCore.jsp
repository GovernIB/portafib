<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PluginCridadaFields" className="es.caib.portafib.model.fields.PluginCridadaFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginCridadaFields.ENTITATID)}">
        <tr id="pluginCridada_entitatID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginCridadaFields.ENTITATID])?'pluginCridada.entitatID':__theForm.labels[PluginCridadaFields.ENTITATID]}" />
             </label>
              <c:if test="${not empty __theForm.help[PluginCridadaFields.ENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginCridadaFields.ENTITATID]}" ></i>
              </c:if>
            </td>
            <td>
          <form:errors path="pluginCridada.entitatID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.ENTITATID)}" >
          <form:hidden path="pluginCridada.entitatID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.pluginCridada.entitatID,__theForm.listOfEntitatForEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.ENTITATID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="pluginCridada_entitatID"  onchange="if(typeof onChangeEntitatID == 'function') {  onChangeEntitatID(this); };"  cssClass="form-control col-md-9-optional" path="pluginCridada.entitatID">
            <c:forEach items="${__theForm.listOfEntitatForEntitatID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.pluginCridada.entitatID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.pluginCridada.entitatID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginCridadaFields.DATA)}">
        <tr id="pluginCridada_data_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginCridadaFields.DATA])?'pluginCridada.data':__theForm.labels[PluginCridadaFields.DATA]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[PluginCridadaFields.DATA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginCridadaFields.DATA]}" ></i>
              </c:if>
            </td>
            <td>
              <form:errors path="pluginCridada.data" cssClass="errorField alert alert-danger" />
    <div class="container">
      <div class="row">
            <div class="form-group">
                <div class="input-group date" id="pluginCridada_data" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.DATA)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#pluginCridada_data" path="pluginCridada.data" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.DATA)}" >
                    <div class="input-group-append"  data-target="#pluginCridada_data"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
          <script type="text/javascript">
            $(function () {
                $('#pluginCridada_data').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
          </script>        </div>
      </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginCridadaFields.PLUGINID)}">
        <tr id="pluginCridada_pluginID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginCridadaFields.PLUGINID])?'pluginCridada.pluginID':__theForm.labels[PluginCridadaFields.PLUGINID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[PluginCridadaFields.PLUGINID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginCridadaFields.PLUGINID]}" ></i>
              </c:if>
            </td>
            <td>
          <form:errors path="pluginCridada.pluginID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.PLUGINID)}" >
          <form:hidden path="pluginCridada.pluginID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.pluginCridada.pluginID,__theForm.listOfPluginForPluginID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.PLUGINID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="pluginCridada_pluginID"  onchange="if(typeof onChangePluginID == 'function') {  onChangePluginID(this); };"  cssClass="form-control col-md-9-optional" path="pluginCridada.pluginID">
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
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginCridadaFields.METODEPLUGIN])?'pluginCridada.metodePlugin':__theForm.labels[PluginCridadaFields.METODEPLUGIN]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[PluginCridadaFields.METODEPLUGIN]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginCridadaFields.METODEPLUGIN]}" ></i>
              </c:if>
            </td>
            <td>
            <form:errors path="pluginCridada.metodePlugin" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.METODEPLUGIN)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.METODEPLUGIN)? ' uneditable-input' : ''}"  style="" maxlength="100" path="pluginCridada.metodePlugin"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginCridadaFields.PARAMETRESTEXT)}">
        <tr id="pluginCridada_parametresText_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginCridadaFields.PARAMETRESTEXT])?'pluginCridada.parametresText':__theForm.labels[PluginCridadaFields.PARAMETRESTEXT]}" />
             </label>
              <c:if test="${not empty __theForm.help[PluginCridadaFields.PARAMETRESTEXT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginCridadaFields.PARAMETRESTEXT]}" ></i>
              </c:if>
            </td>
            <td>
              <form:errors path="pluginCridada.parametresText" cssClass="errorField alert alert-danger" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.PARAMETRESTEXT)? 'true' : 'false'}" path="pluginCridada.parametresText"  />
      <div id="dropdownMenuButton_parametresText" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_parametresText" class="dropdown-menu">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('pluginCridada.parametresText'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('pluginCridada.parametresText'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('pluginCridada.parametresText'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_parametresText').on('click', function(){
					var valor = ($('#dropdownMenuContainer_parametresText').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_parametresText').css('display', valor);
                 return false;
				});
      </script>           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginCridadaFields.PARAMETRESFITXERID)}">
        <tr id="pluginCridada_parametresFitxerID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginCridadaFields.PARAMETRESFITXERID])?'pluginCridada.parametresFitxerID':__theForm.labels[PluginCridadaFields.PARAMETRESFITXERID]}" />
             </label>
              <c:if test="${not empty __theForm.help[PluginCridadaFields.PARAMETRESFITXERID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginCridadaFields.PARAMETRESFITXERID]}" ></i>
              </c:if>
            </td>
            <td>
              <form:errors path="pluginCridada.parametresFitxerID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.PARAMETRESFITXERID)}" >
              <a target="_blank" href="<c:url value="${pfi:fileUrl(parametresFitxerID.parametresFitxerID)}"/>">${parametresFitxerID.parametresFitxerID.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.PARAMETRESFITXERID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.PARAMETRESFITXERID)? 'true' : 'false'}" cssClass="custom-file-input form-control  ${gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.PARAMETRESFITXERID)? ' uneditable-input' : ''}"   path="parametresFitxerID" type="file" />
                  <label class="custom-file-label" for="parametresFitxerID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.pluginCridada.parametresFitxer}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${pfi:fileUrl(__theForm.pluginCridada.parametresFitxer)}"/>">${__theForm.pluginCridada.parametresFitxer.nom}</a>
</small>
                  </span>
                  <span class="input-group-text" id="">
                        <form:checkbox path="parametresFitxerIDDelete"/>
                        <small><fmt:message key="genapp.form.file.delete"/></small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="parametresFitxerID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
					$('#parametresFitxerID').on('change', function(){
						var ruta = $('#parametresFitxerID').val(); 
						var rutaArray = ruta.split('\\');
						$('#parametresFitxerID-custom-file-label').css('display','block');
						$('#parametresFitxerID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
					});
				</script>                </c:otherwise>
                </c:choose>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginCridadaFields.RETORNTEXT)}">
        <tr id="pluginCridada_retornText_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginCridadaFields.RETORNTEXT])?'pluginCridada.retornText':__theForm.labels[PluginCridadaFields.RETORNTEXT]}" />
             </label>
              <c:if test="${not empty __theForm.help[PluginCridadaFields.RETORNTEXT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginCridadaFields.RETORNTEXT]}" ></i>
              </c:if>
            </td>
            <td>
              <form:errors path="pluginCridada.retornText" cssClass="errorField alert alert-danger" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.RETORNTEXT)? 'true' : 'false'}" path="pluginCridada.retornText"  />
      <div id="dropdownMenuButton_retornText" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_retornText" class="dropdown-menu">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('pluginCridada.retornText'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('pluginCridada.retornText'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('pluginCridada.retornText'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_retornText').on('click', function(){
					var valor = ($('#dropdownMenuContainer_retornText').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_retornText').css('display', valor);
                 return false;
				});
      </script>           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginCridadaFields.RETORNFITXERID)}">
        <tr id="pluginCridada_retornFitxerID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginCridadaFields.RETORNFITXERID])?'pluginCridada.retornFitxerID':__theForm.labels[PluginCridadaFields.RETORNFITXERID]}" />
             </label>
              <c:if test="${not empty __theForm.help[PluginCridadaFields.RETORNFITXERID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginCridadaFields.RETORNFITXERID]}" ></i>
              </c:if>
            </td>
            <td>
              <form:errors path="pluginCridada.retornFitxerID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.RETORNFITXERID)}" >
              <a target="_blank" href="<c:url value="${pfi:fileUrl(retornFitxerID.retornFitxerID)}"/>">${retornFitxerID.retornFitxerID.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.RETORNFITXERID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.RETORNFITXERID)? 'true' : 'false'}" cssClass="custom-file-input form-control  ${gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.RETORNFITXERID)? ' uneditable-input' : ''}"   path="retornFitxerID" type="file" />
                  <label class="custom-file-label" for="retornFitxerID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.pluginCridada.retornFitxer}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${pfi:fileUrl(__theForm.pluginCridada.retornFitxer)}"/>">${__theForm.pluginCridada.retornFitxer.nom}</a>
</small>
                  </span>
                  <span class="input-group-text" id="">
                        <form:checkbox path="retornFitxerIDDelete"/>
                        <small><fmt:message key="genapp.form.file.delete"/></small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="retornFitxerID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
					$('#retornFitxerID').on('change', function(){
						var ruta = $('#retornFitxerID').val(); 
						var rutaArray = ruta.split('\\');
						$('#retornFitxerID-custom-file-label').css('display','block');
						$('#retornFitxerID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
					});
				</script>                </c:otherwise>
                </c:choose>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginCridadaFields.TIPUSTESULTAT)}">
        <tr id="pluginCridada_tipusTesultat_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginCridadaFields.TIPUSTESULTAT])?'pluginCridada.tipusTesultat':__theForm.labels[PluginCridadaFields.TIPUSTESULTAT]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[PluginCridadaFields.TIPUSTESULTAT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginCridadaFields.TIPUSTESULTAT]}" ></i>
              </c:if>
            </td>
            <td>
            <form:errors path="pluginCridada.tipusTesultat" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.TIPUSTESULTAT)? 'true' : 'false'}" cssClass="w-25 form-control  ${gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.TIPUSTESULTAT)? ' uneditable-input' : ''}"  style=""  path="pluginCridada.tipusTesultat"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,PluginCridadaFields.TEMPSEXECUCIO)}">
        <tr id="pluginCridada_tempsExecucio_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[PluginCridadaFields.TEMPSEXECUCIO])?'pluginCridada.tempsExecucio':__theForm.labels[PluginCridadaFields.TEMPSEXECUCIO]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[PluginCridadaFields.TEMPSEXECUCIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[PluginCridadaFields.TEMPSEXECUCIO]}" ></i>
              </c:if>
            </td>
            <td>
            <form:errors path="pluginCridada.tempsExecucio" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.TEMPSEXECUCIO)? 'true' : 'false'}" cssClass="w-25 form-control  ${gen:contains(__theForm.readOnlyFields ,PluginCridadaFields.TEMPSEXECUCIO)? ' uneditable-input' : ''}"  style=""  path="pluginCridada.tempsExecucio"   />

           </td>
        </tr>
        </c:if>
        
