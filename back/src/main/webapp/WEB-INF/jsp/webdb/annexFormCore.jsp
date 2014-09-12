<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="AnnexFields" className="es.caib.portafib.model.fields.AnnexFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,AnnexFields.PETICIODEFIRMAID)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AnnexFields.PETICIODEFIRMAID])?'annex.peticioDeFirmaID':__theForm.labels[AnnexFields.PETICIODEFIRMAID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[AnnexFields.PETICIODEFIRMAID]}">
              <i class="icon-info-sign" title="${__theForm.help[AnnexFields.PETICIODEFIRMAID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="annex.peticioDeFirmaID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,AnnexFields.PETICIODEFIRMAID)}" >
          <form:hidden path="annex.peticioDeFirmaID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.annex.peticioDeFirmaID,__theForm.listOfPeticioDeFirmaForPeticioDeFirmaID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,AnnexFields.PETICIODEFIRMAID)}" >
          <form:select id="annex_peticioDeFirmaID"  onchange="if(typeof onChangePeticioDeFirmaID == 'function') {  onChangePeticioDeFirmaID(this); };"  cssClass="input-xxlarge" path="annex.peticioDeFirmaID">
            <c:forEach items="${__theForm.listOfPeticioDeFirmaForPeticioDeFirmaID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AnnexFields.FITXERID)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AnnexFields.FITXERID])?'annex.fitxerID':__theForm.labels[AnnexFields.FITXERID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[AnnexFields.FITXERID]}">
              <i class="icon-info-sign" title="${__theForm.help[AnnexFields.FITXERID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="annex.fitxerID" cssClass="errorField alert alert-error" />
              <div class="fileupload fileupload-new" data-provides="fileupload" style="margin-bottom: 0px">
                <div class="input-append">
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,AnnexFields.FITXERID)}" >
                    <div class="uneditable-input span3">
                      <i class="icon-file fileupload-exists"></i>
                      <span class="fileupload-preview"></span>
                    </div>
                    <span class="btn btn-file">
                      <span class="fileupload-new"><fmt:message key="genapp.form.file.select"/></span>
                      <span class="fileupload-exists"><fmt:message key="genapp.form.file.change"/></span>
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,AnnexFields.FITXERID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,AnnexFields.FITXERID)? 'input uneditable-input' : 'input'}"  path="fitxerID" type="file" />
                    </span>
                    <a href="#" class="btn fileupload-exists" data-dismiss="fileupload"><fmt:message key="genapp.form.file.unselect"/></a>
                    <span class="add-on">&nbsp;</span>
                </c:if>
                <c:if test="${not empty __theForm.annex.fitxer}">
                    <span class="add-on">
                        <a target="_blank" href="<c:url value="${pfi:fileUrl(__theForm.annex.fitxer)}"/>">${__theForm.annex.fitxer.nom}</a>
                    </span>
                </c:if>
                </div>
              </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AnnexFields.ADJUNTAR)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AnnexFields.ADJUNTAR])?'annex.adjuntar':__theForm.labels[AnnexFields.ADJUNTAR]}" />
              <c:if test="${not empty __theForm.help[AnnexFields.ADJUNTAR]}">
              <i class="icon-info-sign" title="${__theForm.help[AnnexFields.ADJUNTAR]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,AnnexFields.ADJUNTAR)}" >
              <form:errors path="annex.adjuntar" cssClass="errorField alert alert-error" />
              <form:checkbox onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,AnnexFields.ADJUNTAR)? 'false' : 'true'}" path="annex.adjuntar" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,AnnexFields.ADJUNTAR)}" >
                <fmt:message key="genapp.checkbox.${__theForm.annex.adjuntar}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AnnexFields.FIRMAR)}">
        <tr>
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AnnexFields.FIRMAR])?'annex.firmar':__theForm.labels[AnnexFields.FIRMAR]}" />
              <c:if test="${not empty __theForm.help[AnnexFields.FIRMAR]}">
              <i class="icon-info-sign" title="${__theForm.help[AnnexFields.FIRMAR]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,AnnexFields.FIRMAR)}" >
              <form:errors path="annex.firmar" cssClass="errorField alert alert-error" />
              <form:checkbox onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,AnnexFields.FIRMAR)? 'false' : 'true'}" path="annex.firmar" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,AnnexFields.FIRMAR)}" >
                <fmt:message key="genapp.checkbox.${__theForm.annex.firmar}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
