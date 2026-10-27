<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="BitacolaFields" className="es.caib.portafib.model.fields.BitacolaFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,BitacolaFields.ENTITATID)}">
        <tr id="bitacola_entitatid_rowid">
          <td id="bitacola_entitatid_columnlabelid">
            <label for="bitacola.entitatid">
              <fmt:message key="${(empty __theForm.labels[BitacolaFields.ENTITATID])?'bitacola.entitatid':__theForm.labels[BitacolaFields.ENTITATID]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[BitacolaFields.ENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[BitacolaFields.ENTITATID]}" ></i>
              </c:if>
            </td>
          <td id="bitacola_entitatid_columnvalueid">
            <form:errors path="bitacola.entitatid" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,BitacolaFields.ENTITATID)? 'readonly' : ''}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,BitacolaFields.ENTITATID)? ' uneditable-input' : ''}"  style="" maxlength="50" path="bitacola.entitatid"  required="required"  aria-required="true"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,BitacolaFields.USUARIID)}">
        <tr id="bitacola_usuariid_rowid">
          <td id="bitacola_usuariid_columnlabelid">
            <label for="bitacola.usuariid">
              <fmt:message key="${(empty __theForm.labels[BitacolaFields.USUARIID])?'bitacola.usuariid':__theForm.labels[BitacolaFields.USUARIID]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[BitacolaFields.USUARIID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[BitacolaFields.USUARIID]}" ></i>
              </c:if>
            </td>
          <td id="bitacola_usuariid_columnvalueid">
            <form:errors path="bitacola.usuariid" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,BitacolaFields.USUARIID)? 'readonly' : ''}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,BitacolaFields.USUARIID)? ' uneditable-input' : ''}"  style="" maxlength="101" path="bitacola.usuariid"  required="required"  aria-required="true"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,BitacolaFields.DATA)}">
        <tr id="bitacola_data_rowid">
          <td id="bitacola_data_columnlabelid">
            <label for="bitacola.data">
              <fmt:message key="${(empty __theForm.labels[BitacolaFields.DATA])?'bitacola.data':__theForm.labels[BitacolaFields.DATA]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[BitacolaFields.DATA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[BitacolaFields.DATA]}" ></i>
              </c:if>
            </td>
          <td id="bitacola_data_columnvalueid">
    <form:errors path="bitacola.data" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="bitacola_dataDiv" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,BitacolaFields.DATA)? 'readonly' : ''}" cssClass="form-control datetimepicker-input"  data-target="#bitacola_dataDiv" path="bitacola.data"   required="required"  aria-required="true"  />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,BitacolaFields.DATA)}" >
                    <div class="input-group-append"  data-target="#bitacola_dataDiv"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script>
            $(function () {
                $('#bitacola_dataDiv').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,BitacolaFields.TIPUSOBJECTE)}">
        <tr id="bitacola_tipusObjecte_rowid">
          <td id="bitacola_tipusObjecte_columnlabelid">
            <label for="bitacola.tipusObjecte">
              <fmt:message key="${(empty __theForm.labels[BitacolaFields.TIPUSOBJECTE])?'bitacola.tipusObjecte':__theForm.labels[BitacolaFields.TIPUSOBJECTE]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[BitacolaFields.TIPUSOBJECTE]}">
              <i class="fas fa-info-circle" title="${__theForm.help[BitacolaFields.TIPUSOBJECTE]}" ></i>
              </c:if>
            </td>
          <td id="bitacola_tipusObjecte_columnvalueid">
          <form:errors path="bitacola.tipusObjecte" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,BitacolaFields.TIPUSOBJECTE)}" >
          <form:hidden path="bitacola.tipusObjecte"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.bitacola.tipusObjecte,__theForm.listOfValuesForTipusObjecte)}" aria-label="bitacola.tipusObjecte"   required aria-required="true"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,BitacolaFields.TIPUSOBJECTE)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="bitacola.tipusObjecte" cssClass="form-control col-md-9-optional"  required="required"  aria-required="true"  onchange="if(typeof onChangeTipusObjecte == 'function') {  onChangeTipusObjecte(this); };">


            <c:forEach items="${__theForm.listOfValuesForTipusObjecte}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,BitacolaFields.OBJECTEID)}">
        <tr id="bitacola_objecteid_rowid">
          <td id="bitacola_objecteid_columnlabelid">
            <label for="bitacola.objecteid">
              <fmt:message key="${(empty __theForm.labels[BitacolaFields.OBJECTEID])?'bitacola.objecteid':__theForm.labels[BitacolaFields.OBJECTEID]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[BitacolaFields.OBJECTEID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[BitacolaFields.OBJECTEID]}" ></i>
              </c:if>
            </td>
          <td id="bitacola_objecteid_columnvalueid">
            <form:errors path="bitacola.objecteid" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,BitacolaFields.OBJECTEID)? 'readonly' : ''}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,BitacolaFields.OBJECTEID)? ' uneditable-input' : ''}"  style="" maxlength="100" path="bitacola.objecteid"  required="required"  aria-required="true"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,BitacolaFields.TIPUSOPERACIO)}">
        <tr id="bitacola_tipusOperacio_rowid">
          <td id="bitacola_tipusOperacio_columnlabelid">
            <label for="bitacola.tipusOperacio">
              <fmt:message key="${(empty __theForm.labels[BitacolaFields.TIPUSOPERACIO])?'bitacola.tipusOperacio':__theForm.labels[BitacolaFields.TIPUSOPERACIO]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[BitacolaFields.TIPUSOPERACIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[BitacolaFields.TIPUSOPERACIO]}" ></i>
              </c:if>
            </td>
          <td id="bitacola_tipusOperacio_columnvalueid">
          <form:errors path="bitacola.tipusOperacio" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,BitacolaFields.TIPUSOPERACIO)}" >
          <form:hidden path="bitacola.tipusOperacio"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.bitacola.tipusOperacio,__theForm.listOfValuesForTipusOperacio)}" aria-label="bitacola.tipusOperacio"   required aria-required="true"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,BitacolaFields.TIPUSOPERACIO)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="bitacola.tipusOperacio" cssClass="form-control col-md-9-optional"  required="required"  aria-required="true"  onchange="if(typeof onChangeTipusOperacio == 'function') {  onChangeTipusOperacio(this); };">


            <c:forEach items="${__theForm.listOfValuesForTipusOperacio}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,BitacolaFields.DESCRIPCIO)}">
        <tr id="bitacola_descripcio_rowid">
          <td id="bitacola_descripcio_columnlabelid">
            <label for="bitacola.descripcio">
              <fmt:message key="${(empty __theForm.labels[BitacolaFields.DESCRIPCIO])?'bitacola.descripcio':__theForm.labels[BitacolaFields.DESCRIPCIO]}" />
             </label>
              <c:if test="${not empty __theForm.help[BitacolaFields.DESCRIPCIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[BitacolaFields.DESCRIPCIO]}" ></i>
              </c:if>
            </td>
          <td id="bitacola_descripcio_columnvalueid">
              <form:errors path="bitacola.descripcio" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,BitacolaFields.DESCRIPCIO)? 'readonly' : ''}" path="bitacola.descripcio"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_descripcio" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;" aria-label="Opcions de format"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_descripcio" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('bitacola.descripcio'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('bitacola.descripcio'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('bitacola.descripcio'); ta.wrap='hard';">Hard Wrap</a>
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,BitacolaFields.OBJECTESERIALITZAT)}">
        <tr id="bitacola_objecteSerialitzat_rowid">
          <td id="bitacola_objecteSerialitzat_columnlabelid">
            <label for="bitacola.objecteSerialitzat">
              <fmt:message key="${(empty __theForm.labels[BitacolaFields.OBJECTESERIALITZAT])?'bitacola.objecteSerialitzat':__theForm.labels[BitacolaFields.OBJECTESERIALITZAT]}" />
             </label>
              <c:if test="${not empty __theForm.help[BitacolaFields.OBJECTESERIALITZAT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[BitacolaFields.OBJECTESERIALITZAT]}" ></i>
              </c:if>
            </td>
          <td id="bitacola_objecteSerialitzat_columnvalueid">
              <form:errors path="bitacola.objecteSerialitzat" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,BitacolaFields.OBJECTESERIALITZAT)? 'readonly' : ''}" path="bitacola.objecteSerialitzat"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_objecteSerialitzat" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;" aria-label="Opcions de format"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_objecteSerialitzat" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('bitacola.objecteSerialitzat'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('bitacola.objecteSerialitzat'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('bitacola.objecteSerialitzat'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script>
			$('#dropdownMenuButton_objecteSerialitzat').on('click', function(){
					var valor = ($('#dropdownMenuContainer_objecteSerialitzat').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_objecteSerialitzat').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
