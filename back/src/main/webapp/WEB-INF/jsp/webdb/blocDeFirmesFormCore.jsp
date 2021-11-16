<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="BlocDeFirmesFields" className="es.caib.portafib.model.fields.BlocDeFirmesFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,BlocDeFirmesFields.ORDRE)}">
        <tr id="blocDeFirmes_ordre_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[BlocDeFirmesFields.ORDRE])?'blocDeFirmes.ordre':__theForm.labels[BlocDeFirmesFields.ORDRE]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[BlocDeFirmesFields.ORDRE]}">
              <i class="fas fa-info-circle" title="${__theForm.help[BlocDeFirmesFields.ORDRE]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="blocDeFirmes.ordre" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,BlocDeFirmesFields.ORDRE)? 'true' : 'false'}" cssClass="form-control col-md-9-optional ${gen:contains(__theForm.readOnlyFields ,BlocDeFirmesFields.ORDRE)? ' uneditable-input' : ''}"  style=""  path="blocDeFirmes.ordre"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,BlocDeFirmesFields.DATAFINALITZACIO)}">
        <tr id="blocDeFirmes_dataFinalitzacio_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[BlocDeFirmesFields.DATAFINALITZACIO])?'blocDeFirmes.dataFinalitzacio':__theForm.labels[BlocDeFirmesFields.DATAFINALITZACIO]}" />
              <c:if test="${not empty __theForm.help[BlocDeFirmesFields.DATAFINALITZACIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[BlocDeFirmesFields.DATAFINALITZACIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="blocDeFirmes.dataFinalitzacio" cssClass="errorField alert alert-danger" />
    <div class="container">
      <div class="row">
            <div class="form-group">
                <div class="input-group date" id="blocDeFirmes_dataFinalitzacio" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,BlocDeFirmesFields.DATAFINALITZACIO)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#blocDeFirmes_dataFinalitzacio" path="blocDeFirmes.dataFinalitzacio" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,BlocDeFirmesFields.DATAFINALITZACIO)}" >
                    <div class="input-group-append"  data-target="#blocDeFirmes_dataFinalitzacio"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
          <script type="text/javascript">
            $(function () {
                $('#blocDeFirmes_dataFinalitzacio').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,BlocDeFirmesFields.FLUXDEFIRMESID)}">
        <tr id="blocDeFirmes_fluxDeFirmesID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[BlocDeFirmesFields.FLUXDEFIRMESID])?'blocDeFirmes.fluxDeFirmesID':__theForm.labels[BlocDeFirmesFields.FLUXDEFIRMESID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[BlocDeFirmesFields.FLUXDEFIRMESID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[BlocDeFirmesFields.FLUXDEFIRMESID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="blocDeFirmes.fluxDeFirmesID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,BlocDeFirmesFields.FLUXDEFIRMESID)}" >
          <form:hidden path="blocDeFirmes.fluxDeFirmesID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.blocDeFirmes.fluxDeFirmesID,__theForm.listOfFluxDeFirmesForFluxDeFirmesID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,BlocDeFirmesFields.FLUXDEFIRMESID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="blocDeFirmes_fluxDeFirmesID"  onchange="if(typeof onChangeFluxDeFirmesID == 'function') {  onChangeFluxDeFirmesID(this); };"  cssClass="form-control col-md-9-optional" path="blocDeFirmes.fluxDeFirmesID">
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
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[BlocDeFirmesFields.MINIMDEFIRMES])?'blocDeFirmes.minimDeFirmes':__theForm.labels[BlocDeFirmesFields.MINIMDEFIRMES]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[BlocDeFirmesFields.MINIMDEFIRMES]}">
              <i class="fas fa-info-circle" title="${__theForm.help[BlocDeFirmesFields.MINIMDEFIRMES]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="blocDeFirmes.minimDeFirmes" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,BlocDeFirmesFields.MINIMDEFIRMES)? 'true' : 'false'}" cssClass="form-control col-md-9-optional ${gen:contains(__theForm.readOnlyFields ,BlocDeFirmesFields.MINIMDEFIRMES)? ' uneditable-input' : ''}"  style=""  path="blocDeFirmes.minimDeFirmes"   />

           </td>
        </tr>
        </c:if>
        
