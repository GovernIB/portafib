<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="AnnexFirmatFields" className="es.caib.portafib.model.fields.AnnexFirmatFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,AnnexFirmatFields.FITXERID)}">
        <tr id="annexFirmat_fitxerID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AnnexFirmatFields.FITXERID])?'annexFirmat.fitxerID':__theForm.labels[AnnexFirmatFields.FITXERID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[AnnexFirmatFields.FITXERID]}">
              <i class="icon-info-sign" title="${__theForm.help[AnnexFirmatFields.FITXERID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
              <form:errors path="annexFirmat.fitxerID" cssClass="errorField alert alert-error" />
              <div class="fileupload fileupload-new" data-provides="fileupload" style="margin-bottom: 0px">
                <div class="input-append">
                <c:if test="${!gen:contains(__theForm.readOnlyFields ,AnnexFirmatFields.FITXERID)}" >
                    <div class="uneditable-input span3">
                      <i class="icon-file fileupload-exists"></i>
                      <span class="fileupload-preview"></span>
                    </div>
                    <span class="btn btn-file">
                      <span class="fileupload-new"><fmt:message key="genapp.form.file.select"/></span>
                      <span class="fileupload-exists"><fmt:message key="genapp.form.file.change"/></span>
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,AnnexFirmatFields.FITXERID)? 'true' : 'false'}" cssClass="${gen:contains(__theForm.readOnlyFields ,AnnexFirmatFields.FITXERID)? 'input uneditable-input' : 'input'}"  path="fitxerID" type="file" />
                    </span>
                    <a href="#" class="btn fileupload-exists" data-dismiss="fileupload"><fmt:message key="genapp.form.file.unselect"/></a>
                    <span class="add-on">&nbsp;</span>
                </c:if>
                <c:if test="${not empty __theForm.annexFirmat.fitxer}">
                    <span class="add-on">
                        <a target="_blank" href="<c:url value="${pfi:fileUrl(__theForm.annexFirmat.fitxer)}"/>">${__theForm.annexFirmat.fitxer.nom}</a>
                    </span>
                </c:if>
                </div>
              </div>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AnnexFirmatFields.ANNEXID)}">
        <tr id="annexFirmat_annexID_rowid">
          <td>
            <label>
              <fmt:message key="${(empty __theForm.labels[AnnexFirmatFields.ANNEXID])?'annexFirmat.annexID':__theForm.labels[AnnexFirmatFields.ANNEXID]}" /> &nbsp;(*)
              <c:if test="${not empty __theForm.help[AnnexFirmatFields.ANNEXID]}">
              <i class="icon-info-sign" title="${__theForm.help[AnnexFirmatFields.ANNEXID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="annexFirmat.annexID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,AnnexFirmatFields.ANNEXID)}" >
          <form:hidden path="annexFirmat.annexID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.annexFirmat.annexID,__theForm.listOfAnnexForAnnexID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,AnnexFirmatFields.ANNEXID)}" >
          <form:select id="annexFirmat_annexID"  onchange="if(typeof onChangeAnnexID == 'function') {  onChangeAnnexID(this); };"  cssClass="input-xxlarge" path="annexFirmat.annexID">
            <c:forEach items="${__theForm.listOfAnnexForAnnexID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
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
              <c:if test="${not empty __theForm.help[AnnexFirmatFields.FIRMAID]}">
              <i class="icon-info-sign" title="${__theForm.help[AnnexFirmatFields.FIRMAID]}" ></i>
              </c:if>
             </label>
            </td>
            <td>
          <form:errors path="annexFirmat.firmaID" cssClass="errorField alert alert-error" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,AnnexFirmatFields.FIRMAID)}" >
          <form:hidden path="annexFirmat.firmaID"/>
          <input type="text" readonly="true" class="input-xxlarge uneditable-input" value="${gen:findValue(__theForm.annexFirmat.firmaID,__theForm.listOfFirmaForFirmaID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,AnnexFirmatFields.FIRMAID)}" >
          <form:select id="annexFirmat_firmaID"  onchange="if(typeof onChangeFirmaID == 'function') {  onChangeFirmaID(this); };"  cssClass="input-xxlarge" path="annexFirmat.firmaID">
            <c:forEach items="${__theForm.listOfFirmaForFirmaID}" var="tmp">
            <form:option value="${tmp.key}" >${tmp.value}</form:option>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
