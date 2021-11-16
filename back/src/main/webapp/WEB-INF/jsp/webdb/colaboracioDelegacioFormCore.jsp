<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="ColaboracioDelegacioFields" className="es.caib.portafib.model.fields.ColaboracioDelegacioFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,ColaboracioDelegacioFields.DESTINATARIID)}">
        <tr id="colaboracioDelegacio_destinatariID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[ColaboracioDelegacioFields.DESTINATARIID])?'colaboracioDelegacio.destinatariID':__theForm.labels[ColaboracioDelegacioFields.DESTINATARIID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[ColaboracioDelegacioFields.DESTINATARIID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ColaboracioDelegacioFields.DESTINATARIID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="colaboracioDelegacio.destinatariID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,ColaboracioDelegacioFields.DESTINATARIID)}" >
          <form:hidden path="colaboracioDelegacio.destinatariID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.colaboracioDelegacio.destinatariID,__theForm.listOfUsuariEntitatForDestinatariID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,ColaboracioDelegacioFields.DESTINATARIID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="colaboracioDelegacio_destinatariID"  onchange="if(typeof onChangeDestinatariID == 'function') {  onChangeDestinatariID(this); };"  cssClass="form-control col-md-9-optional" path="colaboracioDelegacio.destinatariID">
            <c:forEach items="${__theForm.listOfUsuariEntitatForDestinatariID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ColaboracioDelegacioFields.COLABORADORDELEGATID)}">
        <tr id="colaboracioDelegacio_colaboradorDelegatID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[ColaboracioDelegacioFields.COLABORADORDELEGATID])?'colaboracioDelegacio.colaboradorDelegatID':__theForm.labels[ColaboracioDelegacioFields.COLABORADORDELEGATID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[ColaboracioDelegacioFields.COLABORADORDELEGATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ColaboracioDelegacioFields.COLABORADORDELEGATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="colaboracioDelegacio.colaboradorDelegatID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,ColaboracioDelegacioFields.COLABORADORDELEGATID)}" >
          <form:hidden path="colaboracioDelegacio.colaboradorDelegatID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.colaboracioDelegacio.colaboradorDelegatID,__theForm.listOfUsuariEntitatForColaboradorDelegatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,ColaboracioDelegacioFields.COLABORADORDELEGATID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="colaboracioDelegacio_colaboradorDelegatID"  onchange="if(typeof onChangeColaboradorDelegatID == 'function') {  onChangeColaboradorDelegatID(this); };"  cssClass="form-control col-md-9-optional" path="colaboracioDelegacio.colaboradorDelegatID">
            <c:forEach items="${__theForm.listOfUsuariEntitatForColaboradorDelegatID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ColaboracioDelegacioFields.ESDELEGAT)}">
        <tr id="colaboracioDelegacio_esDelegat_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[ColaboracioDelegacioFields.ESDELEGAT])?'colaboracioDelegacio.esDelegat':__theForm.labels[ColaboracioDelegacioFields.ESDELEGAT]}" />
              <c:if test="${not empty __theForm.help[ColaboracioDelegacioFields.ESDELEGAT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ColaboracioDelegacioFields.ESDELEGAT]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,ColaboracioDelegacioFields.ESDELEGAT)}" >
              <form:errors path="colaboracioDelegacio.esDelegat" cssClass="errorField alert alert-danger" />
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,ColaboracioDelegacioFields.ESDELEGAT)? 'false' : 'true'}" path="colaboracioDelegacio.esDelegat" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,ColaboracioDelegacioFields.ESDELEGAT)}" >
                <fmt:message key="genapp.checkbox.${__theForm.colaboracioDelegacio.esDelegat}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ColaboracioDelegacioFields.MOTIU)}">
        <tr id="colaboracioDelegacio_motiu_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[ColaboracioDelegacioFields.MOTIU])?'colaboracioDelegacio.motiu':__theForm.labels[ColaboracioDelegacioFields.MOTIU]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[ColaboracioDelegacioFields.MOTIU]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ColaboracioDelegacioFields.MOTIU]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="colaboracioDelegacio.motiu" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ColaboracioDelegacioFields.MOTIU)? 'true' : 'false'}" cssClass="form-control col-md-9-optional ${gen:contains(__theForm.readOnlyFields ,ColaboracioDelegacioFields.MOTIU)? ' uneditable-input' : ''}"  style="" maxlength="60" path="colaboracioDelegacio.motiu"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ColaboracioDelegacioFields.DESCRIPCIO)}">
        <tr id="colaboracioDelegacio_descripcio_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[ColaboracioDelegacioFields.DESCRIPCIO])?'colaboracioDelegacio.descripcio':__theForm.labels[ColaboracioDelegacioFields.DESCRIPCIO]}" />
              <c:if test="${not empty __theForm.help[ColaboracioDelegacioFields.DESCRIPCIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ColaboracioDelegacioFields.DESCRIPCIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="colaboracioDelegacio.descripcio" cssClass="errorField alert alert-danger" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,ColaboracioDelegacioFields.DESCRIPCIO)? 'true' : 'false'}" path="colaboracioDelegacio.descripcio"  />
      <div id="dropdownMenuButton_descripcio" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_descripcio" class="dropdown-menu">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('colaboracioDelegacio.descripcio'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('colaboracioDelegacio.descripcio'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('colaboracioDelegacio.descripcio'); ta.wrap='hard';">Hard Wrap</a>
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ColaboracioDelegacioFields.DATAINICI)}">
        <tr id="colaboracioDelegacio_dataInici_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[ColaboracioDelegacioFields.DATAINICI])?'colaboracioDelegacio.dataInici':__theForm.labels[ColaboracioDelegacioFields.DATAINICI]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[ColaboracioDelegacioFields.DATAINICI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ColaboracioDelegacioFields.DATAINICI]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="colaboracioDelegacio.dataInici" cssClass="errorField alert alert-danger" />
    <div class="container">
      <div class="row">
            <div class="form-group">
                <div class="input-group date" id="colaboracioDelegacio_dataInici" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ColaboracioDelegacioFields.DATAINICI)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#colaboracioDelegacio_dataInici" path="colaboracioDelegacio.dataInici" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,ColaboracioDelegacioFields.DATAINICI)}" >
                    <div class="input-group-append"  data-target="#colaboracioDelegacio_dataInici"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
          <script type="text/javascript">
            $(function () {
                $('#colaboracioDelegacio_dataInici').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ColaboracioDelegacioFields.DATAFI)}">
        <tr id="colaboracioDelegacio_dataFi_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[ColaboracioDelegacioFields.DATAFI])?'colaboracioDelegacio.dataFi':__theForm.labels[ColaboracioDelegacioFields.DATAFI]}" />
              <c:if test="${not empty __theForm.help[ColaboracioDelegacioFields.DATAFI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ColaboracioDelegacioFields.DATAFI]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="colaboracioDelegacio.dataFi" cssClass="errorField alert alert-danger" />
    <div class="container">
      <div class="row">
            <div class="form-group">
                <div class="input-group date" id="colaboracioDelegacio_dataFi" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,ColaboracioDelegacioFields.DATAFI)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#colaboracioDelegacio_dataFi" path="colaboracioDelegacio.dataFi" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,ColaboracioDelegacioFields.DATAFI)}" >
                    <div class="input-group-append"  data-target="#colaboracioDelegacio_dataFi"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
          <script type="text/javascript">
            $(function () {
                $('#colaboracioDelegacio_dataFi').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ColaboracioDelegacioFields.ACTIVA)}">
        <tr id="colaboracioDelegacio_activa_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[ColaboracioDelegacioFields.ACTIVA])?'colaboracioDelegacio.activa':__theForm.labels[ColaboracioDelegacioFields.ACTIVA]}" />
              <c:if test="${not empty __theForm.help[ColaboracioDelegacioFields.ACTIVA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ColaboracioDelegacioFields.ACTIVA]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,ColaboracioDelegacioFields.ACTIVA)}" >
              <form:errors path="colaboracioDelegacio.activa" cssClass="errorField alert alert-danger" />
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,ColaboracioDelegacioFields.ACTIVA)? 'false' : 'true'}" path="colaboracioDelegacio.activa" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,ColaboracioDelegacioFields.ACTIVA)}" >
                <fmt:message key="genapp.checkbox.${__theForm.colaboracioDelegacio.activa}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ColaboracioDelegacioFields.REVISOR)}">
        <tr id="colaboracioDelegacio_revisor_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[ColaboracioDelegacioFields.REVISOR])?'colaboracioDelegacio.revisor':__theForm.labels[ColaboracioDelegacioFields.REVISOR]}" />
              <c:if test="${not empty __theForm.help[ColaboracioDelegacioFields.REVISOR]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ColaboracioDelegacioFields.REVISOR]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,ColaboracioDelegacioFields.REVISOR)}" >
              <form:errors path="colaboracioDelegacio.revisor" cssClass="errorField alert alert-danger" />
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,ColaboracioDelegacioFields.REVISOR)? 'false' : 'true'}" path="colaboracioDelegacio.revisor" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,ColaboracioDelegacioFields.REVISOR)}" >
                <fmt:message key="genapp.checkbox.${__theForm.colaboracioDelegacio.revisor}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ColaboracioDelegacioFields.MOTIUDESHABILITADA)}">
        <tr id="colaboracioDelegacio_motiuDeshabilitada_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[ColaboracioDelegacioFields.MOTIUDESHABILITADA])?'colaboracioDelegacio.motiuDeshabilitada':__theForm.labels[ColaboracioDelegacioFields.MOTIUDESHABILITADA]}" />
              <c:if test="${not empty __theForm.help[ColaboracioDelegacioFields.MOTIUDESHABILITADA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ColaboracioDelegacioFields.MOTIUDESHABILITADA]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="colaboracioDelegacio.motiuDeshabilitada" cssClass="errorField alert alert-danger" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,ColaboracioDelegacioFields.MOTIUDESHABILITADA)? 'true' : 'false'}" path="colaboracioDelegacio.motiuDeshabilitada"  />
      <div id="dropdownMenuButton_motiuDeshabilitada" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_motiuDeshabilitada" class="dropdown-menu">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('colaboracioDelegacio.motiuDeshabilitada'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('colaboracioDelegacio.motiuDeshabilitada'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('colaboracioDelegacio.motiuDeshabilitada'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_motiuDeshabilitada').on('click', function(){
					var valor = ($('#dropdownMenuContainer_motiuDeshabilitada').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_motiuDeshabilitada').css('display', valor);
                 return false;
				});
      </script>           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,ColaboracioDelegacioFields.FITXERAUTORITZACIOID)}">
        <tr id="colaboracioDelegacio_fitxerAutoritzacioID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[ColaboracioDelegacioFields.FITXERAUTORITZACIOID])?'colaboracioDelegacio.fitxerAutoritzacioID':__theForm.labels[ColaboracioDelegacioFields.FITXERAUTORITZACIOID]}" />
              <c:if test="${not empty __theForm.help[ColaboracioDelegacioFields.FITXERAUTORITZACIOID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[ColaboracioDelegacioFields.FITXERAUTORITZACIOID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="colaboracioDelegacio.fitxerAutoritzacioID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,ColaboracioDelegacioFields.FITXERAUTORITZACIOID)}" >
              <a target="_blank" href="<c:url value="${pfi:fileUrl(fitxerAutoritzacioID.fitxerAutoritzacioID)}"/>">${fitxerAutoritzacioID.fitxerAutoritzacioID.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,ColaboracioDelegacioFields.FITXERAUTORITZACIOID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,ColaboracioDelegacioFields.FITXERAUTORITZACIOID)? 'true' : 'false'}" cssClass="custom-file-input form-control col-md-9-optional ${gen:contains(__theForm.readOnlyFields ,ColaboracioDelegacioFields.FITXERAUTORITZACIOID)? ' uneditable-input' : ''}"   path="fitxerAutoritzacioID" type="file" />
                  <label class="custom-file-label" for="fitxerAutoritzacioID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.colaboracioDelegacio.fitxerAutoritzacio}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
                  <small>              <a target="_blank" href="<c:url value="${pfi:fileUrl(__theForm.colaboracioDelegacio.fitxerAutoritzacio)}"/>">${__theForm.colaboracioDelegacio.fitxerAutoritzacio.nom}</a>
</small>
                  </span>
                  <span class="input-group-text" id="">
                        <form:checkbox path="fitxerAutoritzacioIDDelete"/>
                        <small><fmt:message key="genapp.form.file.delete"/></small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="fitxerAutoritzacioID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                <script type="text/javascript">
					$('#fitxerAutoritzacioID').on('change', function(){
						var ruta = $('#fitxerAutoritzacioID').val(); 
						var rutaArray = ruta.split('\\');
						$('#fitxerAutoritzacioID-custom-file-label').css('display','block');
						$('#fitxerAutoritzacioID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
					});
				</script>                </c:otherwise>
                </c:choose>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
