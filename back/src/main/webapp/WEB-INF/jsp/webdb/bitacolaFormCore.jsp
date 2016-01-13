<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="BitacolaFields" className="es.caib.portafib.model.fields.BitacolaFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,BitacolaFields.DATA)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[BitacolaFields.DATA])?'bitacola.data':__theForm.labels[BitacolaFields.DATA]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[BitacolaFields.DATA]}">
              <i class="icon-info-sign" title="${__theForm.help[BitacolaFields.DATA]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="bitacola.data" cssClass="errorField alert alert-error" />
              <div id="data" class="input-append">
                <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,BitacolaFields.DATA)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,BitacolaFields.DATA)? 'input-medium uneditable-input' : 'input-medium'}"  path="bitacola.data" />
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,BitacolaFields.DATA)}" >
                <span class="add-on">
                  <i data-time-icon="icon-time" data-date-icon="icon-calendar">
                  </i>
                </span>
              </c:if>
              </div>
              <script type="text/javascript">                
                $(function() {
                  $('#data').datetimepicker({
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,BitacolaFields.DESCRIPCIO)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[BitacolaFields.DESCRIPCIO])?'bitacola.descripcio':__theForm.labels[BitacolaFields.DESCRIPCIO]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[BitacolaFields.DESCRIPCIO]}">
              <i class="icon-info-sign" title="${__theForm.help[BitacolaFields.DESCRIPCIO]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="bitacola.descripcio" cssClass="errorField alert alert-error" />
              <form:textarea rows="3" wrap="soft" style="overflow:auto;" cssClass="input-xxlarge" readonly="${ gen:contains(__theForm.readOnlyFields ,BitacolaFields.DESCRIPCIO)? 'true' : 'false'}" path="bitacola.descripcio"  />
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,BitacolaFields.PETICIODEFIRMAID)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[BitacolaFields.PETICIODEFIRMAID])?'bitacola.peticioDeFirmaID':__theForm.labels[BitacolaFields.PETICIODEFIRMAID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[BitacolaFields.PETICIODEFIRMAID]}">
              <i class="icon-info-sign" title="${__theForm.help[BitacolaFields.PETICIODEFIRMAID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="bitacola.peticioDeFirmaID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,BitacolaFields.PETICIODEFIRMAID)}" >
          <form:hidden path="bitacola.peticioDeFirmaID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.bitacola.peticioDeFirmaID,__theForm.listOfPeticioDeFirmaForPeticioDeFirmaID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,BitacolaFields.PETICIODEFIRMAID)}" >
          <form:select id="bitacola_peticioDeFirmaID"  onchange="if(typeof onChangePeticioDeFirmaID == 'function') {  onChangePeticioDeFirmaID(this); };"  cssClass="input-xxlarge" path="bitacola.peticioDeFirmaID">
            <c:forEach items="${__theForm.listOfPeticioDeFirmaForPeticioDeFirmaID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,BitacolaFields.USUARIENTITATID)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[BitacolaFields.USUARIENTITATID])?'bitacola.usuariEntitatID':__theForm.labels[BitacolaFields.USUARIENTITATID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[BitacolaFields.USUARIENTITATID]}">
              <i class="icon-info-sign" title="${__theForm.help[BitacolaFields.USUARIENTITATID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="bitacola.usuariEntitatID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,BitacolaFields.USUARIENTITATID)}" >
          <form:hidden path="bitacola.usuariEntitatID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.bitacola.usuariEntitatID,__theForm.listOfUsuariEntitatForUsuariEntitatID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,BitacolaFields.USUARIENTITATID)}" >
          <form:select id="bitacola_usuariEntitatID"  onchange="if(typeof onChangeUsuariEntitatID == 'function') {  onChangeUsuariEntitatID(this); };"  cssClass="input-xxlarge" path="bitacola.usuariEntitatID">
            <c:forEach items="${__theForm.listOfUsuariEntitatForUsuariEntitatID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
