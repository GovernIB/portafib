<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="AnnexFields" className="es.caib.portafib.model.fields.AnnexFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,AnnexFields.PETICIODEFIRMAID)}">
        <tr id="annex_peticioDeFirmaID_rowid">
          <td id="annex_peticioDeFirmaID_columnlabelid">
            <label for="annex.peticioDeFirmaID">
              <fmt:message key="${(empty __theForm.labels[AnnexFields.PETICIODEFIRMAID])?'annex.peticioDeFirmaID':__theForm.labels[AnnexFields.PETICIODEFIRMAID]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[AnnexFields.PETICIODEFIRMAID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AnnexFields.PETICIODEFIRMAID]}" ></i>
              </c:if>
            </td>
          <td id="annex_peticioDeFirmaID_columnvalueid">
          <form:errors path="annex.peticioDeFirmaID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,AnnexFields.PETICIODEFIRMAID)}" >
          <form:hidden path="annex.peticioDeFirmaID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.annex.peticioDeFirmaID,__theForm.listOfPeticioDeFirmaForPeticioDeFirmaID)}" aria-label="annex.peticioDeFirmaID"   required aria-required="true"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,AnnexFields.PETICIODEFIRMAID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="annex.peticioDeFirmaID" cssClass="form-control col-md-9-optional"  required="required"  aria-required="true"  onchange="if(typeof onChangePeticioDeFirmaID == 'function') {  onChangePeticioDeFirmaID(this); };">


            <c:forEach items="${__theForm.listOfPeticioDeFirmaForPeticioDeFirmaID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AnnexFields.FITXERID)}">
        <tr id="annex_fitxerID_rowid">
          <td id="annex_fitxerID_columnlabelid">
            <label for="annex.fitxerID">
              <fmt:message key="${(empty __theForm.labels[AnnexFields.FITXERID])?'annex.fitxerID':__theForm.labels[AnnexFields.FITXERID]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[AnnexFields.FITXERID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AnnexFields.FITXERID]}" ></i>
              </c:if>
            </td>
          <td id="annex_fitxerID_columnvalueid">
              <form:errors path="annex.fitxerID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,AnnexFields.FITXERID)}" >
              <a target="_blank" href="<c:url value="${pfi:fileUrl(__theForm.annex.fitxer)}"/>">${__theForm.annex.fitxer.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,AnnexFields.FITXERID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <input id="annex.fitxerID" name="fitxerID" class="custom-file-input form-control  ${gen:contains(__theForm.readOnlyFields ,AnnexFields.FITXERID)? ' uneditable-input' : ''}"  type="file"  required aria-required="true"  />
                  <span id="fitxerID-custom-file-label2" class="custom-file-label" >
                    <c:if test="${ empty __theForm.annex.fitxer}">
                    <fmt:message key="genapp.form.file.select"/>
                    </c:if>
                  </span>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.annex.fitxer}">
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="fitxerID-custom-file-label">
                  <small>              <a target="_blank" href="<c:url value="${pfi:fileUrl(__theForm.annex.fitxer)}"/>">${__theForm.annex.fitxer.nom}</a>
</small>
                  </span>
                </div>
                </c:when>
                <c:otherwise>
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="fitxerID-custom-file-label" style="display:none">
                  <small></small>
                  </span>
                </div>
                </c:otherwise>
                </c:choose>
                <script>
					$('#annex\\.fitxerID').on('change', function(){
						var ruta = $('#annex\\.fitxerID').val(); 
						var rutaArray = ruta.split('\\');
						$('#fitxerID-custom-file-label').css('display','block');
						$('#fitxerID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
                       $('#fitxerID-custom-file-label2').html(rutaArray[rutaArray.length - 1]);
                   });
				</script>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AnnexFields.ADJUNTAR)}">
        <tr id="annex_adjuntar_rowid">
          <td id="annex_adjuntar_columnlabelid">
            <label for="annex.adjuntar">
              <fmt:message key="${(empty __theForm.labels[AnnexFields.ADJUNTAR])?'annex.adjuntar':__theForm.labels[AnnexFields.ADJUNTAR]}" />
             </label>
              <c:if test="${not empty __theForm.help[AnnexFields.ADJUNTAR]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AnnexFields.ADJUNTAR]}" ></i>
              </c:if>
            </td>
          <td id="annex_adjuntar_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,AnnexFields.ADJUNTAR)}" >
              <form:errors  path="annex.adjuntar" cssClass="errorField alert alert-danger" />
<fieldset>
<legend class="sr-only">Evitar errors d'Accessibilitat</legend>
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,AnnexFields.ADJUNTAR)? 'false' : 'true'}" id="annex.adjuntar" path="annex.adjuntar"   />
</fieldset>
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,AnnexFields.ADJUNTAR)}" >
                <fmt:message key="genapp.checkbox.${__theForm.annex.adjuntar}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AnnexFields.FIRMAR)}">
        <tr id="annex_firmar_rowid">
          <td id="annex_firmar_columnlabelid">
            <label for="annex.firmar">
              <fmt:message key="${(empty __theForm.labels[AnnexFields.FIRMAR])?'annex.firmar':__theForm.labels[AnnexFields.FIRMAR]}" />
             </label>
              <c:if test="${not empty __theForm.help[AnnexFields.FIRMAR]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AnnexFields.FIRMAR]}" ></i>
              </c:if>
            </td>
          <td id="annex_firmar_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,AnnexFields.FIRMAR)}" >
              <form:errors  path="annex.firmar" cssClass="errorField alert alert-danger" />
<fieldset>
<legend class="sr-only">Evitar errors d'Accessibilitat</legend>
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,AnnexFields.FIRMAR)? 'false' : 'true'}" id="annex.firmar" path="annex.firmar"   />
</fieldset>
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,AnnexFields.FIRMAR)}" >
                <fmt:message key="genapp.checkbox.${__theForm.annex.firmar}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
