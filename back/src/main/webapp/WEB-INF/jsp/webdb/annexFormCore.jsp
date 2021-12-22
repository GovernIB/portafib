<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="AnnexFields" className="es.caib.portafib.model.fields.AnnexFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,AnnexFields.PETICIODEFIRMAID)}">
        <tr id="annex_peticioDeFirmaID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AnnexFields.PETICIODEFIRMAID])?'annex.peticioDeFirmaID':__theForm.labels[AnnexFields.PETICIODEFIRMAID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[AnnexFields.PETICIODEFIRMAID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AnnexFields.PETICIODEFIRMAID]}" ></i>
              </c:if>
            </td>
            <td>
          <form:errors path="annex.peticioDeFirmaID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,AnnexFields.PETICIODEFIRMAID)}" >
          <form:hidden path="annex.peticioDeFirmaID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.annex.peticioDeFirmaID,__theForm.listOfPeticioDeFirmaForPeticioDeFirmaID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,AnnexFields.PETICIODEFIRMAID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="annex_peticioDeFirmaID"  onchange="if(typeof onChangePeticioDeFirmaID == 'function') {  onChangePeticioDeFirmaID(this); };"  cssClass="form-control col-md-9-optional" path="annex.peticioDeFirmaID">
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
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AnnexFields.FITXERID])?'annex.fitxerID':__theForm.labels[AnnexFields.FITXERID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[AnnexFields.FITXERID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AnnexFields.FITXERID]}" ></i>
              </c:if>
            </td>
            <td>
              <form:errors path="annex.fitxerID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,AnnexFields.FITXERID)}" >
              <a target="_blank" href="<c:url value="${pfi:fileUrl(fitxerID.fitxerID)}"/>">${fitxerID.fitxerID.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,AnnexFields.FITXERID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,AnnexFields.FITXERID)? 'true' : 'false'}" cssClass="custom-file-input form-control  ${gen:contains(__theForm.readOnlyFields ,AnnexFields.FITXERID)? ' uneditable-input' : ''}"   path="fitxerID" type="file" />
                  <label class="custom-file-label" for="fitxerID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.annex.fitxer}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
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
                <script type="text/javascript">
					$('#fitxerID').on('change', function(){
						var ruta = $('#fitxerID').val(); 
						var rutaArray = ruta.split('\\');
						$('#fitxerID-custom-file-label').css('display','block');
						$('#fitxerID-custom-file-label small').html(rutaArray[rutaArray.length - 1]);
					});
				</script>                </c:otherwise>
                </c:choose>
              </div>
            </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AnnexFields.ADJUNTAR)}">
        <tr id="annex_adjuntar_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AnnexFields.ADJUNTAR])?'annex.adjuntar':__theForm.labels[AnnexFields.ADJUNTAR]}" />
             </label>
              <c:if test="${not empty __theForm.help[AnnexFields.ADJUNTAR]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AnnexFields.ADJUNTAR]}" ></i>
              </c:if>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,AnnexFields.ADJUNTAR)}" >
              <form:errors path="annex.adjuntar" cssClass="errorField alert alert-danger" />
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,AnnexFields.ADJUNTAR)? 'false' : 'true'}" path="annex.adjuntar" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,AnnexFields.ADJUNTAR)}" >
                <fmt:message key="genapp.checkbox.${__theForm.annex.adjuntar}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AnnexFields.FIRMAR)}">
        <tr id="annex_firmar_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AnnexFields.FIRMAR])?'annex.firmar':__theForm.labels[AnnexFields.FIRMAR]}" />
             </label>
              <c:if test="${not empty __theForm.help[AnnexFields.FIRMAR]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AnnexFields.FIRMAR]}" ></i>
              </c:if>
            </td>
            <td>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,AnnexFields.FIRMAR)}" >
              <form:errors path="annex.firmar" cssClass="errorField alert alert-danger" />
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,AnnexFields.FIRMAR)? 'false' : 'true'}" path="annex.firmar" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,AnnexFields.FIRMAR)}" >
                <fmt:message key="genapp.checkbox.${__theForm.annex.firmar}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
