<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="BitacolaFields" className="es.caib.portafib.model.fields.BitacolaFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,BitacolaFields.ENTITATID)}">
        <tr id="bitacola_entitatid_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[BitacolaFields.ENTITATID])?'bitacola.entitatid':__theForm.labels[BitacolaFields.ENTITATID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[BitacolaFields.ENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[BitacolaFields.ENTITATID]}" ></i>
              </c:if>
            </td>
            <td>
            <form:errors path="bitacola.entitatid" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,BitacolaFields.ENTITATID)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,BitacolaFields.ENTITATID)? ' uneditable-input' : ''}"  style="" maxlength="50" path="bitacola.entitatid"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,BitacolaFields.USUARIID)}">
        <tr id="bitacola_usuariid_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[BitacolaFields.USUARIID])?'bitacola.usuariid':__theForm.labels[BitacolaFields.USUARIID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[BitacolaFields.USUARIID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[BitacolaFields.USUARIID]}" ></i>
              </c:if>
            </td>
            <td>
            <form:errors path="bitacola.usuariid" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,BitacolaFields.USUARIID)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,BitacolaFields.USUARIID)? ' uneditable-input' : ''}"  style="" maxlength="101" path="bitacola.usuariid"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,BitacolaFields.DATA)}">
        <tr id="bitacola_data_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[BitacolaFields.DATA])?'bitacola.data':__theForm.labels[BitacolaFields.DATA]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[BitacolaFields.DATA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[BitacolaFields.DATA]}" ></i>
              </c:if>
            </td>
            <td>
              <form:errors path="bitacola.data" cssClass="errorField alert alert-danger" />
    <div class="container">
      <div class="row">
            <div class="form-group">
                <div class="input-group date" id="bitacola_data" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,BitacolaFields.DATA)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#bitacola_data" path="bitacola.data" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,BitacolaFields.DATA)}" >
                    <div class="input-group-append"  data-target="#bitacola_data"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
          <script type="text/javascript">
            $(function () {
                $('#bitacola_data').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,BitacolaFields.TIPUSOBJECTE)}">
        <tr id="bitacola_tipusObjecte_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[BitacolaFields.TIPUSOBJECTE])?'bitacola.tipusObjecte':__theForm.labels[BitacolaFields.TIPUSOBJECTE]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[BitacolaFields.TIPUSOBJECTE]}">
              <i class="fas fa-info-circle" title="${__theForm.help[BitacolaFields.TIPUSOBJECTE]}" ></i>
              </c:if>
            </td>
            <td>
          <form:errors path="bitacola.tipusObjecte" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,BitacolaFields.TIPUSOBJECTE)}" >
          <form:hidden path="bitacola.tipusObjecte"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.bitacola.tipusObjecte,__theForm.listOfValuesForTipusObjecte)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,BitacolaFields.TIPUSOBJECTE)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="bitacola_tipusObjecte"  onchange="if(typeof onChangeTipusObjecte == 'function') {  onChangeTipusObjecte(this); };"  cssClass="form-control col-md-9-optional" path="bitacola.tipusObjecte">
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
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[BitacolaFields.OBJECTEID])?'bitacola.objecteid':__theForm.labels[BitacolaFields.OBJECTEID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[BitacolaFields.OBJECTEID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[BitacolaFields.OBJECTEID]}" ></i>
              </c:if>
            </td>
            <td>
            <form:errors path="bitacola.objecteid" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,BitacolaFields.OBJECTEID)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,BitacolaFields.OBJECTEID)? ' uneditable-input' : ''}"  style="" maxlength="100" path="bitacola.objecteid"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,BitacolaFields.TIPUSOPERACIO)}">
        <tr id="bitacola_tipusOperacio_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[BitacolaFields.TIPUSOPERACIO])?'bitacola.tipusOperacio':__theForm.labels[BitacolaFields.TIPUSOPERACIO]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[BitacolaFields.TIPUSOPERACIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[BitacolaFields.TIPUSOPERACIO]}" ></i>
              </c:if>
            </td>
            <td>
          <form:errors path="bitacola.tipusOperacio" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,BitacolaFields.TIPUSOPERACIO)}" >
          <form:hidden path="bitacola.tipusOperacio"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.bitacola.tipusOperacio,__theForm.listOfValuesForTipusOperacio)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,BitacolaFields.TIPUSOPERACIO)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="bitacola_tipusOperacio"  onchange="if(typeof onChangeTipusOperacio == 'function') {  onChangeTipusOperacio(this); };"  cssClass="form-control col-md-9-optional" path="bitacola.tipusOperacio">
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
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[BitacolaFields.DESCRIPCIO])?'bitacola.descripcio':__theForm.labels[BitacolaFields.DESCRIPCIO]}" />
             </label>
              <c:if test="${not empty __theForm.help[BitacolaFields.DESCRIPCIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[BitacolaFields.DESCRIPCIO]}" ></i>
              </c:if>
            </td>
            <td>
              <form:errors path="bitacola.descripcio" cssClass="errorField alert alert-danger" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,BitacolaFields.DESCRIPCIO)? 'true' : 'false'}" path="bitacola.descripcio"  />
      <div id="dropdownMenuButton_descripcio" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_descripcio" class="dropdown-menu">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('bitacola.descripcio'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('bitacola.descripcio'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('bitacola.descripcio'); ta.wrap='hard';">Hard Wrap</a>
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,BitacolaFields.OBJECTESERIALITZAT)}">
        <tr id="bitacola_objecteSerialitzat_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[BitacolaFields.OBJECTESERIALITZAT])?'bitacola.objecteSerialitzat':__theForm.labels[BitacolaFields.OBJECTESERIALITZAT]}" />
             </label>
              <c:if test="${not empty __theForm.help[BitacolaFields.OBJECTESERIALITZAT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[BitacolaFields.OBJECTESERIALITZAT]}" ></i>
              </c:if>
            </td>
            <td>
              <form:errors path="bitacola.objecteSerialitzat" cssClass="errorField alert alert-danger" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,BitacolaFields.OBJECTESERIALITZAT)? 'true' : 'false'}" path="bitacola.objecteSerialitzat"  />
      <div id="dropdownMenuButton_objecteSerialitzat" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_objecteSerialitzat" class="dropdown-menu">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('bitacola.objecteSerialitzat'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('bitacola.objecteSerialitzat'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('bitacola.objecteSerialitzat'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_objecteSerialitzat').on('click', function(){
					var valor = ($('#dropdownMenuContainer_objecteSerialitzat').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_objecteSerialitzat').css('display', valor);
                 return false;
				});
      </script>           </td>
        </tr>
        </c:if>
        
