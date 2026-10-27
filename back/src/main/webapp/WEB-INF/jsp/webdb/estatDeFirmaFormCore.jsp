<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="EstatDeFirmaFields" className="es.caib.portafib.model.fields.EstatDeFirmaFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstatDeFirmaFields.FIRMAID)}">
        <tr id="estatDeFirma_firmaID_rowid">
          <td id="estatDeFirma_firmaID_columnlabelid">
            <label for="estatDeFirma.firmaID">
              <fmt:message key="${(empty __theForm.labels[EstatDeFirmaFields.FIRMAID])?'estatDeFirma.firmaID':__theForm.labels[EstatDeFirmaFields.FIRMAID]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[EstatDeFirmaFields.FIRMAID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EstatDeFirmaFields.FIRMAID]}" ></i>
              </c:if>
            </td>
          <td id="estatDeFirma_firmaID_columnvalueid">
          <form:errors path="estatDeFirma.firmaID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EstatDeFirmaFields.FIRMAID)}" >
          <form:hidden path="estatDeFirma.firmaID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.estatDeFirma.firmaID,__theForm.listOfFirmaForFirmaID)}" aria-label="estatDeFirma.firmaID"   required aria-required="true"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EstatDeFirmaFields.FIRMAID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="estatDeFirma.firmaID" cssClass="form-control col-md-9-optional"  required="required"  aria-required="true"  onchange="if(typeof onChangeFirmaID == 'function') {  onChangeFirmaID(this); };">


            <c:forEach items="${__theForm.listOfFirmaForFirmaID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstatDeFirmaFields.USUARIENTITATID)}">
        <tr id="estatDeFirma_usuariEntitatID_rowid">
          <td id="estatDeFirma_usuariEntitatID_columnlabelid">
            <label for="estatDeFirma.usuariEntitatID">
              <fmt:message key="${(empty __theForm.labels[EstatDeFirmaFields.USUARIENTITATID])?'estatDeFirma.usuariEntitatID':__theForm.labels[EstatDeFirmaFields.USUARIENTITATID]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[EstatDeFirmaFields.USUARIENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EstatDeFirmaFields.USUARIENTITATID]}" ></i>
              </c:if>
            </td>
          <td id="estatDeFirma_usuariEntitatID_columnvalueid">
          <form:errors path="estatDeFirma.usuariEntitatID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EstatDeFirmaFields.USUARIENTITATID)}" >
          <form:hidden path="estatDeFirma.usuariEntitatID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.estatDeFirma.usuariEntitatID,__theForm.listOfUsuariEntitatForUsuariEntitatID)}" aria-label="estatDeFirma.usuariEntitatID"   required aria-required="true"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EstatDeFirmaFields.USUARIENTITATID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="estatDeFirma.usuariEntitatID" cssClass="form-control col-md-9-optional"  required="required"  aria-required="true"  onchange="if(typeof onChangeUsuariEntitatID == 'function') {  onChangeUsuariEntitatID(this); };">


            <c:forEach items="${__theForm.listOfUsuariEntitatForUsuariEntitatID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstatDeFirmaFields.DATAINICI)}">
        <tr id="estatDeFirma_dataInici_rowid">
          <td id="estatDeFirma_dataInici_columnlabelid">
            <label for="estatDeFirma.dataInici">
              <fmt:message key="${(empty __theForm.labels[EstatDeFirmaFields.DATAINICI])?'estatDeFirma.dataInici':__theForm.labels[EstatDeFirmaFields.DATAINICI]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[EstatDeFirmaFields.DATAINICI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EstatDeFirmaFields.DATAINICI]}" ></i>
              </c:if>
            </td>
          <td id="estatDeFirma_dataInici_columnvalueid">
    <form:errors path="estatDeFirma.dataInici" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="estatDeFirma_dataIniciDiv" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EstatDeFirmaFields.DATAINICI)? 'readonly' : ''}" cssClass="form-control datetimepicker-input"  data-target="#estatDeFirma_dataIniciDiv" path="estatDeFirma.dataInici"   required="required"  aria-required="true"  />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,EstatDeFirmaFields.DATAINICI)}" >
                    <div class="input-group-append"  data-target="#estatDeFirma_dataIniciDiv"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script>
            $(function () {
                $('#estatDeFirma_dataIniciDiv').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstatDeFirmaFields.DATAFI)}">
        <tr id="estatDeFirma_dataFi_rowid">
          <td id="estatDeFirma_dataFi_columnlabelid">
            <label for="estatDeFirma.dataFi">
              <fmt:message key="${(empty __theForm.labels[EstatDeFirmaFields.DATAFI])?'estatDeFirma.dataFi':__theForm.labels[EstatDeFirmaFields.DATAFI]}" />
             </label>
              <c:if test="${not empty __theForm.help[EstatDeFirmaFields.DATAFI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EstatDeFirmaFields.DATAFI]}" ></i>
              </c:if>
            </td>
          <td id="estatDeFirma_dataFi_columnvalueid">
    <form:errors path="estatDeFirma.dataFi" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="estatDeFirma_dataFiDiv" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EstatDeFirmaFields.DATAFI)? 'readonly' : ''}" cssClass="form-control datetimepicker-input"  data-target="#estatDeFirma_dataFiDiv" path="estatDeFirma.dataFi"   />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,EstatDeFirmaFields.DATAFI)}" >
                    <div class="input-group-append"  data-target="#estatDeFirma_dataFiDiv"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script>
            $(function () {
                $('#estatDeFirma_dataFiDiv').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID)}">
        <tr id="estatDeFirma_tipusEstatDeFirmaInicialID_rowid">
          <td id="estatDeFirma_tipusEstatDeFirmaInicialID_columnlabelid">
            <label for="estatDeFirma.tipusEstatDeFirmaInicialID">
              <fmt:message key="${(empty __theForm.labels[EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID])?'estatDeFirma.tipusEstatDeFirmaInicialID':__theForm.labels[EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID]}" ></i>
              </c:if>
            </td>
          <td id="estatDeFirma_tipusEstatDeFirmaInicialID_columnvalueid">
          <form:errors path="estatDeFirma.tipusEstatDeFirmaInicialID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID)}" >
          <form:hidden path="estatDeFirma.tipusEstatDeFirmaInicialID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.estatDeFirma.tipusEstatDeFirmaInicialID,__theForm.listOfValuesForTipusEstatDeFirmaInicialID)}" aria-label="estatDeFirma.tipusEstatDeFirmaInicialID"   required aria-required="true"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="estatDeFirma.tipusEstatDeFirmaInicialID" cssClass="form-control col-md-9-optional"  required="required"  aria-required="true"  onchange="if(typeof onChangeTipusEstatDeFirmaInicialID == 'function') {  onChangeTipusEstatDeFirmaInicialID(this); };">


            <c:forEach items="${__theForm.listOfValuesForTipusEstatDeFirmaInicialID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstatDeFirmaFields.TIPUSESTATDEFIRMAFINALID)}">
        <tr id="estatDeFirma_tipusEstatDeFirmaFinalID_rowid">
          <td id="estatDeFirma_tipusEstatDeFirmaFinalID_columnlabelid">
            <label for="estatDeFirma.tipusEstatDeFirmaFinalID">
              <fmt:message key="${(empty __theForm.labels[EstatDeFirmaFields.TIPUSESTATDEFIRMAFINALID])?'estatDeFirma.tipusEstatDeFirmaFinalID':__theForm.labels[EstatDeFirmaFields.TIPUSESTATDEFIRMAFINALID]}" />
             </label>
              <c:if test="${not empty __theForm.help[EstatDeFirmaFields.TIPUSESTATDEFIRMAFINALID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EstatDeFirmaFields.TIPUSESTATDEFIRMAFINALID]}" ></i>
              </c:if>
            </td>
          <td id="estatDeFirma_tipusEstatDeFirmaFinalID_columnvalueid">
          <form:errors path="estatDeFirma.tipusEstatDeFirmaFinalID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EstatDeFirmaFields.TIPUSESTATDEFIRMAFINALID)}" >
          <form:hidden path="estatDeFirma.tipusEstatDeFirmaFinalID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.estatDeFirma.tipusEstatDeFirmaFinalID,__theForm.listOfValuesForTipusEstatDeFirmaFinalID)}" aria-label="estatDeFirma.tipusEstatDeFirmaFinalID"   />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EstatDeFirmaFields.TIPUSESTATDEFIRMAFINALID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="estatDeFirma.tipusEstatDeFirmaFinalID" cssClass="form-control col-md-9-optional"  onchange="if(typeof onChangeTipusEstatDeFirmaFinalID == 'function') {  onChangeTipusEstatDeFirmaFinalID(this); };">


            <c:forEach items="${__theForm.listOfValuesForTipusEstatDeFirmaFinalID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>

                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.estatDeFirma.tipusEstatDeFirmaFinalID }">
                  <option value="" selected></option>

              </c:if>
              <c:if test="${not empty __theForm.estatDeFirma.tipusEstatDeFirmaFinalID }">

                  <option value="" ></option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstatDeFirmaFields.COLABORACIODELEGACIOID)}">
        <tr id="estatDeFirma_colaboracioDelegacioID_rowid">
          <td id="estatDeFirma_colaboracioDelegacioID_columnlabelid">
            <label for="estatDeFirma.colaboracioDelegacioID">
              <fmt:message key="${(empty __theForm.labels[EstatDeFirmaFields.COLABORACIODELEGACIOID])?'estatDeFirma.colaboracioDelegacioID':__theForm.labels[EstatDeFirmaFields.COLABORACIODELEGACIOID]}" />
             </label>
              <c:if test="${not empty __theForm.help[EstatDeFirmaFields.COLABORACIODELEGACIOID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EstatDeFirmaFields.COLABORACIODELEGACIOID]}" ></i>
              </c:if>
            </td>
          <td id="estatDeFirma_colaboracioDelegacioID_columnvalueid">
          <form:errors path="estatDeFirma.colaboracioDelegacioID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EstatDeFirmaFields.COLABORACIODELEGACIOID)}" >
          <form:hidden path="estatDeFirma.colaboracioDelegacioID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.estatDeFirma.colaboracioDelegacioID,__theForm.listOfColaboracioDelegacioForColaboracioDelegacioID)}" aria-label="estatDeFirma.colaboracioDelegacioID"   />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EstatDeFirmaFields.COLABORACIODELEGACIOID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="estatDeFirma.colaboracioDelegacioID" cssClass="form-control col-md-9-optional"  onchange="if(typeof onChangeColaboracioDelegacioID == 'function') {  onChangeColaboracioDelegacioID(this); };">


            <c:forEach items="${__theForm.listOfColaboracioDelegacioForColaboracioDelegacioID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>

                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.estatDeFirma.colaboracioDelegacioID }">
                  <option value="" selected></option>

              </c:if>
              <c:if test="${not empty __theForm.estatDeFirma.colaboracioDelegacioID }">

                  <option value="" ></option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstatDeFirmaFields.DESCRIPCIO)}">
        <tr id="estatDeFirma_descripcio_rowid">
          <td id="estatDeFirma_descripcio_columnlabelid">
            <label for="estatDeFirma.descripcio">
              <fmt:message key="${(empty __theForm.labels[EstatDeFirmaFields.DESCRIPCIO])?'estatDeFirma.descripcio':__theForm.labels[EstatDeFirmaFields.DESCRIPCIO]}" />
             </label>
              <c:if test="${not empty __theForm.help[EstatDeFirmaFields.DESCRIPCIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EstatDeFirmaFields.DESCRIPCIO]}" ></i>
              </c:if>
            </td>
          <td id="estatDeFirma_descripcio_columnvalueid">
              <form:errors path="estatDeFirma.descripcio" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,EstatDeFirmaFields.DESCRIPCIO)? 'readonly' : ''}" path="estatDeFirma.descripcio"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_descripcio" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;" aria-label="Opcions de format"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_descripcio" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('estatDeFirma.descripcio'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('estatDeFirma.descripcio'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('estatDeFirma.descripcio'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script>
			$('#dropdownMenuButton_descripcio').on('click', function(){
					var valor = ($('#dropdownMenuContainer_descripcio').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_descripcio').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
