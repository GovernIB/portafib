<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="AnnexFirmatFields" className="es.caib.portafib.model.fields.AnnexFirmatFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,AnnexFirmatFields.FITXERID)}">
        <tr id="annexFirmat_fitxerID_rowid">
          <td id="annexFirmat_fitxerID_columnlabelid">
            <label for="annexFirmat.fitxerID">
              <fmt:message key="${(empty __theForm.labels[AnnexFirmatFields.FITXERID])?'annexFirmat.fitxerID':__theForm.labels[AnnexFirmatFields.FITXERID]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[AnnexFirmatFields.FITXERID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AnnexFirmatFields.FITXERID]}" ></i>
              </c:if>
            </td>
          <td id="annexFirmat_fitxerID_columnvalueid">
              <form:errors path="annexFirmat.fitxerID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,AnnexFirmatFields.FITXERID)}" >
              <a target="_blank" href="<c:url value="${pfi:fileUrl(__theForm.annexFirmat.fitxer)}"/>">${__theForm.annexFirmat.fitxer.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,AnnexFirmatFields.FITXERID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <input id="annexFirmat.fitxerID" name="fitxerID" class="custom-file-input form-control  ${gen:contains(__theForm.readOnlyFields ,AnnexFirmatFields.FITXERID)? ' uneditable-input' : ''}"  type="file"  required aria-required="true"  />
                  <span id="fitxerID-custom-file-label2" class="custom-file-label" >
                    <c:if test="${ empty __theForm.annexFirmat.fitxer}">
                    <fmt:message key="genapp.form.file.select"/>
                    </c:if>
                  </span>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.annexFirmat.fitxer}">
                <div class="input-group-append input-group-append-file">
                  <span class="input-group-text" id="fitxerID-custom-file-label">
                  <small>              <a target="_blank" href="<c:url value="${pfi:fileUrl(__theForm.annexFirmat.fitxer)}"/>">${__theForm.annexFirmat.fitxer.nom}</a>
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
					$('#annexFirmat\\.fitxerID').on('change', function(){
						var ruta = $('#annexFirmat\\.fitxerID').val(); 
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AnnexFirmatFields.ANNEXID)}">
        <tr id="annexFirmat_annexID_rowid">
          <td id="annexFirmat_annexID_columnlabelid">
            <label for="annexFirmat.annexID">
              <fmt:message key="${(empty __theForm.labels[AnnexFirmatFields.ANNEXID])?'annexFirmat.annexID':__theForm.labels[AnnexFirmatFields.ANNEXID]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[AnnexFirmatFields.ANNEXID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AnnexFirmatFields.ANNEXID]}" ></i>
              </c:if>
            </td>
          <td id="annexFirmat_annexID_columnvalueid">
          <form:errors path="annexFirmat.annexID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,AnnexFirmatFields.ANNEXID)}" >
          <form:hidden path="annexFirmat.annexID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.annexFirmat.annexID,__theForm.listOfAnnexForAnnexID)}" aria-label="annexFirmat.annexID"   required aria-required="true"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,AnnexFirmatFields.ANNEXID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="annexFirmat.annexID" cssClass="form-control col-md-9-optional"  required="required"  aria-required="true"  onchange="if(typeof onChangeAnnexID == 'function') {  onChangeAnnexID(this); };">


            <c:forEach items="${__theForm.listOfAnnexForAnnexID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AnnexFirmatFields.FIRMAID)}">
        <tr id="annexFirmat_firmaID_rowid">
          <td id="annexFirmat_firmaID_columnlabelid">
            <label for="annexFirmat.firmaID">
              <fmt:message key="${(empty __theForm.labels[AnnexFirmatFields.FIRMAID])?'annexFirmat.firmaID':__theForm.labels[AnnexFirmatFields.FIRMAID]}" />&nbsp;<span class="text-danger" aria-hidden="true">(*)</span><span class="sr-only">(obligatori, obligatorio)</span>
             </label>
              <c:if test="${not empty __theForm.help[AnnexFirmatFields.FIRMAID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AnnexFirmatFields.FIRMAID]}" ></i>
              </c:if>
            </td>
          <td id="annexFirmat_firmaID_columnvalueid">
          <form:errors path="annexFirmat.firmaID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,AnnexFirmatFields.FIRMAID)}" >
          <form:hidden path="annexFirmat.firmaID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.annexFirmat.firmaID,__theForm.listOfFirmaForFirmaID)}" aria-label="annexFirmat.firmaID"   required aria-required="true"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,AnnexFirmatFields.FIRMAID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select path="annexFirmat.firmaID" cssClass="form-control col-md-9-optional"  required="required"  aria-required="true"  onchange="if(typeof onChangeFirmaID == 'function') {  onChangeFirmaID(this); };">


            <c:forEach items="${__theForm.listOfFirmaForFirmaID}" var="tmp">
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
        
