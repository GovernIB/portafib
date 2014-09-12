<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="BlocDeFirmesFields" className="es.caib.portafib.model.fields.BlocDeFirmesFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,BlocDeFirmesFields.ORDRE)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[BlocDeFirmesFields.ORDRE])?'blocDeFirmes.ordre':__theForm.labels[BlocDeFirmesFields.ORDRE]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[BlocDeFirmesFields.ORDRE]}">
              <i class="icon-info-sign" title="${__theForm.help[BlocDeFirmesFields.ORDRE]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="blocDeFirmes.ordre" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,BlocDeFirmesFields.ORDRE)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,BlocDeFirmesFields.ORDRE)? 'input-mini uneditable-input' : 'input-mini'}"   path="blocDeFirmes.ordre" />
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,BlocDeFirmesFields.DATAFINALITZACIO)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[BlocDeFirmesFields.DATAFINALITZACIO])?'blocDeFirmes.dataFinalitzacio':__theForm.labels[BlocDeFirmesFields.DATAFINALITZACIO]}" />
              <c:if test="${not empty __theForm.help[BlocDeFirmesFields.DATAFINALITZACIO]}">
              <i class="icon-info-sign" title="${__theForm.help[BlocDeFirmesFields.DATAFINALITZACIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="blocDeFirmes.dataFinalitzacio" cssClass="errorField alert alert-error" />
              <div id="dataFinalitzacio" class="input-append">
                <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,BlocDeFirmesFields.DATAFINALITZACIO)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,BlocDeFirmesFields.DATAFINALITZACIO)? 'input-medium uneditable-input' : 'input-medium'}"  path="blocDeFirmes.dataFinalitzacio" />
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,BlocDeFirmesFields.DATAFINALITZACIO)}" >
                <span class="add-on">
                  <i data-time-icon="icon-time" data-date-icon="icon-calendar">
                  </i>
                </span>
              </c:if>
              </div>
              <script type="text/javascript">                
                $(function() {
                  $('#dataFinalitzacio').datetimepicker({
                    language: '${lang}',
                    pick12HourFormat: <c:out value="${fn:contains(gen:getDateTimePattern(), 'a')?'true' : 'false'}"/>,
                    format:  '${gen:getJSDateTimePattern()}',
                    pickTime: true,
                    weekStart: ${gen:getFirstDayOfTheWeek()}
                  });
                });
              </script>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,BlocDeFirmesFields.FLUXDEFIRMESID)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[BlocDeFirmesFields.FLUXDEFIRMESID])?'blocDeFirmes.fluxDeFirmesID':__theForm.labels[BlocDeFirmesFields.FLUXDEFIRMESID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[BlocDeFirmesFields.FLUXDEFIRMESID]}">
              <i class="icon-info-sign" title="${__theForm.help[BlocDeFirmesFields.FLUXDEFIRMESID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="blocDeFirmes.fluxDeFirmesID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,BlocDeFirmesFields.FLUXDEFIRMESID)}" >
          <form:hidden path="blocDeFirmes.fluxDeFirmesID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.blocDeFirmes.fluxDeFirmesID,__theForm.listOfFluxDeFirmesForFluxDeFirmesID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,BlocDeFirmesFields.FLUXDEFIRMESID)}" >
          <form:select id="blocDeFirmes_fluxDeFirmesID"  onchange="if(typeof onChangeFluxDeFirmesID == 'function') {  onChangeFluxDeFirmesID(this); };"  cssClass="input-xxlarge" path="blocDeFirmes.fluxDeFirmesID">
            <c:forEach items="${__theForm.listOfFluxDeFirmesForFluxDeFirmesID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,BlocDeFirmesFields.MINIMDEFIRMES)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[BlocDeFirmesFields.MINIMDEFIRMES])?'blocDeFirmes.minimDeFirmes':__theForm.labels[BlocDeFirmesFields.MINIMDEFIRMES]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[BlocDeFirmesFields.MINIMDEFIRMES]}">
              <i class="icon-info-sign" title="${__theForm.help[BlocDeFirmesFields.MINIMDEFIRMES]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
            <form:errors path="blocDeFirmes.minimDeFirmes" cssClass="errorField alert alert-error" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,BlocDeFirmesFields.MINIMDEFIRMES)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,BlocDeFirmesFields.MINIMDEFIRMES)? 'input-mini uneditable-input' : 'input-mini'}"   path="blocDeFirmes.minimDeFirmes" />
           </td>
        </tr>
        </c:if>
        
