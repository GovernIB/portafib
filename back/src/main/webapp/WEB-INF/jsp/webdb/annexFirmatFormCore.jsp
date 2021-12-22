<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="AnnexFirmatFields" className="es.caib.portafib.model.fields.AnnexFirmatFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,AnnexFirmatFields.FITXERID)}">
        <tr id="annexFirmat_fitxerID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AnnexFirmatFields.FITXERID])?'annexFirmat.fitxerID':__theForm.labels[AnnexFirmatFields.FITXERID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[AnnexFirmatFields.FITXERID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AnnexFirmatFields.FITXERID]}" ></i>
              </c:if>
            </td>
            <td>
              <form:errors path="annexFirmat.fitxerID" cssClass="errorField alert alert-danger" />
            <c:if test="${gen:contains(__theForm.readOnlyFields ,AnnexFirmatFields.FITXERID)}" >
              <a target="_blank" href="<c:url value="${pfi:fileUrl(fitxerID.fitxerID)}"/>">${fitxerID.fitxerID.nom}</a>
            </c:if>
            <c:if test="${!gen:contains(__theForm.readOnlyFields ,AnnexFirmatFields.FITXERID)}" >
              <div class="input-group col-md-9-optional" style="padding: 0px">
                <div class="custom-file">
                  <form:input  readonly="${ gen:contains(__theForm.readOnlyFields ,AnnexFirmatFields.FITXERID)? 'true' : 'false'}" cssClass="custom-file-input form-control  ${gen:contains(__theForm.readOnlyFields ,AnnexFirmatFields.FITXERID)? ' uneditable-input' : ''}"   path="fitxerID" type="file" />
                  <label class="custom-file-label" for="fitxerID">
                  </label>
                </div>
                <c:choose>
                <c:when test="${not empty __theForm.annexFirmat.fitxer}">
                <div class="input-group-append">
                  <span class="input-group-text" id="">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AnnexFirmatFields.ANNEXID)}">
        <tr id="annexFirmat_annexID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AnnexFirmatFields.ANNEXID])?'annexFirmat.annexID':__theForm.labels[AnnexFirmatFields.ANNEXID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[AnnexFirmatFields.ANNEXID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AnnexFirmatFields.ANNEXID]}" ></i>
              </c:if>
            </td>
            <td>
          <form:errors path="annexFirmat.annexID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,AnnexFirmatFields.ANNEXID)}" >
          <form:hidden path="annexFirmat.annexID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.annexFirmat.annexID,__theForm.listOfAnnexForAnnexID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,AnnexFirmatFields.ANNEXID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="annexFirmat_annexID"  onchange="if(typeof onChangeAnnexID == 'function') {  onChangeAnnexID(this); };"  cssClass="form-control col-md-9-optional" path="annexFirmat.annexID">
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
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AnnexFirmatFields.FIRMAID])?'annexFirmat.firmaID':__theForm.labels[AnnexFirmatFields.FIRMAID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[AnnexFirmatFields.FIRMAID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AnnexFirmatFields.FIRMAID]}" ></i>
              </c:if>
            </td>
            <td>
          <form:errors path="annexFirmat.firmaID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,AnnexFirmatFields.FIRMAID)}" >
          <form:hidden path="annexFirmat.firmaID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.annexFirmat.firmaID,__theForm.listOfFirmaForFirmaID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,AnnexFirmatFields.FIRMAID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="annexFirmat_firmaID"  onchange="if(typeof onChangeFirmaID == 'function') {  onChangeFirmaID(this); };"  cssClass="form-control col-md-9-optional" path="annexFirmat.firmaID">
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
        
