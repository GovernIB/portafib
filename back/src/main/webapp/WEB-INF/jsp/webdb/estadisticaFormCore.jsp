<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="EstadisticaFields" className="es.caib.portafib.model.fields.EstadisticaFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstadisticaFields.DATA)}">
        <tr id="estadistica_data_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EstadisticaFields.DATA])?'estadistica.data':__theForm.labels[EstadisticaFields.DATA]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EstadisticaFields.DATA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EstadisticaFields.DATA]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="estadistica.data" cssClass="errorField alert alert-danger" />
    <div class="container">
      <div class="row">
            <div class="form-group">
                <div class="input-group date" id="estadistica_data" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EstadisticaFields.DATA)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#estadistica_data" path="estadistica.data" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,EstadisticaFields.DATA)}" >
                    <div class="input-group-append"  data-target="#estadistica_data"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
          <script type="text/javascript">
            $(function () {
                $('#estadistica_data').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstadisticaFields.TIPUS)}">
        <tr id="estadistica_tipus_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EstadisticaFields.TIPUS])?'estadistica.tipus':__theForm.labels[EstadisticaFields.TIPUS]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EstadisticaFields.TIPUS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EstadisticaFields.TIPUS]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="estadistica.tipus" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EstadisticaFields.TIPUS)}" >
          <form:hidden path="estadistica.tipus"/>
          <input type="text" readonly="true" class="form-control input-xxlarge uneditable-input" value="${gen:findValue(__theForm.estadistica.tipus,__theForm.listOfValuesForTipus)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EstadisticaFields.TIPUS)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="estadistica_tipus"  onchange="if(typeof onChangeTipus == 'function') {  onChangeTipus(this); };"  cssClass="form-control col-md-8" path="estadistica.tipus">
            <c:forEach items="${__theForm.listOfValuesForTipus}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstadisticaFields.ENTITATID)}">
        <tr id="estadistica_entitatID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EstadisticaFields.ENTITATID])?'estadistica.entitatID':__theForm.labels[EstadisticaFields.ENTITATID]}" />
              <c:if test="${not empty __theForm.help[EstadisticaFields.ENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EstadisticaFields.ENTITATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="estadistica.entitatID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EstadisticaFields.ENTITATID)}" >
          <form:hidden path="estadistica.entitatID"/>
          <input type="text" readonly="true" class="form-control input-xxlarge uneditable-input" value="${gen:findValue(__theForm.estadistica.entitatID,__theForm.listOfEntitatForEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EstadisticaFields.ENTITATID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="estadistica_entitatID"  onchange="if(typeof onChangeEntitatID == 'function') {  onChangeEntitatID(this); };"  cssClass="form-control col-md-8" path="estadistica.entitatID">
            <c:forEach items="${__theForm.listOfEntitatForEntitatID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.estadistica.entitatID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.estadistica.entitatID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstadisticaFields.VALOR)}">
        <tr id="estadistica_valor_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EstadisticaFields.VALOR])?'estadistica.valor':__theForm.labels[EstadisticaFields.VALOR]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[EstadisticaFields.VALOR]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EstadisticaFields.VALOR]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="estadistica.valor" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EstadisticaFields.VALOR)? 'true' : 'false'}" cssClass="form-control ${gen:contains(__theForm.readOnlyFields ,EstadisticaFields.VALOR)? ' uneditable-input' : ''}"  style=""  path="estadistica.valor"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstadisticaFields.USUARIAPLICACIOID)}">
        <tr id="estadistica_usuariAplicacioID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EstadisticaFields.USUARIAPLICACIOID])?'estadistica.usuariAplicacioID':__theForm.labels[EstadisticaFields.USUARIAPLICACIOID]}" />
              <c:if test="${not empty __theForm.help[EstadisticaFields.USUARIAPLICACIOID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EstadisticaFields.USUARIAPLICACIOID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="estadistica.usuariAplicacioID" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EstadisticaFields.USUARIAPLICACIOID)? 'true' : 'false'}" cssClass="form-control ${gen:contains(__theForm.readOnlyFields ,EstadisticaFields.USUARIAPLICACIOID)? ' uneditable-input' : ''}"  style="" maxlength="101" path="estadistica.usuariAplicacioID"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstadisticaFields.USUARIENTITATID)}">
        <tr id="estadistica_usuariEntitatID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EstadisticaFields.USUARIENTITATID])?'estadistica.usuariEntitatID':__theForm.labels[EstadisticaFields.USUARIENTITATID]}" />
              <c:if test="${not empty __theForm.help[EstadisticaFields.USUARIENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EstadisticaFields.USUARIENTITATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="estadistica.usuariEntitatID" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EstadisticaFields.USUARIENTITATID)? 'true' : 'false'}" cssClass="form-control ${gen:contains(__theForm.readOnlyFields ,EstadisticaFields.USUARIENTITATID)? ' uneditable-input' : ''}"  style="" maxlength="101" path="estadistica.usuariEntitatID"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstadisticaFields.PARAMETRES)}">
        <tr id="estadistica_parametres_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EstadisticaFields.PARAMETRES])?'estadistica.parametres':__theForm.labels[EstadisticaFields.PARAMETRES]}" />
              <c:if test="${not empty __theForm.help[EstadisticaFields.PARAMETRES]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EstadisticaFields.PARAMETRES]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="estadistica.parametres" cssClass="errorField alert alert-danger" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;max-width:90%;" cssClass="form-control " readonly="${ gen:contains(__theForm.readOnlyFields ,EstadisticaFields.PARAMETRES)? 'true' : 'false'}" path="estadistica.parametres"  />
      <div id="dropdownMenuButton_parametres" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_parametres" class="dropdown-menu">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('estadistica.parametres'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('estadistica.parametres'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('estadistica.parametres'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_parametres').on('click', function(){
					var valor = ($('#dropdownMenuContainer_parametres').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_parametres').css('display', valor);
                 return false;
				});
      </script>           </td>
        </tr>
        </c:if>
        
