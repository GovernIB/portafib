<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="CorreuAgrupatFields" className="es.caib.portafib.model.fields.CorreuAgrupatFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,CorreuAgrupatFields.EMAIL)}">
        <tr id="correuAgrupat_email_rowid">
          <td id="correuAgrupat_email_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[CorreuAgrupatFields.EMAIL])?'correuAgrupat.email':__theForm.labels[CorreuAgrupatFields.EMAIL]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[CorreuAgrupatFields.EMAIL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[CorreuAgrupatFields.EMAIL]}" ></i>
              </c:if>
            </td>
          <td id="correuAgrupat_email_columnvalueid">
            <form:errors path="correuAgrupat.email" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,CorreuAgrupatFields.EMAIL)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,CorreuAgrupatFields.EMAIL)? ' uneditable-input' : ''}"  style="" maxlength="255" path="correuAgrupat.email"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CorreuAgrupatFields.SUBJECT)}">
        <tr id="correuAgrupat_subject_rowid">
          <td id="correuAgrupat_subject_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[CorreuAgrupatFields.SUBJECT])?'correuAgrupat.subject':__theForm.labels[CorreuAgrupatFields.SUBJECT]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[CorreuAgrupatFields.SUBJECT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[CorreuAgrupatFields.SUBJECT]}" ></i>
              </c:if>
            </td>
          <td id="correuAgrupat_subject_columnvalueid">
            <form:errors path="correuAgrupat.subject" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,CorreuAgrupatFields.SUBJECT)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,CorreuAgrupatFields.SUBJECT)? ' uneditable-input' : ''}"  style="" maxlength="255" path="correuAgrupat.subject"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CorreuAgrupatFields.MESSAGE)}">
        <tr id="correuAgrupat_message_rowid">
          <td id="correuAgrupat_message_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[CorreuAgrupatFields.MESSAGE])?'correuAgrupat.message':__theForm.labels[CorreuAgrupatFields.MESSAGE]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[CorreuAgrupatFields.MESSAGE]}">
              <i class="fas fa-info-circle" title="${__theForm.help[CorreuAgrupatFields.MESSAGE]}" ></i>
              </c:if>
            </td>
          <td id="correuAgrupat_message_columnvalueid">
              <form:errors path="correuAgrupat.message" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,CorreuAgrupatFields.MESSAGE)? 'true' : 'false'}" path="correuAgrupat.message"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_message" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_message" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('correuAgrupat.message'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('correuAgrupat.message'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('correuAgrupat.message'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_message').on('click', function(){
					var valor = ($('#dropdownMenuContainer_message').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_message').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CorreuAgrupatFields.HTML)}">
        <tr id="correuAgrupat_html_rowid">
          <td id="correuAgrupat_html_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[CorreuAgrupatFields.HTML])?'correuAgrupat.html':__theForm.labels[CorreuAgrupatFields.HTML]}" />
             </label>
              <c:if test="${not empty __theForm.help[CorreuAgrupatFields.HTML]}">
              <i class="fas fa-info-circle" title="${__theForm.help[CorreuAgrupatFields.HTML]}" ></i>
              </c:if>
            </td>
          <td id="correuAgrupat_html_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,CorreuAgrupatFields.HTML)}" >
              <form:errors path="correuAgrupat.html" cssClass="errorField alert alert-danger" />
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,CorreuAgrupatFields.HTML)? 'false' : 'true'}" path="correuAgrupat.html" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,CorreuAgrupatFields.HTML)}" >
                <fmt:message key="genapp.checkbox.${__theForm.correuAgrupat.html}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CorreuAgrupatFields.USUARIENTITATID)}">
        <tr id="correuAgrupat_usuariEntitatID_rowid">
          <td id="correuAgrupat_usuariEntitatID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[CorreuAgrupatFields.USUARIENTITATID])?'correuAgrupat.usuariEntitatID':__theForm.labels[CorreuAgrupatFields.USUARIENTITATID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[CorreuAgrupatFields.USUARIENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[CorreuAgrupatFields.USUARIENTITATID]}" ></i>
              </c:if>
            </td>
          <td id="correuAgrupat_usuariEntitatID_columnvalueid">
          <form:errors path="correuAgrupat.usuariEntitatID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,CorreuAgrupatFields.USUARIENTITATID)}" >
          <form:hidden path="correuAgrupat.usuariEntitatID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.correuAgrupat.usuariEntitatID,__theForm.listOfUsuariEntitatForUsuariEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,CorreuAgrupatFields.USUARIENTITATID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="correuAgrupat_usuariEntitatID"  onchange="if(typeof onChangeUsuariEntitatID == 'function') {  onChangeUsuariEntitatID(this); };"  cssClass="form-control col-md-9-optional" path="correuAgrupat.usuariEntitatID">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CorreuAgrupatFields.DATACREACIO)}">
        <tr id="correuAgrupat_dataCreacio_rowid">
          <td id="correuAgrupat_dataCreacio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[CorreuAgrupatFields.DATACREACIO])?'correuAgrupat.dataCreacio':__theForm.labels[CorreuAgrupatFields.DATACREACIO]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[CorreuAgrupatFields.DATACREACIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[CorreuAgrupatFields.DATACREACIO]}" ></i>
              </c:if>
            </td>
          <td id="correuAgrupat_dataCreacio_columnvalueid">
    <form:errors path="correuAgrupat.dataCreacio" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="correuAgrupat_dataCreacio" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,CorreuAgrupatFields.DATACREACIO)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#correuAgrupat_dataCreacio" path="correuAgrupat.dataCreacio" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,CorreuAgrupatFields.DATACREACIO)}" >
                    <div class="input-group-append"  data-target="#correuAgrupat_dataCreacio"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#correuAgrupat_dataCreacio').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
        </script>           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,CorreuAgrupatFields.ERROR)}">
        <tr id="correuAgrupat_error_rowid">
          <td id="correuAgrupat_error_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[CorreuAgrupatFields.ERROR])?'correuAgrupat.error':__theForm.labels[CorreuAgrupatFields.ERROR]}" />
             </label>
              <c:if test="${not empty __theForm.help[CorreuAgrupatFields.ERROR]}">
              <i class="fas fa-info-circle" title="${__theForm.help[CorreuAgrupatFields.ERROR]}" ></i>
              </c:if>
            </td>
          <td id="correuAgrupat_error_columnvalueid">
              <form:errors path="correuAgrupat.error" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,CorreuAgrupatFields.ERROR)? 'true' : 'false'}" path="correuAgrupat.error"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_error" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_error" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('correuAgrupat.error'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('correuAgrupat.error'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('correuAgrupat.error'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_error').on('click', function(){
					var valor = ($('#dropdownMenuContainer_error').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_error').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
