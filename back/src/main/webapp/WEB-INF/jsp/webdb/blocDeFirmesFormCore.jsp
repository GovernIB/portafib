<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="BlocDeFirmesFields" className="es.caib.portafib.model.fields.BlocDeFirmesFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,BlocDeFirmesFields.ORDRE)}">
        <tr id="blocDeFirmes_ordre_rowid">
          <td id="blocDeFirmes_ordre_columnlabelid">
            <label for="blocDeFirmes.ordre">
              <fmt:message key="${(empty __theForm.labels[BlocDeFirmesFields.ORDRE])?'blocDeFirmes.ordre':__theForm.labels[BlocDeFirmesFields.ORDRE]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[BlocDeFirmesFields.ORDRE]}">
              <i class="fas fa-info-circle" title="${__theForm.help[BlocDeFirmesFields.ORDRE]}" ></i>
              </c:if>
            </td>
          <td id="blocDeFirmes_ordre_columnvalueid">
            <form:errors path="blocDeFirmes.ordre" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,BlocDeFirmesFields.ORDRE)? 'readonly' : ''}" cssClass="w-25 form-control  ${gen:contains(__theForm.readOnlyFields ,BlocDeFirmesFields.ORDRE)? ' uneditable-input' : ''}"  style=""  path="blocDeFirmes.ordre"  required="required"  aria-required="true"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,BlocDeFirmesFields.DATAFINALITZACIO)}">
        <tr id="blocDeFirmes_dataFinalitzacio_rowid">
          <td id="blocDeFirmes_dataFinalitzacio_columnlabelid">
            <label for="blocDeFirmes.dataFinalitzacio">
              <fmt:message key="${(empty __theForm.labels[BlocDeFirmesFields.DATAFINALITZACIO])?'blocDeFirmes.dataFinalitzacio':__theForm.labels[BlocDeFirmesFields.DATAFINALITZACIO]}" />
             </label>
              <c:if test="${not empty __theForm.help[BlocDeFirmesFields.DATAFINALITZACIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[BlocDeFirmesFields.DATAFINALITZACIO]}" ></i>
              </c:if>
            </td>
          <td id="blocDeFirmes_dataFinalitzacio_columnvalueid">
    <form:errors path="blocDeFirmes.dataFinalitzacio" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="blocDeFirmes_dataFinalitzacioDiv" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,BlocDeFirmesFields.DATAFINALITZACIO)? 'readonly' : ''}" cssClass="form-control datetimepicker-input"  data-target="#blocDeFirmes_dataFinalitzacioDiv" path="blocDeFirmes.dataFinalitzacio"   />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,BlocDeFirmesFields.DATAFINALITZACIO)}" >
                    <div class="input-group-append"  data-target="#blocDeFirmes_dataFinalitzacioDiv"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script>
            $(function () {
                $('#blocDeFirmes_dataFinalitzacioDiv').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,BlocDeFirmesFields.FLUXDEFIRMESID)}">
        <tr id="blocDeFirmes_fluxDeFirmesID_rowid">
          <td id="blocDeFirmes_fluxDeFirmesID_columnlabelid">
            <label for="blocDeFirmes.fluxDeFirmesID">
              <fmt:message key="${(empty __theForm.labels[BlocDeFirmesFields.FLUXDEFIRMESID])?'blocDeFirmes.fluxDeFirmesID':__theForm.labels[BlocDeFirmesFields.FLUXDEFIRMESID]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[BlocDeFirmesFields.FLUXDEFIRMESID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[BlocDeFirmesFields.FLUXDEFIRMESID]}" ></i>
              </c:if>
            </td>
          <td id="blocDeFirmes_fluxDeFirmesID_columnvalueid">
          <form:errors path="blocDeFirmes.fluxDeFirmesID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,BlocDeFirmesFields.FLUXDEFIRMESID)}" >
          <form:hidden path="blocDeFirmes.fluxDeFirmesID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.blocDeFirmes.fluxDeFirmesID,__theForm.listOfFluxDeFirmesForFluxDeFirmesID)}" aria-label="blocDeFirmes.fluxDeFirmesID"   required aria-required="true"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,BlocDeFirmesFields.FLUXDEFIRMESID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="blocDeFirmes.fluxDeFirmesID" cssClass="form-control col-md-9-optional"  required="required"  aria-required="true"  onchange="if(typeof onChangeFluxDeFirmesID == 'function') {  onChangeFluxDeFirmesID(this); };">


            <c:forEach items="${__theForm.listOfFluxDeFirmesForFluxDeFirmesID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,BlocDeFirmesFields.MINIMDEFIRMES)}">
        <tr id="blocDeFirmes_minimDeFirmes_rowid">
          <td id="blocDeFirmes_minimDeFirmes_columnlabelid">
            <label for="blocDeFirmes.minimDeFirmes">
              <fmt:message key="${(empty __theForm.labels[BlocDeFirmesFields.MINIMDEFIRMES])?'blocDeFirmes.minimDeFirmes':__theForm.labels[BlocDeFirmesFields.MINIMDEFIRMES]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[BlocDeFirmesFields.MINIMDEFIRMES]}">
              <i class="fas fa-info-circle" title="${__theForm.help[BlocDeFirmesFields.MINIMDEFIRMES]}" ></i>
              </c:if>
            </td>
          <td id="blocDeFirmes_minimDeFirmes_columnvalueid">
            <form:errors path="blocDeFirmes.minimDeFirmes" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,BlocDeFirmesFields.MINIMDEFIRMES)? 'readonly' : ''}" cssClass="w-25 form-control  ${gen:contains(__theForm.readOnlyFields ,BlocDeFirmesFields.MINIMDEFIRMES)? ' uneditable-input' : ''}"  style=""  path="blocDeFirmes.minimDeFirmes"  required="required"  aria-required="true"   />

           </td>
        </tr>
        </c:if>
        
