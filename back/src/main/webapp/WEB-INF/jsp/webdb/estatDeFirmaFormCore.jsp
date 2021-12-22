<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="EstatDeFirmaFields" className="es.caib.portafib.model.fields.EstatDeFirmaFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstatDeFirmaFields.FIRMAID)}">
        <tr id="estatDeFirma_firmaID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EstatDeFirmaFields.FIRMAID])?'estatDeFirma.firmaID':__theForm.labels[EstatDeFirmaFields.FIRMAID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[EstatDeFirmaFields.FIRMAID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EstatDeFirmaFields.FIRMAID]}" ></i>
              </c:if>
            </td>
            <td>
          <form:errors path="estatDeFirma.firmaID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EstatDeFirmaFields.FIRMAID)}" >
          <form:hidden path="estatDeFirma.firmaID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.estatDeFirma.firmaID,__theForm.listOfFirmaForFirmaID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EstatDeFirmaFields.FIRMAID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="estatDeFirma_firmaID"  onchange="if(typeof onChangeFirmaID == 'function') {  onChangeFirmaID(this); };"  cssClass="form-control col-md-9-optional" path="estatDeFirma.firmaID">
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
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EstatDeFirmaFields.USUARIENTITATID])?'estatDeFirma.usuariEntitatID':__theForm.labels[EstatDeFirmaFields.USUARIENTITATID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[EstatDeFirmaFields.USUARIENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EstatDeFirmaFields.USUARIENTITATID]}" ></i>
              </c:if>
            </td>
            <td>
          <form:errors path="estatDeFirma.usuariEntitatID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EstatDeFirmaFields.USUARIENTITATID)}" >
          <form:hidden path="estatDeFirma.usuariEntitatID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.estatDeFirma.usuariEntitatID,__theForm.listOfUsuariEntitatForUsuariEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EstatDeFirmaFields.USUARIENTITATID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="estatDeFirma_usuariEntitatID"  onchange="if(typeof onChangeUsuariEntitatID == 'function') {  onChangeUsuariEntitatID(this); };"  cssClass="form-control col-md-9-optional" path="estatDeFirma.usuariEntitatID">
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
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EstatDeFirmaFields.DATAINICI])?'estatDeFirma.dataInici':__theForm.labels[EstatDeFirmaFields.DATAINICI]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[EstatDeFirmaFields.DATAINICI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EstatDeFirmaFields.DATAINICI]}" ></i>
              </c:if>
            </td>
            <td>
              <form:errors path="estatDeFirma.dataInici" cssClass="errorField alert alert-danger" />
    <div class="container">
      <div class="row">
            <div class="form-group">
                <div class="input-group date" id="estatDeFirma_dataInici" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EstatDeFirmaFields.DATAINICI)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#estatDeFirma_dataInici" path="estatDeFirma.dataInici" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,EstatDeFirmaFields.DATAINICI)}" >
                    <div class="input-group-append"  data-target="#estatDeFirma_dataInici"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
          <script type="text/javascript">
            $(function () {
                $('#estatDeFirma_dataInici').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstatDeFirmaFields.DATAFI)}">
        <tr id="estatDeFirma_dataFi_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EstatDeFirmaFields.DATAFI])?'estatDeFirma.dataFi':__theForm.labels[EstatDeFirmaFields.DATAFI]}" />
             </label>
              <c:if test="${not empty __theForm.help[EstatDeFirmaFields.DATAFI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EstatDeFirmaFields.DATAFI]}" ></i>
              </c:if>
            </td>
            <td>
              <form:errors path="estatDeFirma.dataFi" cssClass="errorField alert alert-danger" />
    <div class="container">
      <div class="row">
            <div class="form-group">
                <div class="input-group date" id="estatDeFirma_dataFi" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EstatDeFirmaFields.DATAFI)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#estatDeFirma_dataFi" path="estatDeFirma.dataFi" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,EstatDeFirmaFields.DATAFI)}" >
                    <div class="input-group-append"  data-target="#estatDeFirma_dataFi"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
          <script type="text/javascript">
            $(function () {
                $('#estatDeFirma_dataFi').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID)}">
        <tr id="estatDeFirma_tipusEstatDeFirmaInicialID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID])?'estatDeFirma.tipusEstatDeFirmaInicialID':__theForm.labels[EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID]}" ></i>
              </c:if>
            </td>
            <td>
          <form:errors path="estatDeFirma.tipusEstatDeFirmaInicialID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID)}" >
          <form:hidden path="estatDeFirma.tipusEstatDeFirmaInicialID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.estatDeFirma.tipusEstatDeFirmaInicialID,__theForm.listOfValuesForTipusEstatDeFirmaInicialID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EstatDeFirmaFields.TIPUSESTATDEFIRMAINICIALID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="estatDeFirma_tipusEstatDeFirmaInicialID"  onchange="if(typeof onChangeTipusEstatDeFirmaInicialID == 'function') {  onChangeTipusEstatDeFirmaInicialID(this); };"  cssClass="form-control col-md-9-optional" path="estatDeFirma.tipusEstatDeFirmaInicialID">
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
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EstatDeFirmaFields.TIPUSESTATDEFIRMAFINALID])?'estatDeFirma.tipusEstatDeFirmaFinalID':__theForm.labels[EstatDeFirmaFields.TIPUSESTATDEFIRMAFINALID]}" />
             </label>
              <c:if test="${not empty __theForm.help[EstatDeFirmaFields.TIPUSESTATDEFIRMAFINALID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EstatDeFirmaFields.TIPUSESTATDEFIRMAFINALID]}" ></i>
              </c:if>
            </td>
            <td>
          <form:errors path="estatDeFirma.tipusEstatDeFirmaFinalID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EstatDeFirmaFields.TIPUSESTATDEFIRMAFINALID)}" >
          <form:hidden path="estatDeFirma.tipusEstatDeFirmaFinalID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.estatDeFirma.tipusEstatDeFirmaFinalID,__theForm.listOfValuesForTipusEstatDeFirmaFinalID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EstatDeFirmaFields.TIPUSESTATDEFIRMAFINALID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="estatDeFirma_tipusEstatDeFirmaFinalID"  onchange="if(typeof onChangeTipusEstatDeFirmaFinalID == 'function') {  onChangeTipusEstatDeFirmaFinalID(this); };"  cssClass="form-control col-md-9-optional" path="estatDeFirma.tipusEstatDeFirmaFinalID">
            <c:forEach items="${__theForm.listOfValuesForTipusEstatDeFirmaFinalID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.estatDeFirma.tipusEstatDeFirmaFinalID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.estatDeFirma.tipusEstatDeFirmaFinalID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstatDeFirmaFields.COLABORACIODELEGACIOID)}">
        <tr id="estatDeFirma_colaboracioDelegacioID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EstatDeFirmaFields.COLABORACIODELEGACIOID])?'estatDeFirma.colaboracioDelegacioID':__theForm.labels[EstatDeFirmaFields.COLABORACIODELEGACIOID]}" />
             </label>
              <c:if test="${not empty __theForm.help[EstatDeFirmaFields.COLABORACIODELEGACIOID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EstatDeFirmaFields.COLABORACIODELEGACIOID]}" ></i>
              </c:if>
            </td>
            <td>
          <form:errors path="estatDeFirma.colaboracioDelegacioID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EstatDeFirmaFields.COLABORACIODELEGACIOID)}" >
          <form:hidden path="estatDeFirma.colaboracioDelegacioID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.estatDeFirma.colaboracioDelegacioID,__theForm.listOfColaboracioDelegacioForColaboracioDelegacioID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EstatDeFirmaFields.COLABORACIODELEGACIOID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="estatDeFirma_colaboracioDelegacioID"  onchange="if(typeof onChangeColaboracioDelegacioID == 'function') {  onChangeColaboracioDelegacioID(this); };"  cssClass="form-control col-md-9-optional" path="estatDeFirma.colaboracioDelegacioID">
            <c:forEach items="${__theForm.listOfColaboracioDelegacioForColaboracioDelegacioID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.estatDeFirma.colaboracioDelegacioID }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.estatDeFirma.colaboracioDelegacioID }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EstatDeFirmaFields.DESCRIPCIO)}">
        <tr id="estatDeFirma_descripcio_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[EstatDeFirmaFields.DESCRIPCIO])?'estatDeFirma.descripcio':__theForm.labels[EstatDeFirmaFields.DESCRIPCIO]}" />
             </label>
              <c:if test="${not empty __theForm.help[EstatDeFirmaFields.DESCRIPCIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EstatDeFirmaFields.DESCRIPCIO]}" ></i>
              </c:if>
            </td>
            <td>
              <form:errors path="estatDeFirma.descripcio" cssClass="errorField alert alert-danger" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,EstatDeFirmaFields.DESCRIPCIO)? 'true' : 'false'}" path="estatDeFirma.descripcio"  />
      <div id="dropdownMenuButton_descripcio" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_descripcio" class="dropdown-menu">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('estatDeFirma.descripcio'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('estatDeFirma.descripcio'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('estatDeFirma.descripcio'); ta.wrap='hard';">Hard Wrap</a>
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
        
